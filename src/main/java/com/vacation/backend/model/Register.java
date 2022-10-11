package com.vacation.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_vacation_apply")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Register {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reg_id", unique = true, nullable = false)
	private Integer id; 				//휴가 신청 목록

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	@Column(name = "reg_date")
	private LocalDateTime regDate;		//휴가 신청일

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "reg_start_date")
	private LocalDate regStartDate;		//시작일

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "reg_end_date")
	private LocalDate regEndDate;		//종료일

	@Column(name = "reg_num")
	private Double regNum;				//휴가일수
	
	@Column(name = "reg_reason")
	private String regReason; 			//신청 사유
		
	@Column(name = "confirm_emp_code")
	private String confirmEmpCode;		//승인자 코드
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	@Column(name = "confirm_date")
	private LocalDateTime confirmDate;	//휴가 승인일

	@Column(name = "reject_reason")
	private String rejectReason;		//반려 사유
	
	@OneToOne
	@JoinColumn(name = "code")
	private Code vkCode; 				//휴가 요청 상태코드

	@Column(name = "vs_code")
	private String vsCode;				//휴가 승인 상태코드

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "emp_code")
	private Employee employee; 			//사원 테이블 조인컬럼
}