package com.smartek.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smartek.model.User;
import com.smartek.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping("/")
	public String hello() {
		return "hello from spring";
	}
	
	@RequestMapping("/users")
	public List<User> getAllUsers()
	{
		return service.getAllUsers();
	}
	
	@RequestMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable int id)	{
		return service.getUser(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/users")
	public User addUser(@RequestBody User user)	{
		return service.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/users/{id}")
	public Optional<User> deleteUser(@PathVariable int id) {
		return service.deleteUser(id); 
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="users/{id}")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="insertusers")
	public void insertUsers() {
		try {
			service.insertUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
