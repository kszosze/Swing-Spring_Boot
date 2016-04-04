/**
 *  Product Repository Test
 */
package com.dev.backend.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.backend.BackendApplicationTests;
import com.dev.backend.model.SalesOrder;
import com.dev.backend.repository.SalesOrderRepository;

import static com.dev.backend.fixtures.SalesOrderFixtures.salesOrder;
import static com.dev.backend.fixtures.CustomerFixtures.customerDb;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.Is.is;


/**
 * @author Jose Enrique Garcia
 *
 */
public class SalesOrderRepositoryTest extends BackendApplicationTests {
	
	@Autowired
	private SalesOrderRepository salesOrderRepository;
	
	@Test
	public final void testSave()
	{
		SalesOrder testSalesOrder = salesOrderRepository.save(salesOrder());
		assertNotNull(testSalesOrder);
		assertThat(testSalesOrder.getId(),equalTo(4L));
	}

	@Test
	public final void testFindByCode()
	{
		List<SalesOrder> salesOrderList = salesOrderRepository.findByCustomerAndValidTrue(customerDb());
		assertThat(salesOrderList,is(not(empty())));
		assertThat(salesOrderList,hasSize(2));
	}
}
