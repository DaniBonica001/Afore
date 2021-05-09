package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;


public class AforeGUI {
	
	
	
	public AforeGUI() {
		boxLogin.setEffect(new DropShadow ());
	}

	@FXML
	private Pane mainPane;

	@FXML
	private TextField txtEmailLogin;

	@FXML
	private TextField txtPasswordLogin;
	
    @FXML
    private Pane boxLogin;
    
	
    @FXML
    void buttonLogin(ActionEvent event) {

    }
    
    @FXML
    void buttonRegister(ActionEvent event) {

    }
    
    



}
