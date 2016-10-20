package com.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserDaoInterface;
import com.app.pojos.BookingPojo;
import com.app.pojos.CityPojo;
import com.app.pojos.LocationPojo;
import com.app.pojos.SecurityQuestionPojo;
import com.app.pojos.Services;
import com.app.pojos.UserPojo;


@Service
public class UserService implements UserServiceInterface
{
	@Autowired
	UserDaoInterface dao;
	
	/*public void addCity()
	{
		dao.addCity();
	}*/
	
	public ArrayList<CityPojo> getCities()
	{
		return dao.getCities();
	}

	@Override
	public ArrayList<LocationPojo> getLocation(String cityName) 
	{
		return dao.getLocation(cityName); 
		
	}

	@Override
	public String registerUser(UserPojo user) {
		return dao.registerUser(user);
		
	}

	@Override
	public UserPojo validateUser(String email, String password) {
		return dao.validateUser(email,password);
	}

	@Override
	public ArrayList<Services> getServicesList(String serviceCategory) {
		
		return dao.getServicesList(serviceCategory);
	}

	@Override
	public BookingPojo bookService(BookingPojo booking,String serviceCategory,HashMap<String,List<Object[]>> map, HashMap<String,List<Object[]>> map1,String loc) {
		
		return dao.bookService(booking,serviceCategory,map,map1,loc);
	}

	@Override
	public HashMap<String,List<Object[]>> getSlots(String selectedDate,String serviceCategory,String loc) {
		return dao.getSlots(selectedDate,serviceCategory,loc);
	}

	@Override
	public ArrayList<SecurityQuestionPojo> getQuestions() {
		return dao.getQuestions();
	}

	@Override
	public UserPojo getUser(String userEmail) {
		return dao.getUser(userEmail);
	}

	@Override
	public void payForService(BookingPojo temp) {
		dao.payForService(temp);
		
	}

	@Override
	public ArrayList<BookingPojo> getBookingHistory(UserPojo attribute) {
		return dao.getBookingHistory(attribute);
	}

	@Override
	public void changePassword(UserPojo temp) {
		dao.changePassword(temp);
		
	}

	@Override
	public ArrayList<String> getEmails() {
		return dao.getEmails();
	}

	@Override
	public UserPojo saveUser(UserPojo editUser) {
		return dao.saveUser(editUser);
	}
}
