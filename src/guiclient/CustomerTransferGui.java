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

public class CustomerTransferGui {

	private JFrame frame;
	private JTextField textField;
	private Customer customer;
	private static CustomerTransferGui newCustomerTRansferGui;
	private JTextField amount;
	private JComboBox comboBox1;

	public static CustomerTransferGui getCustomerTransferGuiInstance(Customer customer) {

		if (newCustomerTRansferGui == null) {
			newCustomerTRansferGui = new CustomerTransferGui(customer);
		}
		newCustomerTRansferGui.frame.setVisible(true);
		return newCustomerTRansferGui;

	}

	/**
	 * Create the application.
	 */
	public CustomerTransferGui(Customer customer) {
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
		frame.setTitle("Transfer Window");
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 450, 325);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sender");
		lblNewLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblNewLabel.setBounds(58, 54, 61, 15);
		panel.add(lblNewLabel);

		JLabel lblReciver = new JLabel("Receiver");
		lblReciver.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblReciver.setBounds(58, 120, 75, 15);
		panel.add(lblReciver);

		comboBox1 = new JComboBox();
		comboBox1.setBounds(135, 46, 271, 33);
		comboBox1.setModel(new DefaultComboBoxModel(Database.getCustomerAccounts(this.customer).toArray()));

		panel.add(comboBox1);

		textField = new JTextField();
		textField.setBounds(135, 111, 271, 33);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// create transaction
				createTransaction();
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

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblAmount.setBounds(58, 167, 75, 15);
		panel.add(lblAmount);

		amount = new JTextField();
		amount.setColumns(10);
		amount.setBounds(135, 158, 227, 33);
		panel.add(amount);
		
		JLabel label = new JLabel("€");
		label.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 18));
		label.setBounds(380, 161, 30, 24);
		panel.add(label);
		frame.setBounds(100, 100, 450, 350);
		System.out.println(Database.getAccounts());
		GuiTools.centerFrame(frame);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * create transaction of withdraw add transaction to the database
	 * 
	 */
	public void createTransaction() {
		if (textField.getText().replaceAll("\\s+", "").length() == 24) {
			try {
				// Polymorphism
				// withdraw from sender
				for (Account account : this.customer.getAccounts()) {
					if (account.getIban()
							.equalsIgnoreCase(comboBox1.getSelectedItem().toString().replaceAll("\\s+", ""))) {
						System.out.println(account.getBalance());
						account.withDraw(amount.getText());
						System.out.println(account.getBalance());
						Database.addTransaction(new Transaction(comboBox1.getSelectedItem().toString(),
								textField.getText(), 0, customer));
						break;
					}

				}

				for (Account account : Database.getAccounts()) {
					// depoist in recevier
					if (account.getIban().equalsIgnoreCase(textField.getText().replaceAll("\\s+", ""))) {
						System.out.println(account.getBalance());
						account.deposit(amount.getText());
						System.out.println(account.getBalance());
						Database.addTransaction(new Transaction(comboBox1.getSelectedItem().toString(),
								textField.getText(), 1, customer));
						break;
					}
				}

			} catch (InsufficientCredit e) {
				JOptionPane.showMessageDialog(frame, "you don't have enough balance", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(frame, "enter a valid amount", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame, "enter a valid amount", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(frame, "enter a valid Recevier iban", "Error Message",
					JOptionPane.ERROR_MESSAGE);

		}
		// first check if the credit of selected iban is sufficient or throw and error

	}

}
