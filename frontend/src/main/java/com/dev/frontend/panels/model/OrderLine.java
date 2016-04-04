/**
 *  Order Line Entity
 */
package com.dev.frontend.panels.model;

/**
 * @author Jose Enrique Garcia
 *
 */
public class OrderLine extends ModelObject {

	private SalesOrder salesOrder;
	private Product product;
	private Integer quantity;

	public OrderLine() {
	}

	public OrderLine(SalesOrder order, Product product, Integer quantity) {
		super();
		this.salesOrder = order;
		this.product = product;
		this.quantity = quantity;
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

	@Override
	public String toString() {
		return "OrderLine [order=" + salesOrder + ", product=" + product + ", quantity=" + quantity + "]";
	}

	public String[] toRowModel() {
		String[] rowModel = new String[4];
		rowModel[0] = this.product.getCode();
		rowModel[1] = String.valueOf(this.quantity);
		rowModel[2] = String.valueOf(this.product.getPrice());
		rowModel[3] = String.valueOf(this.quantity * this.product.getPrice());
		return rowModel;
	}

	@Override
	public String toJson() {
		return "{ \"salesOrder\":" + salesOrder.toJson() + ", \"product\":" + product.toJson() + ", \"quantity\":\""
				+ quantity + "\"}";
	}

}
