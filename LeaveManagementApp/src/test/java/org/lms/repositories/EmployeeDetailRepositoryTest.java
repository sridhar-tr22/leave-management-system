/**
 * 
 */
package org.lms.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.lms.entities.EmployeeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;

/**
 * @author User1
 *
 */

@Log4j2
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Disabled
class EmployeeDetailRepositoryTest {

	@Autowired
	private EmployeeDetailRepository employeeDetailRepository;

	private EmployeeDetail employeeDetail_1;
	private EmployeeDetail employeeDetail_2;
	private EmployeeDetail employeeDetail_3;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		employeeDetail_1 = new EmployeeDetail(11501L, "aaa@sample.org", "AAA", "ZZZ", "11220", "pwd", LocalDate.now());
		employeeDetail_2 = new EmployeeDetail(11502L, "bbb@sample.org", "BBB", "YYY", "11220", "pwd", LocalDate.now());
		employeeDetail_3 = new EmployeeDetail(11503L, "ccc@sample.org", "CCC", "XXX", "11220", "pwd", LocalDate.now());
	}

	@Test
	void testFindByEmployeeId() {
		List<EmployeeDetail> asList = Arrays.asList(employeeDetail_1, employeeDetail_2, employeeDetail_3);
		employeeDetailRepository.saveAll(asList);

		Optional<EmployeeDetail> findByEmployeeId = employeeDetailRepository.findByEmployeeId(11502L);

		log.info("Date : {}", findByEmployeeId.get().getCreatedDate());
		
		assertThat(findByEmployeeId.get().getFirstName()).isEqualTo(employeeDetail_2.getFirstName());

		assertThat(findByEmployeeId.get().getLastName()).isEqualTo(employeeDetail_2.getLastName());

	}

}
