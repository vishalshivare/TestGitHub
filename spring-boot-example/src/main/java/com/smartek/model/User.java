package com.smartek.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user_data")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	@NotEmpty
	private String firstname;
	@Column(unique=true)
	private String lastname;
	@Max(value=80,message="must be less than 80")
	private int age;
	private String city;
	
	public User() {
		super();
	}
		
	public User(int id,String firstname, String lastname, int age, String city) {
		super();
		this.id=id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getlastname() {
		return lastname;
	}
	public void setlastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
