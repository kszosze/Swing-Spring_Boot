package com.dev.backend.fixtures;

import static com.dev.backend.fixtures.ProductFixtures.product1;
import static com.dev.backend.fixtures.ProductFixtures.product2;
import static com.dev.backend.fixtures.ProductFixtures.productDb;
import static com.dev.backend.fixtures.ProductFixtures.productDb2;
import static com.dev.backend.fixtures.SalesOrderFixtures.salesOrderDb;

import com.dev.backend.model.OrderLine;
import com.dev.backend.model.SalesOrder;

public class OrderLineFixtures {

	public static OrderLine orderLineDb() {
		return new OrderLine(salesOrderDb(), productDb(), 1);
	}

	public static OrderLine orderLineDb2() {
		return new OrderLine(salesOrderDb(), productDb2(), 1);
	}

	public static OrderLine orderLine1(SalesOrder salesOrder) {
		return new OrderLine(salesOrder, product1(), 1);
	}

	public static OrderLine orderLine2(SalesOrder salesOrder) {
		return new OrderLine(salesOrder, product2(), 1);
	}
}
