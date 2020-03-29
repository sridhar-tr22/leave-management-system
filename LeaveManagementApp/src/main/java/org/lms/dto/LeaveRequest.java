package org.lms.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author User1
 *
 */
public class LeaveRequest implements Serializable {

	/**
	 * using default serial version UID = 1L
	 */
	private static final long serialVersionUID = 1L;
	private Long employeeId;
	private String employeeName;
	private String leaveType;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String action;

	/**
	 * <p>
	 * no-argument constructor
	 * </p>
	 */
	public LeaveRequest() {
	}

	/**
	 * @param employeeId
	 * @param employeeName
	 * @param leaveType
	 * @param fromDate
	 * @param toDate
	 * @param action
	 */
	public LeaveRequest(Long employeeId, String employeeName, String leaveType, LocalDate fromDate, LocalDate toDate,
			String action) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.leaveType = leaveType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.action = action;
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName the employeeName to set
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	 * @return the fromDate
	 */
	public LocalDate getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public LocalDate getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * <p>
	 * calculates the total number of days. Increasing toDate by one day to include
	 * that day in total number of days.
	 * </p>
	 * 
	 * @return
	 */
	public int numberOfLeaveDays() {
		long between = ChronoUnit.DAYS.between(fromDate, toDate.plusDays(1));
		return Math.toIntExact(between);
	}
}
