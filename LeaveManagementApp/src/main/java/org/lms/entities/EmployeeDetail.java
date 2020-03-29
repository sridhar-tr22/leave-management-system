package org.lms.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "employee_details")
public class EmployeeDetail implements Serializable {

	/**
	 * default serial version UID = 1L
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long employeeId;
	
	private String email;
	
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	
	private String managerId;
	
	private String password;

	@CreatedBy
	private String createdUser;

	@CreatedDate
	private LocalDate createdDate;

	/**
	 * no-argument constructor
	 */
	public EmployeeDetail() {
	}

	
	
	/**
	 * @param employeeId
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param managerId
	 * @param password
	 * @param createdUser
	 * @param createdDate
	 */
	public EmployeeDetail(Long employeeId, String email, @NotNull String firstName, @NotNull String lastName,
			String managerId, String password, LocalDate createdDate) {
		super();
		this.employeeId = employeeId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.managerId = managerId;
		this.password = password;
		this.createdUser = "ADMIN";
		this.createdDate = createdDate;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the managerId
	 */
	public String getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser the createdUser to set
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return the createdDate
	 */
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

}
