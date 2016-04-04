/**
 * Sales Order Entity
 */
package com.dev.frontend.panels.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose Enrique Garcia
 *
 */
public class SalesOrder extends ModelObject {

	private Long orderNumber;
	private Customer customer;
	private Float total;
	private List<OrderLine> orderLines = new ArrayList<>();

	public SalesOrder() {
	}

	public SalesOrder(Long orderNumber, Customer customer, Float total, List<OrderLine> orderLines) {
		this.orderNumber = orderNumber;
		this.customer = customer;
		this.total = total;
		this.orderLines = orderLines;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	@Override
	public String toString() {
		return "SalesOrder [orderNumber=" + orderNumber + ", customer=" + customer + ", total=" + total
				+ ", orderLines=" + orderLines + "]";
	}

	public String[] toRowTable() {
		String[] rowTable = new String[3];
		rowTable[0] = String.valueOf(this.orderNumber);
		rowTable[1] = "(" + this.customer.getCode() + ")" + this.customer.getName();
		rowTable[2] = String.valueOf(this.total);
		return rowTable;
	}

	@Override
	public String toJson() {
		return "{\"orderNumber\":\"" + orderNumber + "\",\"customer\":" + customer.toJson() + ",\"total\":\"" + total
				+ "\"}";
	}

}
