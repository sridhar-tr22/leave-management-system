package org.lms.repositories.custom;

import org.lms.dto.EmployeeCreateRequest;

public interface CustomizedEmployeeRepository {

	public void saveEmployee(EmployeeCreateRequest request);
	
}
