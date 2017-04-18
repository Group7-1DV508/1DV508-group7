package functions;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {

	private String name;
	private String description;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;

	
	public Event(String eventName, String eventDescription, LocalDateTime start, LocalDateTime end) {
		name = eventName;
		description = eventDescription;
		dateStart = start;
		dateEnd = end;
	}
	
	public Event(String eventName, String eventDescription, LocalDateTime start) {
		name = eventName;
		description = eventDescription;
		dateStart = start;
		
	}
	
	public String getEventName() {
		return name;
	}
	
	public void setEventName(String eventName) {
		name = eventName;
	}
	
	public String getEventDescription() {
		return description;
	}
	
	public void setEventDescription(String eventDescription) {
		description = eventDescription;
	}
	
	public LocalDateTime getEventStart() {
		return dateStart;
	}
	
	public void setEventStart(LocalDateTime start) {
		dateStart = start;
	}
	
	public LocalDateTime getEventEnd() {
		return dateEnd;
	}
	
	public void setEventEnd(LocalDateTime end) {
		dateEnd = end;
	}
	
	
}
