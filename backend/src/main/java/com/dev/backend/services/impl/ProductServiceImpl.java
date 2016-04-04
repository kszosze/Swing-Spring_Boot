/**
 * Product Service Implementation
 */
package com.dev.backend.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Product;
import com.dev.backend.repository.ProductRepository;
import com.dev.backend.services.ProductService;

/**
 * @author Jose Enrique Garcia
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product findByCode(String productcode) {
		return productRepository.findByCodeAndValidTrue(productcode);
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product create(String code, String description, Float price, Integer quantity) {
		return productRepository.save(new Product(code, description, price, quantity));

	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAllByValidTrue();
	}

	@Override
	public Product deleteByCode(String code) {
		Product product = findByCode(code);
		if (product != null) {
			product.setValid(false);
			productRepository.save(product);
		}
		return product;
	}
}
