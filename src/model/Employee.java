package model;

import java.io.Serializable;

public abstract class Employee extends SystemUser implements Serializable{
	
	private static final long serialVersionUID = 1;
	//Attributes
	private String name;
	private String lastName;
	private String id;
	private String phone;
	
	//Relations 
	private Condition condition;
	
	//Constructor
	public Employee(String user, String pass,String name,String lastName,String id,String phone) {
		super(user, pass);
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		this.phone = phone;		
		condition = Condition.ACTIVE;
	}

	//Getters and Setters
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	//********************************************************************************************************************


	
	
	
}
