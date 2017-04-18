package functions;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Timeline {
	
	private ArrayList<Event> events;
	private String name;
	private LocalDateTime start;
	private LocalDateTime end;
	
	
	// Constructor
	public Timeline (String name, LocalDateTime start, LocalDateTime end) {
		events = new ArrayList<Event>();
		this.name = name;
		this.start = start;
		this.end = end;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public LocalDateTime getStart() {
		return start;
	}
	
	public LocalDateTime getEnd() {
		return end;
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	
	// Methods
	public int size() {
		return events.size();
	}
	
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public void addEventDuration(String eventName, String eventDescription, LocalDateTime start, LocalDateTime end) {
		events.add(new Event(eventName, eventDescription, start, end));
	}
	
	public void addEvent(String eventName, String eventDescription, LocalDateTime start) {
		events.add(new Event(eventName, eventDescription, start));
	}

}
