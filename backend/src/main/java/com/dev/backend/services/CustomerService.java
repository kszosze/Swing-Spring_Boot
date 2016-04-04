/**
 *  Customer Service definition
 */
package com.dev.backend.services;

import java.util.List;

import com.dev.backend.model.Customer;

/**
 * @author Jose Enrique Garcia
 *
 */
public interface CustomerService {

	Customer create(String name, String code, String phone, String phone2, String address, Float creditLimit,
			Float credit);

	Customer save(Customer customer);

	Customer findByCode(String code);

	List<Customer> getAll();

	Customer deleteByCode(String code);

}
