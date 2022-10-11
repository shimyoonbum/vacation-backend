package com.vacation.backend.service;

import com.vacation.backend.model.Account;
import com.vacation.backend.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Account getUserInfo(String username) {
        return accountRepository.findByUserId(username);
    }

    @Transactional(readOnly = true)
    public List<Object> getMember(String username) {
        List<Map<String, Object>> member = accountRepository.findAuthoritiesByUsername(username);
        return member.stream()
                .map(account ->account.get("authorityName"))
                .collect(Collectors.toList());
    }
}
