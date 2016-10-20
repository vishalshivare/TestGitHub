package com.orders.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.orders.OrderNotFoundException;
import com.orders.modal.OrderPOJO;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

	private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<OrderPOJO> getOrderById(int id) throws OrderNotFoundException {
		LOGGER.info("Get Details For Order Id :"+id);
		try
		{
			List<OrderPOJO> order=sessionFactory.getCurrentSession().createQuery("select order From OrderPOJO order where order.userId=:Id and order.ordered='no' and order.status='active'").setParameter("Id", id).list();
			if(order!=null)
				return order;
			else 
				throw new OrderNotFoundException("Order Not Exist For EmpId : "+id);	
		}catch (HibernateException e) {
			LOGGER.error("No Order with UserId : "+id+" available in databsae.", e);
			throw e;
		}
	}

	@Override
	public OrderPOJO updateOrder(OrderPOJO order, int id) throws OrderNotFoundException {
		LOGGER.info("Order Updated For Order Id :"+id);
		try
		{
			if(order.getOrderId()==id)
			{
				order.setLastModified(new Date());
				sessionFactory.getCurrentSession().update(order);
				return sessionFactory.getCurrentSession().get(OrderPOJO.class, id); 
			}
			else {
				throw new OrderNotFoundException("Order Id not matched with Order");
			}
		}
		catch (HibernateException e) {
			LOGGER.error("Order Not Updated", e);
			throw e;
		}
	}

	@Override
	public OrderPOJO addOrder(OrderPOJO order) throws OrderNotFoundException {
		LOGGER.info("Adding New Order");
		try
		{	
			order.setCreatedDate(new Date());
			order.setLastModified(new Date());
			order.setStatus("active");
			Integer orderId= (Integer) sessionFactory.getCurrentSession().save(order);
			if(orderId==0)
				throw new OrderNotFoundException("Order Not inserted");
			return 
					sessionFactory.getCurrentSession().get(OrderPOJO.class, orderId);
		}
		catch (HibernateException e) {
			LOGGER.error("Order Not Inserted", e);
			throw e;
		}
	}

	@Override
	public OrderPOJO deleteOrder(int id) throws OrderNotFoundException {
		LOGGER.info("Order deleted for Order Id :"+id);
		try
		{
			OrderPOJO order=(OrderPOJO) sessionFactory.getCurrentSession().get(OrderPOJO.class, id);
			if(order==null){
				throw new OrderNotFoundException("Order Not Exist");
			}
			else{
				order.setLastModified(new Date());
				order.setStatus("inactive");
				sessionFactory.getCurrentSession().update(order);
				return order;
			}
		}
		catch (HibernateException e) {
			LOGGER.error("Order not deleted some error in database", e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPOJO> getAllOrders() throws OrderNotFoundException {
		LOGGER.info("Getting All Order Details");
		try
		{	
			List<OrderPOJO> OrderList=sessionFactory.getCurrentSession().createQuery("From OrderPOJO order where order.status ='active'").list();
			if(OrderList.isEmpty())
				throw new OrderNotFoundException("No Order Exist");
			else
				return OrderList;
		}catch (HibernateException e) {
			LOGGER.error("Order not selected some error in database", e);
			throw e;
		}
	}

	@Override
	public OrderPOJO placeOrderByOrderId(int id) throws OrderNotFoundException {
		LOGGER.info("placing ooder by order Id");
		try
		{	
			OrderPOJO order=sessionFactory.getCurrentSession().get(OrderPOJO.class, id);
			order.setOrdered("yes");
			sessionFactory.getCurrentSession().update(order);
			order=sessionFactory.getCurrentSession().get(OrderPOJO.class, id);
			return order;
		}catch (HibernateException e) {
			LOGGER.error("Order not selected some error in database", e);
			throw e;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderPOJO> getOrderHistoryById(int id) throws OrderNotFoundException {
		LOGGER.info("Get Details For Order Id :"+id);
		try
		{
			List<OrderPOJO> order=sessionFactory.getCurrentSession().createQuery("select order From OrderPOJO order where order.userId=:Id and order.ordered='yes' and order.status='active'").setParameter("Id", id).list();
			if(order!=null)
				return order;
			else 
				throw new OrderNotFoundException("Order Not Exist For EmpId : "+id);	
		}catch (HibernateException e) {
			LOGGER.error("No Order with UserId : "+id+" available in databsae.", e);
			throw e;
		}
	}

}
