package com.dev.frontend.panels.list;

import java.util.LinkedHashMap;
import java.util.List;

import com.dev.frontend.services.Services;

public class ProductDataModel extends ListDataModel {
	private static final long serialVersionUID = 7526529951747614655L;

	public ProductDataModel() {
		super(new String[] { "Code", "Description", "Price", "Quantity" }, 0);
	}

	@Override
	public int getObjectType() {
		return Services.TYPE_PRODUCT;
	}

	@Override
	public String[][] convertRecordsListToTableModel(List<Object> list) {
		// TODO by the candidate
		/*
		 * This method use list returned by Services.listCurrentRecords and
		 * should convert it to array of rows each row is another array of
		 * columns of the row
		 */
		// String[][] sampleData = new String [][]{{"01","Product
		// 1","12.5","25"},{"02","Product 2","10","8"}};
		String[][] tableModel = new String[list.size()][4];
		int count = 0;
		for (Object pObject : list) {
			LinkedHashMap customerMap = (LinkedHashMap) pObject;
			String[] rowTable = new String[4];
			rowTable[0] = (String) customerMap.get("code");
			rowTable[1] = (String) customerMap.get("description");
			rowTable[2] = String.valueOf((Double) customerMap.get("price"));
			rowTable[3] = String.valueOf((Integer) customerMap.get("quantity"));
			tableModel[count] = rowTable;
			count++;
		}

		return tableModel;
	}
}
