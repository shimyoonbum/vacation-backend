package com.vacation.backend.repository;

import com.vacation.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	Employee findByEmpCode(String code);

//	@EntityGraph(attributePaths = {"employee", "empCode"})
	List<Employee> findByEmpCodeOrEmpUpper(String code, Employee code2);
}