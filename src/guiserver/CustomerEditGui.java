package guiserver;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import classes.Account;
import classes.ContactDetails;
import classes.Customer;
import data.Database;
import encryptionModule.PasswordGenerator;
import guiclient.Country;
import guiclient.GuiTools;
import validation.AccountBalanceValidator;
import validation.ContactDetailsValidator;

public class CustomerEditGui {

	private JFrame frame;
	private JTextField fullName;
	private JTextField NationalId;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldCon;
	private JTextField dateOfBirth;
	private JTextField email;
	private JTextField phoneNum;
	private JTextField street;
	private JTextField postalCode;
	private ContactDetailsValidator customerInfoValidator;
	private JComboBox gender;
	private JComboBox nationality;
	private Customer customer;

	/**
	 * Create the application.
	 */
	public CustomerEditGui(Customer customer, ContactDetailsValidator customerInfoValidator) {
		this.customerInfoValidator = customerInfoValidator;
		this.customer = customer;
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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(298, 12, 690, 488);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Full name:");
		lblNewLabel_1.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblNewLabel_1.setBounds(12, 22, 79, 17);
		panel_1.add(lblNewLabel_1);

		fullName = new JTextField();
		fullName.setBounds(135, 12, 193, 38);
		fullName.setText(this.customer.getFullName());
		panel_1.add(fullName);
		fullName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Natinal Id:");
		lblNewLabel_2.setBounds(12, 80, 97, 15);

		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setBounds(12, 145, 105, 15);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Password conf:");
		lblNewLabel_4.setBounds(12, 207, 121, 15);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Gender:");
		lblNewLabel_5.setBounds(12, 338, 70, 15);
		panel_1.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Nationality:");
		lblNewLabel_6.setBounds(370, 338, 97, 15);
		panel_1.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Date of birth:");
		lblNewLabel_7.setBounds(12, 403, 105, 15);
		panel_1.add(lblNewLabel_7);

		NationalId = new JTextField();
		NationalId.setColumns(10);
		NationalId.setText(this.customer.getNationalID());
		NationalId.setBounds(135, 69, 193, 38);
		panel_1.add(NationalId);

		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(135, 134, 193, 38);
		passwordField.setText(this.customer.getPassword());
		panel_1.add(passwordField);

		passwordFieldCon = new JPasswordField();
		passwordFieldCon.setEditable(false);
		passwordFieldCon.setBounds(135, 196, 193, 38);
		passwordFieldCon.setText(this.customer.getPassword());
		panel_1.add(passwordFieldCon);

		nationality = new JComboBox();
		nationality.setModel(new DefaultComboBoxModel<Country>(GuiTools.createCountryList()));
		nationality.setSelectedItem(this.customer.getContactDetails().getAddress().getCountry());
		nationality.setBounds(485, 326, 193, 38);
		panel_1.add(nationality);

		gender = new JComboBox();
		gender.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female" }));
		gender.setSelectedIndex(0);
		gender.setBounds(135, 326, 193, 38);
		panel_1.add(gender);

		dateOfBirth = new JTextField();
		dateOfBirth.setColumns(10);
		dateOfBirth.setText(this.customer.getContactDetails().getDateOfBirth());
		dateOfBirth.setBounds(135, 392, 193, 38);
		panel_1.add(dateOfBirth);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(370, 83, 70, 15);
		panel_1.add(lblEmail);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(365, 139, 127, 17);
		panel_1.add(lblPhoneNumber);

		JLabel lblNewLabel_8 = new JLabel("Street:");
		lblNewLabel_8.setBounds(370, 205, 70, 15);
		panel_1.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Postal Code:");
		lblNewLabel_9.setBounds(370, 267, 97, 15);
		panel_1.add(lblNewLabel_9);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(485, 72, 193, 38);
		email.setText(this.customer.getContactDetails().getEmail());
		panel_1.add(email);

		phoneNum = new JTextField();
		phoneNum.setColumns(10);
		phoneNum.setBounds(485, 129, 193, 38);
		phoneNum.setText(this.customer.getContactDetails().getPhoneNumber());

		panel_1.add(phoneNum);

		street = new JTextField();
		street.setColumns(10);
		street.setBounds(485, 194, 193, 38);
		street.setText(this.customer.getContactDetails().getAddress().getStreet());

		panel_1.add(street);

		postalCode = new JTextField();
		postalCode.setColumns(10);
		postalCode.setBounds(485, 256, 193, 38);
		postalCode.setText(this.customer.getContactDetails().getAddress().getPostalCode());

		panel_1.add(postalCode);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateCustomer();
			}

		});
		btnNewButton.setBounds(530, 422, 148, 38);
		panel_1.add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(367, 422, 142, 38);
		panel_1.add(btnBack);

		JButton btnNewButton_1 = new JButton("generate new password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordField.setText(PasswordGenerator.generateRandomPassword((10)));
				passwordFieldCon.setText(passwordField.getText());
				JOptionPane.showMessageDialog(frame,
						"New password has been successfully generated, it won't be updated untill you save the changes",
						"INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(12, 262, 316, 32);
		panel_1.add(btnNewButton_1);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_2.setBounds(0, 509, 1000, 66);
		frame.getContentPane().add(panel_2);
		GuiTools.centerFrame(frame);
		GuiTools.setLogo(lblNewLabel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void updateCustomer() {
		ContactDetails updatedcontactDetails = updateContactDetails();
		if (updatedcontactDetails.Validate(customerInfoValidator, new ArrayList<String>())) {
			// update customer
			this.customer.setFullName(fullName.getText());
			this.customer.setGender(gender.getSelectedItem().toString());
			this.customer.setNationality(nationality.getSelectedItem().toString());
			this.customer.setPassword(String.valueOf(passwordField.getPassword()));
			this.customer.setContactDetails(updatedcontactDetails);
			this.customer.setNationalID(NationalId.getText());
			Database.updateCustomer(this.customer);
			JOptionPane.showMessageDialog(frame, "Customer information has been updated successfully", "NEW PASSWORD",
					JOptionPane.INFORMATION_MESSAGE);
			clearFields();
			frame.dispose();
		} else {
			// show error message
			JOptionPane.showMessageDialog(frame, customerInfoValidator.brokenRules(updatedcontactDetails));
		}

	}

	private ContactDetails updateContactDetails() {
		// create address
		ContactDetails.Address address = this.customer.getContactDetails().getAddress();
		address.setStreet(street.getText());
		address.setPostalCode(postalCode.getText());
		address.setCountry(nationality.getSelectedItem().toString());
		// create contact details
		ContactDetails updatedcontactDetails = this.customer.getContactDetails();
		updatedcontactDetails.setAddress(address);
		updatedcontactDetails.setDateOfBirth(dateOfBirth.getText());
		updatedcontactDetails.setPhoneNumber(phoneNum.getText());
		updatedcontactDetails.setEmail(email.getText());
		return updatedcontactDetails;
	}

	public void clearFields() {
		fullName.setText("");
		NationalId.setText("");
		passwordField.setText("");
		passwordFieldCon.setText("");
		dateOfBirth.setText("");
		phoneNum.setText("");
		email.setText("");
		street.setText("");
		postalCode.setText("");
		gender.setSelectedIndex(0);
	}
}
