package com.app.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.app.pojos.BookingPojo;
import com.app.pojos.CityPojo;
import com.app.pojos.LocationPojo;
import com.app.pojos.SecurityQuestionPojo;
import com.app.pojos.Services;
import com.app.pojos.UserPojo;

public interface UserDaoInterface
{
	public ArrayList<CityPojo> getCities();

	public ArrayList<LocationPojo> getLocation(String cityName);

	public String registerUser(UserPojo user);

	public UserPojo validateUser(String email, String password);

	public ArrayList<Services> getServicesList(String serviceCategory);

	public BookingPojo bookService(BookingPojo booking,String serviceCategory,HashMap<String,List<Object[]>> map, HashMap<String,List<Object[]>> map1, String loc);

	public HashMap<String,List<Object[]>> getSlots(String selectedDate,String serviceCategory, String loc);

	public ArrayList<SecurityQuestionPojo> getQuestions();

	public UserPojo getUser(String userEmail);

	public void payForService(BookingPojo temp);

	public ArrayList<BookingPojo> getBookingHistory(UserPojo attribute);

	public void changePassword(UserPojo temp);

	public ArrayList<String> getEmails();

	public UserPojo saveUser(UserPojo editUser);
}
