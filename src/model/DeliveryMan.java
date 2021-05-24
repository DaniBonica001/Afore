package model;

public class DeliveryMan extends Employee{

	//Attributes
	private int amountOrders;
	
	//Constructor
	public DeliveryMan(String user, String pass, String name, String lastName, String id, String phone,int orders) {
		super(user, pass, name, lastName, id, phone);
		amountOrders = orders;		
	}
	
	//Getters and Setters

	public void setAmountOrders(int amountOrders) {
		this.amountOrders = amountOrders;
	}
		
	public int getAmountOrders() {
		return amountOrders;
	}
	
	//****************************************************************************************************************************
}
