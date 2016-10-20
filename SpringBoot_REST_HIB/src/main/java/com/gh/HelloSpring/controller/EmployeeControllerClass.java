package com.gh.HelloSpring.controller;

import java.sql.SQLException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gh.HelloSpring.modal.Employee;
import com.gh.HelloSpring.service.EmployeeService;
import com.gh.HelloSpring.utils.EmployeeNotFoundException;

@RestController
@RequestMapping
public class EmployeeControllerClass {

	@Autowired
	private EmployeeService service;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value="/employees/{id}",method=RequestMethod.GET,produces="application/JSON")
	public Employee getEmployeeById(@PathVariable int id) throws EmployeeNotFoundException, SQLException
	{
		logger.info("Enterring method getEmployeeById()::RestController");
		return service.getEmployee(id);
	}

	@RequestMapping(value="/employees/{id}",method=RequestMethod.PUT,consumes="application/JSON",produces="application/JSON")
	public Employee updateEmployee(@Valid @RequestBody Employee emp,@PathVariable int id) throws EmployeeNotFoundException, SQLException 
	{
		logger.info("Enterring method updateEmployee()::RestController");
		return service.updateEmployeeById(emp,id);
	}

	@RequestMapping(value="/employees",method=RequestMethod.POST,consumes="application/JSON",produces="application/JSON")
	public Employee addEmployee(@Valid @RequestBody Employee emp) throws EmployeeNotFoundException, SQLException
	{
		logger.info("Enterring method setEmployee()::RestController");
		return service.stroreEmployee(emp);
	}

	@RequestMapping(value="/employees/{id}",method=RequestMethod.DELETE) 
	public Employee deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException, SQLException
	{
		logger.info("Enterring method deleteEmployee()::RestController");
		return service.deleteEmployeeById(id);
	}

	@RequestMapping(value="/employees",method=RequestMethod.GET,produces="application/JSON")
	public List<Employee> getAllEmployee() throws EmployeeNotFoundException, SQLException
	{
		logger.info("Enterring method getAllEmployee()::RestController");
		return service.getAllEmployee();
	}
}