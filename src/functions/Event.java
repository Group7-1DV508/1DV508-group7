package functions;

import java.time.LocalDate;

public class Event {

	private String name;
	private String description;
	private LocalDate dateStart;
	private LocalDate dateEnd;

	
	Event(String eventName, String eventDescription, LocalDate start, LocalDate end) {
		name = eventName;
		description = eventDescription;
		dateStart = start;
		dateEnd = end;
	}
	
	Event(String eventName, String eventDescription, LocalDate start) {
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
	public LocalDate getEventStart() {
		return dateStart;
	}
	public void setEventStart(LocalDate start) {
		dateStart = start;
	}
	public LocalDate getEventEnd() {
		return dateEnd;
	}
	public void setEventEnd(LocalDate end) {
		dateEnd = end;
	}
	
	
}
