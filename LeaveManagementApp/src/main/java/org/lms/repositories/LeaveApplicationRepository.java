package org.lms.repositories;

import java.util.Optional;

import org.lms.entities.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long>{

	public Optional<LeaveApplication> findByEmployeeId(Long employeeId);
}
