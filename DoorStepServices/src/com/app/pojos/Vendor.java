package com.app.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="DoorStep_Services_Vendors")
public class Vendor {

	private int vendorId;
	private String vendorName;
	private String vendorContact;
	private ServiceCategory serviceCategory;
	private String vendorAddress;
	private LocationPojo vendorWorkingLocation;
	private CityPojo vendorCityName;
	private Set<BookingPojo> bookings=new HashSet<BookingPojo>();
	private Set<VendorTimePojo> vtimes = new HashSet<VendorTimePojo>();
	
	public Vendor(){
		System.out.println("in vendor def constr");
	}

	@Id
	@GeneratedValue
	@Column(name="Vendor_Id_PK",length=10)
	public int getVendorId() {
		return vendorId;
	}
	
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	@Column(name="Vendor_Name",length=50)
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	@Column(name="Vendor_Contact",length=10)
	public String getVendorContact() {
		return vendorContact;
	}

	public void setVendorContact(String vendorContact) {
		this.vendorContact = vendorContact;
	}
	
	@ManyToOne
	@JoinColumn(name="SCategories_Id_PK")
	public ServiceCategory getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}	
	
	@Column(name="Vendor_Address")
	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	
	@ManyToOne
	@JoinColumn(name="Location_Id_PK")
	public LocationPojo getVendorWorkingLocation() {
		return vendorWorkingLocation;
	}
	
	public void setVendorWorkingLocation(LocationPojo vendorWorkingLocation) {
		this.vendorWorkingLocation = vendorWorkingLocation;
	}

	
	@ManyToOne
	@JoinColumn(name="City_Id_PK")
	public CityPojo getVendorCityName() {
		return vendorCityName;
	}

	public void setVendorCityName(CityPojo vendorCityName) {
		this.vendorCityName = vendorCityName;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="vendor",fetch=FetchType.EAGER)
	public Set<BookingPojo> getBookings() {
		return bookings;
	}

	public void setBookings(Set<BookingPojo> bookings) {
		this.bookings = bookings;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="vendorId")
	public Set<VendorTimePojo> getVtimes() {
		return vtimes;
	}

	public void setVtimes(Set<VendorTimePojo> vtimes) {
		this.vtimes = vtimes;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName
				+ ", vendorContact=" + vendorContact + ", serviceCategory="
				+ serviceCategory + ", vendorAddress=" + vendorAddress
				+ ", vendorWorkingLocation=" + vendorWorkingLocation
				+ ", vendorCityName=" + vendorCityName + "]";
	}


	
	
}
