package zerobase.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JpaMemoRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class JpaMemoRepositoryTest {
    @Autowired
    JpaMemoRepository jpaMemoRepository;

    @Test
    void instertMemoTest() {
        Memo newMemo = new Memo(10, "this is jpa memo");

        jpaMemoRepository.save(newMemo);

        List<Memo> memoList = jpaMemoRepository.findAll();
        Assertions.assertTrue(!memoList.isEmpty());
    }

    @Test
    void findMemoTest() {
        Memo newMemo = new Memo(11, "jpa");
        Memo memo = jpaMemoRepository.save(newMemo);
        Optional<Memo> result = jpaMemoRepository.findById(memo.getId());
        Assertions.assertEquals(result.get().getText(), "jpa");
    }
}