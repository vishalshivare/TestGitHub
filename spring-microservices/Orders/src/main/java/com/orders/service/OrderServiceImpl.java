package com.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.OrderNotFoundException;
import com.orders.dao.OrderDao;
import com.orders.modal.OrderPOJO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;
	
	@Override
	public List<OrderPOJO> getOrder(int id) throws OrderNotFoundException {
			return dao.getOrderById(id);
	}

	@Override
	public OrderPOJO updateOrderById(OrderPOJO order,int id) throws OrderNotFoundException {
		return dao.updateOrder(order,id);
	}

	@Override
	public OrderPOJO stroreOrder(OrderPOJO order) throws OrderNotFoundException {
			return dao.addOrder(order);
	}

	@Override
	public OrderPOJO deleteOrderById(int id) throws OrderNotFoundException {
			return dao.deleteOrder(id);
	}

	@Override
	public List<OrderPOJO> getAllOrder() throws OrderNotFoundException {
			return dao.getAllOrders();
	}

	@Override
	public OrderPOJO placeOrderById(int id) throws OrderNotFoundException {
		return dao.placeOrderByOrderId(id);
	}

	@Override
	public List<OrderPOJO> getOrderHistory(int id) throws OrderNotFoundException {
		return dao.getOrderHistoryById(id);
	}

	

}
