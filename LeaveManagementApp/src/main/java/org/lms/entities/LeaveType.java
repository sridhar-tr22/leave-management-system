package org.lms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leave_type")
public class LeaveType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "leave_type_id")
	private Long leaveTypeId;

	@Column(name = "leave_type_name")
	private String leaveTypeName;

	@Column(name = "leave_type_code")
	private String leaveTypeCode;

	@Column(name = "leave_type_count")
	private int leaveTypeCount;
	
	/**
	 * no-argument constructor
	 */
	public LeaveType() {
	}
	
	/**
	 * @param leaveTypeName
	 * @param leaveTypeCode
	 * @param leaveTypeCount
	 */
	public LeaveType(String leaveTypeName, String leaveTypeCode, int leaveTypeCount) {
		super();
		this.leaveTypeName = leaveTypeName;
		this.leaveTypeCode = leaveTypeCode;
		this.leaveTypeCount = leaveTypeCount;
	}

	/**
	 * @return the leaveTypeId
	 */
	public Long getLeaveTypeId() {
		return leaveTypeId;
	}

	/**
	 * @param leaveTypeId the leaveTypeId to set
	 */
	public void setLeaveTypeId(Long leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	/**
	 * @return the leaveTypeName
	 */
	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	/**
	 * @param leaveTypeName the leaveTypeName to set
	 */
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	/**
	 * @return the leaveTypeCode
	 */
	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}

	/**
	 * @param leaveTypeCode the leaveTypeCode to set
	 */
	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}

	/**
	 * @return the leaveTypeCount
	 */
	public int getLeaveTypeCount() {
		return leaveTypeCount;
	}

	/**
	 * @param leaveTypeCount the leaveTypeCount to set
	 */
	public void setLeaveTypeCount(int leaveTypeCount) {
		this.leaveTypeCount = leaveTypeCount;
	}

}
