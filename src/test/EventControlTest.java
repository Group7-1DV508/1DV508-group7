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
	String des;
	LocalDateTime start2;
	LocalDateTime end2;
	EventControl eventC;
	App app;
	
	@Before
	public void setUp() throws Exception {
		name2 = "a";
		des = "b";
		start2 = LocalDateTime.now();
		end2 = start2.plusDays(30);
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
		assertTrue(eventC.onAddEventDuration(name2, des, start2, end2)); 
	}
	
	@Test
	public void testonAddEvent(){
		assertTrue(eventC.onAddEvent(name2, des, start2));
	}
	
	@Test
	public void testonEditEvent() {
		assertTrue(eventC.onEditEvent(name2, des, start2));
	}
	
	@Test
	public void testonEditEventDuration() {
		assertTrue(eventC.onEditEventDuration(name2, des, start2, end2));
	}
}