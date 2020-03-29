package org.lms.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class LeaveApplication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long leaveApplicationId;
	private Long employeeId;
	private LocalDate fromDate;
	private String leaveType;
	private String status;
	private LocalDate toDate;
	private int daysCount;

	@CreatedDate
	private LocalDate createdDate;

	public LeaveApplication() {
	}

	/**
	 * @param leaveApplicationId
	 * @param employeeId
	 * @param fromDate
	 * @param leaveType
	 * @param status
	 * @param toDate
	 * @param createdDate
	 */
	public LeaveApplication(Long leaveApplicationId, Long employeeId, LocalDate fromDate, String leaveType,
			String status, LocalDate toDate, LocalDate createdDate) {
		super();
		this.leaveApplicationId = leaveApplicationId;
		this.employeeId = employeeId;
		this.fromDate = fromDate;
		this.leaveType = leaveType;
		this.status = status;
		this.toDate = toDate;
		this.createdDate = createdDate;
	}

	/**
	 * @return the leaveApplicationId
	 */
	public Long getLeaveApplicationId() {
		return leaveApplicationId;
	}

	/**
	 * @param leaveApplicationId the leaveApplicationId to set
	 */
	public void setLeaveApplicationId(Long leaveApplicationId) {
		this.leaveApplicationId = leaveApplicationId;
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
	 * @return the createdDate
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the daysCount
	 */
	public int getDaysCount() {
		return daysCount;
	}

	/**
	 * @param daysCount the daysCount to set
	 */
	public void setDaysCount(int daysCount) {
		this.daysCount = daysCount;
	}

}
