/**
 * Sales Order Repository
 */
package com.dev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.Customer;
import com.dev.backend.model.SalesOrder;

/**
 * 
 * @author Jose Enrique Garcia
 *
 */
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

	List<SalesOrder> findByCustomerAndValidTrue(Customer customer);

	SalesOrder findByOrderNumberAndValidTrue(Long salesordercode);

	List<SalesOrder> findAllByValidTrue();
}
