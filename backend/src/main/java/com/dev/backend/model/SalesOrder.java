/**
 * Sales Order Entity
 */
package com.dev.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Jose Enrique Garcia
 *
 */
@Entity
@Table(name = "sales_order")
public class SalesOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8810313295613691111L;

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;

	@Column(unique = true, nullable = false)
	private Long orderNumber;
	@ManyToOne
	private Customer customer;
	private Float total;
	@OneToMany(mappedBy = "salesOrder", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<OrderLine> orderLines = new ArrayList<>();
	@JsonIgnore
	private Boolean valid = true;

	public SalesOrder() {
	}

	public SalesOrder(Long orderNumber, Customer customer, Float total, List<OrderLine> orderLines) {
		super();
		this.orderNumber = orderNumber;
		this.customer = customer;
		this.total = total;
		this.orderLines = orderLines;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((valid == null) ? 0 : valid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesOrder other = (SalesOrder) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (valid == null) {
			if (other.valid != null)
				return false;
		} else if (!valid.equals(other.valid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesOrder [id=" + id + ", orderNumber=" + orderNumber + ", customer=" + customer + ", total=" + total
				+ "]";
	}

}
