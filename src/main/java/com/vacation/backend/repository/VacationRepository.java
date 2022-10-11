package com.vacation.backend.repository;

import com.vacation.backend.model.Employee;
import com.vacation.backend.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacationRepository extends JpaRepository<Vacation, Employee> {

	Optional<Vacation> findByEmpCode(Employee codeVacation);
	
}