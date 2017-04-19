package test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controls.EventControl;
import functions.App;

public class EventControlTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

/*	@Test
	public void test() {
		fail("Not yet implemented"); */
//	}
	
	@Test 
	public void testonAddEventDuration() {
		String name2 = "a";
		String des = "b";
		LocalDateTime start2 = LocalDateTime.now();
		LocalDateTime end2 =  start2.plusDays(300);
		EventControl eventC = new EventControl();
		assertTrue(eventC.onAddEventDuration(name2, des, start2, end2)); 
	}
	
	@Test
	public void testonAddEvent(){
		String name2 = "a";
		String des = "b";
		LocalDateTime start2 = LocalDateTime.now();
		EventControl eventC = new EventControl();
		App app = new App();
		eventC.setApp(app);
		app.addTimeline(name2, start2, start2);
		assertTrue(eventC.onAddEvent(name2, des, start2));
		

	}
}