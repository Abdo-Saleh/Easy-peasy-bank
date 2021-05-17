package guiclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.UIManager;

import classes.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerChnagePinGui {

	private JFrame frame;
	private JTextField OldtextField;
	private JTextField NewtextField_1;
	private JTextField ConftextField_2;
	private Customer customer;
	private static CustomerChnagePinGui newCustomerChnagePinGuiInstance;

	public static CustomerChnagePinGui getCustomerChnagePinGuiInstance(Customer customer) {
		if (newCustomerChnagePinGuiInstance == null)
			newCustomerChnagePinGuiInstance = new CustomerChnagePinGui(customer);

		return newCustomerChnagePinGuiInstance;
	}

	/**
	 * Create the application.
	 */
	private CustomerChnagePinGui(Customer customer) {
		this.customer = customer;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Pin Change");
		GuiTools.centerFrame(frame);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 450, 275);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_1.setBounds(0, 222, 450, 53);
		panel.add(panel_1);

		JLabel lblNewLabel = new JLabel("Old Pin Code");
		lblNewLabel.setBounds(34, 56, 105, 15);
		panel.add(lblNewLabel);

		JLabel lblNewPinCode = new JLabel("New Pin Code");
		lblNewPinCode.setBounds(34, 93, 105, 15);
		panel.add(lblNewPinCode);

		JLabel lblConfirmPinCode = new JLabel("Confirm Pin Code");
		lblConfirmPinCode.setBounds(34, 131, 129, 15);
		panel.add(lblConfirmPinCode);

		OldtextField = new JTextField();
		OldtextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		OldtextField.setHorizontalAlignment(SwingConstants.CENTER);
		OldtextField.setBounds(170, 49, 190, 30);
		panel.add(OldtextField);
		OldtextField.setColumns(10);

		NewtextField_1 = new JTextField();
		NewtextField_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		NewtextField_1.setHorizontalAlignment(SwingConstants.CENTER);
		NewtextField_1.setColumns(10);
		NewtextField_1.setBounds(170, 86, 190, 30);
		panel.add(NewtextField_1);

		ConftextField_2 = new JTextField();
		ConftextField_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		ConftextField_2.setHorizontalAlignment(SwingConstants.CENTER);
		ConftextField_2.setColumns(10);
		ConftextField_2.setBounds(170, 124, 190, 30);
		panel.add(ConftextField_2);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateDefaultPinCode();
			}
		});
		btnNewButton.setBounds(271, 166, 89, 25);
		panel.add(btnNewButton);

		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		btnLogOut.setBounds(170, 166, 89, 25);
		panel.add(btnLogOut);
		frame.setVisible(true);
	}

	public void updateDefaultPinCode() {
		if (OldtextField.getText().equalsIgnoreCase(customer.getPinCode())) {
			if (NewtextField_1.getText().equalsIgnoreCase(ConftextField_2.getText())) {
				customer.setPinCode(NewtextField_1.getText());
				JOptionPane.showMessageDialog(frame, "Successfully set new pin code", "INFORMATION MESSAGE",
						JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				CustomerGui.getCustomerGuiInstance(customer);
			} else {
				// not matching new and conf pin
				JOptionPane.showMessageDialog(frame, "Pin Code and Conf Pin code must match", "ERROR MESSAGE",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// wrong old pin entered
			JOptionPane.showMessageDialog(frame, "You have entered a wrong old pin", "ERROR MESSAGE",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
