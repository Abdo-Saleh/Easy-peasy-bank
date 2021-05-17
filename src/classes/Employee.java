package classes;

import java.util.HashMap;
import java.util.Map;

import data.Database;

public class Employee extends Person{
		
	private String salary;
	
	public Employee()
	{
		this.id = Database.numOfEmployees() + 1;
	}
	
	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	@Override
	public Map<String, String> printImportantInfo() {
		Map<String,String> importantInformation = new HashMap<>();
		importantInformation.put("username", userName);
		importantInformation.put("password", password);
		importantInformation.put("email", contactDetails.getEmail());
		importantInformation.put("salary", salary);
		return importantInformation;
		
	}
	
}
