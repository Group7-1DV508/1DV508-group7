package ui;


import controls.ApplicationListener;
import controls.ChangeListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import functions.Event;
import functions.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class ApplicationView implements ChangeListener {
	
	private EventView eventView;
	private TimelineView timelineView;
	private ApplicationListener appListener;
	
	//contains all parts of the window (Main view)
	private final VBox view = new VBox();
	//contains ComboBox to choose current timeline, add/delete timeline and help button
	private final HBox timelineButtons = new HBox();

	/*
	 * contains all parts within the Current Timeline View
	 * Add/Edit/Delete event buttons, scroll window, current timeline and events visuals 	
	 */
	private final VBox timelineMainBox = new VBox();
	//scroll window for timeline
	private final ScrollPane scrollTimeline = new ScrollPane();
	//buttons for add/edit/delete event
	private final HBox eventButtons = new HBox();
	//comboBox to choose timeline
	private final ComboBox<Timeline> chooseTimeline = new ComboBox<Timeline>();
	//contains all created events for the current timeline
	private final ArrayList<Circle> eventCircles = new ArrayList<Circle>();
	//contains all months/years for the current timeline
	private final GridPane currentTimeline = new GridPane();
	//list of months, used to divide the month names to the month boxes
	private final ArrayList<Text> monthTexts = new ArrayList<Text>();
	//contains one month
	private HBox timelineMonth;
	//contains all events at the correct position 
	private final HBox eventBox = new HBox();
	//shape that represents an event
	private Circle eventShape;
	
	//size of the month boxes
	final int MONTH_BOX_HEIGHT = 50;
	final int MONTH_BOX_LENGTH = 100;
	
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
	 * collects the Timeline Buttons and the Main Timeline Box
	 */
	private VBox root() {
		view.setSpacing(20);
		view.setAlignment(Pos.CENTER);
		view.getChildren().addAll(timelineButtonsBox(), timelineMainBox());
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
	 * collects and return all buttons associated with timeline
	 * @return HBox
	 */
	private HBox timelineButtonsBox() {
		timelineButtons.setAlignment(Pos.CENTER);
		timelineButtons.setSpacing(20.0);
		timelineButtons.getChildren().addAll(chooseTimeline, getAddTimelineButton(),
				getDeleteTimelineButton(), createHelpButton());
		return timelineButtons;
	}
	/**
	 * collects and return all buttons associated with event
	 * @return HBox
	 */
	private HBox eventButtonsBox() {
		eventButtons.setAlignment(Pos.CENTER);
		eventButtons.setSpacing(20.0);
		eventButtons.getChildren().addAll(getAddEventButton(), getEditEventButton(),
				getDeleteEventButton());
		return eventButtons;
	}
	/**
	 * creates a combo box where loaded timelines can be chosen from
	 * also calls method to create the current timeline and add events to it
	 * @param timelines , the timelines available
	 * @param current , the currently open timeline
	 */
	private void chooseTimeline(ArrayList<Timeline> timelines, Timeline current) {
		for (Timeline t : timelines) {
			chooseTimeline.getItems().add(t);
		}
		chooseTimeline.setValue(current);
		currentTimeline(current);
		addEventsToTimeline(current);
		
		chooseTimeline.setOnAction( new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				appListener.onTimelineSelected(chooseTimeline.getValue());	
			}
			
		});
	}
	
	/**
	 * The main timeline box, contains scroll box, and event buttons box
	 */
	private VBox timelineMainBox() {
		timelineMainBox.setSpacing(10.0);
		timelineMainBox.setAlignment(Pos.CENTER);
		timelineMainBox.getChildren().addAll(timelineScrollBox(), eventButtonsBox());
		return timelineMainBox;
	}
	/**
	 * Creates a ScrollPane for the timeline and adds the current 
	 * timeline and the events pane to it
	 */
	private ScrollPane timelineScrollBox() {
		VBox content = new VBox();
		scrollTimeline.setPrefSize(400, 200);
		content.getChildren().addAll(currentTimeline, eventBox);
		scrollTimeline.setContent(content);
		
		return scrollTimeline;
	}
	/**
	 * fetch months for the current timeline and adds them to the timeline pane
	 * @param current , the currently open timeline
	 */
	private void currentTimeline(Timeline current) {
		currentTimeline.getChildren().clear();
		currentTimeline.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		currentTimeline.setHgap(5.0);
		int yearStart = current.getYear(current.getStart());
		int yearEnd = current.getYear(current.getEnd());
		
		for (int year = 0 ; year < (yearEnd-yearStart) ; year++) {
			initializeMonthsText();
			for (int i = 1 ; i <= 12 ; i++) {
				createTimelineMonth();
				timelineMonth.getChildren().add(monthTexts.get(i-1));
				currentTimeline.add(timelineMonth, i + 12 * year, 0);
			}	
		}	
	}
	/**
	 * Creates a month box for the Timeline
	 */
	private void createTimelineMonth() {
		timelineMonth = new HBox();
		timelineMonth.setMaxSize(MONTH_BOX_LENGTH, MONTH_BOX_HEIGHT);
		timelineMonth.setMinSize(MONTH_BOX_LENGTH, MONTH_BOX_HEIGHT);
		timelineMonth.setAlignment(Pos.CENTER);
		timelineMonth.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));
		
	}
	
	/**
	 * Collects event information (start/end) and calls method to create an event
	 * shape with the correct allignment at the timeline
	 * also creates a line to connect the shape to the timeline
	 * @param current , current Timeline
	 */
	private void addEventsToTimeline(Timeline current) {
		ArrayList<Event> events = new ArrayList<Event>();
		eventCircles.clear();
		createEventBox(current);
		int timelineYearStart = current.getYear(current.getStart());
		int yearStart;
		int monthStart;
		int dayStart;
		int yearEnd;
		int monthEnd;
		int dayEnd;
		
		events = current.getEvents();
		for (Event event : events) {
			if (event.getEventEnd() == null) {
				yearStart = current.getYear(event.getEventStart());
				monthStart = current.getMonth(event.getEventStart());
				dayStart = current.getDay(event.getEventStart());
			}
			else {
				yearStart = current.getYear(event.getEventStart());
				monthStart = current.getMonth(event.getEventStart());
				dayStart = current.getDay(event.getEventStart());
				yearEnd = current.getYear(event.getEventEnd());
				monthEnd = current.getMonth(event.getEventEnd());
				dayEnd = current.getDay(event.getEventEnd());
			}
			
		createEventShape(timelineYearStart-yearStart, monthStart , dayStart );
		eventCircles.add(eventShape);
		}
		
		sortEventList(eventCircles); 
		setAllignmentEvents(eventCircles);
		for (Circle circle : eventCircles) {
			Line line = new Line();
			line.setStartX(circle.getCenterX());
			line.setStartY(0);
			line.setEndX(circle.getCenterX());
			line.setEndY(circle.getCenterY()-12.5);
			line.setManaged(false);
			eventBox.getChildren().add(line);
		}
		for (Circle circle : eventCircles) {
			eventBox.getChildren().add(circle);
		}
	}
	
	/**
	 * Creates a pane with the same length as the timeline where
	 * Events are added
	 * @param current
	 */
	private void createEventBox(Timeline current) {
		eventBox.getChildren().clear();
		int start = current.getYear(current.getStart());
		int end = current.getYear(current.getEnd());
		long boxLength = ((end-start) * 12 ) * 100 +(end-start) * 5 * 12 ;
		eventBox.setMaxSize(boxLength, 150);
		eventBox.setMinSize(boxLength, 150);
	}
	/**
	 * create a circle shape that is visuals for the Event
	 * @param year what year in the timeline the Event begins at
	 * @param month month of the Event
	 * @param day day of the Event
	 */
	private void createEventShape(int year, int month, int day) {
		eventShape = new Circle();
		eventShape.setRadius(12.5);
		eventShape.setStroke(Color.BLACK);
		eventShape.setFill(Color.PEACHPUFF);
		long xAllignment = (((year) * 12) + (month-1)) *100 + (100/30 * day) + (5 * (12 *year + month));
		eventShape.setCenterX(xAllignment);
		eventShape.setCenterY(25);
		eventShape.setManaged(false);
	}
	/**
	 * if Event shapes collide event gets a lower alignment
	 * @param events , ArrayList<Circle>
	 */
	private void setAllignmentEvents(ArrayList<Circle> events) {
		boolean previousMoved = false;
		for (int i = events.size()-1 ; i > 0 ; i--) {
			if (events.get(i).getCenterX() - events.get(i-1).getCenterX() >= 25) {
				previousMoved = false;
			}
			else if (!previousMoved) {
				events.get(i).setCenterY(55);
				previousMoved = true;
			}
			else {
				events.get(i).setCenterY(85);
				previousMoved = false;
			}
		}	
	}
	
	/**
	 * compare Alignment of the Circles and sort the ArrayList according to that
	 * @param events ArrayList<Circle>
	 */
	private void sortEventList(ArrayList<Circle> events) {
		Comparator<Circle> compare = new Comparator<Circle>(){

			@Override
			public int compare(Circle o1, Circle o2) {
				return (int) (o1.getCenterX() - o2.getCenterX());
			}
		};
		Collections.sort(events, compare);
	}
	/**
	 * Creates an ArrayList<Text> with the months name
	 * is used to add correct month name to the correct monthBox
	 */
	public void initializeMonthsText() {
		monthTexts.clear();
		
		Text january = new Text("Jan");
		Text february = new Text("Feb");
		Text march = new Text("Mar");
		Text april = new Text("Apr");
		Text may = new Text("May");
		Text june = new Text("Jun");
		Text july = new Text("Jul");
		Text august = new Text("Aug");
		Text september = new Text("Sep");
		Text october = new Text("Oct");
		Text november = new Text("Nov");
		Text december = new Text("Dec");
		
		monthTexts.add(january);
		monthTexts.add(february);
		monthTexts.add(march);
		monthTexts.add(april);
		monthTexts.add(may);
		monthTexts.add(june);
		monthTexts.add(july);
		monthTexts.add(august);
		monthTexts.add(september);
		monthTexts.add(october);
		monthTexts.add(november);
		monthTexts.add(december);
			
	}

	@Override
	public void onChangedTimeline(ArrayList<Timeline> timelines, Timeline current) {
		chooseTimeline(timelines, current);
		
		root();
		
	}

	@Override
	public void onNewTimelineSelected(Timeline current) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEditTimeline(Timeline current) {
		addEventsToTimeline(current);
		
		root();
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
	
	/**
	 * helpmethod while implementing
	 * @param row 
	 * @param column
	 * @param pane
	 * @return Pane
	 */
	private Pane getNodeAtIndex(int row, int column, GridPane pane) {
		ObservableList<Node> children = pane.getChildren();
		
		for (Node n : children) {
			if (pane.getRowIndex(n) == row && pane.getColumnIndex(n) == column) {
				return (Pane)n;
			}
		}
		
		return null;
	}
	
	
	
}
