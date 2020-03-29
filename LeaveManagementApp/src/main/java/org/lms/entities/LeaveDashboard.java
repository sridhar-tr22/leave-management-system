package org.lms.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class LeaveDashboard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long dashboardId;
	private long employeeId;
	private String leaveType;
	private int totalLeaves;
	private int consumedLeaves;
	private int remainingLeaves;

	@CreatedDate
	private LocalDate createdDate;
	@CreatedDate
	private LocalDate modifiedDate;

	/**
	 * @return the dashboardId
	 */
	public long getDashboardId() {
		return dashboardId;
	}

	/**
	 * @param dashboardId the dashboardId to set
	 */
	public void setDashboardId(long dashboardId) {
		this.dashboardId = dashboardId;
	}

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
	 * @return the modifiedDate
	 */
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
