package ui;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Client;
import model.Condition;
import model.Order;
import model.Product;
import model.Restaurant;



public class AforeGUI {	
	
	//Relations
	private Restaurant restaurant;
	
	//Constructor #1
	public AforeGUI(Restaurant rest) {
		restaurant = rest;
	}
	
	//Constructor #2
	public AforeGUI() {		
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
    public void buttonLogin(ActionEvent event) {
    	openScreen("menu.fxml",mainPaneLogin);    	
    }
    
    @FXML
    void buttonLoginAdministrator(ActionEvent event) {
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
    void buttonOpenEmployeeManagement(ActionEvent event) {
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
    		restaurant.addEmployee(userCashier,passCashier,nameCashier,lastNameCashier,idCashier,phoneCashier,waiter);
    		
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
    		
    		restaurant.addEmployee(userChef, passChef, nameChef, lastNameChef, idChef, phoneChef, dishes);
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
    		
    		restaurant.addEmployee(userWaiter,passWaiter,nameWaiter,lastNameWaiter,idWaiter,phoneWaiter,tables);
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
    		
    		restaurant.addEmployeeDM(userDM, passDM, nameDM, lastNameDm, idDM, phoneDM, orders);
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
    		restaurant.addProduct(id,name,category,size,price,Integer.parseInt(txtAvailabilityProduct.getText()),description);
    		txtIdProduct.setText("");
    		txtNameProduct.setText("");
    		choiceBoxCategoryProduct.getSelectionModel().clearSelection();
    		choiceBoxSizeProduct.getSelectionModel().clearSelection();
    		txtPriceProduct.setText("");
    		txtAvailabilityProduct.setText("");
    		txtDescriptionProduct.setText("");

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
    public void buttonUpdateProductUpdate(ActionEvent event) {
    	String name=txtUpdateProductName.getText();
    	String price=txtUpdateProductPrice.getText();
    	String availability=txtUpdateProductAvailability.getText();
    	
    	if (!txtUpdateProductId.getText().equals("")) {
    		Product product=restaurant.findProductBinarySearch(txtUpdateProductId.getText());
    		
        	if(product!=null) {
        		
        		if(choiceBoxUpdateSizeProduct.getSelectionModel().isEmpty() == false && choiceBoxUpdateCategoryProduct.getSelectionModel().isEmpty()==false && !name.equals("") && !price.equals("") && !availability.equals("")) {
    	    		try {
    	    		product.setName(name);
    	    		product.setPrice(price);
    	    		product.setSize(choiceBoxUpdateSizeProduct.getValue());
    	    		product.setAvailability(Integer.parseInt(availability));
    	    		product.setCategory(choiceBoxUpdateCategoryProduct.getValue());
    	    		//Aquí llamo a la persistencia
    	    		
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
    			
    			//Aquí invoco el método de la resistencia
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
    public void buttonCreateClientRegister(ActionEvent event) {
    	if(!txtcreateClientName.getText().equals("") && !txtcreateClientId.getText().equals("")) {
    		String name=txtcreateClientName.getText();
    		String id=txtcreateClientId.getText();
    		String address=txtcreateClientAdress.getText();
    		String obs=txtcreateClientObservation.getText();
    		String phone=txtcreateClientPhone.getText();
    		
    		restaurant.addClient(name, id, address, phone, obs); 	
    		
        	txtcreateClientName.setText("");
    		txtcreateClientId.setText("");
    		txtcreateClientAdress.setText("");
    		txtcreateClientObservation.setText("");
    		txtcreateClientPhone.setText("");
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
    public void buttonClientToUpdate(ActionEvent event) {
    	Client clientToUpd = restaurant.findClient(txtUpdateClientId.getText());    	
    	
    	if (clientToUpd!=null) {
    		if (!txtUpdateClientName.getText().equals("") && !txtUpdateClientIde.getText().equals("") && !txtUpdateClientAddress.getText().equals("") && !txtUpdateClientPhone.getText().equals("")) {
        		
        		String name = txtUpdateClientName.getText();
        		String id = txtUpdateClientIde.getText();
        		String address = txtUpdateClientAddress.getText();
        		String phone = txtUpdateClientPhone.getText();
        		
        		clientToUpd.setName(name);
        		clientToUpd.setId(id);
        		clientToUpd.setAddress(address);
        		clientToUpd.setPhone(phone);
        		clientToUpd.setObservations(txtUpdateClientObs.getText());    
        		//Aquí pongo el método de persistencia
        		
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
    public void butttonDisableClientUpdateState(ActionEvent event) {    	
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
    

    
}
