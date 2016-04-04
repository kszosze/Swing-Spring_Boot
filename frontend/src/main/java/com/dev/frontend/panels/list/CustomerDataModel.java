package com.dev.frontend.panels.list;

import java.util.LinkedHashMap;
import java.util.List;

import com.dev.frontend.services.Services;

public class CustomerDataModel extends ListDataModel {
	private static final long serialVersionUID = 7526529951747613655L;

	public CustomerDataModel() {
		super(new String[] { "Code", "Name", "Phone", "Current Credit" }, 0);
	}

	@Override
	public int getObjectType() {
		return Services.TYPE_CUSTOMER;
	}

	@Override
	public String[][] convertRecordsListToTableModel(List<Object> list) {
		// TODO by the candidate
		/*
		 * This method use list returned by Services.listCurrentRecords and
		 * should convert it to array of rows each row is another array of
		 * columns of the row
		 */
		// String[][] sampleData = new String [][]{{"01","Customer
		// 1","+201011121314","23.4"},{"02","Customer
		// 2","+201112131415","1.4"}};
		String[][] tableModel = new String[list.size()][3];
		int count = 0;
		for (Object cObject : list) {
			LinkedHashMap customerMap = (LinkedHashMap) cObject;
			String[] rowTable = new String[4];
			rowTable[0] = (String) customerMap.get("code");
			rowTable[1] = (String) customerMap.get("name");
			rowTable[2] = (String) customerMap.get("phone");
			rowTable[3] = String.valueOf((Double) customerMap.get("credit"));
			tableModel[count] = rowTable;
			count++;
		}

		return tableModel;
	}
}
