package com.user.controller;

import java.sql.SQLException;
import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.user.UserNotFoundException;
import com.user.modal.UserPOJO;
import com.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/users/{Id}",method=RequestMethod.GET,produces="application/JSON")
	public UserPOJO getUserById(@PathVariable int Id) throws UserNotFoundException, SQLException
	{
		LOGGER.info("Enterring method getUserById()::RestController");
		return service.getUser(Id);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/users/{userId}",method=RequestMethod.PUT,consumes="application/JSON",produces="application/JSON")
	public UserPOJO updateUser(@Valid @RequestBody UserPOJO user,@PathVariable int userId) throws UserNotFoundException, SQLException, DataIntegrityViolationException 
	{
		LOGGER.info("Enterring method updateUser()::RestController");
		return service.updateUserById(user,userId);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/users",method=RequestMethod.POST,consumes="application/JSON",produces="application/JSON")
	public UserPOJO addUser(@Valid @RequestBody UserPOJO user) throws UserNotFoundException, SQLException, DataIntegrityViolationException
	{
		LOGGER.info("Enterring method addUser()::RestController");
		return service.stroreUser(user);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/users/{userId}",method=RequestMethod.DELETE) 
	public UserPOJO deleteUser(@PathVariable int userId) throws UserNotFoundException, SQLException
	{
		LOGGER.info("Enterring method deleteUser()::RestController");
		return service.deleteUserById(userId);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/users",method=RequestMethod.GET,produces="application/JSON")
	public List<UserPOJO> getAllUser() throws UserNotFoundException, SQLException
	{
		LOGGER.info("Enterring method getAllUser()::RestController");
		return service.getAllUser();
	}
}
