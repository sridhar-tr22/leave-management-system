package org.lms.repositories;

import java.util.Optional;

import org.lms.entities.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author User1
 *
 */
@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long>{

	public Optional<LeaveType> findByLeaveTypeCode(String leaveTypeCode);
	
}
