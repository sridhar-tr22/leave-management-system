package org.lms.services;

import org.lms.dto.LeaveRequest;

/**
 *
 * @author User1
 * 
 *         <p>
 *         service interface for applying leave
 *         </p>
 *
 */
public interface LeaveApplicationService {

	/**
	 * @param leaveRequest
	 */
	void applyLeave(LeaveRequest leaveRequest);

}
