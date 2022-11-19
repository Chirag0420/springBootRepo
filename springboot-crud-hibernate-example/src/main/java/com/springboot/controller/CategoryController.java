package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Category;
import com.springboot.service.CategoryService;

@RestController
public class CategoryController 
{

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("api/categories")
	public ResponseEntity<List<Category>> getAllCategory(@RequestParam int page ){
		System.out.println("page"+page);
		return ResponseEntity.ok().body(categoryService.getAllCategory(0,page));
		
	}
	
	@GetMapping("api/categories/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
		Category categoryById = categoryService.getCategoryById(id);
		return ResponseEntity.ok().body(categoryById);
	}
	
	@PostMapping("api/categories")
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		System.out.println("calling createCategory....");
		return ResponseEntity.ok().body(this.categoryService.createCategory(category));
	}
	
	@PutMapping("api/categories/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable long id, @RequestBody Category category){
		category.setCid(id);
		return ResponseEntity.ok().body(this.categoryService.updateCategory(category));
	}

	@DeleteMapping("api/categories/{id}")
	public HttpStatus deleteCategory(@PathVariable long id){
		this.categoryService.deleteCategory(id);
		return HttpStatus.OK;
	}


}
