package com.gh.HelloSpring.test;

import static org.junit.Assert.assertEquals;

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
import com.gh.HelloSpring.modal.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetEmployeeTest {

	@LocalServerPort
	private int port;
	private URL baseURL;
	RestTemplate restTemplate;
	
	@Before
	public void setUp() throws Exception
	{
		System.out.println("before Test");
		restTemplate=new RestTemplate();
		this.baseURL=new URL("http://localhost:"+ port +"/emp/1");
	}
	
	@After
	public void tearDown() {
		System.out.println("after test");
		restTemplate=null;

	}
	
	@Test
	public void getEmployeeByIdSuccessTest() throws Exception
	{
		System.out.println("getEmployeeByIdSuccessTest");
		ResponseEntity<Employee> response=restTemplate.getForEntity(baseURL.toURI(), Employee.class);
		Employee emp=response.getBody();
		assertEquals(1, emp.getEmpid());
		assertEquals("test", emp.getFname());
		assertEquals("Shivare", emp.getLname());
		assertEquals("1992-09-29", emp.getDob().toString());
		assertEquals("Pune", emp.getAddress());
		assertEquals("9755104644", emp.getMobile());
		System.out.println("success test Case run ");
	}
	
	@Test
	public void getAllEmployeeSuccessTest() throws Exception
	{
		System.out.println("getAllEmployeeSuccessTest");
		this.baseURL=new URL("http://localhost:"+ port +"/emps");
		ResponseEntity<Employee[]> response=restTemplate.getForEntity(baseURL.toURI(), Employee[].class);
		Employee[] emps=response.getBody();
		for(Employee e:emps)
		{
			System.out.println(e.getFname()+" "+e.getLname());
		}
	}
	
}
