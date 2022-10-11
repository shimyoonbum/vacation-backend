package com.vacation.backend.service;

import com.vacation.backend.dto.request.SubmitRequestDto;
import com.vacation.backend.model.Notice;
import com.vacation.backend.repository.AccountRepository;
import com.vacation.backend.repository.EmployeeRepository;
import com.vacation.backend.repository.NoticeRepository;
import com.vacation.backend.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository repository;
    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;


    @Transactional(readOnly = true)
    public Page<Map<String, Object>> getNoticeList(Pageable pageable) {
        return repository.findNoticeList(pageable);
    }
    @Transactional(readOnly = true)
    public Map<String, Object> getNoticeDetail(int id) {
        return repository.findNoticeDetail(id);
    }

    public Notice submitNotice(SubmitRequestDto dto) throws IOException {

        String multipartFileUrl = null;
        String fileName = null;

        MultipartFile multipartFile = dto.getFile();

        if(multipartFile != null){

            multipartFileUrl = FileUtil.uploadFile(multipartFile);
            fileName = multipartFile.getOriginalFilename();
        }

        String empCode = accountRepository.findByUsername(dto.getUsername());

        Notice notice = Notice.builder()
                .notiStartDate(LocalDate.parse(dto.getStart()))
                .notiEndDate(LocalDate.parse(dto.getEnd()))
                .notiTilte(dto.getSubject())
                .notiText(dto.getContent())
                .delYn("N")
                .notiFile(fileName)
                .notiFileUrl(multipartFileUrl)
                .employee(employeeRepository.findByEmpCode(empCode))
                .build();
        return repository.save(notice);
    }

    public Map<String, Object> delNotice(int id, String flag) throws Throwable {

        switch (flag){

            case "F" :
                Notice notice = repository.findById(id).orElseThrow((Supplier<Throwable>) () -> null);
                FileUtil.deleteFile(notice);
                notice.setNotiFile(null);
                notice.setNotiFileUrl(null);
                repository.save(notice);

                break;
            case "N" :
                notice = repository.findById(id).orElseThrow((Supplier<Throwable>) () -> null);
                notice.setDelYn("Y");
                repository.save(notice);
                break;
        }

        return repository.findNoticeDetail(id);
    }

    public Notice updNotice(SubmitRequestDto dto) throws Throwable {

        Notice notice = repository.findById(dto.getId()).orElseThrow((Supplier<Throwable>) () -> null);

        notice.setNotiStartDate(LocalDate.parse(dto.getStart()));
        notice.setNotiEndDate(LocalDate.parse(dto.getEnd()));
        notice.setNotiTilte(dto.getSubject());
        notice.setNotiText(dto.getContent());

        MultipartFile multipartFile = dto.getFile();
        if(multipartFile != null){
            FileUtil.deleteFile(notice);
            String multipartFileUrl = FileUtil.uploadFile(multipartFile);
            String fileName = multipartFile.getOriginalFilename();
            notice.setNotiFile(fileName);
            notice.setNotiFileUrl(multipartFileUrl);
        }

        return repository.save(notice);
    }
}
