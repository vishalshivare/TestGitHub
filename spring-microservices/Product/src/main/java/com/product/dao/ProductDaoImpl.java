package com.product.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.product.ProductNotFoundException;
import com.product.modal.ProductPOJO;


@Transactional
@Repository
public class ProductDaoImpl implements ProductDao {

	
	private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ProductPOJO getProductById(int id) throws ProductNotFoundException {
		LOGGER.info("Get Details For Product Id :"+id);
		try
		{
			/*ProductPOJO product=(ProductPOJO) sessionFactory.getCurrentSession().get(ProductPOJO.class, id);*/
			ProductPOJO product=(ProductPOJO) sessionFactory.getCurrentSession().createQuery("select product From ProductPOJO product where product.productId=:Id and product.status='active'").setParameter("Id", id).uniqueResult();
			if(product!=null)
				return product;
			else 
				throw new ProductNotFoundException("Product Not Exist For productId : "+id);	
		}catch (HibernateException e) {
			LOGGER.error("No Product with productId : "+id+" available in databsae.", e);
			throw e;
		}
	}

	@Override
	public ProductPOJO updateProduct(ProductPOJO product, int id) throws ProductNotFoundException {
		LOGGER.info("Product Updated For Product Id :"+id);
		try
		{
			if(product.getProductId()==id)
			{
				product.setLastModified(new Date());
				sessionFactory.getCurrentSession().update(product);
				return sessionFactory.getCurrentSession().get(ProductPOJO.class, id); 
			}
			else {
				throw new ProductNotFoundException("Product Id not matched with Product");
			}
		}
		catch (HibernateException e) {
			LOGGER.error("Product Not Updated", e);
			throw e;
		}
	}

	@Override
	public ProductPOJO addProduct(ProductPOJO product) throws ProductNotFoundException {
		LOGGER.info("Adding New Product");
		try
		{	
			product.setCreatedDate(new Date());
			product.setLastModified(new Date());
			product.setStatus("active");
			Integer productId= (Integer) sessionFactory.getCurrentSession().save(product);
			if(productId==0)
				throw new ProductNotFoundException("Product Not inserted");
			return 
					sessionFactory.getCurrentSession().get(ProductPOJO.class, productId);
		}
		catch (HibernateException e) {
			LOGGER.error("Product Not Inserted", e);
			throw e;
		}
	}

	@Override
	public ProductPOJO deleteProduct(int id) throws ProductNotFoundException {
		LOGGER.info("Product deleted for Product Id :"+id);
		try
		{
			ProductPOJO product=(ProductPOJO) sessionFactory.getCurrentSession().get(ProductPOJO.class, id);
			if(product==null){
				throw new ProductNotFoundException("Product Not Exist");
			}
			else{
				product.setLastModified(new Date());
				product.setStatus("inactive");
				sessionFactory.getCurrentSession().update(product);
				return product;
			}
		}
		catch (HibernateException e) {
			LOGGER.error("Product not deleted some error in database", e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductPOJO> getAllProducts() throws ProductNotFoundException {
		LOGGER.info("Getting All Product Details");
		try
		{	
			List<ProductPOJO> ProductList=sessionFactory.getCurrentSession().createQuery("From ProductPOJO product where product.status ='active'").list();
			if(ProductList.isEmpty())
				throw new ProductNotFoundException("No Product Exist");
			else
				return ProductList;
		}catch (HibernateException e) {
			LOGGER.error("Product not selected some error in database", e);
			throw e;
		}
	}

}
