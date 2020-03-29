package org.lms.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lms.dto.LeaveTypeResponse;
import org.lms.entities.LeaveType;
import org.lms.repositories.LeaveTypeRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class LeaveTypeServiceTest {

	@Autowired
	private LeaveTypeService leaveTypeService;

	@MockBean
	private LeaveTypeRepository leaveTypeRepository;

	LeaveType leaveType_1;
	LeaveType leaveType_2;
	LeaveType leaveType_3;
	List<LeaveType> asList;

	@BeforeEach
	void setUp() throws Exception {
		leaveType_1 = new LeaveType("Annual Leave", "AL", 15);
		leaveType_2 = new LeaveType("Restricted Holiday", "RH", 6);
		leaveType_3 = new LeaveType("Personal Leave", "PL", 1);
		asList = Arrays.asList(leaveType_1, leaveType_2, leaveType_3);
	}

	@Test
	void testGetAllLeaveType() {
		
		Mockito.when(leaveTypeRepository.findAll()).thenReturn(asList);
		
		List<LeaveTypeResponse> allLeaveType = leaveTypeService.getAllLeaveType();
		allLeaveType.stream()
			.forEach(e -> {
				log.info("LeaveType - Name: {}, Code: {}, Count:  {}", e.getLeaveTypeName(), e.getLeaveTypeCode(), e.getLeaveTypeCount());
		});
		assertEquals(asList.size(), allLeaveType.size());
	}

}
