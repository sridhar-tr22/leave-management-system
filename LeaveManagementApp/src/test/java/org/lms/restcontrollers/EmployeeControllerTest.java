package org.lms.restcontrollers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasKey;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lms.dto.DashboardResponse;
import org.lms.dto.LeaveDashboardResponse;
import org.lms.dto.LeaveRequest;
import org.lms.dto.LeaveResponse;
import org.lms.dto.ResponseMessage;
import org.lms.services.LeaveApplicationService;
import org.lms.services.LeaveDashboardService;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest
class EmployeeControllerTest {

	@Autowired
	private EmployeeController controller;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LeaveApplicationService applicationService;

	@MockBean
	private LeaveDashboardService dashboardService;

	private DashboardResponse dashboardForEmployee;

	StringBuffer URI;

	LeaveRequest leaveRequest;
	LeaveResponse leaveResponse;

	StringBuffer requestJson;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		dashboardForEmployee = new DashboardResponse();
		URI = new StringBuffer("/employees");
		leaveRequest = new LeaveRequest(567190L, "Sample_Name", "AL", LocalDate.of(2020, 4, 6),
				LocalDate.of(2020, 4, 8), "Apply");

		leaveResponse = new LeaveResponse();
		leaveResponse.setEmployeeId(567190L);
		leaveResponse.setFromDate(LocalDate.of(2020, 4, 6));
		leaveResponse.setToDate(LocalDate.of(2020, 4, 8));
		leaveResponse.setNumberofDays(3);
		leaveResponse.setStatus("APPLIED");
		leaveResponse.setLeaveType("AL");
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setStatusCode("200");
		responseMessage.setStatusMessage("leave is applied successfully.");
		leaveResponse.setMessage(responseMessage);

		requestJson = new StringBuffer();
		requestJson.append("{\"employeeId\": 1234,").append("\"employeeName\": \"Name\",")
				.append("\"leaveType\": \"AL\",").append("\"fromDate\": [2020,3,30],")
				.append("\"toDate\": [2020,3,30],").append("\"action\": \"Apply\"}");
	}

	@Test
	void testGetDashboard() throws Exception {
		long employeeId = 12156l;
		dashboardForEmployee.setEmployeeId(employeeId);
		dashboardForEmployee
				.setLeaveDashboardList(Arrays.asList(new LeaveDashboardResponse(), new LeaveDashboardResponse()));
		Mockito.when(dashboardService.getDashboardForEmployee(ArgumentMatchers.anyLong()))
				.thenReturn(dashboardForEmployee);

		URI.append("/{employeeId}/dashboard");

		mockMvc.perform(get(URI.toString(), employeeId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.leaveDashboardList.[0]", hasKey("employeeId"))).andExpect(status().isOk());
	}

	@Test
	void testApplyLeave() throws Exception {
		URI.append("/leaves");
		Mockito.when(applicationService.applyLeave(Mockito.any(LeaveRequest.class))).thenReturn(leaveResponse);
		
		mockMvc.perform(post(URI.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson.toString()))
			.andExpect(jsonPath("$.message.statusCode").isString())
			.andExpect(status().isOk());
	}

	@Test
	void testSampleRequest() throws Exception {
		URI.append("/leaves/sample/request");
		mockMvc.perform(get(URI.toString()).contentType(MediaType.APPLICATION_JSON)).andDo(print());

	}
}
