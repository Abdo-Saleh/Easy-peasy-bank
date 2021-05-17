package guiserver;

import java.awt.EventQueue;

import javax.swing.JFrame;

import guiclient.GuiTools;
import validation.AccountBalanceValidator;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import classes.Account;
import classes.Customer;
import classes.JavaMailUtil;
import data.Database;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CustomerNewAccountGui {

	public JFrame frame;
	private JTextField iban;
	private JTextField fullName;
	private Account account;
	private Customer customer;
	private JTextField balance;
	private AccountBalanceValidator accountBalanceValidator;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//				//	NewCustomerAccountGui window = new NewCustomerAccountGui();
//				//	window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public CustomerNewAccountGui(Account account, Customer customer, AccountBalanceValidator accountBalanceValidator) {
		this.account = account;
		this.customer = customer;
		this.accountBalanceValidator = accountBalanceValidator;
		initialize();
	}
	
	private void saveAccountInfo()
	{
		String oldBalance = account.getBalance();
		account.setBalance(balance.getText());
		if (account.Validate(accountBalanceValidator, new ArrayList<String>())) {
			sendAccountInfo();
			//save to database
			Database.addAccount(account);
			Database.addCustomer(customer);				
			frame.dispose();
		}
		else {
			JOptionPane.showMessageDialog(frame, accountBalanceValidator.brokenRules(account));
			account.setBalance(oldBalance);
		}

	}
	
	private void sendAccountInfo() {
		String message = "Welcome in our bank, here you are your credentials\n"
				+"User name: "+
				customer.getUserName()+" \n"+ 
				"password(change recommended): "+ 
				customer.getPassword()+" \n"+
				"Iban: "+customer.getAccounts().get(0).toString();
				
		JavaMailUtil.sendMail(customer.getContactDetails().getEmail(), message);
		JOptionPane.showMessageDialog(frame,
				"Succuessfully created account for user: " + customer.getFullName());
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 550, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 12, 526, 273);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(12, 12, 352, 249);
		panel.add(lblNewLabel);
		GuiTools.centerFrame(frame);
		GuiTools.setLogo(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_1.setBounds(0, 285, 538, 240);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Iban:");
		lblNewLabel_1.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblNewLabel_1.setForeground(UIManager.getColor("Button.background"));
		lblNewLabel_1.setBounds(12, 42, 76, 29);
		panel_1.add(lblNewLabel_1);

		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setForeground(UIManager.getColor("Button.background"));
		lblFullName.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblFullName.setBounds(12, 85, 132, 29);
		panel_1.add(lblFullName);

		iban = new JTextField();
		iban.setHorizontalAlignment(SwingConstants.CENTER);
		iban.setEditable(false);
		iban.setFont(new Font("Dialog", Font.PLAIN, 20));
		iban.setBounds(137, 44, 389, 29);
		panel_1.add(iban);
		iban.setColumns(10);
		iban.setText(account.toString());

		fullName = new JTextField();
		fullName.setHorizontalAlignment(SwingConstants.CENTER);
		fullName.setEditable(false);
		fullName.setFont(new Font("Dialog", Font.PLAIN, 20));
		fullName.setColumns(10);
		fullName.setBounds(137, 87, 389, 29);
		fullName.setText(customer.getFullName());
		panel_1.add(fullName);

		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setForeground(UIManager.getColor("Button.background"));
		lblBalance.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblBalance.setBounds(12, 129, 132, 29);
		panel_1.add(lblBalance);

		balance = new JTextField();
		balance.setText((String) null);
		balance.setFont(new Font("Dialog", Font.PLAIN, 20));
		balance.setColumns(10);
		balance.setBounds(137, 131, 338, 29);
		balance.setText(account.getBalance() + "");
		panel_1.add(balance);

		JButton save = new JButton("Save & Send Info");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveAccountInfo();
			}
		});
		save.setBounds(373, 187, 153, 25);
		panel_1.add(save);

		JLabel lblNewLabel_2 = new JLabel("€");
		lblNewLabel_2.setForeground(UIManager.getColor("Button.background"));
		lblNewLabel_2.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		lblNewLabel_2.setBounds(486, 133, 40, 25);
		panel_1.add(lblNewLabel_2);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(230, 187, 117, 25);
		panel_1.add(btnBack);
	}
}
