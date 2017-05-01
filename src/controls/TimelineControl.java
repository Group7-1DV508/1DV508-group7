package controls;

import java.time.LocalDateTime;

import functions.App;

public class TimelineControl implements TimelineListener {

	private App currentApp;

	@Override
	public boolean onAddTimeline(String name, LocalDateTime start, LocalDateTime end) {
		if (isCorrectInput(name, start, end) && 
				(Integer.parseInt(start.toString().substring(0, 4)) <= Integer.parseInt(end.toString().substring(0, 4)))) {
			// If start and end dates are same, increase end date by one year, since
			// this was an intention by user anyway
			if (Integer.parseInt(start.toString().substring(0, 4)) == Integer.parseInt(end.toString().substring(0, 4))) {
				end = end.plusYears(1);
				currentApp.addTimeline(name, start, end);
			}
			else {
				currentApp.addTimeline(name, start, end);
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean onDeleteTimeline() {
		if (currentApp.getTimelines().contains(currentApp.getCurrentTimeline())) {	
			currentApp.removeTimeline(currentApp.getCurrentTimeline());
			return true;
		}
		else {
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
		if (start.equals(temp) || start == null) {
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
		if (end.equals(temp) || end == null) {
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
