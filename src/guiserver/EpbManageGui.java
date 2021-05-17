package guiserver;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import classes.Employee;
import classes.Person;
import guiclient.GuiTools;
import validation.ContactDetailsValidator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EpbManageGui {

	private JFrame frame;
	private Person person;
	ChatRoom chroom;
	Thread thread;
	// Singletone Patterm
	private static EpbManageGui epbManageGuiInstance;

	/**
	 * Create the application.
	 */
	private EpbManageGui(Person person) {
		this.person = person;
		initialize();
	}

	public static EpbManageGui getNewCustomerGuiInstance(Person person) {
		if (epbManageGuiInstance == null) {
			epbManageGuiInstance = new EpbManageGui(person);
		}
		epbManageGuiInstance.frame.setVisible(true);
		return epbManageGuiInstance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel.setBounds(0, 524, 800, 71);
		frame.getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(33, 59, 302, 420);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(12, 27, 327, 381);
		panel_1.add(lblNewLabel);
		GuiTools.setLogo(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(347, 59, 422, 420);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btnNewButton = new JButton("Accounts");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccountsGui.getAccountsGui();
			}
		});
		btnNewButton.setBounds(133, 54, 163, 41);
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Employees");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmployeesGui.getEmployeesGuiInstance();
			}
		});
		btnNewButton_1.setBounds(133, 122, 163, 41);
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_3 = new JButton("Transactions");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new TransactionHistoryGui();
			}
		});
		btnNewButton_3.setBounds(133, 187, 163, 41);
		panel_2.add(btnNewButton_3);

		JButton btnOpenChatRoom = new JButton("Open Chat Room");
		btnOpenChatRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChatRoom chroom = new ChatRoom(person);
				thread = new Thread(chroom);
				thread.start();
			}
		});
		btnOpenChatRoom.setBounds(133, 247, 163, 41);
		panel_2.add(btnOpenChatRoom);

		JButton btnNewButton_5 = new JButton("Log out");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EpbSystemLogInGui();
				frame.dispose();
			}
		});
		btnNewButton_5.setBounds(638, 17, 117, 25);
		frame.getContentPane().add(btnNewButton_5);

		GuiTools.centerFrame(frame);
		frame.setVisible(true);
	}
}
