package controls;

import java.time.LocalDateTime;

public interface TimelineListener {
	
	boolean onAddTimeline(String name, LocalDateTime start, LocalDateTime end);

}
