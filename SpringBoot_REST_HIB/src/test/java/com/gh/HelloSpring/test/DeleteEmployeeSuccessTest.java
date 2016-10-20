package com.gh.HelloSpring.test;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.gh.HelloSpring.dao.EmployeeDAOImpl;
import com.gh.HelloSpring.modal.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteEmployeeSuccessTest {

	@LocalServerPort
	private int port;
	private URI baseURL;
	private RestTemplate restTemplate;
	EmployeeDAOImpl dao;
	Employee emp;
	
	@Before
	public void setUP() throws Exception
	{
		restTemplate=new RestTemplate();
		dao=new EmployeeDAOImpl();
		emp=new Employee();
		emp.setEmpid(0);
		emp.setFname("fname");
		emp.setLname("lname");
		emp.setDob(new Date(19900929));
		emp.setAddress("address");
		emp.setMobile("9755104644");
		restTemplate=new RestTemplate();
		this.baseURL=new URI("http://localhost:"+port+"/emp");
		restTemplate.postForEntity(baseURL,emp,Employee.class);
		
	}
	
	@After
	public void tearDown()
	{
		restTemplate=null;
		emp=null;
	}
	
	@Test
	public void deleteEmployeeSuccessTest() throws Exception
	{
		this.baseURL=new URI("http://localhost:"+port+"/emp/0");
		restTemplate.delete(baseURL);
		//ResponseEntity<Employee> response=restTemplate.getForEntity(baseURL, Employee.class);
		/*Employee emp=response.getBody();
		assertEquals(0, emp.getEmpid());*/
		
	}
}
