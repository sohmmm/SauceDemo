package com.saucedemo.utils;

public class Product {
	private String name;
	private String description;
	private String price;

	public Product(String name, String description, String price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getPrice() {
		return this.price;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.description + " " + this.price;
	}
}