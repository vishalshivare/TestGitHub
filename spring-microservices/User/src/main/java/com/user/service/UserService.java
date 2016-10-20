package com.user.service;

import java.util.List;

import com.user.UserNotFoundException;
import com.user.modal.UserPOJO;

public interface UserService {

	UserPOJO getUser(int id) throws UserNotFoundException;

	UserPOJO updateUserById(UserPOJO user, int id) throws UserNotFoundException;

	UserPOJO stroreUser(UserPOJO user) throws UserNotFoundException;

	UserPOJO deleteUserById(int id) throws UserNotFoundException;

	List<UserPOJO> getAllUser() throws UserNotFoundException;

}
