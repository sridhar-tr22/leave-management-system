package org.lms.services;

import java.util.List;

import org.lms.dto.DashboardResponse;
import org.lms.dto.LeaveDashboardResponse;
import org.lms.entities.LeaveDashboard;

public interface LeaveDashboardService {

	public List<LeaveDashboard> populateDashboard(Long employeeId);
	
	public DashboardResponse getDashboardForEmployee(Long employeeId);
	
	public LeaveDashboardResponse getByEmployeeIdAndLeaveType(Long employeeId, String type);
	
	void fetchandUpdateDashboard();
}
