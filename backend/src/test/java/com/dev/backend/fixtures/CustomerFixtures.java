package com.dev.backend.fixtures;

import com.dev.backend.model.Customer;

public class CustomerFixtures {

	public static final Customer customer() {
		return new Customer("TestCode", "Test User", "+55523568974", "", "Local Street", 100.0f, 45.90f);
	}

	public static final Customer customerDb() {
		Customer customer = new Customer("cod1", "customer1", "+555895623", "", "Local Street", 100.0f, 45.7f);
		customer.setId(1L);

		return customer;
	}
}
