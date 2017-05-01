package controls;

import functions.Event;
import functions.Timeline;
import javafx.scene.control.Button;

public interface ApplicationListener {
	
	/**
	 * Called when a new Timeline is selected from ComboBox in ApplicationView
	 * @param t , chosen Timeline
	 */
	void onTimelineSelected(Timeline t);

	void onNewEventSelected(Event e);
	
	void onTimelineSaved();
}
