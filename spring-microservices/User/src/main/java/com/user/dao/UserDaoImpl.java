package com.user.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.user.UserNotFoundException;
import com.user.modal.UserPOJO;


@Transactional
@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public UserPOJO getUserById(int id) throws UserNotFoundException {
		LOGGER.info("Get Details For User Id :"+id);
		try
		{
			UserPOJO user=(UserPOJO) sessionFactory.getCurrentSession().createQuery("select user From UserPOJO user where user.userId=:Id and user.status='active'").setParameter("Id", id).uniqueResult();
			if(user!=null)
				return user;
			else 
				throw new UserNotFoundException("User Not Exist For EmpId : "+id);	
		}catch (HibernateException e) {
			LOGGER.error("No User with UserId : "+id+" available in databsae.", e);
			throw e;
		}
	}

	@Override
	public UserPOJO updateUser(UserPOJO user, int id) throws UserNotFoundException {
		LOGGER.info("User Updated For User Id :"+id);
		try
		{
			if(user.getUserId()==id)
			{
				user.setLastModified(new Date());
				sessionFactory.getCurrentSession().update(user);
				return sessionFactory.getCurrentSession().get(UserPOJO.class, id); 
			}
			else {
				throw new UserNotFoundException("User Id not matched with User");
			}
		}
		catch (HibernateException e) {
			LOGGER.error("User Not Updated", e);
			throw e;
		}
	}

	@Override
	public UserPOJO addUser(UserPOJO user) throws UserNotFoundException {
		LOGGER.info("Adding New User");
		try
		{	
			user.setCreatedDate(new Date());
			user.setLastModified(new Date());
			user.setStatus("active");
			user.setRole("user");
			Integer userId= (Integer) sessionFactory.getCurrentSession().save(user);
			if(userId==0)
				throw new UserNotFoundException("User Not inserted");
			return 
					sessionFactory.getCurrentSession().get(UserPOJO.class, userId);
		}
		catch (HibernateException e) {
			LOGGER.error("User Not Inserted", e);
			throw e;
		}
	}

	@Override
	public UserPOJO deleteUser(int id) throws UserNotFoundException {
		LOGGER.info("User deleted for User Id :"+id);
		try
		{
			UserPOJO user=(UserPOJO) sessionFactory.getCurrentSession().get(UserPOJO.class, id);
			if(user==null){
				throw new UserNotFoundException("User Not Exist");
			}
			else{
				user.setLastModified(new Date());
				user.setStatus("inactive");
				sessionFactory.getCurrentSession().update(user);
				return user;
			}
		}
		catch (HibernateException e) {
			LOGGER.error("User not deleted some error in database", e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPOJO> getAllUsers() throws UserNotFoundException {
		LOGGER.info("Getting All User Details");
		try
		{	
			List<UserPOJO> userList=sessionFactory.getCurrentSession().createQuery("From UserPOJO user where user.status ='active'").list();
			if(userList.isEmpty())
				throw new UserNotFoundException("No User Exist");
			else
				return userList;
		}catch (HibernateException e) {
			LOGGER.error("User not selected some error in database", e);
			throw e;
		}
	}
	
}
