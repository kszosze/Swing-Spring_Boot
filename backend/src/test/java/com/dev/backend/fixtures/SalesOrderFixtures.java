package com.dev.backend.fixtures;

import static com.dev.backend.fixtures.CustomerFixtures.customerDb;
import static com.dev.backend.fixtures.OrderLineFixtures.orderLine1;
import static com.dev.backend.fixtures.OrderLineFixtures.orderLine2;
import static com.dev.backend.fixtures.ProductFixtures.productDb;
import static com.dev.backend.fixtures.ProductFixtures.productDb2;

import java.util.Arrays;

import com.dev.backend.model.OrderLine;
import com.dev.backend.model.SalesOrder;

public class SalesOrderFixtures {

	public static SalesOrder salesOrder() {
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setOrderNumber(11L);
		salesOrder.setCustomer(customerDb());
		salesOrder.setTotal(234.4f);
		salesOrder.setOrderLines(Arrays.asList(orderLine1(salesOrder), orderLine2(salesOrder)));
		return salesOrder;
	}

	public static SalesOrder salesOrderDb() {
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setId(1L);
		salesOrder.setOrderNumber(1L);
		salesOrder.setCustomer(customerDb());
		salesOrder.setTotal(147.0f);
		OrderLine orderLine1 = new OrderLine(salesOrder, productDb(), 1);
		orderLine1.setId(1L);
		OrderLine orderLine2 = new OrderLine(salesOrder, productDb2(), 1);
		orderLine2.setId(2l);
		salesOrder.setOrderLines(Arrays.asList(orderLine1, orderLine2));
		return salesOrder;
	}
}
