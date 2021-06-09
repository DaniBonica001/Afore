package model;

public class FoodDelivery {
	
	//Constants
	public final static String PICK_UP = "Recoger en el restaurante";
	public final static String SEND = "Programas envío";
	
	//Attributes
	private String optionDomicile;
	private String clientName;
	private double timeShipment;
	
	//Relations
	private Client objClient;
	private FoodDelivery left;
	private FoodDelivery right;
	
	//Constructor #1
	public FoodDelivery(String domicile,String client,Client objClient,double time) {
		optionDomicile = domicile;
		clientName = client;
		this.objClient = objClient;
		timeShipment = time;	
	}
	
	//Constructor #2
	public FoodDelivery(String domicile,String client,Client objClient) {
		optionDomicile = domicile;
		clientName = client;
		this.objClient = objClient;
	}

	//Getters and Setters 
	public void setOptionDomicile(String optionDomicile) {
		this.optionDomicile = optionDomicile;
	}
	
	public String getOptionDomicile() {
		return optionDomicile;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setTimeShipment(double timeShipment) {
		this.timeShipment = timeShipment;
	}

	public double getTimeShipment() {
		return timeShipment;
	}

	public void setObjClient(Client objClient) {
		this.objClient = objClient;
	}

	public Client getObjClient() {
		return objClient;
	}

	public FoodDelivery getLeft() {
		return left;
	}

	public void setLeft(FoodDelivery left) {
		this.left = left;
	}

	public FoodDelivery getRight() {
		return right;
	}

	public void setRight(FoodDelivery right) {
		this.right = right;
	}
	
	//************************************************************************************************++

	
	

	

}
