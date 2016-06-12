package phocas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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

import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;

public class PhocasGUI extends JFrame {

	private JPanel homePanel;
	private JPanel onlineOrderPanel;
	private JTabbedPane managerActionsPanel;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhocasGUI frame = new PhocasGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PhocasGUI() {
		
		onlineOrderPanel = new OnlineOrderPanel();
		managerActionsPanel = new EmployeeActionsPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 360);
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
		btnMakeAnOnline.setBounds(80, 95, 286, 29);
		homePanel.add(btnMakeAnOnline);
		
		JButton btnLogInAs = new JButton("Log in as an employee");
		btnLogInAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePanel.removeAll();
				homePanel.setLayout(new java.awt.BorderLayout());
				homePanel.add(managerActionsPanel);
				homePanel.revalidate();
				homePanel.repaint();
				System.out.println("clicked");
			}
		});
		btnLogInAs.setBounds(80, 277, 286, 29);
		homePanel.add(btnLogInAs);
		
		JLabel lblWelcomeToPhocas = new JLabel("Welcome To Phocas!");
		lblWelcomeToPhocas.setFont(new Font("Hoefler Text", Font.BOLD | Font.ITALIC, 30));
		lblWelcomeToPhocas.setBounds(101, 35, 246, 48);
		homePanel.add(lblWelcomeToPhocas);
		
		textField = new JTextField();
		textField.setBounds(213, 197, 134, 28);
		homePanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(213, 237, 134, 28);
		homePanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setBounds(80, 203, 121, 16);
		homePanel.add(lblEmployeeName);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setBounds(80, 243, 121, 16);
		homePanel.add(lblEmployeeId);
		
		JLabel lblNewLabel = new JLabel("OR");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel.setBounds(213, 121, 125, 48);
		homePanel.add(lblNewLabel);
	}
}
