import javafx.application.*;

import java.time.LocalDateTime;

import controls.ApplicationControl;
import functions.App;
import io.FileHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

		app.addTimeline("Timeline", LocalDateTime.of(-0011, 01, 01, 00, 00), LocalDateTime.of(0011, 01, 01, 00, 00));

		app.addTimeline("New Timeline", LocalDateTime.of(2001, 01, 01, 00, 00), LocalDateTime.of(2005, 01, 01, 00, 00));
		app.addEventToCurrentDuration("Event Test 1", "This Event Has Duration", LocalDateTime.of(2001, 9, 13, 00, 00), LocalDateTime.of(2001, 10, 30, 00, 00));
		app.addEventToCurrentDuration("Event Test 2", ":D :D :D", LocalDateTime.of(2001, 05, 10, 00, 00), LocalDateTime.of(2001, 05, 15, 00, 00));
		app.addEventToCurrent("Event Test 3", "We Try Some Different Events For The Save Method", LocalDateTime.of(2001, 04, 11, 00, 00));
		app.addEventToCurrent("Event Test 4", "We Have Done Awesome Work With Our Project", LocalDateTime.of(2001, 04, 15, 00, 00));
		app.addEventToCurrent("Event Test 5", ":)", LocalDateTime.of(2001, 12, 31, 00, 00));
		app.addEventToCurrent("Event Test 6", "Last Event Test", LocalDateTime.of(2001, 8, 22, 00, 00));
		app.addEventToCurrentDuration("Event Test 7", "This Event Has Duration", LocalDateTime.of(2001, 2, 13, 00, 00), LocalDateTime.of(2001, 3, 30, 00, 00));
		app.addEventToCurrentDuration("Event Test 8", ":D :D :D", LocalDateTime.of(2001, 05, 10, 00, 00), LocalDateTime.of(2001, 06, 3, 00, 00));
		app.addEventToCurrentDuration("Event Test 9", "This Event Has Duration", LocalDateTime.of(2001, 5, 1, 00, 00), LocalDateTime.of(2001, 5, 20, 00, 00));
		app.addEventToCurrentDuration("Event Test 10", ":D :D :D", LocalDateTime.of(2001, 1, 10, 00, 00), LocalDateTime.of(2001, 02, 15, 00, 00));
		app.addEventToCurrent("Event Test 11", "We Try Some Different Events For The Save Method", LocalDateTime.of(2001, 07, 11, 00, 00));
		app.addEventToCurrent("Event Test 12", "We Have Done Awesome Work With Our Project", LocalDateTime.of(2001, 01, 15, 00, 00));
		app.addEventToCurrent("Event Test 13", ":)", LocalDateTime.of(2001, 05, 05, 00, 00));

		app.addEventToCurrentDuration("Event Test 14", "This Event Has Duration", LocalDateTime.of(2002, 5, 31, 00, 00), LocalDateTime.of(2003, 10, 30, 00, 00));
		app.addEventToCurrentDuration("Event Test 15", ":D :D :D", LocalDateTime.of(2002, 05, 10, 00, 00), LocalDateTime.of(2002, 06, 20, 00, 00));
		app.addEventToCurrent("Event Test 16", "We Try Some Different Events For The Save Method", LocalDateTime.of(2004, 04, 11, 00, 00));
		app.addEventToCurrent("Event Test 17", "We Have Done Awesome Work With Our Project", LocalDateTime.of(2003, 04, 15, 00, 00));
		app.addEventToCurrent("Event Test 18", ":)", LocalDateTime.of(2004, 11, 05, 00, 00));
		app.addEventToCurrent("Event Test 19", "Last Event Test", LocalDateTime.of(2004, 8, 22, 00, 00));
		app.addEventToCurrentDuration("Event Test 20", "This Event Has Duration", LocalDateTime.of(2002, 5, 13, 00, 00), LocalDateTime.of(2002, 5, 30, 00, 00));
		app.addEventToCurrentDuration("Event Test 21", ":D :D :D", LocalDateTime.of(2001, 04, 10, 00, 00), LocalDateTime.of(2004, 06, 3, 00, 00));
		app.addEventToCurrentDuration("Event Test 22", "This Event Has Duration", LocalDateTime.of(2003, 3, 1, 00, 00), LocalDateTime.of(2003, 3, 20, 00, 00));
		app.addEventToCurrentDuration("Event Test 23", ":D :D :D", LocalDateTime.of(2002, 05, 10, 00, 00), LocalDateTime.of(2002, 07, 15, 00, 00));
		app.addEventToCurrent("Event Test 24", "We Try Some Different Events For The Save Method", LocalDateTime.of(2004, 05, 11, 00, 00));
		app.addEventToCurrent("Event Test 25", "We Have Done Awesome Work With Our Project", LocalDateTime.of(2003, 05, 15, 00, 00));
		app.addEventToCurrent("Event Test 26", ":)", LocalDateTime.of(2003, 05, 05, 00, 00));


		//Collect Root from ApplicationView and build
		Scene scene = new Scene(appView.getRoot(), 1100, 600);
		primaryStage.getIcons().add(new Image("/Timeline.png"));
		primaryStage.setTitle("Timeline Manager");
		primaryStage.setScene(scene);
		primaryStage.show();


	}

}