package se.lnu.timelinemanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicationFunction implements ApplicationInterface {
	private TimelineFunction currentTimeLine = null;
	private List<TimelineFunction> timelines;

	@Override
	public void addTimeline(String name, LocalDate startDate, LocalDate endDate) {
		TimelineFunction currentTimeLine = new TimelineFunction(name, startDate, endDate);
		if (timelines == null)
			timelines = new ArrayList<TimelineFunction>();
		timelines.add(currentTimeLine);
	}

	public TimelineFunction getCurrentTimeLine() {
		return currentTimeLine;
	}

	public void setCurrentTimeLine(TimelineFunction currentTimeLine) {
		this.currentTimeLine = currentTimeLine;
	}
	

}
