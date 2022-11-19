package com.springboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.model.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
