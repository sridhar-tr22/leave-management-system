package org.lms.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.lms.dto.DashboardRequest;
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
	public List<LeaveDashboardResponse> populateDashboard(Long employeeId) {
		
		List<LeaveDashboardResponse> asListDash = new ArrayList<>();
		   
		List<LeaveDashboard> saveAll = dashboardRepository.saveAll(prepareRequests(employeeId));
		saveAll.stream()
			.forEach( item -> {
				LeaveDashboardResponse response = new LeaveDashboardResponse();
				response.setDashboardId(item.getDashboardId());
				response.setEmployeeId(item.getEmployeeId());
				response.setLeaveType(item.getLeaveType());
				response.setTotalLeaves(item.getTotalLeaves());
				response.setConsumedLeaves(item.getConsumedLeaves());
				response.setRemainingLeaves(item.getRemainingLeaves());
				asListDash.add(response);
			});
		
		return asListDash;
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
			dashboard.setModifiedDate(LocalDate.now());
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
		findAllByEmployeeId.orElseThrow(
				() -> new FaultException("Leave Dashboard is not available for the given Employee, please try later."));
		dashboardResponse.setEmployeeId(employeeId);
		dashboardResponse.setLeaveDashboardList(asList);
		return dashboardResponse;
	}

	@Override
	public Optional<LeaveDashboardResponse> getByEmployeeIdAndLeaveType(Long employeeId, String type) {
		LeaveDashboardResponse leaveDashboardResponse = new LeaveDashboardResponse();
		Optional<LeaveDashboard> findByEmployeeIdAndLeaveType = dashboardRepository
				.findByEmployeeIdAndLeaveType(employeeId, type);
		findByEmployeeIdAndLeaveType.ifPresent(item -> {
			LeaveDashboard leaveDashboard = findByEmployeeIdAndLeaveType.get();
			leaveDashboardResponse.setEmployeeId(leaveDashboard.getEmployeeId());
			leaveDashboardResponse.setLeaveType(leaveDashboard.getLeaveType());
			leaveDashboardResponse.setTotalLeaves(leaveDashboard.getTotalLeaves());
			leaveDashboardResponse.setConsumedLeaves(leaveDashboard.getConsumedLeaves());
			leaveDashboardResponse.setRemainingLeaves(leaveDashboard.getRemainingLeaves());
		});
		findByEmployeeIdAndLeaveType.orElseThrow(
				() -> new FaultException("Leave Type is not present for the given Employee, please try later."));
		return Optional.of(leaveDashboardResponse);
	}

	/**
	 * <p>
	 * this method updates the consumed leaves and remaining leaves of given type of
	 * an employee.
	 * </p>
	 * 
	 * @param DashboardRequest dashboardRequest
	 * 
	 */
	@Override
	public LeaveDashboardResponse fetchAndUpdateDashboard(DashboardRequest dashboardRequest) {
		LeaveDashboard dashboard = null;
		Objects.requireNonNull(dashboardRequest, "request parameter for fetchAndUpdateDashboard() is null");
		Optional<LeaveDashboard> findByEmployeeIdAndLeaveType = dashboardRepository
				.findByEmployeeIdAndLeaveType(dashboardRequest.getEmployeeId(), dashboardRequest.getLeaveType());
		dashboard = findByEmployeeIdAndLeaveType.get();
		if (dashboard.getLeaveType().equals(dashboardRequest.getLeaveType())) {
			dashboard.setConsumedLeaves(dashboardRequest.getConsumedLeaves());
			dashboard.setRemainingLeaves(dashboardRequest.getRemainingLeaves());
			dashboard.setModifiedDate(LocalDate.now());
		}
		LeaveDashboard saved = dashboardRepository.save(dashboard);
		LeaveDashboardResponse modifiedResponse = new LeaveDashboardResponse();
		modifiedResponse.setEmployeeId(saved.getEmployeeId());
		modifiedResponse.setLeaveType(saved.getLeaveType());
		modifiedResponse.setTotalLeaves(saved.getTotalLeaves());
		modifiedResponse.setConsumedLeaves(saved.getConsumedLeaves());
		modifiedResponse.setRemainingLeaves(saved.getRemainingLeaves());

		return modifiedResponse;
	}
}
