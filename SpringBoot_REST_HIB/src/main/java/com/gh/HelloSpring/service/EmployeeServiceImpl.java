package com.gh.HelloSpring.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gh.HelloSpring.dao.EmployeeDAO;
import com.gh.HelloSpring.modal.Employee;
import com.gh.HelloSpring.utils.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO dao;
	
	@Override
	public Employee getEmployee(int id) throws EmployeeNotFoundException, SQLException {
			return dao.getEmployeeById(id);
	}

	@Override
	public Employee updateEmployeeById(Employee emp,int id) throws EmployeeNotFoundException, SQLException {
		return dao.updateEmployee(emp,id);
	}

	@Override
	public Employee stroreEmployee(Employee emp) throws EmployeeNotFoundException, SQLException {
			return dao.addEmployee(emp);
	}

	@Override
	public Employee deleteEmployeeById(int id) throws EmployeeNotFoundException, SQLException {
			return dao.deleteEmployee(id);
	}

	@Override
	public List<Employee> getAllEmployee() throws EmployeeNotFoundException, SQLException {
			return dao.getAllEmployees();
	}
}
