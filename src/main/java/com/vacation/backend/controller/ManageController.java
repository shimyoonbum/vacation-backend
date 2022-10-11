package com.vacation.backend.controller;

import com.vacation.backend.service.ManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("/manage")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class ManageController {

    private final ManageService service;

    @GetMapping("/getMember")
    public ResponseEntity<?> getMember() {
        return ResponseEntity.ok(service.getMember());
    }

    //휴가 승인/반려
    @PutMapping("/doUpdate/{id}")
    public ResponseEntity<?> doUpdate(@RequestBody Map<String, Object> map, @PathVariable Integer id){
        Map<String, Integer> res = new HashMap<>();

        res.put("result", service.update(map, id));
        return ResponseEntity.ok(res);
    }
}
