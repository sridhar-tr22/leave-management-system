package org.lms.repositories;

import java.util.List;
import java.util.Optional;

import org.lms.entities.LeaveDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDashboardRepository extends JpaRepository<LeaveDashboard, Long> {

	/**
	 * <p>
	 * fetches all associated records for an employee.
	 * </p>
	 * 
	 * @param employeeId
	 * @return
	 */
	Optional<List<LeaveDashboard>> findAllByEmployeeId(Long employeeId);

	/**
	 * 
	 * <p>
	 * fetches a dash board record for an employee associated with the given leave
	 * type.
	 * </p>
	 * 
	 * @param employeeId
	 * @param leaveType
	 * @return
	 */
	Optional<LeaveDashboard> findByEmployeeIdAndLeaveType(Long employeeId, String leaveType);

}
