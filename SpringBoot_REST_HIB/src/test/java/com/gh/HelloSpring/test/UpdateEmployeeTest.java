package com.gh.HelloSpring.test;

import java.net.URL;

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
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateEmployeeTest {

	@LocalServerPort
	private int port;
	private URL baseURL;
	RestTemplate restTemplate;
	EmployeeDAOImpl dao;
	Employee emp;
	
	@Before
	public void setUp() throws Exception
	{
		emp=new Employee();
		dao=new EmployeeDAOImpl();
		restTemplate=new RestTemplate();
		System.out.println("before Test");
		emp=dao.getEmployeeById(2);
		emp.setAddress("India");
		this.baseURL=new URL("http://localhost:"+ port +"/emp/1");
	}
	
	@After
	public void tearDown() throws Exception {
		/*System.out.println("after test");
		emp.setAddress("Pune");
		restTemplate.put(baseURL.toURI(), emp);
		restTemplate=null;
		dao=null;*/

	}
	
	@Test
	public void updateEmployeeSuccessTest() throws Exception
	{
		/*System.out.println("getEmployeeByIdSuccessTest");
		restTemplate.put(baseURL.toURI(), emp);
		ResponseEntity<Employee> response=restTemplate.getForEntity(baseURL.toURI(), Employee.class);
		Employee empTest=response.getBody();
		assertEquals(emp.getEmpid(), empTest.getEmpid());
		assertEquals(emp.getFname(), empTest.getFname());
		assertEquals(emp.getLname(), empTest.getLname());
		assertEquals(emp.getDob().toString(), empTest.getDob().toString());
		assertEquals(emp.getAddress(), empTest.getAddress());
		assertEquals(emp.getMobile(), empTest.getMobile());
		System.out.println("success test Case run ");*/
	}
}
