package com.dev.backend.services;

import java.util.List;

import com.dev.backend.model.Product;

public interface ProductService {

	Product findByCode(String productcode);

	Product save(Product product);

	Product create(String code, String description, Float price, Integer quantity);

	List<Product> getAll();

	Product deleteByCode(String code);

}
