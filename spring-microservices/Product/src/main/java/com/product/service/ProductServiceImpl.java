package com.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.ProductNotFoundException;
import com.product.dao.ProductDao;
import com.product.modal.ProductPOJO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;
	
	@Override
	public ProductPOJO getProduct(int id) throws ProductNotFoundException {
			return dao.getProductById(id);
	}

	@Override
	public ProductPOJO updateProductById(ProductPOJO product,int id) throws ProductNotFoundException {
		return dao.updateProduct(product,id);
	}

	@Override
	public ProductPOJO stroreProduct(ProductPOJO product) throws ProductNotFoundException {
			return dao.addProduct(product);
	}

	@Override
	public ProductPOJO deleteProductById(int id) throws ProductNotFoundException {
			return dao.deleteProduct(id);
	}

	@Override
	public List<ProductPOJO> getAllProduct() throws ProductNotFoundException {
			return dao.getAllProducts();
	}
	
}
