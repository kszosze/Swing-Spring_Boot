/**
 * Product Entity
 */
package com.dev.frontend.panels.model;

/**
 * @author Jose Enrique Garcia
 *
 */
public class Product extends ModelObject {

	private String code;
	private String description;
	private Float price;
	private Integer quantity;

	public Product() {
	}

	public Product(String code, String description, Float price, Integer quantity) {
		this.code = code;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", description=" + description + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}

	public String[] toRowTable() {
		String[] rowTable = new String[4];
		rowTable[0] = String.valueOf(this.code);
		rowTable[1] = this.description;
		rowTable[2] = String.valueOf(this.price);
		rowTable[3] = String.valueOf(this.quantity);
		return rowTable;
	}

	@Override
	public String toJson() {
		return "{ \"code\":\"" + code + "\", \"description\":\"" + description + "\", \"price\":\"" + price
				+ "\", \"quantity\":\"" + quantity + "\"}";
	}

}
