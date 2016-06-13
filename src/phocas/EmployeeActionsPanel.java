package phocas;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

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

	/**
	 * Create the panel.
	 */
	public EmployeeActionsPanel() {
		
		JPanel panel = new JPanel();
		addTab("Orders", null, panel, null);
		panel.setLayout(null);
		
		txtItemNames = new JTextField();
		txtItemNames.setText("item names");
		txtItemNames.setToolTipText("itemNames");
		txtItemNames.setBounds(227, 5, 239, 28);
		panel.add(txtItemNames);
		txtItemNames.setColumns(10);
		
		JButton btnAddNewInstore = new JButton("Add new in-store order");
		btnAddNewInstore.setBounds(6, 6, 196, 29);
		panel.add(btnAddNewInstore);
		
		JButton btnFulfillOrder = new JButton("Fulfill in-store order");
		btnFulfillOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFulfillOrder.setBounds(251, 45, 196, 29);
		panel.add(btnFulfillOrder);
		
		txtInstoreOrderId = new JTextField();
		txtInstoreOrderId.setText("in-store order id");
		txtInstoreOrderId.setBounds(6, 45, 239, 28);
		panel.add(txtInstoreOrderId);
		txtInstoreOrderId.setColumns(10);
		
		JButton btnCancelInstoreOrder = new JButton("Cancel in-store order");
		btnCancelInstoreOrder.setBounds(251, 70, 196, 29);
		panel.add(btnCancelInstoreOrder);
		
		JButton btnFulfillOnlineOrder = new JButton("Fulfill online order");
		btnFulfillOnlineOrder.setBounds(251, 111, 196, 29);
		panel.add(btnFulfillOnlineOrder);
		
		txtOnlineOrderId = new JTextField();
		txtOnlineOrderId.setText("online order id");
		txtOnlineOrderId.setBounds(6, 110, 239, 28);
		panel.add(txtOnlineOrderId);
		txtOnlineOrderId.setColumns(10);
		
		JButton btnCancelOnlineOrder = new JButton("Cancel online order");
		btnCancelOnlineOrder.setBounds(251, 136, 196, 29);
		panel.add(btnCancelOnlineOrder);
		
		txtOrderId = new JTextField();
		txtOrderId.setText("order id");
		txtOrderId.setBounds(6, 176, 237, 28);
		panel.add(txtOrderId);
		txtOrderId.setColumns(10);
		
		JButton btnNewButton = new JButton("Check order status");
		btnNewButton.setBounds(251, 177, 196, 29);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update order status");
		btnNewButton_1.setBounds(250, 203, 197, 29);
		panel.add(btnNewButton_1);
		
		txtNewOrderStatus = new JTextField();
		txtNewOrderStatus.setText("New order status");
		txtNewOrderStatus.setBounds(6, 202, 239, 28);
		panel.add(txtNewOrderStatus);
		txtNewOrderStatus.setColumns(10);
		
		JPanel deliveryPanel = new JPanel();
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
		addTab("Add", null, addPanel, null);
		addPanel.setLayout(null);
		
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
		btnAddItem.setBounds(376, 7, 117, 29);
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
		btnAddStore.setBounds(376, 48, 117, 29);
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
		btnAddNewEmployee.setBounds(170, 177, 188, 29);
		addPanel.add(btnAddNewEmployee);
		
		txtManagerid = new JTextField();
		txtManagerid.setText("manager ID");
		txtManagerid.setBounds(69, 141, 134, 28);
		addPanel.add(txtManagerid);
		txtManagerid.setColumns(10);
		
		txtStoreId_1 = new JTextField();
		txtStoreId_1.setText("store ID");
		txtStoreId_1.setBounds(244, 141, 134, 28);
		addPanel.add(txtStoreId_1);
		txtStoreId_1.setColumns(10);

	}
}