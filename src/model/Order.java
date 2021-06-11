package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	
	//Relations
	private Client client;
	private Employee employee;
	private List<Product>products;
	
	//Constructor
	public Order() {
		products = new ArrayList<Product>();
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
