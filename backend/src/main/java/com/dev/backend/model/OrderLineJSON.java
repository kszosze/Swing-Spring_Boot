/**
 * 
 */
package com.dev.backend.model;

/**
 * @author kszosze
 *
 */
public class OrderLineJSON {

	private Long salesOrderNumber;
	private String productCode;
	private Integer quantity;

	public Long getSalesOrderNumber() {
		return salesOrderNumber;
	}

	public void setSalesOrderNumber(Long salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
