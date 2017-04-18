package ui;

import java.time.LocalDateTime;

import controls.TimelineControl;
import controls.TimelineListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TimelineView {

	private Button addTimeline = new Button("Add Timeline");
	private GridPane gp = new GridPane();
	private Button confirmTimeline = new Button("Save");
	private HBox addTimelineButton= new HBox();
	private HBox timelineBox = new HBox();
	private Stage addTimelineWindow = new Stage();
	private final TextField timelineName = new TextField("Name");
	private final TextField timelineStart = new TextField("Year");
	private final TextField timelineEnd = new TextField("Year");
	private TimelineListener timelineListener;
	private boolean timelineAdded = false;
	private HBox eventButton = new HBox();
	
	
	public void addListener (TimelineListener timelineListener) {
		this.timelineListener = timelineListener;
	}
	
	public void setTimelineAdd(boolean b) {
		timelineAdded = b;
	}
	
	public boolean getTimelineAdd() {
		return timelineAdded;
	}
	
	// Return HBox with a button "Add timeline" that, when pressed,
	// will open new window with 3 text fields (name, start and end date)
	// and a "Save" button
	public GridPane getRoot(HBox eventButton) {
		addTimelineWindow();
		gp.add(addTimelineButton, 0, 1);
		gp.add(timelineBox, 0, 2);
		this.eventButton = eventButton;
		
		return gp;
	}
	
	// Sets setOnAction method for button
	private void addTimelineWindow() {
		addTimelineButton.getChildren().add(addTimeline);
		addTimeline.setPadding(new Insets(5));
		addTimeline.setOnAction(new TimelineHandler());
		
	}
	
	// Responsible for opening new window when 
	// add timeline is clicked.
	private void openAddTimeline() {
		GridPane addTimelineRoot = initAddTimeline();
		// Sets what happens when "Save" button is clicked
		confirmTimeline.setOnAction(new ConfirmTimelineHandler());
		addTimelineWindow.setTitle("Add timeline");
		addTimelineWindow.setScene(new Scene(addTimelineRoot, 400, 400));
		addTimelineWindow.show();
	}
	
	// Window that opens after "Add timeline" is clicked.
	// Contains 3 text fields and a button. 
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
	
	private void displayTimeline () {
		HBox rect = new HBox ();
		rect.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
		rect.getChildren().add(eventButton);
		timelineBox.getChildren().add(rect);
		
		
	}
	
	// handle() method for "Add timeline" button
	private class TimelineHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			openAddTimeline();
		}
	}
	
	// handle() method for "Save" button
	private class ConfirmTimelineHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			TimelineControl tc = new TimelineControl();
			String name = timelineName.getText();
			String startDate = timelineStart.getText()+"-01-01T03:00:00";
			String endDate = timelineEnd.getText()+"-01-01T03:00:00";
			
			LocalDateTime start = LocalDateTime.parse(startDate);
			LocalDateTime end = LocalDateTime.parse(endDate);
			
			// If timeline was added successfully, closes the window
			if (tc.onAddTimeline(name, start, end)) {
				setTimelineAdd(true);
				displayTimeline();
				addTimelineWindow.close();
			}
			
		}
		
	}
	
}
