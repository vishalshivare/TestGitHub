package com.app.service;

import java.util.List;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.*;
import com.app.pojos.BookingPojo;
import com.app.pojos.CityPojo;
import com.app.pojos.LocationPojo;
import com.app.pojos.ServiceCategory;
import com.app.pojos.Services;
import com.app.pojos.UserPojo;
import com.app.pojos.Vendor;

@Service
public class AdminService implements AdminServiceInterface {

	@Autowired
	AdminDaoInterface dao;
	
	public int addCategory(ServiceCategory obj){
		return dao.addServiceCategory(obj);
	}

	@Override
	public List<ServiceCategory> getServiceCategoryDetails() {
		
		return dao.getServiceCategoryDetails();
		
	}

	

	@Override
	public int addCity(CityPojo obj) {
		
		return dao.addCity(obj);
	}

	@Override
	public List<CityPojo> getCityList() {
		
		return dao.getCityList();
	}

	@Override
	public int addLocation(LocationPojo obj, String cityName) {
		
		return dao.addLocation(obj,cityName);
	}

	@Override
	public List<LocationPojo> getLocationList() {
		
		return  dao.getLocationList();
	}

	@Override
	public List<LocationPojo> getLocationListByCity(String city) {
		return dao.getLocationListByCity(city);
	}

	@Override
	public ServiceCategory getServiceCategoryId(int serviceCategoryId) {
		
		return (ServiceCategory)dao.getServiceCategoryId(serviceCategoryId);
	}

	@Override
	public int addService(Services obj, String serviceCategoryId) {
		
		return dao.addService(obj,serviceCategoryId);
	}

	@Override
	public int addVendor(String categoryId, String vendorCityName,
			String locationName, Vendor vendorPojo) {
		
		return dao.addVendor(categoryId,vendorCityName,locationName,vendorPojo);
	}

	@Override
	public CityPojo getCity(int id) {
		
		return dao.getCity(id);
	}

	@Override
	public List<Services> getServicesDetails() {
		return dao.getServicesDetails();
	}

	@Override
	public List<Vendor> getVendorDetails() {
		return dao.getVendorDetails();
	}

	@Override
	public Boolean deleteVendor(String vendorID) {
		
		return dao.deleteVendor(vendorID);
	}

	@Override
	public List<UserPojo> getRegisteredUsers() {
		
		return dao.getRegisteredUsers();
	}

	@Override
	public List<BookingPojo> getBookings() {
		
		return dao.getBookings();
	}

}
