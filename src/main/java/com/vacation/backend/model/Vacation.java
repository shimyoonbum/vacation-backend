package com.vacation.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_vacation_management")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vac_id", unique = true, nullable = false)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "emp_code", unique = true, nullable = false)
	private Employee empCode;			//사원 코드
	
	private String year;				//발생연도
	
	@Column(name = "acq_days_num")
	private Double acqDaysNum;			//발생일수
	
	@Column(name = "use_days_num")
	private Double useDaysNum;			//사용일수
	
	@Column(name = "res_days_num")
	private Double resDaysNum;			//잔여일수


}