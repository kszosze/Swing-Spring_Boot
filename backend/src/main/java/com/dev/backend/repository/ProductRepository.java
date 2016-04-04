/**
 * Product repository
 */
package com.dev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.Product;

/**
 * 
 * @author Jose Enrique Garcia
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByCodeAndValidTrue(String code);

	List<Product> findAllByValidTrue();
}
