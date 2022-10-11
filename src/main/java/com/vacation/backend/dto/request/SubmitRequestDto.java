package com.vacation.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitRequestDto {

    private int id;
    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String subject;

    @NotBlank(message = "게시글 본문을 입력해주세요.")
    private String content;
    private String start;
    private String end;
    @NotNull(message = "사용자 아이디를 입력해주세요.")
    private String username;
    @MaybeNull
    private MultipartFile file;
}