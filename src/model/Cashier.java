package model;

public class Cashier extends Employee{
	
	//Attributes
	private boolean waiter;
	
	//Constructor
	public Cashier(String user, String pass, String name, String lastName, String id, String phone,boolean waiter) {
		super(user, pass, name, lastName, id, phone);
		this.waiter = waiter;		
	}
	
	//Getters and Setters

	public void setWaiter(boolean waiter) {
		this.waiter = waiter;
	}
	
	public boolean isWaiter() {
		return waiter;
	}
	
	//**************************************************************************************************************************
	
	
	
}
