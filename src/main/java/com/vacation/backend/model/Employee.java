package com.vacation.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_employee")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	@Column(name = "emp_code", unique = true, nullable = false)
	private String empCode;					//사원 코드
	
	@Column(name = "emp_name")
	private String empName;					//이름
	
	@Column(name = "emp_rank")
	private String empRank;					//직위
	
	private String gender;					//성별

	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private String phone;					//번호
	
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	@Column(name = "join_date")
	private LocalDateTime joinDate;			//입사일
	
	@OneToOne
	@JoinColumn(name = "emp_upper")
	private Employee empUpper;				//상위자 코드

	@OneToOne
	@JoinColumn(name = "orgCode")
	private Organization organization;		//조직 코드
	
	@OneToOne(mappedBy = "empCode")
	@JsonIgnoreProperties({"empCode"})
	private Vacation vacation;				//휴가 조인 컬럼
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnoreProperties({"employee"})
	private List<Register> register;		//연차 등록 조인 컬럼

	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	private List<Notice> notice;		//연차 등록 조인 컬럼
}