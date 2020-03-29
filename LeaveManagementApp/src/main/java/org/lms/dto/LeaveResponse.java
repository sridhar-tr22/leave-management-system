package org.lms.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author User1
 *
 */

public class LeaveResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private long employeeId;
	private String leaveType;
	private LocalDate fromDate;
	private LocalDate toDate;
	private int numberofDays;
	private int remainingLeaves;
	private String status;
	private Object message;
	
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return the numberofDays
	 */
	public int getNumberofDays() {
		return numberofDays;
	}

	/**
	 * @param numberofDays the numberofDays to set
	 */
	public void setNumberofDays(int numberofDays) {
		this.numberofDays = numberofDays;
	}

}
