package com.springboot.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.springboot.model.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {

}
