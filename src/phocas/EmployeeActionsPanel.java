package phocas;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;

import java.awt.Color;
import java.sql.ResultSet;

public class EmployeeActionsPanel extends JTabbedPane {
	
	private JTextField txtItemNames;
	private JTextField txtInstoreOrderId;
	private JTextField txtOnlineOrderId;
	private JTextField txtOrderId;
	private JTextField txtNewOrderStatus;
	private JTextField txtDeliveryId;
	private JTextField txtMenuId;
	private JTextField txtItemName;
	private JTextField txtPrice;
	private JTextField txtStoreId;
	private JTextField txtCity;
	private JTextField txtProvince;
	private JTextField txtLocation;
	private JTextField txtEmployeeName;
	private JTextField txtGender;
	private JTextField txtEmployeeid;
	private JTextField txtManagerid;
	private JTextField txtStoreId_1;
	private JPanel AdditionalInfoPanel;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnFindAllEmployee;
	private JTextField txtStoreId_2;
	private JButton btnFindTheStores;
	private JTextField txtMenuId_1;
	private JTextField txtServeStartTime;
	private JTextField txtServeEndTime;
	private JButton btnAddMenu;
	private JTextField txtMenuIdToDelete;
	private JButton btnDeleteMenu;
	private JButton btnSalesOfEach;
	private JButton btnSalesOfEach_1;
	private JButton btnMostExpensiveItem;
	private JButton btnLeastExpensiveItem;

