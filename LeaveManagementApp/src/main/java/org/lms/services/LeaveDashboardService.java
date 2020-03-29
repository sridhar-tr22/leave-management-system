package org.lms.services;

import java.util.List;

import org.lms.dto.DashboardRequest;
import org.lms.dto.DashboardResponse;
import org.lms.dto.LeaveDashboardResponse;
import org.lms.entities.LeaveDashboard;

/**
 * @author User1
 *
 */
public interface LeaveDashboardService {

	/**
	 * @param employeeId
	 * @return
	 */
	public List<LeaveDashboard> populateDashboard(Long employeeId);

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
	public LeaveDashboardResponse getByEmployeeIdAndLeaveType(Long employeeId, String type);

	/**
	 * @param dashboardRequest
	 * @return
	 */
	public LeaveDashboardResponse fetchAndUpdateDashboard(DashboardRequest dashboardRequest);
}
