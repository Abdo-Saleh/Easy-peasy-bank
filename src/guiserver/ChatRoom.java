package guiserver;

import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import classes.Employee;
import classes.Person;
import guiclient.GuiTools;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ChatRoom implements Runnable {

	private static JFrame frame;
	private JTextField textField;
	static JTextArea textArea;
	static ServerSocket serversocket;
	static Socket socket;
	private static Person person;
	static DataInputStream in;
	static DataOutputStream out;

	/**
	 * Create the application.
	 * 
	 */
	public ChatRoom(Person person) {
		this.person = person;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void closeWindow(WindowEvent e) {
		JFrame frame = (JFrame) e.getSource();

		int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit Call Center Service?",
				"Exit Application", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {
			try {
				serversocket.close();
				if (socket != null)
					socket.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, "Unexpected Error, try to exit later", "ERROR MESSAGE",
						JOptionPane.ERROR_MESSAGE);
			}
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Call Center Service");
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

		textArea = new JTextArea();
		textArea.setBounds(12, 12, 557, 226);
		panel.add(textArea);

		textField = new JTextField();
		textField.setBounds(12, 245, 413, 44);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String msgOut = "";
					msgOut = textField.getText().trim();
					msgOut = person.getFullName() + ": " + msgOut;
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
		btnNewButton.setBounds(458, 244, 81, 44);
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_1.setBounds(0, 300, 600, 75);
		frame.getContentPane().add(panel_1);
		GuiTools.centerFrame(frame);
	}

	@Override
	public void run() {
		String msgIn = "";
		try {
			// server starts at 8888 port
			serversocket = new ServerSocket(8111);
			socket = serversocket.accept();

			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());

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
