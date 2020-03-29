package org.lms.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.lms.dto.DashboardResponse;
import org.lms.dto.LeaveDashboardResponse;
import org.lms.dto.LeaveTypeResponse;
import org.lms.entities.LeaveDashboard;
import org.lms.exceptions.FaultException;
import org.lms.repositories.LeaveDashboardRepository;
import org.lms.services.LeaveDashboardService;
import org.lms.services.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author User1
 *
 */
@Service
public class LeaveDashboardServiceImpl implements LeaveDashboardService {

	@Autowired
	private LeaveDashboardRepository dashboardRepository;

	@Autowired
	private LeaveTypeService leaveTypeService;

	@Override
	public List<LeaveDashboard> populateDashboard(Long employeeId) {
		List<LeaveDashboard> preparedRequests = prepareRequests(employeeId);
		List<LeaveDashboard> saveAll = dashboardRepository.saveAll(preparedRequests);
		return saveAll;
	}

	/**
	 * @param list
	 * @param employeeId
	 * @return
	 */
	private List<LeaveDashboard> prepareRequests(Long employeeId) {

		List<LeaveTypeResponse> allLeaveType = leaveTypeService.getAllLeaveType();
		List<LeaveDashboard> asDashRequestList = new ArrayList<>();

		allLeaveType.stream().forEach(e -> {
			// constructing LeaveDashboard for each record of Leave type.
			LeaveDashboard dashboard = new LeaveDashboard();
			dashboard.setEmployeeId(employeeId);
			dashboard.setLeaveType(e.getLeaveTypeCode());
			dashboard.setTotalLeaves(e.getLeaveTypeCount());
			dashboard.setConsumedLeaves(0);
			dashboard.setCreatedDate(LocalDate.now());
			dashboard.setRemainingLeaves(e.getLeaveTypeCount());
			asDashRequestList.add(dashboard);
		});
		return asDashRequestList;
	}

	@Override
	public DashboardResponse getDashboardForEmployee(Long employeeId) {

		DashboardResponse dashboardResponse = new DashboardResponse();
		List<LeaveDashboardResponse> asList = new ArrayList<>();

		Optional<List<LeaveDashboard>> findAllByEmployeeId = dashboardRepository.findAllByEmployeeId(employeeId);
		findAllByEmployeeId.ifPresent(items -> {
			items.stream().forEach(item -> {
				LeaveDashboardResponse leaveDashboard = new LeaveDashboardResponse();
				leaveDashboard.setEmployeeId(item.getEmployeeId());
				leaveDashboard.setLeaveType(item.getLeaveType());
				leaveDashboard.setTotalLeaves(item.getTotalLeaves());
				leaveDashboard.setConsumedLeaves(item.getConsumedLeaves());
				leaveDashboard.setRemainingLeaves(item.getRemainingLeaves());
				asList.add(leaveDashboard);
			});
		});
		findAllByEmployeeId.orElseThrow(() -> new FaultException(
				"Leave Dashboard is not available for the given Employee, please try later."));
		dashboardResponse.setEmployeeId(employeeId);
		dashboardResponse.setLeaveDashboardList(asList);
		return dashboardResponse;
	}

	@Override
	public LeaveDashboardResponse getByEmployeeIdAndLeaveType(Long employeeId, String type) {
		LeaveDashboardResponse leaveDashboardResponse = new LeaveDashboardResponse();
		Optional<LeaveDashboard> findByEmployeeIdAndLeaveType = dashboardRepository.findByEmployeeIdAndLeaveType(employeeId, type);
		findByEmployeeIdAndLeaveType.ifPresent(item -> {
			LeaveDashboard leaveDashboard = findByEmployeeIdAndLeaveType.get();
			leaveDashboardResponse.setEmployeeId(leaveDashboard.getEmployeeId());
			leaveDashboardResponse.setLeaveType(leaveDashboard.getLeaveType());
			leaveDashboardResponse.setTotalLeaves(leaveDashboard.getTotalLeaves());
			leaveDashboardResponse.setConsumedLeaves(leaveDashboard.getConsumedLeaves());
			leaveDashboardResponse.setRemainingLeaves(leaveDashboard.getRemainingLeaves());
		});
		findByEmployeeIdAndLeaveType.orElseThrow(() -> new FaultException(
				"Leave Type is not present for the given Employee, please try later."));
		return leaveDashboardResponse;
	}
	
	@Override
	public void fetchandUpdateDashboard() {
		// TODO Auto-generated method stub
		
	}
}
