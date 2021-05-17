package guiclient;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import classes.Customer;
import data.Database;
import guiserver.EpbManageGui;
import guiserver.EpbSystemLogInGui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerGui {

	private JFrame frame;
	private static CustomerGui customerGuiInstance;
	private Customer customer;
	CustomerService customerService;
	Thread thread;

	/**
	 * Create the application.
	 */
	private CustomerGui(Customer customer) {
		this.customer = customer;
		initialize();
	}

	public static CustomerGui getCustomerGuiInstance(Customer customer) {
		// TODO Auto-generated method stub
		if (customerGuiInstance == null) {
			customerGuiInstance = new CustomerGui(customer);
		}
		customerGuiInstance.frame.setVisible(true);
		return customerGuiInstance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 700, 500);
		GuiTools.centerFrame(frame);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(41, 51, 309, 349);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("");
		label.setBounds(12, 12, 285, 314);
		GuiTools.setLogo(label);
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(362, 51, 309, 349);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("Deposit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DepositCustomerGui.getCustomerTransferGuiInstance(customer);

			}
		});
		btnNewButton.setBounds(66, 78, 184, 39);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Transfer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerTransferGui.getCustomerTransferGuiInstance(customer);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(66, 27, 184, 39);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Change Pin");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerChnagePinGui.getCustomerChnagePinGuiInstance(customer);
			}
		});
		btnNewButton_2.setBounds(66, 180, 184, 39);
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Balance inquiry");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerBalanceInquery.getCustomerBalanceInqueryInstance(customer);

			}
		});
		btnNewButton_3.setBounds(66, 129, 184, 39);
		panel_1.add(btnNewButton_3);

		JButton btnTransactionHistory = new JButton("Transaction History");
		btnTransactionHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Database.getCustomerTransactions(customer).size() != 0) {
					new TransactionsHistoryCustomerGui(customer);
				} else {
					JOptionPane.showMessageDialog(frame, "you don't have any transaction yet", "ERROR MESSAGE",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnTransactionHistory.setBounds(66, 231, 184, 39);
		panel_1.add(btnTransactionHistory);

		JButton btnCallBranch = new JButton("Call branch");
		btnCallBranch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerService customerService = new CustomerService(customer);
				thread = new Thread(customerService);
				thread.start();
			}
		});
		btnCallBranch.setBounds(66, 282, 184, 39);
		panel_1.add(btnCallBranch);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_2.setBounds(0, 413, 700, 62);
		frame.getContentPane().add(panel_2);

		JButton btnNewButton_4 = new JButton("Log out");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerLogInGui.getCustomerLogInGuiInstance();
				frame.dispose();
			}
		});
		btnNewButton_4.setBounds(551, 12, 117, 25);
		frame.getContentPane().add(btnNewButton_4);

	}

}
