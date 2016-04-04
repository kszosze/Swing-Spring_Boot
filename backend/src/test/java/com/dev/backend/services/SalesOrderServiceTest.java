/**
 * Sales Order Service test cases
 */
package com.dev.backend.services;

import static com.dev.backend.fixtures.CustomerFixtures.customerDb;
import static com.dev.backend.fixtures.ProductFixtures.productDb;
import static com.dev.backend.fixtures.SalesOrderFixtures.salesOrder;
import static com.dev.backend.fixtures.SalesOrderFixtures.salesOrderDb;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.backend.BackendApplicationTests;
import com.dev.backend.model.SalesOrder;

/**
 * @author Jose Enrique Garcia
 *
 */
public class SalesOrderServiceTest extends BackendApplicationTests {

	@Autowired
	private SalesOrderService salesOrderService;

	@Test
	public void testAddOrderLine() {
		assertThat(salesOrderDb().getOrderLines(), hasSize(2));
		assertThat(salesOrderDb().getTotal(), is(147.0f));
		SalesOrder newSalesOrder = salesOrderService.addOrderLine(salesOrderDb(), productDb(), 3);
		assertThat(newSalesOrder.getOrderLines(), hasSize(2));
		assertThat(newSalesOrder.getTotal(), is(217.5f));
	}

	@Test
	public void testCreate() {
		SalesOrder salesOrder = salesOrderService.create(salesOrder());
		assertNotNull(salesOrder.getId());
		assertThat(salesOrder.getCustomer(), is(customerDb()));
		assertThat(salesOrder.getOrderNumber(), is(11L));
	}

	@Test
	public void testFindAll() {
		List<SalesOrder> salesOrderList = salesOrderService.findAll();
		assertThat(salesOrderList, not(empty()));
		assertThat(salesOrderList, hasSize(3));
	}

	@Test
	public void testFindByCode() {
		SalesOrder salesOrder = salesOrderService.findByOrderNumber(4L);
		assertNotNull(salesOrder);
		assertThat(salesOrder.getId(), is(3L));
		assertThat(salesOrder.getTotal(), is(347.0f));
	}

	@Test
	public void testFindByCustomer() {
		List<SalesOrder> salesOrderList = salesOrderService.findByCustomer(customerDb());
		assertThat(salesOrderList, not(empty()));
		assertThat(salesOrderList, hasSize(2));
	}

	@Test
	public void testDeleteByCode() {
		SalesOrder salesOrder = salesOrderService.deletedByCode(4l);
		assertFalse(salesOrder.getValid());
		assertThat(salesOrderService.findAll(), hasSize(2));
	}
}
