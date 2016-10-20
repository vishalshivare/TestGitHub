package com.ek.controller;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.ek.modal.UserPOJO;
import com.ek.modal.OrderPOJO;
import com.ek.modal.ProductPOJO;



@RestController
public class EKartController {

	private static final Logger LOGGER = Logger.getLogger(EKartController.class);
	
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value="/login/{id}/{password}",method=RequestMethod.GET,produces="application/JSON")
	public JSONObject validateUser(@PathVariable(name="id") int userId,@PathVariable(name="password") String pass,HttpSession session) throws MalformedURLException, RestClientException, URISyntaxException 
	{
		LOGGER.info("Enterring method validateUser()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9091/users/"+userId);
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<UserPOJO> response=restTemplate.getForEntity(baseURL.toURI(), UserPOJO.class);
		UserPOJO user=response.getBody();
		JSONObject userJson =new JSONObject();
		if(user==null)
		{
			userJson.put("status", "NotExist");
			return userJson;
		}

		else if(user.getPassword().equals(pass))
		{
			userJson.put("userId", user.getUserId());
			userJson.put("userName", user.getFName());
			userJson.put("status", "Success");
			userJson.put("role", user.getRole());
			session.setAttribute("validUser", user);
			return userJson;
		}
		else
			userJson.put("status", "CorrectPassword");
		return userJson;
	}

	@RequestMapping(value="/getProducts",method=RequestMethod.GET,produces="application/JSON")
	public List<ProductPOJO> getAllProducts() throws MalformedURLException, RestClientException, URISyntaxException 
	{
		LOGGER.info("Enterring method getAllProducts()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9092/products");
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<ProductPOJO[]> response= restTemplate.getForEntity(baseURL.toURI(), ProductPOJO[].class);
		List<ProductPOJO> productList=Arrays.asList(response.getBody());
		return productList;	
	}

	@RequestMapping(value="/addToCart/{productId}/{quantity}",method=RequestMethod.POST,produces="application/JSON")
	public String addToCart(@PathVariable(name="productId") int productId,@PathVariable(name="quantity") int quantity,HttpSession session) throws MalformedURLException, RestClientException, URISyntaxException
	{
		LOGGER.info("Enterring method addToCart()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9092/products/"+productId);
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<ProductPOJO> responseProduct=restTemplate.getForEntity(baseURL.toURI(), ProductPOJO.class);
		ProductPOJO product=responseProduct.getBody();
		double amount=quantity*product.getPrice();
		UserPOJO user=(UserPOJO) session.getAttribute("validUser");
		OrderPOJO order=new OrderPOJO();
		order.setProductId(productId);
		order.setQuantity(quantity);
		order.setUserId(user.getUserId());
		order.setOrdered("no");
		order.setOrderDate(new Date());
		order.setAmount(amount);
		baseURL=new URL("http://192.168.50.100:9093/orders");
		restTemplate.postForEntity(baseURL.toURI(),order,OrderPOJO.class);
		return "success";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/showCart",method=RequestMethod.GET,produces="application/JSON")
	public List<JSONObject> showCart(HttpSession session) throws Exception
	{
		LOGGER.info("Enterring method showCart()::EKartController");
		List<JSONObject> allOrder=new ArrayList<>();
		UserPOJO user= (UserPOJO) session.getAttribute("validUser");
		//URL baseURL=new URL("http://192.168.50.100:9093/orders/6");
		URL baseURL=new URL("http://192.168.50.100:9093/orders/"+user.getUserId());
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<OrderPOJO[]> orderResponse = restTemplate.getForEntity(baseURL.toURI(), OrderPOJO[].class);
		List<OrderPOJO> orderList=Arrays.asList(orderResponse.getBody());
		for(OrderPOJO o : orderList)
		{
			JSONObject order =new JSONObject();
			order.put("orderId",o.getOrderId());
			order.put("userId",o.getUserId());
			order.put("orderDate", o.getOrderDate());
			baseURL=new URL("http://192.168.50.100:9092/products/"+o.getProductId());
			ProductPOJO product=(restTemplate.getForEntity(baseURL.toURI(), ProductPOJO.class)).getBody();
			order.put("productId", product.getProductId());
			order.put("productName", product.getPname());
			order.put("productBrand", product.getBrand());
			order.put("productPrice", product.getPrice());
			order.put("quantity", o.getQuantity());
			order.put("amount", o.getAmount());
			allOrder.add(order);
		}
		return allOrder;
	}

	@RequestMapping(value="/orderNow/{orderId}",method=RequestMethod.GET,produces="application/JSON")
	public String orderNow(@PathVariable int orderId) throws MalformedURLException, RestClientException, URISyntaxException
	{
		LOGGER.info("Enterring method orderNow()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9093/orders/"+orderId);
		RestTemplate restTemplate=new RestTemplate();
		restTemplate.postForEntity(baseURL.toURI(), OrderPOJO.class, OrderPOJO.class);
		return "success";	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/showHistory",method=RequestMethod.GET,produces="application/JSON")
	public List<JSONObject> showHistory(HttpSession session) throws Exception
	{
		LOGGER.info("Enterring method showHistory()::EKartController");
		List<JSONObject> allOrder=new ArrayList<>();
		UserPOJO user= (UserPOJO) session.getAttribute("validUser");
		URL baseURL=new URL("http://192.168.50.100:9093/ordersHistory/"+user.getUserId());
		RestTemplate restTemplate=new RestTemplate();
		ResponseEntity<OrderPOJO[]> orderResponse = restTemplate.getForEntity(baseURL.toURI(), OrderPOJO[].class);
		List<OrderPOJO> orderList=Arrays.asList(orderResponse.getBody());
		for(OrderPOJO o : orderList)
		{
			JSONObject order =new JSONObject();
			order.put("orderId",o.getOrderId());
			order.put("userId",o.getUserId());
			order.put("orderDate", o.getOrderDate());
			baseURL=new URL("http://192.168.50.100:9092/products/"+o.getProductId());
			ProductPOJO product=(restTemplate.getForEntity(baseURL.toURI(), ProductPOJO.class)).getBody();
			order.put("productId", product.getProductId());
			order.put("productName", product.getPname());
			order.put("productBrand", product.getBrand());
			order.put("productPrice", product.getPrice());
			order.put("quantity", o.getQuantity());
			order.put("amount", o.getAmount());
			allOrder.add(order);
		}
		return allOrder;
	}

	@RequestMapping(value="/products/{id}",method=RequestMethod.GET,produces="application/JSON")
	public ProductPOJO getProductById(@PathVariable int id) throws MalformedURLException, RestClientException, URISyntaxException
	{
		LOGGER.info("Enterring method getProductById()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9092/products/"+id);
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate.getForEntity(baseURL.toURI(), ProductPOJO.class).getBody();

	}

	@RequestMapping(value="/products/{id}",method=RequestMethod.PUT,consumes="application/JSON",produces="application/JSON")
	public ProductPOJO updateProduct(@RequestBody ProductPOJO product,@PathVariable int id)  throws MalformedURLException, RestClientException, URISyntaxException
	{
		LOGGER.info("Enterring method updateProduct()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9092/products/"+id);
		RestTemplate restTemplate=new RestTemplate();
		restTemplate.put(baseURL.toURI(), product);
		return (restTemplate.getForEntity(baseURL.toURI(), ProductPOJO.class)).getBody();
	}

	@RequestMapping(value="/products",method=RequestMethod.POST,consumes="application/JSON",produces="application/JSON")
	public ProductPOJO addProduct(@RequestBody ProductPOJO product) throws MalformedURLException, RestClientException, URISyntaxException 
	{
		LOGGER.info("Enterring method addProduct()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9092/products");
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate.postForObject(baseURL.toURI(), product, ProductPOJO.class);
	}

	@RequestMapping(value="/products/{id}",method=RequestMethod.DELETE) 
	public ProductPOJO deleteProduct(@PathVariable int id) throws MalformedURLException, RestClientException, URISyntaxException 
	{
		LOGGER.info("Enterring method deleteProduct()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9092/products/"+id);
		RestTemplate restTemplate=new RestTemplate();
		ProductPOJO product=(restTemplate.getForEntity(baseURL.toURI(), ProductPOJO.class)).getBody();
		restTemplate.delete(baseURL.toURI());
		return product;
	}

	@RequestMapping(value="/products",method=RequestMethod.GET,produces="application/JSON")
	public List<ProductPOJO> getAllProduct() throws MalformedURLException, RestClientException, URISyntaxException
	{
		LOGGER.info("Enterring method getAllProduct()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9092/products");
		RestTemplate restTemplate=new RestTemplate();
		return Arrays.asList((restTemplate.getForEntity(baseURL.toURI(), ProductPOJO[].class)).getBody());
	}

	@RequestMapping(value="/users/{Id}",method=RequestMethod.GET,produces="application/JSON")
	public UserPOJO getUserById(@PathVariable int Id) throws MalformedURLException, RestClientException, URISyntaxException 
	{
		LOGGER.info("Enterring method getUserById()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9091/users/"+Id);
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate.getForEntity(baseURL.toURI(), UserPOJO.class).getBody();

	}

	@RequestMapping(value="/users/{userId}",method=RequestMethod.DELETE)  
	public UserPOJO deleteUser(@PathVariable int userId) throws MalformedURLException, RestClientException, URISyntaxException 
	{
		LOGGER.info("Enterring method deleteUser()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9091/users/"+userId);
		RestTemplate restTemplate=new RestTemplate();
		UserPOJO user=(restTemplate.getForEntity(baseURL.toURI(), UserPOJO.class)).getBody();
		restTemplate.delete(baseURL.toURI());
		return user;
	}
	
	@RequestMapping(value="/users",method=RequestMethod.POST,consumes="application/JSON",produces="application/JSON")
	public UserPOJO addUser(@RequestBody UserPOJO user) throws MalformedURLException, RestClientException, URISyntaxException
	{
		LOGGER.info("Enterring method addUser()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9091/users");
		RestTemplate restTemplate=new RestTemplate();
		return restTemplate.postForObject(baseURL.toURI(), user, UserPOJO.class);
	}
	
	@RequestMapping(value="/users/{userId}",method=RequestMethod.PUT,consumes="application/JSON",produces="application/JSON")
	public UserPOJO updateUser(@RequestBody UserPOJO user,@PathVariable int userId) throws MalformedURLException, RestClientException, URISyntaxException
	{
		LOGGER.info("Enterring method updateUser()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9091/users/"+userId);
		RestTemplate restTemplate=new RestTemplate();
		restTemplate.put(baseURL.toURI(), user);
		return restTemplate.getForEntity(baseURL.toURI(), UserPOJO.class).getBody();
	}

	@RequestMapping(value="/users",method=RequestMethod.GET,produces="application/JSON")
	public List<UserPOJO> getAllUser() throws MalformedURLException, RestClientException, URISyntaxException 
	{
		LOGGER.info("Enterring method getAllUser()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9091/users");
		RestTemplate restTemplate=new RestTemplate();
		return Arrays.asList((restTemplate.getForEntity(baseURL.toURI(), UserPOJO[].class)).getBody());
	}
	
	@RequestMapping(value="/orders/{id}",method=RequestMethod.DELETE) 
	public OrderPOJO deleteOrder(@PathVariable int id) throws MalformedURLException, RestClientException, URISyntaxException 
	{
		LOGGER.info("Enterring method deleteOrder()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9093/orders/"+id);
		RestTemplate restTemplate=new RestTemplate();
		OrderPOJO order=(restTemplate.getForEntity(baseURL.toURI(), OrderPOJO.class)).getBody();
		restTemplate.delete(baseURL.toURI());
		return order;
		
	}

	@RequestMapping(value="/orders",method=RequestMethod.GET,produces="application/JSON")
	public List<OrderPOJO> getAllOrder() throws MalformedURLException, RestClientException, URISyntaxException 
	{
		LOGGER.info("Enterring method getAllOrder()::EKartController");
		URL baseURL=new URL("http://192.168.50.100:9093/orders");
		RestTemplate restTemplate=new RestTemplate();
		return Arrays.asList((restTemplate.getForEntity(baseURL.toURI(), OrderPOJO[].class)).getBody());
	}
}
