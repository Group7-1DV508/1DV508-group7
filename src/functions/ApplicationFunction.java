package functions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import se.lnu.timelinemanager.ApplicationInterface;

public class ApplicationFunction implements ApplicationInterface {
	private Timeline currentTimeLine = null;
	private List<Timeline> timelines;

	@Override
	public void addTimeline(String name, LocalDate startDate, LocalDate endDate) {
		Timeline currentTimeLine = new Timeline(name, startDate, endDate);
		if (timelines == null)
			timelines = new ArrayList<Timeline>();
		timelines.add(currentTimeLine);
	}

	public Timeline getCurrentTimeLine() {
		return currentTimeLine;
	}

	public void setCurrentTimeLine(Timeline currentTimeLine) {
		this.currentTimeLine = currentTimeLine;
	}

	public List<Timeline> getTimelines() {
		return timelines;
	}

}
