package org.lms.restcontrollers;

import java.time.LocalDate;

import org.lms.dto.DashboardResponse;
import org.lms.dto.LeaveRequest;
import org.lms.dto.LeaveResponse;
import org.lms.services.LeaveApplicationService;
import org.lms.services.LeaveDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private LeaveApplicationService applicationService;

	@Autowired
	private LeaveDashboardService dashboardService;

	@GetMapping(path = "/{employeeId}/dashboard")
	public ResponseEntity<DashboardResponse> getDashboard(@PathVariable(name = "employeeId") Long employeeId) {
		DashboardResponse dashboardForEmployee = dashboardService.getDashboardForEmployee(employeeId);
		ResponseEntity<DashboardResponse> responseEntity = new ResponseEntity<>(dashboardForEmployee, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping(path = "/leaves")
	public ResponseEntity<LeaveResponse> applyLeave(@RequestBody LeaveRequest leaveRequest) {
		LeaveResponse applyLeave = applicationService.applyLeave(leaveRequest);
		ResponseEntity<LeaveResponse> responseEntity = new ResponseEntity<LeaveResponse>(applyLeave, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(path = "/leaves/sample/request")
	public ResponseEntity<LeaveRequest> sampleRequest() {
		LeaveRequest request = new LeaveRequest();
		request.setEmployeeId(1234l);
		request.setEmployeeName("Name");
		request.setLeaveType("AL");
		request.setFromDate(LocalDate.now());
		request.setToDate(LocalDate.now());
		request.setAction("Apply");
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
}
