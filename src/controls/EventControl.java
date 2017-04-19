package controls;

import java.time.LocalDateTime;

import functions.App;

public class EventControl implements EventListener {
	
	private App currentApp;

	@Override
	public void onAddEventDuration(String name, String description, LocalDateTime start, LocalDateTime end) {
		//if (isCorrectInput(name, description, start, end)) {
			//currentApp.getCurrentTimeline().addEventDuration(name, description, start, end);
			//return true;
		//}
		//return true;
		
		
	}

	@Override
	public boolean onAddEvent(String name, String description, LocalDateTime start) {
		if (isCorrectInput(name, description, start)) {
			currentApp.addEventToCurrent(name, description, start);
			return true;
		}
		return false;
	}
	
	public void setApp(App app) {
		currentApp = app;
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
	
	private boolean isDescriptionCorrect(String description) {
		if (description == null) {
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
	
	private boolean isCorrectInput(String name, String description, LocalDateTime start) {
		if (isNameCorrect(name) && isStartCorrect(start) && isDescriptionCorrect(description)) {
			return true;
		}
		else {
			return false;
		}
	}
	

		

}
