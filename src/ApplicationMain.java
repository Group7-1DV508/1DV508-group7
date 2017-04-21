
import javafx.application.*;

import java.time.LocalDateTime;

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
		//Create a App, FileHandler, ApplicationView and ApplicationControl
		App app = new App();
		FileHandler fileHandler = new FileHandler();
		ApplicationView appView = new ApplicationView();
		ApplicationControl appControl = new ApplicationControl(appView, app, fileHandler);
		
		appControl.setUpListeners();
		
		app.addTimeline("TheTimeline", LocalDateTime.of(2000, 01, 01, 00, 00), LocalDateTime.of(2001, 01, 01, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2000, 04, 13, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2000, 04, 30, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2000, 04, 15, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2000, 12, 05, 00, 00));

		
		//Collect Root from ApplicationView and build
		Scene scene = new Scene(appView.getRoot(), 900, 900);
		primaryStage.setTitle("Timeline Manager");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}
