package exceptions;

public class MinimalBalanceException extends Exception {

	private String balance;

	public MinimalBalanceException(String balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "MinimalBalanceException [balance=" + balance + "]";
	}

}
