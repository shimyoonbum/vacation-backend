package com.vacation.backend.controller;

import com.vacation.backend.dto.request.SubmitRequestDto;
import com.vacation.backend.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public ResponseEntity<?> getNoticeInfo(@PageableDefault(size = 10, sort="noti_id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(noticeService.getNoticeList(pageable));
    }

    @GetMapping("/noticeDetail")
    public ResponseEntity<?> getNoticeDetailInfo(@RequestParam int id) {
        return ResponseEntity.ok(noticeService.getNoticeDetail(id));
    }

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> submit(@ModelAttribute @Valid SubmitRequestDto dto) throws IOException {
        return ResponseEntity.ok(noticeService.submitNotice(dto));
    }

    @PutMapping("/noticeUpdate")
    public ResponseEntity<?> noticeUpdate(@ModelAttribute @Valid SubmitRequestDto dto) throws Throwable {
        return ResponseEntity.ok(noticeService.updNotice(dto));
    }

    @GetMapping("/noticeDelete")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> noticeFileDelete(@RequestParam int id) throws Throwable {
        return ResponseEntity.ok(noticeService.delNotice(id, "F"));
    }

    @DeleteMapping("/noticeDelete")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> noticeDelete(@RequestParam int id) throws Throwable {
        return ResponseEntity.ok(noticeService.delNotice(id, "N"));
    }
}
