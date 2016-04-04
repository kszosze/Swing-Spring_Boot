/**
 *  Product Repository Test
 */
package com.dev.backend.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.backend.BackendApplicationTests;
import com.dev.backend.model.Product;
import static com.dev.backend.fixtures.ProductFixtures.product;
import static org.junit.Assert.assertNotNull;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * @author Jose Enrique Garcia
 *
 */
public class ProductRepositoryTest extends BackendApplicationTests {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public final void testSave()
	{
		Product testProduct = productRepository.save(product());
		assertNotNull(testProduct);
		assertThat(testProduct.getId(),equalTo(4L));
	}

	@Test
	public final void testFindByCode()
	{
		Product testProduct = productRepository.findByCodeAndValidTrue("prod1");
		assertNotNull(testProduct);
		assertThat(testProduct.getId(),equalTo(1L));
		assertThat(testProduct.getQuantity(),(equalTo(10)));
	}
}
