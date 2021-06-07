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
	private List<Order>orders;
	private Client firstClient;
	
	
	//Constructor
	public Restaurant() {
		products = new ArrayList<Product>();
		deliveries = new ArrayList<FoodDelivery>();
		employees = new ArrayList<Employee>();	
		orders = new ArrayList<Order>();
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
	
	public void setOrders(List<Order>orders) {
		this.orders = orders;		
	}
	
	public List<Order> getOrders(){
		return orders;
	}

	public void setFirstClient(Client firstClient) {
		this.firstClient = firstClient;
	}

	public Client getFirstClient() {
		return firstClient;
	}	
	
	//***********************************************************************************************************************
	
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
	
	//M�todo de b�squeda binaria de un producto
	public Product findProductBinarySearch (String id) {
		bubbleSortProductById();
		int idToFind = Integer.parseInt(id);
		Product product = null;
		boolean found = false;
		int start = 0;
		int end = products.size()-1;
		
		while (start<=end && !found) {
			int middle = (start + end)/2;
			int changeId = Integer.parseInt(products.get(middle).getId());
			
			if (changeId == idToFind ) {
				found = true;
				product = products.get(middle);
			}else if (changeId > idToFind) {
				end = middle-1;
			}else if (changeId < idToFind) {
				start = middle+1;
			}
		}
		return product;
	}
	

	//Ordenamiento burbuja de productos por id - Ordena de menor a mayor
	public void bubbleSortProductById() {
		for (int i=0;i<products.size()-1;i++) {
			for (int j=0;j<products.size()-1;j++) {
				
				try {
					
					long id1 = Long.parseLong(products.get(j).getId());
					long id2 = Long.parseLong(products.get(j+1).getId());
					
					if (id1>id2) {
						Product temp = products.get(j);
						products.set(j,products.get(j+1));
						products.set(j+1,temp);					
					}
					
				}catch (NumberFormatException e) {
					e.printStackTrace();
				}				
			}
		}
		for (int i=0;i<products.size();i++) {
			System.out.println(products.get(i).getId()+", "+products.get(i).getName()+", "+products.get(i).getPrice());
		}
	}
	
	
	//Ordenamiento insertion de productos por precio - Ordena de menor a mayor - hilo
	public void insertionSortProductByPrice() {
		for (int i=0;i<products.size();i++) {	
			for (int j=i;j>0;j--) {

				long price1 = Long.parseLong(products.get(j).getPrice());
				long price2 = Long.parseLong(products.get(j-1).getPrice());
				System.out.println("p1: "+price1);
				System.out.println("p2: "+price2);
								
				if (price1<price2) {
					Product temp = products.get(j);
					products.set(j,products.get(j-1));
					products.set(j-1,temp);					
				}							
			}				
		}
		
		for (int i=0;i<products.size();i++) {
			System.out.println("Productos by insertion by price");
			System.out.println(products.get(i).getId()+", "+products.get(i).getName()+", "+products.get(i).getPrice());
		}
	}
	
	//Ordenamiento insertion de empleados por nombre - Ordena en orden alfab�tico - hilo
	public void insertionSortEmployeeByName() {
		for (int i=0;i<employees.size();i++) {
			for (int j=i;j>0;j--) {
				
				int answer = employees.get(j).getName().compareTo(employees.get(j-1).getName());
				
				if (answer == -1) {
					Employee temp = employees.get(j);
					employees.set(j, employees.get(j-1));
					employees.set(j-1, temp);					
				}
			}
		}
		
		for (int i=0;i<employees.size();i++) {
			System.out.println("Empleados by insertion Name");
			System.out.println(employees.get(i).getId()+", "+employees.get(i).getName());
		}
	}
	
		
	
	//Ordenamiento selection de Empleados por telefono
	public void selectionSortEmployeeByPhone() {
		for (int i=0;i<employees.size()-1;i++) {
			try {
				
				long  phoneMin = Long.parseLong(employees.get(i).getPhone());
				for (int j=i+1;j<employees.size();j++) {
					long phoneToCom = Long.parseLong(employees.get(j).getPhone());				
					if (phoneToCom < phoneMin) {
						Employee temp = employees.get(j);
						employees.set(j,employees.get(i));
						employees.set(i,temp);
					}
				}
				
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
		
		}
	}	
	
	//Ordenamiento selection de Empleados por apellido
	public void selectionSortEmployeeByLastName() {
		//Cuando se elimine un empleado, invoca este m�todo
		for (int i=0;i<employees.size()-1;i++) {
			for (int j=i+1;j<employees.size();j++) {
				if (employees.get(j).getLastName().compareTo(employees.get(i).getLastName()) == -1) {
					Employee temp = employees.get(j);
					employees.set(j,employees.get(i));
					employees.set(i,temp);
				}
			}		
		}
	}
	
	
	public void threadToSortProducts() {
		//System.out.println("Vengo al metodo en rest");
		//Restaurant rest = new Restaurant();		
		synchronized (products) {
			//System.out.println("Pase el sync");
			//System.out.println("products().size(): "+products.size());
			if (products.size()!=0) {
				//System.out.println("imprimo algo!!!");
				products.notify();				
			}
			//System.out.println("No entre al if");
		}
	}


	public void addProduct(String id,String name,String category,String size,String price,int available,String description) {
		Product findProduct = findProduct(id);		
		if (findProduct==null) {
			
			products.add(new Product(id, name, category, size, price, available, description));
			threadToSortProducts();			
			
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

	public void deleteProduct(String id) {	
		Product findProduct= findProduct(id);		
		if(findProduct!=null) {
			products.remove(findProduct);
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al encontrar producto");
			alert.setHeaderText("Producto inexistente");
			alert.setContentText("El producto con id "+id+" no est� actualmente en la lista de productos del restaurante.");
			alert.showAndWait();
		}
	}
	
	/*
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
			alert.setContentText("El producto con id "+id+" no est� actualmente en la lista de productos del restaurante.");
			alert.showAndWait();
		}
		
	}
	*/
	
	public Employee findEmployee(String id) {
		selectionSortEmployeeByPhone();
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
	
	
	public void threadToSortEmployees() {
		//System.out.println("Vengo al metodo en rest");
		//Restaurant rest = new Restaurant();		
		synchronized (employees) {
			//System.out.println("Pase el sync");
			//System.out.println("products().size(): "+products.size());
			if (employees.size()!=0) {
				//System.out.println("imprimo algo!!!");
				employees.notify();				
			}
			//System.out.println("No entre al if");
		}
	}


	//Create a cashier employee
	public void addEmployee(String userCashier, String passCashier, String nameCashier, String lastNameCashier,
			String idCashier, String phoneCashier, boolean waiter) {
		
		Employee findEmployee = findEmployee(idCashier);
		
		if (findEmployee == null) {
			employees.add(new Cashier(userCashier,passCashier,nameCashier,lastNameCashier,idCashier,phoneCashier,waiter));
			threadToSortEmployees();
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Creaci�n del empleado");
			alert.setHeaderText("El empleado ha sido creado");
			alert.setContentText("El empleado con id "+idCashier+" ha sido creado exitosamente.");
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creaci�n del empleado");
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
			threadToSortEmployees();
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Creaci�n del empleado");
			alert.setHeaderText("El empleado ha sido creado");
			alert.setContentText("El empleado con id "+idChef+" ha sido creado exitosamente.");
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creaci�n del empleado");
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
			threadToSortEmployees();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Creaci�n del empleado");
			alert.setHeaderText("El empleado ha sido creado");
			alert.setContentText("El empleado con id "+idWaiter+" ha sido creado exitosamente.");
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creaci�n del empleado");
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
			threadToSortEmployees();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Creaci�n del empleado");
			alert.setHeaderText("El empleado ha sido creado");
			alert.setContentText("El empleado con id "+idDM+" ha sido creado exitosamente.");
			alert.showAndWait();			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la creaci�n del empleado");
			alert.setHeaderText("El empleado ya existe");
			alert.setContentText("El empleado con id "+idDM+" ya se ha creado.");
			alert.showAndWait();
		}	
		

		
	}
	
		
	//M�todo recursivo
	public Client findLastClient(Client current) {
		if(current.getNext()==null) {
			return current;
		}
		else {
			return findLastClient(current.getNext());
		}

	}
	
	public Client findClient(String id) {
		Client client=firstClient;
		Client findClient=null;
		boolean salir=false;
		
		while(client!=null && !salir) {
		//PRIMERO HACE ESTE CICLO PARA BUSCAR ALGUNO QUE TENGA EL MISMO ID
			
			if(client.getId().equals(id)) {
				salir=true;
				findClient=client;				
			}
			else {
				client=client.getNext();
			}
		}			
		return findClient;
	}
	

	public Order findClientOrder(Client client) {
		Order order = null;
		boolean exit = false;
		for (int i=0;i<orders.size() && !exit;i++) {
			if (orders.get(i).getClient().equals(client)) {
				exit = true;
				order = orders.get(i);				
			}
		}
		return order;
	}

	public void addClient(String name,String id,String address,String phone,String obs) {
		Client findClient = findClient(id);
		if (findClient==null) {
			Client newClient = new Client (name, id, address, phone, obs);
			
			if(firstClient==null) {//SI NO HAY CLIENTES
    			firstClient = newClient;
    		}else {
    			Client lastClient = findLastClient(firstClient);
    			lastClient.setNext(newClient);
    			newClient.setPrevious(lastClient);
    		}
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Cliente creado");
			alert.setHeaderText("El cliente ha sido creado");
			alert.setContentText("El cliente con el id "+id+" ha sido creado satisfactoriamente.");
			alert.showAndWait();
			
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al crear el cliente");
			alert.setHeaderText("Cliente Existente");
			alert.setContentText("El cliente con el id "+id+" ya est� registrado en el restaurante.");
			alert.showAndWait();
		}		
	}


	public void deleteClient(String id) {
		Client clientToDelete = findClient(id);		
		
		if (clientToDelete.equals(firstClient)) {
			if (clientToDelete.getNext() == null) {
				firstClient = null;
			}else {
				clientToDelete.getNext().setPrevious(null);
				firstClient = clientToDelete.getNext();
				clientToDelete.setNext(null); 
			}
		  		
		}else {
			Client prev = clientToDelete.getPrevious();
        	Client next = clientToDelete.getNext();
        	
			if (clientToDelete.getNext()!=null) {
				next.setPrevious(prev);
			}
			if (clientToDelete.getPrevious()!=null) {
				prev.setNext(next);
			}
		}		
	}

}
