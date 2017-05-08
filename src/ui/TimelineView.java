package ui;

import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
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
	private final DatePicker timelineStart = new DatePicker();
	private final DatePicker timelineEnd = new DatePicker();
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
		timelineEnd.setPromptText("End Year");
		
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

		@Override ////change
		public void handle(ActionEvent arg0) {
			// Variables to collect input from user
			String name = timelineName.getText();
			LocalDate startDate = timelineEnd.getValue();
			LocalDate endDate = timelineEnd.getValue();
			System.out.println(startDate +"-.-.-.-."+endDate );
			
			// Checks if all fields contain input
			if (name.length() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error in timeline name");
				alert.setHeaderText("Please choose a name for your timeline");
				alert.show();
			}
			
			else if (startDate == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error in timeline start date");
				alert.setHeaderText("Please choose a start date for your timeline");
				alert.show();
			}
			
			else if (endDate == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error in timeline end date");
				alert.setHeaderText("Please choose an end date for your timeline.");
				alert.show();
				
			}else{
			
			
			
			// Parses temporary values if user input is wrong, to avoid
			// exception
			//LocalDateTime start = LocalDateTime.parse("0000-01-01T03:00:01");
			//LocalDateTime end = LocalDateTime.parse("0000-01-01T03:00:01");

			// If the startDate is 4 integers long, parse into LocalDateTime
			
			LocalDateTime	start =  createLocalDateTime(timelineStart.getValue().getYear()+"",timelineStart.getValue().getMonthValue()+"",timelineStart.getValue().getDayOfMonth()+"","00");////

			// If the endDate is 4 integers long, parse into LocalDateTime
		
			LocalDateTime	end =  createLocalDateTime(timelineEnd.getValue().getYear()+"",timelineEnd.getValue().getMonthValue()+"",timelineEnd.getValue().getDayOfMonth()+"","00");///
			

			// If timeline was added successfully, closes the window
			if (timelineListener.onAddTimeline(name, start, end)) {
				timelineName.clear();
				timelineStart.getEditor().clear();
				timelineEnd.getEditor().clear();
				addTimelineWindow.close();

				timelineName.clear();
				timelineStart.getEditor().clear();
				timelineEnd.getEditor().clear();

				}
			}
		}
		
		
		 
			private LocalDateTime createLocalDateTime(String j, String k, String l, String hour) {
				String localDate;
				LocalDateTime time = null;

				// if user input year is less than 4 digits, zeroes will be added in
				// front.
				for (int i = 0; i < 4 - j.length(); i++) {
					j = "0" + j;
				}
				// if month is less than 2 digits, zeroes will be added in front
				for (int i = 0; i < 2 - k.length(); i++) {
					k = "0" + k;
				}
				// if day is less than 2 digits, zeroes will be added in front
				for (int i = 0; i < 2 - l.length(); i++) {
					l = "0" + l;
				}
				// creates the String format that is needed to create LocalDateTime
				localDate = j + "-" + k + "-" + l + "T" + hour + ":00";

				try {
					time = LocalDateTime.parse(localDate);
				} catch (Exception e) {
					// if input is wrong, show error (popup window) message.
					Alert fieldError = new Alert(Alert.AlertType.ERROR, "Date input is not correct.");
					fieldError.showAndWait();
					System.out.println(e);
				}
				return time;

			}

	}

}
