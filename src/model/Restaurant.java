package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Restaurant {
	
	//Relations
	private List<Product>products;
	private List<FoodDelivery>deliveries;
	private List<Employee>employees;
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
	
	public void setEmployees(List<Employee>employees) {
		this.employees = employees;
	}
	
	public List<Employee> getEmployees(){
		return employees;
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
			alert.setTitle("Creación del producto");
			alert.setHeaderText("El producto ha sido creado");
			alert.setContentText("El producto con id "+id+" ha sido creado exitosamente.");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creación del producto");
			alert.setHeaderText("El producto ya existe");
			alert.setContentText("El producto con id "+id+" ya se ha creado.");
			alert.showAndWait();
		}
	}

	public void deleteProduct(String id) {	
		Product findProduct= findProduct(id);
		
		if(findProduct!=null) {
			products.remove(findProduct);
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al encontrar producto");
			alert.setHeaderText("Producto inexistente");
			alert.setContentText("El producto con id "+id+" no está actualmente en la lista de productos del restaurante.");
			alert.showAndWait();
		}
	}
	
	public void updateProduct(String id,String name,String category,String size, String price, int availability,String description) {
		Product findProduct= findProduct(id);
		
		if(findProduct!=null) {
			findProduct.setName(name);
			findProduct.setCategory(category);
			findProduct.setSize(size);
			findProduct.setPrice(price);
			findProduct.setAvailability(availability);
			findProduct.setDescription(description);
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al encontrar producto");
			alert.setHeaderText("Producto inexistente");
			alert.setContentText("El producto con id "+id+" no está actualmente en la lista de productos del restaurante.");
			alert.showAndWait();
		}
		
	}


}
