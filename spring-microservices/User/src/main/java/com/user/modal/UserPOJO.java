package com.user.modal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Users_1314")
public class UserPOJO {
	
	@Id
	@GeneratedValue
	private int userId;
	
	@Length(max=10,message="The length of first_name field should be less than 10")
	@NotEmpty(message = "The first_name field should not be empty")
	@NotNull(message = "The first_name field should not be null")
	@Pattern(regexp = "[a-z-A-Z]*", message = "The first_name field value should be only alphabete")
	private String FName;
	
	
	@Length(max=10,message="The length of last_name field should be less than 10")
	@NotEmpty(message = "The last_name field should not be empty")
	@NotNull(message = "The last_name field should not be null")
	@Pattern(regexp = "[a-z-A-Z]*", message = "The last_name field value should be only alphabete")
	private String LName;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Email(message="Enter proper Email Address")
	@Column(unique=true)
	@Length(max=30,message="The length of email field should be less than 15")
	@NotEmpty(message = "The email field should not be empty")
	@NotNull(message = "The email field should not be null")
	private String email;
	
	@Length(max=10,message="The length of password field should be less than 10")
	@NotEmpty(message = "The password field should not be empty")
	@NotNull(message = "The password field should not be null")
	private String password;
	
	@Length(max=10,message="The length of mobile field should be less than 10")
	@NotEmpty(message = "The mobile field should not be empty")
	@NotNull(message = "The mobile field should not be null")
	@Pattern(regexp = "^[0-9]*$", message = "The mobile field value should be only numeric")
	private String mobile;
	
	@Length(max=20,message="The length of address field should be less than 20")
	@NotEmpty(message = "The address field should not be empty")
	@NotNull(message = "The address field should not be null")
	private String address;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	private Date lastModified;
	

	private String status;

	private String role;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFName() {
		return FName;
	}
	public void setFName(String fName) {
		FName = fName;
	}
	public String getLName() {
		return LName;
	}
	public void setLName(String lName) {
		LName = lName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}