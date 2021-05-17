package data;

import java.util.ArrayList;

import classes.Account;
import classes.Admin;
import classes.ContactDetails;
import classes.Customer;
import classes.Employee;
import classes.JointAccount;
import classes.Person;
import classes.Transaction;
import guiserver.EpbManageGui;

public class Database {

	private static boolean debugMode = true;

	// private static Database databaseInstance;
	
	/**
	 * this is the database part which is going to store the data for the application virtually
	 */
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private static ArrayList<Employee> employees = new ArrayList<Employee>();
	private static ArrayList<Admin> admins = new ArrayList<Admin>();
	private static ArrayList<Account> accounts = new ArrayList<Account>();
	private static ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	/**
	 * sender credentials 
	 */
	private static String sender = "epbuser2020@gmail.com";
	private static String password = "weWillBeBetter2020";

	// Transaction API
	/**
	 * @return number of transcations in the database + 1;
	 */
	public static int numOfTransactions() {
		if (debugMode)
			System.out.println("retrived transactions");

		return transactions.size() + 1;
	}

	/**
	 * @param transation to be added to the database 
	 * @return boolean detect if it stored correctly or not
	 */
	public static boolean addTransaction(Transaction transaction) {
		if (debugMode)
			System.out.println("added Transaction[ " + transaction.getId() + " ] from " + transaction.getFrom()
					+ " => to " + transaction.getTo() + " status: " + transaction.getStatus() + " type "
					+ transaction.getTransactionType() + " date " + transaction.getDate() + " to the database");

		return transactions.add(transaction);
	}

	/**
	 * @param customer 
	 * @return ArrayList<Transaction> of certain customer
	 */
	public static ArrayList<Transaction> getCustomerTransactions(Customer customer) {
		ArrayList<Transaction> resutl = new ArrayList<Transaction>();

		for (Transaction transaction : Database.getTransactions()) {
			if (transaction.getPerson().getId() == customer.getId())
				resutl.add(transaction);
		}
		return resutl;
	}
	/**
	 *
	 * @return ArrayList<Transaction> all the transactions
	 */
	public static ArrayList<Transaction> getTransactions() {
		if (debugMode)
			System.out.println("retrived Transactions");

		return transactions;
	}

	// Customers API
	/**
	 *
	 * @return ArrayList<Customer> all the customers
	 */
	public static ArrayList<Customer> getCustomers() {
		if (debugMode)
			System.out.println("retrived customers");

		return customers;
	}

	/**
	 * @param customer
	 * @return ArrayList<Account> of certain customer
	 */
	public static ArrayList<Account> getCustomerAccounts(Customer customer) {
		return customer.getAccounts();
	}

	/**
	 * @param customer to be updated
	 * @return nothing
	 */
	public static void updateCustomer(Customer customer) {
		if (debugMode)
			System.out.println("Customer:" + customer.getFullName() + " profile has been updated successfully ");

		Database.getCustomers().set(customer.getId() - 1, customer);
	}

	public static int numOfCustomers() {
		if (debugMode)
			System.out.println("retrived number of customers");

		return customers.size() + 1;
	}

	public static boolean addCustomer(Customer customer) {
		if (debugMode)
			System.out.println("added customer[ " + customer.getId() + " ]to the database");

		return customers.add(customer);
	}

	public static Customer lookUpForCustomer(String nationalId) {
		for (Customer customer : Database.getCustomers()) {
			if (customer.getNationalID().equalsIgnoreCase(nationalId)) {
				return customer;
			}
		}
		return null;
	}

	///////////// End Customer APi////////////

	// Accounts API
	public static String getSender() {
		if (debugMode)
			System.out.println("retrived sender");

		return sender;
	}

	public static ArrayList<Account> getAccounts() {
		if (debugMode)
			System.out.println("retrived accounts");

		return accounts;
	}

	public static String getSenderPassword() {
		if (debugMode)
			System.out.println("retrived sender's password");

		return password;
	}

	public static boolean isIBanUnique(String iban) {
		if (debugMode)
			System.out.println("check if the iban is unique");

		for (Account account : accounts) {
			return !account.getIban().equalsIgnoreCase(iban);
		}
		return true;
	}

	public static boolean addAccount(Account account) {
		if (debugMode)
			System.out.println("added account[ " + account.getId() + " ]to the database");

		return accounts.add(account);
	}

