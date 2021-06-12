package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import exceptions.NoNumericInputException;
import exceptions.NoNumericPhoneException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import model.Cashier;
import model.Chef;
import model.Client;
import model.Condition;
import model.DeliveryMan;
import model.Employee;
import model.Order;
import model.Product;
import model.Restaurant;
import model.Waiter;



public class AforeGUI {	
	Employee actualEmployee;
	
	//Relations
	private Restaurant restaurant;
	
	//Constructor #1
	public AforeGUI(Restaurant rest) {
		restaurant = rest;
		actualEmployee=null;
	}
	

	public void openScreen(String nameFxml, Pane paneToOpen)  {
		FXMLLoader fxml = new FXMLLoader (getClass().getResource(nameFxml));
		fxml.setController(this);
    	Parent root = null;
		try {
			root = fxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	paneToOpen.getChildren().setAll(root);
	}
	

	//LOGIN.FXML THINGS*************************************************************************

	@FXML
	private Pane mainPaneLogin;

	@FXML
	private TextField txtEmailLogin;

	@FXML
	private TextField txtPasswordLogin;
    
	@FXML
    public void buttonLogin(ActionEvent event) { //-->Clase AforeGUI
	String email = txtEmailLogin.getText();
	String password = txtPasswordLogin.getText();
	
	if (!email.equals("") && !password.equals("")){
		boolean entry = restaurant.logInUser(email,password);

		if (entry == true){
	    		openScreen("menu.fxml",mainPaneLogin);   
	    		usernameMenu.setText(email);
	    		
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al ingresar");
			alert.setHeaderText("Datos inexistentes");
			alert.setContentText("El usuario y/o la contraseña son incorrectos, por favor intentelo de nuevo");
			alert.showAndWait();
		}

	}else{

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error al ingresar");
		alert.setHeaderText("Campos incompletos");
		alert.setContentText("Es necesario ingresar su usuario y contraseña para poder acceder a las funcionalidades del programa");
		alert.showAndWait();
	}
 	
    }
    
    @FXML
    public void buttonLoginAdministrator(ActionEvent event) {
    	openScreen("menu-admin.fxml",mainPaneLogin);
    }
    
    @FXML
    public void buttonRegister(ActionEvent event) {
    	openScreen("register1.fxml",mainPaneLogin);
    	
    }
    
    
  //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+  
    //MENU-ADMIN.FXML THINGS**************************************************************************
    @FXML
    private Pane paneToChangeAdm;
    
    public Thread hora;
    public Thread hilo;

    @FXML
    public void buttonOpenEmployeeManagement(ActionEvent event) {
    	openScreen("employees.fxml",paneToChangeAdm);    	

		hora = new Thread(new Runnable() {
			public void run() {
				while (true) {
					updateHour();
				}
			}
		});
		hora.start();
    }
    
    public void updateHour() {
		hilo = new Thread(new Runnable() {
			public void run() {
				labelHour.setText(calculateHour());
			}
		});
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}

			// UI update is run on the Application thread
			Platform.runLater(hilo);
		}
	}

	// This method helps to calculate the hour in a military hour
	public String calculateHour() {
		String hora;
		String min;
		String seg;
		String message;
		Calendar calendario = new GregorianCalendar();
		Date horaActual = new Date();
		calendario.setTime(horaActual);

		hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY)
				: "0" + calendario.get(Calendar.HOUR_OF_DAY);
		min = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE)
				: "0" + calendario.get(Calendar.MINUTE);
		seg = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND)
				: "0" + calendario.get(Calendar.SECOND);

		message = hora + ":" + min + ":" + seg;
		return message;
	}
    
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+     
    //REGISTER1.FXML THINGS**********************************************************************************************************************************************************
    
    @FXML
    private Pane mainPaneRegister;
    
    @FXML
    void createAdministratorUser(ActionEvent event) {

    }
    
    @FXML
    public void openCreateCashierEmployee(ActionEvent event) throws IOException{
    	openScreen("create-cashier-employee.fxml",mainPaneRegister);
    	initializeToggleGroupCashier();
    }
    public void initializeToggleGroupCashier() {
    	ToggleGroup tgCashier = new ToggleGroup();
    	this.rbYesCashier.setToggleGroup(tgCashier);
    	this.rbNoCashier.setToggleGroup(tgCashier);    	
    }
    
    @FXML
    public void openCreateChefEmployee(ActionEvent event)throws IOException {
    	openScreen("create-chef-employee.fxml",mainPaneRegister);
    }

    @FXML
    public void openCreateDeliveryManEmployee(ActionEvent event)throws IOException {
    	openScreen("create-deliveryMan-employee.fxml",mainPaneRegister);
    	initializeChoiceBoxAmountOrdersDeliveryMan();
    }

    @FXML
    public void openCreateWaiterEmployee(ActionEvent event) throws IOException{
    	openScreen("create-waiter-employee.fxml",mainPaneRegister);
    	initializeChoiceBoxAmounTablesWaiter();
    }
    

    @FXML
    public void backToLogin(ActionEvent event) {
    	openScreen("login.fxml",mainPaneRegister);
    }
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+  
    //MENU.FXML THINGS**************************************************************************
    
    @FXML
    private Pane mainPaneMenu;
    
    @FXML
    private ImageView profilePicturaUser;

    @FXML
    private Label usernameMenu;

    @FXML
    private Pane paneToChange;

    @FXML
    private MenuButton clientsMenuButton;

    @FXML
    private MenuButton employeesMenuButton;

    @FXML
    private MenuButton orderMenuButton;

    @FXML
    public void buttonCreateClient(ActionEvent event) throws IOException {
    	openScreen("create-client.fxml",paneToChange); 	
    }
 
    @FXML
    public void buttonCreateProduct(ActionEvent event) throws IOException{
    	openScreen("create-product.fxml",paneToChange);   
    	initializeChoiceBoxCategoryProduct();
    	initializeChoiceBoxSizeProduct();
    }

    @FXML
    public void buttonDeleteClient(ActionEvent event) throws IOException{
    	openScreen("delete-client.fxml",paneToChange);  
    }

    @FXML
    public void buttonDeleteEmployee(ActionEvent event) throws IOException{
    	openScreen("delete-employee.fxml",paneToChange);    	
    }

    @FXML
    public void buttonDeletepProduct(ActionEvent event) throws IOException{
    	openScreen("delete-product.fxml",paneToChange);  
    	
    }

    @FXML
    public void buttonDeliveryFood(ActionEvent event) throws IOException{
    	openScreen("domicilios.fxml",paneToChange);
    	initializeScreenDomicilios();
    }
    
    public void initializeScreenDomicilios() {
    	ObservableList<String> items =FXCollections.observableArrayList(restaurant.getNameProducts());
    	listViewOfProducts.setItems(items);
    	
    	ToggleGroup tgOp = new ToggleGroup();        
        rbPickUpRestaurant.setToggleGroup(tgOp);
        rbScheduleShipment.setToggleGroup(tgOp);    		
    }

    @FXML
    public void buttonEnableDisableClient(ActionEvent event) throws IOException{
    	openScreen("disable-client.fxml",paneToChange);    	
    	initializeToggleGroupDisableClient();
    }
    
    public void initializeToggleGroupDisableClient() {
    	ToggleGroup tgDisClient = new ToggleGroup();
    	this.rbDisableClientHabilitado.setToggleGroup(tgDisClient);
    	this.rbDisableClientDeshabilitado.setToggleGroup(tgDisClient);
    }

    @FXML
    public void buttonEnableDisableEmployee(ActionEvent event) throws IOException{
    	openScreen("disable-employee.fxml",paneToChange);
    }

    @FXML
    public void buttonEnableDisableProduct(ActionEvent event) throws IOException{
    	openScreen("disable-product.fxml",paneToChange);
    }

    @FXML
    public void buttonExitMenu(ActionEvent event) throws IOException{
    	openScreen("login.fxml",mainPaneMenu);
    }


    @FXML
    public void buttonOpenProductOrder(ActionEvent event) {
    	openScreen("product-Order.fxml",paneToChange);
    	initializeScreenOrder();   	
    }
    
    public void initializeScreenOrder() {
    	ObservableList<String> items =FXCollections.observableArrayList(restaurant.getNameProducts());
    	listViewOfOrderProducts.setItems(items);    	
    	
    	Employee employee = restaurant.findEmployeeByUsername(usernameMenu.getText());
    	actualEmployee=employee;
    	if (employee!=null) {
    		LabelEmployeeName.setText(employee.getName()+" "+employee.getLastName());
    	}    	 
    }
    
    @FXML
    public void buttonRateServiceClient(ActionEvent event) throws IOException{
    	openScreen("grade-service.fxml",paneToChange);
    }

    @FXML
    public void buttonReserve(ActionEvent event) throws IOException{
    	openScreen("reserva.fxml",paneToChange);
    }

    @FXML
    public void buttonUpdateClient(ActionEvent event) throws IOException{
    	openScreen("update-client.fxml",paneToChange);
    }

    @FXML
    public void buttonUpdateEmployee(ActionEvent event) throws IOException{
    	openScreen("update-employee.fxml",paneToChange);
    }
 
    @FXML
    public void buttonUpdateProduct(ActionEvent event) throws IOException{
    	openScreen("update-product.fxml",paneToChange);
    	
		ObservableList<String>categories = FXCollections.observableArrayList("Bebida","Entrada","Plato principal","Postre","Vino","Ensalada","Respostería");
		choiceBoxUpdateCategoryProduct.setItems(categories);
		
    	ObservableList<String>sizes = FXCollections.observableArrayList("Personal","Para dos","Familiar");
    	choiceBoxUpdateSizeProduct.setItems(sizes);
    }
    
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+     
    //CREATE-CASHIER-EMPLOYEE.FXML THINGS**********************************************************************************************************************************************************
    
    @FXML
    private Pane mainPaneCreateCashier;

    @FXML
    private TextField txtNameCashier;

    @FXML
    private TextField txtUserCashier;

    @FXML
    private PasswordField pfPasswordCashier;

    @FXML
    private TextField txtLastNameCashier;

    @FXML
    private TextField txtIdCashier;

    @FXML
    private TextField txtPhoneCashier;

    @FXML
    private RadioButton rbYesCashier;

    @FXML
    private RadioButton rbNoCashier;

    @FXML
    public void backFromCashierToRegister1(ActionEvent event) {
    	openScreen("register1.fxml",mainPaneCreateCashier);
    	
    }

    @FXML
    public void createCashierEmployee(ActionEvent event) {
    	if (!txtNameCashier.getText().equals("") && !txtUserCashier.getText().equals("") &&
    			!pfPasswordCashier.getText().equals("") && !txtLastNameCashier.getText().equals("") &&
    			!txtIdCashier.getText().equals("") && !txtPhoneCashier.getText().equals("") && rbYesCashier.isSelected() || rbNoCashier.isSelected() ) {
    		
    		
    		String nameCashier = txtNameCashier.getText();
    		String lastNameCashier = txtLastNameCashier.getText();
    		String idCashier = txtIdCashier.getText();
    		String phoneCashier = txtPhoneCashier.getText();
    		String userCashier = txtUserCashier.getText();
    		String passCashier = pfPasswordCashier.getText();
    		boolean waiter = false;
    		
    		if (rbYesCashier.isSelected()) {
        		waiter = true;
        	}else if (rbNoCashier.isSelected()) {
        		waiter = false;
        	}    		
    		try {
				restaurant.addEmployee(userCashier,passCashier,nameCashier,lastNameCashier,idCashier,phoneCashier,waiter);
			} catch (IOException e) {				
				e.printStackTrace();
			} catch (NoNumericPhoneException nnpe) {
        		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error al crear el empleado");
    			alert.setHeaderText("Telefono no númerico");
    			alert.setContentText(nnpe.getMessage());
    			alert.showAndWait();
			}
    		
    		txtNameCashier.setText("");
        	txtUserCashier.setText("");
        	pfPasswordCashier.setText("");
        	txtLastNameCashier.setText("");
        	txtIdCashier.setText("");
        	txtPhoneCashier.setText("");
        	if (rbYesCashier.isSelected()) {
        		rbYesCashier.setSelected(false);
        	}else if (rbNoCashier.isSelected()) {
        		rbNoCashier.setSelected(false);
        	}   		
    		
    	}else if (txtNameCashier.getText().equals("") || txtUserCashier.getText().equals("") ||
    			pfPasswordCashier.getText().equals("") || txtLastNameCashier.getText().equals("") ||
    			txtIdCashier.getText().equals("") || txtPhoneCashier.getText().equals("") || !rbYesCashier.isSelected() || !rbNoCashier.isSelected()) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al guardar datos");
    		alert.setHeaderText("Campos incompletos");
    		alert.setContentText("Todos los campos deben ser llenados");
    		alert.showAndWait();
    	}
    }

    @FXML
    public void noCreateCashierEmployee(ActionEvent event) {
    	txtNameCashier.setText("");
    	txtUserCashier.setText("");
    	pfPasswordCashier.setText("");
    	txtLastNameCashier.setText("");
    	txtIdCashier.setText("");
    	txtPhoneCashier.setText("");
    	if (rbYesCashier.isSelected()) {
    		rbYesCashier.setSelected(false);
    	}else if (rbNoCashier.isSelected()) {
    		rbNoCashier.setSelected(false);
    	}
    }

    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+     
    //CREATE-CHEF-EMPLOYEE.FXML THINGS**********************************************************************************************************************************************************
    
    @FXML
    private Pane mainPaneCreateChef;

    @FXML
    private TextField txtNameChef;

    @FXML
    private TextField txtUserChef;

    @FXML
    private PasswordField pfPasswordChef;

    @FXML
    private TextField txtLastNameChef;

    @FXML
    private TextField txtIdChef;

    @FXML
    private TextField txtPhoneChef;

    @FXML
    private TextArea txtDishesChef;

    @FXML
    public void backFromChefToRegister1(ActionEvent event) {
    	openScreen("register1.fxml",mainPaneCreateChef);
    }

    @FXML
    public void createChefEmployee(ActionEvent event) {
    	if (!txtNameChef.getText().equals("") && !txtUserChef.getText().equals("") && 
    			!pfPasswordChef.getText().equals("") && !txtLastNameChef.getText().equals("") &&
    			!txtIdChef.getText().equals("") && !txtPhoneChef.getText().equals("") &&
    			!txtDishesChef.getText().equals("")) {
    		
    		String nameChef = txtNameChef.getText();
    		String lastNameChef = txtLastNameChef.getText();
    		String idChef = txtIdChef.getText();
    		String phoneChef = txtPhoneChef.getText();
    		String userChef = txtUserChef.getText();
    		String passChef = pfPasswordChef.getText();
    		String dishes = txtDishesChef.getText();
    		
    		try {
				restaurant.addEmployee(userChef, passChef, nameChef, lastNameChef, idChef, phoneChef, dishes);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	  	txtNameChef.setText("");
        	txtUserChef.setText("");
        	pfPasswordChef.setText("");
        	txtLastNameChef.setText("");
        	txtIdChef.setText("");
        	txtPhoneChef.setText("");
        	txtDishesChef.setText("");    
        	
    	}else if (txtNameChef.getText().equals("") || txtUserChef.getText().equals("") || 
    			pfPasswordChef.getText().equals("") || txtLastNameChef.getText().equals("") ||
    			txtIdChef.getText().equals("") || txtPhoneChef.getText().equals("") ||
    			txtDishesChef.getText().equals("")) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al guardar datos");
    		alert.setHeaderText("Campos incompletos");
    		alert.setContentText("Todos los campos deben ser llenados");
    		alert.showAndWait();    		
    	}
    }

    @FXML
    public void noCreateChefEmployee(ActionEvent event) {
    	txtNameChef.setText("");
    	txtUserChef.setText("");
    	pfPasswordChef.setText("");
    	txtLastNameChef.setText("");
    	txtIdChef.setText("");
    	txtPhoneChef.setText("");
    	txtDishesChef.setText("");    	
    }
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+     
    //CREATE-WAITER-EMPLOYEE.FXML THINGS**********************************************************************************************************************************************************
    
    @FXML
    private Pane mainPaneCreateWaiter;

    @FXML
    private TextField txtNameWaiter;

    @FXML
    private ChoiceBox<Integer> choiceBoxAmounTablesWaiter;

    @FXML
    private TextField txtUserWaiter;

    @FXML
    private PasswordField pfPasswordWaiter;

    @FXML
    private TextField txtLastNameWaiter;

    @FXML
    private TextField txtIdWaiter;

    @FXML
    private TextField txtPhoneWaiter;
    
    public void initializeChoiceBoxAmounTablesWaiter() {
    	ObservableList<Integer>tables = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
    	choiceBoxAmounTablesWaiter.setItems(tables);
    }

    @FXML
    public void backFromWaiterToRegister1(ActionEvent event) {
    	openScreen("register1.fxml",mainPaneCreateWaiter);
    }

    @FXML
    public void createWaiterEmployee(ActionEvent event) {
    	if (!txtNameWaiter.getText().equals("") && !txtUserWaiter.getText().equals("") &&
    			!pfPasswordWaiter.getText().equals("") && !txtLastNameWaiter.getText().equals("") &&
    			!txtIdWaiter.getText().equals("") && !txtPhoneWaiter.getText().equals("") && 
    			!choiceBoxAmounTablesWaiter.getSelectionModel().isEmpty()) {
    		
    		String nameWaiter = txtNameWaiter.getText();
    		String lastNameWaiter = txtLastNameWaiter.getText();
    		String idWaiter = txtIdWaiter.getText();
    		String phoneWaiter = txtPhoneWaiter.getText();
    		String userWaiter = txtUserWaiter.getText();
    		String passWaiter = pfPasswordWaiter.getText();
    		int tables = choiceBoxAmounTablesWaiter.getSelectionModel().getSelectedItem();
    		
    		try {
				restaurant.addEmployee(userWaiter,passWaiter,nameWaiter,lastNameWaiter,idWaiter,phoneWaiter,tables);
			} catch (IOException e) {
				e.printStackTrace();
			}
        	txtNameWaiter.setText("");
        	txtUserWaiter.setText("");
        	pfPasswordWaiter.setText("");
        	txtLastNameWaiter.setText("");
        	txtIdWaiter.setText("");
        	txtPhoneWaiter.setText("");
        	choiceBoxAmounTablesWaiter.getSelectionModel().clearSelection();
    	
    	}else if (txtNameWaiter.getText().equals("") || txtUserWaiter.getText().equals("") ||
    			pfPasswordWaiter.getText().equals("") || txtLastNameWaiter.getText().equals("") ||
    			txtIdWaiter.getText().equals("") || txtPhoneWaiter.getText().equals("") || 
    			choiceBoxAmounTablesWaiter.getSelectionModel().isEmpty()) {    		
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al guardar datos");
    		alert.setHeaderText("Campos incompletos");
    		alert.setContentText("Todos los campos deben ser llenados");
    		alert.showAndWait();        		
    	}
    }

    @FXML
    public void noCreateWaiterEmployee(ActionEvent event) {
    	txtNameWaiter.setText("");
    	txtUserWaiter.setText("");
    	pfPasswordWaiter.setText("");
    	txtLastNameWaiter.setText("");
    	txtIdWaiter.setText("");
    	txtPhoneWaiter.setText("");
    	choiceBoxAmounTablesWaiter.getSelectionModel().clearSelection();
    }
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+     
    //CREATE-DELIVERYMAN-EMPLOYEE.FXML THINGS**********************************************************************************************************************************************************

    @FXML
    private Pane mainPaneCreateDeliveryMan;

    @FXML
    private TextField txtNameDeliveryMan;

    @FXML
    private ChoiceBox<Integer> choiceBoxAmountOrdersDeliveryMan;

    @FXML
    private TextField txtUserDeliveryMan;

    @FXML
    private PasswordField pfPasswordDeliveryMan;

    @FXML
    private TextField txtLastNameDeliveryMan;

    @FXML
    private TextField txtIdDeliveryMan;

    @FXML
    private TextField txtPhoneDeliveryMan;
    
    
    public void initializeChoiceBoxAmountOrdersDeliveryMan() {
    	ObservableList<Integer>orders = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15);
    	choiceBoxAmountOrdersDeliveryMan.setItems(orders);
    }

    @FXML
    public void backFromDeliveryManToRegister1(ActionEvent event) {
    	openScreen("register1.fxml",mainPaneCreateDeliveryMan);
    }

    @FXML
    public void createDeliveryManEmployee(ActionEvent event) {
    	if (!txtNameDeliveryMan.getText().equals("") && !txtUserDeliveryMan.getText().equals("") &&
    			!pfPasswordDeliveryMan.getText().equals("") && !txtLastNameDeliveryMan.getText().equals("") &&
    			!txtIdDeliveryMan.getText().equals("") && !txtPhoneDeliveryMan.getText().equals("") &&
    			!choiceBoxAmountOrdersDeliveryMan.getSelectionModel().isEmpty()) {
    		
    		String nameDM = txtNameDeliveryMan.getText();
    		String lastNameDm = txtLastNameDeliveryMan.getText();
    		String idDM = txtIdDeliveryMan.getText();
    		String phoneDM = txtPhoneDeliveryMan.getText();
    		String userDM = txtUserDeliveryMan.getText();
    		String passDM = pfPasswordDeliveryMan.getText();
    		int orders = choiceBoxAmountOrdersDeliveryMan.getSelectionModel().getSelectedItem();
    		
    		try {
				restaurant.addEmployeeDM(userDM, passDM, nameDM, lastNameDm, idDM, phoneDM, orders);
			} catch (IOException e) {				
				e.printStackTrace();
			}
    		txtNameDeliveryMan.setText("");
        	txtUserDeliveryMan.setText("");
        	pfPasswordDeliveryMan.setText("");
        	txtLastNameDeliveryMan.setText("");
        	txtIdDeliveryMan.setText("");
        	txtPhoneDeliveryMan.setText("");
        	choiceBoxAmountOrdersDeliveryMan.getSelectionModel().clearSelection();    		
    	
    	}else if (txtNameDeliveryMan.getText().equals("") || txtUserDeliveryMan.getText().equals("") ||
    			pfPasswordDeliveryMan.getText().equals("") || txtLastNameDeliveryMan.getText().equals("") ||
    			txtIdDeliveryMan.getText().equals("") || txtPhoneDeliveryMan.getText().equals("") ||
    			choiceBoxAmountOrdersDeliveryMan.getSelectionModel().isEmpty()) {
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al guardar datos");
    		alert.setHeaderText("Campos incompletos");
    		alert.setContentText("Todos los campos deben ser llenados");
    		alert.showAndWait();    
    		
    	}
    		
    }

    @FXML
    public void noCreateDeliveryManEmployee(ActionEvent event) {
    	txtNameDeliveryMan.setText("");
    	txtUserDeliveryMan.setText("");
    	pfPasswordDeliveryMan.setText("");
    	txtLastNameDeliveryMan.setText("");
    	txtIdDeliveryMan.setText("");
    	txtPhoneDeliveryMan.setText("");
    	choiceBoxAmountOrdersDeliveryMan.getSelectionModel().clearSelection();
    }
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+  
    //DELETE-EMPLOYEE.FXML THINGS**************************************************************************

    @FXML
    private Pane mainPaneDeleteEmployee;

    @FXML
    private TextField txtDeleteEmployeeId;

    @FXML
    private TextField txtDeleteEmployeeUser;

    @FXML
    private TextField txtDeleteEmployeeIde;

    @FXML
    private TextField txtDeleteEmployeePhone;

    @FXML
    private TextField txtDeleteEmployeeName;

    @FXML
    private TextField txtDeleteEmployeeTypeEmployee;

    @FXML
    public void buttonFindEmployeeToDelete(ActionEvent event) {
    	if (!txtDeleteEmployeeId.getText().equals("")){
    		Employee findEmployee = restaurant.findEmployeeBinarySearch(txtDeleteEmployeeId.getText());

    		if (findEmployee !=null){
    			txtDeleteEmployeeName.setText(findEmployee.getName());
    			txtDeleteEmployeeIde.setText(findEmployee.getId());
    			txtDeleteEmployeePhone.setText(findEmployee.getPhone());
    			txtDeleteEmployeeUser.setText(findEmployee.getUsername());
    			if (findEmployee instanceof Cashier){
    				txtDeleteEmployeeTypeEmployee.setText("Cajero");
    			}else if (findEmployee instanceof Waiter){
    				txtDeleteEmployeeTypeEmployee.setText("Mesero");
    			}else if (findEmployee instanceof Chef){
    				txtDeleteEmployeeTypeEmployee.setText("Chef");
    			}else if (findEmployee instanceof DeliveryMan){
    				txtDeleteEmployeeTypeEmployee.setText("Domiciliario");
    			}
    		}
    	}
    }


    @FXML
    public void buttonDeleteEmployeeDelete(ActionEvent event) throws IOException {
	if (!txtDeleteEmployeeId.getText().equals("")){
		Employee findEmployee = restaurant.findEmployeeBinarySearch(txtDeleteEmployeeId.getText());
		
		if (findEmployee!=null){

			restaurant.deleteEmployee(txtDeleteEmployeeId.getText());
			    			
			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Empleado eliminado");
    			alert.setHeaderText("El empleado ha sido eliminado satisfactoriamente");
    			alert.setContentText("El empleado con id"+findEmployee.getId()+" ha sido eliminado");
    			alert.showAndWait();

			txtDeleteEmployeeId.setText("");
			txtDeleteEmployeeName.setText("");
			txtDeleteEmployeeIde.setText("");
			txtDeleteEmployeePhone.setText("");
			txtDeleteEmployeeUser.setText("");
			txtDeleteEmployeeTypeEmployee.setText("");

	

		}else{
			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error en la eliminación del empleado");
    			alert.setHeaderText("Empleado no encontrado");
    			alert.setContentText("El empleado con id "+txtDeleteEmployeeId.getText()+" no se ha encontrado.");
    			alert.showAndWait();

		}


	}else{

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error en la eliminación del empleado");
		alert.setHeaderText("Campos incompletos");
		alert.setContentText("Es necesario el id del empleado para poder eliminarlo");
		alert.showAndWait();
	}
    }

    @FXML
    public void buttonCancelDeleteEmployee(ActionEvent event) {
    	txtDeleteEmployeeId.setText("");
    	txtDeleteEmployeeName.setText("");
    	txtDeleteEmployeeIde.setText("");
    	txtDeleteEmployeePhone.setText("");
    	txtDeleteEmployeeUser.setText("");
    	txtDeleteEmployeeTypeEmployee.setText("");
    }
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+  
    //UPDATE-EMPLOYEE.FXML THINGS**************************************************************************

 @FXML
    private Pane mainPaneCreateEmployee;

    @FXML
    private TextField txtUpdateEmployeeName;

    @FXML
    private TextField txtUpdateEmployeeUser;

    @FXML
    private TextField txtUpdateEmployeePassword;

    @FXML
    private TextField txtUpdateEmployeeLastName;

    @FXML
    private TextField txtUpdateEmployeeId;

    @FXML
    private TextField txtUpdateEmployeePhone;



    @FXML
    public void buttonFindEmployeeToUpdate(ActionEvent event) {
    	if (!txtUpdateEmployeeId.getText().equals("")) {
    		Employee findEmployee = restaurant.findEmployeeBinarySearch(txtUpdateEmployeeId.getText());
    		if (findEmployee!=null) {
    	    	txtUpdateEmployeeName.setText(findEmployee.getName());
    	    	txtUpdateEmployeeLastName.setText(findEmployee.getLastName());
    	    	txtUpdateEmployeePhone.setText(findEmployee.getPhone());
    	    	txtUpdateEmployeeUser.setText(findEmployee.getUsername());
    	    	txtUpdateEmployeePassword.setText(findEmployee.getPassword());    	       	    	
    		}    			
    	}
    }

    @FXML
    public void buttonUpdateEmployeeUpdate(ActionEvent event) throws IOException {
    	if (!txtUpdateEmployeeId.getText().equals("")) {
    		Employee findEmployee = restaurant.findEmployeeBinarySearch(txtUpdateEmployeeId.getText());
    		if (findEmployee!=null) {
    			
    			if (!txtUpdateEmployeeName.getText().equals("") && !txtUpdateEmployeeLastName.getText().equals("") &&
    					!txtUpdateEmployeePhone.getText().equals("") && !txtUpdateEmployeeUser.getText().equals("") &&
    					!txtUpdateEmployeePassword.getText().equals("")) {
    				
    				String name = txtUpdateEmployeeName.getText();
    				String lastName = txtUpdateEmployeeLastName.getText();
    				String phone = txtUpdateEmployeePhone.getText();
    				String user = txtUpdateEmployeeUser.getText();
    				String password = txtUpdateEmployeePassword.getText();
    				
    				
    				restaurant.updateEmployee(txtUpdateEmployeeId.getText(),name,lastName,phone,user,password);
    				
    				Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Empleado actualizado satisfactotiamente");
					alert.setHeaderText("El empleado ha sido actuaizado");
					alert.setContentText("El empleado fue actualizado satisfactoriamente.");
					alert.showAndWait();
					
			    	txtUpdateEmployeeId.setText("");
			    	txtUpdateEmployeeName.setText("");
			    	txtUpdateEmployeeLastName.setText("");
			    	txtUpdateEmployeePhone.setText("");
			    	txtUpdateEmployeeUser.setText("");
			    	txtUpdateEmployeePassword.setText("");    				
    				
    			}else {
    				Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Error al actualizar datos");
    				alert.setHeaderText("Todos los campos son requeridos");
    				alert.setContentText("Todos los campos deben estar llenos");
    				alert.showAndWait();
    			}
    		}else {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error al buscar el empleado");
    			alert.setHeaderText("Empleado no encontrado");
    			alert.setContentText("El empleado con id "+txtUpdateEmployeeId.getText()+" no se ha encontrado.");
    			alert.showAndWait();
    		}
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al actualizar datos");
			alert.setHeaderText("Todos los campos son requeridos");
			alert.setContentText("Es necesario el id para realizar la búsqueda del empleado a eliminar");
			alert.showAndWait();
    	}
    }
    
    @FXML
    public void buttonCancelUpdateEmployee(ActionEvent event) {
    	txtUpdateEmployeeId.setText("");
    	txtUpdateEmployeeName.setText("");
    	txtUpdateEmployeeLastName.setText("");
    	txtUpdateEmployeePhone.setText("");
    	txtUpdateEmployeeUser.setText("");
    	txtUpdateEmployeePassword.setText("");    	
    }


    
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+  
    //CREATE-PRODUCT.FXML THINGS**************************************************************************
       
    
    @FXML
    private Pane mainPaneCreateProduct;

    @FXML
    private TextField txtIdProduct;
    
    @FXML
    private TextField txtNameProduct;

    @FXML
    private ChoiceBox<String> choiceBoxCategoryProduct;

    @FXML
    private ChoiceBox<String> choiceBoxSizeProduct;
    
    @FXML
    private TextField txtPriceProduct;

    @FXML
    private TextField txtAvailabilityProduct;

    @FXML
    private TextArea txtDescriptionProduct;
    
    
    public void initializeChoiceBoxCategoryProduct(){
    	ObservableList<String>categories = FXCollections.observableArrayList("Bebida","Entrada","Plato principal","Postre","Vino","Ensalada","Respostería");
    	choiceBoxCategoryProduct.setItems(categories);
    }
    
    public void initializeChoiceBoxSizeProduct(){
    	ObservableList<String>sizes = FXCollections.observableArrayList("Personal","Para dos","Familiar");
    	choiceBoxSizeProduct.setItems(sizes);
    }

    @FXML
    public void createProduct(ActionEvent event) {
    	String id = txtIdProduct.getText();
    	String name = txtNameProduct.getText();
    	String category = choiceBoxCategoryProduct.getValue();
    	String size= choiceBoxSizeProduct.getValue();
    	String price = txtPriceProduct.getText();
    	// int available = Integer.parseInt(txtAvailabilityProduct.getText());
    	String description = txtDescriptionProduct.getText();

    	
    	
    	if (!id.equals("") && !name.equals("") && !category.equals("") && !size.equals("") && !price.equals("") && !txtAvailabilityProduct.getText().equals("") && !description.equals("")) {
    		try {
    			Product findProduct =restaurant.findProduct(id);
    			if (findProduct==null) {
    				restaurant.addProduct(id,name,category,size,price,Integer.parseInt(txtAvailabilityProduct.getText()),description);

    				Alert alert = new Alert(AlertType.CONFIRMATION);
    				alert.setTitle("Creación del producto");
    				alert.setHeaderText("El producto ha sido creado");
    				alert.setContentText("El producto con id "+id+" ha sido creado exitosamente.");
    				alert.showAndWait();

    				txtIdProduct.setText("");
    				txtNameProduct.setText("");
    				choiceBoxCategoryProduct.getSelectionModel().clearSelection();
    				choiceBoxSizeProduct.getSelectionModel().clearSelection();
    				txtPriceProduct.setText("");
    				txtAvailabilityProduct.setText("");
    				txtDescriptionProduct.setText("");
    			}else {
    				Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Error en la creación del producto");
    				alert.setHeaderText("El producto ya existe");
    				alert.setContentText("El producto con id "+id+" ya se ha creado.");
    				alert.showAndWait();
    			}
    			
    			
    		}catch(NoNumericInputException nnie) {
        		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error al crear el producto");
    			alert.setHeaderText("No fue posible crear el producto");
    			alert.setContentText(nnie.getMessage());
    			alert.showAndWait();
    		}

    	}else if (id.equals("") || name.equals("") || category.equals("") || size.equals("") || price.equals("") || txtAvailabilityProduct.getText().equals("") || description.equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al guardar datos");
    		alert.setHeaderText("Campos incompletos");
    		alert.setContentText("Todos los campos deben ser llenados");
    		alert.showAndWait();
    	}
    }

    @FXML
    public void noCreateProduct(ActionEvent event) {
       	txtIdProduct.setText("");
        txtNameProduct.setText("");
        choiceBoxCategoryProduct.setValue(null);
        choiceBoxSizeProduct.setValue(null);
        txtPriceProduct.setText("");
        txtAvailabilityProduct.setText("");
        txtDescriptionProduct.setText("");
    }
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+     
    //DELETE PRODUCT THINGS**********************************************************************************************************************************************************
    @FXML
    private TextField deleteProductId;

    @FXML
    private TextField deleteProductName;

    @FXML
    private TextField deleteProductCategory;

    @FXML
    private TextField deleteProductPrice;

    @FXML
    private TextField deleteProductAvailability;

    @FXML
    private TextField deleteProductSize;

    @FXML
    public void deleteProductButtonCancel(ActionEvent event) {
    	deleteProductId.setText("");
    	deleteProductName.setText("");
    	deleteProductCategory.setText("");
    	deleteProductPrice.setText("");
    	deleteProductAvailability.setText("");
    	deleteProductSize.setText("");
    }

    @FXML
    public void deleteProductButtonEliminar(ActionEvent event) {
    	if (!deleteProductId.getText().equals("")) {
    		 Product findProduct= restaurant.findProductBinarySearch(deleteProductId.getText());
    		 
    	        if(findProduct!=null) {
    	        	restaurant.deleteProduct(deleteProductId.getText());
    	        	
    				Alert alert = new Alert(AlertType.CONFIRMATION);
    				alert.setTitle("Producto eliminado");
    				alert.setHeaderText("El producto ha sido eliminado satisfactoriamente");
    				alert.setContentText("El producto con "+findProduct.getName()+" ha sido eliminado");
    				alert.showAndWait();
    				
    				deleteProductId.setText("");
    	    		deleteProductName.setText("");
    	    		deleteProductAvailability.setText("");
    	    		deleteProductCategory.setText("");
    	    		deleteProductPrice.setText("");
    	    		deleteProductSize.setText("");
    	        }
    	        else {
    				Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Error en la eliminación del producto");
    				alert.setHeaderText("Producto no encontrado");
    				alert.setContentText("El producto con id "+deleteProductId.getText()+" no se ha encontrado.");
    				alert.showAndWait();
    	        }
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la eliminación del producto");
			alert.setHeaderText("Campos incompletos");
			alert.setContentText("Es necesario el id del productos para poder eliminarlo");
			alert.showAndWait();
    	}
       
    }

    @FXML
    public void findProductById(ActionEvent event) {
    	Product findProduct= restaurant.findProductBinarySearch(deleteProductId.getText());
    	
    	if(findProduct!=null) {
    		deleteProductName.setText(findProduct.getName());
    		deleteProductAvailability.setText(String.valueOf(findProduct.getAvailability()));
    		deleteProductCategory.setText(findProduct.getCategory());
    		deleteProductPrice.setText(findProduct.getPrice());
    		deleteProductSize.setText(findProduct.getSize());
    	}
    	else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al buscar el producto");
			alert.setHeaderText("Producto no encontrado");
			alert.setContentText("El producto con id "+deleteProductId.getText()+" no se ha encontrado.");
			alert.showAndWait();
    	}
    }
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+     
    //UPDATE PRODUCT THINGS**********************************************************************************************************************************************************
     
    @FXML
    private Pane mainPaneUpdateProduct;

    @FXML
    private TextField txtUpdateProductId;

    @FXML
    private TextField txtUpdateProductName;

    @FXML
    private TextField txtUpdateProductPrice;

    @FXML
    private TextField txtUpdateProductAvailability;

    @FXML
    private ChoiceBox<String> choiceBoxUpdateCategoryProduct;
    
    @FXML
    private ChoiceBox<String> choiceBoxUpdateSizeProduct;
    
    

    @FXML
    public void buttonUpdateProductFind(ActionEvent event) {
    	if(txtUpdateProductId.getText()!=null) {
    		Product product=restaurant.findProductBinarySearch(txtUpdateProductId.getText());
    		if(product!=null) {
    			txtUpdateProductName.setText(product.getName());
    			txtUpdateProductPrice.setText(product.getPrice());
    			txtUpdateProductAvailability.setText(String.valueOf(product.getAvailability()));
    			choiceBoxUpdateCategoryProduct.setValue(product.getCategory());
    			choiceBoxUpdateSizeProduct.setValue(product.getSize());
	
    		}else {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error al buscar el producto");
    			alert.setHeaderText("Producto no encontrado");
    			alert.setContentText("El producto con id "+txtUpdateProductId.getText()+" no se ha encontrado.");
    			alert.showAndWait();
    		}
    		
    	}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al buscar el producto");
			alert.setHeaderText("Campo de id requerido");
			alert.setContentText("El campo del id debe ser ingresado.");
			alert.showAndWait();
    	}
    }

    @FXML
    public void buttonUpdateProductUpdate(ActionEvent event) { //-->Clase AforeGUI
    	String name=txtUpdateProductName.getText();
    	String price=txtUpdateProductPrice.getText();
    	int availability = Integer.parseInt(txtUpdateProductAvailability.getText());

    	if (!txtUpdateProductId.getText().equals("")) {
    		Product product=restaurant.findProductBinarySearch(txtUpdateProductId.getText());

    		if(product!=null) {

    			if(choiceBoxUpdateSizeProduct.getSelectionModel().isEmpty() == false && choiceBoxUpdateCategoryProduct.getSelectionModel().isEmpty()==false && !name.equals("") && !price.equals("") && availability!=0) {
    				try {

    					
						restaurant.updateProduct(txtUpdateProductId.getText(),name,choiceBoxUpdateCategoryProduct.getSelectionModel().getSelectedItem(),choiceBoxUpdateSizeProduct.getSelectionModel().getSelectedItem(),price, availability);
					
    					
    					Alert alert = new Alert(AlertType.CONFIRMATION);
    					alert.setTitle("Producto actualizado satisfactotiamente");
    					alert.setHeaderText("El producto ha sido actuaizado");
    					alert.setContentText("El producto fue actualizado satisfactoriamente.");
    					alert.showAndWait();
    				

    					txtUpdateProductName.setText("");
    					txtUpdateProductId.setText("");
    					txtUpdateProductPrice.setText("");
    					txtUpdateProductAvailability.setText("");
    					choiceBoxUpdateCategoryProduct.getSelectionModel().clearSelection();
    					choiceBoxUpdateSizeProduct.getSelectionModel().clearSelection();


    				}catch(NumberFormatException e) {
    					Alert alert = new Alert(AlertType.ERROR);
    					alert.setTitle("Error al leer disponibilidad");
    					alert.setHeaderText("La disponibilidad debe ser numérica");
    					alert.setContentText("La disponibilidad "+txtUpdateProductAvailability.getText()+" no es correcta, debe ser numérica.");
    					alert.showAndWait();
    				}
    			}
    			else {
    				Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Error al actualizar datos");
    				alert.setHeaderText("Todos los campos son requeridos");
    				alert.setContentText("Todos los campos deben estar llenos");
    				alert.showAndWait();
    			}
    		}else {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error al buscar el producto");
    			alert.setHeaderText("Producto no encontrado");
    			alert.setContentText("El producto con id "+txtUpdateProductId.getText()+" no se ha encontrado.");
    			alert.showAndWait();
    		}
    	}    	
    }

    @FXML
    public void buttonUpdateProuctCancel(ActionEvent event) {
    	txtUpdateProductName.setText("");
    	txtUpdateProductId.setText("");
    	txtUpdateProductPrice.setText("");
    	txtUpdateProductAvailability.setText("");
    	choiceBoxUpdateCategoryProduct.getSelectionModel().clearSelection();
    	choiceBoxUpdateSizeProduct.getSelectionModel().clearSelection();
    }
    
  //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+     
    //DISABLE PRODUCT THINGS**********************************************************************************************************************************************************
     

    @FXML
    private Pane mainPaneDisableProduct;

    @FXML
    private TextField txtDisableProductId;

    @FXML
    private TextField txtDisableProductName;

    @FXML
    private RadioButton rbDisableProductHabilitado;

    @FXML
    private RadioButton rbDisableProductDeshabilitado;

    @FXML
    private TextField txtDisableProductSize;

    @FXML
    private TextField txtDisableProductPrice;

    @FXML
    private TextField txtDisableProductCategory;

    @FXML
    private TextField txtDisableProductAvailabiility;
    
    @FXML
    private ToggleGroup state;
    
    @FXML
    public void buttonDisableProductFind(ActionEvent event) {
    	if(txtDisableProductId.getText()!=null) {
    		Product product=restaurant.findProduct(txtDisableProductId.getText());
    		if(product!=null) {
    			txtDisableProductName.setText(product.getName());
    			txtDisableProductPrice.setText(product.getPrice());
    			txtDisableProductAvailabiility.setText(String.valueOf(product.getAvailability()));
    			txtDisableProductCategory.setText(product.getCategory());
    			txtDisableProductSize.setText(product.getSize());
    			if(product.getCondition()==Condition.ACTIVE) {
    				rbDisableProductHabilitado.setSelected(true);
    			}
    			else if(product.getCondition()==Condition.INACTIVE) {
    				rbDisableProductDeshabilitado.setSelected(true);
    			}
	
    		}else {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error al buscar el producto");
    			alert.setHeaderText("Producto no encontrado");
    			alert.setContentText("El producto con id "+txtDisableProductId.getText()+" no se ha encontrado.");
    			alert.showAndWait();
    		}
    		
    	}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al buscar el producto");
			alert.setHeaderText("Campo de id requerido");
			alert.setContentText("El campo del id debe ser ingresado.");
			alert.showAndWait();
    	}
    }

    @FXML
    public void buttonDisableProductUpdateEstado(ActionEvent event) {
    	if(txtDisableProductId.getText()!=null) {
    		Product product=restaurant.findProduct(txtDisableProductId.getText());
    		if(product!=null) {
    			if(rbDisableProductDeshabilitado.isSelected()) {
    				product.setCondition(Condition.INACTIVE);
    				Alert alert = new Alert(AlertType.CONFIRMATION);
    				alert.setTitle("Estado actualizado");
    				alert.setHeaderText("El estado ha sido cambiado");
    				alert.setContentText("El estado del producto ha sido cambiado a inactivo");
    				alert.showAndWait();
    				
    			}else if(rbDisableProductHabilitado.isSelected()){
    				product.setCondition(Condition.ACTIVE);
    				Alert alert = new Alert(AlertType.CONFIRMATION);
    				alert.setTitle("Estado actualizado");
    				alert.setHeaderText("El estado ha sido cambiado");
    				alert.setContentText("El estado del producto ha sido cambiado a activo");
    				alert.showAndWait();
    			}
    			
    			restaurant.exportProductsData();
    			    			
    		}else {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error al buscar el producto");
    			alert.setHeaderText("Producto no encontrado");
    			alert.setContentText("El producto con id "+txtDisableProductId.getText()+" no se ha encontrado.");
    			alert.showAndWait();
    		}
    	}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al buscar el producto");
			alert.setHeaderText("Campo de id requerido");
			alert.setContentText("El campo del id debe ser ingresado.");
			alert.showAndWait();
    	}
    }
    
    //**********************************************************************************************
    //+
    //+
    //+
    //+
    //+     
    //CREATE CLIENT THINGS**********************************************************************************************************************************************************
         
    @FXML
    private Pane mainPaneCreateClient;

    @FXML
    private TextField txtcreateClientName;

    @FXML
    private TextField txtcreateClientId;

    @FXML
    private TextField txtcreateClientAdress;

    @FXML
    private TextField txtcreateClientPhone;

    @FXML
    private TextArea txtcreateClientObservation;

    @FXML
    public void buttonCreateClientRegister(ActionEvent event) throws IOException {
    	if(!txtcreateClientName.getText().equals("") && !txtcreateClientId.getText().equals("")) {
    		String name=txtcreateClientName.getText();
			String id=txtcreateClientId.getText();
			String address=txtcreateClientAdress.getText();
			String obs=txtcreateClientObservation.getText();
			String phone=txtcreateClientPhone.getText();
			
    		Client findClient = restaurant.findClient(id);
    		if (findClient==null) {
    			restaurant.addClient(name, id, address, phone, obs); 

    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Cliente creado");
    			alert.setHeaderText("El cliente ha sido creado");
    			alert.setContentText("El cliente con el id "+id+" ha sido creado satisfactoriamente.");
    			alert.showAndWait();

    			txtcreateClientName.setText("");
    			txtcreateClientId.setText("");
    			txtcreateClientAdress.setText("");
    			txtcreateClientObservation.setText("");
    			txtcreateClientPhone.setText("");
    		}else {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error al crear el cliente");
    			alert.setHeaderText("Cliente Existente");
    			alert.setContentText("El cliente con el id "+id+" ya está registrado en el restaurante.");
    			alert.showAndWait();
    		}
    	}
    	else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al buscar el producto");
			alert.setHeaderText("Campo de id y nombre requeridos");
			alert.setContentText("El campo del id y nombre son obligatorios.");
			alert.showAndWait();	
    	}
    }

    @FXML
    public void buttonCreateClientCancel(ActionEvent event) {
    	txtcreateClientName.setText("");
		txtcreateClientId.setText("");
		txtcreateClientAdress.setText("");
		txtcreateClientObservation.setText("");
		txtcreateClientPhone.setText("");
		
    }

    @FXML
    public void buttonOpenReserva(ActionEvent event) {
    	openScreen("reserva.fxml",paneToChange);
    }
    
    
    //*********************************************************************************************************************************************************************************************+
    //+
    //+
    //+
    //+
    //+     
    //DELETE CLIENT THINGS**********************************************************************************************************************************************************
    
    @FXML
    private Pane mainPaneDeleteClient;

    @FXML
    private TextField txtDeleteClientId;

    @FXML
    private TextField txtDeleteClientName;

    @FXML
    private TextField txtDeleteClientIde;

    @FXML
    private TextField txtDeleteClientAddress;

    @FXML
    private TextField txtDeleteClientPhone;

    @FXML
    private Label labelMaxAfore;

    @FXML
    private Label labelCurrentPeople;

    @FXML
    private Label labelEmptySpaces;

    @FXML
    public void buttonFindClientToDelete(ActionEvent event) {    	
    	Client clientToDelete = restaurant.findClient(txtDeleteClientId.getText());
    	if (clientToDelete!=null) {
    		txtDeleteClientName.setText(clientToDelete.getName());
    		txtDeleteClientIde.setText(clientToDelete.getId());
    		txtDeleteClientAddress.setText(clientToDelete.getAddress());
    		txtDeleteClientPhone.setText(clientToDelete.getPhone());
    			
    	}else if (clientToDelete == null && txtDeleteClientId.getText().equals("")){      	
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la eliminación del cliente");
			alert.setHeaderText("Cliente no encontrado");
			alert.setContentText("Es necesario el id para realizar la búsqueda del cliente");
			alert.showAndWait();
			
    	}else if (clientToDelete == null && !txtDeleteClientId.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la eliminación del cliente");
			alert.setHeaderText("Cliente no encontrado");
			alert.setContentText("El cliente con id "+txtDeleteClientId.getText()+" no se ha encontrado");
			alert.showAndWait();
    	}    	
    }
    
    @FXML
    public void buttonClientToDelete(ActionEvent event) {
    	
    	if (!txtDeleteClientId.getText().equals("")) {
    		Client clientToDelete = restaurant.findClient(txtDeleteClientId.getText());
    		
    		if (clientToDelete!=null) {
        		Order hasOrders = restaurant.findClientOrder(clientToDelete);
        		
        		if (hasOrders == null) {        			
        			
        			restaurant.deleteClient(txtDeleteClientId.getText());
				       		
        		
                	txtDeleteClientId.setText("");
                	txtDeleteClientName.setText("");
            		txtDeleteClientIde.setText("");
            		txtDeleteClientAddress.setText("");
            		txtDeleteClientPhone.setText("");
            		
        			Alert alert = new Alert(AlertType.CONFIRMATION);
        			alert.setTitle("Cliente eliminado");
        			alert.setHeaderText("El cliente ha sido eliminado satisfactoriamente");
        			alert.setContentText("El cliente con id"+clientToDelete.getId()+" ha sido eliminado");
        			alert.showAndWait(); 
        		
        		}else {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error en la eliminación del cliente");
        			alert.setHeaderText("El cliente tiene una orden pendiente");
        			alert.setContentText("El cliente con id"+clientToDelete.getId()+" no podrá ser eliminado");
        			alert.showAndWait(); 
        		}
             		
        		
        	}else{    		
        		Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error en la eliminación del cliente");
    			alert.setHeaderText("Cliente no encontrado");
    			alert.setContentText("El cliente con id "+txtDeleteClientId.getText()+" no se ha encontrado");
    			alert.showAndWait();			
        	}
    	}
    	
    }

    @FXML
    public void buttonNoClientToDelete(ActionEvent event) {
    	txtDeleteClientId.setText("");
    	txtDeleteClientName.setText("");
		txtDeleteClientIde.setText("");
		txtDeleteClientAddress.setText("");
		txtDeleteClientPhone.setText("");
    }
    
    
    //*********************************************************************************************************************************************************************************************+
    //+
    //+
    //+
    //+
    //+     
    //UPDATE CLIENT THINGS**********************************************************************************************************************************************************
    
    @FXML
    private Pane mainPaneUpdateClient;

    @FXML
    private TextField txtUpdateClientName;

    @FXML
    private TextField txtUpdateClientAddress;

    @FXML
    private TextField txtUpdateClientPhone;

    @FXML
    private TextArea txtUpdateClientObs;

    @FXML
    private TextField txtUpdateClientId;
    
    @FXML
    private TextField txtUpdateClientIde;

    @FXML
    public void buttonFindClientToUpdate(ActionEvent event) {
    	Client clientToUpd = restaurant.findClient(txtUpdateClientId.getText());
    	if (clientToUpd!=null) {
    		txtUpdateClientName.setText(clientToUpd.getName());
    		txtUpdateClientIde.setText(clientToUpd.getId());
        	txtUpdateClientAddress.setText(clientToUpd.getAddress());
        	txtUpdateClientPhone.setText(clientToUpd.getPhone());
        	txtUpdateClientObs.setText(clientToUpd.getObservations()); 

    	}else if (clientToUpd == null && txtUpdateClientId.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al actualizar el cliente");
    		alert.setHeaderText("Cliente no encontrado");
    		alert.setContentText("Es necesario el id para realizar la búsqueda del cliente");
    		alert.showAndWait();
    		
    	}else if (clientToUpd == null && !txtUpdateClientId.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al actualizar el cliente");
    		alert.setHeaderText("Cliente no encontrado");
    		alert.setContentText("El cliente con id "+txtUpdateClientId.getText()+" no se ha encontrado.");
    		alert.showAndWait();
    	}
    }
    
    @FXML
    public void buttonClientToUpdate(ActionEvent event) throws IOException { //-->Clase AforeGUI
    	if (!txtUpdateClientId.getText().equals("")){
    		Client clientToUpd = restaurant.findClient(txtUpdateClientId.getText());    	

    		if (clientToUpd!=null) {
    			if (!txtUpdateClientName.getText().equals("") && !txtUpdateClientIde.getText().equals("") && !txtUpdateClientAddress.getText().equals("") && !txtUpdateClientPhone.getText().equals("")) {

    				String name = txtUpdateClientName.getText();
    				String id = txtUpdateClientIde.getText();
    				String address = txtUpdateClientAddress.getText();
    				String phone = txtUpdateClientPhone.getText();

    				restaurant.updateClient(id,name,address,phone,txtUpdateClientObs.getText());

    				Alert alert = new Alert(AlertType.CONFIRMATION);
    				alert.setTitle("Cliente actualizado satisfactoriamente");
    				alert.setHeaderText("Cliente actualizado");
    				alert.setContentText("Se ha actualizado la información del cliente.");
    				alert.showAndWait();

    				txtUpdateClientId.setText("");
    				txtUpdateClientName.setText("");
    				txtUpdateClientIde.setText("");
    				txtUpdateClientAddress.setText("");
    				txtUpdateClientPhone.setText("");
    				txtUpdateClientObs.setText(""); 

    			}else {
    				Alert alert = new Alert(AlertType.ERROR);
    				alert.setTitle("Error al actualizar el cliente");
    				alert.setHeaderText("Datos incompletos");
    				alert.setContentText("El nombre, dirección y teléfono son necesarios.");
    				alert.showAndWait();
    			}	

    		}else{
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error al actualizar el cliente");
    			alert.setHeaderText("Cliente no encontrado");
    			alert.setContentText("El cliente con id "+txtUpdateClientId.getText()+" no se ha encontrado.");
    			alert.showAndWait();

    		}
    	}    
    }  

    
    
    @FXML
    public void buttonNoClientToUpdate(ActionEvent event) {
    	txtUpdateClientId.setText("");
    	txtUpdateClientName.setText("");
    	txtUpdateClientIde.setText("");
    	txtUpdateClientAddress.setText("");
    	txtUpdateClientPhone.setText("");
    	txtUpdateClientObs.setText("");    	
    }
    
    
    //*********************************************************************************************************************************************************************************************+
    //+
    //+
    //+
    //+
    //+     
    //DISABLE CLIENT THINGS**********************************************************************************************************************************************************
   
    @FXML
    private Pane mainPaneDisableClient;

    @FXML
    private TextField txtDisableClientId;

    @FXML
    private TextField txtDisableClientName;

    @FXML
    private TextField txtDisableClientIde;

    @FXML
    private TextField txtDisableClientAddress;

    @FXML
    private TextField txtDisableClientPhone;

    @FXML
    private RadioButton rbDisableClientHabilitado;

    @FXML
    private RadioButton rbDisableClientDeshabilitado;

    @FXML
    public void buttonFindClientToDisable(ActionEvent event) {
    	Client clientToDis = restaurant.findClient(txtDisableClientId.getText());
    	
    	if (clientToDis!=null) {
    		txtDisableClientName.setText(clientToDis.getName());
    		txtDisableClientIde.setText(clientToDis.getId());
    		txtDisableClientAddress.setText(clientToDis.getAddress());
    		txtDisableClientPhone.setText(clientToDis.getPhone());
    		if (clientToDis.getCondition() == Condition.ACTIVE) {
    			rbDisableClientHabilitado.setSelected(true);
    		}else if (clientToDis.getCondition() == Condition.INACTIVE) {
    			rbDisableClientDeshabilitado.setSelected(true);
    		}
    		
    	}else if (clientToDis == null && txtDisableClientId.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al buscar el cliente");
    		alert.setHeaderText("Cliente no encontrado");
    		alert.setContentText("Es necesario el id para realizar la búsquda del cliente");
    		alert.showAndWait();
    		
    	}else if (clientToDis == null && !txtDisableClientId.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al buscar el cliente");
    		alert.setHeaderText("Cliente no encontrado");
    		alert.setContentText("El cliente con id "+txtDisableClientId.getText()+" no se ha encontrado.");
    		alert.showAndWait();    		
    	}
    }

    @FXML
    public void butttonDisableClientUpdateState(ActionEvent event) throws IOException {    	
    	if (!txtDisableClientId.getText().equals("")) {
    		Client clientToDis = restaurant.findClient(txtDisableClientId.getText());
        	
        	if (clientToDis!=null) {
        		if (rbDisableClientHabilitado.isSelected()) {
        			clientToDis.setCondition(Condition.ACTIVE);    
        			Alert alert = new Alert(AlertType.CONFIRMATION);
    				alert.setTitle("Estado actualizado");
    				alert.setHeaderText("El estado ha sido cambiado");
    				alert.setContentText("El estado del cliente ha sido cambiado a activo");
    				alert.showAndWait();
        		}else if (rbDisableClientDeshabilitado.isSelected()) {
        			clientToDis.setCondition(Condition.INACTIVE);
        			Alert alert = new Alert(AlertType.CONFIRMATION);
    				alert.setTitle("Estado actualizado");
    				alert.setHeaderText("El estado ha sido cambiado");
    				alert.setContentText("El estado del cliente ha sido cambiado a inactivo");
    				alert.showAndWait();
        		}
        		
        		restaurant.saveClientsData();
        		//Aquí pongo el método de persistencia
        		
        		txtDisableClientId.setText("");
        		txtDisableClientName.setText("");
        		txtDisableClientIde.setText("");
        		txtDisableClientAddress.setText("");
        		txtDisableClientPhone.setText("");
        		if (rbDisableClientHabilitado.isSelected()) {
        			rbDisableClientHabilitado.setSelected(false);
        		}else if (rbDisableClientDeshabilitado.isSelected()) {
        			rbDisableClientDeshabilitado.setSelected(false);
        		}
        		
        		        		
        	}else {
        		Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("Error al buscar el cliente");
        		alert.setHeaderText("Cliente no encontrado");
        		alert.setContentText("El cliente con id "+txtDisableClientId.getText()+" no se ha encontrado.");
        		alert.showAndWait();
        	}
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error al buscar el cliente");
    		alert.setHeaderText("Campos incompletos");
    		alert.setContentText("El id es necesario para realizar la búsqueda del cliente");
    		alert.showAndWait();
    	}
    
    }
    
    
    
    //*********************************************************************************************************************************************************************************************+
    //+
    //+
    //+
    //+
    //+     
    //EMPLOYEES THINGS**********************************************************************************************************************************************************
   
    @FXML
    private TextField txtEntryEmployeeId;

    @FXML
    private TextField txtEntryEmployeeName;

    @FXML
    private TextField txtEntryEmployeePhone;

    @FXML
    private TextField txtEntryEmployeeUser;

    @FXML
    private TextField txtEntryEmployeeWork;

    @FXML
    private TextField txtExitEmployeeId;

    @FXML
    private TextField txtExitEmployeeName;

    @FXML
    private TextField txtExitEmployeePhone;

    @FXML
    private TextField txtExitEmployeeUser;

    @FXML
    private TextField txtExitEmployeeWork;

    @FXML
    private Label labelHour;
  
	@FXML
    void buttonEntry(ActionEvent event) {
    	
    }

    @FXML
    void buttonExit(ActionEvent event) {

    }

    @FXML
    void buttonFindEntryEmployee(ActionEvent event) {

    }

    @FXML
    void buttonFindExitEmployee(ActionEvent event) {

    }

    @FXML
    void buttonNoEntry(ActionEvent event) {

    }

    @FXML
    void buttonNoExit(ActionEvent event) {

    }
    
  //*********************************************************************************************************************************************************************************************+
    //+
    //+
    //+
    //+
    //+     
    //GRADE-SERVICE THINGS**********************************************************************************************************************************************************
   
    @FXML
    private Pane mainPaneGradeServiceClient;

    @FXML
    private ToggleGroup atencionGrade;

    @FXML
    private ToggleGroup comidaGrade;
    
    @FXML
    private RadioButton rbAtencionGrade1;
    @FXML
    private RadioButton rbAtencionGrade2;
    @FXML
    private RadioButton rbAtencionGrade3;
    @FXML
    private RadioButton rbAtencionGrade4;
    @FXML
    private RadioButton rbAtencionGrade5;

    @FXML
    private RadioButton rbFoodGrade1;
    @FXML
    private RadioButton rbFoodGrade2;
    @FXML
    private RadioButton rbFoodGrade3;
    @FXML
    private RadioButton rbFoodGrade4;
    @FXML
    private RadioButton rbFoodGrade5;
    
    @FXML
    private TextField txtDay;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtYear;

    @FXML
    public void buttonRegisterGrades(ActionEvent event) {
    	if(atencionGrade.getSelectedToggle()!=null && comidaGrade.getSelectedToggle()!=null) {
    		int gradeAtencion=0;
    		int gradeFood=0;
    		
    		if(rbAtencionGrade1.isSelected()) {
    			gradeAtencion=1;
    		}else if(rbAtencionGrade2.isSelected()) {
    			gradeAtencion=2;
    		}else if(rbAtencionGrade3.isSelected()) {
    			gradeAtencion=3;
    		}else if(rbAtencionGrade4.isSelected()) {
    			gradeAtencion=4;
    		}else{
    			gradeAtencion=5;
    		}
    		
    		if(rbFoodGrade1.isSelected()) {
    			gradeFood=1;
    		}else if(rbFoodGrade2.isSelected()) {
    			gradeFood=2;
    		}else if(rbFoodGrade3.isSelected()) {
    			gradeFood=3;
    		}else if(rbFoodGrade4.isSelected()) {
    			gradeFood=4;
    		}else{
    			gradeFood=5;
    		}
    		
    		restaurant.addGrade(restaurant.getRootDay(), gradeAtencion, gradeFood, Integer.parseInt(txtDay.getText()),Integer.parseInt(txtMonth.getText()), Integer.parseInt(txtYear.getText()) );
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Campos necesarios");
    		alert.setHeaderText("Campos obligatorios");
    		alert.setContentText("Debe escoger una calificacion al servicio y a la comida.");
    		alert.showAndWait();
    	}
    }
    
    //*********************************************************************************************************************************************************************************************+
    //+
    //+
    //+
    //+
    //+     
    //FOOD-DELIVERY THINGS**********************************************************************************************************************************************************
   
    
    @FXML
    private Pane mainPaneDomicilio;

    @FXML
    private RadioButton rbPickUpRestaurant;

    @FXML
    private RadioButton rbScheduleShipment;

    @FXML
    private ListView<String> listViewOfProducts;

    @FXML
    private TextField txtAmountOfProduct;

    @FXML
    private Label labelShippingTime;
    
    @FXML
    private TextField txtShippingTime;

    @FXML
    private TableView<Product> tableViewReceipt;
    
    @FXML
    private TableColumn<Product,String> tableColumnProductName;

    @FXML
    private TableColumn<Product,Integer> tableColumnProductAmount;

    @FXML
    private TableColumn<Product,String> tableColumnProductPrice;

    @FXML
    private TextField txtClientNameToDelivery;
    
    ObservableList<Product> productsDelivery = FXCollections.observableArrayList();
    
    public void initializeTableViewDelivery() {     	
    	tableColumnProductName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
    	tableColumnProductAmount.setCellValueFactory(new PropertyValueFactory<Product,Integer>("amountOfProduct"));
    	tableColumnProductPrice.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
    	
    	tableViewReceipt.setItems(productsDelivery);   	
    }
    
    @FXML
    public void buttonAddProductToReceiptDelivery(ActionEvent event) {
    	if (listViewOfProducts.getSelectionModel().isEmpty()==false && !txtAmountOfProduct.getText().equals("")) {    		
    		Product product = restaurant.findProductByName(listViewOfProducts.getSelectionModel().getSelectedItem());
    			if (product!=null) {
    				product.setAmountOfProduct(Integer.parseInt(txtAmountOfProduct.getText()));
    				if (product.getAmountOfProduct() <= product.getAvailability()) {
    					productsDelivery.add(product);    					
        				initializeTableViewDelivery();
    				}else {
    		    		Alert alert = new Alert(AlertType.ERROR);
    		    		alert.setTitle("Error");
    		    		alert.setHeaderText("Sin disponibilidad");
    		    		alert.setContentText("No se tiene la cantidad necesaria del producto para realizar el pedido, por favor ingrese una cantidad más baja o cambie el producto");
    		    		alert.showAndWait();
    				}
    				
    			}else {
		    		Alert alert = new Alert(AlertType.ERROR);
		    		alert.setTitle("Error");
		    		alert.setHeaderText("Producto no encontrado");
		    		alert.setContentText("El nombre del producto seleccionado no coincide con los productos del restaurant");
		    		alert.showAndWait();
    			}
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Campos incompletos");
    		alert.setContentText("Es necesario escoger un producto he ingresar su cantidad para añadirlo a la factura");
    		alert.showAndWait();
    	}
    	
    	txtAmountOfProduct.setText("");
    }

    @FXML
    public void buttonCancelDelivery(ActionEvent event) {
    	productsDelivery.removeAll(productsDelivery);
    	initializeTableViewDelivery();
    }

    @FXML
    public void buttonDeleteReceipt(ActionEvent event) {
    	if (tableViewReceipt.getSelectionModel().isEmpty()==false) {
        	Product productDelete = tableViewReceipt.getSelectionModel().getSelectedItem();
        	productsDelivery.remove(productDelete);
        	initializeTableViewDelivery();
    	}

    }

    @FXML
    public void buttonSendReceipt(ActionEvent event) {
    	if (!txtClientNameToDelivery.getText().equals("") && !txtShippingTime.getText().equals("") && productsDelivery!=null) {
    		TextInputDialog dialog = new TextInputDialog();
    		dialog.setTitle("Text Input Dialog");
    		dialog.setHeaderText("Envío de la factura al correo electrónico");
    		dialog.setContentText("Por favor ingrese el correo electrónico el cliente:"); 
    		// Traditional way to get the response value.
    		Optional<String> result = dialog.showAndWait();
    		if (result.isPresent()){
    			enviarConGmail(result.get(),"Esto es una prueba","Confirma recibido");
    		    System.out.println("Your email: " + result.get());
    		}
    		
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Campos incompletos");
    		alert.setContentText("Es necesario el nombre del cliente y la hora que el cliente desea su pedid para enviar el recibo");
    		alert.showAndWait();
    	}
    }
    
    
    private void enviarConGmail(String destinatario, String asunto, String cuerpo) {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "dabo.0106@gmail.com";  //Para la dirección dabo.0106@gmail.com

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", "dafaju010604");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, "dafaju010604");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }
    
    
    
    //*********************************************************************************************************************************************************************************************+
    //+
    //+
    //+
    //+
    //+     
    //PRODUCT-ORDER THINGS**********************************************************************************************************************************************************
    
    @FXML
    private Pane mainPaneOrder;   

    @FXML
    private Pane paneChangeWithTable;

    @FXML
    private Pane factura;
    
    @FXML
    private Label labelTable;

    @FXML
    private Label LabelEmployeeName;

    @FXML
    private Label labelTotalToPay;

    @FXML
    private TableView<Product> tableViewReceiptProductOrder;

    @FXML
    private TableColumn<Product,String> tableColumnOrderProductName;

    @FXML
    private TableColumn<Product,Integer> tableColumnOrderAmountProduct;

    @FXML
    private TableColumn<Product,String> tableColumnOrderProductPrice;

    @FXML
    private ListView<String> listViewOfOrderProducts;

    @FXML
    private TextField txtAmounOFProduct;

    private int amountOfTables = 1;
    private int layoutX = 33;
    private int layoutY = 90;
    
    
   
    
    ObservableList<Product>productsToOrder = FXCollections.observableArrayList();
     
     public void initializeTableViewReceiptProductOrder() {
    	 tableColumnOrderProductName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));    	 
    	 tableColumnOrderAmountProduct.setCellValueFactory(new PropertyValueFactory<Product,Integer>("amountOfProduct"));
    	 tableColumnOrderProductPrice.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
    	 
    	 tableViewReceiptProductOrder.setItems(productsToOrder);    	 
     }
     
     public void calculateTotal() {
    	 long total = 0;
    	 for (int i=0;i<productsToOrder.size();i++) {
    		 total += Long.parseLong(productsToOrder.get(i).getPrice()) * productsToOrder.get(i).getAmountOfProduct();    		 
    	 }
    	 
    	 labelTotalToPay.setText(String.valueOf(total));
    	 
    	 
     }

    @FXML
    public void buttonAddProducToReceipt(ActionEvent event) {
    	if (listViewOfOrderProducts.getSelectionModel().isEmpty()==false && !txtAmounOFProduct.getText().equals("")) {
    		Product findProduct = restaurant.findProductByName(listViewOfOrderProducts.getSelectionModel().getSelectedItem());
    		if (findProduct!=null) {
    			findProduct.setAmountOfProduct(Integer.parseInt(txtAmounOFProduct.getText()));
    			if (findProduct.getAmountOfProduct()<= findProduct.getAvailability()) {
    				productsToOrder.add(findProduct);
    				initializeTableViewReceiptProductOrder(); 	
    				calculateTotal();
    				
    			}else {
		    		Alert alert = new Alert(AlertType.ERROR);
		    		alert.setTitle("Error");
		    		alert.setHeaderText("Sin disponibilidad");
		    		alert.setContentText("No se tiene la cantidad necesaria del producto para realizar el pedido, por favor ingrese una cantidad más baja o cambie el producto");
		    		alert.showAndWait();
    			}    			
    			
    		}else {
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Error");
	    		alert.setHeaderText("Producto no encontrado");
	    		alert.setContentText("El nombre del producto seleccionado no coincide con los productos del restaurant");
	    		alert.showAndWait();
    		}
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error");
    		alert.setHeaderText("Campos incompletos");
    		alert.setContentText("Es necesario escoger un producto he ingresar su cantidad para añadirlo a la factura");
    		alert.showAndWait();
    	}
    	txtAmounOFProduct.setText("");
    }

    @FXML
    public void buttonCancelReceipt(ActionEvent event) {
    	productsToOrder.removeAll(productsToOrder);
    	initializeTableViewReceiptProductOrder();
    }

    @FXML
    public void buttonDeleteProductfReceipt(ActionEvent event) {
    	if (tableViewReceiptProductOrder.getSelectionModel().isEmpty()==false) {
    		Product product = tableViewReceiptProductOrder.getSelectionModel().getSelectedItem();
    		productsToOrder.remove(product);
    		initializeTableViewReceiptProductOrder();
    	}
    }

    @FXML
    public void buttonOpenScreenCreateProduct(ActionEvent event) {
    	openScreen("create-product.fxml",paneToChange);
    }

    @FXML
    public void buttonPrintReceipt(ActionEvent event) {
    	Employee employee = restaurant.findEmployeeByUsername(LabelEmployeeName.getText());
    	List<Product> products = new ArrayList<Product>();
    	if (employee!=null && productsToOrder!=null) {
    		products = productsToOrder;
    		try {
				restaurant.addOrder(employee,products);
				Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.setTitle("Orden creada");
	    		alert.setHeaderText("La orden ha sido creada");
	    		alert.setContentText("La orden ha sido creada satisfactoriamente");
	    		alert.showAndWait();
			} catch (IOException e) {				
				e.printStackTrace();
			}
    	
    	}else {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText("Información incompleta");
    		alert.setContentText("Para guardar la orden se necesita un listado de productos y el nombre del empleado que tomó la orden");
    		alert.showAndWait();
    	}
    	
    	//Order order= new Order(employee,products);
    	//order.getProducts().addAll(productsToOrder);
    	//order.setEmployee(actualEmployee);
    	//restaurant.getOrders().add(order);
    }


    @FXML
    public void buttonShowTables(ActionEvent event) { 
    	openScreen("tables.fxml",mainPaneOrder);   
    	recreateRadioButtonsTables();    	
    }
    
    private void recreateRadioButtonsTables() {
    	RadioButton rb;    
    	int i=0;
    	while (i<restaurant.getTables()) {
    	  	if (amountOfTables<10) {
        		rb = new RadioButton("Table 0"+amountOfTables);
        	}else {
        		rb = new RadioButton("Table "+amountOfTables);
        	}    	
        	rb.setLayoutX(layoutX);
        	rb.setLayoutY(layoutY);   	
        	amountOfTables ++;
        	
        	if (layoutX == 341) {    
        		layoutY += 77;
        		layoutX = 33;    		
        	}else {
        		layoutX += 77;
        	}    	    	    	
        
        	rb.setToggleGroup(tgTables);
        	paneTables.getChildren().add(rb);     		
    		i++;    		
    	}    	
    }
    
    //*********************************************************************************************************************************************************************************************+
    //+
    //+
    //+
    //+
    //+     
    //TABLES THINGS**********************************************************************************************************************************************************
    
    @FXML
    private Pane paneTableToChange;

    @FXML
    private AnchorPane paneTables;
    
    private ToggleGroup tgTables = new ToggleGroup();
    
    @FXML
    public void buttonAddTable(ActionEvent event) {    	
    	RadioButton rb;    	
    	if (amountOfTables<10) {
    		rb = new RadioButton("Table 0"+amountOfTables);
    	}else {
    		rb = new RadioButton("Table "+amountOfTables);
    	}    
    	
    	restaurant.setTables(restaurant.getTables()+1);
    	
    	rb.setLayoutX(layoutX);
    	rb.setLayoutY(layoutY);   	
    	
    	amountOfTables ++;
    	
    	if (layoutX == 341) {    
    		layoutY += 63;
    		layoutX = 33;    		
    	}else {
    		layoutX += 77;
    	}    	    	    	
    
    	rb.setToggleGroup(tgTables);
    	paneTables.getChildren().add(rb); 
    	
    	
    }
        
    @FXML
    public void buttonHideTables(ActionEvent event) {   
    	amountOfTables = 1;
    	layoutX = 33;
    	layoutY = 90;
    	openScreen("product-Order.fxml",paneTableToChange);
    	
    	RadioButton rb = (RadioButton) tgTables.getSelectedToggle();
    	if (rb!=null) {
    		if (rb.isSelected()) {

    			labelTable.setText("Mesa "+rb.getText().substring(6));        		
    		}
    	}
   
    	initializeScreenOrder();
    	initializeTableViewReceiptProductOrder();
    	calculateTotal();
    }       
}
