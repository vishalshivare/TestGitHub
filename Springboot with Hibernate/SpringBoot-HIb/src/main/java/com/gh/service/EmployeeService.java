package com.gh.service;

import java.util.List;
import com.gh.modal.Employee;
import com.gh.utils.EmployeeNotFoundException;

public interface EmployeeService {

	Employee getEmployee(int id) throws EmployeeNotFoundException;

	Employee updateEmployeeById(Employee emp, int id) throws EmployeeNotFoundException;

	Employee stroreEmployee(Employee emp) throws EmployeeNotFoundException;

	Employee deleteEmployeeById(int id) throws EmployeeNotFoundException;

	List<Employee> getAllEmployee() throws EmployeeNotFoundException;

}
