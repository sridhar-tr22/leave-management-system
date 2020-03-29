package org.lms.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lms.dto.DashboardResponse;
import org.lms.dto.LeaveDashboardResponse;
import org.lms.dto.LeaveTypeResponse;
import org.lms.entities.LeaveDashboard;
import org.lms.entities.LeaveType;
import org.lms.exceptions.FaultException;
import org.lms.repositories.LeaveDashboardRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class LeaveDashboardServiceTest {

	@Autowired
	private LeaveDashboardService leaveDashboardService;

	@MockBean
	private LeaveDashboardRepository dashboardRepository;

	@MockBean
	private LeaveTypeService leaveTypeService;

	LeaveType leaveType_1;
	LeaveType leaveType_2;
	LeaveType leaveType_3;
	List<LeaveType> asList;
	List<LeaveTypeResponse> leaveTypeList;

	private LeaveDashboard leaveDashboard_1;
	private LeaveDashboard leaveDashboard_2;
	private LeaveDashboard leaveDashboard_3;

	@BeforeEach
	void setUp() throws Exception {

		leaveType_1 = new LeaveType("Annual Leave", "AL", 15);
		leaveType_2 = new LeaveType("Restricted Holiday", "RH", 6);
		leaveType_3 = new LeaveType("Personal Leave", "PL", 1);
		asList = Arrays.asList(leaveType_1, leaveType_2, leaveType_3);

		leaveTypeList = new ArrayList<>();
		asList.stream().forEach(e -> {
			LeaveTypeResponse leaveTypeResponse = new LeaveTypeResponse();
			leaveTypeResponse.setLeaveTypeId(e.getLeaveTypeId());
			leaveTypeResponse.setLeaveTypeName(e.getLeaveTypeName());
			leaveTypeResponse.setLeaveTypeCode(e.getLeaveTypeCode());
			leaveTypeResponse.setLeaveTypeCount(e.getLeaveTypeCount());
			leaveTypeList.add(leaveTypeResponse);
		});

		leaveDashboard_1 = new LeaveDashboard();
		leaveDashboard_2 = new LeaveDashboard();
		leaveDashboard_3 = new LeaveDashboard();

	}

	@Test
	void testPopulateDashboard() {
		Mockito.when(leaveTypeService.getAllLeaveType()).thenReturn(leaveTypeList);
		List<LeaveDashboard> asList2 = Arrays.asList(leaveDashboard_1, leaveDashboard_2, leaveDashboard_3);
		Mockito.when(dashboardRepository.saveAll(ArgumentMatchers.<List<LeaveDashboard>>any())).thenReturn(asList2);
		List<LeaveDashboard> populateDashboard = leaveDashboardService.populateDashboard(21021L);
		assertThat(populateDashboard).containsAll(asList2);
	}

	@Test
	void testGetDashboardForEmployee() {
		List<LeaveDashboard> asList = Arrays.asList(leaveDashboard_1, leaveDashboard_2, leaveDashboard_3);
		Optional<List<LeaveDashboard>> of = Optional.of(asList);
		Mockito.when(dashboardRepository.findAllByEmployeeId(Mockito.any(Long.class))).thenReturn(of);
		DashboardResponse dashboardForEmployee = leaveDashboardService.getDashboardForEmployee(2132L);
		assertEquals(dashboardForEmployee.getEmployeeId(), 2132L);

	}

	@Test
	void testGetDashboardForEmployee_IfNotPresent() {
		Mockito.when(dashboardRepository.findAllByEmployeeId(Mockito.any(Long.class))).thenReturn(Optional.empty());
		FaultException exception = assertThrows(FaultException.class,
				() -> leaveDashboardService.getDashboardForEmployee(21121L));
		assertEquals("Leave Dashboard is not available for the given Employee, please try later.",
				exception.getMessage());

	}

	@Test
	void testGetByEmployeeIdAndLeaveType() {
		leaveDashboard_1.setEmployeeId(2311L);
		leaveDashboard_1.setLeaveType("AL");
		Mockito.when(dashboardRepository.findByEmployeeIdAndLeaveType(ArgumentMatchers.anyLong(),
				ArgumentMatchers.anyString())).thenReturn(Optional.of(leaveDashboard_1));
		LeaveDashboardResponse byEmployeeIdAndLeaveType = leaveDashboardService.getByEmployeeIdAndLeaveType(2311L,
				"AL");

		assertEquals(leaveDashboard_1.getEmployeeId(), byEmployeeIdAndLeaveType.getEmployeeId());
		assertEquals(leaveDashboard_1.getLeaveType(), byEmployeeIdAndLeaveType.getLeaveType());
	}

}
