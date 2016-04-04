/**
 *  Customer Repository Test
 */
package com.dev.backend.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.backend.BackendApplicationTests;
import com.dev.backend.model.Customer;
import com.dev.backend.repository.CustomerRepository;

import static com.dev.backend.fixtures.CustomerFixtures.customer;
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
public class CustomerRepositoryTest extends BackendApplicationTests {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public final void testSave()
	{
		Customer testCustomer = customerRepository.save(customer());
		assertNotNull(testCustomer);
		assertThat(testCustomer.getId(),equalTo(4L));
	}

	@Test
	public final void testFindByCode()
	{
		Customer testCustomer = customerRepository.findByCodeAndValidTrue("cod1");
		assertNotNull(testCustomer);
		assertThat(testCustomer.getId(),equalTo(1L));
		assertThat(testCustomer.getSalesOrderList(),hasSize(2));
	}
	
	@Test
	public final void testFindByNameLike()
	{
		List<Customer> customerList = customerRepository.findAllByNameLikeAndValidTrue("customer%");
		assertThat(customerList,is(not(empty())));
		assertThat(customerList,hasSize(4));
		
	}
}
