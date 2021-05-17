package classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.Admin;
import classes.Customer;
import classes.Employee;



public class Admin extends Person {
	
	private List<Employee> subordinates;
	private String salary;
	private String office;
	public List<Employee> getSubordinates() {
		return subordinates;
	}
	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	
	@Override
	public Map<String, String> printImportantInfo() {
		Map<String,String> importantInformation = new HashMap<>();
		importantInformation.put("username", userName);
		importantInformation.put("password", password);
		importantInformation.put("email", contactDetails.getEmail());
		importantInformation.put("office", office);
		importantInformation.put("salary", salary);
		importantInformation.put("Id", String.valueOf(id));

		return importantInformation;
		
	}
}
