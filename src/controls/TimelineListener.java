package controls;

import java.time.LocalDate;

public interface TimelineListener {
	
	void onAddTimeline(String name, LocalDate start, LocalDate end);	//add new timeline

}
