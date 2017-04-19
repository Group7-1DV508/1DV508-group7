package functions;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controls.ChangeListener;

public class App {
	
	ChangeListener changeListener;
	private ArrayList<Timeline> timelines;
	private Timeline current;
	
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
		current = new Timeline(name, start, end);
		timelines.add(current);
		changeListener.onChangedTimeline(timelines, current);
	}
	
	public void addEventToCurrent(String name, String description, LocalDateTime start) {
		current.addEvent(name, description, start);
		changeListener.onEditTimeline(current);
	}
	
	public void addListener(ChangeListener cl) {
		changeListener = cl;
	}
	
	public void setCurrentTimeline(Timeline t) {
		current = t;
		changeListener.onNewTimelineSelected(current);
	}
	public Timeline getCurrentTimeline() {
		return current;
	}

	public ArrayList<Timeline> getTimelines(){
		return timelines;
	}
	
}
