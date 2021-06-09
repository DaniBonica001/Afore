package model;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoNumericInputException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Restaurant implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final String SAVE_PATH_FILE_EMPLOYEES = "data/EmployeesData.ap2";
	private static final String SAVE_PATH_FILE_PRODUCT = "data/ProductsData.txt";
	private static final String SAVE_PATH_FILE_CLIENTS = "data/ClientsData.ap2";
	//Relations
	private List<Product>products;
	private List<FoodDelivery>deliveries;
	private List<Employee>employees;
	private List<Order>orders;
	private Client firstClient;
	private Day rootDay;
	
	
	//Constructor
	public Restaurant() {
		products = new ArrayList<Product>();
		deliveries = new ArrayList<FoodDelivery>();
		employees = new ArrayList<Employee>();	
		orders = new ArrayList<Order>();
		rootDay=null;
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
	
	public boolean logInUser(String username,String password) { //-->Clase Restaurant
		boolean exit=false;
		boolean open=false;
		
		for (int i=0;i<employees.size() && !exit;i++) {
			if (employees.get(i) instanceof SystemUser) {
				SystemUser objUser = (SystemUser)employees.get(i);
				if (username.equals(objUser.getUsername()) && password.equals(objUser.getPassword()) && employees.get(i).getCondition().equals(Condition.ACTIVE)) {
					exit=true;
					open=true;					
				}
			}			
		}
		return open;
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
	
	//Método de búsqueda binaria de un producto
	public Product findProductBinarySearch (String id) {
		bubbleSortProductById();
		long idToFind = Long.parseLong(id);
		Product product = null;
		boolean found = false;
		int start = 0;
		int end = products.size()-1;
		
		while (start<=end && !found) {
			int middle = (start + end)/2;
			long changeId = Long.parseLong(products.get(middle).getId());
			
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
	
	
	//Método de búsqueda binaria de un empleadp
	public Employee findEmployeeBinarySearch (String id) {
		bubbleSortEmployeeById();
		long idToFind = Long.parseLong(id);
		Employee employee = null;
		boolean found = false;
		int start = 0;
		int end = employees.size()-1;
		
		while (start<=end && !found) {
			int middle = (start + end)/2;
			long changeId = Long.parseLong(employees.get(middle).getId());
			
			if (changeId == idToFind) {
				found = true;
				employee = employees.get(middle);
			}else if (changeId > idToFind) {
				end = middle-1;
			}else if (changeId < idToFind) {
				start = middle+1;
			}
			
		}
		return employee;
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
					System.out.println("El id debe ser un valor numérico");
					e.printStackTrace();
				}				
			}
		}
		for (int i=0;i<products.size();i++) {
			System.out.println(products.get(i).getId()+", "+products.get(i).getName()+", "+products.get(i).getPrice());
		}
	}
	
	//Ordenamiento burbuja de empleados por id - Ordena de menor a mayor
	public void bubbleSortEmployeeById() {
		for (int i=0;i<employees.size()-1;i++) {
			for (int j=0;j<employees.size()-1;j++) {
				try {
					long id1 = Long.parseLong(employees.get(j).getId());
					long id2 = Long.parseLong(employees.get(j+1).getId());	
					
					if (id1>id2) {
						Employee temp = employees.get(j);
						employees.set(j,employees.get(j+1));
						employees.set(j+1, temp);
					}
				}catch (NumberFormatException e) {
					System.out.println("El id debe ser un valor numérico");
					e.printStackTrace();
				}
			}
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
	
	//Ordenamiento insertion de empleados por nombre - Ordena en orden alfabético - hilo
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
				System.out.println("El télefono debe ser un valor numérico");
				e.printStackTrace();
			}
		
		}
	}	
	
	//Ordenamiento selection de Empleados por apellido
	public void selectionSortEmployeeByLastName() {
		//Cuando se elimine un empleado, invoca este método
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
    public boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
 
	public void addProduct(String id,String name,String category,String size,String price,int available,String description) throws NoNumericInputException{	
		Product findProduct= findProduct(id);
		
		if(findProduct==null) {
			if(isNumeric(price)==true) {
				products.add(new Product(id, name, category, size, price, available, description));
				exportProductsData();
				threadToSortProducts();			

			}else {
				throw new NoNumericInputException("El precio debe ser numérico");
			}
		}
	}

	public void deleteProduct(String id) {	
		Product findProduct= findProduct(id);		
		if(findProduct!=null) {
			products.remove(findProduct);
			exportProductsData();
		}
	}
	
	
	public void updateProduct(String id,String name,String category,String size, String price, int availability) {
		Product findProduct= findProduct(id);
		
		if(findProduct!=null) {
			findProduct.setName(name);
			findProduct.setCategory(category);
			findProduct.setSize(size);
			findProduct.setPrice(price);
			findProduct.setAvailability(availability);
			exportProductsData();
		}
		
	}
	
	
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
			String idCashier, String phoneCashier, boolean waiter) throws IOException {
		
		Employee findEmployee = findEmployee(idCashier);
		
		if (findEmployee == null) {
			employees.add(new Cashier(userCashier,passCashier,nameCashier,lastNameCashier,idCashier,phoneCashier,waiter));
			saveEmployeesData();
			threadToSortEmployees();
			
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
			String phoneChef, String dishes) throws IOException {
		
		Employee findEmployee = findEmployee(idChef);
		
		if (findEmployee == null) {
			employees.add(new Chef (userChef,passChef,nameChef,lastNameChef,idChef,phoneChef,dishes));
			saveEmployeesData();
			threadToSortEmployees();
			
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
			String idWaiter, String phoneWaiter, int tables) throws IOException {
		
		Employee findEmployee = findEmployee(idWaiter);
		
		if (findEmployee == null) {
			employees.add(new Waiter(userWaiter,passWaiter,nameWaiter,lastNameWaiter,idWaiter,phoneWaiter,tables));
			saveEmployeesData();
			threadToSortEmployees();
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
			String phoneDM, int orders) throws IOException {
		
		Employee findEmployee = findEmployee(idDM);
		
		if (findEmployee == null) {
			employees.add(new DeliveryMan(userDM,passDM,nameDM,lastNameDm,idDM,phoneDM,orders));
			saveEmployeesData();
			threadToSortEmployees();
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
	
	
	public void deleteEmployee(String id) throws IOException{
		selectionSortEmployeeByLastName();
		Employee findEmployee = findEmployee(id);
		if (findEmployee!=null){
			employees.remove(findEmployee);
			saveEmployeesData();
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al encontrar cliente");
			alert.setHeaderText("Cliente inexistente");
			alert.setContentText("El cliente con id "+id+" no está actualmente en la lista de clientes del restaurante.");
			alert.showAndWait();
		}
	}
	
	public void updateEmployee(String id, String name, String lastName, String phone, String user, String password) throws IOException {
		Employee findEmployee = findEmployee(id);
		if (findEmployee!=null) {
			findEmployee.setName(name);
			findEmployee.setLastName(lastName);
			findEmployee.setPhone(phone);
			findEmployee.setUsername(user);
			findEmployee.setPassword(password);
			saveEmployeesData();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al encontrar empleado");
			alert.setHeaderText("Empleado inexistente");
			alert.setContentText("El emplado con id "+id+" no está actualmente en la lista de empleados del restaurante.");
			alert.showAndWait();
		}
		
	}
		
	//Método recursivo
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
			
			try {
				saveClientsData();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}	
	}


	public void deleteClient(String id) {
		Client clientToDelete = findClient(id);	
		
		if(clientToDelete!=null) {

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

			try {
				saveClientsData();
			} catch (IOException e) {			
				e.printStackTrace();
			}
		}
	}
	
	public void updateClient(String id, String name, String address, String phone, String obs){ //-->Clase Restaurant
		Client findClient = findClient(id);
		
		if (findClient!=null){
	        	findClient.setId(id);
			findClient.setName(name);
	        	findClient.setAddress(address);
	        	findClient.setPhone(phone);
	        	findClient.setObservations(obs);   
	        	try {
					saveClientsData();
				} catch (IOException e) {					
					e.printStackTrace();
				}
		}

	}
	
	//metodo recursivo
	public void addGrade(Day current, int gradeAtencion, int gradeFood, int day, int month, int year) {
		//1. VERIFICAR SI EL DÍA ACTUAL YA EXISTE EN EL ARBOL BINARO
		Day findDay=findDay(rootDay, day, month, year);
		if(findDay!=null) {
			//2. SI EXISTE MODIFICAR SU NOTA DE ATENCION Y COMIDA
			//System.out.println("***********gradeFood es"+gradeFood);
			//System.out.println("ANTES EL PROMEDIO DE LA COMIDA ESTABA EN"+findDay.getAverageFoodGrade());
			findDay.setAverageFoodGrade((findDay.getAverageFoodGrade()+gradeFood)/2);
			findDay.setAverageServiceGrade((findDay.getAverageServiceGrade()+gradeAtencion)/2);
			//System.out.println("SE HA CAMBIADO EL PROMEDIO DEL DIA DE COMIDA"+findDay.getAverageFoodGrade());
		}
		//3. SI NO EXISTE CREAR UNA NUEVA CLASE DAY Y AÑADIRLA AL ARBOL BINARIO
		else {
			/*
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year  = localDate.getYear();
		int month = localDate.getMonthValue();
		int day   = localDate.getDayOfMonth();
			 */

			Day newDay= new Day(day, month, year, gradeAtencion, gradeFood);

			//SI NO EXISTE ESE DÍA EN EL ARBOL BINARIO
			if(current==null) {
				setRootDay(newDay);
				System.out.println("Se añadió el primer dia");
			}else {
				if(newDay.getYear()<current.getYear()) {// si su año es menor que el current automaticamente mirar a la izquierda
					if(current.getLeft()==null) {
						current.setLeft(newDay);
						System.out.println("AÑO Se añadió a la izquierda de "+ current.getDay()+"/"+current.getMonth()+"/"+current.getYear());
					}else {
						addGrade(current.getLeft(), gradeAtencion, gradeFood, day, month, year);
					}
				}else if(newDay.getYear()==current.getYear()) {//si su año es igual al current entonces hay que mirar el mes para ver cual es mayor
					if(newDay.getMonth()<current.getMonth()) {// si su mes es menor que el current automaticamente mirar a la izquierda
						if(current.getLeft()==null) {
							current.setLeft(newDay);
							System.out.println("MES Se añadió a la izquierda de "+ current.getDay()+"/"+current.getMonth()+"/"+current.getYear());
						}else {
							addGrade(current.getLeft(), gradeAtencion, gradeFood, day, month, year);
						}
					}else if(newDay.getMonth()==current.getMonth()) {//si su mes es igual al current entonces hay que mirar el dia para ver cual es mayor
						if(newDay.getDay()<=current.getDay()) {// si su dia es menor-igual que el current automaticamente mirar a la izquierda
							if(current.getLeft()==null) {
								current.setLeft(newDay);
								System.out.println("DIA Se añadió a la izquierda de "+ current.getDay()+"/"+current.getMonth()+"/"+current.getYear());
							}else {
								addGrade(current.getLeft(), gradeAtencion, gradeFood, day, month, year);
							}
						}else {//si el dia es mayor que el current entonces mirar para la derecha automaticamente
							if(current.getRight()==null) {//si aún no hay right entonces lo asigna
								current.setRight(newDay);
								System.out.println("DIA Se añadió a la derecha de "+ current.getDay()+"/"+current.getMonth()+"/"+current.getYear());
							}
							else {//si hay right entonces recursividad pero partiendo del right
								addGrade(current.getRight(), gradeAtencion, gradeFood, day, month, year);
							}
						}

					}else {//si el mes es mayor que el current entonces mirar para la derecha automaticamente
						if(current.getRight()==null) {//si aún no hay right entonces lo asigna
							current.setRight(newDay);
							System.out.println("MES Se añadió a la derecha de "+ current.getDay()+"/"+current.getMonth()+"/"+current.getYear());
						}
						else {//si hay right entonces recursividad pero partiendo del right
							addGrade(current.getRight(), gradeAtencion, gradeFood, day, month, year);
						}
					}

				}else {//si el año es mayor que el current entonces mirar para la derecha automaticamente
					if(current.getRight()==null) {//si aún no hay right entonces lo asigna
						current.setRight(newDay);
						System.out.println("AÑO Se añadió a la derecha de "+ current.getDay()+"/"+current.getMonth()+"/"+current.getYear());
					}
					else {//si hay right entonces recursividad pero partiendo del right
						addGrade(current.getRight(), gradeAtencion, gradeFood, day, month, year);
					}
				}
			}
		}
		
	}
	
	//metodo recursivo
	public Day findDay(Day current, int day, int month, int year) {
		Day findDay=null;
		if(current==null) {
			return null;
		}
		else {
		if(current.getDay()==day && current.getMonth()==month && current.getYear()==year) {
			findDay=current;
			return findDay;
		}else {
			if(year<current.getYear()) {//si el año es menor al del actual entonces mire a la izquierda
				if(current.getLeft()==null) {//si no tiene nada a la izquierda es porque no existe ese dia
					return null;
				}else {//si existe alguno a la izquierda engonces recursividad con el que está a la izquierda
					return findDay(current.getLeft(), day, month, year);
				}
			}else if(current.getYear()==year) {
				if(month<current.getMonth()) {//si el mes es menor al del actual mirar a la izquierda
					if(current.getLeft()==null) {//si no hay nada a la izquierda entonces no existe
						return null;
					}else {
						return findDay(current.getLeft(), day, month, year);
					}
				}else if(month==current.getMonth()) {
					if(day<=current.getDay()) {//si el mes es menor al del actual mirar a la izquierda
						if(current.getLeft()==null) {//si no hay nada a la izquierda entonces no existe
							return null;
						}else {
							return findDay(current.getLeft(), day, month, year);
						}
					}else {
						if(current.getRight()==null) {//si no hay nada a la izquierda entonces no existe
							return null;
						}else {
							return findDay(current.getRight(), day, month, year);
						}
					}
				}else {
					if(current.getRight()==null) {//si no hay nada a la izquierda entonces no existe
						return null;
					}else {
						return findDay(current.getRight(), day, month, year);
					}
				}
			}else {//Si el año es mayor al actual
				if(current.getRight()==null) {//si no tiene nada a la izquierda es porque no existe ese dia
					return null;
				}else {//si existe alguno a la izquierda engonces recursividad con el que está a la izquierda
					return findDay(current.getRight(), day, month, year);
				}
			}
		}
		}
	}

	public Day getRootDay() {
		return rootDay;
	}

	public void setRootDay(Day rootDay) {
		this.rootDay = rootDay;
	}
	
	
	//Import employees Data (serializacion)
	@SuppressWarnings("unchecked")
	public boolean loadEmployeesData() throws IOException, ClassNotFoundException{
		 File f = new File(SAVE_PATH_FILE_EMPLOYEES);
		 boolean loaded = false;
		 if(f.exists()){
			 ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			 employees = (List<Employee>)ois.readObject();
			 ois.close();
			 loaded = true;
		 }		 
		 return loaded;	
	}
	
	//Export employees data (serializacion)
	 public void saveEmployeesData() throws IOException{
		 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_EMPLOYEES));
		 oos.writeObject(employees);
		 oos.close();
	 }
	 
	 //Import clients data (serializacion)
	 public void loadClientsData() throws FileNotFoundException, IOException, ClassNotFoundException {
		 ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_PATH_FILE_CLIENTS));
		 firstClient = (Client)in.readObject();
		 in.close();		 
	 }

	 
	 //Export clients data (serializacion)
	 public void saveClientsData() throws IOException{
		 ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_CLIENTS));	
		 Client currentClient = firstClient;
		 
		 while(currentClient!=null) {			 
			 oos.writeObject(currentClient);
			 currentClient = currentClient.getNext();
		 }		 
		 oos.close();
	 }
	 


	 //Export products data (.txt)
	 public void exportProductsData() {
		 PrintWriter pw;
		try {
			pw = new PrintWriter(SAVE_PATH_FILE_PRODUCT);
			 for (int i=0;i<products.size();i++) {
				 Product myProduct = products.get(i);
				 pw.println(myProduct.getId()+";"+myProduct.getName()+";"+myProduct.getCategory()+";"+myProduct.getSize()+";"+myProduct.getPrice()+";"+myProduct.getAvailability()+";"+myProduct.getDescription());			 
			 }
			 pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		 
	 }
	 
	 //Import product data (.txt)
	 public void importProductsData() throws IOException {
		 BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(SAVE_PATH_FILE_PRODUCT));
		    String line = br.readLine();
		    while(line!=null){
		      String[] parts = line.split(";");
		      //Invoca el método addContact
		      addProduct(parts[0],parts[1],parts[2],parts[3],parts[4],Integer.parseInt(parts[5]),parts[6]);
		      line = br.readLine();
		    }
		    br.close();
		} catch (FileNotFoundException | NumberFormatException | NoNumericInputException e) {
			e.printStackTrace();
		}
	
	 }
	 
	 
}
