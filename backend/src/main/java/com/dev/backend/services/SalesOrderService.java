package com.dev.backend.services;

import java.util.List;

import com.dev.backend.model.Customer;
import com.dev.backend.model.OrderLine;
import com.dev.backend.model.Product;
import com.dev.backend.model.SalesOrder;

public interface SalesOrderService {

	SalesOrder create(SalesOrder salesOrder);

	SalesOrder update(SalesOrder oldSalesOrder, SalesOrder newSalesOrder);

	SalesOrder findByOrderNumber(Long salesOrderNumber);

	SalesOrder addOrderLine(SalesOrder salesOrder, Product product, Integer quantity);

	List<SalesOrder> findAll();

	List<SalesOrder> findByCustomer(Customer customer);

	SalesOrder deletedByCode(Long orderNumber);

	void delete(SalesOrder salesOrder);

	OrderLine findOrderLineBySalesOrderAndProduct(SalesOrder salesOrder, Product product);

	SalesOrder updateOrderLine(OrderLine orderLine, Integer newQuantity);

}
