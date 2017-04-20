package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controls.ChangeListener;
import functions.App;
import functions.Timeline;

public class ApplicationTest {
	
	App app;
	String name;
	LocalDateTime start;
	LocalDateTime end;
	Timeline timeline;
	
	@Before
	public void setUp() throws Exception {
		app = new App();
		name = "name";
		start = LocalDateTime.now();
		end = start.plusDays(30);
		timeline = new Timeline(name, start, end);
		// Skeleton for ChangeListener interface to be able to
		// add it to App object.
		class ChangeListenerForTest implements ChangeListener {
			@Override
			public void onChangedTimeline(ArrayList<Timeline> timelines, Timeline current) {
			}

			@Override
			public void onNewTimelineSelected(Timeline current) {
				
			}

			@Override
			public void onEditTimeline(Timeline current) {
				
			}
			
		}
		app.addListener(new ChangeListenerForTest());
	}

	@Test
	public void testAddTimeline() {
		app.addTimeline(name, start, end);
		assertEquals(app.getCurrentTimeline().getName(), timeline.getName());
		assertEquals(app.getCurrentTimeline().getStart(), timeline.getStart());
		assertEquals(app.getCurrentTimeline().getEnd(), timeline.getEnd());
	}
	
	@Test
	public void testAddEventToCurrent() {
		String description = "desc";
		app.addTimeline(timeline.getName(), timeline.getStart(), timeline.getEnd());
		app.addEventToCurrent(name, description, start);
		assertEquals(app.getCurrentTimeline().size(), 1);
	}
	
	@Test
	public void testSetCurrentTimeline() {
		app.setCurrentTimeline(timeline);
		assertEquals(app.getCurrentTimeline(), timeline);
	}
	
	@Test
	public void testGetTimelines() {
		app.addTimeline(name, start, end);
		assertEquals(app.getTimelines().size(), 1);
	}

}
