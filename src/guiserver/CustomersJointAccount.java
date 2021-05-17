package guiserver;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import classes.Account;
import classes.Customer;
import classes.JointAccount;
import data.Database;
import exceptions.MinimalBalanceException;
import guiclient.GuiTools;
import validation.ContactDetailsValidator;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomersJointAccount {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField txtNatinalId;
	private JTextField textField_3;
	Customer firstCustomer;
	Customer secondCustomer;
	JointAccount jointaccount;
	private static CustomersJointAccount newCustomersJointAccountInstance;

	public static CustomersJointAccount getCustomersJointAccountInstance() {
		if (newCustomersJointAccountInstance == null) {
			newCustomersJointAccountInstance = new CustomersJointAccount();
		}
		newCustomersJointAccountInstance.frame.setVisible(true);
		return newCustomersJointAccountInstance;
	}

	/**
	 * Create the application.
	 */
	private CustomersJointAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 12, 273, 399);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(12, 12, 270, 357);
		panel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_2.setBounds(0, 509, 1000, 66);
		frame.getContentPane().add(panel_2);
		GuiTools.centerFrame(frame);
		GuiTools.setLogo(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(311, 12, 630, 474);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblIban = new JLabel("Iban");
		lblIban.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 16));
		lblIban.setBounds(31, 268, 70, 15);
		panel_1.add(lblIban);

		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 16));
		lblBalance.setBounds(31, 313, 70, 23);
		panel_1.add(lblBalance);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(139, 266, 461, 29);
		panel_1.add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(139, 307, 396, 29);
		panel_1.add(textField_2);

		JLabel label = new JLabel("€");
		label.setFont(new Font("DejaVu Serif", Font.BOLD, 20));
		label.setBounds(547, 306, 33, 34);
		panel_1.add(label);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (checkbalance(textField_2.getText())) {
						if (jointaccount != null) {
							Database.addAccount(jointaccount);
							JOptionPane.showMessageDialog(frame,
									"Successfully created joint account for " + firstCustomer.getFullName() + " "
											+ secondCustomer.getFullName(),
									"INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						} else
							JOptionPane.showMessageDialog(frame, "please initialize new iban and try again",
									"INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (MinimalBalanceException e) {
					JOptionPane.showMessageDialog(frame, "balance has to be more than 10", "INFORMATION MESSAGE",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(482, 415, 117, 25);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(353, 415, 117, 25);
		panel_1.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("FIrst Client:");
		lblNewLabel_1.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblNewLabel_1.setBounds(14, 51, 106, 15);
		panel_1.add(lblNewLabel_1);

		JLabel lblSencodClient = new JLabel("Sencod Client:");
		lblSencodClient.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblSencodClient.setBounds(14, 159, 117, 15);
		panel_1.add(lblSencodClient);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblNewLabel_2.setBounds(122, 51, 174, 15);
		panel_1.add(lblNewLabel_2);

		JLabel label_1 = new JLabel("");
		label_1.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		label_1.setBounds(135, 159, 174, 15);
		panel_1.add(label_1);

		txtNatinalId = new JTextField();
		txtNatinalId.setBounds(107, 78, 114, 25);
		panel_1.add(txtNatinalId);
		txtNatinalId.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(114, 186, 114, 25);
		panel_1.add(textField_3);

		JButton btnNewButton_2 = new JButton("Find");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(txtNatinalId.getText());
				if (!txtNatinalId.getText().isEmpty()) {
					firstCustomer = Database.lookUpForCustomer(txtNatinalId.getText());
					lblNewLabel_2.setText(firstCustomer.getFullName());
				} else {
					JOptionPane.showMessageDialog(frame, "Enter natinal id", "Error MESSAGE",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(267, 78, 90, 25);
		panel_1.add(btnNewButton_2);

		JButton button = new JButton("Find");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField_3.getText().isEmpty()) {
					secondCustomer = Database.lookUpForCustomer(txtNatinalId.getText());
					label_1.setText(secondCustomer.getFullName());
				} else {
					JOptionPane.showMessageDialog(frame, "Enter natinal id", "Error MESSAGE",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(267, 186, 90, 25);
		panel_1.add(button);

		JLabel lblNatinalId_1 = new JLabel("Natinal ID");
		lblNatinalId_1.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblNatinalId_1.setBounds(14, 83, 96, 15);
		panel_1.add(lblNatinalId_1);

		JLabel lblNatinalId = new JLabel("Natinal ID");
		lblNatinalId.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 14));
		lblNatinalId.setBounds(14, 191, 96, 15);
		panel_1.add(lblNatinalId);

		JButton btnInitializeIban = new JButton("initialize account");
		btnInitializeIban.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (firstCustomer == null || secondCustomer == null)
					JOptionPane.showMessageDialog(frame, "please retrive two customers", "INFORMATION MESSAGE",
							JOptionPane.INFORMATION_MESSAGE);
				else {
					jointaccount = new JointAccount(firstCustomer.getId(), secondCustomer.getId());
					textField.setText(jointaccount.getIban());
					textField_2.setText(jointaccount.getBalance());
					jointaccount.setBalance(textField_2.getText());
				}
			}
		});
		btnInitializeIban.setBounds(436, 348, 164, 25);
		panel_1.add(btnInitializeIban);
		frame.setVisible(true);
	}

	private boolean checkbalance(String balance) throws MinimalBalanceException {
		if (Double.parseDouble(balance) < 10.0)
			throw new MinimalBalanceException(balance);

		return true;

	}
}
