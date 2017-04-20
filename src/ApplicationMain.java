
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
		
		App app = new App();
		FileHandler fileHandler = new FileHandler();
		ApplicationView appView = new ApplicationView();
		ApplicationControl appControl = new ApplicationControl(appView, app, fileHandler);
		appControl.setUpListeners();
		
		Scene scene = new Scene(appView.getRoot(), 900, 900);
		primaryStage.setTitle("Timeline Manager");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
