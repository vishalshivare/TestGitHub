package com.orders.service;

import java.util.List;

import com.orders.OrderNotFoundException;
import com.orders.modal.OrderPOJO;

public interface OrderService {

	List<OrderPOJO> getOrder(int id) throws OrderNotFoundException;

	OrderPOJO updateOrderById(OrderPOJO order, int id) throws OrderNotFoundException;

	OrderPOJO stroreOrder(OrderPOJO order) throws OrderNotFoundException;

	OrderPOJO deleteOrderById(int id) throws OrderNotFoundException;

	List<OrderPOJO> getAllOrder() throws OrderNotFoundException;

	OrderPOJO placeOrderById(int id) throws OrderNotFoundException;

	List<OrderPOJO> getOrderHistory(int id) throws OrderNotFoundException;

}
