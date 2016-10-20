package com.gh.HelloSpring.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Table(name="employees")
public class Employee {

	@Id
	@GeneratedValue
	private int empid;

	@Length(max=10,message="The length of first_name field should be less than 10")
	@NotEmpty(message = "The first_name field should not be empty")
	@NotNull(message = "The first_name field should not be null")
	@Pattern(regexp = "[a-z-A-Z]*", message = "The first_name field value should be only alphabete")
	private String fname;
	
	@Length(max=10,message="The length of last_name field should be less than 10")
	@NotEmpty(message = "The last_name field should not be empty")
	@NotNull(message = "The last_name field should not be null")
	@Pattern(regexp = "[a-z-A-Z]*", message = "The last_name field value should be only alphabete")
	private String lname;
	
	@Past(message="Date must be in past")
	private Date dob;
	
	@Length(max=20,message="The length of address field should be less than 20")
	@NotEmpty(message = "The address field should not be empty")
	@NotNull(message = "The address field should not be null")
	private String address;
	
	
	@Length(max=10,message="The length of mobile field should be less than 10")
	@NotEmpty(message = "The mobile field should not be empty")
	@NotNull(message = "The mobile field should not be null")
	@Pattern(regexp = "^[0-9]*$", message = "The mobile field value should be only numeric")
	private String mobile;
	
	private String status;
	private Date createdDate;
	private Date lastModified;
	Calendar cal;
	
	
	public Employee() {
	}
	
	public Employee(String fname, String lname, Date dob, String address, String mobile) {
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.address = address;
		this.mobile = mobile;
		this.status ="active";
		this.createdDate=(Date) cal.getTime();
		this.lastModified=(Date) cal.getTime();
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
