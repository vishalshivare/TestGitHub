package com.app.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Doorstep_Services_Bookings")
public class BookingPojo
{
	public int BookingId;
	public Vendor vendor;
	public UserPojo bookingUser;
	public String address;
	public Date date;
	public String timeSlot;
	public String serviceIds;
	public String status;
	public double bill;
	public LocationPojo locations;
	
	public BookingPojo() {
		System.out.println("in booking def constr");
	}

	@Id
	@GeneratedValue
	@Column(name="Booking_Id", length=10)
	public int getBookingId() {
		return BookingId;
	}

	public void setBookingId(int bookingId) {
		BookingId = bookingId;
	}

	@Column(name="Booking_Address", length=255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name="Booking_Date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name="Time_Slot", length=10)
	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	@Column(name="Booking_Status", length=15)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="total_Bill", columnDefinition="decimal(6,2)")
	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}

	@ManyToOne
	@JoinColumn(name="Vendor_Id_PK")
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@ManyToOne
	@JoinColumn(name="UserId")
	public UserPojo getBookingUser() {
		return bookingUser;
	}

	public void setBookingUser(UserPojo bookingUser) {
		this.bookingUser = bookingUser;
	}
	
	@Column(name="List_of_ServiceIds",length=20)
	public String getServiceIds() {
		return serviceIds;
	}

	public void setServiceIds(String serviceIds) {
		this.serviceIds = serviceIds;
	}

	@ManyToOne
	@JoinColumn(name="Location_Id_PK")
	public LocationPojo getLocations() {
		return locations;
	}

	public void setLocations(LocationPojo locations) {
		this.locations = locations;
	}
	
	@Override
	public String toString() {
		return "BookingPojo [BookingId=" + BookingId + ", vendor=" + vendor
				+ ", bookingUser=" + bookingUser + ", address=" + address
				+ ", date=" + date + ", timeSlot=" + timeSlot + ", serviceId="
				+ serviceIds + ", status=" + status + ", bill=" + bill + "]";
	}

		
	
}
