package com.vacation.backend.service;

import com.vacation.backend.dto.request.VacationApplyDto;
import com.vacation.backend.dto.response.VacationDto;
import com.vacation.backend.model.Employee;
import com.vacation.backend.model.Register;
import com.vacation.backend.model.Vacation;
import com.vacation.backend.repository.*;
import com.vacation.backend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VacationService {
	private final AccountRepository accountRepository;
	private final RegisterRepository registerRepository;
	private final EmployeeRepository employeeRepository;
	private final CodeRepository codeRepository;
	private final VacationRepository vacationRepository;

	private static final Logger logger = LoggerFactory.getLogger(VacationService.class);
	public VacationDto getMyUserInfo() {
		String username = SecurityUtil.getCurrentUsername().get();
		String code = accountRepository.findByUsername(username);
		Employee employee = employeeRepository.findByEmpCode(code);

		Optional<Vacation> vacation = vacationRepository.findByEmpCode(employee);
		if (vacation.isPresent()){
			Vacation vacPs = vacation.get();

			VacationDto.VacationDtoBuilder builder = VacationDto.builder()
					.empCode(vacPs.getEmpCode().getEmpCode())
					.orgCode(vacPs.getEmpCode().getOrganization().getCode().getCodeName())
					.orgName(vacPs.getEmpCode().getEmpRank())
					.joinDate(vacPs.getEmpCode().getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
					.daysNum(vacPs.getResDaysNum())
					.register(employee.getRegister());

			if(vacPs.getEmpCode().getEmpUpper() == null){
				builder.empName("없음");
			}else{
				builder.empName(vacPs.getEmpCode().getEmpUpper().getEmpName());
			}

			return builder.build();
		}else
			throw new RuntimeException("데이터가 존재하지 않음.");
	}

	public List<Register> getMyApplyInfo() {
		String username = SecurityUtil.getCurrentUsername().get();
		String code = accountRepository.findByUsername(username);
		Employee employee = employeeRepository.findByEmpCode(code);
		return registerRepository.findTop5ByEmployee(employee, Sort.by(Sort.Direction.DESC, "regDate"));
	}

	//일괄 삭제
	@Transactional
	public int deleteById(Map<String, Object> list) {
		int count = 0;

		List<Integer> obj = (List<Integer>) list.get("ids");
		String empCode = list.get("code").toString();

		// deleteInBatch를 활용하여 where reg_id=? or reg_id=? or reg_id=? 와 같이 일괄 사제가 가능하다.
		// registerRepository.deleteInBatch(registerRepository.findAllById(Lists.newArrayList(obj)));

		for (Integer o : obj) {
			int a = registerRepository.deleteById(o, empCode);
			count += a;
		}

		return count;
	}

	//개별 삭제
	@Transactional
	public boolean deleteById(Integer id) throws Exception {
		try {
			registerRepository.deleteById(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	//휴가 등록
	@Transactional
	public Register apply(VacationApplyDto dto) {
		Register r = Register.builder()
				.regDate(LocalDateTime.now())
				.regReason(dto.getRegReason())
				.regNum(dto.getRegNum())
				.regEndDate(dto.getRegEndDate())
				.regStartDate(dto.getRegStartDate())
				.vkCode(codeRepository.findByCode(dto.getCode()).get())
				.vsCode("VS1")
				.employee(employeeRepository.findByEmpCode(dto.getEmpCode()))
				.build();

		return registerRepository.save(r);
	}
	
	//휴가 수정
	@Transactional
	public int update(VacationApplyDto dto, Integer id){
		Optional<Register> r = registerRepository.findById(id);
		
		r.ifPresent(updReg -> {
			//반차 이외에 일수가 1개 까이는 현상 방지
			if(dto.getCode().equals("VK2")) {
				updReg.setRegEndDate(dto.getRegEndDate());
				updReg.setRegStartDate(dto.getRegStartDate());
			}else {
				updReg.setRegEndDate(dto.getRegEndDate().plusDays(1));
				updReg.setRegStartDate(dto.getRegStartDate().plusDays(1));
			}
			updReg.setRegReason(dto.getRegReason());				
			updReg.setRegNum(dto.getRegNum());
			updReg.setVkCode(codeRepository.findByCode(dto.getCode()).get());
		});		

		return 1;
	}

	//휴가 수정
	@Transactional
	public Register update_v2(VacationApplyDto dto, Integer id){

		Optional<Register> registerOptional = registerRepository.findById(id);
		if(!registerOptional.isPresent())
			throw new RuntimeException("데이터가 존재하지 않음.");

		Register register = registerOptional.get();

		register.setRegEndDate(dto.getRegEndDate());
		register.setRegStartDate(dto.getRegStartDate());
		register.setRegReason(dto.getRegReason());
		register.setRegNum(dto.getRegNum());
		register.setVkCode(codeRepository.findByCode(dto.getCode()).get());

		return register;
	}
}