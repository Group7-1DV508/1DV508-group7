package controls;

import java.time.LocalDateTime;

import functions.App;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TimelineControl implements TimelineListener {

	private App currentApp;

	@Override
	public boolean onAddTimeline(String name, LocalDateTime start, LocalDateTime end) {
		if (isCorrectInput(name, start, end)) {
			currentApp.addTimeline(name, start, end);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if while creating a timeline a name has been chosen.
	 *
	 * @param name
	 *            Name of timeline
	 * @return true if name is not a null
	 */
	private boolean isNameCorrect(String name) {

		if (name.length() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in timeline name");
			alert.setHeaderText("Please choose a name for your timeline");
			alert.show();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if while creating a timeline a start date has been chosen.
	 *
	 * @param start
	 *            Date of when timeline starts
	 * @return true if start date is not a null
	 */
	private boolean isStartCorrect(LocalDateTime start) {
		if (start == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in start date");
			alert.setHeaderText("Please choose a start date for your timeline");
			alert.show();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if while creating a timeline an end date has been chosen.
	 *
	 * @param end
	 *            Date of when timeline ends
	 * @return true if end date is not a null
	 */
	private boolean isEndCorrect(LocalDateTime end) {
		if (end == null) {
			System.out.println("Please choose an end date for your timeline");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if all input made by user is correct
	 *
	 * @param name
	 *            Name of timeline
	 * @param start
	 *            Date of when timeline starts
	 * @param end
	 *            Date of when timeline ends
	 * @return
	 */
	private boolean isCorrectInput(String name, LocalDateTime start, LocalDateTime end) {
		if (isNameCorrect(name) && isStartCorrect(start) && isEndCorrect(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets an application for this listener
	 *
	 * @param app
	 */
	public void setApp(App app) {
		currentApp = app;
	}

}
