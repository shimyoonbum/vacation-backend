package com.vacation.backend.repository;

import com.vacation.backend.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, String> {
	Optional<Code> findByCode(String code);
}