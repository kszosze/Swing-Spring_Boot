/**
 *  Order Line Entity
 */
package com.dev.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jose Enrique Garcia
 *
 */
@Entity
@Table(name = "order_line")
@NamedQuery(name = "OrderLine.findAllSalesOrderByProduct", query = "Select o.salesOrder from OrderLine o where o.product = ?1")
public class OrderLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5461704069318336098L;

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	@ManyToOne
	@JsonBackReference
	private SalesOrder salesOrder;
	@ManyToOne
	private Product product;
	private Integer quantity;
	@JsonIgnore
	private Boolean valid = true;

	public OrderLine() {
	}

	public OrderLine(SalesOrder order, Product product, Integer quantity) {
		super();
		this.salesOrder = order;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder order) {
		this.salesOrder = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((salesOrder == null) ? 0 : salesOrder.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		OrderLine other = (OrderLine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (salesOrder == null) {
			if (other.salesOrder != null)
				return false;
		} else if (!salesOrder.equals(other.salesOrder))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderLine [id=" + id + ", order=" + salesOrder.getOrderNumber() + ", product=" + product.getCode()
				+ ", quantity=" + quantity + "]";
	}

}
