package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Product;
import com.springboot.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public Product createProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> productDb = this.productRepository.findById(product.getId());
		
		if(productDb.isPresent()) {
			Product productUpdate = productDb.get();
			productUpdate.setId(product.getId());
			productUpdate.setName(product.getName());
			productUpdate.setDescription(product.getDescription());
			productRepository.save(productUpdate);
			return productUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + product.getId());
		}		
	}

	@Override
	public List<Product> getAllProduct(int pageNo,int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		return this.productRepository.findAll(paging).toList();
	}

	@Override
	public Product getProductById(long productId) {
		
		Optional<Product> productDb = this.productRepository.findById(productId);
		
		if(productDb.isPresent()) {
			return productDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
	}

	@Override
	public void deleteProduct(long productId) {
		Optional<Product> productDb = this.productRepository.findById(productId);
		
		if(productDb.isPresent()) {
			this.productRepository.delete(productDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + productId);
		}
		
	}


}

