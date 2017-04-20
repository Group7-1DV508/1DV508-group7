package controls;

import java.util.ArrayList;

import functions.Timeline;

public interface ChangeListener {
	
	void onChangedTimeline(ArrayList<Timeline> timelines, Timeline current);//used when a timeline is added or deleted
	
	void onNewTimelineSelected(Timeline current);							//used when current timeline is updated
	
	void onEditTimeline(Timeline current);									//used when current timeline is edited, either timeline or event
																			//or when new events are created or deleted
	
}
