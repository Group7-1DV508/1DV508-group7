package controls;

import java.time.LocalDate;

public interface EventListener {
	
	void onAddEventDuration(String name, String description, LocalDate start, LocalDate end);	//add event with a duration 
	void onAddEvent(String name, String description, LocalDate start);							//add event without a duration
	

}
