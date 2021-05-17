package guiserver;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import classes.Customer;
import classes.Person;
import classes.Transaction;
import data.Database;
import guiclient.GuiTools;

public class TransactionHistoryGui {

	DefaultTableModel model;

	TransactionHistoryGui() {
		JFrame frame;
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 539);
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
		panel_1.setBounds(297, 12, 691, 431);
		model = new DefaultTableModel();

		String headers[] = { "ID", "from", "to", "date", "status", "transactionType", "Client" };

		JTable jtable = new JTable();
		jtable.setBounds(30, 40, 400, 300);
		JScrollPane sp = new JScrollPane(jtable);

		model.setColumnIdentifiers(headers);

		initTable();

		jtable.setModel(model);
		panel_1.add(sp);

		frame.getContentPane().add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("OptionPane.errorDialog.border.background"));
		panel_2.setBounds(0, 448, 1000, 66);
		frame.getContentPane().add(panel_2);
		GuiTools.centerFrame(frame);
		GuiTools.setLogo(lblNewLabel);
		frame.setVisible(true);

		frame.setTitle("Transaction history");

	}

	public void initTable() {
		for (int i = 0; i < Database.getTransactions().size(); i++)
			model.addRow(new Object[] { Database.getTransactions().get(i).getId(),
					Database.getTransactions().get(i).getFrom(), Database.getTransactions().get(i).getTo(),
					Database.getTransactions().get(i).getDate(), Database.getTransactions().get(i).getStatus(),
					Database.getTransactions().get(i).getTransactionType(),
					Database.getTransactions().get(i).getPerson().getFullName() });
	}
}