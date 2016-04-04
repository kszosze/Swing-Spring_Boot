/**
 * Product Service test cases
 */
package com.dev.backend.services;

import static com.dev.backend.fixtures.ProductFixtures.product;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.backend.BackendApplicationTests;
import com.dev.backend.model.Product;

/**
 * @author Jose Enrique Garcia
 *
 */
public class ProductServiceTest extends BackendApplicationTests {

	@Autowired
	private ProductService productService;

	@Test
	public void testCreate() {
		Product product = productService.create("testCode1", "Testing Description", 34.5f, 13);
		assertNotNull(product);
		assertNotNull(product.getId());
		assertThat(product.getCode(), is("testCode1"));
	}

	@Test
	public void testFindByCode() {
		Product product = productService.findByCode("prod1");
		assertNotNull(product);
		assertThat(product.getId(), is(1l));
		assertThat(product.getCode(), is("prod1"));
	}

	@Test
	public void testSave() {
		Product testProduct = productService.save(product());
		assertNotNull(testProduct);
		assertThat(testProduct.getId(), equalTo(4L));
	}

	@Test
	public void testGetAll() {
		List<Product> productList = productService.getAll();
		assertThat(productList, not(empty()));
		assertThat(productList, hasSize(3));
	}

	@Test
	public void testDeleteByCode() {
		Product product = productService.deleteByCode("prod1");
		assertFalse(product.getValid());
		assertThat(productService.getAll(), hasSize(2));
	}
}
