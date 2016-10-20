package com.user.dao;

import java.util.List;

import com.user.UserNotFoundException;
import com.user.modal.UserPOJO;

public interface UserDao {

	UserPOJO getUserById(int id) throws UserNotFoundException;

	UserPOJO updateUser(UserPOJO emp, int id) throws UserNotFoundException;

	UserPOJO addUser(UserPOJO emp) throws UserNotFoundException;

	UserPOJO deleteUser(int id) throws UserNotFoundException;

	List<UserPOJO> getAllUsers() throws UserNotFoundException;

}
