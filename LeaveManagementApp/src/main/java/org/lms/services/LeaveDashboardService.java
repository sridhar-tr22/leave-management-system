package org.lms.services;

import java.util.List;
import java.util.Optional;

import org.lms.dto.DashboardRequest;
import org.lms.dto.DashboardResponse;
import org.lms.dto.LeaveDashboardResponse;

/**
 * @author User1
 *
 */
public interface LeaveDashboardService {

	/**
	 * @param employeeId
	 * @return
	 */
	public List<LeaveDashboardResponse> populateDashboard(Long employeeId);

	/**
	 * @param employeeId
	 * @return
	 */
	public DashboardResponse getDashboardForEmployee(Long employeeId);

	/**
	 * @param employeeId
	 * @param type
	 * @return
	 */
	public Optional<LeaveDashboardResponse> getByEmployeeIdAndLeaveType(Long employeeId, String type);

	/**
	 * @param dashboardRequest
	 * @return
	 */
	public LeaveDashboardResponse fetchAndUpdateDashboard(DashboardRequest dashboardRequest);
}
