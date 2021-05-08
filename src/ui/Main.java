package ui;



import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
	
	private AforeGUI aforeGUI;
	
	
	public Main() {		
		aforeGUI= new AforeGUI();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(aforeGUI);		
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Restaurante");	
		primaryStage.show();
				
	}	


}
