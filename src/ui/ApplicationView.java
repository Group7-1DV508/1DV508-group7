package ui;


import controls.ApplicationListener;
import controls.ChangeListener;

import java.awt.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import functions.Event;
import functions.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ApplicationView implements ChangeListener {
	
	private EventView eventView;
	private TimelineView timelineView;
	private ApplicationListener appListener;
	private GridPane view;
	private GridPane timelineBox;
	
	/**
	 * Constructor, creates and initialize EventView and TimelineView
	 */
	public ApplicationView() {
		eventView = new EventView();
		timelineView = new TimelineView();
	}
	
	/**
	 * Update the ApplicationListener variable with the ApplicationListener given as input
	 * @param appList , (ApplicationListener)
	 */
	public void addListener(ApplicationListener appList) {
		appListener = appList;
	}
	
	/**
	 * Returns the EventView currently used
	 * @return EventView
	 */
	public EventView getEventView() {
		return eventView;
	}
	
	/**
	 * Returns the TimelineView currently used
	 * @return TimelineView
	 */
	public TimelineView getTimelineView() {
		return timelineView;
	}

	/**
	 * Returns the root of the Application Window
	 * @return GridPane
	 */
	public VBox getRoot()  {
		
		return root();
	}
	
	/**
	 * Creates the Root for the Application Window
	 */
	private VBox root() {
		VBox view = new VBox();
		view.setSpacing(20);
		view.setAlignment(Pos.CENTER);
		view.getChildren().addAll(timelineButtonsBox(), timelineBox());
		return view;
	}
	/**
	 * Return "Add Timeline" Button
	 * @return Button
	 */
	private Button getAddTimelineButton() {
		return new Button("Add Timeline");
	}
	/**
	 * Returns the "Delete Timeline" Button
	 * @return Button
	 */
	private Button getDeleteTimelineButton() {
		return new Button("Delete Timeline");
	}
	/**
	 * Returns the "Add Event" Button
	 * @return Button
	 */
	private Button getAddEventButton() {
		return new Button("Add Event");
	}
	/**
	 * Returns the "Edit Event" Button
	 * @return Button
	 */
	private Button getEditEventButton() {
		return new Button("Edit Event");
	}
	/**
	 * Returns the "Delete Event" Button
	 * @return Button
	 */
	private Button getDeleteEventButton() {
		return new Button("Delete Event");
	}
	/**
	 * Creates the Help Button
	 */
	private Button createHelpButton() {
		return new Button("Help");
	}
	/**
	 * creates a combo box where loaded timelines can be chosen from
	 * @return
	 */
	private ComboBox createChoseTimeline() {
		return new ComboBox();
	}
	/**
	 * puts the timeline view together
	 */
	private VBox timelineBox() {
		VBox timelineView = new VBox();
		timelineView.setSpacing(10.0);
		timelineView.setAlignment(Pos.CENTER);
		timelineView.getChildren().addAll(timelineScrollBox(), eventButtonsBox());
		return timelineView;
	}
	/**
	 * Creates a ScrollPane for the timeline
	 */
	private ScrollPane timelineScrollBox() {
		ScrollPane scrollTimeline = new ScrollPane();
		scrollTimeline.setPrefSize(400, 200);
		return scrollTimeline;
	}
	/**
	 * Creates the current Timeline
	 */
	private void currentTimeline() {
		
	}
	/**
	 * puts all buttons associated with timeline together in a HBox
	 * @return HBox
	 */
	private HBox timelineButtonsBox() {
		HBox timelineButtons = new HBox();
		timelineButtons.setAlignment(Pos.CENTER);
		timelineButtons.setSpacing(20.0);
		timelineButtons.getChildren().addAll(createChoseTimeline(), getAddTimelineButton(),
				getDeleteTimelineButton(), createHelpButton());
		return timelineButtons;
	}
	/**
	 * puts all buttons associated with event together in a HBox
	 * @return HBox
	 */
	private HBox eventButtonsBox() {
		HBox eventButtons = new HBox();
		eventButtons.setAlignment(Pos.CENTER);
		eventButtons.setSpacing(20.0);
		eventButtons.getChildren().addAll(getAddEventButton(), getEditEventButton(),
				getDeleteEventButton());
		return eventButtons;
	}

	@Override
	public void onChangedTimeline(ArrayList<Timeline> timelines, Timeline current) {
		Text info = new Text("You have created a Timeline:");
		Text name = new Text("Name: "+current.getName());
		Text start = new Text("Start Year: "+getYear(current.getStart().toString()));
		Text end = new Text("End Year: "+getYear(current.getEnd().toString()));
		
		timelineBox.add(info, 0, 1);
		timelineBox.add(name, 0, 2);
		timelineBox.add(start, 0, 3);
		timelineBox.add(end, 0, 4);
		timelineBox.add(eventView.getRoot(), 0, 8);
		view.add(timelineBox, 0, 3);
		
		
	}

	@Override
	public void onNewTimelineSelected(Timeline current) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEditTimeline(Timeline current) {
		ArrayList<Event> events = current.getEvents();
		VBox eventBox = new VBox();
		
		for (Event e : events) {
			Text name = new Text(e.getEventName());
			Text description = new Text(e.getEventDescription());
			Text start = new Text(getDateTime(e.getEventStart().toString()));
			eventBox.getChildren().addAll(name, description, start);
		}
		timelineBox.add(eventBox, 5, 1);
		
	}
	/**
	 * takes a LocalDateTime as a String as input and recieves the year
	 * @param str LocalDateTime in String format
	 * @return String 
	 */
	public String getYear(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append(str.charAt(0));
		sb.append(str.charAt(1));
		sb.append(str.charAt(2));
		sb.append(str.charAt(3));
		return sb.toString();
	}
	
	/**
	 * takes a LocalDateTime as a String as input and returns as Date: ... Time: ..
	 * @param str LocalDateTime in String format
	 * @return String
	 */
	public String getDateTime(String str) {
		String temp = "Date: "+str.replace("T", " Time: ");
		return temp;
	}
	
}
