package com.springboot.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cid;
	
	@Column(name = "cname")
	private String cname;
	
	@OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
	 @JoinColumn(name = "cat_pro_fk",referencedColumnName = "cid")
	private List<Product> products = new ArrayList<>();
	
	 
	 public Category() {
		
	}
	 public void addProduct(Product product)
	 {
		 products.add(product);
	 }
	 
	 public void removeProduct(Product product)
	 {
		 products.remove(product);
	 }
	
	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
