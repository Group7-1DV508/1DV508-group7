package functions;

import java.time.LocalDate;
import java.util.ArrayList;

public class Timeline {
	
	private ArrayList<Event> events;
	private String name;
	private LocalDate start;
	private LocalDate end;
	
	
	// Constructor
	public Timeline (String name, LocalDate start, LocalDate end) {
		this.name = name;
		this.start = start;
		this.end = end;
	}
	
	// Getters
	public String getName() {
		return name;
	}
	
	public LocalDate getStart() {
		return start;
	}
	
	public LocalDate getEnd() {
		return end;
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStart(LocalDate start) {
		this.start = start;
	}
	
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	
	// Methods
	public void addEventDuration(String eventName, String eventDescription, LocalDate start, LocalDate end) {
		events.add(new Event(eventName, eventDescription, start, end));
	}
	
	public void addEvent(String eventName, String eventDescription, LocalDate start) {
		events.add(new Event(eventName, eventDescription, start));
	}

}
