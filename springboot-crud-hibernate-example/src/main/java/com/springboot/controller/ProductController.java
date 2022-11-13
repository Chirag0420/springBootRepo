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
import com.springboot.model.Product;
import com.springboot.service.CategoryService;
import com.springboot.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("api/products")
	public ResponseEntity<List<Product>> getAllProduct(@RequestParam String page ){
		System.out.println("page>>"+page);
		//if(page=="2")
		return ResponseEntity.ok().body(productService.getAllProduct());
		//else
		//return ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping("api/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id){
		return ResponseEntity.ok().body(productService.getProductById(id));
	}
	
	@PostMapping("api/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		if(product.getCategory()!=null)
		{
			long cid = product.getCategory().getCid();
			System.out.println("cid>>>>"+cid);
			Category categoryDB = categoryService.getCategoryById(cid);
			product.setCategory(categoryDB);
		}
		
		return ResponseEntity.ok().body(this.productService.createProduct(product));
	}
	
	@PutMapping("api/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product){
		product.setId(id);
		return ResponseEntity.ok().body(this.productService.updateProduct(product));
	}

	@DeleteMapping("api/products/{id}")
	public HttpStatus deleteProduct(@PathVariable long id){
		this.productService.deleteProduct(id);
		return HttpStatus.OK;
	}
}