	public static int numOfAccounts() {
		if (debugMode)
			System.out.println("get number of accounts");

		return accounts.size() + 1;
	}

	// Admin API
	public static int numOfAdmins() {
		if (debugMode)
			System.out.println("get number of admins");

		return admins.size();
	}

	// EMPLOYEE API
	public static boolean addEmployee(Employee employee) {
		if (debugMode)
			System.out.println("added employee[ " + employee.getId() + " ]to the database");

		return employees.add(employee);
	}

	public static int numOfEmployees() {

		return Database.employees.size() + 1;
	}

	public static void updateEmployee(Employee employee) {
		if (debugMode)
			System.out.println("Employee:" + employee.getFullName() + " profile has been updated successfully ");

		Database.getEmployees().set(employee.getId() - 1, employee);
	}

	public static Employee lookUpForEmployee(String nationalId) {
		for (Employee employee : Database.getEmployees()) {
			if (employee.getNationalID().equalsIgnoreCase(nationalId)) {
				return employee;
			}
		}
		return null;
	}

	public static ArrayList<Employee> getEmployees() {
		return employees;
	}

	/////////////////// End of Employee API/////////////////////////
	public static Person checkUserNameAndPasswordEmployee(String username, String password) {
		for (Person employee : employees) {
			if (employee.getUserName().equalsIgnoreCase(username) && employee.getPassword().equalsIgnoreCase(password))
				return employee;
		}

		for (Person admin : admins) {
			if (admin.getUserName().equalsIgnoreCase(username) && admin.getPassword().equalsIgnoreCase(password))
				return admin;
		}
		return null;
	}

	public static Customer checkUserNameAndPasswordCustomer(String username, String password) {
		for (Customer customer : customers) {
			if (customer.getUserName().equalsIgnoreCase(username) && customer.getPassword().equalsIgnoreCase(password))
				return customer;
		}
		return null;
	}

	public static void initializeDb() {
		if (debugMode)
			System.out.println("init database");

		ContactDetails contactDetails = new ContactDetails(sender, "+421919237636", "14/2/1993");
		ContactDetails.Address address = contactDetails.new Address("123 example", "12345", "Slovakia");
		contactDetails.setAddress(address);
		Admin admin = new Admin();
		admin.setFullName("EPB Admin");
		admin.setUserName("epbAdmin");
		admin.setPassword("11223344");
		admin.setNationality("Syria");
		admin.setGender("Male");
		admin.setId(admins.size() + 1);
		admin.setSalary("1000.0");
		admin.setOffice("Office-1");
		admin.setContactDetails(contactDetails);

		admins.add(admin);

		ContactDetails contactDetailsCustomer = new ContactDetails("engabdosaleh1993@gmail.com", "+42191543568",
				"14/2/1993");
		ContactDetails.Address addresscustomer = contactDetailsCustomer.new Address("321 example", "53245", "Slovakia");
		contactDetailsCustomer.setAddress(addresscustomer);
		
		Account normalaccount1 = new Account();
		ArrayList<Account> user1accounts = new ArrayList<Account>();

		user1accounts.add(normalaccount1);

		Customer customer = new Customer(user1accounts);
		customer.setContactDetails(contactDetailsCustomer);
		customer.setUserName("user1");
		customer.setPassword("11223344");
		customer.setNationalID("xa123");
		customer.setFullName("customer 1");
		customer.setPinCode("0000");
		customer.setNationality("Syria");
		customer.setGender("Male");

		accounts.add(normalaccount1);
		customers.add(customer);

		Account normalaccount2 = new Account();
		ArrayList<Account> user2accounts = new ArrayList<Account>();
		user2accounts.add(normalaccount2);

		Customer customer2 = new Customer(user2accounts);
		customer2.setContactDetails(contactDetailsCustomer);
		customer2.setUserName("user2");
		customer2.setPassword("11223344");
		customer2.setNationalID("xa1234");
		customer2.setFullName("customer 2");
		customer2.setPinCode("1234");
		customer2.setNationality("Syria");
		customer2.setGender("Male");

		accounts.add(normalaccount2);
		customers.add(customer2);

		Account jointaccount = new JointAccount(customer.getId(), customer2.getId());
		customer.addAccount(jointaccount);
		customer2.addAccount(jointaccount);
		accounts.add(jointaccount);

	}

}
