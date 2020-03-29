/**
 * 
 */
package org.lms.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.lms.dto.DashboardRequest;
import org.lms.dto.LeaveDashboardResponse;
import org.lms.dto.LeaveRequest;
import org.lms.dto.LeaveResponse;
import org.lms.dto.ResponseMessage;
import org.lms.entities.LeaveApplication;
import org.lms.exceptions.FaultException;
import org.lms.repositories.LeaveApplicationRepository;
import org.lms.services.LeaveApplicationService;
import org.lms.services.LeaveDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

/**
 * @author User1
 *
 */
@Log4j2
@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

	@Autowired
	private LeaveApplicationRepository applicationRepository;

	@Autowired
	private LeaveDashboardService dashboardService;

	private LeaveResponse leaveResponse;
	private ResponseMessage message;

	@Override
	@Transactional
	public LeaveResponse applyLeave(LeaveRequest leaveRequest) {
		log.info("Leave Request from Id: {}, Type: {}, From: {}, To:{}", leaveRequest.getEmployeeId(),
				leaveRequest.getLeaveType(), leaveRequest.getFromDate(), leaveRequest.getToDate());
		LeaveDashboardResponse leaveDashboard = getDashboardForEmployee(leaveRequest);
		LeaveApplication leaveApplicationRequest = prepareLeaveAppRequest(leaveRequest);
		DashboardRequest leaveDashboardRequest = prepareDashboardRequest(leaveRequest, leaveDashboard);
		LeaveApplication saveAndFlush = applicationRepository.saveAndFlush(leaveApplicationRequest);
		LeaveDashboardResponse fetchAndUpdateDashboard = dashboardService
				.fetchAndUpdateDashboard(leaveDashboardRequest);
		leaveResponse = new LeaveResponse();
		leaveResponse.setEmployeeId(saveAndFlush.getEmployeeId());
		leaveResponse.setLeaveType(saveAndFlush.getLeaveType());
		leaveResponse.setRemainingLeaves(fetchAndUpdateDashboard.getRemainingLeaves());
		leaveResponse.setStatus(saveAndFlush.getStatus());
		leaveResponse.setNumberofDays(saveAndFlush.getDaysCount());
		leaveResponse.setFromDate(saveAndFlush.getFromDate());
		leaveResponse.setToDate(saveAndFlush.getToDate());
		message = new ResponseMessage();
		message.setStatusCode("200");
		message.setStatusMessage(String.format("\"%s\" leave is applied from %s to %s successfully.",
				leaveResponse.getLeaveType(), leaveResponse.getFromDate(), leaveResponse.getToDate()));
		leaveResponse.setMessage(message);
		return leaveResponse;
	}

	private LeaveDashboardResponse getDashboardForEmployee(LeaveRequest leaveRequest) {
		log.info("Obtaining dashboard for EmployeeId: {}", leaveRequest.getEmployeeId());
		LeaveDashboardResponse leaveDashboard = null;
		Optional<LeaveDashboardResponse> byEmployeeIdAndLeaveType = dashboardService
				.getByEmployeeIdAndLeaveType(leaveRequest.getEmployeeId(), leaveRequest.getLeaveType());
		if (byEmployeeIdAndLeaveType.isPresent()) {
			leaveDashboard = byEmployeeIdAndLeaveType.get();
			log.info("Fetched Dashboard for Id: {}, Type: {}, Consumed: {}, Remaining: {}",
					leaveDashboard.getEmployeeId(), leaveDashboard.getLeaveType(), leaveDashboard.getConsumedLeaves(),
					leaveDashboard.getRemainingLeaves());
		} else {
			List<LeaveDashboardResponse> listOfleaves = dashboardService
					.populateDashboard(leaveRequest.getEmployeeId());
			log.info("Creating Dashboard for Employee: {}...", leaveRequest.getEmployeeId());
			for (LeaveDashboardResponse leave : listOfleaves) {
				if (leave.getLeaveType().equals(leaveRequest.getLeaveType())) {
					log.info("Selected Dashboard for Id: {}, Type: {},Total: {}, Consumed: {}, Remaining: {}",
							leave.getEmployeeId(), leave.getLeaveType(), leave.getTotalLeaves(),
							leave.getConsumedLeaves(), leave.getRemainingLeaves());
					leaveDashboard = leave;
				}
			}
		}
		return leaveDashboard;
	}

	private LeaveApplication prepareLeaveAppRequest(LeaveRequest request) {
		log.info("started preparing Leave Application request for EmployeeId: {}", request.getEmployeeId());
		LeaveApplication application = new LeaveApplication();
		// 1. Employee Id
		application.setEmployeeId(request.getEmployeeId());
		// 2. Leave Type
		application.setLeaveType(request.getLeaveType());
		// 3. From Date
		application.setFromDate(request.getFromDate());
		// 4. To Date
		application.setToDate(request.getToDate());
		// 5. Action
		application.setStatus("APPLIED");
		// 6. Number of Days
		application.setDaysCount(request.numberOfLeaveDays());
		// 7. Created Date
		application.setCreatedDate(LocalDate.now());
		log.info("Leave application request for EmployeeId: {} is completed.", request.getEmployeeId());
		return application;
	}

	private DashboardRequest prepareDashboardRequest(LeaveRequest request, LeaveDashboardResponse response) {
		log.info("started preparing Dashboard request for EmployeeId: {}", request.getEmployeeId());
		DashboardRequest dashboardRequest = new DashboardRequest();
		// 1. Dashboard Id
		dashboardRequest.setDashboardId(response.getDashboardId());
		// 2. Employee Id
		dashboardRequest.setEmployeeId(response.getEmployeeId());
		// 3. Leave Type
		dashboardRequest.setLeaveType(response.getLeaveType());
		log.info(
				"present dashboard data for Employee: {}, has consumed {} leaves and remaining {} leaves out of {} for {} type.",
				response.getEmployeeId(), response.getConsumedLeaves(), response.getRemainingLeaves(),
				response.getTotalLeaves(), response.getLeaveType());
		// 4. Total Leaves
		dashboardRequest.setTotalLeaves(response.getTotalLeaves());

		// 5. Consumed Leaves
		int consumedLeaves = processConsumedLeaves(request.numberOfLeaveDays(), response.getConsumedLeaves());
		dashboardRequest.setConsumedLeaves(consumedLeaves);
		// 6. Remaining Leaves
		int remainingLeaves = processRemainingLeaves(request.numberOfLeaveDays(), response.getTotalLeaves(),
				response.getRemainingLeaves());
		dashboardRequest.setRemainingLeaves(remainingLeaves);
		log.info(
				"updated dashboard data for Employee: {} after applying leave for {} days from {} to {}, has remaining {} leaves out of {} for {} type.",
				dashboardRequest.getEmployeeId(), request.numberOfLeaveDays(), request.getFromDate(),
				request.getToDate(), dashboardRequest.getRemainingLeaves(), dashboardRequest.getTotalLeaves(),
				dashboardRequest.getLeaveType());
		return dashboardRequest;
	}

	private int processConsumedLeaves(int appliedDaysCount, int consumedLeavesCount) {
		log.info("caluclating consumed leaves...");
		return (appliedDaysCount + consumedLeavesCount);
	}

	private int processRemainingLeaves(int daysCount, int total, int remaining) {
		log.info("caluclating remaining leaves...");
		if (remaining < daysCount || daysCount > total) {
			throw new FaultException("Insufficient Leave Balance to apply leave.");
		} else {
			remaining = remaining - daysCount;
		}
		return remaining;
	}
}
