package com.gh.HelloSpring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gh.HelloSpring.modal.Employee;
import com.gh.HelloSpring.utils.EmployeeNotFoundException;


@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	@Autowired
	DataSource dataSource;

	@Override
	public Employee getEmployeeById(int id) throws EmployeeNotFoundException, SQLException {
		LOGGER.info("Get Details For Emp Id :"+id);
		try(Connection connection=dataSource.getConnection();
				PreparedStatement preStatement=connection.prepareStatement("select * from emp where empid=?");)
		{
			Employee employee=new Employee();
			preStatement.setInt(1, id);
			ResultSet resultSet=preStatement.executeQuery();
			if(resultSet.next())
			{
				employee.setEmpid(resultSet.getInt(1));
				employee.setFname(resultSet.getString(2));
				employee.setLname(resultSet.getString(3));
				employee.setDob(resultSet.getDate(4));
				employee.setAddress(resultSet.getString(5));
				employee.setMobile(resultSet.getString(6));
				return employee;
			}else{
				throw new EmployeeNotFoundException("Employee Not Exist in Database");
			}
		}catch (SQLException e) {
			LOGGER.error("No Employee with EmpID : "+id+" available in databsae.", e);
			throw e;
		}
	}

	@Override
	public Employee updateEmployee(Employee emp,int id) throws EmployeeNotFoundException, SQLException {
		LOGGER.info("Employee Updated For Emp Id :"+id);
		try(Connection connection=dataSource.getConnection();
				PreparedStatement preStatement=connection.prepareStatement("update emp set fname=?,lname=?,dob=?,address=?,mobile=? where empid=?");)
		{
			if(id==emp.getEmpid())
			{
				preStatement.setString(1, emp.getFname());
				preStatement.setString(2, emp.getLname());
				preStatement.setDate(3, emp.getDob());
				preStatement.setString(4, emp.getAddress());
				preStatement.setString(5, emp.getMobile());
				preStatement.setInt(6,emp.getEmpid());
				int count= preStatement.executeUpdate();
				if(count<1)
					throw new EmployeeNotFoundException("Error while updating");
				else
					return emp;
			}
			else {
				throw new EmployeeNotFoundException("Emp Id not matched with Employee");
			}
		}
		catch (SQLException e) {
			LOGGER.error("Employee Not Updated", e);
			throw e;
		}
	}

	@Override
	public Employee addEmployee(Employee emp) throws EmployeeNotFoundException, SQLException {
		try(Connection connection=dataSource.getConnection();
				PreparedStatement pstForEmpId=connection.prepareStatement("select getEmployeeId.nextval from dual");
				PreparedStatement preStatement=connection.prepareStatement("insert into emp values(?,?,?,?,?,?)");)
		{
			int EmpId=0;
			ResultSet resultSet=pstForEmpId.executeQuery();
			if(resultSet.next())
			{
				EmpId=resultSet.getInt(1);
			}
			preStatement.setInt(1,EmpId);
			preStatement.setString(2, emp.getFname());
			preStatement.setString(3, emp.getLname());
			preStatement.setString(5, emp.getAddress());
			preStatement.setString(6, emp.getMobile());
			preStatement.setDate(4, emp.getDob());
			preStatement.executeQuery();
			return emp;
		}
		catch (SQLIntegrityConstraintViolationException e) {
			LOGGER.error("Employee Already Exist", e);
			throw e;
		}
		catch (SQLException e) {
			LOGGER.error("Employee Not Inserted", e);
			throw e;
		}
	}

	@Override
	public Employee deleteEmployee(int id) throws EmployeeNotFoundException, SQLException {
		LOGGER.info("Employee deleted for Emp Id :"+id);
		try(Connection connection=dataSource.getConnection();
				PreparedStatement preStatement=connection.prepareStatement("delete from emp where empid=?");)
		{
			Employee employee=this.getEmployeeById(id);
			if(employee==null)
			{
				throw new EmployeeNotFoundException("Employee Not Exist");
			}
			preStatement.setInt(1, id);
			int isUpdate=preStatement.executeUpdate();
			if(isUpdate<1)
				throw new EmployeeNotFoundException("Error while updating");
			else
				return employee;
		}
		catch (SQLException e) {
			LOGGER.error("Employee not deleted some error in database", e);
			throw e;
		}
	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeNotFoundException, SQLException {
		List<Employee> empList=new ArrayList<>();
		try(Connection connection=dataSource.getConnection();
				PreparedStatement preStatement=connection.prepareStatement("select empid,fname,lname,dob,address,mobile from emp");)
		{
			ResultSet resultSet=preStatement.executeQuery(); 
			if(resultSet==null)
				throw new EmployeeNotFoundException("No Employee Found In Database");
			while(resultSet.next())
			{
				Employee emp=new Employee();
				emp.setEmpid(resultSet.getInt(1));
				emp.setFname(resultSet.getString(2));
				emp.setLname(resultSet.getString(3));
				emp.setDob(resultSet.getDate(4));
				emp.setAddress(resultSet.getString(5));
				emp.setMobile(resultSet.getString(6));
				empList.add(emp);
			}
			return empList;
		}catch (SQLException e) {
			LOGGER.error("Employee not selected some error in database", e);
			throw e;
		}
	}
}
