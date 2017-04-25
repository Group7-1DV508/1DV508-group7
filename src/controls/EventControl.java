package controls;

import java.time.LocalDateTime;

import functions.App;
import functions.Event;

public class EventControl implements EventListener {

	private App currentApp;
	private Event currentEvent;

	@Override
	public boolean onAddEventDuration(String name, String description, LocalDateTime start, LocalDateTime end) {
		if (isCorrectInputDuration(name, description, start, end)) {
			currentApp.addEventToCurrentDuration(name, description, start, end);
			return true;
		}
		return false;
	}

	@Override
	public boolean onAddEvent(String name, String description, LocalDateTime start) {
		if (isCorrectInput(name, description, start)) {
			currentApp.addEventToCurrent(name, description, start);
			return true;
		}
		return false;
	}

	@Override
	public boolean onEditEventDuration(String name, String description, LocalDateTime start, LocalDateTime end) {
		if (isCorrectInputDuration(name, description, start, end) || name != currentEvent.getEventName()
				|| description != currentEvent.getEventDescription() || start != currentEvent.getEventStart()
				|| end != currentEvent.getEventEnd()) {
			currentEvent.setEventName(name);
			currentEvent.setEventDescription(description);
			currentEvent.setEventStart(start);
			currentEvent.setEventEnd(end);
			return true;
		}
		return false;
	}

	@Override
	public boolean onEditEvent(String name, String description, LocalDateTime start) {
		if (isCorrectInput(name, description, start) || name != currentEvent.getEventName()
				|| description != currentEvent.getEventDescription() || start != currentEvent.getEventStart()) {
			currentEvent.setEventName(name);
			currentEvent.setEventDescription(description);
			currentEvent.setEventStart(start);
			return true;
		}
		return false;
	}
	
	public void setCurrentEvent (Event e) {
		currentEvent = e;
	}
	
	public Event getCurrentEvent (Event e) {
		return currentEvent;
	}
	/**
	 * Set the variable currentApp to the App created by the ApplicationControl
	 * 
	 * @param app,
	 *            the currently used App
	 */
	public void setApp(App app) {
		currentApp = app;
	}

	/**
	 * validate if a name is chosen for the event that is being created
	 * 
	 * @param name,
	 *            entered by the user
	 * @return boolean, true if name is valid otherwise false
	 */
	private boolean isNameCorrect(String name) {
		if (name.length() == 0) {
			System.out.println("Please chose a name for your timeline");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * validate that a description is added to the event being created
	 * 
	 * @param description,
	 *            entered by the user
	 * @return boolean, true if description is valid otherwise false
	 */
	private boolean isDescriptionCorrect(String description) {
		if (description == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * validate that the LocalDateTime has been initialized (that it´s not
	 * null)
	 * 
	 * @param start,
	 *            date added by the user
	 * @return boolean, true if start date is valid otherwise false
	 */
	private boolean isStartCorrect(LocalDateTime start) {
		if (start == null) {
			System.out.println("Please chose a start date for your timeline");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * validate that the LocalDateTime has been initialized (that it´s not
	 * null)
	 * 
	 * @param end,
	 *            date added by the user
	 * @return boolean, true if end date is valid otherwise false
	 */
	private boolean isEndCorrect(LocalDateTime end) {
		if (end == null) {
			System.out.println("Please chose an end date for your timeline");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * combines isCorrect methods to validate all input at once.
	 * 
	 * @param name
	 *            String added by the user
	 * @param description
	 *            String added by the user
	 * @param start
	 *            LocalDateTime added by the user
	 * @return boolean, true if all inputs are valid otherwise false
	 */
	private boolean isCorrectInput(String name, String description, LocalDateTime start) {
		if (isNameCorrect(name) && isStartCorrect(start) && isDescriptionCorrect(description)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * combines isCorrect methods to validate all input at once.
	 * 
	 * @param name
	 *            String added by the user
	 * @param description
	 *            String added by the user
	 * @param start
	 *            LocalDateTime added by the user
	 * @param end
	 *            LocalDateTime added by the user
	 * @return boolean, true if all inputs are valid otherwise false
	 */
	private boolean isCorrectInputDuration(String name, String description, LocalDateTime start, LocalDateTime end) {
		if (isNameCorrect(name) && isStartCorrect(start) && isEndCorrect(end) && isDescriptionCorrect(description)) {
			return true;
		} else {
			return false;
		}
	}

}
