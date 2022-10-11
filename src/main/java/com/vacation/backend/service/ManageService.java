package com.vacation.backend.service;


import com.vacation.backend.common.BadRequestException;
import com.vacation.backend.model.Employee;
import com.vacation.backend.model.Register;
import com.vacation.backend.model.Vacation;
import com.vacation.backend.repository.AccountRepository;
import com.vacation.backend.repository.EmployeeRepository;
import com.vacation.backend.repository.RegisterRepository;
import com.vacation.backend.repository.VacationRepository;
import com.vacation.backend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManageService {
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    private final RegisterRepository registerRepository;
    private final VacationRepository vacationRepository;

    private static final Logger logger = LoggerFactory.getLogger(ManageService.class);
        
    @Transactional(readOnly = true)
	public Map<String, Object> getMember() {
    	
		Map<String, Object> member = new HashMap<>();
		
		String username = SecurityUtil.getCurrentUsername().get();
		String code = accountRepository.findByUsername(username);
		Employee codeUpper = employeeRepository.findByEmpCode(code);
		
		member.put("authorities", accountRepository.findAuthoritiesByUsername(username));
		member.put("emp", employeeRepository.findByEmpCodeOrEmpUpper(code, codeUpper));
				
		return member;
	}
    
    //휴가 승인 처리
	@Transactional
	public int update(Map<String, Object> map, Integer id) throws BadRequestException {
		Optional<Register> r = registerRepository.findById(id);
		String username = SecurityUtil.getCurrentUsername().get();
		String code = accountRepository.findByUsername(username);	
		
		r.ifPresent(updReg -> {
			updReg.setConfirmDate(LocalDateTime.now());
			updReg.setConfirmEmpCode(code);
			updReg.setRejectReason(map.get("reason").toString());
			updReg.setVsCode(map.get("vsCode").toString());
		});		
		
		if(map.get("vsCode").toString().equals("VS3"))
			return 1;
		//휴가 승인시 날짜 삭감 처리
		Employee codeVacation = employeeRepository.findByEmpCode(map.get("empCode").toString());
		
		Optional<Vacation> v = vacationRepository.findByEmpCode(codeVacation);
		 
		v.ifPresent(updReg -> {

			Double d = Double.parseDouble(map.get("regNum").toString());		
			
			if(updReg.getResDaysNum()-d < 0)
				throw new BadRequestException(HttpStatus.BAD_REQUEST, "사용 가능 연차 수 초과");
			
			updReg.setResDaysNum(updReg.getResDaysNum()-d);
			updReg.setUseDaysNum(updReg.getUseDaysNum()+d);
		});		
		
		return 1;
	}  	
}