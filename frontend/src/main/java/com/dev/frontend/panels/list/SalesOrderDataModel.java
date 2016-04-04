package com.dev.frontend.panels.list;

import java.util.LinkedHashMap;
import java.util.List;

import com.dev.frontend.services.Services;

public class SalesOrderDataModel extends ListDataModel {
	private static final long serialVersionUID = 7526529951747614655L;

	public SalesOrderDataModel() {
		super(new String[] { "Order Number", "Customer", "Total Price" }, 0);
	}

	@Override
	public int getObjectType() {
		return Services.TYPE_SALESORDER;
	}

	@Override
	public String[][] convertRecordsListToTableModel(List<Object> list) {
		// TODO by the candidate
		/*
		 * This method use list returned by Services.listCurrentRecords and
		 * should convert it to array of rows each row is another array of
		 * columns of the row
		 */
		// String[][] sampleData = new String[][] { { "22423", "(01)Customer 1",
		// "122.5" }, { "22424", "(02)Customer 2", "3242.5" } };
		String[][] tableModel = new String[list.size()][3];
		int count = 0;
		for (Object sObject : list) {
			LinkedHashMap customerMap = (LinkedHashMap) sObject;
			String[] rowTable = new String[4];
			rowTable[0] = String.valueOf((Integer) customerMap.get("orderNumber"));
			rowTable[1] = (String) ((LinkedHashMap) customerMap.get("customer")).get("code");
			rowTable[2] = String.valueOf((Double) customerMap.get("total"));
			tableModel[count] = rowTable;
			count++;
		}

		return tableModel;
	}
}
