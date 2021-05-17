package exceptions;

public class InsufficientCredit extends Exception {

	private String balance;

	public InsufficientCredit(String balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "MinimalBalanceException [balance=" + balance + "]";
	}
}
