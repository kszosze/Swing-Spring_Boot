package com.dev.backend.repository;

import static com.dev.backend.fixtures.OrderLineFixtures.orderLineDb;
import static com.dev.backend.fixtures.ProductFixtures.productDb;
import static com.dev.backend.fixtures.SalesOrderFixtures.salesOrderDb;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.backend.BackendApplicationTests;
import com.dev.backend.model.OrderLine;
import com.dev.backend.model.SalesOrder;

public class OrderLineRepositoryTest extends BackendApplicationTests {

	@Autowired
	private OrderLineRepository orderLineRepository;

	@Test
	public void testSave() {
		OrderLine orderLineTest = orderLineRepository.save(orderLineDb());
		assertNotNull(orderLineTest);
		assertThat(orderLineTest.getId(), is(7L));
	}

	@Test
	public void testFindAllByProduct() {
		List<OrderLine> orderLineList = orderLineRepository.findAllByProductAndValidTrue(productDb());
		assertThat(orderLineList, is(not(empty())));
		assertThat(orderLineList, is(hasSize(2)));
	}

	@Test
	public void testFindAllBySalesOrder() {
		List<OrderLine> orderLineList = orderLineRepository.findAllBySalesOrderAndValidTrue(salesOrderDb());
		assertThat(orderLineList, is(not(empty())));
		assertThat(orderLineList, is(hasSize(2)));
	}

	@Test
	public void testFindAllBySalesOrderAndProduct() {
		OrderLine orderLine = orderLineRepository.findAllBySalesOrderAndProductAndValidTrue(salesOrderDb(), productDb());
		assertNotNull(orderLine);
		assertThat(orderLine.getSalesOrder(), is(salesOrderDb()));
		assertThat(orderLine.getProduct(), is(productDb()));
	}

	@Test
	public void testFindAllSalesOrderByProduct() {
		List<SalesOrder> orderLineList = orderLineRepository.findAllSalesOrderByProductAndValidTrue(productDb());
		assertThat(orderLineList, is(not(empty())));
		assertThat(orderLineList, is(hasSize(2)));
	}
}
