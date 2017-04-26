package ui;

import java.time.LocalDateTime;

import controls.TimelineListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TimelineView {

	private Button addTimeline = new Button("Add Timeline");
	// Main pane that contains "Add Timeline" button
	private GridPane gp = new GridPane();
	private Button confirmTimeline = new Button("Finish");
	// HBox for "Add Timeline" button
	private HBox addTimelineButton = new HBox();
	// HBox for timeline display
	private HBox timelineBox = new HBox();
	// Stage for new window where user inputs information about timeline
	private Stage addTimelineWindow = new Stage();
	private final TextField timelineName = new TextField();
	private final TextField timelineStart = new TextField();
	private final TextField timelineEnd = new TextField();
	private final BooleanProperty theFocus = new SimpleBooleanProperty(true);
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

	/**
	 * Sets EventHandler class for when "Add Timeline" is clicked, puts the
	 * button in HBox
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
		addTimelineWindow.setTitle("ADD TIMELINE WINDOW");
		addTimelineWindow.setScene(new Scene(addTimelineRoot, 600, 300));
		addTimelineWindow.setResizable(false);
		addTimelineWindow.show();
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

		/*
		 * The textfield timelineName was already focused(clicked) once the user
		 * enters the add timeline window. Using BooleanProperty we change the
		 * focus to the root and the promptText from the TextField is displayed.
		 */
		timelineName.focusedProperty().addListener((obsV, oldV, newV) -> {
			if (newV && theFocus.get()) {
				addTimelineRoot.requestFocus();
				theFocus.setValue(false);
			}
		});
		return addTimelineRoot;
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
				addTimelineWindow.close();
			}

		}

	}

}
