package validation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.Account;

public class AccountBalanceValidator implements IValidator<Account> {

	@Override
	public boolean isValid(Account entity) {
		return brokenRules(entity).size() <= 0;
	}

	@Override
	public ArrayList<String> brokenRules(Account entity) {
		// TODO Auto-generated method stub
		ArrayList<String> brokenRules = new ArrayList<String>();

		if (!isValidBalance(entity.getBalance())) {
			brokenRules.add("Balance has to be a numerical value in one of these forms: 123 | 3.14159 | 0.234");
			return brokenRules;
		}
		
		
		if (Double.parseDouble(entity.getBalance()) < Double.parseDouble(entity.minimumBalance)) {
			brokenRules.add("Balance has to be more than "+ entity.minimumBalance+" €");
		}
		
		return brokenRules;
	}

	public static boolean isValidBalance(String balance) {

		String regexBalance = "^\\d+\\.?\\d*$";
		Pattern pattern = Pattern.compile(regexBalance);
		Matcher matcher = pattern.matcher(balance);

		return matcher.matches();
	}
	
//	public static void main(String args[])
//		{
//	//		123 | 3.14159 | 0.234
//			System.out.println(isValidBalance("0.11"));
//			false
//		}
}
