package guiclient;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import classes.Customer;
import data.Database;
import guiserver.EpbManageGui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerLogInGui {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel label;
	private String result = "";
	private boolean flag = false;
	private static CustomerLogInGui newCustomerLogInGuiInstance;

	public static CustomerLogInGui getCustomerLogInGuiInstance() {
		if (newCustomerLogInGuiInstance == null)
			newCustomerLogInGuiInstance = new CustomerLogInGui();

		newCustomerLogInGuiInstance.frame.setVisible(true);
		return newCustomerLogInGuiInstance;
	}

	/**
	 * Create the application.
	 */
	private CustomerLogInGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(200, 200, 400, 500);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		label = new JLabel("");
		label.setBounds(42, 12, 322, 258);
		frame.getContentPane().add(label);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel.setBounds(0, 282, 400, 193);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(129, 27, 207, 19);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(129, 58, 207, 19);
		panel.add(passwordField);

		JButton btnLogin = new JButton("LogIn");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogIn();
			}
		});
		btnLogin.setBounds(134, 115, 117, 25);
		panel.add(btnLogin);

		JLabel lblNewLabel = new JLabel("User name");
		lblNewLabel.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblNewLabel.setForeground(UIManager.getColor("MenuItem.background"));
		lblNewLabel.setBounds(22, 29, 99, 15);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblNewLabel_1.setForeground(UIManager.getColor("MenuItem.background"));
		lblNewLabel_1.setBounds(22, 60, 91, 15);
		panel.add(lblNewLabel_1);

		GuiTools.centerFrame(frame);
		GuiTools.setLogo(label);
		frame.setVisible(true);
	}

	private void LogIn() {
		Customer customer = Database.checkUserNameAndPasswordCustomer(textField.getText(),
				String.valueOf(passwordField.getPassword()));
		System.out.println(customer);
		if (customer != null) {
			if (!customer.isBlocked()) {
				// change default pin
				if (!customer.getPinCode().equalsIgnoreCase("0000")) {
					do {
						result = JOptionPane.showInputDialog(frame, "Enter your Pin");
						if (customer.getPinCode().equalsIgnoreCase(result)) {
							CustomerGui.getCustomerGuiInstance(customer);

							flag = false;
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(frame, "You've entered Wrong Pin", "Error Message",
									JOptionPane.ERROR_MESSAGE);
							flag = true;
						}
					} while (flag);

				} else {
					CustomerChnagePinGui.getCustomerChnagePinGuiInstance(customer);
					frame.dispose();
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Username or password is incorrect");
			}
		} else {
			JOptionPane.showMessageDialog(frame, "your account has been blocked, contact the bank please");
		}

	}

}
