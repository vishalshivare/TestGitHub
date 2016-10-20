package com.app.service;

import java.util.List;

import com.app.pojos.BookingPojo;
import com.app.pojos.CityPojo;
import com.app.pojos.LocationPojo;
import com.app.pojos.ServiceCategory;
import com.app.pojos.Services;
import com.app.pojos.UserPojo;
import com.app.pojos.Vendor;

public interface AdminServiceInterface {

	public int addCategory(ServiceCategory obj);

	public List<ServiceCategory> getServiceCategoryDetails();

	
	public int addCity(CityPojo obj);

	public List<CityPojo> getCityList();

	public int addLocation(LocationPojo obj, String cityName);

	public List<LocationPojo> getLocationList();

	public List<LocationPojo> getLocationListByCity(String city);

	public ServiceCategory getServiceCategoryId(int serviceCategoryId);

	public int addService(Services obj, String serviceCategoryId);

	public int addVendor(String categoryId, String vendorCityName,
			String locationName, Vendor vendorPojo);

	public CityPojo getCity(int id);

	public List<Services> getServicesDetails();

	public List<Vendor> getVendorDetails();

	public Boolean deleteVendor(String vendorID);

	public List<UserPojo> getRegisteredUsers();

	public List<BookingPojo> getBookings();
}
