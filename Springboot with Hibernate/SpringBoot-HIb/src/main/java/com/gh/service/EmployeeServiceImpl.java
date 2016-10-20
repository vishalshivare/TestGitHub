package com.gh.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gh.dao.EmployeeDAO;
import com.gh.modal.Employee;
import com.gh.utils.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO dao;
	
	@Override
	public Employee getEmployee(int id) throws EmployeeNotFoundException {
			return dao.getEmployeeById(id);
	}

	@Override
	public Employee updateEmployeeById(Employee emp,int id) throws EmployeeNotFoundException {
		return dao.updateEmployee(emp,id);
	}

	@Override
	public Employee stroreEmployee(Employee emp) throws EmployeeNotFoundException {
			return dao.addEmployee(emp);
	}

	@Override
	public Employee deleteEmployeeById(int id) throws EmployeeNotFoundException {
			return dao.deleteEmployee(id);
	}

	@Override
	public List<Employee> getAllEmployee() throws EmployeeNotFoundException {
			return dao.getAllEmployees();
	}
}
