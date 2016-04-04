package com.dev.frontend.panels.edit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.dev.frontend.panels.model.Customer;
import com.dev.frontend.services.Services;

public class EditCustomer extends EditContentPanel {
	private static final long serialVersionUID = -8971249970444644844L;
	private JTextField txtCode = new JTextField();
	private JTextField txtName = new JTextField();
	private JTextField txtAddress = new JTextField();
	private JTextField txtPhone1 = new JTextField();
	private JTextField txtPhone2 = new JTextField();
	private JTextField txtCreditLimit = new JTextField();
	private JTextField txtCurrentCredit = new JTextField();
	private GridBagConstraints gbc_1;
	private GridBagConstraints gbc_2;
	private GridBagConstraints gbc_3;
	private GridBagConstraints gbc_4;
	private GridBagConstraints gbc_5;
	private GridBagConstraints gbc_6;
	private GridBagConstraints gbc_7;
	private GridBagConstraints gbc_8;
	private GridBagConstraints gbc_9;
	private GridBagConstraints gbc_10;

	public EditCustomer() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gridBagLayout.columnWidths = new int[] { 100, 100, 0, 80 };
		setLayout(gridBagLayout);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("Code"), gbc);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(txtCode, gbc);
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		txtCode.setColumns(10);
		txtCode.setSize(20, 80);
		gbc_1 = new GridBagConstraints();
		gbc_1.anchor = GridBagConstraints.WEST;
		gbc_1.insets = new Insets(5, 5, 5, 5);
		gbc_1.gridx = 0;
		gbc_1.gridy = 1;
		add(new JLabel("Name"), gbc_1);

		gbc_5 = new GridBagConstraints();
		gbc_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_5.insets = new Insets(5, 5, 5, 5);
		gbc_5.gridx = 1;
		gbc_5.gridy = 1;
		gbc_5.gridwidth = 3;
		gbc_5.anchor = GridBagConstraints.LAST_LINE_START;
		add(txtName, gbc_5);
		txtName.setColumns(28);
		txtName.setSize(20, 80);

		gbc_2 = new GridBagConstraints();
		gbc_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_2.anchor = GridBagConstraints.WEST;
		gbc_2.insets = new Insets(5, 5, 5, 5);
		gbc_2.gridx = 0;
		gbc_2.gridy = 2;
		add(new JLabel("Address"), gbc_2);

		gbc_6 = new GridBagConstraints();
		gbc_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_6.insets = new Insets(5, 5, 5, 5);
		gbc_6.gridx = 1;
		gbc_6.gridy = 2;
		gbc_6.gridwidth = 3;
		gbc_6.anchor = GridBagConstraints.LAST_LINE_START;
		add(txtAddress, gbc_6);
		txtAddress.setColumns(28);
		txtAddress.setSize(20, 80);

		gbc_3 = new GridBagConstraints();
		gbc_3.anchor = GridBagConstraints.WEST;
		gbc_3.insets = new Insets(5, 5, 5, 5);
		gbc_3.gridx = 0;
		gbc_3.gridy = 3;
		add(new JLabel("Phone 1"), gbc_3);

		gbc_7 = new GridBagConstraints();
		gbc_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_7.insets = new Insets(5, 5, 5, 5);
		gbc_7.gridx = 1;
		gbc_7.gridy = 3;
		gbc_7.anchor = GridBagConstraints.LAST_LINE_START;
		add(txtPhone1, gbc_7);
		txtPhone1.setColumns(10);
		txtPhone1.setSize(20, 80);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 2;
		gbc.gridy = 3;
		add(new JLabel("Phone 2"), gbc);

		gbc_8 = new GridBagConstraints();
		gbc_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_8.insets = new Insets(5, 5, 5, 15);
		gbc_8.gridx = 3;
		gbc_8.gridy = 3;
		gbc_8.anchor = GridBagConstraints.LAST_LINE_START;
		add(txtPhone2, gbc_8);
		txtPhone2.setColumns(10);
		txtPhone2.setSize(20, 80);

		gbc_4 = new GridBagConstraints();
		gbc_4.anchor = GridBagConstraints.WEST;
		gbc_4.insets = new Insets(5, 5, 5, 5);
		gbc_4.gridx = 0;
		gbc_4.gridy = 4;
		add(new JLabel("Credit Limit"), gbc_4);

		gbc_9 = new GridBagConstraints();
		gbc_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_9.insets = new Insets(5, 5, 5, 5);
		gbc_9.gridx = 1;
		gbc_9.gridy = 4;
		gbc_9.anchor = GridBagConstraints.LAST_LINE_START;
		add(txtCreditLimit, gbc_9);
		txtCreditLimit.setColumns(10);
		txtCreditLimit.setSize(20, 80);

		gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 2;
		gbc.gridy = 4;
		add(new JLabel("Current Credit"), gbc);

		gbc_10 = new GridBagConstraints();
		gbc_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_10.insets = new Insets(5, 5, 5, 15);
		gbc_10.gridx = 3;
		gbc_10.gridy = 4;
		gbc_10.anchor = GridBagConstraints.LAST_LINE_START;
		add(txtCurrentCredit, gbc_10);
		txtCurrentCredit.setColumns(10);
		txtCurrentCredit.setSize(20, 40);
		txtCurrentCredit.setEditable(false);

	}

	public boolean bindToGUI(Object o) {
		// TODO by the candidate
		/*
		 * This method use the object returned by Services.readRecordByCode and
		 * should map it to screen widgets
		 */
		if (o != null) {
			Customer customer = (Customer) o;
			txtCode.setText(customer.getCode());
			txtName.setText(customer.getName());
			txtPhone1.setText(customer.getPhone());
			txtPhone2.setText(customer.getPhone2());
			txtAddress.setText(customer.getAddress());
			if (customer.getCreditLimit() != null) {
				txtCreditLimit.setText(customer.getCreditLimit().toString());
			} else {
				txtCreditLimit.setText("0.0");
			}
			if (customer.getCredit() != null) {
				txtCurrentCredit.setText(customer.getCredit().toString());
			} else {
				txtCurrentCredit.setText("0.0");
			}
			return true;
		} else {
			return false;
		}
	}

	public Object guiToObject() {
		// TODO by the candidate
		/*
		 * This method collect values from screen widgets and convert them to
		 * object of your type This object will be used as a parameter of method
		 * Services.save
		 */
		Customer customer = new Customer();
		customer.setCode(txtCode.getText());
		customer.setName(txtName.getText());
		customer.setPhone(txtPhone1.getText());
		customer.setPhone2(txtPhone2.getText());
		customer.setAddress(txtAddress.getText());
		if (!txtCreditLimit.getText().isEmpty()) {
			customer.setCreditLimit(Float.valueOf(txtCreditLimit.getText()));
		}
		if (!txtCurrentCredit.getText().isEmpty()) {
			customer.setCredit(Float.valueOf(txtCurrentCredit.getText()));
		}
		return customer;
	}

	@Override
	public int getObjectType() {
		return Services.TYPE_CUSTOMER;
	}

	@Override
	public String getCurrentCode() {
		return txtCode.getText();
	}

	public void clear() {
		txtCode.setText("");
		txtName.setText("");
		txtPhone1.setText("");
		txtPhone2.setText("");
		txtAddress.setText("");
		txtCreditLimit.setText("");
		txtCurrentCredit.setText("");
	}

	public void onInit() {

	}
}
