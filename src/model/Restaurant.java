package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Restaurant {
	
	//Relations
	private List<Product>products;
	
	//Constructor
	public Restaurant() {
		products = new ArrayList<Product>();
	}
	
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

}
