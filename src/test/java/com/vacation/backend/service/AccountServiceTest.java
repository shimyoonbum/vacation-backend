package com.vacation.backend.service;


import com.vacation.backend.model.Account;
import com.vacation.backend.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    //3개(서비스, 레포지토리, 메일 Util 을 가짜환경 메모리에 올림)
    @InjectMocks
    private AccountService service;

    @Mock
    private AccountRepository repository;

    @Test
    public void test() {
        // given
//        Long id = 1L;
//
//        // stub
//        Account book = new Account(1L, "junit강의", "메타코딩");
//        Optional<Account> bookOP = Optional.of(book);
//        when(repository.findById(id)).thenReturn(bookOP);
//
//        // when
//        BookRespDto bookRespDto = service.책한건보기(id);
//
//        // then
//        assertThat(bookRespDto.getTitle()).isEqualTo(book.getTitle());
//        assertThat(bookRespDto.getAuthor()).isEqualTo(book.getAuthor());
    }
}
