package zerobase.weather.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;
import java.util.List;

import javax.sql.DataSource;

@Repository
public class JdbcMemoRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public JdbcMemoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Memo save(Memo memo) {
        String sql = "INSERT INTO memo VALUES (?, ?)";
        jdbcTemplate.update(sql, memo.getId(), memo.getText());
        return memo;
    }
    private RowMapper<Memo> memoRowMapper() {
        return(rs, rowNum) -> new Memo(
                rs.getInt("id"),
                rs.getString("text")
        );
    }
    public List<Memo> findAll() {
        String sql = "SELECT * FROM memo";
        return jdbcTemplate.query(sql, memoRowMapper());
    }
    public Memo findById(int id) {
        String sql = "SELECT * FROM memo WHERE id = ?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst().get();
    }
}
