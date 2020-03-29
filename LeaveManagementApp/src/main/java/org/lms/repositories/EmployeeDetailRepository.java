package org.lms.repositories;

import java.util.Optional;

import org.lms.entities.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {

	public Optional<EmployeeDetail> findByEmployeeId(long employeeId);
}
