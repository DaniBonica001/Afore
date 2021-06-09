package model;

import java.io.Serializable;

public class Chef extends Employee implements Serializable{

	private static final long serialVersionUID = 1;
	//Attributes
	private String dishes;
	
	//Constructor
	public Chef(String user, String pass, String name, String lastName, String id, String phone,String dishes) {
		super(user, pass, name, lastName, id, phone);
		this.dishes = dishes;		
	}

	//Getters and Setters
	
	public void setDishes(String dishes) {
		this.dishes = dishes;
	}
	
	public String getDishes() {
		return dishes;
	}
	
	//**********************************************************************************************************************
}
