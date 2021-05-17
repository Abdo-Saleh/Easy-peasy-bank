package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import data.Database;

public class Customer extends Person {

	// Creating HAS-A relationship with Accounts class
	private ArrayList<Account> accounts;

	private String pinCode;

	/**
	 * 
	 * @author abd alrahman saleh 
	 * it represents the Customer with attached accounts, normal account and joint account
	 * @param accounts arraylist of accounts

	 */
	public Customer(ArrayList<Account> accounts) {
		this.id = Database.numOfCustomers();
		this.accounts = accounts;
		this.blocked = false;
		this.pinCode = "0000";
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	public boolean addAccount(Account account) {
		return accounts.add(account);
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public Map<String, String> printImportantInfo() {
		Map<String, String> importantInformation = new HashMap<>();
		importantInformation.put("username", userName);
		importantInformation.put("password", password);
		importantInformation.put("email", contactDetails.getEmail());
		importantInformation.put("Id", String.valueOf(id));

		return importantInformation;

	}
}
