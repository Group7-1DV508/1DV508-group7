package test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controls.ChangeListener;
import controls.EventControl;
import functions.App;
import functions.Timeline;

public class EventControlTest {
	
	String name3;
	String name2;
	String name;
	String des3;
	String des2;
	String des;
	LocalDateTime start3;
	LocalDateTime start2;
	LocalDateTime start;
	LocalDateTime end3;
	LocalDateTime end2;
	LocalDateTime end;
	EventControl eventC;
	App app;
	
	@Before
	public void setUp() throws Exception {
		name3 = "";
		name2 = "a";
		name = "c";
		des3 = "";
		des2 = "b";
		des = "d";
		start3 = null;
		start2 = LocalDateTime.now();
		start = start2.plusHours(20);
		end3 = null;
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
	public void testonAddEventDurationIncorrect() {
		boolean result = eventC.onAddEventDuration(name3, des3, start3, end3);
		assertFalse(result);
		assertEquals(app.getCurrentTimeline().size(), 0);
		
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
	public void testonAddEventIncorrect() {
		app.getCurrentTimeline().addEvent(name, des, start);
		boolean result = eventC.onAddEvent(name3, des3, start3);
		assertFalse(result);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventName(), name);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventDescription(), des);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventStart(), start);
	}
	
	@Test
	public void testonEditEvent() {
		app.getCurrentTimeline().addEvent(name2, des2, start2);
		eventC.setCurrentEvent(app.getCurrentTimeline().getEvents().get(0));
		boolean result = eventC.onEditEvent(name, des, start);
		assertTrue(result);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventName(), name);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventDescription(), des);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventStart(), start);
	}
	
	@Test
	public void testonEditEventIncorect() {
		app.getCurrentTimeline().addEvent(name2, des2, start2);
		eventC.setCurrentEvent(app.getCurrentTimeline().getEvents().get(0));
		boolean result = eventC.onEditEvent(name3, des3, start3);
		assertFalse(result);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventName(), name2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventDescription(), des2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventStart(), start2);
		
	}
	
	@Test
	public void testonEditEventDuration() {
		app.getCurrentTimeline().addEvent(name2, des2, start2);
		eventC.setCurrentEvent(app.getCurrentTimeline().getEvents().get(0));
		boolean result = eventC.onEditEventDuration(name, des, start, end);
		assertTrue(result);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventName(), name);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventDescription(), des);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventStart(), start);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventEnd(), end);
	}
	
	@Test
	public void testonEditEventDurationIncorrect() {
		app.getCurrentTimeline().addEventDuration(name2, des2, start2, end2);
		eventC.setCurrentEvent(app.getCurrentTimeline().getEvents().get(0));
		boolean result = eventC.onEditEventDuration(name3, des3, start3, end3);
		assertFalse(result);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventName(), name2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventDescription(), des2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventStart(), start2);
		assertEquals(app.getCurrentTimeline().getEvents().get(0).getEventEnd(), end2);
	}
}