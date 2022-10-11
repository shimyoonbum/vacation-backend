package com.vacation.backend.repository;

import com.vacation.backend.controller.AuthController;
import com.vacation.backend.model.Account;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest// DB 와 관련된 컴포넌트만 메모리에 로딩
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryTest.class);
    @Autowired // DI
    private AccountRepository repository;

    @Test
    public void test() {
        // given
        String title = "ID0001";

        // when
        Account accountPS = repository.findByUserId("pjs1234@gmail.com");

        // then
        assertEquals(title, accountPS.getId());
    } // 트랜잭션 종료 (저장된 데이터를 초기화함)

    @Test
    public void test2() {
        // given
        String title = "ROLE_ADMIN";
        String title2 = "ROLE_USER";

        // when
        List<Map<String, Object>> accountPS = repository.findAuthoritiesByUsername("pjs1234@gmail.com");
        List<Object> results = accountPS.stream()
                .map(account ->account.get("authorityName"))
                .collect(Collectors.toList());

        // then
        assertEquals(title, results.get(0));
        assertEquals(title2, results.get(1));
    } // 트랜잭션 종료 (저장된 데이터를 초기화함)
}
