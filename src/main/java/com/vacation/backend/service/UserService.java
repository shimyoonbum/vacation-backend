package com.vacation.backend.service;


import com.vacation.backend.dto.request.UserDto;
import com.vacation.backend.model.Account;
import com.vacation.backend.model.Authority;
import com.vacation.backend.repository.AccountRepository;
import com.vacation.backend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Transactional
    public Account signup(UserDto userDto) {
        if (accountRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입된 유저입니다!");
        }
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Account user = Account.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return accountRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<Account> getMyUserWithAuthorities() {
    	return SecurityUtil.getCurrentUsername().flatMap(accountRepository::findOneWithAuthoritiesByUsername);
    }
}