package se.lnu.timelinemanager;

import java.time.LocalDate;

public class TimelineFunction {
	String name;
	LocalDate startDate;
	LocalDate endDate;

	public TimelineFunction(String name, LocalDate startDate, LocalDate endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
