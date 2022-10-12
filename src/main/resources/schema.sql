## 샘플 데이터 자동 생성기 Script
## JPA DDL-AUTO NONE 일때만 다음 Script가 실행되도록 해야함!

SET foreign_key_checks = 0;
DELETE FROM vacation.tb_account_authority;
DELETE FROM vacation.tb_authority;
DELETE FROM vacation.tb_account;
DELETE FROM vacation.tb_vacation_management;
DELETE FROM vacation.tb_organization;
DELETE FROM vacation.tb_employee;
DELETE FROM vacation.tb_code;
DELETE FROM vacation.tb_code_group;

INSERT INTO vacation.tb_code_group
(group_code, group_name)
VALUES('OD', '조직구분');
INSERT INTO vacation.tb_code_group
(group_code, group_name)
VALUES('VK', '휴가유형');
INSERT INTO vacation.tb_code_group
(group_code, group_name)
VALUES('VS', '휴가승인상태');

INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('1', '최상위', '0', 'OD');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('2', '본부', '0', 'OD');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('3', '그룹', '0', 'OD');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('4', '팀', '0', 'OD');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VK1', '연차', '1', 'VK');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VK2', '반차', '0.5', 'VK');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VK3', '출산전후휴가', '0', 'VK');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VK4', '배우자출산휴가', '0', 'VK');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VK5', '경조사휴가', '0', 'VK');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VK6', '보건휴가', '0', 'VK');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VK7', '병가', '0', 'VK');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VS1', '대기', '0', 'VS');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VS2', '승인', '0', 'VS');
INSERT INTO vacation.tb_code
(code, code_name, code_value, group_code)
VALUES('VS3', '반려', '0', 'VS');

INSERT INTO vacation.tb_authority
(authority_name)
VALUES('ROLE_ADMIN');
INSERT INTO vacation.tb_authority
(authority_name)
VALUES('ROLE_USER');


INSERT INTO vacation.tb_organization
(org_code, org_name, org_upper, code)
VALUES('OR0001', '사장', NULL, '1');
INSERT INTO vacation.tb_organization
(org_code, org_name, org_upper, code)
VALUES('OR0002', '용산', 'OR0001', '2');
INSERT INTO vacation.tb_organization
(org_code, org_name, org_upper, code)
VALUES('OR0003', 'SI', 'OR0002', '3');
INSERT INTO vacation.tb_organization
(org_code, org_name, org_upper, code)
VALUES('OR0004', 'SM', 'OR0002', '3');
INSERT INTO vacation.tb_organization
(org_code, org_name, org_upper, code)
VALUES('OR0005', 'SI1', 'OR0003', '4');
INSERT INTO vacation.tb_organization
(org_code, org_name, org_upper, code)
VALUES('OR0006', 'SI2', 'OR0003', '4');
INSERT INTO vacation.tb_organization
(org_code, org_name, org_upper, code)
VALUES('OR0007', 'SM1', 'OR0004', '4');
INSERT INTO vacation.tb_organization
(org_code, org_name, org_upper, code)
VALUES('OR0008', 'SM2', 'OR0005', '4');

INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0001', 'ROLE_ADMIN');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0002', 'ROLE_ADMIN');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0003', 'ROLE_ADMIN');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0004', 'ROLE_ADMIN');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0005', 'ROLE_ADMIN');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0008', 'ROLE_ADMIN');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0011', 'ROLE_ADMIN');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0014', 'ROLE_ADMIN');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0001', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0002', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0003', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0004', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0005', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0006', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0007', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0008', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0009', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0010', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0011', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0012', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0013', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0014', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0015', 'ROLE_USER');
INSERT INTO vacation.tb_account_authority
(user_id, authority_name)
VALUES('ID0016', 'ROLE_USER');

INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0001', '박정식 대표', '사장', 'M', '1995-01-01 00:00:00', '01076451234', NULL, 'OR0001');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0002', '윤재수', '이사', 'M', '1995-01-01 00:00:00', '01075241244', 'E0001', 'OR0002');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0003', '홍인수', '부장', 'M', '1995-01-01 00:00:00', '01012341234', 'E0002', 'OR0003');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0004', '이상준', '부장', 'M', '1995-01-01 00:00:00', '01085644242', 'E0002', 'OR0004');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0005', '송찬식', '차장', 'M', '2001-09-08 00:00:00', '01099460013', 'E0003', 'OR0005');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0006', '김민섭', '과장', 'M', '2005-01-01 00:00:00', '01042539764', 'E0005', 'OR0005');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0007', '차경운', '사원', 'F', '2005-01-27 00:00:00', '01073668462', 'E0005', 'OR0005');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0008', '유상미', '차장', 'F', '2004-03-20 00:00:00', '01019948424', 'E0003', 'OR0006');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0009', '최우빈', '과장', 'M', '2004-07-01 00:00:00', '01055553333', 'E0008', 'OR0006');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0010', '조준형', '대리', 'M', '2004-12-15 00:00:00', '01066661038', 'E0008', 'OR0006');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0011', '심윤범', '과장', 'M', '2009-11-11 00:00:00', '01064258465', 'E0004', 'OR0007');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0012', '정희라', '대리', 'F', '2010-01-10 00:00:00', '01064268465', 'E0011', 'OR0007');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0013', '전여진', '사원', 'F', '2009-01-10 00:00:00', '01064743665', 'E0011', 'OR0007');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0014', '박우성', '차장', 'M', '2013-06-08 00:00:00', '01054238555', 'E0004', 'OR0008');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0015', '전강산', '과장', 'M', '2013-08-10 00:00:00', '01090011427', 'E0014', 'OR0008');
INSERT INTO vacation.tb_employee
(emp_code, emp_name, emp_rank, gender, join_date, phone, emp_upper, org_code)
VALUES('E0016', '김지훈', '대리', 'M', '2013-10-22 00:00:00', '01010046667', 'E0014', 'OR0008');

INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0001', 1, '$2a$10$.u7Wzv3AVJPB9KqDEuvr7eGr/MA1HSWm2CwKwxgnNd1dPqGN2.adi', 'pjs1234@gmail.com', 'E0001');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0002', 1, '$2a$10$EHP0j.Uis.o/wl3Owj/CQe7/2Du9zToVLEuhOK8PnnLE6pEsMzyX6', 'yth1234@gmail.com', 'E0002');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0003', 1, '$2a$10$Qddf.M6uPqBMnnEsp7UpP.xixtFo671be2ZSe6uG1d5D1Zd/UZWbW', 'hit1234@gmail.com', 'E0003');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0004', 1, '$2a$10$FkorSGP79LXNFv1si68hheMn72X7mmVTZ0vL08YfsesFxMN/9JEmG', 'lsj1234@gmail.com', 'E0004');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0005', 1, '$2a$10$NJTNPPUPSH/dbU7szKBmvumRtQPIYJVjZcrBiUbWKefd012b0cI3C', 'scg1234@gmail.com', 'E0005');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0006', 1, '$2a$10$33oSs2ygyguBAP0mm/B0He6OClIXFMjCzET4Axik6VHLVazERd0oq', 'kms1234@gmail.com', 'E0006');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0007', 1, '$2a$10$780myjCukUyUDChuCet13.OG4sIv2QudI4O/8p7YcmHMM704NVPhK', 'ckw1234@gmail.com', 'E0007');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0008', 1, '$2a$10$GoBC6tzAeIcwNRYDZdmbtOs8CvwkQY7XueH23mHTRJoV7NywCo1Ca', 'yjs1234@gmail.com', 'E0008');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0009', 1, '$2a$10$LI6GWFb.7ZB8WHoiiGV5mO7YP6mYNie6Ud9uI6Z0Cw8xjpbWdXhwa', 'cwb1234@gmail.com', 'E0009');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0010', 1, '$2a$10$KFZcZ2y8jbgmgdPHc/y/c.nNthRSgElv6GYCTVXOndiotinlQf6F6', 'jjh1234@gmail.com', 'E0010');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0011', 1, '$2a$10$Ya1yKuEPXDFc4aCvBf6uiOdBsCjVHyeXuyGJ2FIveZRLJ./CdkXt.', 'syb1234@gmail.com', 'E0011');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0012', 1, '$2a$10$0unYqyOnqR/4l/9aN/M/kO3//9VcNwzhE8sIpc6NGrFTedKZpNrs.', 'jhr1234@gmail.com', 'E0012');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0013', 1, '$2a$10$ZiTcdNGKWJf8Oc4P49rYYOXrmrQlpAkRNgZpMhDPUFAou0ZBSkAEe', 'jyj1234@gmail.com', 'E0013');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0014', 1, '$2a$10$pJ9VlJFfwYSmQQ42afI5AePOByLekfPr0XzBb0Lk2Z.N2BG05qzVa', 'pws1234@gmail.com', 'E0014');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0015', 1, '$2a$10$.G89USsF3QReIN2rBqbQ7.neBPru9VFg2V57449Zo1Qo6wfZCXS4u', 'jgs1234@gmail.com', 'E0015');
INSERT INTO vacation.tb_account
(user_id, activated, password, username, emp_code)
VALUES('ID0016', 1, '$2a$10$jZvVYj6yhatCorGgkOZLSu/QUZHH9ww4hJCQxMt7tBuwEX.vvN8ri', 'kjh1234@gmail.com', 'E0016');

INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(1, 15.0, 15.0, 0.0, '2022', 'E0001');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(2, 15.0, 15.0, 0.0, '2022', 'E0002');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(3, 15.0, 15.0, 0.0, '2022', 'E0003');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(4, 15.0, 15.0, 0.0, '2022', 'E0004');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(5, 15.0, 15.0, 0.0, '2022', 'E0005');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(6, 15.0, 15.0, 0.0, '2022', 'E0006');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(7, 15.0, 15.0, 0.0, '2022', 'E0007');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(8, 15.0, 15.0, 0.0, '2022', 'E0008');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(9, 15.0, 15.0, 0.0, '2022', 'E0009');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(10, 15.0, 15.0, 0.0, '2022', 'E0010');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(11, 15.0, 13.0, 2.0, '2022', 'E0011');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(12, 15.0, 15.0, 0.0, '2022', 'E0012');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(13, 15.0, 7.0, 8.0, '2022', 'E0013');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(14, 15.0, 15.0, 0.0, '2022', 'E0014');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(15, 15.0, 15.0, 0.0, '2022', 'E0015');
INSERT INTO vacation.tb_vacation_management
(vac_id, acq_days_num, res_days_num, use_days_num, `year`, emp_code)
VALUES(16, 15.0, 15.0, 0.0, '2022', 'E0016');

SET foreign_key_checks = 1;