package se.lnu.timelinemanager;

import java.time.LocalDate;

public interface ApplicationInterface {
	
	public void addTimeline(String name, LocalDate startDate, LocalDate endDate);

}