	/**
	 * Create the panel.
	 */
	public EmployeeActionsPanel(Database db, Boolean isManager, int storeID, int empID) {
		
		JPanel orderPanel = new JPanel();
		orderPanel.setBackground(new Color(240, 248, 255));
		addTab("Orders", null, orderPanel, null);
		orderPanel.setLayout(null);
		
		txtItemNames = new JTextField();
		txtItemNames.setText("item names");
		txtItemNames.setToolTipText("itemNames");
		txtItemNames.setBounds(227, 5, 239, 28);
		orderPanel.add(txtItemNames);
		txtItemNames.setColumns(10);
		
		JButton btnAddNewInstore = new JButton("Add new in-store order");
		btnAddNewInstore.setBounds(6, 6, 196, 29);
		orderPanel.add(btnAddNewInstore);
		
		JButton btnFulfillOrder = new JButton("Fulfill in-store order");
		btnFulfillOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFulfillOrder.setBounds(251, 45, 196, 29);
		orderPanel.add(btnFulfillOrder);
		
		txtInstoreOrderId = new JTextField();
		txtInstoreOrderId.setText("in-store order id");
		txtInstoreOrderId.setBounds(6, 45, 239, 28);
		orderPanel.add(txtInstoreOrderId);
		txtInstoreOrderId.setColumns(10);
		
		JButton btnCancelInstoreOrder = new JButton("Cancel in-store order");
		btnCancelInstoreOrder.setBounds(251, 70, 196, 29);
		orderPanel.add(btnCancelInstoreOrder);
		
		JButton btnFulfillOnlineOrder = new JButton("Fulfill online order");
		btnFulfillOnlineOrder.setBounds(251, 111, 196, 29);
		orderPanel.add(btnFulfillOnlineOrder);
		
		txtOnlineOrderId = new JTextField();
		txtOnlineOrderId.setText("online order id");
		txtOnlineOrderId.setBounds(6, 110, 239, 28);
		orderPanel.add(txtOnlineOrderId);
		txtOnlineOrderId.setColumns(10);
		
		JButton btnCancelOnlineOrder = new JButton("Cancel online order");
		btnCancelOnlineOrder.setBounds(251, 136, 196, 29);
		orderPanel.add(btnCancelOnlineOrder);
		
		txtOrderId = new JTextField();
		txtOrderId.setText("order id");
		txtOrderId.setBounds(6, 176, 237, 28);
		orderPanel.add(txtOrderId);
		txtOrderId.setColumns(10);
		
		JButton btnNewButton = new JButton("Check order status");
		btnNewButton.setBounds(251, 177, 196, 29);
		orderPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update order status");
		btnNewButton_1.setBounds(250, 203, 197, 29);
		orderPanel.add(btnNewButton_1);
		
		txtNewOrderStatus = new JTextField();
		txtNewOrderStatus.setText("New order status");
		txtNewOrderStatus.setBounds(6, 202, 239, 28);
		orderPanel.add(txtNewOrderStatus);
		txtNewOrderStatus.setColumns(10);
		
		JPanel deliveryPanel = new JPanel();
		deliveryPanel.setBackground(new Color(240, 248, 255));
		addTab("Delivery", null, deliveryPanel, null);
		deliveryPanel.setLayout(null);
		
		txtDeliveryId = new JTextField();
		txtDeliveryId.setText("delivery id");
		txtDeliveryId.setBounds(149, 88, 205, 28);
		deliveryPanel.add(txtDeliveryId);
		txtDeliveryId.setColumns(10);
		
		JButton btnFulfillDelivery = new JButton("Fulfill delivery");
		btnFulfillDelivery.setBounds(189, 128, 117, 29);
		deliveryPanel.add(btnFulfillDelivery);
		
		JPanel addPanel = new JPanel();
		addPanel.setBackground(new Color(240, 248, 255));
		//TODO
		//if (isManager) {
			addTab("Add/Delete", null, addPanel, null);
			addPanel.setLayout(null);
		//}
		
		txtMenuId = new JTextField();
		txtMenuId.setBounds(6, 6, 92, 28);
		txtMenuId.setText("menu id");
		addPanel.add(txtMenuId);
		txtMenuId.setColumns(10);
		
		txtItemName = new JTextField();
		txtItemName.setText("item name");
		txtItemName.setBounds(110, 6, 176, 28);
		addPanel.add(txtItemName);
		txtItemName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setText("price");
		txtPrice.setBounds(298, 6, 60, 28);
		addPanel.add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnAddItem = new JButton("add item");
		btnAddItem.setBounds(422, 7, 117, 29);
		addPanel.add(btnAddItem);
		
		txtStoreId = new JTextField();
		txtStoreId.setText("store id");
		txtStoreId.setBounds(6, 46, 68, 28);
		addPanel.add(txtStoreId);
		txtStoreId.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setText("city");
		txtCity.setBounds(75, 46, 72, 28);
		addPanel.add(txtCity);
		txtCity.setColumns(10);
		
		txtProvince = new JTextField();
		txtProvince.setText("Province");
		txtProvince.setBounds(149, 46, 72, 28);
		addPanel.add(txtProvince);
		txtProvince.setColumns(10);
		
		txtLocation = new JTextField();
		txtLocation.setText("Location");
		txtLocation.setBounds(224, 46, 154, 28);
		addPanel.add(txtLocation);
		txtLocation.setColumns(10);
		
		JButton btnAddStore = new JButton("add store");
		btnAddStore.setBounds(422, 47, 117, 29);
		addPanel.add(btnAddStore);
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.setText("employee name");
		txtEmployeeName.setBounds(6, 101, 232, 28);
		addPanel.add(txtEmployeeName);
		txtEmployeeName.setColumns(10);
		
		txtGender = new JTextField();
		txtGender.setText("gender");
		txtGender.setBounds(250, 101, 60, 28);
		addPanel.add(txtGender);
		txtGender.setColumns(10);
		
		txtEmployeeid = new JTextField();
		txtEmployeeid.setText("employeeID");
		txtEmployeeid.setBounds(336, 101, 134, 28);
		addPanel.add(txtEmployeeid);
		txtEmployeeid.setColumns(10);
		
		JButton btnAddNewEmployee = new JButton("Add new employee");
		btnAddNewEmployee.setBounds(351, 141, 188, 29);
		addPanel.add(btnAddNewEmployee);
		
		txtManagerid = new JTextField();
		txtManagerid.setText("manager ID");
		txtManagerid.setBounds(16, 141, 134, 28);
		addPanel.add(txtManagerid);
		txtManagerid.setColumns(10);
		
		txtStoreId_1 = new JTextField();
		txtStoreId_1.setText("store ID");
		txtStoreId_1.setBounds(170, 141, 134, 28);
		addPanel.add(txtStoreId_1);
		txtStoreId_1.setColumns(10);
		
		txtMenuId_1 = new JTextField();
		txtMenuId_1.setText("menu ID");
		txtMenuId_1.setBounds(6, 181, 77, 28);
		addPanel.add(txtMenuId_1);
		txtMenuId_1.setColumns(10);
		
		txtServeStartTime = new JTextField();
		txtServeStartTime.setText("serve start time");
		txtServeStartTime.setBounds(87, 181, 134, 28);
		addPanel.add(txtServeStartTime);
		txtServeStartTime.setColumns(10);
		
		txtServeEndTime = new JTextField();
		txtServeEndTime.setText("serve end time");
		txtServeEndTime.setBounds(224, 181, 134, 28);
		addPanel.add(txtServeEndTime);
		txtServeEndTime.setColumns(10);
		
		btnAddMenu = new JButton("add menu");
		btnAddMenu.setBounds(422, 182, 117, 29);
		addPanel.add(btnAddMenu);
		
		txtMenuIdToDelete = new JTextField();
		txtMenuIdToDelete.setText("menu ID to delete");
		txtMenuIdToDelete.setBounds(13, 212, 134, 28);
		addPanel.add(txtMenuIdToDelete);
		txtMenuIdToDelete.setColumns(10);
		
		btnDeleteMenu = new JButton("delete menu");
		btnDeleteMenu.setBounds(170, 213, 117, 29);
		addPanel.add(btnDeleteMenu);
		
		AdditionalInfoPanel = new JPanel();
		AdditionalInfoPanel.setBackground(new Color(240, 248, 255));
		//TODO
		//if (isManager) {
			addTab("Additional Info", null, AdditionalInfoPanel, null);
			AdditionalInfoPanel.setLayout(null);
		//}
		
		JLabel lblSelect = new JLabel("find");
		lblSelect.setBounds(6, 6, 43, 16);
		AdditionalInfoPanel.add(lblSelect);
		
		textField = new JTextField();
		textField.setBounds(38, 0, 49, 28);
		AdditionalInfoPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblFrom = new JLabel("from");
		lblFrom.setBounds(99, 6, 36, 16);
		AdditionalInfoPanel.add(lblFrom);
		
		textField_1 = new JTextField();
		textField_1.setBounds(135, 0, 93, 28);
		AdditionalInfoPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblWhere = new JLabel("where");
		lblWhere.setBounds(237, 6, 61, 16);
		AdditionalInfoPanel.add(lblWhere);
		
		textField_2 = new JTextField();
		textField_2.setBounds(285, 0, 254, 28);
		AdditionalInfoPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnRun = new JButton("run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String attributes = textField.getText();
				String tables = textField_1.getText();
				String condition = textField_1.getText();
				JOptionPane.showMessageDialog(null, attributes + tables + condition);
			}
		});
		btnRun.setBounds(192, 25, 117, 29);
		AdditionalInfoPanel.add(btnRun);
		
		btnFindAllEmployee = new JButton("Find all employee ids working at this store");
		btnFindAllEmployee.setBounds(6, 67, 292, 29);
		AdditionalInfoPanel.add(btnFindAllEmployee);
		
		txtStoreId_2 = new JTextField();
		txtStoreId_2.setText("store ID");
		txtStoreId_2.setBounds(309, 66, 134, 28);
		AdditionalInfoPanel.add(txtStoreId_2);
		txtStoreId_2.setColumns(10);
		
		btnFindTheStores = new JButton("Find the stores that has all the menus");
		btnFindTheStores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.findStoreAllMenu();
				String s = db.ResultSetToString(rs);
				JOptionPane.showMessageDialog(null, s);
			}
		});
		btnFindTheStores.setBounds(6, 98, 292, 29);
		AdditionalInfoPanel.add(btnFindTheStores);
		
		btnSalesOfEach = new JButton("Sales of each store");
		btnSalesOfEach.setBounds(333, 98, 206, 29);
		AdditionalInfoPanel.add(btnSalesOfEach);
		
		btnSalesOfEach_1 = new JButton("Sales of each province");
		btnSalesOfEach_1.setBounds(333, 128, 206, 29);
		AdditionalInfoPanel.add(btnSalesOfEach_1);
		
		btnMostExpensiveItem = new JButton("Most expensive item");
		btnMostExpensiveItem.setBounds(6, 128, 158, 29);
		AdditionalInfoPanel.add(btnMostExpensiveItem);
		
		btnLeastExpensiveItem = new JButton("Least expensive item");
		btnLeastExpensiveItem.setBounds(161, 128, 167, 29);
		AdditionalInfoPanel.add(btnLeastExpensiveItem);

	}
	
}
