package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class AforeGUI {	
	
	public AforeGUI() {
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

    
    
    
    



}
