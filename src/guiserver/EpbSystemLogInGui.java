package guiserver;

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

import classes.Account;
import classes.Customer;
import classes.Person;
import data.Database;
import encryptionModule.PasswordGenerator;
import guiclient.CustomerLogInGui;
import guiclient.GuiTools;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EpbSystemLogInGui {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Database.initializeDb();
					EpbSystemLogInGui window = new EpbSystemLogInGui();
					CustomerLogInGui.getCustomerLogInGuiInstance();
					// System.out.println(Database.getCustomerAccounts(Database.getCustomers().get(1)));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EpbSystemLogInGui() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel.setBounds(0, 282, 400, 193);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Employee");
		lblNewLabel_1.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblNewLabel_1.setForeground(UIManager.getColor("MenuItem.background"));
		lblNewLabel_1.setBounds(22, 29, 99, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblNewLabel_2.setForeground(UIManager.getColor("MenuItem.background"));
		lblNewLabel_2.setBounds(22, 60, 91, 15);
		panel.add(lblNewLabel_2);

		JButton btnNewButton = new JButton("LogIn");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Person person = Database.checkUserNameAndPasswordEmployee(textField.getText(),
						String.valueOf(passwordField.getPassword()));
				if (person != null) {
					{
						if (!person.isBlocked()) {
							EpbManageGui.getNewCustomerGuiInstance(person);
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(frame,
									"your account has been blocked, contact the bank please");
						}
					}
				} else
					JOptionPane.showMessageDialog(frame, "Username or password is incorrect");
			}
		});
		btnNewButton.setBounds(134, 115, 117, 25);
		panel.add(btnNewButton);
		textField = new JTextField();
		textField.setBounds(129, 27, 207, 19);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(129, 58, 207, 19);
		panel.add(passwordField);

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setBounds(12, 12, 388, 258);
		frame.getContentPane().add(lblNewLabel);
		frame.setBounds(200, 200, 400, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GuiTools.centerFrame(frame);
		GuiTools.setLogo(lblNewLabel);
		frame.setVisible(true);
	}

}
