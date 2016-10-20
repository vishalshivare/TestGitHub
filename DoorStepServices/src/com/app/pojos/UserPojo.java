package com.app.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="Doorstep_Services_Users")
public class UserPojo
{
	public int userId;
	public String firstName;
	public String lastName;
	public String email;
	public String password;
	public String mobileNumber;
	public String address;
	public String location;
	public String city;
	public String pinCode;
	public String gender;
	public String role;
	public String securityQuestion;
	private String securityAnswer;
	public Set<BookingPojo> bookings=new HashSet<BookingPojo>();
	
	public UserPojo() { System.out.println("in def constr");	}
	
	@Id
	@GeneratedValue
	@Column(name="UserId",length=10)
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="FirstName",length=25)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="LastName",length=25)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="Email",length=50,unique=true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="Password",length=20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="MobileNumber",length=10)
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Column(name="Address",length=255)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="Location",length=20)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name="City",length=20)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="PinCode",length=10)
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	@Column(name="Gender",length=10)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="Role",length=10)
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="bookingUser")
	public Set<BookingPojo> getBookings() {
		return bookings;
	}

	public void setBookings(Set<BookingPojo> bookings) {
		this.bookings = bookings;
	}

	@Column(name="Security_Question",length=255)
	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	@Column(name="Security_Answer",length=255)
	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	@Override
	public String toString() {
		return "UserPojo [userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", mobileNumber=" + mobileNumber + ", address="
				+ address + ", location=" + location + ", city=" + city
				+ ", pinCode=" + pinCode + ", gender=" + gender + ", role="
				+ role + "]";
	}
}
