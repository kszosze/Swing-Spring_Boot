package com.dev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.model.OrderLine;
import com.dev.backend.model.Product;
import com.dev.backend.model.SalesOrder;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

	List<OrderLine> findAllBySalesOrderAndValidTrue(SalesOrder salesOrder);

	List<OrderLine> findAllByProductAndValidTrue(Product product);

	List<SalesOrder> findAllSalesOrderByProductAndValidTrue(Product product);

	OrderLine findAllBySalesOrderAndProductAndValidTrue(SalesOrder salesOrder, Product product);

}
