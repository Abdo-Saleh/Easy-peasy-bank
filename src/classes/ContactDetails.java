package classes;

import java.util.ArrayList;

import validation.IValidatable;
import validation.IValidator;

public class ContactDetails implements IValidatable<ContactDetails> {

	private String email;
	private String phoneNumber;
	protected String dateOfBirth;

	// Creating HAS-A relationship with Address class
	private Address address;

	/**
	 * 
	 * @author abd alrahman saleh it represents the contact details for every person
	 * @param email
	 * @param phone number
	 * @param date  of birth
	 */
	// constructor injection where i inject the dependency address.
	public ContactDetails(String email, String phoneNumber, String dateOfBirth) {
		super();
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	// setter injection for address dependency
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public boolean Validate(IValidator<ContactDetails> validator, ArrayList<String> brokenRules) {
		brokenRules = validator.brokenRules(this);
		return validator.isValid(this);
	}

	// Street name + number
	// Postal code + Town
	// COUNTRY (if it's on abroad)
	//
	// Nezábudková 3084/25
	// 84545 Bratislava
	// Slovensko

	public class Address {
		private String street;
		private String postalCode;
		private String Country;

		/**
		 * 
		 * @author abd alrahman saleh 
		 * it represents the Address of every person
		 * @param email
		 * @param phone number
		 * @param date  of birth
		 */
		public Address(String street, String postalCode, String country) {
			super();
			this.street = street;
			this.postalCode = postalCode;
			Country = country;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}

		public String getCountry() {
			return Country;
		}

		public void setCountry(String country) {
			Country = country;
		}

	}
}
