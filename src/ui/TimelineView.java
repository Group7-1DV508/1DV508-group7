package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class TimelineView {

	private Button addTimeline = new Button("Add Timeline");
	private Button deleteTimeline = new Button("Delete Timeline");
	private Button confirmTimeline = new Button("Finish");
	// HBox for "Add Timeline" button
	private HBox addTimelineButton = new HBox();
	// Stage for new window where user inputs information about timeline
	private Stage addTimelineWindow = new Stage();
	private final TextField timelineName = new TextField();
	private DatePicker timelineStart = new DatePicker();
	private DatePicker timelineEnd = new DatePicker();
	Converter converter = new Converter();
	private TimelineListener timelineListener;
	
	private boolean gotFilePath;

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
		timelineStart.setConverter(converter);
		timelineEnd.setPromptText("End Year");
		timelineEnd.setConverter(converter);

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
	 * Opens an alert window of type confirmation, asks user if they really want
	 * to delete selected timeline. If ok is pressed, timeline is deleted and
	 * alert window closes. If cancel is pressed, window closes without deleting
	 * current timeline.
	 * 
	 * @author Indre Kvedaraite
	 *
	 */
	private class DeleteTimelineHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Stage stage = new Stage();
			HBox buttony = new HBox();
			CheckBox checkBox = new CheckBox("Delete file along with timeline.");
			Button timeline = new Button("Delete");
			//Button timelineAndFile = new Button("Delete Timeline and File");
			Button cancel = new Button("Cancel");
			
			if (!gotFilePath) {
				checkBox.setDisable(true);
			}

			timeline.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
					confirmation.setTitle("Deleting Timeline");
					confirmation.setContentText("Are you sure you wish to delete the current timeline?");
					Optional<ButtonType> result = confirmation.showAndWait();
					if (result.get() == ButtonType.OK) {
						if (checkBox.isSelected()) {
							timelineListener.onDeleteFile();
							timelineListener.onDeleteTimeline();
						}
						else {
							timelineListener.onDeleteTimeline();
						}
						confirmation.close();
						stage.close();
					} else {
						confirmation.close();
					}
				}
			});

			cancel.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					stage.close();
				}
			});
			HBox checkbox = new HBox();
			checkbox.getChildren().add(checkBox);
			buttony.getChildren().clear();
			buttony.setAlignment(Pos.CENTER);
			buttony.setSpacing(20.0);
			buttony.getChildren().addAll(timeline, cancel);
			VBox box = new VBox();
			box.setSpacing(20);
			box.setPadding(new Insets(30));
			box.getChildren().addAll(checkbox, buttony);

			Scene scenery = new Scene(box);

			stage.setTitle("Delete Options");
			stage.setScene(scenery);
			stage.show();
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
			LocalDate startDate = timelineStart.getValue();
			LocalDate endDate = timelineEnd.getValue();
		 

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
			
			
	
			LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of( 00,00));
			LocalDateTime end = LocalDateTime.of(endDate, LocalTime.of( 00,00));
			System.out.println("Start: "+start+" End: "+end);

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

	}
	public void setTimelineSaved(boolean b) {
		gotFilePath = b;
	}
	
	private class Converter extends StringConverter<LocalDate> {

		String pattern = "GGyyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		
		@Override
		public String toString(LocalDate date) {
			if (date != null) {
				return formatter.format(date);
			}
			else {
				return "";
			}
		}

		@Override
		public LocalDate fromString(String string) {
			if (string != null && !string.isEmpty()) {
				return LocalDate.parse(string, formatter);
			}
			else {
				return null; 
			}
		}
		
	}

}
