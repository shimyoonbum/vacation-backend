package com.vacation.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_code")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Code {

	@Id	
	private String code;

	@OneToOne
	@JoinColumn(name = "groupCode")
	private CodeGroup codeGroup; //코드 그룹 조인
	
	@Column(name = "code_name")
	private String codeName;	//코드 명
	
	@Column(name = "code_value")
	private String codeValue;	//코드 값
}
