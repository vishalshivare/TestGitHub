package com.product.dao;

import java.util.List;

import com.product.ProductNotFoundException;
import com.product.modal.ProductPOJO;

public interface ProductDao {

	ProductPOJO getProductById(int id) throws ProductNotFoundException;

	ProductPOJO updateProduct(ProductPOJO product, int id) throws ProductNotFoundException;

	ProductPOJO addProduct(ProductPOJO product) throws ProductNotFoundException;

	ProductPOJO deleteProduct(int id) throws ProductNotFoundException;

	List<ProductPOJO> getAllProducts() throws ProductNotFoundException;

}
