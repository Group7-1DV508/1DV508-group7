package controls;

import java.time.LocalDateTime;

import functions.App;

public class TimelineControl implements TimelineListener {
	
	private App currentApp;

	@Override
	public boolean onAddTimeline(String name, LocalDateTime start, LocalDateTime end) {
		if (isCorrectInput (name, start, end)) {
			//currentApp.addTimeline(name, start, end);
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isNameCorrect(String name) {
		if (name.length() == 0) {
			System.out.println("Please chose a name for your timeline");
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean isStartCorrect(LocalDateTime start) {
		if (start == null) {
			System.out.println("Please chose a start date for your timeline");
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean isEndCorrect (LocalDateTime end) {
		if (end == null) {
			System.out.println("Please chose an end date for your timeline");
			return false;
		}
		else {
			return true;
		}
	}
	
	private boolean isCorrectInput(String name, LocalDateTime start, LocalDateTime end) {
		if (isNameCorrect(name) && isStartCorrect(start) && isEndCorrect(end)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setApp(App app) {
		currentApp = app;
	}
	
	

}
