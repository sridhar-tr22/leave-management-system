/**
 * 
 */
package org.lms.dto;

import java.io.Serializable;

/**
 * @author User1
 *
 */
public class LeaveTypeResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long leaveTypeId;
	private String leaveTypeName;
	private String leaveTypeCode;
	private int leaveTypeCount;

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
