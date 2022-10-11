package com.vacation.backend.dto.response;

import com.vacation.backend.model.Register;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacationDto{
	String orgCode;
	String orgName;
	String joinDate;
	String empName;
	String empCode;
	Double daysNum;
	List<Register> register;
}
