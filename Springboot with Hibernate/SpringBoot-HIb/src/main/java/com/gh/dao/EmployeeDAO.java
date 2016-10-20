package com.gh.dao;

import java.util.List;
import com.gh.modal.Employee;
import com.gh.utils.EmployeeNotFoundException;

public interface EmployeeDAO {

	Employee getEmployeeById(int id) throws EmployeeNotFoundException;

	Employee updateEmployee(Employee emp, int id) throws EmployeeNotFoundException;

	Employee addEmployee(Employee emp) throws EmployeeNotFoundException;

	Employee deleteEmployee(int id) throws EmployeeNotFoundException;

	List<Employee> getAllEmployees() throws EmployeeNotFoundException;
}
