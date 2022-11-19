package com.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.Category;
import com.springboot.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		Optional<Category> categoryDb = this.categoryRepository.findById(category.getCid());
		if(categoryDb.isPresent()) {
			Category categoryUpdate = categoryDb.get();
			categoryUpdate.setCid(category.getCid());
			categoryUpdate.setCname(category.getCname());
			categoryRepository.save(categoryUpdate);
			return categoryUpdate;
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + category.getCid());
		}	
	}

	@Override
	public List<Category> getAllCategory(int pageNo,int pageSize) {
		Pageable paging=PageRequest.of(pageNo, pageSize) ;
		return categoryRepository.findAll(paging).toList();
	}

	@Override
	public Category getCategoryById(long cId) {
		Optional<Category> categoryDb = this.categoryRepository.findById(cId);
		if(categoryDb.isPresent()) {
			return categoryDb.get();
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + cId);
		}
	}

	@Override
	public void deleteCategory(long cId) {
		Optional<Category> categoryDb = this.categoryRepository.findById(cId);
		if(categoryDb.isPresent()) {
			this.categoryRepository.delete(categoryDb.get());
		}else {
			throw new ResourceNotFoundException("Record not found with id : " + cId);
		}
		
	}

}
