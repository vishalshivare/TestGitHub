package com.smartek.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smartek.dao.UserDao;
import com.smartek.model.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao dao;
	@Autowired
	private EntityManager entityManager; 
	
	public List<User> getAllUsers()
	{
		return (List<User>) dao.findAll();
	}

	public Optional<User> getUser(int id) {
		return dao.findById(id);
	}

	public User addUser(User user) {
		return dao.save(user);
	}

	public User updateUser(User user) {
		return dao.save(user);
	}

	public Optional<User> deleteUser(int id) {
		Optional<User> user=dao.findById(id);
		if(user != null){
			dao.deleteById(id);
		}
		return user;
	}
	
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
	public void insertUsers() throws Exception {
			String query = "insert into user values(44,'a3', 'b1', 10, 'pune'),(47,'a4', 'b4', 10, 'pune')";
			entityManager.createNativeQuery(query).executeUpdate();
			throw new RuntimeException();
	}
}
