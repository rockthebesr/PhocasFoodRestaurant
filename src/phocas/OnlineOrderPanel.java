package phocas;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JTextArea;

public class OnlineOrderPanel extends JPanel {
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public OnlineOrderPanel(Database d) {
		final Database db = d;
		setBackground(new Color(240, 248, 255));
		setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblOnlineOrder = new JLabel("Make An Online Order");
		lblOnlineOrder.setBounds(162, 41, 159, 16);
		add(lblOnlineOrder);
		
		textField_1 = new JTextField();
		textField_1.setBounds(187, 97, 317, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(28, 160, 61, 16);
		add(lblItems);
		
		textField_2 = new JTextField();
		textField_2.setBounds(187, 125, 317, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblStoreId = new JLabel("store ID");
		lblStoreId.setBounds(28, 131, 61, 16);
		add(lblStoreId);
		
		textField_3 = new JTextField();
		textField_3.setBounds(187, 154, 317, 28);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblYourName = new JLabel("Your name");
		lblYourName.setBounds(28, 195, 121, 16);
		add(lblYourName);
		
		textField_4 = new JTextField();
		textField_4.setBounds(187, 189, 317, 28);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(28, 224, 61, 16);
		add(lblAddress);
		
		textField_5 = new JTextField();
		textField_5.setBounds(187, 218, 317, 28);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("phone number");
		lblPhoneNumber.setBounds(28, 254, 110, 16);
		add(lblPhoneNumber);
		
		textField_6 = new JTextField();
		textField_6.setBounds(187, 248, 317, 28);
		add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnOrder = new JButton("ORDER!");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empID = textField_1.getText();
				String storeID = textField_2.getText();
				String items = textField_3.getText();
				String name = textField_4.getText();
				String address = textField_5.getText();
				String phone = textField_6.getText();
				if (empID.isEmpty() 
						|| storeID.isEmpty()
						||items.isEmpty()
						||name.isEmpty()
						||address.isEmpty()
						||phone.isEmpty()
						) {
					JOptionPane.showMessageDialog(null, "At least one of the information is empty!");
				} else {
					try {
						db.makeOnlineOrder(items, Integer.parseInt(storeID), Integer.parseInt(empID), address, name, Integer.parseInt(phone));
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "store ID, employee ID and phone should be only numbers!");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "failed to add order");
					}
				}
			}
		});
		btnOrder.setBounds(204, 295, 117, 29);
		add(btnOrder);
		
		JLabel lblEmpId = new JLabel("emp ID");
		lblEmpId.setBounds(28, 103, 61, 16);
		add(lblEmpId);

	}
}
