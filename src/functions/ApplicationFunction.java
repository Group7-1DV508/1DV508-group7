package functions;

import java.time.LocalDate;
import java.util.ArrayList;

public class ApplicationFunction {
	
	ArrayList<TimelineFunction> Timelines;
	
	public void addTimeline(String name, LocalDate start, LocalDate end) {
		Timelines.add(new TimelineFunction(name, start, end));
	}
	
	
}
