package ui;

import java.time.LocalDateTime;
import java.util.Optional;

import controls.TimelineListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TimelineView {

	private Button addTimeline = new Button("Add Timeline");
	private Button deleteTimeline = new Button ("Delete Timeline");
	private Button confirmTimeline = new Button("Finish");
	// HBox for "Add Timeline" button
	private HBox addTimelineButton = new HBox();
	// Stage for new window where user inputs information about timeline
	private Stage addTimelineWindow = new Stage();
	private final TextField timelineName = new TextField();
	private final TextField timelineStart = new TextField();
	private final TextField timelineEnd = new TextField();
	private TimelineListener timelineListener;

	/**
	 * Sets listener to be able to implement functions for certain UI actions
	 * (such as button click)
	 *
	 * @param timelineListener
	 */
	public void addListener(TimelineListener timelineListener) {
		this.timelineListener = timelineListener;
	}

	/**
	 * HBox with a button, that when pressed opens new window that contains text
	 * fields for user to input information about timeline (name, start and end
	 * dates) and a "Save" button
	 *
	 * @return An HBox with a button "Add Timeline"
	 */
	public Button getAddTimelineButton() {
		addTimelineWindow();
		return addTimeline;
	}
	
	public Button getDeleteTimelineButton() {
		deleteTimeline.setPadding(new Insets(5));
		deleteTimeline.setOnAction(new DeleteTimelineHandler());
		return deleteTimeline;
	}

	/**
	 * Sets EventHandler class for when "Add Timeline" is clicked, puts the
	 * button in HBox
	 */
	private void addTimelineWindow() {
		addTimelineButton.getChildren().add(addTimeline);
		addTimelineButton.setPadding(new Insets(5));
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
		addTimelineWindow.setScene(new Scene(addTimelineRoot, 600, 300));
		addTimelineWindow.setResizable(false);
		addTimelineWindow.show();
		addTimelineRoot.requestFocus();
	}

	/**
	 * Skeleton for add timeline window
	 *
	 * @return GridPane containing three text fields and a button.
	 */
	private GridPane initAddTimeline() {

		GridPane addTimelineRoot = new GridPane();

		confirmTimeline.setFont(new Font("Times new Roman", 20));
		timelineName.setPromptText("Timeline Name");
		timelineName.setFont(new Font("Times new Roman", 20));
		timelineStart.setPromptText("Start Year");
		timelineStart.setFont(new Font("Times new Roman", 15));
		timelineEnd.setPromptText("End Year");
		timelineEnd.setFont(new Font("Times new Roman", 15));

		confirmTimeline.setMinSize(100, 30);

		HBox nameBox = new HBox(timelineName);
		HBox startBox = new HBox(timelineStart);
		HBox endBox = new HBox(timelineEnd);
		HBox dates = new HBox();
		HBox confirmTimelineButton = new HBox(confirmTimeline);

		startBox.setPadding(new Insets(0, 25, 0, 0));
		confirmTimelineButton.setPadding(new Insets(10));
		dates.setPadding(new Insets(30));

		confirmTimelineButton.setAlignment(Pos.CENTER);
		dates.setAlignment(Pos.CENTER);
		nameBox.setAlignment(Pos.CENTER);

		dates.getChildren().addAll(startBox, endBox);
		addTimelineRoot.add(nameBox, 0, 1);
		addTimelineRoot.add(dates, 0, 2);
		addTimelineRoot.add(confirmTimelineButton, 0, 3);
		addTimelineRoot.setAlignment(Pos.CENTER);

		return addTimelineRoot;
	}

	/**
	 * Opens an alert window of type confirmation, asks user if they
	 * really want to delete selected timeline. If ok is pressed, timeline
	 * is deleted and alert window closes. If cancel is pressed, window closes without 
	 * deleting current timeline.
	 * @author Indre Kvedaraite
	 *
	 */
	private class DeleteTimelineHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Alert confirmation = new Alert(AlertType.CONFIRMATION);
			confirmation.setTitle("Deleting timeline");
			confirmation.setContentText("Are you sure you want to delete this timeline?");
			Optional<ButtonType> result = confirmation.showAndWait();
			if (result.get() == ButtonType.OK){
			    if (timelineListener.onDeleteTimeline()) {
			    	confirmation.close();
			    }
			} else {
				confirmation.close();
			}
		}
		
	}
  

	/**
	 * Private class of EventHandler that runs a method to open add timeline
	 * window when "Add Timeline" button is pressed
   *
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
	 * Private class of EventHandler that runs a method to save timeline in
	 * application if input is correct and close add timeline window. Is ran
	 * when "Save" button is clicked. Also checks if LocalDateTime format is
	 * correct.
	 *
	 * @author Indre Kvedaraite, Stefanos Bampovits
	 *
	 */
	private class ConfirmTimelineHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// Variables to collect input from user
			String name = timelineName.getText();
			String startDate = timelineStart.getText();
			String endDate = timelineEnd.getText();
			
			// Checks if all fields contain input
			if (name.length() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error in timeline name");
				alert.setHeaderText("Please choose a name for your timeline");
				alert.show();
			}
			
			else if (startDate.length() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error in timeline start date");
				alert.setHeaderText("Please choose a start date for your timeline");
				alert.show();
			}
			
			else if (endDate.length() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error in timeline end date");
				alert.setHeaderText("Please choose an end date for your timeline.");
				alert.show();
				
			}
			
			if (startDate.compareTo(endDate) > 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error in timeline dates");
				alert.setHeaderText("Start date has to be earlier than end date!");
				alert.show();
			}
			
			// Parses temporary values if user input is wrong, to avoid
			// exception
			LocalDateTime start = LocalDateTime.parse("0000-01-01T03:00:01");
			LocalDateTime end = LocalDateTime.parse("0000-01-01T03:00:01");

			// If the startDate is 4 integers long, parse into LocalDateTime
			if (startDate.length() == 4 && startDate.matches("[0-9]+")) {
				start = LocalDateTime.parse(startDate + "-01-01T03:00:00");
			}
			// If the endDate is 4 integers long, parse into LocalDateTime
			if (endDate.length() == 4 && endDate.matches("[0-9]+")) {
				end = LocalDateTime.parse(endDate + "-01-01T03:00:00");
			}

			// If timeline was added successfully, closes the window
			if (timelineListener.onAddTimeline(name, start, end)) {
				timelineName.clear();
				timelineStart.clear();
				timelineEnd.clear();
				addTimelineWindow.close();

				timelineName.clear();
				timelineStart.clear();
				timelineEnd.clear();

			}

		}

	}

}
