package controls;

import java.time.LocalDateTime;

<<<<<<< HEAD
import functions.Timeline;
=======
>>>>>>> refs/remotes/origin/master

public interface TimelineListener {
	
	/**
	 * Handles adding of a timeline
	 * @param name Name of a timeline
	 * @param start Start date of a timeline
	 * @param end End date of a timeline
	 * @return true if timeline has been successfully added
	 */
	boolean onAddTimeline(String name, LocalDateTime start, LocalDateTime end);
	
	
	/**
	 * Deletes specified timeline from timeline list
	 * @param timeline
	 * @return true if timeline was deleted successfully
	 */
<<<<<<< HEAD
	boolean onDeleteTimeline (Timeline timeline);
=======
	boolean onDeleteTimeline ();
>>>>>>> refs/remotes/origin/master


}