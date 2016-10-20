package com.product.service;

import java.util.List;
import com.product.ProductNotFoundException;
import com.product.modal.ProductPOJO;

public interface ProductService {

	ProductPOJO getProduct(int id) throws ProductNotFoundException;

	ProductPOJO updateProductById(ProductPOJO product, int id) throws ProductNotFoundException;

	ProductPOJO stroreProduct(ProductPOJO product) throws ProductNotFoundException;

	ProductPOJO deleteProductById(int id) throws ProductNotFoundException;

	List<ProductPOJO> getAllProduct() throws ProductNotFoundException;

}
