package com.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.pojos.BookingPojo;
import com.app.pojos.CityPojo;
import com.app.pojos.LocationPojo;
import com.app.pojos.UserPojo;
import com.app.pojos.VendorTimePojo;
import com.app.service.UserServiceInterface;

@Controller
@Transactional
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserServiceInterface service;
	HttpSession session;

	@RequestMapping("/register")
	public String showRegisterPage(ModelMap map, HttpSession session) {
		map.addAttribute("cities", service.getCities());
		map.addAttribute("questions", service.getQuestions());
		session.setAttribute("usersemail", service.getEmails());
		map.addAttribute("user", new UserPojo());
		return "register";
	}

	@RequestMapping(value = "/AddCity")
	public @ResponseBody String getSearchResultViaAjax(
			@ModelAttribute(value = "city") CityPojo city, BindingResult result) {
		ArrayList<LocationPojo> list = service.getLocation(city.cityName);
		String returnText = "<select name='location' id='location' class='form-control'><option value='000' selected='selected'>Select</option>";
		for (LocationPojo p : list) {
			returnText += "<option id=" + p.locationId + ">" + p.locationName
					+ "</option>";
		}
		returnText += "</select>";
		return returnText;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/registerUser")
	public String registerUser(UserPojo user, BindingResult result,
			ModelMap map, HttpSession session) {
		/*ArrayList<String> emails = (ArrayList<String>) session
				.getAttribute("usersemail");
		for (String s : emails) {
			if (s.equals(user.getEmail())) {
				map.addAttribute("register",
						"Email already registered Please retry");
				return "register";
			}
		}*/
		System.out.println(service.registerUser(user));
		map.addAttribute("status", "success");
		map.addAttribute("loginUser", new UserPojo());
		return "../../../home";
	}

	@RequestMapping(value = "/login")
	public String loginUser(UserPojo loginUser, BindingResult result,
			HttpSession session) {
		if ((loginUser.email).equals("admin@admin.com")
				&& (loginUser.password).equals("admin"))
			return "menu";
		session.setAttribute("loggedInUser",
				service.validateUser(loginUser.email, loginUser.password));
		if (session.getAttribute("loggedInUser") == null)
			return "invalid";
		return "home2";
	}

	@RequestMapping(value = "/booking")
	public String bookService(
			@RequestParam(value = "serviceCategory") String serviceCategory,
			ModelMap map, HttpSession session) {
		if (session.getAttribute("loggedInUser") == null) {
			// session.setAttribute("service", serviceCategory);
			map.addAttribute("status", "success");
			map.addAttribute("loginUser", new UserPojo());
			return "../../../home";
		}
		session.setAttribute("serviceCategory", serviceCategory);
		map.addAttribute("cities", service.getCities());
		if (serviceCategory.equals("Plumber"))
			map.addAttribute(
					"image",
					"http://www.uniqueplumbingsupplies.co.uk/wp-content/uploads/2014/05/banner1.png");
		else
			map.addAttribute("image",
					"http://www.fh-carpentry.co.uk/template/banner-pic01.jpg");
		map.addAttribute("listOfServices",
				service.getServicesList(serviceCategory));
		map.addAttribute("booking", new BookingPojo());
		return "booking";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public String bookService(
			@RequestParam(value = "service") String[] serviceId,
			@RequestParam(value = "city") String city,
			@RequestParam(value = "location") String loc,
			@ModelAttribute(value = "booking") BookingPojo booking,
			BindingResult result, HttpSession session) {
		System.out.println(loc + " " + city);
		String s = "";
		for (String x : serviceId)
			s += x + ",";
		String serviceIds = s.substring(0, s.length() - 1);
		System.out.println(serviceIds);
		UserPojo user = (UserPojo) session.getAttribute("loggedInUser");
		booking.setBookingUser(user);
		booking.setStatus("added");
		booking.setServiceIds(serviceIds);
		booking.setAddress(booking.getAddress() + ", " + loc + ", " + city);
		System.out.println(booking.getDate().toString());
		session.setAttribute("booking", (BookingPojo) service.bookService(
				booking, (String) session.getAttribute("serviceCategory"),
				(HashMap<String, List<Object[]>>) session
						.getAttribute("fullyAvailableVendors"),
				(HashMap<String, List<Object[]>>) session
						.getAttribute("availableVendors"), loc));
		if (session.getAttribute("booking") != null) {
			System.out.println("in booking "
					+ ((BookingPojo) session.getAttribute("booking"))
							.toString());
			return "paymentPage";
		}
		return "redirect:booking";
	}

	@RequestMapping("getSlots")
	public @ResponseBody String getSlots(
			@RequestParam(value = "date") String selectedDate,
			@RequestParam(value = "location") String loc, HttpSession session) {
		System.out.println(selectedDate);
		System.out.println("location" + loc);

		HashMap<String, List<Object[]>> slots = (HashMap<String, List<Object[]>>) service
				.getSlots(selectedDate,
						(String) session.getAttribute("serviceCategory"), loc);
		if (slots == null) {
			return "<select name='timeSlot' class='form-control'><option>8-11</option><option>11-2</option><option>2-5</option><option>5-8</option></select>";
		}
		if (slots.size() == 1) {
			String returnText = "<select name='timeSlot' class='form-control'>";
			session.setAttribute("fullyAvailableVendors", slots);
			String[] availableSlots = (String[]) slots.get("slots").get(0);
			for (String o : availableSlots) {
				System.out.println(o);
				returnText += "<option>" + o + "</option>";
			}
			returnText += "</select>";
			System.out.println(returnText);
			return returnText;
		} else {
			if (slots.size() == 0)
				return "No Available Time Slots for selected time and place Please select some other date";
			else {
				ArrayList<String> availableSlots = new ArrayList<String>();
				if (slots.get("slotOne") != null)
					availableSlots.add("8-11");
				if (slots.get("slotTwo") != null)
					availableSlots.add("11-2");
				if (slots.get("slotThree") != null)
					availableSlots.add("2-5");
				if (slots.get("slotFour") != null)
					availableSlots.add("5-8");
				String returnText = "<select name='timeSlot' class='form-control'>";
				for (String o : availableSlots) {
					System.out.println(o);
					returnText += "<option>" + o + "</option>";
				}
				returnText += "</select>";
				System.out.println(returnText);
				session.setAttribute("availableVendors", slots);
				return returnText;
			}
		}
	}

	@RequestMapping("/forgot")
	public String getEmail() {
		return "forgot";
	}

	@RequestMapping("sendEmail")
	public @ResponseBody String getSecurityQuestion(
			@RequestParam(value = "email") String userEmail, HttpSession map) {
		UserPojo temp = (UserPojo) service.getUser(userEmail);
		System.out.println(temp);
		if (temp == null)
			return "Invalid Email Id Please retry or <a href='register'>Register</a>";
		else {
			map.setAttribute("user", temp);
			return "<p>Provide the answer for your selected question</p><table class='table table-bordered'><thead style='text-align: center;'><tr><td>"
					+ temp.getSecurityQuestion()
					+ " :</td><td><input type='text' name='answer' id='answer' class='form-control' required></td></tr><tr><td colspan='2'><input type='button' value='Submit' onclick='call()''></td><tr></thead></table>";
		}
	}

	@RequestMapping("sendAnswer")
	public @ResponseBody String getPassword(
			@RequestParam(value = "answer") String ans, HttpSession map) {
		UserPojo user = (UserPojo) map.getAttribute("user");
		System.out.println("in send answer" + user.toString());
		if (ans.equals(user.getSecurityAnswer())) {
			return "Your password is " + user.getPassword()
					+ "<a href='home.jsp'>  Please login</a>";
		}
		return "<h6>Wrong Answer Please retry</h6>";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String paymentPage(HttpSession ses) {
		BookingPojo temp = (BookingPojo) ses.getAttribute("booking");
		temp.setStatus("Recieved");
		service.payForService(temp);
		// System.out.println("in payment "+((BookingPojo)ses.getAttribute("booking")).toString());
		return "payment";
	}

	@RequestMapping("/logout")
	public String userLogout(HttpSession session) {
		session.invalidate();
		return "../../../home";
	}

	@RequestMapping("/history")
	public String bookingHistory(HttpSession session, ModelMap map) {
		ArrayList<BookingPojo> list = service
				.getBookingHistory((UserPojo) session
						.getAttribute("loggedInUser"));
		if (list.size() == 0) {
			map.addAttribute("list",
					"No Booking History Please book a service today");
			return "history";
		} else {
			map.addAttribute("bookingList", list);
			return "history";
		}
	}

	@RequestMapping("changePassword")
	public String showChangePassword() {
		return "changePassword";
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public String changePassword(@RequestParam(value = "oldPass") String old,
			@RequestParam(value = "newPass") String newPass,
			HttpSession session, ModelMap map) {
		UserPojo temp = (UserPojo) session.getAttribute("loggedInUser");
		if (temp.getPassword().equals(old)) {
			temp.setPassword(newPass);
			service.changePassword(temp);
			session.invalidate();
			return "../../../home";
		} else {
			session.setAttribute("pass", "Password mismatch please retry");
			return "redirect:changePassword";
		}
	}

	@RequestMapping("/paymentSuccess")
	public String paymentSuccess() {
		return "paymentSuccess";
	}

	@RequestMapping("/home2")
	public String redirectToHome(HttpSession session) {
		if(session.getAttribute("loggedInUser")==null)
			return "invalid";
		return "home2";
	}
	
	@RequestMapping("/editUser")
	public String getEditUser(ModelMap map)
	{
		map.addAttribute("editUser",new UserPojo());
		map.addAttribute("cities", service.getCities());
		map.addAttribute("questions", service.getQuestions());
		return "editProfile";
	}

	@RequestMapping(value="/editUser",method=RequestMethod.POST)
	public String editUser(UserPojo editUser, HttpSession session)
	{
		System.out.println(editUser.getFirstName());
		editUser.setEmail(((UserPojo)session.getAttribute("loggedInUser")).getEmail());
		editUser.setPassword(((UserPojo)session.getAttribute("loggedInUser")).getPassword());
		editUser.setUserId(((UserPojo)session.getAttribute("loggedInUser")).getUserId());
		session.setAttribute("loggedInUser",service.saveUser(editUser));
		session.setAttribute("updateSuccess", "Profile Updated Successfully");
		return "redirect:editUser";
	}
}
