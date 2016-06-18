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
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeActionsPanel extends JTabbedPane {
	
	private JTextField txtItemName_1;
	private JTextField txtInstoreOrderId;
	private JTextField txtOnlineOrderId;
	private JTextField txtOrderId;
	private JTextField txtNewOrderStatus;
	private JTextField txtDeliveryId;
	private JTextField txtItemName;
	private JTextField txtPrice;
	private JTextField txtStoreId;
	private JTextField txtCity;
	private JTextField txtProvince;
	private JTextField txtLocation;
	private JTextField txtEmployeeName;
	private JTextField txtGender;
	private JTextField txtEmployeeid;
	private JTextField txtManagerId_1;
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
	private JTextField txtMenuId;
	private JTextField txtManagerId;
    private JTextField txtRegularEmployeeToDelete;
    private JButton btnDeleteEmployee;


	/**
	 * Create the panel.
	 */
	public EmployeeActionsPanel(final Database db, Boolean isManager,final int storeID,final int empID) {
		
		JPanel orderPanel = new JPanel();
		orderPanel.setBackground(new Color(240, 248, 255));
		addTab("Orders", null, orderPanel, null);
		orderPanel.setLayout(null);
		
		txtItemName_1 = new JTextField();
		txtItemName_1.setText("item name");
		txtItemName_1.setToolTipText("item name");
		txtItemName_1.setBounds(227, 5, 239, 28);
		orderPanel.add(txtItemName_1);
		txtItemName_1.setColumns(10);
		
		JButton btnAddNewInstore = new JButton("Add new in-store order");
		btnAddNewInstore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = txtItemName_1.getText();
				try {
					db.makeInStoreOrder(item, storeID, empID);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Failed to add order");
				}
				
			}
		});
		btnAddNewInstore.setBounds(6, 6, 196, 29);
		orderPanel.add(btnAddNewInstore);
		
		JButton btnFulfillOrder = new JButton("Fulfill in-store order");
		btnFulfillOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderId = txtInstoreOrderId.getText();
				try {
					db.fulfillInStoreOrder(Integer.parseInt(orderId));
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "order id can only be numbers");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "order was not fulfilled successfully");
				}
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
		btnCancelInstoreOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderId = txtInstoreOrderId.getText();
				try {
					db.cancelInStoreOrder(Integer.parseInt(orderId));
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "order id can only be numbers");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "order was not cancelled successfully");
				}
			}
		});
		btnCancelInstoreOrder.setBounds(251, 70, 196, 29);
		orderPanel.add(btnCancelInstoreOrder);
		
		JButton btnFulfillOnlineOrder = new JButton("Fulfill online order");
		btnFulfillOnlineOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderId = txtOnlineOrderId.getText();
				try {
					db.fulfillOnlineOrder(Integer.parseInt(orderId));
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "order id can only be numbers");
				}
			}
		});
		btnFulfillOnlineOrder.setBounds(251, 111, 196, 29);
		orderPanel.add(btnFulfillOnlineOrder);
		
		txtOnlineOrderId = new JTextField();
		txtOnlineOrderId.setText("online order id");
		txtOnlineOrderId.setBounds(6, 110, 239, 28);
		orderPanel.add(txtOnlineOrderId);
		txtOnlineOrderId.setColumns(10);
		
		JButton btnCancelOnlineOrder = new JButton("Cancel online order");
		btnCancelOnlineOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderId = txtOnlineOrderId.getText();
				try {
					db.cancelInStoreOrder(Integer.parseInt(orderId));
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "order id can only be numbers");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "declined");
				}
			}
		});
		btnCancelOnlineOrder.setBounds(251, 136, 196, 29);
		orderPanel.add(btnCancelOnlineOrder);
		
		txtOrderId = new JTextField();
		txtOrderId.setText("order id");
		txtOrderId.setBounds(6, 176, 237, 28);
		orderPanel.add(txtOrderId);
		txtOrderId.setColumns(10);
		
		JButton btnNewButton = new JButton("Check order status");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderId = txtOrderId.getText();
				db.checkOrderStatus(Integer.parseInt(orderId));
			}
		});
		btnNewButton.setBounds(251, 177, 196, 29);
		orderPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update order status");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String orderId = txtOrderId.getText();
				String newStatus = txtNewOrderStatus.getText();
				try {
					db.updateOrderStatus(Integer.parseInt(orderId), newStatus);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "order id must be a number");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "declined");
				}
			}
		});
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
		btnFulfillDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String did = txtDeliveryId.getText();
				db.fulfillDelivery(Integer.parseInt(did));
			}
		});
		btnFulfillDelivery.setBounds(189, 128, 117, 29);
		deliveryPanel.add(btnFulfillDelivery);
		
		JPanel addPanel = new JPanel();
		addPanel.setBackground(new Color(240, 248, 255));
		if (isManager) {
			addTab("Add/Delete", null, addPanel, null);
			addPanel.setLayout(null);
		}
		
		txtItemName = new JTextField();
		txtItemName.setText("item name");
		txtItemName.setBounds(6, 6, 206, 28);
		addPanel.add(txtItemName);
		txtItemName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setText("price");
		txtPrice.setBounds(224, 6, 60, 28);
		addPanel.add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnAddItem = new JButton("add item");
		btnAddItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = txtItemName.getText();
                String price = txtPrice.getText();
                db.addItem(item, Double.parseDouble(price));
            }
        });
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
		txtLocation.setBounds(224, 46, 134, 28);
		addPanel.add(txtLocation);
		txtLocation.setColumns(10);
		
		JButton btnAddStore = new JButton("add store");
		btnAddStore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String storeId = txtStoreId.getText();
                String city = txtStoreId.getText();
                String province = txtProvince.getText();
                String location = txtLocation.getText();
                String menuId = txtMenuId.getText();
                String managerId = txtManagerId.getText();
                db.addStore(Integer.parseInt(storeId), city, province, location, Integer.parseInt(managerId), Integer.parseInt(menuId));
            }
        });
		btnAddStore.setBounds(428, 72, 117, 29);
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
		btnAddNewEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtEmployeeName.getText();
                String gender = txtGender.getText();
                int empID = Integer.parseInt(txtEmployeeid.getText());
                int managerID = Integer.parseInt(txtManagerId_1.getText());
                int storeID = Integer.parseInt(txtStoreId_1.getText());
                db.addRegularEmployee(name, gender, storeID, managerID, empID);
            }
        });
		btnAddNewEmployee.setBounds(351, 141, 188, 29);
		addPanel.add(btnAddNewEmployee);
		
		txtManagerId_1 = new JTextField();
		txtManagerId_1.setText("manager ID");
		txtManagerId_1.setBounds(16, 141, 134, 28);
		addPanel.add(txtManagerId_1);
		txtManagerId_1.setColumns(10);
		
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
		btnAddMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int menuID = Integer.parseInt(txtMenuId_1.getText());
                int serveStartTime = Integer.parseInt(txtServeStartTime.getText());
                int serveEndTime = Integer.parseInt(txtServeEndTime.getText());
                db.addMenu(menuID, serveStartTime, serveEndTime);
            }
        });
		btnAddMenu.setBounds(422, 182, 117, 29);
		addPanel.add(btnAddMenu);
		
		txtMenuIdToDelete = new JTextField();
		txtMenuIdToDelete.setText("menu ID to delete");
		txtMenuIdToDelete.setBounds(13, 212, 134, 28);
		addPanel.add(txtMenuIdToDelete);
		txtMenuIdToDelete.setColumns(10);
		
		btnDeleteMenu = new JButton("delete menu");
		btnDeleteMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int menuID = Integer.parseInt(txtMenuIdToDelete.getText());
				db.deleteMenu(menuID);
			}
		});
		btnDeleteMenu.setBounds(170, 213, 117, 29);
		addPanel.add(btnDeleteMenu);

        	txtRegularEmployeeToDelete = new JTextField();
        	txtRegularEmployeeToDelete.setText("condition to delete");
        	txtRegularEmployeeToDelete.setBounds(13, 245, 180, 28);
        	addPanel.add(txtRegularEmployeeToDelete);
        	txtRegularEmployeeToDelete.setColumns(10);

        	btnDeleteEmployee = new JButton("delete employee");
        	btnDeleteEmployee.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
                	String str = txtRegularEmployeeToDelete.getText();
                	db.deleteEmployee(str);
            	}
        	});
		btnDeleteEmployee.setBounds(230, 245, 180, 29);
        	addPanel.add(btnDeleteEmployee);
		
		txtMenuId = new JTextField();
		txtMenuId.setText("menu ID");
		txtMenuId.setBounds(368, 46, 68, 28);
		addPanel.add(txtMenuId);
		txtMenuId.setColumns(10);
		
		txtManagerId = new JTextField();
		txtManagerId.setText("manager id");
		txtManagerId.setBounds(448, 46, 91, 28);
		addPanel.add(txtManagerId);
		txtManagerId.setColumns(10);
		
		AdditionalInfoPanel = new JPanel();
		AdditionalInfoPanel.setBackground(new Color(240, 248, 255));
		if (isManager) {
			addTab("Additional Info", null, AdditionalInfoPanel, null);
			AdditionalInfoPanel.setLayout(null);
		}
		
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
		
		JButton btnRunQuery = new JButton("run");
		btnRunQuery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String attributes = textField.getText();
				String tables = textField_1.getText();
				String condition = textField_2.getText();
				//String q = "select " + attributes + " from " + tables + " where " + condition;
				ResultSet rs = db.generalQuery(attributes, tables, condition);
				String s = db.resultSetToString(rs);
				JOptionPane.showMessageDialog(null, s);
			}
		});
		btnRunQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String attributes = textField.getText();
				String tables = textField_1.getText();
				String condition = textField_2.getText();
				//String q = "select " + attributes + " from " + tables + " where " + condition;
				ResultSet rs = db.generalQuery(attributes, tables, condition);
				String s = db.resultSetToString(rs);
				JOptionPane.showMessageDialog(null, s);
			}
		});
		btnRunQuery.setBounds(181, 26, 117, 29);
		AdditionalInfoPanel.add(btnRunQuery);
		
		btnFindAllEmployee = new JButton("Find all employee ids working at this store");
		btnFindAllEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.findEmpIDForStore(Integer.parseInt(txtStoreId_2.getText()));
				String q = db.resultSetToString(rs);
				JOptionPane.showMessageDialog(null, q);
			}
		});
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
				String s = db.resultSetToString(rs);
				JOptionPane.showMessageDialog(null, s);
			}
		});
		btnFindTheStores.setBounds(6, 98, 292, 29);
		AdditionalInfoPanel.add(btnFindTheStores);
		
		btnSalesOfEach = new JButton("Sales of each store");
		btnSalesOfEach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.SaleOfEachStore();
				String q = db.resultSetToString(rs);
				JOptionPane.showMessageDialog(null, q);
			}
		});
		btnSalesOfEach.setBounds(333, 98, 206, 29);
		AdditionalInfoPanel.add(btnSalesOfEach);
		
		btnSalesOfEach_1 = new JButton("Sales of each province");
		btnSalesOfEach_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.SaleOfEachProvince();
				String q = db.resultSetToString(rs);
				JOptionPane.showMessageDialog(null, q);
			}
		});
		btnSalesOfEach_1.setBounds(333, 128, 206, 29);
		AdditionalInfoPanel.add(btnSalesOfEach_1);
		
		btnMostExpensiveItem = new JButton("Most expensive item");
		btnMostExpensiveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.MaxMinPrice("max");
				String q = db.resultSetToString(rs);
				JOptionPane.showMessageDialog(null, q);
			}
		});
		btnMostExpensiveItem.setBounds(6, 128, 158, 29);
		AdditionalInfoPanel.add(btnMostExpensiveItem);
		
		btnLeastExpensiveItem = new JButton("Least expensive item");
		btnLeastExpensiveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = db.MaxMinPrice("min");
				String q = db.resultSetToString(rs);
				JOptionPane.showMessageDialog(null, q);
			}
		});
		btnLeastExpensiveItem.setBounds(161, 128, 167, 29);
		AdditionalInfoPanel.add(btnLeastExpensiveItem);

	}

	
}
