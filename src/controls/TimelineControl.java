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
	 * @return true if name length is not 0
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
	 * @return true if start date is not temp date
	 */
	private boolean isStartCorrect(LocalDateTime start) {
		LocalDateTime temp = LocalDateTime.parse("0000-01-01T03:00:01");
		if (start.equals(temp)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in timeline start date");
			alert.setHeaderText("Please choose a start date for your timeline");
			alert.setContentText("Start year must be 4 numbers long. (Ex. 0001,0002...2016,2017)");
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
	 * @return true if end date is not temp date
	 */
	private boolean isEndCorrect(LocalDateTime end) {
		LocalDateTime temp = LocalDateTime.parse("0000-01-01T03:00:01");
		if (end.equals(temp)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in timeline end date");
			alert.setHeaderText("Please choose an end date for your timeline.");
			alert.setContentText("End year must be 4 numbers long. (Ex. 0001,0002...2016,2017)");
			alert.show();
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
