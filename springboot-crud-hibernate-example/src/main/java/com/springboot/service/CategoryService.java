package com.springboot.service;

import java.util.List;

import com.springboot.model.Category;

public interface CategoryService 
{
	Category createCategory(Category category);

	Category updateCategory(Category category);

	List<Category> getAllCategory();

	void deleteCategory(long cId);

	Category getCategoryById(long cId);
}
