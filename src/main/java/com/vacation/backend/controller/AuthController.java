package com.vacation.backend.controller;

import com.vacation.backend.dto.request.LoginDto;
import com.vacation.backend.dto.response.ResponseDto;
import com.vacation.backend.jwt.JwtFilter;
import com.vacation.backend.jwt.JwtTokenProvider;
import com.vacation.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManager;
    private final AccountService service;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authorize(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {

        // id와 pw 받아서 로그인 시도함. AuthenticationManager로 로그인시도하면
        // PrincipalDetailsService의 loadUserByUsername이 실행됩니다.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManager.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //인증 정보로 jwt 토큰 생성
        String jwt = tokenProvider.createToken(authentication);

        //토큰을 header 및 body에 넣습니다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        Map<String, Object> reMap = new HashMap<>();
        reMap.put("token", jwt);
        reMap.put("username", new LoginDto(loginDto.getUsername(), service.getMember(loginDto.getUsername())));

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(new ResponseDto<>(HttpStatus.OK.value(), reMap));
    }
}
