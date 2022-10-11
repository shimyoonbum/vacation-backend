package com.vacation.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_code_group")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeGroup {

	@JsonIgnore
	@Id	
	@Column(name = "group_code", unique = true, nullable = false)
	private String groupCode;	//그룹 코드
	
	@Column(name = "group_name")
	private String groupName;	//그룹 코드 이름
}