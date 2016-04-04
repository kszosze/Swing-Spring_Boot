/**
 * Backend Main Controller
 */
package com.dev.backend.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.backend.exception.BackendErrorCode;
import com.dev.backend.exception.BackendException;
import com.dev.backend.model.Customer;
import com.dev.backend.model.OrderLine;
import com.dev.backend.model.Product;
import com.dev.backend.model.SalesOrder;
import com.dev.backend.services.CustomerService;
import com.dev.backend.services.ProductService;
import com.dev.backend.services.SalesOrderService;

/**
 * @author Jose Enrique Garcia
 *
 */
@RestController
@RequestMapping("/backend/v1/")
public class BackendController {
	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private SalesOrderService salesOrderService;

	@RequestMapping(value = "/add/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Customer addCustomer(@RequestBody Customer customerJSON) {
		Customer customer = customerService.save(customerJSON);
		return customer;
	}

	@RequestMapping(value = "/add/salesorder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody SalesOrder createSalesOrder(@RequestBody SalesOrder salesOrderJson) {
		Customer customer = customerService.findByCode(salesOrderJson.getCustomer().getCode());
		if (customer != null) {
			salesOrderJson.setCustomer(customer);
			SalesOrder salesOrder = salesOrderService.findByOrderNumber(salesOrderJson.getOrderNumber());
			if (salesOrder != null) {
				if (salesOrder.getTotal() != salesOrderJson.getTotal()) {
					return salesOrderService.update(salesOrder, salesOrderJson);
				} else {
					return salesOrder;
				}
			} else {
				return salesOrderService.create(salesOrderJson);
			}

		} else {
			throw new BackendException(BackendErrorCode.CUSTOMER_NOT_FOUND, salesOrderJson.getCustomer().getCode());
		}
	}

	@RequestMapping(value = "/add/orderline", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public @ResponseBody SalesOrder addOrderLine(@RequestBody OrderLine orderLineJSON) {
		SalesOrder salesOrder = salesOrderService.findByOrderNumber(orderLineJSON.getSalesOrder().getOrderNumber());
		if (salesOrder != null) {
			Product product = productService.findByCode(orderLineJSON.getProduct().getCode());
			if (product != null) {
				OrderLine orderLine = salesOrderService.findOrderLineBySalesOrderAndProduct(salesOrder, product);
				if (orderLine != null) {
					if (orderLine.getQuantity() != orderLineJSON.getQuantity()) {
						return salesOrderService.updateOrderLine(orderLine, orderLineJSON.getQuantity());
					} else
						return salesOrder;
				} else if (orderLineJSON.getQuantity() <= product.getQuantity()) {
					return salesOrderService.addOrderLine(salesOrder, product, orderLineJSON.getQuantity());
				} else {
					throw new BackendException(BackendErrorCode.PRODUCT_SOLD_OUT,
							product.getCode() + " - " + orderLineJSON.getQuantity());
				}
			} else {
				throw new BackendException(BackendErrorCode.PRODUCT_NOT_FOUND, orderLineJSON.getProduct().getCode());
			}
		} else {
			throw new BackendException(BackendErrorCode.SALES_ORDER_NOT_FOUND,
					orderLineJSON != null ? String.valueOf(orderLineJSON.getSalesOrder().getOrderNumber()) : "null");
		}
	}

	@RequestMapping(value = "/add/product", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Product addProduct(@RequestBody Product productJSON) {
		Product product = productService.findByCode(productJSON.getCode());
		if (product == null) {
			product = productService.save(productJSON);
		} else {
			product.setPrice(productJSON.getPrice());
			product.setDescription(productJSON.getDescription());
			product.setQuantity(productJSON.getQuantity());
			productService.save(product);
		}
		return product;
	}

	@RequestMapping(value = "/find/customer/{code}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Customer findCustomerByCode(@PathVariable String code) {
		return customerService.findByCode(code);
	}

	@RequestMapping(value = "/find/product/{code}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Product findProductByCode(@PathVariable String code) {
		return productService.findByCode(code);
	}

	@RequestMapping(value = "/find/salesorder/{orderNumber}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody SalesOrder findSalesOrderByOrderNumber(@PathVariable Long orderNumber) {
		return salesOrderService.findByOrderNumber(orderNumber);
	}

	@RequestMapping(value = "/delete/customer/{code}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Customer deleteCustomerByCode(@PathVariable String code) {
		return customerService.deleteByCode(code);
	}

	@RequestMapping(value = "/delete/product/{code}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Product deleteProductByCode(@PathVariable String code) {
		return productService.deleteByCode(code);
	}

	@RequestMapping(value = "/delete/salesorder/{orderNumber}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody SalesOrder deleteSalesOrderByOrderNumber(@PathVariable Long orderNumber) {
		return salesOrderService.deletedByCode(orderNumber);
	}

	@RequestMapping(value = "/get/all/customer", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Customer> getAllCustomer() {
		return customerService.getAll();
	}

	@RequestMapping(value = "/get/all/salesorder", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<SalesOrder> getSalesOrder() {
		return salesOrderService.findAll();
	}

	@RequestMapping(value = "/get/all/salesorder/{customerCode}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<SalesOrder> getSalesOrder(@PathVariable String customerCode) {
		if (StringUtils.isEmpty(customerCode)) {
			return salesOrderService.findAll();
		} else {
			Customer customer = null;
			if ((customer = customerService.findByCode(customerCode)) != null) {
				return salesOrderService.findByCustomer(customer);
			} else {
				throw new BackendException(BackendErrorCode.CUSTOMER_NOT_FOUND, customerCode);
			}
		}
	}

	@RequestMapping(value = "/get/all/product", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Product> getAllProduct() {
		return productService.getAll();
	}
}
