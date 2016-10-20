package com.user.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.UserNotFoundException;
import com.user.dao.UserDao;
import com.user.modal.UserPOJO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	@Override
	public UserPOJO getUser(int id) throws UserNotFoundException {
			return dao.getUserById(id);
	}

	@Override
	public UserPOJO updateUserById(UserPOJO emp,int id) throws UserNotFoundException {
		return dao.updateUser(emp,id);
	}

	@Override
	public UserPOJO stroreUser(UserPOJO emp) throws UserNotFoundException {
			return dao.addUser(emp);
	}

	@Override
	public UserPOJO deleteUserById(int id) throws UserNotFoundException {
			return dao.deleteUser(id);
	}

	@Override
	public List<UserPOJO> getAllUser() throws UserNotFoundException {
			return dao.getAllUsers();
	}
}
