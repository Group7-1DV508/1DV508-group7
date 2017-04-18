package functions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class App {
	
	ArrayList<Timeline> Timelines = new ArrayList<Timeline>();
	Timeline current;
	
	/**
	 * Create a new timeline and adds it to the timeline list, also update the "current timeline"
	 * variable.
	 * @param name - String name of the timeline
	 * @param start - LocalDate the start of the timeline
	 * @param end - LocalDate the end of the timeline
	 */
	public void addTimeline(String name, LocalDateTime start, LocalDateTime end) {
		current = new Timeline(name, start, end);
		Timelines.add(current);
	}
	
	public void setCurrentTimeline(Timeline t) {
		current = t;
	}
	public Timeline getCurrentTimeline() {
		return current;
	}
	
	
}
