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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Restaurant;


public class AforeGUI {	
	
	//Relations
	private Restaurant restaurant;
	
	public AforeGUI(Restaurant rest) {
		restaurant = rest;
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
    	FXMLLoader menufxml = new FXMLLoader (getClass().getResource("menu.fxml"));
    	menufxml.setController(this);
    	Parent root = menufxml.load();
    	mainPaneLogin.getChildren().setAll(root);
    	
    }
    
    @FXML
    void buttonRegister(ActionEvent event) {

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
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-client.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

 
    @FXML
    public void createCashierEmployee(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-cashier-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void createChefEmployee(ActionEvent event)throws IOException {
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-chef-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void createDeliveryManEmployee(ActionEvent event)throws IOException {
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-deliveryMan-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void createWaiterEmployee(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-waiter-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonCreateProduct(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("create-product.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    	initializeChoiceBoxCategoryProduct();
    	initializeChoiceBoxSizeProduct();
    }

    @FXML
    public void buttonDeleteClient(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("delete-client.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonDeleteEmployee(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("delete-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonDeletepProduct(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("delete-product.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonDeliveryFood(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("domicilios.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonEnableDisableClient(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("disable-client.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonEnableDisableEmployee(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("disable-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonEnableDisableProduct(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("disable-product.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonExitMenu(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("login.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	mainPaneMenu.getChildren().setAll(root);
    }

    @FXML
    public void buttonRateServiceClient(ActionEvent event) throws IOException{
       	FXMLLoader fxml = new FXMLLoader (getClass().getResource("grade-service.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonReserve(ActionEvent event) throws IOException{
       	FXMLLoader fxml = new FXMLLoader (getClass().getResource("reserva.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonUpdateClient(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("update-client.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonUpdateEmployee(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("update-employee.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
    }

    @FXML
    public void buttonUpdateProduct(ActionEvent event) throws IOException{
    	FXMLLoader fxml = new FXMLLoader (getClass().getResource("update-product.fxml"));
    	fxml.setController(this);
    	Parent root = fxml.load();
    	paneToChange.getChildren().setAll(root);
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
    
    
    
    



}
