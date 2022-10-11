package com.vacation.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_notice")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "noti_id", unique = true, nullable = false)
	private Integer id;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	@Column(name = "reg_date" , updatable = false)
	private LocalDateTime regDate = LocalDateTime.now();		//공지사항 등록일

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	@Column(name = "upd_date")
	private LocalDateTime updDate = LocalDateTime.now();		//공지사항 수정일

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "noti_start_date")
	private LocalDate notiStartDate;	//공지시작일

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "noti_end_date")
	private LocalDate notiEndDate;		//종료일

	@Column(name = "noti_title")
	private String notiTilte; 			//공지 내용
	
	@Column(name = "noti_text")
	private String notiText; 			//공지 내용
		
	@Column(name = "noti_file")
	private String notiFile;			//첨부파일

	@Column(name = "noti_file_url")
	private String notiFileUrl;			//첨부파일 경로

	@Column(name = "del_yn", columnDefinition = "varchar(1) default 'N'")
	private String delYn;				//삭제 여부

	@ManyToOne
	@JoinColumn(name = "emp_code")
	private Employee employee; 			//사원 테이블 조인컬럼
}