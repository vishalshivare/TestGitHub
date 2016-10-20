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
@Table(name = "Vendor_TimeSlot")
public class VendorTimePojo {
	public int vTimeId;
	public Vendor vendorId;
	public ServiceCategory serviceCategory;
	public String slotOne;
	public String slotTwo;
	public String slotThree;
	public String slotFour;
	public Date date;
	public LocationPojo location;

	public VendorTimePojo() {
		System.out.println("in vendor time def constr");
	}

	@Id
	@GeneratedValue
	@Column(name = "Vendor_Time_Id", length = 10)
	public int getvTimeId() {
		return vTimeId;
	}

	public void setvTimeId(int vTimeId) {
		this.vTimeId = vTimeId;
	}

	@ManyToOne
	@JoinColumn(name = "Vendor_Id_PK")
	public Vendor getVendorId() {
		return vendorId;
	}

	public void setVendorId(Vendor vendorId) {
		this.vendorId = vendorId;
	}

	@ManyToOne
	@JoinColumn(name = "SCategories_Id_PK")
	public ServiceCategory getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	@Column(name = "Slot_One", length = 20)
	public String getSlotOne() {
		return slotOne;
	}

	public void setSlotOne(String slotOne) {
		this.slotOne = slotOne;
	}

	@Column(name = "Slot_Two", length = 20)
	public String getSlotTwo() {
		return slotTwo;
	}

	public void setSlotTwo(String slotTwo) {
		this.slotTwo = slotTwo;
	}

	@Column(name = "Slot_Three", length = 20)
	public String getSlotThree() {
		return slotThree;
	}

	public void setSlotThree(String slotThree) {
		this.slotThree = slotThree;
	}

	@Column(name = "Slot_Four", length = 20)
	public String getSlotFour() {
		return slotFour;
	}

	public void setSlotFour(String slotFour) {
		this.slotFour = slotFour;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "Booking_Date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name = "Location_Id_PK")
	public LocationPojo getLocation() {
		return location;
	}

	public void setLocation(LocationPojo location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "VendorTimePojo [vTimeId=" + vTimeId + ", vendorId=" + vendorId
				+ ", serviceCategory=" + serviceCategory + ", slotOne="
				+ slotOne + ", slotTwo=" + slotTwo + ", slotThree=" + slotThree
				+ ", slotFour=" + slotFour + ", date=" + date + "]";
	}

}
