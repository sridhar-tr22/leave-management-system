package org.lms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author User1
 *
 */
public class DashboardResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long employeeId;
	private List<LeaveDashboardResponse> leaveDashboardList;
	private Object message;

	/**
	 * @return the emmployeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param emmployeeId the emmployeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the leaveDashboardList
	 */
	public List<LeaveDashboardResponse> getLeaveDashboardList() {
		return leaveDashboardList;
	}

	/**
	 * @param leaveDashboardList the leaveDashboardList to set
	 */
	public void setLeaveDashboardList(List<LeaveDashboardResponse> leaveDashboardList) {
		this.leaveDashboardList = leaveDashboardList;
	}

	/**
	 * @return the message
	 */
	public Object getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Object message) {
		this.message = message;
	}

}
