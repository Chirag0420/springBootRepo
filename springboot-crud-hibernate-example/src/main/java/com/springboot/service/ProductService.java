package com.springboot.service;

import java.util.List;

import com.springboot.model.Product;

public interface ProductService 
{
	Product createProduct(Product product);

	Product updateProduct(Product product);

	List<Product> getAllProduct();

	Product getProductById(long productId);

	void deleteProduct(long id);
	

	List<Product> getProductsByCategoryId(Iterable<Long> catId);
}
