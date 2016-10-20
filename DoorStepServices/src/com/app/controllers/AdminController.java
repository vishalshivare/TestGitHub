package com.app.controllers;


import java.util.List;











import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.pojos.BookingPojo;
import com.app.pojos.CityPojo;
import com.app.pojos.LocationPojo;
import com.app.pojos.ServiceCategory;
import com.app.pojos.Services;
import com.app.pojos.UserPojo;
import com.app.pojos.Vendor;
import com.app.service.AdminServiceInterface;



@Controller
@Transactional
public class AdminController {

	@Autowired
	AdminServiceInterface adminService;
	
	@RequestMapping(value="/getter",method=RequestMethod.GET)
	public @ResponseBody String getter(@RequestParam(value="cityName") String city, Model map){
		
		//CityPojo obj=adminService.getCity(id);
		System.out.println("+++++++++++++++"+city);
		List<LocationPojo> location;
		location=adminService.getLocationListByCity(city);
		
		String msg="<select id='location' name='locationName' class='btn btn-default'>";
		for(LocationPojo l: location)
		{System.out.println("location ajax"+l.getLocationName());
			msg+="<option id="+l.getLocationId()+">"+l.getLocationName()+"</option>";
		}
		msg+="</select>";
		return msg;
	}
	
	@RequestMapping(value="/menu")
	public String generateMenu(){
		System.out.println("Menu Generated");
		return "menu";
	}
	//add service category 
	@RequestMapping(value="/serviceCategory")
	public String getServiceCategory(Model map){
		List<ServiceCategory> list;
		list=adminService.getServiceCategoryDetails();
		System.out.println(list);
		map.addAttribute("servicelist",list);
			
		map.addAttribute(new ServiceCategory());
		return "serviceCategory";
	}
	@RequestMapping(value="/serviceCategory",method=RequestMethod.POST)
	public String postServiceCategory(ServiceCategory obj,Model map){
		System.out.println(adminService.addCategory(obj));
		return "redirect:serviceCategory";
	}
	
	//add services
	@RequestMapping(value="/services")
	public String getService(Model map){
		
		List<Services> slist;
		slist=adminService.getServicesDetails();
		System.out.println(slist);
		map.addAttribute("servicesDetailList",slist);
		
		
		List<ServiceCategory> list;
		list=adminService.getServiceCategoryDetails();
		System.out.println(list);
		map.addAttribute("servicelist",list);
		map.addAttribute(new Services());
		String serviceCategoryId=null;
		map.addAttribute("serviceCategoryId",serviceCategoryId);
		
		
		
		return "services";
	}
	
	@RequestMapping(value="/services",method=RequestMethod.POST)
	public String postService(Services obj,String serviceCategoryId,Model map){
		
		System.out.println("Service added with id="+adminService.addService(obj,serviceCategoryId));
		return "redirect:services";
	}
	
	//add city
	@RequestMapping(value="/city")
	public String getCity(Model map){
		
		List<CityPojo> list;
		list=adminService.getCityList();
		map.addAttribute("cityList",list);
		
		map.addAttribute("cityPojoObj",new CityPojo());
		System.out.println("in get city");
		return "city";
	}
	
	@RequestMapping(value="/city" ,method=RequestMethod.POST)
	public String postCity(CityPojo cityPojoObj,Model map){
		System.out.println("in post city...cityName="+cityPojoObj.getCityName());
		System.out.println(adminService.addCity(cityPojoObj));
		System.out.println("after post city");
		return "redirect:city";
	}
	
	//add location
	
	@RequestMapping(value="/location")
	public String getLocation(Model map){
		String cityName=null, locationName=null;
		List<CityPojo> list;
		list=adminService.getCityList();
		
		List<LocationPojo> locationList;
		locationList=adminService.getLocationList();
		map.addAttribute("locationList",locationList);
		
		System.out.println("in getlocation()....."+list);
		map.addAttribute("cityList",list);
		map.addAttribute("cityName",cityName);
		map.addAttribute("LocationName", locationName);
		String cityId=null;
		map.addAttribute("cityId",cityId);
		return "location";
	}
	
	@RequestMapping(value="/location", method=RequestMethod.POST)
	public String postLocation(String locationName, String cityId,Model map){
		//obj.city.addLocation(obj);
		System.out.println("city name1:"+cityId);
		System.out.println("location name1:"+locationName);
		LocationPojo obj=new LocationPojo();
		obj.setLocationName(locationName);
		System.out.println(adminService.addLocation(obj,cityId));
		
		return "redirect:location";
	}
	
	//add vendor
	@RequestMapping(value="/vendor")
	public String getVendorPage(Model map){
		
		List<Vendor> vlist;
		vlist=adminService.getVendorDetails();
		System.out.println(vlist);
		map.addAttribute("vendorList",vlist);
		
		//getting service category
			List<ServiceCategory> list;
			list=adminService.getServiceCategoryDetails();
			System.out.println(list);
			map.addAttribute("servicelist",list);
		
		//getting cities list
		
			List<CityPojo> cityList;
			cityList=adminService.getCityList();
			System.out.println("cityList"+cityList);
			map.addAttribute("cityList",cityList);
		
		//getting location
		
			List<LocationPojo> locationList;
			locationList=adminService.getLocationList();
			System.out.println("locationList"+locationList);
			map.addAttribute("locationList",locationList);
		
		//adding pojo of vendor
			map.addAttribute("vendorPojo",new Vendor());
		
		//adding variable for holding service category id
		
			String categoryId=null;
			map.addAttribute("categoryId", categoryId);
		
		//adding variable for holding city name
			String vendorCity=null;
			map.addAttribute("vendorCityId", vendorCity);
		
		//adding variable for holding location name
			String locationId=null;
			map.addAttribute("locationId", locationId);
			
		return "vendor";
	}
	
	
	@RequestMapping(value="/vendor",method=RequestMethod.POST)
	public String postVendorPage(String categoryId,String vendorCity,String locationName,@ModelAttribute(value="vendorPojo") Vendor vendor,BindingResult result){
		System.out.println("*********#######"+vendor.toString());
		System.out.println("Vendor added "+ adminService.addVendor(categoryId,vendorCity,locationName,vendor)); 
		return "redirect:vendor";
		
		
	}
	
	
	
	//view registered users
	@RequestMapping(value="/users")
	public String getUsers(Model map){
		List<UserPojo> list=adminService.getRegisteredUsers();
		System.out.println("Registered Users-->"+list);
		map.addAttribute("userList",list);
		return "users";
	}
	
			
	//delete vendor
			@RequestMapping(value="/delete-vendor",method=RequestMethod.POST)
			public String deleteVendor(@RequestParam(value="vendorID") String vendorID){
				adminService.deleteVendor(vendorID);
				
			return "redirect:vendor";	
			}
			
			@RequestMapping(value="show-bookings")
			public String getShowBookingToAdmin(Model map){
				List<BookingPojo> list=adminService.getBookings();
				map.addAttribute("bookingList", list);
				System.out.println("bookingList......"+list);
				return "show-bookings";
			}
			
}//end of controller class
