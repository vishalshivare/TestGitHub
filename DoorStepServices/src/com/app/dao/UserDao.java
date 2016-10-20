package com.app.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.BookingPojo;
import com.app.pojos.CityPojo;
import com.app.pojos.LocationPojo;
import com.app.pojos.SecurityQuestionPojo;
import com.app.pojos.ServiceCategory;
import com.app.pojos.Services;
import com.app.pojos.UserPojo;
import com.app.pojos.Vendor;
import com.app.pojos.VendorTimePojo;

@Repository
public class UserDao implements UserDaoInterface {
	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	public ArrayList<CityPojo> getCities() {
		ArrayList<CityPojo> listOfCities = (ArrayList<CityPojo>) factory
				.getCurrentSession().createQuery("select c from CityPojo c")
				.list();
		return listOfCities;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public ArrayList<LocationPojo> getLocation(String cityName) {
		System.out.println(cityName);
		CityPojo city = (CityPojo) factory
				.getCurrentSession()
				.createQuery(
						"select city from CityPojo city where cityName=:name")
				.setParameter("name", cityName).uniqueResult();
		ArrayList<LocationPojo> listOfLocations = (ArrayList<LocationPojo>) factory
				.getCurrentSession()
				.createQuery("select l from LocationPojo l where city=:id")
				.setParameter("id", city).list();
		System.out.println(city.getCityId());
		for (LocationPojo p : listOfLocations) {
			System.out.println(p.locationName);
		}
		return listOfLocations;
	}

	@Override
	public String registerUser(UserPojo user) {
		int id;
		try {
			id = (int) factory.getCurrentSession().save(user);
		} catch (Exception e) {
			return "Failure";
		}
		if (id > 0)
			return "Success";
		return "Failure";

	}

	@Override
	public UserPojo validateUser(String email, String password) {
		System.out.println(email + "" + password);
		UserPojo user = (UserPojo) factory
				.getCurrentSession()
				.createQuery(
						"select u from UserPojo u where u.email=:email and u.password=:pass")
				.setParameter("email", email).setParameter("pass", password)
				.uniqueResult();
		if (user != null) {
			System.out.println(user.toString());
			return user;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Services> getServicesList(String serviceCategory) {
		ServiceCategory serviceCategoryObj = (ServiceCategory) factory
				.getCurrentSession()
				.createQuery(
						"select s from ServiceCategory s where s.serviceCategoryName=:name")
				.setParameter("name", serviceCategory).uniqueResult();
		return (ArrayList<Services>) factory
				.getCurrentSession()
				.createQuery(
						"select s from Services s where s.serviceCategory=:id")
				.setParameter("id", serviceCategoryObj).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public BookingPojo bookService(BookingPojo booking, String serviceCategory,
			HashMap<String, List<Object[]>> map,
			HashMap<String, List<Object[]>> map1, String loc) {
		LocationPojo bookingLocation = (LocationPojo) factory
				.getCurrentSession()
				.createQuery(
						"select l from LocationPojo l where l.locationName=:name")
				.setParameter("name", loc).uniqueResult();
		ServiceCategory serviceCategoryObj = (ServiceCategory) factory
				.getCurrentSession()
				.createQuery(
						"select s from ServiceCategory s where s.serviceCategoryName=:name")
				.setParameter("name", serviceCategory).uniqueResult();
		booking.setLocations(bookingLocation);
		System.out.println(bookingLocation.getLocationId());
		System.out.println(serviceCategoryObj.getServiceCategoryId());
		Scanner sc = new Scanner(booking.getServiceIds());
		sc.useDelimiter(",");
		double totalBill = 0;
		while (sc.hasNext()) {
			Integer temp = Integer.parseInt(sc.next());
			totalBill += ((Services) factory.getCurrentSession().get(
					Services.class, temp)).getRate();
		}
		sc.close();
		System.out.println(totalBill);
		booking.setBill(totalBill);
		Integer[] vendors;
		if (map == null && map1 == null) {
			Vendor selectedVendor = ((List<Vendor>) factory
					.getCurrentSession()
					.createQuery(
							"select v from Vendor v where v.serviceCategory=:cat and v.vendorWorkingLocation=:loc")
					.setParameter("cat", serviceCategoryObj)
					.setParameter("loc", bookingLocation).list()).get(0);
			booking.setVendor(selectedVendor);
		} else if (map != null) {
			vendors = (Integer[]) map.get("slots").get(1);
			Vendor selectedVendor = (Vendor) factory.getCurrentSession().get(
					Vendor.class, vendors[0]);
			System.out.println("in full " + selectedVendor.toString());
			booking.setVendor(selectedVendor);
		} else {
			Integer vendorId;
			Vendor selectedVendor = new Vendor();
			if (booking.getTimeSlot().equals("8-11")) {
				vendorId = (Integer) ((BigDecimal) map1.get("slotOne").get(0)[1])
						.intValueExact();
				selectedVendor = (Vendor) factory.getCurrentSession().get(
						Vendor.class, vendorId);
				System.out.println(selectedVendor.toString());
			} else if (booking.getTimeSlot().equals("11-2")) {
				vendorId = (Integer) ((BigDecimal) map1.get("slotOne").get(0)[1])
						.intValueExact();
				selectedVendor = (Vendor) factory.getCurrentSession().get(
						Vendor.class, vendorId);
				System.out.println(selectedVendor.toString());
			} else if (booking.getTimeSlot().equals("2-5")) {
				vendorId = (Integer) ((BigDecimal) map1.get("slotOne").get(0)[1])
						.intValueExact();
				selectedVendor = (Vendor) factory.getCurrentSession().get(
						Vendor.class, vendorId);
				System.out.println(selectedVendor.toString());
			} else if (booking.getTimeSlot().equals("5-8")) {
				vendorId = (Integer) ((BigDecimal) map1.get("slotOne").get(0)[1])
						.intValueExact();
				selectedVendor = (Vendor) factory.getCurrentSession().get(
						Vendor.class, vendorId);
				System.out.println(selectedVendor.toString());
			}
			booking.setVendor(selectedVendor);
		}
		Integer id = (Integer) factory.getCurrentSession().save(booking);
		return (BookingPojo) factory.getCurrentSession().get(BookingPojo.class,
				id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, List<Object[]>> getSlots(String selectedDate,
			String serviceCategory, String loc) {
		ServiceCategory serviceCategoryObj = (ServiceCategory) factory
				.getCurrentSession()
				.createQuery(
						"select s from ServiceCategory s where s.serviceCategoryName=:name")
				.setParameter("name", serviceCategory).uniqueResult();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		List<Object[]> slots = new ArrayList<Object[]>();
		HashMap<String, List<Object[]>> x = new HashMap<String, List<Object[]>>();
		LocationPojo vendorLocation = (LocationPojo) factory
				.getCurrentSession()
				.createQuery(
						"select l from LocationPojo l where l.locationName=:name")
				.setParameter("name", loc).uniqueResult();
		System.out.println("date" + selectedDate.toString());
		System.out.println("cat" + serviceCategory);
		try {
			ids = (ArrayList<Integer>) factory
					.getCurrentSession()
					.createQuery(
							"select v.vendorId from Vendor v where v!= ALL(select vt.vendorId from VendorTimePojo vt where vt.serviceCategory=:id and vt.date=:d) and v.serviceCategory=:cat and v.vendorWorkingLocation=:location")
					.setParameter("id", serviceCategoryObj)
					.setParameter(
							"d",
							new SimpleDateFormat("MM/dd/yyyy")
									.parse(selectedDate))
					.setParameter("cat", serviceCategoryObj)
					.setParameter("location", vendorLocation).list();
			System.out.println(ids);
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			List<Object[]> l = (List<Object[]>) factory
					.getCurrentSession()
					.createQuery(
							"select v.vendorId,v.serviceCategory from VendorTimePojo v where v.date=:d ")
					.setParameter(
							"d",
							new SimpleDateFormat("MM/dd/yyyy")
									.parse(selectedDate)).list();
			if (l.size() == 0)
				return null;
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (ids.size() > 0) {
			String[] s = { "8-11", "11-2", "2-5", "5-8" };
			Integer[] availableIds = new Integer[ids.size()];
			int y = 0;
			for (Integer i : ids) {
				availableIds[y] = i;
				y++;
			}
			slots.add(s);
			slots.add(availableIds);
			x.put("slots", slots);
			return x;
		} else
			try {

				List<Object[]> slotFour = (List<Object[]>) factory
						.getCurrentSession()
						.createSQLQuery(
								"select slot_four,vendor_id_pk  from (select * from Vendor_TimeSlot where SCATEGORIES_ID_PK=:id and BOOKING_DATE=:d and LOCATION_ID_PK=:loc) test where slot_four='available'")
						.setParameter("id", serviceCategoryObj)
						.setParameter(
								"d",
								new SimpleDateFormat("MM/dd/yyyy")
										.parse(selectedDate))
						.setParameter("loc", vendorLocation).list();
				List<Object[]> slotThree = (List<Object[]>) factory
						.getCurrentSession()
						.createSQLQuery(
								"select slot_three,vendor_id_pk from (select * from Vendor_TimeSlot where SCATEGORIES_ID_PK=:id and BOOKING_DATE=:d and LOCATION_ID_PK=:loc) test where slot_three='available'")
						.setParameter("id", serviceCategoryObj)
						.setParameter(
								"d",
								new SimpleDateFormat("MM/dd/yyyy")
										.parse(selectedDate))
						.setParameter("loc", vendorLocation).list();
				List<Object[]> slotTwo = (List<Object[]>) factory
						.getCurrentSession()
						.createSQLQuery(
								"select slot_two,vendor_id_pk from (select * from Vendor_TimeSlot where SCATEGORIES_ID_PK=:id and BOOKING_DATE=:d and LOCATION_ID_PK=:loc) test where slot_two='available'")
						.setParameter("id", serviceCategoryObj)
						.setParameter(
								"d",
								new SimpleDateFormat("MM/dd/yyyy")
										.parse(selectedDate))
						.setParameter("loc", vendorLocation).list();
				List<Object[]> slotOne = (List<Object[]>) factory
						.getCurrentSession()
						.createSQLQuery(
								"select slot_one,vendor_id_pk from (select * from Vendor_TimeSlot where SCATEGORIES_ID_PK=:id and BOOKING_DATE=:d and LOCATION_ID_PK=:loc) test where slot_one='available'")
						.setParameter("id", serviceCategoryObj)
						.setParameter(
								"d",
								new SimpleDateFormat("MM/dd/yyyy")
										.parse(selectedDate))
						.setParameter("loc", vendorLocation).list();
				for (Object[] ob : slotOne)
					System.out.println(ob[0].toString() + " "
							+ ob[1].toString());
				if (slotFour.size() > 0)
					x.put("slotFour", slotFour);
				if (slotThree.size() > 0)
					x.put("slotThree", slotThree);
				if (slotTwo.size() > 0)
					x.put("slotTwo", slotTwo);
				if (slotOne.size() > 0)
					x.put("slotOne", slotOne);
			} catch (HibernateException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return x;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<SecurityQuestionPojo> getQuestions() {
		ArrayList<SecurityQuestionPojo> q = (ArrayList<SecurityQuestionPojo>) factory
				.getCurrentSession()
				.createQuery("select q from SecurityQuestionPojo q").list();
		for (SecurityQuestionPojo securityQuestionPojo : q) {
			System.out.println(securityQuestionPojo.getQuestion());
		}
		return q;
	}

	@Override
	public UserPojo getUser(String userEmail) {
		return (UserPojo) factory.getCurrentSession()
				.createQuery("select u from UserPojo u where u.email=:e")
				.setParameter("e", userEmail).uniqueResult();
	}

	@Override
	public void payForService(BookingPojo temp) {
		factory.getCurrentSession().update(temp);
		VendorTimePojo vt = new VendorTimePojo();
		vt.setDate(temp.getDate());
		System.out.println("location from booking"+temp.getLocations().getLocationName());
		vt.setLocation(temp.getLocations());
		vt.setVendorId(temp.getVendor());
		if (temp.getTimeSlot().equals("8-11")) {
			vt.setSlotOne("Busy");
			vt.setSlotTwo("available");
			vt.setSlotThree("available");
			vt.setSlotFour("available");
		}
		if (temp.getTimeSlot().equals("11-2")) {
			vt.setSlotOne("available");
			vt.setSlotTwo("Busy");
			vt.setSlotThree("available");
			vt.setSlotFour("available");
		}
		if (temp.getTimeSlot().equals("2-5")) {
			vt.setSlotOne("available");
			vt.setSlotTwo("available");
			vt.setSlotThree("Busy");
			vt.setSlotFour("available");
		}
		if (temp.getTimeSlot().equals("5-8")) {
			vt.setSlotOne("available");
			vt.setSlotTwo("available");
			vt.setSlotThree("available");
			vt.setSlotFour("Busy");
		}
		System.out.println("vt pojo" + vt.toString());
		Scanner sc = new Scanner(temp.getServiceIds());
		sc.useDelimiter(",");
		ServiceCategory serviceCAt = (ServiceCategory) factory
				.getCurrentSession()
				.createQuery(
						"select s.serviceCategory from Services s where s.serviceId=:id")
				.setParameter("id", Integer.parseInt(sc.next())).uniqueResult();
		sc.close();
		System.out.println(serviceCAt.getServiceCategoryId());
		vt.setServiceCategory(serviceCAt);
		Integer r = (Integer) factory.getCurrentSession().save(vt);
		System.out.println(r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<BookingPojo> getBookingHistory(UserPojo attribute) {
		return (ArrayList<BookingPojo>) factory
				.getCurrentSession()
				.createQuery(
						"select b from BookingPojo b where b.bookingUser=:user and b.status=:stat")
				.setParameter("user", attribute).setParameter("stat", "Recieved").list();
	}

	@Override
	public void changePassword(UserPojo temp) {
		factory.getCurrentSession().update(temp);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> getEmails() {
		
		ArrayList<String> emails = (ArrayList<String>)factory.getCurrentSession().createQuery("select u.email from UserPojo u").list(); 
		return emails;
	}

	@Override
	public UserPojo saveUser(UserPojo editUser) {
		
		factory.getCurrentSession().update(editUser);
		return (UserPojo)factory.getCurrentSession().get(UserPojo.class, editUser.getUserId());
	}
}
