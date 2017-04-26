package functions;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controls.ChangeListener;

public class App {
	
	ChangeListener changeListener;
	private ArrayList<Timeline> timelines;
	private Timeline current;
	
	/**
	 * Constructor, initializes and ArrayList<Timeline>
	 */
	public App() {
		timelines = new ArrayList<Timeline>();
	}
	
	/**
	 * Create a new timeline and adds it to the timeline list, also update the "current timeline"
	 * variable.
	 * @param name - String name of the timeline
	 * @param start - LocalDate the start of the timeline
	 * @param end - LocalDate the end of the timeline
	 */
	public void addTimeline(String name, LocalDateTime start, LocalDateTime end) {
		setCurrentTimeline(new Timeline(name, start, end));
		timelines.add(current);
		changeListener.onChangedTimeline(timelines, current);
	}
	
	/**
	 * Adds an Event to current timeline by calling the addEvent method in Timeline
	 * @param name, event name
	 * @param description , event description
	 * @param start , event start date
	 */
	public void addEventToCurrent(String name, String description, LocalDateTime start) {
		current.addEvent(name, description, start);
		changeListener.onEditTimeline(current);
	}
	
	/**
	 * Adds an Event with duration to current timeline by calling the addEvent method in Timeline
	 * @param name, event name
	 * @param description , event description
	 * @param start , event start date
	 * @param end , event end date
	 */
	public void addEventToCurrentDuration(String name, String description, LocalDateTime start, LocalDateTime end) {
		current.addEventDuration(name, description, start, end);
		changeListener.onEditTimeline(current);
	}
	
	/**
	 * Update the ChangeListener variable with the ChangeListener given as input
	 * @param cl , (ChangeListener)
	 */
	public void addListener(ChangeListener cl) {
		changeListener = cl;
	}
	
	/**
	 * update the variable current with a new current Timeline
	 * @param t , the Timeline used at the moment
	 */
	public void setCurrentTimeline(Timeline t) {
		current = t;
		changeListener.onNewTimelineSelected(current);
	}
	
	/**
	 * returns the currently open timeline
	 * @return Timeline
	 */
	public Timeline getCurrentTimeline() {
		return current;
	}

	/**
	 * returns a list of all open timelines in the App
	 * @return ArrayList<Timeline>
	 */
	public ArrayList<Timeline> getTimelines(){
		return timelines;
	}
	
}
