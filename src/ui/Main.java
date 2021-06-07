package ui;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Restaurant;
import thread.ThreadInsertionSortEmployeeByName;
import thread.ThreadInsertionSortProductByPrice;


public class Main extends Application{
	
	private AforeGUI aforeGUI;
	private Restaurant restaurant;
	
	
	public Main() {	
		restaurant = new Restaurant();
		aforeGUI= new AforeGUI(restaurant);
	}

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {		
		ThreadInsertionSortProductByPrice thread1 = new ThreadInsertionSortProductByPrice(restaurant);
		ThreadInsertionSortEmployeeByName thread2 = new ThreadInsertionSortEmployeeByName(restaurant);
		thread1.start();
		thread2.start();
		
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(aforeGUI);		
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Restaurante");	
		primaryStage.show();
				
	}	


}
