
import javafx.application.*;
import controls.ApplicationControl;
import functions.App;
import io.FileHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ui.ApplicationView;

public class ApplicationMain extends Application {
	
	public static void main(String[] args) {
		
		
		launch(args);
		

	}

	public void start(Stage primaryStage) throws Exception {
		
		ApplicationView appView = new ApplicationView();
		App app = new App();
		FileHandler fileHandler = new FileHandler();
		ApplicationControl appControl = new ApplicationControl(appView, app, fileHandler);
		
		Scene scene = new Scene(appView.getRoot(), 500, 500);
		primaryStage.setTitle("Timeline- Manager");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
