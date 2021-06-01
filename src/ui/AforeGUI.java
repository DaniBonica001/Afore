package ui;

import java.io.IOException;
import java.text.ParseException;

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
import model.Product;
import model.Restaurant;


public class AforeGUI {	
	
	//Relations
	private Restaurant restaurant;
	
	public AforeGUI(Restaurant rest) {
		restaurant = rest;
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
    public void buttonLogin(ActionEvent event) throws IOException {
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
    		choiceBoxCategoryProduct.setValue(null);
    		choiceBoxSizeProduct.setValue(null);
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
        Product findProduct= restaurant.findProduct(deleteProductId.getText());
        
        if(findProduct!=null) {
        	restaurant.getProducts().remove(findProduct);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Producto eliminado");
			alert.setHeaderText("El producto ha sido eliminado satisfactoriamente");
			alert.setContentText("El producto con "+findProduct.getName()+" ha sido eliminado");
			alert.showAndWait();
			deleteProductId.setText(null);
    		deleteProductName.setText(null);
    		deleteProductAvailability.setText(null);
    		deleteProductCategory.setText(null);
    		deleteProductPrice.setText(null);
    		deleteProductSize.setText(null);
        }
        else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error en la eliminación del producto");
			alert.setHeaderText("Producto no encontrado");
			alert.setContentText("El producto con id "+deleteProductId.getText()+" no se ha encontrado.");
			alert.showAndWait();
        }
    }

    @FXML
    public void findProductById(ActionEvent event) {
    	Product findProduct= restaurant.findProduct(deleteProductId.getText());
    	
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
    		Product product=restaurant.findProduct(txtUpdateProductId.getText());
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
    	
    	Product product=restaurant.findProduct(txtUpdateProductId.getText());
    	if(product!=null) {
    		if(choiceBoxUpdateSizeProduct.getValue()!=null && choiceBoxUpdateCategoryProduct.getValue()!=null && !name.equals("") && !price.equals("") && !availability.equals("")) {
	    		try {
	    		product.setName(name);
	    		product.setPrice(price);
	    		product.setSize(choiceBoxUpdateSizeProduct.getValue());
	    		product.setAvailability(Integer.parseInt(availability));
	    		product.setCategory(choiceBoxUpdateCategoryProduct.getValue());
	    		
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Producto actualizado satisfactotiamente");
				alert.setHeaderText("El producto ha sido actuaizado");
				alert.setContentText("El producto fue actualizado satisfactoriamente.");
				alert.showAndWait();
				
		    	txtUpdateProductName.setText(null);
		    	txtUpdateProductId.setText(null);
		    	txtUpdateProductPrice.setText(null);
		    	txtUpdateProductAvailability.setText(null);
		    	choiceBoxUpdateCategoryProduct.setValue(null);
		    	choiceBoxUpdateSizeProduct.setValue(null);
	    		
	    		
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

    @FXML
    public void buttonUpdateProuctCancel(ActionEvent event) {
    	txtUpdateProductName.setText(null);
    	txtUpdateProductId.setText(null);
    	txtUpdateProductPrice.setText(null);
    	txtUpdateProductAvailability.setText(null);
    	choiceBoxUpdateCategoryProduct.setValue(null);
    	choiceBoxUpdateSizeProduct.setValue(null);
    	
    }
    
}
