package com.gh.HelloSpring.dao;

import java.sql.SQLException;
import java.util.List;

import com.gh.HelloSpring.modal.Employee;
import com.gh.HelloSpring.utils.EmployeeNotFoundException;

public interface EmployeeDAO {

	Employee getEmployeeById(int id) throws EmployeeNotFoundException, SQLException;

	Employee updateEmployee(Employee emp, int id) throws EmployeeNotFoundException, SQLException;

	Employee addEmployee(Employee emp) throws EmployeeNotFoundException, SQLException;

	Employee deleteEmployee(int id) throws EmployeeNotFoundException, SQLException;

	List<Employee> getAllEmployees() throws EmployeeNotFoundException, SQLException;

}
