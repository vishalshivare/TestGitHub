package com.app.dao;


import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.BookingPojo;
import com.app.pojos.CityPojo;
import com.app.pojos.LocationPojo;
import com.app.pojos.ServiceCategory;
import com.app.pojos.Services;
import com.app.pojos.UserPojo;
import com.app.pojos.Vendor;
import com.app.pojos.VendorTimePojo;

@Repository
public class AdminDAO implements AdminDaoInterface {

	@Autowired
	private SessionFactory sf;
	
	public int addServiceCategory(ServiceCategory obj){
		return (int)sf.getCurrentSession().save(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceCategory> getServiceCategoryDetails() {
		
		return sf.getCurrentSession().createQuery("from ServiceCategory").list();
		
		
		
	}

	
	@Override
	public int addCity(CityPojo obj) {
	System.out.println("City obj"+obj.toString());
		int id= (int) sf.getCurrentSession().save(obj);
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CityPojo> getCityList() {
		
		return sf.getCurrentSession().createQuery("from CityPojo").list();
	}

	@Override
	public int addLocation(LocationPojo obj,String cityId) {
		System.out.println("city name"+cityId);
		CityPojo cityObj=(CityPojo) sf.getCurrentSession().createQuery("select c from CityPojo c where c.cityId = :name").setParameter("name", Integer.parseInt(cityId)).uniqueResult();
		System.out.println("city object for location"+cityObj);
		cityObj.addLocation(obj);
		obj.setCity(cityObj);
		return (int) sf.getCurrentSession().save(cityObj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationPojo> getLocationList() {
		
		return sf.getCurrentSession().createQuery("from LocationPojo").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LocationPojo> getLocationListByCity(String city) {
		
		CityPojo cityObj=(CityPojo)sf.getCurrentSession().createQuery("select c from CityPojo c where c.cityName=:name").setParameter("name", city).uniqueResult();		
		return sf.getCurrentSession().createQuery("select loc from LocationPojo loc where loc.city=:city").setParameter("city", cityObj).list();
	}

	@Override
	public ServiceCategory getServiceCategoryId(int serviceCategoryId) {
		
		return (ServiceCategory) sf.getCurrentSession().get(ServiceCategory.class, serviceCategoryId);
	}

	@Override
	public int addService(Services obj, String serviceCategoryId) {
		ServiceCategory categoryObj=(ServiceCategory) sf.getCurrentSession().get(ServiceCategory.class,Integer.parseInt(serviceCategoryId ));
		categoryObj.addServices(obj);
		obj.setServiceCategory(categoryObj);
		
		return (int) sf.getCurrentSession().save(categoryObj);
	}

	@Override
	public int addVendor(String categoryId, String vendorCityName,
			String locationName, Vendor vendorPojo) {
		
		System.out.println("Vendor Details*************"+vendorPojo.toString());
		CityPojo cityObj=(CityPojo) sf.getCurrentSession().createQuery("select c from CityPojo c where c.cityName=:name").setParameter("name", vendorCityName).uniqueResult();
		cityObj.addVendor(vendorPojo);
		vendorPojo.setVendorCityName(cityObj);
		sf.getCurrentSession().save(cityObj);
		
		
		LocationPojo locationObj=(LocationPojo) sf.getCurrentSession().createQuery("select l from LocationPojo l where l.locationName=:name").setParameter("name", locationName).uniqueResult();
		locationObj.addVendor(vendorPojo);
		vendorPojo.setVendorWorkingLocation(locationObj);
		sf.getCurrentSession().save(locationObj);
		
		ServiceCategory categoryObj=(ServiceCategory) sf.getCurrentSession().get(ServiceCategory.class, Integer.parseInt(categoryId));
		categoryObj.addVendor(vendorPojo);
		vendorPojo.setServiceCategory(categoryObj);
		int id=(int) sf.getCurrentSession().save(categoryObj);
		
		
		return id;
	}

	@Override
	public CityPojo getCity(int id) {
		
		return (CityPojo) sf.getCurrentSession().get(CityPojo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Services> getServicesDetails() {
		return sf.getCurrentSession().createQuery("from Services").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> getVendorDetails() {
		return sf.getCurrentSession().createQuery("from Vendor").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean deleteVendor(String vendorID) {
		
			System.out.println("###########################Vendor id="+vendorID);
			Vendor vendorObj=(Vendor) sf.getCurrentSession().get(Vendor.class, Integer.parseInt(vendorID));
			List<VendorTimePojo> timeObjList=(List<VendorTimePojo>) sf.getCurrentSession().createQuery("select t from VendorTimePojo t where t.vendorId=:vendor").setParameter("vendor",vendorObj).list();
			Set<VendorTimePojo> set=vendorObj.getVtimes();
			sf.getCurrentSession().createQuery("delete BookingPojo t where t.vendor=:ven").setParameter("ven",vendorObj).executeUpdate();
			LocationPojo locationObj=vendorObj.getVendorWorkingLocation();
			System.out.println("Location obj@@@@@@@@@@@@@@@@"+locationObj.toString());
			Set<VendorTimePojo> locationSet=locationObj.getVtimes();
			ServiceCategory serviceCategory=vendorObj.getServiceCategory();
			Set<Vendor> categoryVendorSet=serviceCategory.getVendors();
			categoryVendorSet.remove(vendorObj);
			serviceCategory.setVendors(categoryVendorSet);			
			
			
			for(VendorTimePojo timeObj:timeObjList){
				sf.getCurrentSession().delete(timeObj);
				System.out.println("deleting.....vendortimepojo************************"+timeObj.toString());
				
				set.remove(timeObj);
				locationSet.remove(timeObj);
			}
			
			
			
			vendorObj.setVtimes(set);
			locationObj.setVtimes(locationSet);
			System.out.println("deleting.......vendor********************"+vendorObj.toString());
			
			Set<Vendor> locationVendorSet=locationObj.getVendors();
			locationVendorSet.remove(vendorObj);
			locationObj.setVendors(locationVendorSet);
			
			CityPojo cityObj=locationObj.getCity();
			
			Set<Vendor> vendorSet=cityObj.getVendors();
			vendorSet.remove(vendorObj);
			cityObj.setVendors(vendorSet);
			
			sf.getCurrentSession().save(serviceCategory);
			sf.getCurrentSession().save(cityObj);
			sf.getCurrentSession().save(locationObj);	
			sf.getCurrentSession().delete(vendorObj);
			
			
			
			/*
			
			System.out.println("deleting.....vendortimepojo************************"+timeObj.toString());
			System.out.println("deleting.......vendor********************"+vendorObj.toString());
			
			Set<VendorTimePojo> set=vendorObj.getVtimes();
			set.remove(timeObj);
			vendorObj.setVtimes(set);
			
			LocationPojo locationObj=vendorObj.getVendorWorkingLocation();
			System.out.println("Location obj@@@@@@@@@@@@@@@@"+locationObj.toString());
			
			Set<VendorTimePojo> locationSet=locationObj.getVtimes();
			locationSet.remove(timeObj);
			locationObj.setVtimes(locationSet);
			
			Set<Vendor> locationVendorSet=locationObj.getVendors();
			locationVendorSet.remove(vendorObj);
			locationObj.setVendors(locationVendorSet);
			
			CityPojo cityObj=locationObj.getCity();
			
			Set<Vendor> vendorSet=cityObj.getVendors();
			vendorSet.remove(vendorObj);
			cityObj.setVendors(vendorSet);
			
			sf.getCurrentSession().save(cityObj);
			sf.getCurrentSession().save(locationObj);			
			sf.getCurrentSession().delete(timeObj);
			sf.getCurrentSession().delete(vendorObj);
	*/	
			
	
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPojo> getRegisteredUsers() {
		
		return sf.getCurrentSession().createQuery("from UserPojo").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookingPojo> getBookings() {
		
		return (List<BookingPojo>)sf.getCurrentSession().createQuery("from BookingPojo").list();
	}
}
