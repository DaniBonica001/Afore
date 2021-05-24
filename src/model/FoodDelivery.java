package model;

public class FoodDelivery {
	
	//Constants
	public final static String PICK_UP = "Recoger en el restaurante";
	public final static String SEND = "Programas env�o";
	
	//Attributes
	private String optionDomicile;
	private String clientName;
	private int timeShipment;
	
	//Relations
	private Client objClient;
	
	//Constructor #1
	public FoodDelivery(String domicile,String client,Client objClient,int time) {
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

	public void setTimeShipment(int timeShipment) {
		this.timeShipment = timeShipment;
	}

	public int getTimeShipment() {
		return timeShipment;
	}

	public void setObjClient(Client objClient) {
		this.objClient = objClient;
	}

	public Client getObjClient() {
		return objClient;
	}
	
	//************************************************************************************************++

	
	

	

}
