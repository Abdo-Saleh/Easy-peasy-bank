package guiclient;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import classes.Account;
import classes.Customer;
import classes.Person;
import guiserver.ChatRoom;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class CustomerService implements Runnable {

	private static JFrame frame;
	private JTextField textField;
	static JTextArea textArea;
	static Socket socket;
	private Customer customer;
	static DataInputStream in;
	static DataOutputStream out;
	private static CustomerService customerservice;

	/**
	 * Create the application.
	 */
	public CustomerService(Customer customer) {
		this.customer = customer;
		initialize();
	}

	private void closeWindow(WindowEvent e) {
		JFrame frame = (JFrame) e.getSource();

		int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit Customer Service?",
				"Exit Application", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, "Unexpected Error, try to exit later", "ERROR MESSAGE",
						JOptionPane.ERROR_MESSAGE);
			}
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Customer Service");
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeWindow(e);
			}
		});
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("List.background"));
		panel.setBounds(12, 0, 588, 301);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		GuiTools.centerFrame(frame);

		textArea = new JTextArea();
		textArea.setBounds(12, 12, 557, 226);
		panel.add(textArea);

		textField = new JTextField();
		textField.setBounds(12, 245, 428, 44);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msgOut = "";
					msgOut = textField.getText().trim();
					msgOut = customer.getFullName() + ": " + msgOut;
					out.writeUTF(msgOut);
					textArea.setText(textArea.getText().trim() + "\n" + msgOut);
					textField.setText("");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "Unexpected Error, try to send again", "ERROR MESSAGE",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(452, 245, 117, 44);
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_1.setBounds(0, 300, 600, 75);
		frame.getContentPane().add(panel_1);
	}

	@Override
	public void run() {
		try {
			socket = new Socket("127.0.0.1", 8111);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			String msgIn = "";
			while (!msgIn.equals("exit")) {
				msgIn = in.readUTF();
				textArea.setText(textArea.getText().trim() + "\n" + msgIn);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Unexpected Error, Connection lost", "ERROR MESSAGE",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
