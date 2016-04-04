/**
 * Backend Controller Test
 */
package com.dev.backend.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dev.backend.BackendApplicationTests;

/**
 * @author Jose Enrique Garcia
 *
 */
@WebIntegrationTest
public class BackendControllerTest extends BackendApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testAddCustomer() {
		try {
			mockMvc.perform(post("/backend/v1/add/customer").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(
					"{\"name\":\"customer10\", \"code\":\"custom10\", \"phone\":\"5558963241\",\"address\":\"Whatever Street\",\"creditLimit\":\"100.0\"}"))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.name").value("customer10"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testCreateSalesOrder() {
		try {
			mockMvc.perform(
					post("/backend/v1/add/salesorder").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(
							"{\"orderNumber\":\"3\",\"customer\":{\"code\":\"cod1\", \"name\":\"customer1\", \"phone\":\"+555895623\", \"credit\":\"45.7\"},\"total\":\"348.7\"}"))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.orderNumber").value(3))
					.andExpect(jsonPath("$.customer.name").value("customer1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddOrderLine() {
		try {
			mockMvc.perform(post("/backend/v1/add/orderline").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content("{\"salesOrderNumber\":\"2\",\"productCode\":\"prod3\",\"quantity\":\"5\"}"))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andDo(print()).andExpect(jsonPath("$.total").value(161.5))
					.andExpect(jsonPath("$.customer.name").value("customer1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddProduct() {
		try {
			mockMvc.perform(post("/backend/v1/add/product").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(
					"{\"code\":\"prod10\",\"description\":\"Description product 10\",\"price\":\"54.9\",\"quantity\":\"10\"}"))
					.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.description").value("Description product 10"))
					.andExpect(jsonPath("$.quantity").value(10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindCustomerByCode() {
		try {
			mockMvc.perform(get("/backend/v1/find/customer/cod1")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.name").value("customer1")).andExpect(jsonPath("$.credit").value(45.7));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindProductByCode() {
		try {
			mockMvc.perform(get("/backend/v1/find/product/prod1")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.description").value("this is the product 1"))
					.andExpect(jsonPath("$.price").value(23.5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindSalesOrderByOrderNumber() {
		try {
			mockMvc.perform(get("/backend/v1/find/salesorder/4")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.orderNumber").value(4)).andExpect(jsonPath("$.total").value(347.0))
					.andExpect(jsonPath("$.orderLines").isArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteCustomerByCode() {
		try {
			mockMvc.perform(delete("/backend/v1/delete/customer/cod1")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.name").value("customer1")).andExpect(jsonPath("$.credit").value(45.7));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteProductByCode() {
		try {
			mockMvc.perform(delete("/backend/v1/delete/product/prod1")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.description").value("this is the product 1"))
					.andExpect(jsonPath("$.price").value(23.5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteSalesOrderByOrderNumber() {
		try {
			mockMvc.perform(delete("/backend/v1/delete/salesorder/4")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$.orderNumber").value(4)).andExpect(jsonPath("$.total").value(347.0))
					.andExpect(jsonPath("$.orderLines").isArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllCustomer() {
		try {
			mockMvc.perform(get("/backend/v1/get/all/customer")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].name").value("customer1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllProduct() {
		try {
			mockMvc.perform(get("/backend/v1/get/all/product")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].code").value("prod1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetSalesOrder() {
		try {
			mockMvc.perform(get("/backend/v1/get/all/salesorder/cod2")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].orderNumber").value(4));
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
