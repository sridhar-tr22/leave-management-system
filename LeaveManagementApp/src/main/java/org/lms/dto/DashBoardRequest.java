package org.lms.dto;

import java.io.Serializable;

/**
 * @author User1
 *
 */
public class DashBoardRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long employeeId;
	private String leaveType;
	private int totalLeaves;
	private int consumedLeaves;
	private int remainingLeaves;

	/**
	 * @return the employeeId
	 */
	public long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	/**
	 * @return the totalLeaves
	 */
	public int getTotalLeaves() {
		return totalLeaves;
	}

	/**
	 * @param totalLeaves the totalLeaves to set
	 */
	public void setTotalLeaves(int totalLeaves) {
		this.totalLeaves = totalLeaves;
	}

	/**
	 * @return the consumedLeaves
	 */
	public int getConsumedLeaves() {
		return consumedLeaves;
	}

	/**
	 * @param consumedLeaves the consumedLeaves to set
	 */
	public void setConsumedLeaves(int consumedLeaves) {
		this.consumedLeaves = consumedLeaves;
	}

	/**
	 * @return the remainingLeaves
	 */
	public int getRemainingLeaves() {
		return remainingLeaves;
	}

	/**
	 * @param remainingLeaves the remainingLeaves to set
	 */
	public void setRemainingLeaves(int remainingLeaves) {
		this.remainingLeaves = remainingLeaves;
	}

}
