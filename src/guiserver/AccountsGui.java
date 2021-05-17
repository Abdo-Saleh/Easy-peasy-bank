package guiserver;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import guiclient.GuiTools;
import validation.ContactDetailsValidator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;

import classes.Customer;
import data.Database;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountsGui {

	private static AccountsGui accountsGuiInstance;
	private JFrame frame;

	public static AccountsGui getAccountsGui() {
		if (accountsGuiInstance == null) {
			accountsGuiInstance = new AccountsGui();
		}
		return accountsGuiInstance;
	}

	/**
	 * Create the application.
	 */
	private AccountsGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 550, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 12, 252, 351);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(12, 12, 228, 327);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(276, 12, 262, 351);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("New Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerNewGui.getCustomerGuiInstance();
			}
		});
		btnNewButton.setBounds(36, 55, 178, 30);
		panel_1.add(btnNewButton);

		JButton btnEditAccount = new JButton("Edit Account");
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nationalId = JOptionPane.showInputDialog("Enter Customer's National ID");
				Customer customer = Database.lookUpForCustomer(nationalId);
				if (customer != null)
					new CustomerEditGui(customer, new ContactDetailsValidator());
				else
					JOptionPane.showMessageDialog(null, "Enter a valid National Id", "ERROR",
							JOptionPane.ERROR_MESSAGE);

			}
		});
		btnEditAccount.setBounds(36, 116, 178, 30);
		panel_1.add(btnEditAccount);

		JButton btnBlockAccount = new JButton("BLock Account");
		btnBlockAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nationalId = JOptionPane.showInputDialog("Enter Customer's National ID");
				Customer customer = Database.lookUpForCustomer(nationalId);
				if (customer != null) {
					customer.setBlocked(true);
					JOptionPane.showMessageDialog(null,
							"Customer: " + customer.getFullName() + " account has been blocked successfully",
							"INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Enter a valid National Id", "ERROR",
							JOptionPane.ERROR_MESSAGE);

			}
		});
		btnBlockAccount.setBounds(36, 222, 178, 30);
		panel_1.add(btnBlockAccount);

		JButton btnUnblockAccount = new JButton("UnbLock Account");
		btnUnblockAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nationalId = JOptionPane.showInputDialog("Enter Customer's National ID");
				Customer customer = Database.lookUpForCustomer(nationalId);
				if (customer != null) {
					customer.setBlocked(true);
					JOptionPane.showMessageDialog(null,
							"Customer: " + customer.getFullName() + " account has been unblocked successfully",
							"INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Enter a valid National Id", "ERROR",
							JOptionPane.ERROR_MESSAGE);
			}

		});
		btnUnblockAccount.setBounds(36, 277, 178, 30);
		panel_1.add(btnUnblockAccount);
		
		JButton btnCreateJointAccount = new JButton("Create Joint Account");
		btnCreateJointAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomersJointAccount.getCustomersJointAccountInstance();
			}
		});
		btnCreateJointAccount.setBounds(36, 168, 178, 30);
		panel_1.add(btnCreateJointAccount);

		GuiTools.setLogo(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_2.setBounds(0, 370, 550, 55);
		frame.getContentPane().add(panel_2);
		GuiTools.centerFrame(frame);
		frame.setVisible(true);
	}
}
