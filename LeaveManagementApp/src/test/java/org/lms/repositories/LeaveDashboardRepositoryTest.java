package org.lms.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.lms.entities.LeaveDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Disabled
class LeaveDashboardRepositoryTest {

	@Autowired
	private LeaveDashboardRepository dashboardRepository;
	
	private LeaveDashboard leaveDashboard;
	private LeaveDashboard leaveDashboard_1;
	private LeaveDashboard leaveDashboard_2;
	
	@BeforeEach
	void setUp() throws Exception {
		leaveDashboard = new LeaveDashboard();
		leaveDashboard.setEmployeeId(2201L);
		leaveDashboard.setLeaveType("ANNUAL LEAVE");
		leaveDashboard.setTotalLeaves(15);
		leaveDashboard.setRemainingLeaves(15);
		leaveDashboard.setConsumedLeaves(0);
		
		leaveDashboard_1 = new LeaveDashboard();
		leaveDashboard_1.setEmployeeId(2201L);
		leaveDashboard_1.setLeaveType("PERSONAL LEAVE");
		leaveDashboard_1.setTotalLeaves(1);
		leaveDashboard_1.setRemainingLeaves(1);
		leaveDashboard_1.setConsumedLeaves(0);
		
		leaveDashboard_2 = new LeaveDashboard();
		leaveDashboard_2.setEmployeeId(2201L);
		leaveDashboard_2.setLeaveType("RESTRICTED HOLIDAY");
		leaveDashboard_2.setTotalLeaves(6);
		leaveDashboard_2.setRemainingLeaves(6);
		leaveDashboard_2.setConsumedLeaves(0);
		
	}

	@Test
	void testFindAllByEmployeeId() {
		
		List<LeaveDashboard> asList = Arrays.asList(leaveDashboard, leaveDashboard_1, leaveDashboard_2);
		dashboardRepository.saveAll(asList);
		dashboardRepository.flush();
		
		
		Optional<List<LeaveDashboard>> findAll = dashboardRepository.findAllByEmployeeId(2201L);
		findAll.ifPresent(items -> {
			items.stream()
				.forEach(item -> {
					log.info("Dash - ID: {}, EId: {}, Type: {}, Total: {}, Consumed: {}, Remaining: {}",
							item.getDashboardId(),
							item.getEmployeeId(), item.getLeaveType(),
							item.getTotalLeaves(), item.getConsumedLeaves(),
							item.getRemainingLeaves());
				});
		});
		assertThat(findAll.get().size()).isGreaterThanOrEqualTo(1);
		
		
	}
	
	@Test
	void testFindByEmployeeIdAndLeaveType() {
		List<LeaveDashboard> asList = Arrays.asList(leaveDashboard, leaveDashboard_1, leaveDashboard_2);
		dashboardRepository.saveAll(asList);
		dashboardRepository.flush();
		
		Optional<LeaveDashboard> findByEmployeeIdAndLeaveType = dashboardRepository.findByEmployeeIdAndLeaveType(2201L, "RESTRICTED HOLIDAY");
		
		findByEmployeeIdAndLeaveType.ifPresent(item -> {
			log.info("Dash - ID: {}, EId: {}, Type: {}, Total: {}, Consumed: {}, Remaining: {}",
					item.getDashboardId(),
					item.getEmployeeId(), item.getLeaveType(),
					item.getTotalLeaves(), item.getConsumedLeaves(),
					item.getRemainingLeaves());
		});
		
		assertThat(findByEmployeeIdAndLeaveType.isPresent()).isTrue();
		
	}
		
}
