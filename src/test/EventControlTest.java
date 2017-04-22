package test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controls.ApplicationControl;
import controls.ChangeListener;
import controls.EventControl;
import functions.App;
import functions.Timeline;
import io.FileHandler;
import ui.ApplicationView;

public class EventControlTest {
	
	String name2;
	String name;
	String des2;
	String des;
	LocalDateTime start2;
	LocalDateTime start;
	LocalDateTime end2;
	LocalDateTime end;
	EventControl eventC;
	App app;
	
	@Before
	public void setUp() throws Exception {
		name2 = "a";
		name = "c";
		des2 = "b";
		des = "d";
		start2 = LocalDateTime.now();
		start = start2.plusHours(20);
		end2 = start2.plusDays(30);
		end = end2.plusMonths(2);
		eventC = new EventControl();
		app = new App();
		eventC.setApp(app);
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
		app.addTimeline(name2, start2, start2);
	}
	
	@Test 
	public void testonAddEventDuration() {
		boolean result = eventC.onAddEventDuration(name2, des2, start2, end2);
		assertTrue(result); 
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventName(), name2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventDescription(), des2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventStart(), start2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventEnd(), end2);
		
	}
	
	@Test
	public void testonAddEvent(){
		boolean result = eventC.onAddEvent(name2, des2, start2);
		assertTrue(result);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventName(), name2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventDescription(), des2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventStart(), start2);
	}
	
	@Test
	public void testonEditEvent() {
		boolean result = eventC.onEditEvent(name, des, start);
		assertTrue(result);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventName(), name);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventDescription(), des);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventStart(), start);
	}
	
	@Test
	public void testonEditEventDuration() {
		boolean result = eventC.onEditEventDuration(name, des, start, end);
		assertTrue(result);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventName(), name);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventDescription(), des);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventStart(), start);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventEnd(), end);
	}
}