package phocas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OnlineOrderPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public OnlineOrderPanel() {
		setBorder(new MatteBorder(0, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("City");
		lblNewLabel.setBounds(28, 75, 61, 16);
		add(lblNewLabel);
		
		JLabel lblOnlineOrder = new JLabel("Make An Online Order");
		lblOnlineOrder.setBounds(162, 41, 159, 16);
		add(lblOnlineOrder);
		
		textField = new JTextField();
		textField.setBounds(187, 69, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Province");
		lblNewLabel_1.setBounds(28, 103, 61, 16);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(187, 97, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);

	}

}
