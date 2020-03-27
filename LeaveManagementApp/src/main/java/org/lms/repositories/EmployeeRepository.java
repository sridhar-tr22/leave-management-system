package org.lms.repositories;

import org.lms.entities.EmployeeDetail;
import org.lms.repositories.custom.CustomizedEmployeeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author User1
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetail, Long>, CustomizedEmployeeRepository {

}
