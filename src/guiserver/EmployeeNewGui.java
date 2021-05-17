package guiserver;

import java.awt.Color;
import java.awt.EventQueue;
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
import classes.Employee;
import classes.ContactDetails.Address;
import data.Database;
import encryptionModule.PasswordGenerator;
import guiclient.Country;
import guiclient.GuiTools;
import validation.AccountBalanceValidator;
import validation.ContactDetailsValidator;

public class EmployeeNewGui {

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
	private ContactDetailsValidator employeeInfoValidator;
	private JComboBox gender;
	private JComboBox nationality;
	private static EmployeeNewGui newCEmployeeNewGuiInstance;

	public static EmployeeNewGui getEmployeeNewGuiInstance() {
		if (newCEmployeeNewGuiInstance == null) {
			newCEmployeeNewGuiInstance = new EmployeeNewGui(new ContactDetailsValidator());
		}
		newCEmployeeNewGuiInstance.frame.setVisible(true);
		return newCEmployeeNewGuiInstance;
	}

	/**
	 * Create the application.
	 */
	private EmployeeNewGui(ContactDetailsValidator employeeInfoValidator) {
		this.employeeInfoValidator = employeeInfoValidator;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(298, 12, 690, 488);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Full name:");
		lblNewLabel_1.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		lblNewLabel_1.setBounds(12, 82, 79, 17);
		panel_1.add(lblNewLabel_1);

		fullName = new JTextField();
		fullName.setBounds(135, 72, 193, 38);
		panel_1.add(fullName);
		fullName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Natinal Id:");
		lblNewLabel_2.setBounds(12, 140, 97, 15);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setBounds(12, 205, 105, 15);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Password conf:");
		lblNewLabel_4.setBounds(12, 267, 121, 15);
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
		NationalId.setBounds(135, 129, 193, 38);
		panel_1.add(NationalId);

		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(135, 194, 193, 38);
		passwordField.setText(PasswordGenerator.generateRandomPassword((10)));
		panel_1.add(passwordField);

		passwordFieldCon = new JPasswordField();
		passwordFieldCon.setEditable(false);
		passwordFieldCon.setBounds(135, 256, 193, 38);
		passwordFieldCon.setText(passwordField.getText());

		panel_1.add(passwordFieldCon);

		nationality = new JComboBox();
		nationality.setModel(new DefaultComboBoxModel<Country>(GuiTools.createCountryList()));
		nationality.setBounds(485, 326, 193, 38);
		panel_1.add(nationality);

		gender = new JComboBox();
		gender.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female" }));
		gender.setSelectedIndex(0);
		gender.setBounds(135, 326, 193, 38);
		panel_1.add(gender);

		dateOfBirth = new JTextField();
		dateOfBirth.setColumns(10);
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
		panel_1.add(email);

		phoneNum = new JTextField();
		phoneNum.setColumns(10);
		phoneNum.setBounds(485, 129, 193, 38);
		panel_1.add(phoneNum);

		street = new JTextField();
		street.setColumns(10);
		street.setBounds(485, 194, 193, 38);
		panel_1.add(street);

		postalCode = new JTextField();
		postalCode.setColumns(10);
		postalCode.setBounds(485, 256, 193, 38);
		panel_1.add(postalCode);

		JButton btnNewButton = new JButton("Create Now");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createEmployee();
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

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_2.setBounds(0, 509, 1000, 66);
		frame.getContentPane().add(panel_2);
		GuiTools.centerFrame(frame);
		GuiTools.setLogo(lblNewLabel);
		frame.setVisible(true);
		frame.setTitle("New Employee");
	}

	private void createEmployee() {
		ContactDetails contactDetails = createContactDetails();
		if (contactDetails.Validate(employeeInfoValidator, new ArrayList<String>())) {
			// create customer && account
			Employee employee = new Employee();
			employee.setNationality(nationality.getSelectedItem().toString());
			employee.setGender(gender.getSelectedItem().toString());
			employee.setFullName(fullName.getText());
			employee.setUserName(createUniqueUserName(employee.getFullName(), contactDetails.getDateOfBirth()));
			employee.setPassword(String.valueOf(passwordField.getPassword()));
			employee.setContactDetails(contactDetails);
			employee.setNationalID(NationalId.getText());
			// save to the database
			Database.addEmployee(employee);
			JOptionPane.showMessageDialog(frame, "Successfully added employee", "INFORMATION MESSAGE",
					JOptionPane.INFORMATION_MESSAGE);
			clearFields();
			frame.dispose();
		} else {
			// show error message
			JOptionPane.showMessageDialog(frame, employeeInfoValidator.brokenRules(contactDetails));
		}
	}

	private ContactDetails createContactDetails() {
		// create contact details
		ContactDetails contactdetails = new ContactDetails(email.getText(), phoneNum.getText(), dateOfBirth.getText());
		// create address
		ContactDetails.Address address = contactdetails.new Address(street.getText(), postalCode.getText(),
				nationality.getSelectedItem().toString());
		return contactdetails;
	}

	private String createUniqueUserName(String fullName, String date) {
		String username = "";
		int num1 = (Integer.parseInt(date.substring(0, 2))
				+ Integer.parseInt(date.substring(3, 5)) / Integer.parseInt(date.substring(7)));
		// day + month / year in order to give unique digit

		int num2 = Integer.parseInt(date.substring(1, 2));
		// to ensure username is unique

		username = fullName.substring(0, ((fullName.length() - 1) / 2)).replaceAll("\\s", "") + fullName.charAt(0)
				+ num1 + num2;
		return username;

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
