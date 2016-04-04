/**
 * Customer Service Implementation
 */
package com.dev.backend.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.model.Customer;
import com.dev.backend.model.SalesOrder;
import com.dev.backend.repository.CustomerRepository;
import com.dev.backend.services.CustomerService;
import com.dev.backend.services.SalesOrderService;

/**
 * @author Jose Enrique Garcia
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private SalesOrderService salesOrderService;

	@Override
	public Customer create(String name, String code, String phone, String phone2, String address, Float creditLimit,
			Float credit) {
		Customer customer = new Customer(code, name, phone, phone2, address, creditLimit, credit);
		return customerRepository.save(customer);
	}

	@Override
	public Customer save(Customer newCustomer) {
		Customer customer = null;
		if (newCustomer.getId() == null) {
			customer = findByCode(newCustomer.getCode());
			if (customer == null) {
				customer = customerRepository.save(newCustomer);
			} else {
				newCustomer.setId(customer.getId());
				customer = customerRepository.save(newCustomer);
			}
		}
		return customer;
	}

	@Override
	public Customer findByCode(String code) {
		return customerRepository.findByCodeAndValidTrue(code);
	}

	@Override
	public List<Customer> getAll() {

		return customerRepository.findAllByValidTrue();
	}

	@Override
	public Customer deleteByCode(String code) {
		Customer customer = findByCode(code);
		if (customer != null) {
			customer.setValid(false);
			customerRepository.save(customer);
			for (SalesOrder salesOrder : customer.getSalesOrderList()) {
				salesOrderService.delete(salesOrder);
			}
		}
		return customer;
	}

}
