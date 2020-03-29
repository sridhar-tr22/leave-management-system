package org.lms.services;

import org.lms.dto.LeaveRequest;
import org.lms.dto.LeaveResponse;

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
	public LeaveResponse applyLeave(LeaveRequest leaveRequest);

}
