package com.orders.controller;

import java.util.List;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.orders.OrderNotFoundException;
import com.orders.modal.OrderPOJO;
import com.orders.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService service;
	
	private static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	/*@RequestMapping(value="/orders/{id}",method=RequestMethod.GET,produces="application/JSON")
	public OrderPOJO getOrderById(@PathVariable int id) throws OrderNotFoundException
	{
		LOGGER.info("Enterring method getOrderById()::RestController");
		return service.getOrder(id);
	}*/
	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/orders/{id}",method=RequestMethod.GET,produces="application/JSON")
	public List<OrderPOJO> getOrderByUserId(@PathVariable int id) throws OrderNotFoundException
	{
		LOGGER.info("Enterring method getOrderByUserId()::RestController");
		return service.getOrder(id);
	}
	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/orders/{id}",method=RequestMethod.POST,produces="application/JSON")
	public OrderPOJO placeOrderById(@PathVariable int id) throws OrderNotFoundException
	{
		LOGGER.info("Enterring method placeOrderById()::RestController");
		return service.placeOrderById(id);
	}
	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/ordersHistory/{id}",method=RequestMethod.GET,produces="application/JSON")
	public List<OrderPOJO> getOrderHistoryByUserId(@PathVariable int id) throws OrderNotFoundException
	{
		LOGGER.info("Enterring method getOrderHistoryByUserId()::RestController");
		return service.getOrderHistory(id);
	}
	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/orders/{id}",method=RequestMethod.PUT,consumes="application/JSON",produces="application/JSON")
	public OrderPOJO updateOrder(@Valid @RequestBody OrderPOJO order,@PathVariable int id) throws OrderNotFoundException 
	{
		LOGGER.info("Enterring method updateOrder()::RestController");
		return service.updateOrderById(order,id);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/orders",method=RequestMethod.POST,consumes="application/JSON",produces="application/JSON")
	public OrderPOJO addOrder(@Valid @RequestBody OrderPOJO order) throws OrderNotFoundException
	{
		LOGGER.info("Enterring method addOrder()::RestController");
		return service.stroreOrder(order);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/orders/{id}",method=RequestMethod.DELETE) 
	public OrderPOJO deleteOrder(@PathVariable int id) throws OrderNotFoundException
	{
		LOGGER.info("Enterring method deleteOrder()::RestController");
		return service.deleteOrderById(id);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/orders",method=RequestMethod.GET,produces="application/JSON")
	public List<OrderPOJO> getAllOrder() throws OrderNotFoundException
	{
		LOGGER.info("Enterring method getAllOrder()::RestController");
		return service.getAllOrder();
	}
}
