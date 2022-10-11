package com.vacation.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_account")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	@JsonIgnore
	@Id
	@Column(name = "user_id", nullable = false)
	private String id; //아이디 코드

	@Column(name = "username", length = 50)
	private String username;

	@JsonIgnore
	@Column(name = "password", length = 100)
	private String password;

	@JsonIgnore
	@Column(name = "activated")
	private boolean activated;

	@OneToOne
	@JoinColumn(name = "empCode")
	private Employee employee;//사원 조인 컬럼

	@ManyToMany
	@JoinTable(name = "tb_account_authority",
			joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "authority_name") })
	private Set<Authority> authorities;

}
