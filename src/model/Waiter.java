package model;

import java.io.Serializable;

public class Waiter extends Employee implements Serializable{

	private static final long serialVersionUID = 1;
	//Attributes
	private int tables;

	//Constructor
	public Waiter(String user, String pass, String name, String lastName, String id, String phone,int tables) {
		super(user, pass, name, lastName, id, phone);
		this.tables = tables;		
	}

	//Getters and Setters
	public void setTables(int tables) {
		this.tables = tables;
	}
	
	public int getTables() {
		return tables;
	}
	
	//***********************************************************************************************************************


	
}
