package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Restaurant {
	
	//Relations
	private List<Product>products;
	private List<FoodDelivery>deliveries;
	private Client firstClient;
	
	
	//Constructor
	public Restaurant() {
		products = new ArrayList<Product>();
	}
	
	//Getters and Setters
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setDeliveries(List<FoodDelivery> deliveries) {
		this.deliveries = deliveries;
	}
	
	public List<FoodDelivery> getDeliveries(){
		return deliveries;
	}

	public void setFirstClient(Client firstClient) {
		this.firstClient = firstClient;
	}

	public Client getFirstClient() {
		return firstClient;
	}	
	
	//***********************************
	
	public Product findProduct(String id) {
		Product product=null;
		boolean exit = false;
		for (int i=0;i<products.size() && !exit;i++) {
			if (products.get(i).getId().equals(id)) {
				exit = true;
				product = products.get(i);
			}
		}
		return product;
	}

	public void addProduct(String id,String name,String category,String size,String price,int available,String description) {
		Product findProduct = findProduct(id);		
		if (findProduct==null) {
			products.add(new Product(id, name, category, size, price, available, description));
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Creaci�n del producto");
			alert.setHeaderText("El producto ha sido creado");
			alert.setContentText("El producto con id "+id+" ha sido creado exitosamente.");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creaci�n del producto");
			alert.setHeaderText("El producto ya existe");
			alert.setContentText("El producto con id "+id+" ya se ha creado.");
			alert.showAndWait();
		}
	}

}
