package classes;

import java.util.HashMap;
import java.util.Map;

public class Person {

	protected int id;
	protected String fullName;
	protected String nationalID;
	protected String userName;
	protected String password;
	protected String gender;
	protected String nationality;
	protected boolean blocked;
	
	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	protected ContactDetails contactDetails;

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNationalID() {
		return nationalID;
	}

	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	public Map<String, String> printImportantInfo() {
		Map<String, String> importantInformation = new HashMap<>();
		importantInformation.put("username", userName);
		importantInformation.put("password", password);
		importantInformation.put("email", contactDetails.getEmail());
		importantInformation.put("Id", String.valueOf(id));

		return importantInformation;
	}

	@Override
	public String toString() {
		return fullName + " " + gender + " " + nationality;
	}

}
