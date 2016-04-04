package com.dev.frontend.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.dev.frontend.panels.ComboBoxItem;
import com.dev.frontend.panels.model.Customer;
import com.dev.frontend.panels.model.OrderLine;
import com.dev.frontend.panels.model.Product;
import com.dev.frontend.panels.model.SalesOrder;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

public class Services {

	private static final Configuration conf = Configuration.builder().mappingProvider(new JacksonMappingProvider())
			.jsonProvider(new JacksonJsonProvider()).build();

	public static final int TYPE_PRODUCT = 1;
	public static final int TYPE_CUSTOMER = 2;
	public static final int TYPE_SALESORDER = 3;

	public static final String url = "http://localhost:8080/backend/v1";

	public static Object save(Object object, int objectType) {
		// TODO by the candidate
		/*
		 * This method is called eventually after you click save on any edit
		 * screen object parameter is the return object from calling method
		 * guiToObject on edit screen and the type is identifier of the object
		 * type and may be TYPE_PRODUCT , TYPE_CUSTOMER or TYPE_SALESORDER
		 */
		Object result = null;
		try {
			if (TYPE_CUSTOMER == objectType) {
				Customer customer = (Customer) object;
				WSService.wsPost(url + "/add/customer/", customer);
				result = customer;
			} else if (TYPE_PRODUCT == objectType) {
				Product product = (Product) object;
				WSService.wsPost(url + "/add/product/", product);
				result = product;
			} else if (TYPE_SALESORDER == objectType) {
				SalesOrder salesOrder = (SalesOrder) object;
				WSService.wsPost(url + "/add/salesorder/", salesOrder);
				for (OrderLine orderLine : salesOrder.getOrderLines()) {
					WSService.wsPost(url + "/add/orderline/", orderLine);
				}
				result = salesOrder;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Object readRecordByCode(String code, int objectType) {
		// TODO by the candidate
		/*
		 * This method is called when you select record in list view of any
		 * entity and also called after you save a record to re-bind the record
		 * again the code parameter is the first column of the row you have
		 * selected and the type is identifier of the object type and may be
		 * TYPE_PRODUCT , TYPE_CUSTOMER or TYPE_SALESORDER
		 */
		Object result = null;
		try {
			if (TYPE_CUSTOMER == objectType) {
				String answer = WSService.wsGet(url + "/find/customer/", new Object[] { code });
				if (!answer.isEmpty()) {
					result = JsonPath.parse(answer, conf).read("$", Customer.class);
				}
			} else if (TYPE_PRODUCT == objectType) {
				String answer = WSService.wsGet(url + "/find/product/", new Object[] { code });
				if (!answer.isEmpty()) {
					result = JsonPath.parse(answer, conf).read("$", Product.class);
				}
			} else if (TYPE_SALESORDER == objectType) {
				String answer = WSService.wsGet(url + "/find/salesorder/", new Object[] { code });
				if (!answer.isEmpty()) {
					result = JsonPath.parse(answer, conf).read("$", SalesOrder.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean deleteRecordByCode(String code, int objectType) {
		// TODO by the candidate
		/*
		 * This method is called when you click delete button on an edit view
		 * the code parameter is the code of (Customer - PRoduct ) or order
		 * number of Sales Order and the type is identifier of the object type
		 * and may be TYPE_PRODUCT , TYPE_CUSTOMER or TYPE_SALESORDER
		 */
		boolean result = false;
		try {
			if (TYPE_CUSTOMER == objectType) {
				WSService.wsDelete(url + "/delete/customer/", new Object[] { code });
				result = true;
			} else if (TYPE_PRODUCT == objectType) {
				WSService.wsDelete(url + "/delete/product/", new Object[] { code });
				result = true;
			} else if (TYPE_SALESORDER == objectType) {
				WSService.wsDelete(url + "/delete/salesorder/", new Object[] { code });
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	public static List<Object> listCurrentRecords(int objectType) {
		// TODO by the candidate
		/*
		 * This method is called when you open any list screen and should return
		 * all records of the specified type
		 */
		List<Object> result = new ArrayList<>();
		try {
			if (TYPE_CUSTOMER == objectType) {
				String answer = WSService.wsGet(url + "/get/all/customer/", new Object[] {});
				if (!answer.isEmpty()) {
					result.addAll(JsonPath.parse(answer, conf).read("$[*]", List.class));
				}
			} else if (TYPE_PRODUCT == objectType) {
				String answer = WSService.wsGet(url + "/get/all/product/", new Object[] {});
				if (!answer.isEmpty()) {
					result.addAll(JsonPath.parse(answer, conf).read("$[*]", List.class));
				}
			} else if (TYPE_SALESORDER == objectType) {
				String answer = WSService.wsGet(url + "/get/all/salesorder/", new Object[] {});
				if (!answer.isEmpty()) {
					result.addAll(JsonPath.parse(answer, conf).read("$", List.class));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<ComboBoxItem> listCurrentRecordRefernces(int objectType) {
		// TODO by the candidate
		/*
		 * This method is called when a Combo Box need to be initialized and
		 * should return list of ComboBoxItem which contains code and
		 * description/name for all records of specified type
		 */
		List<ComboBoxItem> result = new ArrayList<>();
		try {
			if (TYPE_CUSTOMER == objectType) {
				String answer = WSService.wsGet(url + "/get/all/customer/", new Object[] {});
				if (!answer.isEmpty()) {
					for (Object cObject : JsonPath.parse(answer, conf).read("$", List.class)) {
						LinkedHashMap customerMap = (LinkedHashMap) cObject;
						result.add(
								new ComboBoxItem((String) customerMap.get("code"), (String) customerMap.get("name")));
					}
				}
			} else if (TYPE_PRODUCT == objectType) {
				String answer = WSService.wsGet(url + "/get/all/product/", new Object[] {});
				if (!answer.isEmpty()) {
					for (Object pObject : JsonPath.parse(answer, conf).read("$", List.class)) {
						LinkedHashMap productMap = (LinkedHashMap) pObject;
						result.add(new ComboBoxItem((String) productMap.get("code"),
								(String) productMap.get("description")));
					}
				}
			} else if (TYPE_SALESORDER == objectType) {
				String answer = WSService.wsGet(url + "/get/all/salesorder/", new Object[] {});
				if (!answer.isEmpty()) {
					for (Object sObject : JsonPath.parse(answer, conf).read("$", List.class)) {
						LinkedHashMap salesOrderMap = (LinkedHashMap) sObject;
						result.add(new ComboBoxItem(String.valueOf(salesOrderMap.get("orderNumber")),
								String.valueOf(salesOrderMap.get("orderNumber"))));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static double getProductPrice(String productCode) {
		// TODO by the candidate
		/*
		 * This method is used to get unit price of product with the code passed
		 * as a parameter
		 */
		Float result = 0f;
		try {
			result = JsonPath.parse(WSService.wsGet(url + "/find/product/", new Object[] { productCode }), conf)
					.read("$.price", Float.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
