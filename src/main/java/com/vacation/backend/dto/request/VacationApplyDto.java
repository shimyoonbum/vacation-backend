package com.vacation.backend.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacationApplyDto {
	
	private LocalDate regStartDate;
	private LocalDate regEndDate;
	private Double regNum;
	private String regReason;
	private String code;
	private String empCode;
}
