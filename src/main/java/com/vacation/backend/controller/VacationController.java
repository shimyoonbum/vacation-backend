package com.vacation.backend.controller;

import java.util.HashMap;
import java.util.Map;

import com.vacation.backend.dto.request.VacationApplyDto;
import com.vacation.backend.dto.response.ResponseDto;
import com.vacation.backend.model.Register;
import com.vacation.backend.service.VacationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vacation")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class VacationController {

    private static final Logger logger = LoggerFactory.getLogger(VacationController.class);

    private final VacationService service;

    @GetMapping("/user")
    public ResponseEntity<?> getMyUserInfo() {
        return ResponseEntity.ok()
                .body(new ResponseDto<>(HttpStatus.OK.value(), service.getMyUserInfo()));
    }

    @GetMapping("/apply5")
    public ResponseEntity<?> getMyApplyInfo() {
        return ResponseEntity.ok()
                .body(new ResponseDto<>(HttpStatus.OK.value(), service.getMyApplyInfo()));
    }

    //휴가 등록
    @PostMapping("/doApply")
    public ResponseEntity<?> doApply(@RequestBody VacationApplyDto dto) {
        Register result = service.apply(dto);

        return ResponseEntity.ok(result);
    }

    //휴가 수정
    @PutMapping("/doUpdate/{id}")
    public ResponseEntity<?> doUpdate(@RequestBody VacationApplyDto dto, @PathVariable Integer id) {
        Map<String, Integer> res = new HashMap<>();
        int count = 0;

        try {
            count = service.update(dto, id);
        } catch (Exception e) {
            res.put("result", 0);
            return ResponseEntity.ok(res);
        }

        res.put("result", count);
        return ResponseEntity.ok(res);
    }

    //휴가 수정
    @PutMapping("/doUpdate_v2/{id}")
    public ResponseEntity<?> doUpdate_v2(@RequestBody VacationApplyDto dto, @PathVariable Integer id) {
        return ResponseEntity.ok(service.update_v2(dto, id));
    }

    //일괄 삭제
    @DeleteMapping("/deleteReg")
    public ResponseEntity<?> deleteReg(@RequestBody Map<String, Object> list) throws Exception{
        int count = service.deleteById(list);

        Map<String, Integer> res = new HashMap<>();
        res.put("result", count);
        return ResponseEntity.ok(res);
    }

    //개별 삭제
    @DeleteMapping("/deleteReg/{id}")
    public ResponseEntity<?> deleteOneReg(@PathVariable Integer id) throws Exception{
        boolean res = service.deleteById(id);

        if(!res) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}

