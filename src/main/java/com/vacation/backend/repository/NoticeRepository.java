package com.vacation.backend.repository;

import com.vacation.backend.model.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

	@Query(value = "select row_number() over (ORDER BY noti_id desc) as rownum, te.emp_name, tn.noti_id, tn.noti_file, tn.noti_text, tn.noti_title, \r\n" +
			"CASE WHEN\r\n" +
			"        INSTR(DATE_FORMAT(tn.reg_date, '%Y-%m-%d %p %h:%i'), 'PM') > 0\r\n" +
			"        THEN\r\n" +
			"        REPLACE(DATE_FORMAT(tn.reg_date, '%Y-%m-%d %p %h:%i'), 'PM', '오후')\r\n" +
			"        ELSE\r\n" +
			"        REPLACE(DATE_FORMAT(tn.reg_date, '%Y-%m-%d %p %h:%i'), 'AM', '오전')\r\n" +
			"        END AS reg_date\r\n" +
			"from tb_notice tn \r\n" +
			"inner join tb_employee te on tn.emp_code = te.emp_code\r\n" +
			"where date(now()) between date(tn.noti_start_date) and date(tn.noti_end_date)"+
			"and del_yn = 'N'"
			, nativeQuery = true
			, countQuery = "select count(*) from tb_notice\n" +
			"where date(now()) between date(noti_start_date) and date(noti_end_date)"+
			"and del_yn = 'N'")
	Page<Map<String, Object>> findNoticeList(Pageable pageable);
	@Query(value = "select te.emp_name, ta.username, tn.noti_id, tn.noti_file, tn.noti_file_url as file_url,\r\n " +
			"tn.noti_text, tn.noti_title, tn.noti_start_date, tn.noti_end_date,\r\n" +
			"CASE WHEN\r\n" +
			"        INSTR(DATE_FORMAT(tn.reg_date, '%Y-%m-%d %p %h:%i'), 'PM') > 0\r\n" +
			"        THEN\r\n" +
			"        REPLACE(DATE_FORMAT(tn.reg_date, '%Y-%m-%d %p %h:%i'), 'PM', '오후')\r\n" +
			"        ELSE\r\n" +
			"        REPLACE(DATE_FORMAT(tn.reg_date, '%Y-%m-%d %p %h:%i'), 'AM', '오전')\r\n" +
			"        END AS reg_date\r\n" +
			"from tb_notice tn \r\n" +
			"inner join tb_employee te on tn.emp_code = te.emp_code\r\n" +
			"inner join tb_account ta on tn.emp_code = ta.emp_code\r\n" +
			"where tn.noti_id = ?1"
			, nativeQuery = true)
	Map<String, Object> findNoticeDetail(int id);
}