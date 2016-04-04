package com.dev.backend.fixtures;

import com.dev.backend.model.Product;

public class ProductFixtures {

	public static Product productDb() {
		Product product = new Product();
		product.setId(1L);
		product.setCode("prod1");
		product.setDescription("this is the product 1");
		product.setPrice(23.5f);
		product.setQuantity(10);
		return product;
	}

	public static Product productDb2() {
		Product product = new Product();
		product.setId(2L);
		product.setCode("prod2");
		product.setDescription("this is the product 2");
		product.setPrice(123.5f);
		product.setQuantity(5);
		return product;
	}

	public static Product product() {
		return new Product("prod7", "Test Product 4", 45.7f, 5);
	}

	public static Product product1() {
		return new Product("prod10", "Test Product 10", 15.7f, 3);
	}

	public static Product product2() {
		return new Product("prod11", "Test Product 11", 35.7f, 8);
	}
}
