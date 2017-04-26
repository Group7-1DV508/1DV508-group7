package ui;


import controls.ApplicationListener;
import controls.ChangeListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import functions.Event;
import functions.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
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
	private final ArrayList<EventShape> eventShapes = new ArrayList<EventShape>();
	//contains all months/years for the current timeline
	private final GridPane currentTimeline = new GridPane();
	//list of months, used to divide the month names to the month boxes
	private final ArrayList<Text> monthTexts = new ArrayList<Text>();
	//contains one month
	private HBox timelineMonth;
	//contains all events at the correct position 
	private final HBox eventBox = new HBox();
	//shape that represents an event
	private EventShape eventShape;
	private final HBox showYearBox = new HBox();
	
	
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
		view.getChildren().clear();
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
		return timelineView.getAddTimelineButton();
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
		return eventView.getAddEventButton();
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
		timelineButtons.getChildren().clear();
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
		eventButtons.getChildren().clear();
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
		
		chooseTimeline.getItems().clear();
		
		for (Timeline t : timelines) {
			chooseTimeline.getItems().add(t);
		}
		chooseTimeline.setValue(current);
		chooseTimeline.setOnAction( new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				if (chooseTimeline.getItems().size() > 0) {
					appListener.onTimelineSelected(chooseTimeline.getValue());
				}
			}
			
		});
	}
	
	/**
	 * The main timeline box, contains scroll box, and event buttons box
	 */
	private VBox timelineMainBox() {
		timelineMainBox.getChildren().clear();
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
		content.getChildren().addAll(showYearBox, currentTimeline, eventBox);
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
		timelineMonth.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
	}
	
	/**
	 * Collects event information (start/end) and calls method to create an event
	 * shape with the correct allignment at the timeline
	 * also creates a line to connect the shape to the timeline
	 * @param current , current Timeline
	 */
	private void addEventsToTimeline(Timeline current) {
		ArrayList<Event> events = new ArrayList<Event>();
		eventShapes.clear();
		createEventBox(current);
		
		
		events = current.getEvents();
		for (Event event : events) {
			if (event.getEventEnd() == null) {
				eventShape = new EventShape(current, event, event.getEventStart());
				eventShape.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("clicked Event");
						
					}
					
				});
			}
			else {
				eventShape = new EventShape(current, event, event.getEventStart(), event.getEventEnd());
				
				eventShape.setOnMouseEntered(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						EventShape source = (EventShape) event.getSource();
						source.getBar().setVisible(true);
					}
					
				});
				eventShape.setOnMouseExited(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						EventShape source = (EventShape) event.getSource();
						source.getBar().setVisible(false);
						
					}
					
				});
				
				eventShape.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						System.out.println("clicked Event");
						
					}
					
				});
				eventBox.getChildren().add(eventShape.getBar());
			}
			
		eventShapes.add(eventShape);
		}
		
		sortEventList(eventShapes); 
		setAllignmentEvents(eventShapes);
		for (Circle circle : eventShapes) {
			Line line = new Line();
			line.setStartX(circle.getCenterX());
			line.setStartY(0);
			line.setEndX(circle.getCenterX());
			line.setEndY(circle.getCenterY()-12.5);
			line.setManaged(false);
			eventBox.getChildren().add(line);
		}
		for (EventShape circle : eventShapes) {
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
		eventBox.setMaxSize(boxLength, 250);
		eventBox.setMinSize(boxLength, 250);
	}

	
	/**
	 * if Event shapes collide event gets a lower alignment
	 * @param events , ArrayList<Circle>
	 */
	private void setAllignmentEvents(ArrayList<EventShape> events) {
		int allignment = 25;
		int firstCircle;
		int secondCircle;
		
		for (int index = 0 ; index < events.size()-1 ; index++ ) {
			firstCircle = (int)events.get(index).getCenterX();
			secondCircle = (int)events.get(1+index).getCenterX();
			if (firstCircle > secondCircle ||( firstCircle-secondCircle <26 && 
					firstCircle - secondCircle >-26)) {
				allignment = allignment+30;
			}
			events.get(index+1).setCenterY(allignment);
		}	
	}
	
	/**
	 * compare Alignment of the Circles and sort the ArrayList according to that
	 * @param events ArrayList<Circle>
	 */
	private void sortEventList(ArrayList<EventShape> events) {
		Comparator<EventShape> compare = new Comparator<EventShape>(){

			@Override
			public int compare(EventShape o1, EventShape o2) {
				return (int) (o1.getCenterX() - o2.getCenterX());
			}
			
		};
		
		Collections.sort(events, compare);
		
		int counter;
		int firstShape;
		int secondShape;
		for (int index = 0 ; index < events.size() -1; index++ ) {
			counter = index;
			firstShape = (int)events.get(index).getCenterX();
			secondShape = (int)events.get(1+index).getCenterX();
			while(firstShape - secondShape < 26 && events.size()-1 > counter && firstShape - secondShape > -26) {
				counter++;
				EventShape temp = events.get(index+1);
				events.remove(index+1);
				events.add(temp);
				secondShape = (int)events.get(1+index).getCenterX();
				
			}
		}
		
	}
	/**
	 * creates box to show years on top of the Timeline
	 * @param current , current timeline
	 */
	private void showYear(Timeline current) {
		showYearBox.getChildren().clear();
		int start = current.getYear(current.getStart());
		int end = current.getYear(current.getEnd());
		long boxLength = ((end-start) * 12 ) * 100 +(end-start) * 5 * 12 ;
		showYearBox.setMaxSize(boxLength, 50);
		showYearBox.setMinSize(boxLength, 50);
		
		for (int i = 0 ; i < end-start ; i++) {
			Text text = new Text(Integer.toString(start+i));
			text.setFont(new Font(25));
			text.setManaged(false);
			text.setX( ( i * 12 * 100) +( i * 5 * 12 ) +5 );
			text.setY(40);
			showYearBox.getChildren().add(text);
		}
		
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
		showYear(current);
		currentTimeline(current);
		addEventsToTimeline(current);
	}

	@Override
	public void onNewTimelineSelected(Timeline current) {
		currentTimeline(current);
		showYear(current);
		addEventsToTimeline(current);
	}

	@Override
	public void onEditTimeline(Timeline current) {
		addEventsToTimeline(current);
		
	}
  
}
