package com.product.controller;

import java.sql.SQLException;
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

import com.product.ProductNotFoundException;
import com.product.modal.ProductPOJO;
import com.product.service.ProductService;
@RestController
public class ProductController {

	@Autowired
	private ProductService service;
	
	private static final Logger LOGGER = Logger.getLogger(ProductController.class);
	
	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/products/{id}",method=RequestMethod.GET,produces="application/JSON")
	public ProductPOJO getProductById(@PathVariable int id) throws ProductNotFoundException, SQLException
	{
		LOGGER.info("Enterring method getProductById()::RestController");
		return service.getProduct(id);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/products/{id}",method=RequestMethod.PUT,consumes="application/JSON",produces="application/JSON")
	public ProductPOJO updateProduct(@Valid @RequestBody ProductPOJO product,@PathVariable int id) throws ProductNotFoundException, SQLException 
	{
		LOGGER.info("Enterring method updateProduct()::RestController");
		return service.updateProductById(product,id);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/products",method=RequestMethod.POST,consumes="application/JSON",produces="application/JSON")
	public ProductPOJO addProduct(@Valid @RequestBody ProductPOJO product) throws ProductNotFoundException, SQLException
	{
		LOGGER.info("Enterring method addProduct()::RestController");
		return service.stroreProduct(product);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/products/{id}",method=RequestMethod.DELETE) 
	public ProductPOJO deleteProduct(@PathVariable int id) throws ProductNotFoundException, SQLException
	{
		LOGGER.info("Enterring method deleteProduct()::RestController");
		return service.deleteProductById(id);
	}

	@CrossOrigin(origins="http://192.168.50.100:9094")
	@RequestMapping(value="/products",method=RequestMethod.GET,produces="application/JSON")
	public List<ProductPOJO> getAllProduct() throws ProductNotFoundException, SQLException
	{
		LOGGER.info("Enterring method getAllProduct()::RestController");
		return service.getAllProduct();
	}
	
}
