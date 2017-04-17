package functions;

import java.time.LocalDate;
import java.util.ArrayList;

public class Application {
	
	ArrayList<Timeline> Timelines;
	
	public void addTimeline(String name, LocalDate start, LocalDate end) {
		Timelines.add(new Timeline(name, start, end));
	}
	
	
}
