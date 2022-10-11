package com.vacation.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_organization")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

	@JsonIgnore
	@Id
	@Column(name = "org_code", length = 50, unique = true, nullable = false)
	private String orgCode;

	@Column(name = "org_name", length = 50)
	private String orgName;
	
	@OneToOne
	@JoinColumn(name = "code")
	private Code code;

	@Column(name = "org_upper", length = 50)
	private String orgUpper;
}