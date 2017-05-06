package controls;

import java.time.LocalDateTime;

import functions.App;
import functions.Event;
<<<<<<< HEAD
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
=======
>>>>>>> refs/remotes/origin/master

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
		setCurrentEvent();
		if (isCorrectInputDuration(name, description, start, end)) {
			currentEvent.setEventName(name);
			currentEvent.setEventDescription(description);
			currentEvent.setEventStart(start);
			currentEvent.setEventEnd(end);
			currentApp.eventEdited();
			return true;
		}
		return false;
	}

	@Override
	public boolean onEditEvent(String name, String description, LocalDateTime start) {
		setCurrentEvent();
		if (isCorrectInput(name, description, start)) {
			currentEvent.setEventName(name);
			currentEvent.setEventDescription(description);
			currentEvent.setEventStart(start);
			currentApp.eventEdited();
			return true;
		}
		return false;
	}
	
	@Override
<<<<<<< HEAD
	public boolean onDeleteEvent(Event event) {
		setCurrentEvent();
		if (currentApp.getCurrentTimeline().getEvents().contains(event)) {
			currentApp.getCurrentTimeline().deleteEvent(currentEvent);
=======
	public boolean onDeleteEvent() {
		setCurrentEvent();
		if (currentApp.getCurrentTimeline().getEvents().contains(currentEvent)) {
			currentApp.removeEvent();
>>>>>>> refs/remotes/origin/master
			return true;
		}
		else {
			return false;
		}
	}
	
<<<<<<< HEAD
=======
	public boolean isStartCorrectTimeline (LocalDateTime start) {
		return currentApp.getCurrentTimeline().getStart().getYear() <= start.getYear();
	}
	
	public boolean isEndCorrectTimeline (LocalDateTime end) {
		return currentApp.getCurrentTimeline().getEnd().getYear() >= end.getYear();
	}
	 /**
	  * Checks if start date is not later than end date
	  * @param start
	  * @param end
	  * @return true if dates are correct
	  */
	private boolean areDatesCorrect (LocalDateTime start, LocalDateTime end) {
		return start.compareTo(end) < 0;
	}
	
>>>>>>> refs/remotes/origin/master
	public void setCurrentEvent() {
		currentEvent = currentApp.getCurrentEvent();
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
<<<<<<< HEAD
	 * validate that the LocalDateTime has been initialized (that it´s not
=======
	 * validate that the LocalDateTime has been initialized (that itÂ´s not
>>>>>>> refs/remotes/origin/master
	 * null)
	 * 
	 * @param start,
	 *            date added by the user
	 * @return boolean, true if start date is valid otherwise false
	 */
	private boolean isStartCorrect(LocalDateTime start) {
<<<<<<< HEAD
		LocalDateTime temp = LocalDateTime.parse("0000-01-01T03:00:01");
		if (start.equals(temp)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in timeline start date");
			alert.setHeaderText("Please choose a start date for your timeline");
			alert.setContentText("Start year must be 4 numbers long. (Ex. 0001,0002...2016,2017)");
			alert.show();
=======
		if (start == null || !isStartCorrectTimeline(start)) {
>>>>>>> refs/remotes/origin/master
			return false;
		} else {
			return true;
		}
	}

	/**
<<<<<<< HEAD
	 * validate that the LocalDateTime has been initialized (that it´s not
=======
	 * validate that the LocalDateTime has been initialized (that itÂ´s not
>>>>>>> refs/remotes/origin/master
	 * null)
	 * 
	 * @param end,
	 *            date added by the user
	 * @return boolean, true if end date is valid otherwise false
	 */
	private boolean isEndCorrect(LocalDateTime end) {
<<<<<<< HEAD
		LocalDateTime temp = LocalDateTime.parse("0000-01-01T03:00:01");
		if (end.equals(temp)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in timeline end date");
			alert.setHeaderText("Please choose an end date for your timeline.");
			alert.setContentText("End year must be 4 numbers long. (Ex. 0001,0002...2016,2017)");
			alert.show();
=======
		if (end == null || !isEndCorrectTimeline(end)) {
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
		if (isNameCorrect(name) && isStartCorrect(start) && isEndCorrect(end) && isDescriptionCorrect(description)) {
=======
		if (isNameCorrect(name) && isStartCorrect(start) && isEndCorrect(end) && isDescriptionCorrect(description) && areDatesCorrect(start, end)) {
>>>>>>> refs/remotes/origin/master
			return true;
		} else {
			return false;
		}
	}

}