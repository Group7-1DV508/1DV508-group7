package src;



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
		
		app.addTimeline("New Timeline", LocalDateTime.of(2001, 01, 01, 00, 00), LocalDateTime.of(2005, 01, 01, 00, 00));
		app.addTimeline("TheTimeline", LocalDateTime.of(2000, 01, 01, 00, 00), LocalDateTime.of(2002, 01, 01, 00, 00));
		app.addEventToCurrentDuration("MyEvent", "hi", LocalDateTime.of(2000, 04, 13, 00, 00), LocalDateTime.of(2000, 04, 30, 00, 00));
		app.addEventToCurrentDuration("MyEvent", "hi", LocalDateTime.of(2000, 12, 15, 00, 00), LocalDateTime.of(2000, 12, 30, 00, 00));
		app.addEventToCurrentDuration("MyEvent", "hi", LocalDateTime.of(2000, 05, 15, 00, 00), LocalDateTime.of(2000, 05, 30, 00, 00));
		app.addTimeline("Timeline", LocalDateTime.of(2001, 01, 01, 00, 00), LocalDateTime.of(2005, 01, 01, 00, 00));
		app.addEventToCurrentDuration("MyEvent", "hi", LocalDateTime.of(2001, 04, 13, 00, 00), LocalDateTime.of(2000, 04, 30, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2001, 04, 20, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2001, 04, 30, 00, 00));
		app.addEventToCurrentDuration("MyEvent", "hi", LocalDateTime.of(2001, 04, 13, 00, 00), LocalDateTime.of(2000, 04, 30, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2001, 04, 11, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2001, 04, 15, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2001, 04, 05, 00, 00));
		app.addEventToCurrent("MyEvent", "hi", LocalDateTime.of(2001, 04, 22, 00, 00));

		//Collect Root from ApplicationView and build
		Scene scene = new Scene(appView.getRoot(), 935, 935);
		primaryStage.setTitle("Timeline Manager");
		primaryStage.setScene(scene);
		primaryStage.show();


	}

}
