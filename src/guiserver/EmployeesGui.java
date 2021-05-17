package guiserver;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import classes.Customer;
import classes.Employee;
import data.Database;
import guiclient.GuiTools;
import validation.ContactDetailsValidator;

public class EmployeesGui {

	private JFrame frame;
	private static EmployeesGui employeesGuiInstance;

	public static EmployeesGui getEmployeesGuiInstance() {
		if (employeesGuiInstance == null) {
			employeesGuiInstance = new EmployeesGui();
		}
		return employeesGuiInstance;
	}

	private EmployeesGui() {
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

		JButton btnNewButton = new JButton("New Employee");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmployeeNewGui.getEmployeeNewGuiInstance();
			}
		});
		btnNewButton.setBounds(33, 90, 172, 30);
		panel_1.add(btnNewButton);

		JButton btnEditAccount = new JButton("Edit Employee");
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nationalId = JOptionPane.showInputDialog("Enter Employee's National ID");
				Employee employee = Database.lookUpForEmployee(nationalId);
				if (employee != null)
					new EmployeeEditGui(employee, new ContactDetailsValidator());
				else
					JOptionPane.showMessageDialog(null, "Enter a valid National Id", "ERROR",
							JOptionPane.ERROR_MESSAGE);

			}
		});
		btnEditAccount.setBounds(33, 151, 172, 30);
		panel_1.add(btnEditAccount);

		JButton btnBlockAccount = new JButton("BLock Employee");
		btnBlockAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nationalId = JOptionPane.showInputDialog("Enter Customer's National ID");
				Employee employee = Database.lookUpForEmployee(nationalId);
				if (employee != null) {
					employee.setBlocked(true);
					JOptionPane.showMessageDialog(null,
							"Customer: " + employee.getFullName() + " account has been blocked successfully",
							"INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(null, "Enter a valid National Id", "ERROR",
							JOptionPane.ERROR_MESSAGE);

			}
		});
		btnBlockAccount.setBounds(33, 207, 172, 30);
		panel_1.add(btnBlockAccount);

		JButton btnUnblockAccount = new JButton("UnbLock Employee");
		btnUnblockAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String nationalId = JOptionPane.showInputDialog("Enter Customer's National ID");
//				Customer customer = AccountsGui.lookUp(nationalId);
//				if (customer != null) {
//					customer.setBlocked(true);
//					JOptionPane.showMessageDialog(null,
//							"Customer: " + customer.getFullName() + " account has been unblocked successfully",
//							"INFORMATION MESSAGE", JOptionPane.INFORMATION_MESSAGE);
//				} else
//					JOptionPane.showMessageDialog(null, "Enter a valid National Id", "ERROR",
//							JOptionPane.ERROR_MESSAGE);
			}

		});
		btnUnblockAccount.setBounds(33, 262, 172, 30);
		panel_1.add(btnUnblockAccount);

		GuiTools.setLogo(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_2.setBounds(0, 370, 550, 55);
		frame.getContentPane().add(panel_2);
		GuiTools.centerFrame(frame);
		frame.setVisible(true);
		frame.setTitle("Employees Window");
	}

	

}
