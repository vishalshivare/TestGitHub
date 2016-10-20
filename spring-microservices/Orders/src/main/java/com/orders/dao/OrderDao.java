package com.orders.dao;

import java.util.List;

import com.orders.OrderNotFoundException;
import com.orders.modal.OrderPOJO;

public interface OrderDao {

	List<OrderPOJO> getOrderById(int id) throws OrderNotFoundException;

	OrderPOJO updateOrder(OrderPOJO order, int id) throws OrderNotFoundException;

	OrderPOJO addOrder(OrderPOJO order) throws OrderNotFoundException;

	OrderPOJO deleteOrder(int id) throws OrderNotFoundException;

	List<OrderPOJO> getAllOrders() throws OrderNotFoundException;

	OrderPOJO placeOrderByOrderId(int id) throws OrderNotFoundException;

	List<OrderPOJO> getOrderHistoryById(int id) throws OrderNotFoundException;
}
