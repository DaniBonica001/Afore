package model;

import java.io.Serializable;

public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	//Attributes
	private String name;
	private String id;
	private String address;
	private String phone;
	private String observations;
	
	//Relations
	private Client next;
	private Client previous;
	private Condition condition;
	
	//Constructor
	public Client(String name,String id,String address,String phone,String obs) {
	this.name = name;
	this.id = id;
	this.address = address;
	this.phone = phone;
	observations = obs;		
	condition = Condition.ACTIVE;
	}

	//Getters and Setters
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getAddress() {
		return address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getObservations() {
		return observations;
	}

	public void setNext(Client next) {
		this.next = next;
	}
	
	public Client getNext() {
		return next;
	}

	public void setPrevious(Client previous) {
		this.previous = previous;
	}

	public Client getPrevious() {
		return previous;
	}
	
	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Condition getCondition() {
		return condition;
	}
	
	//*****************************************************************************************

}
