import javafx.application.*;

import java.io.File;
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
		app.addEventToCurrentDuration("An Event", "This Event Has Duration", LocalDateTime.of(2001, 04, 13, 00, 00), LocalDateTime.of(2001, 04, 30, 00, 00));
		app.addEventToCurrentDuration("Party Time", ":D :D :D", LocalDateTime.of(2001, 04, 13, 00, 00), LocalDateTime.of(2001, 04, 30, 00, 00));
		app.addEventToCurrent("Event", "We Try Some Different Events For The Save Method", LocalDateTime.of(2001, 04, 11, 00, 00));
		app.addEventToCurrent("Project Day", "We Have Done Awesome Work With Our Project", LocalDateTime.of(2001, 04, 15, 00, 00));
		app.addEventToCurrent("Event Without Duration", ":)", LocalDateTime.of(2001, 04, 05, 00, 00));
		app.addEventToCurrent("Last One", "Last Event Test", LocalDateTime.of(2001, 04, 22, 00, 00));

		fileHandler.saveTimeline(app.getCurrentTimeline(), new File(System.getProperty("user.home")+"/Documents/myTimeline.xml"));


		//Collect Root from ApplicationView and build
		Scene scene = new Scene(appView.getRoot(), 1267, 700);
		primaryStage.setTitle("Timeline Manager");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();


	}

}