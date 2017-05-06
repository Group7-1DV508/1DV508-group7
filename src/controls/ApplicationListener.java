package controls;

<<<<<<< HEAD
=======
import java.util.ArrayList;

>>>>>>> refs/remotes/origin/master
import functions.Event;
import functions.Timeline;

public interface ApplicationListener {
	
	/**
	 * Called when a new Timeline is selected from ComboBox in ApplicationView
	 * @param t , chosen Timeline
	 */
	void onTimelineSelected(Timeline t);
<<<<<<< HEAD

	void onNewEventSelected(Event e);
}
=======

	void onNewEventSelected(Event e);
	/**
	 * Returns a list of currently loaded timeline
	 * @return arraylist containing timelines
	 */
	ArrayList<Timeline> getTimelines();
	

	void onTimelineSaved();
	
	void onTimelineLoaded();
}
>>>>>>> refs/remotes/origin/master
