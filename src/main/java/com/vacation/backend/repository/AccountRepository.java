package com.vacation.backend.repository;

import com.vacation.backend.model.Account;
import com.vacation.backend.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
	@EntityGraph(attributePaths = "authorities")
	Optional<Account> findOneWithAuthoritiesByUsername(String username);	

	@Query(value = "select b.authority_name authorityName \r\n" +
			"from tb_account a \r\n" + 
			"left outer join tb_account_authority b on a.user_id=b.user_id\r\n" + 
			"where username = :username", nativeQuery = true)
	List<Map<String, Object>> findAuthoritiesByUsername(@Param(value = "username") String username);
	
	@Query(value = "select emp_code from tb_account where username = :username", nativeQuery = true)
	String findByUsername(@Param(value = "username") String username);

	@Query(value = "select * from tb_account where username = :username", nativeQuery = true)
	Account findByUserId(@Param(value = "username") String username);
}