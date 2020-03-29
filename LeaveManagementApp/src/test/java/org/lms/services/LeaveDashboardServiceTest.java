package org.lms.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.ArgumentMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lms.dto.DashboardRequest;
import org.lms.dto.DashboardResponse;
import org.lms.dto.LeaveDashboardResponse;
import org.lms.dto.LeaveTypeResponse;
import org.lms.entities.LeaveDashboard;
import org.lms.entities.LeaveType;
import org.lms.repositories.LeaveDashboardRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class LeaveDashboardServiceTest {

	@Autowired
	private LeaveDashboardService leaveDashboardService;

	@MockBean
	private LeaveDashboardRepository dashboardRepository;

	@MockBean
	private LeaveTypeService leaveTypeService;

	private LeaveType leaveType_1;
	private LeaveType leaveType_2;
	private LeaveType leaveType_3;
	private List<LeaveType> asList;
	private List<LeaveTypeResponse> leaveTypeList;

	private LeaveDashboard leaveDashboard_1;
	private LeaveDashboard leaveDashboard_2;
	private LeaveDashboard leaveDashboard_3;

	private DashboardRequest dashboardRequest;

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
		leaveDashboard_1.setEmployeeId(10001L);
		leaveDashboard_1.setTotalLeaves(16);
		leaveDashboard_1.setLeaveType("AL");
		leaveDashboard_1.setConsumedLeaves(1);
		leaveDashboard_1.setRemainingLeaves(15);

		leaveDashboard_2 = new LeaveDashboard();
		leaveDashboard_3 = new LeaveDashboard();

		dashboardRequest = new DashboardRequest();
		dashboardRequest.setEmployeeId(10001L);
		dashboardRequest.setLeaveType("AL");
		dashboardRequest.setConsumedLeaves(16);
		dashboardRequest.setConsumedLeaves(6);
		dashboardRequest.setRemainingLeaves(10);

	}

	@Test
	void testPopulateDashboard() {
		Mockito.when(leaveTypeService.getAllLeaveType()).thenReturn(leaveTypeList);
		List<LeaveDashboard> asList2 = Arrays.asList(leaveDashboard_1, leaveDashboard_2, leaveDashboard_3);
		Mockito.when(dashboardRepository.saveAll(ArgumentMatchers.<List<LeaveDashboard>>any())).thenReturn(asList2);
		List<LeaveDashboardResponse> populateDashboard = leaveDashboardService.populateDashboard(21021L);
		assertThat(populateDashboard.size()).isGreaterThanOrEqualTo(1);
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
		DashboardResponse dashboardForEmployee = leaveDashboardService.getDashboardForEmployee(21121L);
		assertEquals(dashboardForEmployee.getEmployeeId(), 21121L);
	}

	@Test
	void testGetByEmployeeIdAndLeaveType() {
		leaveDashboard_1.setEmployeeId(2311L);
		leaveDashboard_1.setLeaveType("AL");
		Mockito.when(dashboardRepository.findByEmployeeIdAndLeaveType(ArgumentMatchers.anyLong(),
				ArgumentMatchers.anyString())).thenReturn(Optional.of(leaveDashboard_1));
		Optional<LeaveDashboardResponse> byEmployeeIdAndLeaveType = leaveDashboardService.getByEmployeeIdAndLeaveType(2311L,
				"AL");

		assertEquals(leaveDashboard_1.getEmployeeId(), byEmployeeIdAndLeaveType.get().getEmployeeId());
		assertEquals(leaveDashboard_1.getLeaveType(), byEmployeeIdAndLeaveType.get().getLeaveType());
	}

	@Test
	void testFetchAndUpdateDashboard() {

		Mockito.when(dashboardRepository.findByEmployeeIdAndLeaveType(ArgumentMatchers.anyLong(),
				ArgumentMatchers.anyString())).thenReturn(Optional.of(leaveDashboard_1));

		Mockito.when(dashboardRepository.save(Mockito.any(LeaveDashboard.class))).thenReturn(leaveDashboard_1);

		log.info("Before - ID: {}, Type: {}, Total: {}, Consumed: {}, Remaining: {}", dashboardRequest.getEmployeeId(),
				leaveDashboard_1.getLeaveType(), leaveDashboard_1.getTotalLeaves(),
				leaveDashboard_1.getConsumedLeaves(), leaveDashboard_1.getRemainingLeaves());

		LeaveDashboardResponse fetchAndUpdateDashboard = leaveDashboardService
				.fetchAndUpdateDashboard(dashboardRequest);

		log.info("Updated - ID: {}, Type: {}, Total: {}, Consumed: {}, Remaining: {}",
				fetchAndUpdateDashboard.getEmployeeId(), fetchAndUpdateDashboard.getLeaveType(),
				fetchAndUpdateDashboard.getTotalLeaves(), fetchAndUpdateDashboard.getConsumedLeaves(),
				fetchAndUpdateDashboard.getRemainingLeaves());

		assertEquals(dashboardRequest.getConsumedLeaves(), fetchAndUpdateDashboard.getConsumedLeaves());
		assertEquals(dashboardRequest.getRemainingLeaves(), fetchAndUpdateDashboard.getRemainingLeaves());
		assertEquals(dashboardRequest.getLeaveType(), fetchAndUpdateDashboard.getLeaveType());
	}

}
