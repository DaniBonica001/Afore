package ui;

import java.io.IOException;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    	/*    	
    	FXMLLoader menufxml = new FXMLLoader (getClass().getResource("menu.fxml"));
    	menufxml.setController(this);
    	Parent root = menufxml.load();
    	mainPaneLogin.getChildren().setAll(root);
    	*/
    	
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
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-client.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }
 
    @FXML
    public void buttonCreateProduct(ActionEvent event) throws IOException{
    	openScreen("create-product.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-product.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    	initializeChoiceBoxCategoryProduct();
    	initializeChoiceBoxSizeProduct();
    }

    @FXML
    public void buttonDeleteClient(ActionEvent event) throws IOException{
    	openScreen("delete-client.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("delete-client.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonDeleteEmployee(ActionEvent event) throws IOException{
    	openScreen("delete-employee.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("delete-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonDeletepProduct(ActionEvent event) throws IOException{
    	openScreen("delete-product.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("delete-product.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonDeliveryFood(ActionEvent event) throws IOException{
    	openScreen("domicilios.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("domicilios.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonEnableDisableClient(ActionEvent event) throws IOException{
    	openScreen("disable-client.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("disable-client.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonEnableDisableEmployee(ActionEvent event) throws IOException{
    	openScreen("disable-employee.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("disable-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonEnableDisableProduct(ActionEvent event) throws IOException{
    	openScreen("disable-product.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("disable-product.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonExitMenu(ActionEvent event) throws IOException{
    	openScreen("login.fxml",mainPaneMenu);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("login.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	mainPaneMenu.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonRateServiceClient(ActionEvent event) throws IOException{
    	openScreen("grade-service.fxml",paneToChange);
    	/*
       	FXMLLoader fxml = new FXMLLoader (getClass().getResource("grade-service.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonReserve(ActionEvent event) throws IOException{
    	openScreen("reserva.fxml",paneToChange);    	
    	/*
       	FXMLLoader fxml = new FXMLLoader (getClass().getResource("reserva.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonUpdateClient(ActionEvent event) throws IOException{
    	openScreen("update-client.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("update-client.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonUpdateEmployee(ActionEvent event) throws IOException{
    	openScreen("update-employee.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("update-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void buttonUpdateProduct(ActionEvent event) throws IOException{
    	openScreen("update-product.fxml",paneToChange);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("update-product.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	*/
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
    //Dialog<String> dialog = createDialog();
	//dialog.setTitle("Error al guardar datos");
	//dialog.setContentText("Todos los campos de texto deben ser llenados");
	//dialog.show();   

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
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-cashier-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	mainPaneRegister.getChildren().setAll(root);
    	*/
    }
    
    @FXML
    public void openCreateChefEmployee(ActionEvent event)throws IOException {
    	openScreen("create-chef-employee.fxml",mainPaneRegister);
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-chef-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	mainPaneRegister.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void openCreateDeliveryManEmployee(ActionEvent event)throws IOException {
    	openScreen("create-deliveryMan-employee.fxml",mainPaneRegister);
    	initializeChoiceBoxAmountOrdersDeliveryMan();
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-deliveryMan-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	mainPaneRegister.getChildren().setAll(root);
    	*/
    }

    @FXML
    public void openCreateWaiterEmployee(ActionEvent event) throws IOException{
    	openScreen("create-waiter-employee.fxml",mainPaneRegister);
    	initializeChoiceBoxAmounTablesWaiter();
    	/*
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-waiter-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	mainPaneRegister.getChildren().setAll(root);
    	*/
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
    private TextField txtPasswordCashier;

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
    void createCashierEmployee(ActionEvent event) {

    }

    @FXML
    void noCreateCashierEmployee(ActionEvent event) {

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
    private TextField txtPasswordChef;

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
    void createChefEmployee(ActionEvent event) {

    }

    @FXML
    void noCreateChefEmployee(ActionEvent event) {

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
    private TextField txtPasswordWaiter;

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
    void createWaiterEmployee(ActionEvent event) {

    }

    @FXML
    void noCreateWaiterEmployee(ActionEvent event) {

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
    private TextField txtPasswordDeliveryMan;

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
    void createDeliveryManEmployee(ActionEvent event) {

    }

    @FXML
    void noCreateDeliveryManEmployee(ActionEvent event) {

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
    	deleteProductId.setText(null);
    	deleteProductName.setText(null);
    	deleteProductCategory.setText(null);
    	deleteProductPrice.setText(null);
    	deleteProductAvailability.setText(null);
    	deleteProductSize.setText(null);
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
    
}
