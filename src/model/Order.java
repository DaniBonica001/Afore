package model;

import java.io.Serializable;
//import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	//Relations
	private Client client;
	private Employee employee;
	private List<Product>products;
	
	//Constructor #1
	public Order(Employee emp,List<Product> products) {
		employee = emp;
		this.products = products;
	}	

	
	//Getters and setters
	public void setClient(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	//***********************************************************************************************************************
}
