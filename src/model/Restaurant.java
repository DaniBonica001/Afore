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
		deliveries = new ArrayList<FoodDelivery>();
		employees = new ArrayList<Employee>();		
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
	
	public Employee findEmployee(String id) {
		Employee employee = null;
		boolean exit = false;
		for (int i=0;i<employees.size() && !exit;i++) {
			if (employees.get(i).getId().equals(id)) {
				exit = true;
				employee = employees.get(i);						
			}
		}
		return employee;
	}

	//Create a cashier employee
	public void addEmployee(String userCashier, String passCashier, String nameCashier, String lastNameCashier,
			String idCashier, String phoneCashier, boolean waiter) {
		
		Employee findEmployee = findEmployee(idCashier);
		
		if (findEmployee == null) {
			employees.add(new Cashier(userCashier,passCashier,nameCashier,lastNameCashier,idCashier,phoneCashier,waiter));
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Creación del empleado");
			alert.setHeaderText("El empleado ha sido creado");
			alert.setContentText("El empleado con id "+idCashier+" ha sido creado exitosamente.");
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creación del empleado");
			alert.setHeaderText("El empleado ya existe");
			alert.setContentText("El empleado con id "+idCashier+" ya se ha creado.");
			alert.showAndWait();
		}		
	}

	//Create a chef employee
	public void addEmployee(String userChef, String passChef, String nameChef, String lastNameChef, String idChef,
			String phoneChef, String dishes) {
		
		Employee findEmployee = findEmployee(idChef);
		
		if (findEmployee == null) {
			employees.add(new Chef (userChef,passChef,nameChef,lastNameChef,idChef,phoneChef,dishes));
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Creación del empleado");
			alert.setHeaderText("El empleado ha sido creado");
			alert.setContentText("El empleado con id "+idChef+" ha sido creado exitosamente.");
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creación del empleado");
			alert.setHeaderText("El empleado ya existe");
			alert.setContentText("El empleado con id "+idChef+" ya se ha creado.");
			alert.showAndWait();
		}
		
	}

	//Create a waiter employee
	public void addEmployee(String userWaiter, String passWaiter, String nameWaiter, String lastNameWaiter,
			String idWaiter, String phoneWaiter, int tables) {
		
		Employee findEmployee = findEmployee(idWaiter);
		
		if (findEmployee == null) {
			employees.add(new Waiter(userWaiter,passWaiter,nameWaiter,lastNameWaiter,idWaiter,phoneWaiter,tables));
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Creación del empleado");
			alert.setHeaderText("El empleado ha sido creado");
			alert.setContentText("El empleado con id "+idWaiter+" ha sido creado exitosamente.");
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creación del empleado");
			alert.setHeaderText("El empleado ya existe");
			alert.setContentText("El empleado con id "+idWaiter+" ya se ha creado.");
			alert.showAndWait();
		}		
	}

	//Create a delivery man employee
	public void addEmployeeDM(String userDM, String passDM, String nameDM, String lastNameDm, String idDM,
			String phoneDM, int orders) {
		
		Employee findEmployee = findEmployee(idDM);
		
		if (findEmployee == null) {
			employees.add(new DeliveryMan(userDM,passDM,nameDM,lastNameDm,idDM,phoneDM,orders));
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Creación del empleado");
			alert.setHeaderText("El empleado ha sido creado");
			alert.setContentText("El empleado con id "+idDM+" ha sido creado exitosamente.");
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creación del empleado");
			alert.setHeaderText("El empleado ya existe");
			alert.setContentText("El empleado con id "+idDM+" ya se ha creado.");
			alert.showAndWait();
		}	
		

		
	}


}
