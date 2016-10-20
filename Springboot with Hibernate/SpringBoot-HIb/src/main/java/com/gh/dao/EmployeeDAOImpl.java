package com.gh.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.gh.modal.Employee;
import com.gh.utils.EmployeeNotFoundException;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
		LOGGER.info("Get Details For Emp Id :"+id);
		try
		{

			Employee employee=(Employee) sessionFactory.getCurrentSession().createQuery("select emp From Employee emp where emp.empid=:Id and emp.status='active'").setParameter("Id", id).uniqueResult();
			if(employee!=null)
				return employee;
			else 
				throw new EmployeeNotFoundException("Employee Not Exist For EmpId : "+id);	
		}catch (HibernateException e) {
			LOGGER.error("No Employee with EmpID : "+id+" available in databsae.", e);
			throw e;
		}
	}

	@Override
	public Employee updateEmployee(Employee emp,int id) throws EmployeeNotFoundException {
		LOGGER.info("Employee Updated For Emp Id :"+id);
		try
		{
			if(emp.getEmpid()==id)
			{
				emp.setLastModified(new Date());
				sessionFactory.getCurrentSession().update(emp);
				return (Employee) sessionFactory.getCurrentSession().get(Employee.class, id); 
			}
			else {
				throw new EmployeeNotFoundException("Emp Id not matched with Employee");
			}
		}
		catch (HibernateException e) {
			LOGGER.error("Employee Not Updated", e);
			throw e;
		}
	}

	@Override
	public Employee addEmployee(Employee emp) throws EmployeeNotFoundException {
		try
		{	
			emp.setCreatedDate(new Date());
			emp.setLastModified(new Date());
			emp.setStatus("active");
			Integer empId= (Integer) sessionFactory.getCurrentSession().save(emp);
			if(empId==0)
				throw new EmployeeNotFoundException("Employee Not inserted");
			return 
					sessionFactory.getCurrentSession().get(Employee.class, empId);
		}
		catch (HibernateException e) {
			LOGGER.error("Employee Not Inserted", e);
			throw e;
		}
	}

	@Override
	public Employee deleteEmployee(int id) throws EmployeeNotFoundException {
		LOGGER.info("Employee deleted for Emp Id :"+id);
		try
		{
			Employee employee=(Employee) sessionFactory.getCurrentSession().get(Employee.class, id);
			if(employee==null){
				throw new EmployeeNotFoundException("Employee Not Exist");
			}
			else{
				employee.setLastModified(new Date());
				employee.setStatus("inactive");
				sessionFactory.getCurrentSession().update(employee);
				return employee;
			}
		}
		catch (HibernateException e) {
			LOGGER.error("Employee not deleted some error in database", e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
		try
		{	
			List<Employee> empList=sessionFactory.getCurrentSession().createQuery("From Employee emp where emp.status ='active'").list();
			if(empList.isEmpty())
				throw new EmployeeNotFoundException("No employee Exist");
			else
				return empList;
		}catch (HibernateException e) {
			LOGGER.error("Employee not selected some error in database", e);
			throw e;
		}
	}
}
