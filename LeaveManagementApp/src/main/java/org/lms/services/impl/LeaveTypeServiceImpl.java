/**
 * 
 */
package org.lms.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.lms.dto.LeaveTypeResponse;
import org.lms.entities.LeaveType;
import org.lms.repositories.LeaveTypeRepository;
import org.lms.services.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author User1
 *
 */

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

	@Autowired
	private LeaveTypeRepository leaveTypeRepository;

	@Override
	public List<LeaveTypeResponse> getAllLeaveType() {
		List<LeaveType> findAllLeaveType = leaveTypeRepository.findAll();
		List<LeaveTypeResponse> mappedEntityToDto = mapEntityToDto(findAllLeaveType);
		return mappedEntityToDto;
	}

	/**
	 * <p>
	 * function - maps entity to data transfer objects.
	 * </p>
	 * 
	 * @param List<LeaveType>
	 * @return List<LeaveTypeResponse>
	 */
	private List<LeaveTypeResponse> mapEntityToDto(List<LeaveType> source) {
		List<LeaveTypeResponse> leaveTypeList = new ArrayList<>();
		if (Objects.isNull(source)) {
			//returning empty list.
			return leaveTypeList;
		}
		source.stream().forEach(e -> {
			LeaveTypeResponse leaveTypeResponse = new LeaveTypeResponse();
			leaveTypeResponse.setLeaveTypeId(e.getLeaveTypeId());
			leaveTypeResponse.setLeaveTypeName(e.getLeaveTypeName());
			leaveTypeResponse.setLeaveTypeCode(e.getLeaveTypeCode());
			leaveTypeResponse.setLeaveTypeCount(e.getLeaveTypeCount());
			leaveTypeList.add(leaveTypeResponse);
		});
		return leaveTypeList;
	}

}
