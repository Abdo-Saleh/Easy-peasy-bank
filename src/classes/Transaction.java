package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import data.Database;


public class Transaction {

	private int id;
	private String from;
	private String to;
	private String date;
	private String status;
	private String transactionType;
	private Person person;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	// create inner enumeration which is a special "class" that represents a group
	// of constants
	enum StatusList {
		DONE, PENDING, REFUSED
	}

	enum TransactionType {
		WITHDRAW, DEPOSIT
	}

	/**
	 * 
	 * @author abd alrahman saleh
	 * @param from            it refers to the sender's iban
	 * @param to              it refers to the recivers's iban
	 * @param transactionType int value which refers to withDraw = 0, deposit = 1
	 */
	public Transaction(String from, String to, int transactionType, Person person) {
		this.id = Database.numOfTransactions();
		this.from = from;
		this.to = to;
		this.date = getCurrentDateAndTime();
		this.status = StatusList.DONE.toString();
		this.transactionType = TransactionType.values()[transactionType].toString();
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	private String getCurrentDateAndTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}
}
