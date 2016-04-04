/**
 *  Customer Service test cases
 */
package com.dev.backend.services;

import static com.dev.backend.fixtures.CustomerFixtures.customer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.backend.BackendApplicationTests;
import com.dev.backend.model.Customer;

/**
 * @author Jose Enrique Garcia
 *
 */
public class CustomerServiceTest extends BackendApplicationTests {

	@Autowired
	private CustomerService customerService;

	/**
	 * Test method for
	 * {@link com.dev.backend.services.CustomerService#create(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testCreate() throws Exception {
		Customer customer = customerService.create("Test Customer", "Test Code", "+555875421", "", "Local Street",
				100.0f, 0f);
		assertNotNull(customer);
		assertNotNull(customer.getId());
		assertThat(customer.getName(), is("Test Customer"));
	}

	/**
	 * Test method for
	 * {@link com.dev.backend.services.CustomerService#save(com.dev.backend.model.Customer)}
	 * .
	 */
	@Test
	public void testSave() throws Exception {
		Customer testCustomer = customerService.save(customer());
		assertNotNull(testCustomer);
		assertThat(testCustomer.getId(), equalTo(4L));
	}

	/**
	 * Test method for
	 * {@link com.dev.backend.services.CustomerService#findByCode(java.lang.String)}
	 * .
	 */
	@Test
	public void testFindByCode() throws Exception {
		Customer testCustomer = customerService.findByCode("cod1");
		assertNotNull(testCustomer);
		assertThat(testCustomer.getId(), equalTo(1L));
		assertThat(testCustomer.getSalesOrderList(), hasSize(2));
	}

	/**
	 * Test method for {@link com.dev.backend.services.CustomerService#getAll()}
	 * .
	 */
	@Test
	public void testGetAll() throws Exception {
		List<Customer> customerList = customerService.getAll();
		assertThat(customerList, hasSize(3));
	}

	@Test
	public void testDeleteByCode() {
		Customer testCustomer = customerService.deleteByCode("cod1");
		assertFalse(testCustomer.getValid());
		assertThat(customerService.getAll(), hasSize(2));
	}

}
