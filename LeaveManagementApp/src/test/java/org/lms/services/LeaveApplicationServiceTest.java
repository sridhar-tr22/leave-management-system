package org.lms.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lms.dto.LeaveDashboardResponse;
import org.lms.dto.LeaveRequest;
import org.lms.entities.LeaveApplication;
import org.lms.exceptions.FaultException;
import org.lms.repositories.LeaveApplicationRepository;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class LeaveApplicationServiceTest {

	@Autowired
	private LeaveApplicationService applicationService;

	@MockBean
	private LeaveApplicationRepository applicationRepository;

	@MockBean
	private LeaveDashboardService dashboardService;

	LeaveRequest leaveRequest;
	LeaveRequest leaveRequest_for_30_days;
	LeaveDashboardResponse dashboardResponseAL;
	LeaveDashboardResponse dashboardResponseRH;
	LeaveDashboardResponse dashboardResponsePL;
	LeaveApplication application;

	@BeforeEach
	void setUp() throws Exception {
		leaveRequest = new LeaveRequest(1234L, "Name", "AL", LocalDate.of(2020, 04, 10), LocalDate.of(2020, 04, 12),
				"Apply");
		leaveRequest_for_30_days = new LeaveRequest(1234L, "Name", "AL", LocalDate.of(2020, 04, 1),
				LocalDate.of(2020, 04, 30), "Apply");
		dashboardResponseAL = new LeaveDashboardResponse();
		dashboardResponseAL.setDashboardId(1L);
		dashboardResponseAL.setEmployeeId(1234L);
		dashboardResponseAL.setLeaveType("AL");
		dashboardResponseAL.setTotalLeaves(16);
		dashboardResponseAL.setRemainingLeaves(16);
		dashboardResponseAL.setConsumedLeaves(0);

		dashboardResponseRH = new LeaveDashboardResponse();
		dashboardResponseRH.setDashboardId(2L);
		dashboardResponseRH.setEmployeeId(1234L);
		dashboardResponseRH.setLeaveType("RH");
		dashboardResponseRH.setTotalLeaves(5);
		dashboardResponseRH.setRemainingLeaves(5);
		dashboardResponseRH.setConsumedLeaves(0);

		dashboardResponsePL = new LeaveDashboardResponse();
		dashboardResponsePL.setDashboardId(3L);
		dashboardResponsePL.setEmployeeId(1234L);
		dashboardResponsePL.setLeaveType("PL");
		dashboardResponsePL.setTotalLeaves(1);
		dashboardResponsePL.setRemainingLeaves(1);
		dashboardResponsePL.setConsumedLeaves(0);
	}

	@Test
	void testApplyLeave_whenDashboardRecord_Available() {

		Mockito.when(
				dashboardService.getByEmployeeIdAndLeaveType(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(dashboardResponseAL));

		// Mockito.when(applicationRepository.saveAndFlush(Mockito.any(LeaveApplication.class))).thenReturn(arg0);

		applicationService.applyLeave(leaveRequest);

	}

	@Test
	void testApplyLeave_whenDashboardRecord_Not_Available() {

		Mockito.when(
				dashboardService.getByEmployeeIdAndLeaveType(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString()))
				.thenReturn(Optional.empty());

		List<LeaveDashboardResponse> asList = Arrays.asList(dashboardResponseAL, dashboardResponseRH,
				dashboardResponsePL);
		Mockito.when(dashboardService.populateDashboard(ArgumentMatchers.anyLong())).thenReturn(asList);

		applicationService.applyLeave(leaveRequest);
	}

	@Test
	void testApplyLeave_whenAppliedLeaves_GreaterThan_TotalAvailableLeave() {

		Mockito.when(
				dashboardService.getByEmployeeIdAndLeaveType(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(dashboardResponseAL));

		// Mockito.when(applicationRepository.saveAndFlush(Mockito.any(LeaveApplication.class))).thenReturn(arg0);

		FaultException exception = assertThrows(FaultException.class, () -> {
			applicationService.applyLeave(leaveRequest_for_30_days);
		});
		
		assertEquals("Insufficient Leave Balance to apply leave.", exception.getMessage());

	}

}
