package org.lms.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.lms.entities.LeaveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class LeaveTypeRepositoryTest {

	@Autowired
	private LeaveTypeRepository typeRepository;

	private LeaveType leaveTypeAL;
	private LeaveType leaveTypeRH;
	private LeaveType leaveTypePL;

	String leaveTypeCodeAL = "AL";
	String leaveTypeCodeRH = "RH";
	String leaveTypeCodePL = "PL";

	@BeforeEach
	void setUp() throws Exception {
		leaveTypeAL = new LeaveType("Annual Leave", "AL", 15);
		leaveTypeRH = new LeaveType("Restricted Holiday", "RH", 6);
		leaveTypePL = new LeaveType("Personal Leave", "PL", 1);
	}

	@Test
	@Disabled
	void testFindByLeaveTypeCode() {
		List<LeaveType> asList = Arrays.asList(leaveTypeAL, leaveTypeRH, leaveTypePL);
		List<LeaveType> saveAll = typeRepository.saveAll(asList);
		assertThat(saveAll.size()).isEqualTo(asList.size());
	}

}
