package guiclient;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountGui {

	private JFrame frame;
	/**
	 * Create the application.
	 */
	public AccountGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(22, 28, 346, 423);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AccountGui.class.getResource("/Images/mylogo.png")));
		label.setBounds(12, 12, 279, 399);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(391, 28, 385, 423);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("New Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(119, 67, 175, 41);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit Customer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(119, 138, 175, 41);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete Customer");
		btnNewButton_2.setBounds(119, 208, 175, 41);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Block Customer");
		btnNewButton_3.setBounds(119, 275, 175, 41);
		panel_1.add(btnNewButton_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_2.setBounds(0, 457, 800, 68);
		frame.getContentPane().add(panel_2);
		
		GuiTools.centerFrame(frame);
	}

}
