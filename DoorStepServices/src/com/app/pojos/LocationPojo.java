package com.app.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="DoorStep_Services_Locations")
public class LocationPojo
{
	public int locationId;
	public String locationName;
	public CityPojo city;
	public Set<Vendor> vendors = new HashSet<>();
	public Set<VendorTimePojo> vtimes= new HashSet<VendorTimePojo>();
	public Set<BookingPojo> bookings = new HashSet<BookingPojo>();
	
	public LocationPojo() {
		System.out.println("in location def constr");
	}
	
	@Id
	@Column(name="Location_Id_PK")
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	
	@Column(name="Location_Name")
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	@ManyToOne
	@JoinColumn(name="City_Id_PK")
	public CityPojo getCity() {
		return city;
	}
	public void setCity(CityPojo city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + locationId;
		return result;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorWorkingLocation" ,fetch = FetchType.EAGER)
	public Set<Vendor> getVendors() {
		return vendors;
	}

	public void setVendors(Set<Vendor> vendors) {
		this.vendors = vendors;
	}
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="location")
	public Set<VendorTimePojo> getVtimes() {
		return vtimes;
	}

	public void setVtimes(Set<VendorTimePojo> vtimes) {
		this.vtimes = vtimes;
	}

	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="locations")
	public Set<BookingPojo> getBookings() {
		return bookings;
	}

	public void setBookings(Set<BookingPojo> bookings) {
		this.bookings = bookings;
	}

	public boolean addVendor(Vendor vendorObj) {
		if (vendors.add(vendorObj)) {
			vendorObj.setVendorWorkingLocation(this);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationPojo other = (LocationPojo) obj;
		if (locationId != other.locationId)
			return false;
		return true;
	}
	
}
