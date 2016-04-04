/**
 * Sales Order Service Implementation
 */
package com.dev.backend.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Customer;
import com.dev.backend.model.OrderLine;
import com.dev.backend.model.Product;
import com.dev.backend.model.SalesOrder;
import com.dev.backend.repository.OrderLineRepository;
import com.dev.backend.repository.SalesOrderRepository;
import com.dev.backend.services.ProductService;
import com.dev.backend.services.SalesOrderService;

/**
 * @author Jose Enrique Garcia
 *
 */
@Service
public class SalesOrderServiceImpl implements SalesOrderService {

	private static final Logger logger = LoggerFactory.getLogger(SalesOrderService.class);

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	@Autowired
	private OrderLineRepository orderLineRepository;

	@Autowired
	private ProductService productService;

	@Override
	public SalesOrder create(SalesOrder newSalesOrder) {
		newSalesOrder.setTotal(0.0f);
		return salesOrderRepository.save(newSalesOrder);
	}

	@Override
	public SalesOrder update(SalesOrder oldSalesOrder, SalesOrder newSalesOrder) {
		oldSalesOrder.setTotal(0.0f);
		return salesOrderRepository.save(oldSalesOrder);
	}

	@Override
	public OrderLine findOrderLineBySalesOrderAndProduct(SalesOrder salesOrder, Product product) {
		return orderLineRepository.findAllBySalesOrderAndProductAndValidTrue(salesOrder, product);
	}

	@Override
	public SalesOrder updateOrderLine(OrderLine orderLine, Integer newQuantity) {
		Product product = orderLine.getProduct();
		if (product.getQuantity() + orderLine.getQuantity() - newQuantity >= 0) {
			product.setQuantity(product.getQuantity() + orderLine.getQuantity() - newQuantity);
			productService.save(product);
			orderLine.setQuantity(newQuantity);
			orderLineRepository.save(orderLine);
			SalesOrder salesOrder = orderLine.getSalesOrder();
			salesOrder.setTotal(salesOrder.getTotal() + (orderLine.getQuantity() - newQuantity) * product.getPrice());
			salesOrderRepository.save(salesOrder);
			return orderLine.getSalesOrder();
		} else {
			return null;
		}

	}

	@Override
	public SalesOrder findByOrderNumber(Long salesordercode) {
		return salesOrderRepository.findByOrderNumberAndValidTrue(salesordercode);
	}

	@Override
	public SalesOrder addOrderLine(SalesOrder salesOrder, Product product, Integer quantity) {
		product.setQuantity(product.getQuantity() - quantity);
		productService.save(product);
		salesOrder.setTotal(salesOrder.getTotal() + product.getPrice() * quantity);
		OrderLine orderLine = orderLineRepository.findAllBySalesOrderAndProductAndValidTrue(salesOrder, product);
		if (orderLine != null) {
			orderLine.setQuantity(orderLine.getQuantity() + quantity);
		} else {
			orderLine = new OrderLine(salesOrder, product, quantity);
			salesOrder.getOrderLines().add(orderLine);
		}
		orderLineRepository.save(orderLine);
		salesOrderRepository.save(salesOrder);
		return salesOrder;
	}

	@Override
	public List<SalesOrder> findAll() {
		return salesOrderRepository.findAllByValidTrue();
	}

	@Override
	public List<SalesOrder> findByCustomer(Customer customer) {
		return salesOrderRepository.findByCustomerAndValidTrue(customer);
	}

	@Override
	public SalesOrder deletedByCode(Long orderNumber) {
		SalesOrder salesOrder = findByOrderNumber(orderNumber);
		if (salesOrder != null) {
			delete(salesOrder);
		}
		return salesOrder;
	}

	@Override
	public void delete(SalesOrder salesOrder) {
		salesOrder.setValid(false);
		salesOrderRepository.save(salesOrder);
		for (OrderLine orderLine : salesOrder.getOrderLines()) {
			Product product = orderLine.getProduct();
			product.setQuantity(product.getQuantity() + orderLine.getQuantity());
			productService.save(product);
			orderLine.setValid(false);
			orderLineRepository.save(orderLine);
		}
	}
}
