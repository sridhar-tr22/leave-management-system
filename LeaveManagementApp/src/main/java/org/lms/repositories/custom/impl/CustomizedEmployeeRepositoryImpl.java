package org.lms.repositories.custom.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.lms.dto.EmployeeCreateRequest;
import org.lms.entities.EmployeeDetail;
import org.lms.repositories.EmployeeRepository;
import org.lms.repositories.custom.CustomizedEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class CustomizedEmployeeRepositoryImpl implements CustomizedEmployeeRepository {

	@Lazy
	@Autowired
	private EmployeeRepository employeeRepository;
	private EmployeeDetail employeeDetail;

	public CustomizedEmployeeRepositoryImpl() {
		employeeDetail = new EmployeeDetail();
	}

	@Override
	public void saveEmployee(EmployeeCreateRequest request) {
		Objects.requireNonNull(request, "employee create request");
		employeeDetail.setEmail(request.getEmail());
		employeeDetail.setFirstName(request.getFirstName());
		employeeDetail.setLastName(request.getLastName());
		employeeDetail.setPassword(request.getPassword());
		employeeDetail.setCreatedDate(LocalDateTime.now());
		employeeDetail.setCreatedUser("ADMIN");
		EmployeeDetail saveAndFlush = employeeRepository.saveAndFlush(employeeDetail);

	}

}
