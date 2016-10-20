package com.app.pojos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DoorStep_Services_Cities")
public class CityPojo 
{
	public int cityId;
	public String cityName;
	public Set<LocationPojo> locations = new HashSet<>();
	public Set<Vendor> vendors = new HashSet<>();

	public CityPojo() {
		System.out.println("in city def constr");
	}
	
	@Id
	@GeneratedValue
	@Column(name="City_Id_PK",length=10)
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	@Column(name="City_Name",length=20)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "city" ,fetch = FetchType.EAGER)
	public Set<LocationPojo> getLocations() {
		return locations;
	}

	public void setLocations(Set<LocationPojo> locations) {
		this.locations = locations;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vendorCityName" ,fetch = FetchType.EAGER)
	public Set<Vendor> getVendors() {
		return vendors;
	}

	public void setVendors(Set<Vendor> vendors) {
		this.vendors = vendors;
	}
	
	
	public boolean addLocation(LocationPojo location )
	{
		if(locations.add(location))
		{
			location.setCity(this);
			return true;
		}
		return false;
	}
	
	public boolean removeLocation(LocationPojo location) {
		return locations.remove(location);
	}
	
	public boolean addVendor(Vendor vendorObj )
	{
		if(vendors.add(vendorObj))
		{
			vendorObj.setVendorCityName(this);
			return true;
		}
		return false;
	}
}
