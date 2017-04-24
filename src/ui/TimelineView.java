package ui;

import java.time.LocalDateTime;

import controls.TimelineListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	private final TextField timelineName = new TextField("Timeline Name");
	private final TextField timelineStart = new TextField("Start Date");
	private final TextField timelineEnd = new TextField("End Date");
	private TimelineListener timelineListener;
	// HBox that contains "Add Event" button
	private HBox eventButton = new HBox();

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
	public GridPane getRoot() {
		addTimelineWindow();
		gp.add(addTimelineButton, 0, 1);
		gp.add(timelineBox, 0, 2);
		addTimelineButton.setPadding(new Insets(10));
		return gp;
	}

	public Button getAddTimelineButton(){

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
		addTimelineWindow.setTitle("Add timeline");
		addTimelineWindow.setScene(new Scene(addTimelineRoot, 600, 300));
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
		timelineName.setFont(new Font("Times new Roman", 20));
		timelineStart.setFont(new Font("Times new Roman", 15));
		timelineEnd.setFont(new Font("Times new Roman", 15));

		confirmTimeline.setMinSize(100, 30);

		HBox nameBox = new HBox(timelineName);
		HBox startBox = new HBox(timelineStart);
		HBox endBox = new HBox(timelineEnd);
		HBox dates = new HBox();
		HBox confirmTimelineButton = new HBox(confirmTimeline);

		startBox.setTranslateY(75);
		startBox.setTranslateX(-10);

		endBox.setTranslateY(75);
		endBox.setTranslateX(10);

		confirmTimelineButton.setTranslateY(100);
		nameBox.setTranslateY(50);

		nameBox.setAlignment(Pos.TOP_CENTER);
		startBox.setAlignment(Pos.TOP_CENTER);
		endBox.setAlignment(Pos.TOP_CENTER);
		confirmTimelineButton.setAlignment(Pos.TOP_CENTER);

		dates.getChildren().addAll(startBox, endBox);
		addTimelineRoot.add(nameBox, 0, 1);
		addTimelineRoot.add(dates, 0, 2);
		addTimelineRoot.add(confirmTimelineButton, 0, 3);
		addTimelineRoot.setAlignment(Pos.TOP_CENTER);

		return addTimelineRoot;
	}

	/**
	 * Displays a draft of how the timeline should look when it's added. The
	 * timeline contains "Add event" button.
	 */
	private void displayTimeline() {


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
	 * when "Save" button is clicked.
	 *
	 * @author Indre Kvedaraite
	 *
	 */
	private class ConfirmTimelineHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			// Variables to collect input from user
			String name = timelineName.getText();
			String startDate = timelineStart.getText() + "-01-01T03:00:00";
			String endDate = timelineEnd.getText() + "-01-01T03:00:00";

			LocalDateTime start = LocalDateTime.parse(startDate);
			LocalDateTime end = LocalDateTime.parse(endDate);

			// If timeline was added successfully, closes the window
			if (timelineListener.onAddTimeline(name, start, end)) {
				// Displays timeline
				displayTimeline();
				addTimelineWindow.close();
			}

		}

	}

}
