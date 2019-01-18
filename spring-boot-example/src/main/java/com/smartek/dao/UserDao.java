package com.smartek.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartek.model.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
