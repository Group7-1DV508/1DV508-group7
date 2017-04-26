
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
		app.addEventToCurrentDuration("jag är Duration", "hi", LocalDateTime.of(2001, 04, 13, 00, 00), LocalDateTime.of(2000, 04, 30, 00, 00));
		app.addEventToCurrentDuration("duration 2", "hi", LocalDateTime.of(2001, 04, 13, 00, 00), LocalDateTime.of(2000, 04, 30, 00, 00));
		app.addEventToCurrent("icke duration", "hi", LocalDateTime.of(2001, 04, 11, 00, 00));
		app.addEventToCurrent("jag vill inte", "hi", LocalDateTime.of(2001, 04, 15, 00, 00));
		app.addEventToCurrent("utan end", "hi", LocalDateTime.of(2001, 04, 05, 00, 00));
		app.addEventToCurrent("Hej hopp inget slut", "hi", LocalDateTime.of(2001, 04, 22, 00, 00));

		//Collect Root from ApplicationView and build
		Scene scene = new Scene(appView.getRoot(), 935, 935);
		primaryStage.setTitle("Timeline Manager");
		primaryStage.setScene(scene);
		primaryStage.show();


	}

}
