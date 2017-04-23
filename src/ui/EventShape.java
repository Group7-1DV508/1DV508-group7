package ui;

import java.time.LocalDateTime;

import functions.Event;
import functions.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
/**
 * protected class, only classes in package ui got access.
 * creates an circle (EventShape) that holds variables to the duration bar
 * and the Event it represents, also holds variable to the Timeline the 
 * Event belongs to 
 * @author carolinenilsson
 *
 */
class EventShape extends Circle {
	
	private final Event event;
	private final Timeline timeline;
	private final int timelineStart;
	
	private int yearStart;
	private int monthStart;
	private int dayStart;
	private int yearEnd;
	private int monthEnd;
	private int dayEnd;
	
	private final Line bar;
	private int startX;
	private int endX;
	
	/**
	 * Constructor, creates an EventShape for Event without duration 
	 * @param current - the Timeline the Event belongs to 
	 * @param e - Event
	 * @param start - LocalDateTime of the Event start
	 */
	public EventShape(Timeline current, Event e, LocalDateTime start) {
		timeline = current;
		event = e;
		timelineStart = current.getYear(current.getStart());
		yearStart = current.getYear(start);
		monthStart = current.getMonth(start);
		dayStart = current.getDay(start);
		yearEnd = 0;
		monthEnd = 0;
		dayEnd = 0;
		bar = new Line();
		setValueX();
		createEventShape();
	}
	
	/**
	 * Constructor, creates an EventShape and a duration bar for Event with duration
	 * @param current - the Timeline the Event belongs to 
	 * @param e - Event
	 * @param start - LocalDateTime of the Event start
	 * @param end - LocalDateTime of the Event end
	 */
	public EventShape(Timeline current, Event e, LocalDateTime start, LocalDateTime end) {
		timeline = current;
		event = e;
		timelineStart = current.getYear(current.getStart());
		yearStart = current.getYear(start);
		monthStart = current.getMonth(start);
		dayStart = current.getDay(start);
		yearEnd = current.getYear(end);
		monthEnd = current.getMonth(end);
		dayEnd = current.getDay(end);
		bar = new Line();
		setValueX();
		createEventShape();
		createDurationBar();
	}
	
	/**
	 * create a circle shape that is visuals for the Event
	 */
	private void createEventShape() {
		setValueX();
		setRadius(12.5);
		setStroke(Color.BLACK);
		setFill(Color.MEDIUMSEAGREEN);
		setCenterX(startX);
		setCenterY(25);
		setManaged(false);
	}
	/**
	 * updates the x-coordinate of the EventShape
	 */
	private void updateEventShape() {
		setCenterX(startX);
	}
	/**
	 * Creates an duration bar for the Event
	 */
	private void createDurationBar() {
		updateBar();
		bar.setStrokeWidth(5.0);
		bar.setStroke(Color.BLACK);
		bar.setVisible(false);
		bar.setManaged(false);
	}
	
	/**
	 * initializes the x-coordinate and y-coordinate value math explained below:
	 * year (into the timeline) multiplied by 12 
	 * then add month date (ex. 05 for may) subtracted by 1 
	 * (correct amount of months) multiplied by 100 (length of the monthBox)
	 * add (monthBox length divided by 30 (generaly days / month) 100/30 multiplied by days date)
	 * then add 5 multiplied by total number of months (this is due to spacing at 5.0 between months)
	 */
	private void setValueX() {
		int year = yearStart - timelineStart;
		if (yearEnd == 0) {
			startX = (((year) * 12) + (monthStart-1)) *100 + (100/30 * dayStart) + (5 * (12 *year + monthStart));;
		}
		else {
			startX = (((year) * 12) + (monthStart-1)) *100 + (100/30 * dayStart) + (5 * (12 *year + monthStart));;
			endX = (((year) * 12) + (monthEnd-1)) *100 + (100/30 * dayEnd) + (5 * (12 *year + monthEnd));;

		}
		
	}
	/**
	 * Sets new x-corrdiante and y-coordinates for start and end on bar
	 */
	private void updateBar() {
		bar.setStartX(startX);
		bar.setEndX(endX);
		bar.setStartY(0);
		bar.setEndY(0);
	}
	/**
	 * sets visibility, Boolean condition on duration bar
	 * (bar only shows when mouse hover the EventShape) 
	 * @param b Boolean
	 */
	public void setBarVisibility(boolean b) {
		bar.setVisible(b);
	}
	/**
	 * sets new date for non duration Event
	 * @param start
	 */
	public void setDate(LocalDateTime start) {
		yearStart = timeline.getYear(start);
		monthStart = timeline.getMonth(start);
		dayStart = timeline.getDay(start);
		setValueX();
		updateEventShape();
		
	}
	/**
	 * sets new date for duration Event
	 * @param start
	 * @param end
	 */
	public void setDate(LocalDateTime start, LocalDateTime end) {
		yearStart = timeline.getYear(start);
		monthStart = timeline.getMonth(start);
		dayStart = timeline.getDay(start);
		yearEnd = timeline.getYear(end);
		monthEnd = timeline.getMonth(end);
		dayEnd = timeline.getDay(end);
		
		setValueX();
		updateEventShape();
		updateBar();
	}
	/**
	 * Returns the bar of the EventShape
	 * @return Line
	 */
	public Line getBar() {
		return bar;
	}
	/**
	 * return the Event that the EventShape belongs to
	 * @return Event
	 */
	public Event getEvent() {
		return event;
	}

}
