package com.gh.HelloSpring.service;

import java.sql.SQLException;
import java.util.List;

import com.gh.HelloSpring.modal.Employee;
import com.gh.HelloSpring.utils.EmployeeNotFoundException;

public interface EmployeeService {

	Employee getEmployee(int id) throws EmployeeNotFoundException, SQLException;

	Employee updateEmployeeById(Employee emp, int id) throws EmployeeNotFoundException, SQLException;

	Employee stroreEmployee(Employee emp) throws EmployeeNotFoundException, SQLException;

	Employee deleteEmployeeById(int id) throws EmployeeNotFoundException, SQLException;

	List<Employee> getAllEmployee() throws EmployeeNotFoundException, SQLException;

}
