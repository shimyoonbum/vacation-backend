package com.vacation.backend.repository;

import com.vacation.backend.model.Employee;
import com.vacation.backend.model.Register;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Integer> {
	@Modifying
	@Query(value = "delete from tb_vacation_apply where reg_id = :id and emp_code = :code ", nativeQuery = true)
	int deleteById(@Param(value = "id") Integer id, @Param(value = "code") String code);

	List<Register> findTop5ByEmployee(Employee employee, Sort regDate);
}