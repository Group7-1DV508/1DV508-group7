package controls;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface EventListener {
	
	void onAddEventDuration(String name, String description, LocalDateTime start, LocalDateTime end);	//add event with a duration 
	boolean onAddEvent(String name, String description, LocalDateTime start);							//add event without a duration
	

}
