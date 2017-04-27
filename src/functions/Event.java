package functions;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Event")
@XmlType(propOrder = {"EventName", "EventDescription", "EventStartDate", "EventEndDate"})
public class Event {

	private String name;
	private String description;
	private LocalDateTime dateStart;
	private LocalDateTime dateEnd;
	private boolean isDuration;

	/**
	 * Constructor, created an Event with duration
	 * @param eventName, name of the Event (String)
	 * @param eventDescription, description of the Event (String)
	 * @param start, start date of the Event (LocalDateTime)
	 * @param end, end date of the Event (LocalDateTime)
	 */
	public Event(String eventName, String eventDescription, LocalDateTime start, LocalDateTime end) {
		name = eventName;
		description = eventDescription;
		dateStart = start;
		dateEnd = end;
		isDuration = true;
	}
	/**
	 * Constructor, created an Event with duration
	 * @param eventName, name of the Event (String)
	 * @param eventDescription, description of the Event (String)
	 * @param start, start date of the Event (LocalDateTime)
	 */
	public Event(String eventName, String eventDescription, LocalDateTime start) {
		name = eventName;
		description = eventDescription;
		dateStart = start;
		dateEnd = null;
		isDuration = false;
		
	}
	
	/**
	 * gets the name of the Event
	 * @return String
	 */
	@XmlElement(name = "EventName")
	public String getEventName() {
		return name;
	}
	
	/**
	 * change the name of the Event
	 * @param eventName (String)
	 */
	public void setEventName(String eventName) {
		name = eventName;
	}
	/**
	 * gets the description of the Event
	 * @return String
	 */
	@XmlElement(name = "EventDescription")
	public String getEventDescription() {
		return description;
	}
	/**
	 * change the description of an Event
	 * @param eventDescription (String)
	 */
	public void setEventDescription(String eventDescription) {
		description = eventDescription;
	}
	
	/**
	 * gets the start date of the Event
	 * @return LocalDateTime
	 */
	public LocalDateTime getEventStart() {
		return dateStart;
	}
	/**
	 * change the start time of an Event
	 * @param start (LocalDateTime)
	 */
	public void setEventStart(LocalDateTime start) {
		dateStart = start;
	}
	
	/**
	 * gets the end date of the Event
	 * @return LocalDateTime
	 */
	
	public LocalDateTime getEventEnd() {
		return dateEnd;
	}
	
	/**
	 * change the end date of an Event
	 * @param end (LocalDateTime)
	 */
	public void setEventEnd(LocalDateTime end) {
		dateEnd = end;
	}
	/**
	 * checks if the current event is an event with duration or not
	 * @return boolean
	 */
	public boolean isDuration() {
		return isDuration;
	}
	/**
	 * returns the start date as a String in order to save the date in a xml file
	 * @return String - the start date
	 */
	@XmlElement(name = "EventStartDate")
	public String getStartXml() {
		return dateStart.toString();
	}
	/**
	 * returns the end date as a String in order to save the date in a xml file 
	 * @return String - the end date 
	 */
	@XmlElement(name = "EventEndDate")
	public String getEndXml() {
		return dateEnd.toString();
	}
	
}
