package ui;

import java.time.LocalDateTime;

import controls.TimelineListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TimelineView {

	private Button addTimeline = new Button("Add Timeline");
	// Main pane that contains "Add Timeline" button
	private GridPane gp = new GridPane();
	private Button confirmTimeline = new Button("Save");
	// HBox for "Add Timeline" button
	private HBox addTimelineButton= new HBox();
	// HBox for timeline display
	private HBox timelineBox = new HBox();
	// Stage for new window where user inputs information about timeline
	private Stage addTimelineWindow = new Stage();
	private final TextField timelineName = new TextField("Name");
	private final TextField timelineStart = new TextField("Year");
	private final TextField timelineEnd = new TextField("Year");
	private TimelineListener timelineListener;
	// HBox that contains "Add Event" button
	private HBox eventButton = new HBox();
	
	/**
	 * Sets listener to be able to implement functions for certain UI actions (such as button click)
	 * @param timelineListener
	 */
	public void addListener (TimelineListener timelineListener) {
		this.timelineListener = timelineListener;
	}
	
	/**
	 * HBox with a button, that when pressed opens new window that contains text fields for user to 
	 * input information about timeline (name, start and end dates) and a "Save" button
	 * @return An HBox with a button "Add Timeline"
	 */
	public Button getAddTimelineButton() {
		addTimelineWindow();
		return addTimeline;
	}
	
	/**
	 * Sets EventHandler class for when "Add Timeline" is clicked, puts
	 * the button in HBox
	 */
	private void addTimelineWindow() {
		addTimelineButton.getChildren().add(addTimeline);
		addTimeline.setPadding(new Insets(5));
		addTimeline.setOnAction(new TimelineHandler());
		
	}
	
	/**
	 * Opens new window for user to add new timeline.
	 */
	private void openAddTimeline() {
		GridPane addTimelineRoot = initAddTimeline();
		// Sets what happens when "Save" button is clicked
		confirmTimeline.setOnAction(new ConfirmTimelineHandler());
		addTimelineWindow.setTitle("Add timeline");
		addTimelineWindow.setScene(new Scene(addTimelineRoot, 400, 400));
		addTimelineWindow.show();
	}
	
	/**
	 * Skeleton for add timeline window
	 * @return GridPane containing three text fields and a button.
	 */
	private GridPane initAddTimeline() {
		
		GridPane addTimelineRoot = new GridPane();
		
		HBox nameBox = new HBox(timelineName);
		HBox startBox = new HBox(timelineStart);
		HBox endBox = new HBox(timelineEnd);
		HBox dates = new HBox();
		HBox confirmTimelineButton = new HBox(confirmTimeline);
		
		nameBox.setAlignment(Pos.CENTER);
		startBox.setAlignment(Pos.CENTER);
		endBox.setAlignment(Pos.CENTER);
		confirmTimelineButton.setAlignment(Pos.CENTER);
		
		dates.getChildren().addAll(startBox, endBox);
		addTimelineRoot.add(nameBox, 0, 1);
		addTimelineRoot.add(dates, 0, 2);
		addTimelineRoot.add(confirmTimelineButton, 0, 3);
		
		return addTimelineRoot;
	}
	
	/**
	 * Displays pink rectangle when timeline is added in main 
	 * program window. The rectangle contains "Add event" button.
	 */
	private void displayTimeline () {
		HBox rect = new HBox ();
		rect.setStyle("-fx-background-color: #FF87C3;");
		rect.setMinWidth(500);
		rect.setMinHeight(200);
		rect.getChildren().add(eventButton);
		eventButton.setPadding(new Insets(5));
		rect.setAlignment(Pos.BOTTOM_CENTER);
		timelineBox.getChildren().add(rect);
		
		
	}
	
	/**
	 * Private class of EventHandler that runs a method to open
	 * add timeline window when "Add Timeline" button is pressed
	 * @author Indre Kvedaraite
	 *
	 */
	private class TimelineHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			openAddTimeline();
		}
	}
	
	/**
	 * Private class of EventHandler that runs a method to save
	 * timeline in application if input is correct and close add timeline
	 * window. Is ran when "Save" button is clicked.
	 * @author Indre Kvedaraite
	 *
	 */
	private class ConfirmTimelineHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// Variables to collect input from user
			String name = timelineName.getText();
			String startDate = timelineStart.getText()+"-01-01T03:00:00";
			String endDate = timelineEnd.getText()+"-01-01T03:00:00";
			
			LocalDateTime start = LocalDateTime.parse(startDate);
			LocalDateTime end = LocalDateTime.parse(endDate);
			
			// If timeline was added successfully, closes the window
			if (timelineListener.onAddTimeline(name, start, end)) {
				// Displays timeline
				//displayTimeline();
				addTimelineWindow.close();
			}
			
		}
		
	}
	
}
