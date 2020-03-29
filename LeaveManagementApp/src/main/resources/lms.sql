CREATE SCHEMA `leaveMS`;


CREATE TABLE `leavems`.`leave_type` (
  `leave_type_id` INT NOT NULL,
  `leave_type_name` VARCHAR(20) NOT NULL,
  `leave_type_count` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`leave_type_id`));

/* Leave Type*/
INSERT INTO `leavems`.`leave_type` (`leave_type_id`, `leave_type_name`, `leave_type_count`) VALUES ('1', 'Annual Leave', '15');
INSERT INTO `leavems`.`leave_type` (`leave_type_id`, `leave_type_name`, `leave_type_count`) VALUES ('2', 'Restricted Holiday', '6');
INSERT INTO `leavems`.`leave_type` (`leave_type_id`, `leave_type_name`, `leave_type_count`) VALUES ('3', 'Personal Leave', '1');


INSERT INTO `leavems`.`leave_type` (`leave_type_id`, `leave_type_count`, `leave_type_name`, `leave_type_code`) VALUES ('1', '15', 'Annual Leave', 'AL');
INSERT INTO `leavems`.`leave_type` (`leave_type_id`, `leave_type_count`, `leave_type_name`, `leave_type_code`) VALUES ('2', '6', 'Restricted Holiday', 'RH');
INSERT INTO `leavems`.`leave_type` (`leave_type_id`, `leave_type_count`, `leave_type_name`, `leave_type_code`) VALUES ('3', '1', 'Personal Leave', 'PL');


insert into leavems.employee_details (created_date, created_user, email, first_name, last_name, manager_id, password, employee_id) values ('2020-03-28', "ADMIN", 
"aaa@sample.org", "AAA", "ZZZ", "11220", "pwd", 11501);
insert into leavems.employee_details (created_date, created_user, email, first_name, last_name, manager_id, password, employee_id) values ('2020-03-28', "ADMIN", 
"bbb@sample.org", "BBB", "YYY", "11220", "pwd", 11502);
insert into leavems.employee_details (created_date, created_user, email, first_name, last_name, manager_id, password, employee_id) values ('2020-03-28', "ADMIN", 
"ccc@sample.org", "CCC", "XXX", "11220", "pwd", 11503);
