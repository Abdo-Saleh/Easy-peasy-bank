package validation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.ContactDetails;

public class ContactDetailsValidator implements IValidator<ContactDetails> {

	@Override
	public boolean isValid(ContactDetails entity) {
		return brokenRules(entity).size() <= 0;
	}

	@Override
	public ArrayList<String> brokenRules(ContactDetails entity) {

		ArrayList<String> brokenRules = new ArrayList<String>();

		if (!isValidEmail(entity.getEmail())) {
			brokenRules.add("email must be in form of exampl@example.com");
		}
		if (!isValidAddress(entity.getAddress())) {
			brokenRules.add("Address must be in form (345 street name), Country, 12345");
		}

		if (!isValidPhoneNumber(entity.getPhoneNumber())) {
			brokenRules.add("Phone Number must be in form of +421 xxx xxx xxx");
		}
		if (!isValidDate(entity.getDateOfBirth())) {
			brokenRules.add("Date birh mus be in form of DD/MM/YYYY");
		}
		return brokenRules;
	}

	public boolean isValidAddress(ContactDetails.Address address) {

		String regexPostalCode = "^\\d{5}-\\d{4}|\\d{5}|[A-Z]\\d[A-Z] \\d[A-Z]\\d$";
		String regexstreet = "\\d{1,3}.?\\d{0,3}\\s[a-zA-Z]{2,30}";
		String regexCountry = "^[a-zA-Z]+$";

		// 12345
		Pattern patternPostalCode = Pattern.compile(regexPostalCode);
		// 234 Wodonga
		Pattern patternstreet = Pattern.compile(regexstreet);
		// slovakia
		Pattern patternCountry = Pattern.compile(regexCountry);

		Matcher matcherPostalCode = patternPostalCode.matcher(address.getPostalCode());
		Matcher matcherstreet = patternstreet.matcher(address.getStreet());
		Matcher matcherCountry = patternCountry.matcher(address.getCountry());

		return matcherPostalCode.matches() && matcherCountry.matches() && matcherstreet.matches();
	}

	public boolean isValidEmail(String email) {
		/*
		 * pattern example: user@domain.com : true user@domain.co.in : true
		 * user.name@domain.com : true user_name@domain.com : true
		 * username@yahoo.corporate.in : true
		 * 
		 * .username@yahoo.com : false username@yahoo.com. : false username@yahoo..com :
		 * false username@yahoo.c : false username@yahoo.corporate : false
		 */

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public boolean isValidPhoneNumber(String phone) {

		String regexphone = "^(\\(?\\+?[0-9]+\\)?)?[0-9_\\- \\(\\)]+$";
		Pattern pattern = Pattern.compile(regexphone);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	public boolean isValidDate(String date) {
		String regexDate = "^\\d{1,2}\\/\\d{1,2}\\/\\d{4}$";
		Pattern pattern = Pattern.compile(regexDate);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}

//	public static void main(String args[])
//	{
//		ContactDetailsValidator s = new ContactDetailsValidator();
//		Address add = new Address("234 Wodonga", "12345", "SLovakia");
//		System.out.println(s.isValidPhoneNumber("+421919237636"));
//		
//	}
}
