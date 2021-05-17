package guiclient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import classes.Account;
import classes.Customer;
import classes.Transaction;
import data.Database;
import exceptions.InsufficientCredit;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerBalanceInquery {

	private JFrame frame;
	private Customer customer;
	private static CustomerBalanceInquery newCustomerBalanceInquery;
	private JTextField amount;
	private JComboBox comboBox1;

	public static CustomerBalanceInquery getCustomerBalanceInqueryInstance(Customer customer) {

		if (newCustomerBalanceInquery == null) {
			newCustomerBalanceInquery = new CustomerBalanceInquery(customer);
		}
		newCustomerBalanceInquery.frame.setVisible(true);
		return newCustomerBalanceInquery;

	}

	/**
	 * Create the application.
	 */
	public CustomerBalanceInquery(Customer customer) {
		this.customer = customer;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Check Balance Window");
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 450, 325);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sender");
		lblNewLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblNewLabel.setBounds(58, 54, 61, 15);
		panel.add(lblNewLabel);

		comboBox1 = new JComboBox();
		comboBox1.setBounds(135, 46, 271, 33);
		comboBox1.setModel(new DefaultComboBoxModel(Database.getCustomerAccounts(this.customer).toArray()));

		panel.add(comboBox1);

		JButton btnSend = new JButton("Check");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// create transaction
				checkbalance();
			}
		});
		btnSend.setBounds(315, 217, 91, 25);
		panel.add(btnSend);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerGui.getCustomerGuiInstance(customer);
				frame.dispose();
			}
		});
		btnBack.setBounds(212, 217, 91, 25);
		panel.add(btnBack);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_1.setBounds(0, 254, 462, 71);
		panel.add(panel_1);

		JLabel lblAmount = new JLabel("Balance");
		lblAmount.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblAmount.setBounds(58, 127, 75, 15);
		panel.add(lblAmount);

		amount = new JTextField();
		amount.setEditable(false);
		amount.setColumns(10);
		amount.setBounds(135, 118, 222, 33);
		panel.add(amount);

		JLabel lblNewLabel_1 = new JLabel("€");
		lblNewLabel_1.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 18));
		lblNewLabel_1.setBounds(375, 127, 30, 24);
		panel.add(lblNewLabel_1);
		frame.setBounds(100, 100, 450, 350);
		System.out.println(Database.getAccounts());
		GuiTools.centerFrame(frame);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * create transaction of withdraw add transaction to the database
	 * 
	 */
	public void checkbalance() {
		for (Account account : this.customer.getAccounts()) {
			if (account.getIban().equalsIgnoreCase(comboBox1.getSelectedItem().toString().replaceAll("\\s+", ""))) {
				amount.setText(account.getBalance());
				break;
			}

		}
		// first check if the credit of selected iban is sufficient or throw and error

	}
}
