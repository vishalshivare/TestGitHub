package com.gh.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="employee1314")
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
	@Temporal(TemporalType.DATE)
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
	
	@Column(name="createdDate")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name="lastModified")
	@Temporal(TemporalType.DATE)
	private Date lastModified;
	
	@Transient
	private SimpleDateFormat sdf;
	
	
	public Employee() {
		sdf=new SimpleDateFormat("yyyy-MM-dd");
	}
	
	/*public Employee(String fname, String lname, String dob, String address, String mobile) throws Exception {
		this.fname = fname;
		this.lname = lname;
		this.dob = sdf.parse(dob);
		this.address = address;
		this.mobile = mobile;
		this.status ="active";
		this.createdDate= new Date();
		this.lastModified=new Date();
	}*/

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}
