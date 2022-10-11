package com.vacation.backend.repository;

import com.vacation.backend.model.Notice;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest// DB 와 관련된 컴포넌트만 메모리에 로딩
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NoticeRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(NoticeRepositoryTest.class);
    @Autowired // DI
    private NoticeRepository repository;

    @Test
    public void test() {
        //given
        Pageable paging = PageRequest.of(0,5, Sort.Direction.DESC, "noti_id");

        // when
        Page<Map<String, Object>> result = repository.findNoticeList(paging);

        // then
        assertEquals(5, result.get().count());
    } // 트랜잭션 종료 (저장된 데이터를 초기화함)
}
