/**
 * Customer Repository
 */
package com.dev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.Customer;

/**
 * @author Jose Enrique Garcia
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@EntityGraph(value = "Customer.sales", type = EntityGraphType.LOAD)
	Customer findByCodeAndValidTrue(String code);

	@EntityGraph(value = "Customer.sales", type = EntityGraphType.LOAD)
	List<Customer> findAllByNameLikeAndValidTrue(String name);

	List<Customer> findAllByValidTrue();
}
