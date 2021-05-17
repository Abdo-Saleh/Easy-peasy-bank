package classes;

import java.util.ArrayList;

public class JointAccount extends Account {

	private int firstEntityId;
	private int secondEntityId;

	/**
	 * 
	 * @author abd alrahman saleh 
	 * @param firstEntityId
	 * @param secondEntityId
	 */
	public JointAccount(int firstEntityId, int secondEntityId) {
		super();
		this.firstEntityId = firstEntityId;
		this.secondEntityId = secondEntityId;
	}

	public int getFirstEntityId() {
		return firstEntityId;
	}

	public void setFirstEntityId(int firstEntityId) {
		this.firstEntityId = firstEntityId;
	}

	public int getSecondEntityId() {
		return secondEntityId;
	}

	public void setSecondEntityId(int secondEntityId) {
		this.secondEntityId = secondEntityId;
	}

}
