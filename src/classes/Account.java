package classes;

import java.util.ArrayList;
import java.util.Random;

import data.Database;
import exceptions.InsufficientCredit;
import validation.IValidatable;
import validation.IValidator;

/**
 * 
 * @author abd alrahman saleh 
 * implemets IValidatable so we can make validation
 *         on every account
 */
public class Account implements IValidatable<Account> {

	public static String minimumBalance = "10.0";

	protected int Id;
	protected String Iban;
	protected String balance;

	public Account() {
		this.Id = Database.numOfAccounts();
		this.Iban = randomUniqueIbanGenerater();
		// to be thrown an own exception when it's less than 10 euro
		this.balance = "10.0";

	}

	/**
	 * 
	 * @param amount it refers the amount to be withdrawn it will withdrawn amount
	 *               of money from an account
	 */
	public void withDraw(String amount) throws InsufficientCredit {
		if (Double.parseDouble(this.getBalance()) < Double.parseDouble(amount)) {
			throw new InsufficientCredit(this.getBalance());
		} else {
			// set new balance
			this.setBalance(Double.toString(Double.parseDouble(this.getBalance()) - Double.parseDouble(amount)));
		}
	}

	/**
	 * 
	 * @param amount it refers the amount to be deposit
	 */
	public void deposit(String amount) {
		this.setBalance(Double.toString(Double.parseDouble(this.getBalance()) + Double.parseDouble(amount)));
	}

	/**
	 * it will generate a unique iban
	 * 
	 * @return string unique IBAN to be used
	 */
	private String randomUniqueIbanGenerater() {
		Random rand = new Random();
		String randomlyGeneratedIban = "";
		int n, i;
		boolean isUnique = false;
		// checking if it's existed before
		while (!isUnique) {
			randomlyGeneratedIban = "SK90";
			// generating randomly 20 digit
			for (i = 0; i < 20; i++) {
				n = rand.nextInt(10) + 0;
				randomlyGeneratedIban += Integer.toString(n);
			}
			// connect to the database
			isUnique = Database.isIBanUnique(randomlyGeneratedIban);
		}

		return randomlyGeneratedIban;
	}

	/**
	 * it will align iban digits
	 * 
	 * @return string aligned iban digits
	 */
	private String alignIbanDigits(String Iban) {
		String alignedIban = "";
		for (int i = 0; i < 24; i++) {
			if (i % 4 == 0)
				alignedIban += " ";
			alignedIban += Iban.charAt(i);
		}
		return alignedIban;
	}

	public int getId() {
		return Id;
	}

	public String getIban() {
		return Iban;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return alignIbanDigits(Iban);
	}

	@Override
	public boolean Validate(IValidator<Account> validator, ArrayList<String> brokenRules) {
		brokenRules = validator.brokenRules(this);
		return validator.isValid(this);
	}

}
