/**
 *  Customer entity class
 */
package com.dev.frontend.panels.model;

import java.io.Serializable;

/**
 * @author Jose Enrique Garcia
 *
 */

public class Customer extends ModelObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2367745470125232148L;
	private String code;
	private String name;
	private String phone;
	private String phone2;
	private String address;
	private Float creditLimit = 0f;
	private Float credit = 0f;

	public Customer() {
	}

	public Customer(String code, String name, String phone, String phone2, String address, Float creditLimit,
			Float credit) {
		super();
		this.code = code;
		this.name = name;
		this.phone = phone;
		this.phone2 = phone2;
		this.address = address;
		this.creditLimit = creditLimit;
		this.credit = credit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Float getCredit() {
		return credit;
	}

	public void setCredit(Float credit) {
		this.credit = credit;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Float creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public String toString() {
		return "Customer [code=" + code + ", name=" + name + ", phone=" + phone + ", credit=" + credit + "]";
	}

	public String[] toRowTable() {
		String[] rowTable = new String[4];
		rowTable[0] = String.valueOf(this.code);
		rowTable[1] = this.name;
		rowTable[2] = this.phone;
		rowTable[3] = String.valueOf(this.credit);
		return rowTable;
	}

	@Override
	public String toJson() {
		return "{\"name\":\"" + name + "\"," + "\"code\":\"" + code + "\"," + "\"phone\":\"" + phone + "\","
				+ "\"phone2\":\"" + phone2 + "\",\"address\":\"" + address + "\",\"creditLimit\":\"" + creditLimit
				+ "\",\"credit\":\"" + credit + "\"}";
	}

}
