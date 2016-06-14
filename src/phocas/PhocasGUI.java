package phocas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PhocasGUI extends JFrame {

	private JPanel homePanel;
	private JPanel onlineOrderPanel;
	private JTextField txtEmployeeName;
	private JTextField txtEmployeeID;
	
	public Database db;

	/**
	 * Create the frame.
	 */
	public PhocasGUI(Database db) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				db.disconnect();
			}
		});
		this.db = db;
		
		onlineOrderPanel = new OnlineOrderPanel(db);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 392);
		homePanel = new JPanel();
		homePanel.setBackground(new Color(204, 255, 255));
		homePanel.setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(homePanel);
		homePanel.setLayout(null);
		
		JButton btnMakeAnOnline = new JButton("Make an online order");
		btnMakeAnOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.removeAll();
				homePanel.setLayout(new java.awt.BorderLayout());
				homePanel.add(onlineOrderPanel);
				homePanel.revalidate();
				homePanel.repaint();
				System.out.println("clicked");
			}
		});
		btnMakeAnOnline.setBounds(121, 95, 286, 29);
		homePanel.add(btnMakeAnOnline);

		
		JLabel lblWelcomeToPhocas = new JLabel("Welcome To Phocas!");
		lblWelcomeToPhocas.setFont(new Font("Hoefler Text", Font.BOLD | Font.ITALIC, 30));
		lblWelcomeToPhocas.setBounds(161, 35, 246, 48);
		homePanel.add(lblWelcomeToPhocas);
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.setBounds(213, 197, 134, 28);
		homePanel.add(txtEmployeeName);
		txtEmployeeName.setColumns(10);
		
		txtEmployeeID = new JTextField();
		txtEmployeeID.setBounds(213, 237, 134, 28);
		homePanel.add(txtEmployeeID);
		txtEmployeeID.setColumns(10);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(80, 203, 121, 16);
		homePanel.add(lblEmployeeName);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(80, 243, 121, 16);
		homePanel.add(lblEmployeeId);
		
		
		JButton btnLogInAs = new JButton("Log in as an employee");
		btnLogInAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtEmployeeName.getText();
				String empId = txtEmployeeID.getText();
				if (!name.isEmpty() && !empId.isEmpty()) {
					Boolean empExist = db.existEmployee(name, empId);
					if (empExist) {
						Boolean empIsManager = db.isManager(empId);
						int storeId = db.getStoreIdForEmp(empId);
					    JTabbedPane employeeActionsPanel = new EmployeeActionsPanel(db, empIsManager, storeId, Integer.parseInt(empId));
					    
						homePanel.removeAll();
						homePanel.setLayout(new java.awt.BorderLayout());
						homePanel.add(employeeActionsPanel);
						homePanel.revalidate();
						homePanel.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "Employee does not exist!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please enter your name and employee id!");
				}
			}
		});
		btnLogInAs.setBounds(121, 281, 286, 29);
		homePanel.add(btnLogInAs);
		
		JLabel lblNewLabel = new JLabel("OR");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel.setBounds(237, 137, 125, 48);
		homePanel.add(lblNewLabel);
	}
	
	public void run() {
		try {
			this.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
