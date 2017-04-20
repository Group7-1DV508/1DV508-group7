package test;

import static org.junit.Assert.*;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import functions.Timeline;

public class TimelineTest {
	
	String tName ;
	String eName;
	String eDescription;
	LocalDateTime aDay;
	LocalDateTime aFuture;
	Timeline t;
	
	@Before
	public void setUp() {
		tName = "Sup";
		eName = "Hup";
		eDescription = "W";
		aDay = LocalDateTime.now();
		aFuture = aDay.plusDays(500);
		t =  new Timeline(tName, aDay, aFuture);
	}
	
	@Test
	public void testTimelineConstructor() {
		assertEquals(t.getName(), tName);
		assertEquals(t.getStart(), aDay);
		assertEquals(t.getEnd(), aFuture);
	} 

	@Test
	public void testGetName() {
		assertEquals(t.getName(), tName);
	}

	@Test
	public void testGetStart() {
		 assertEquals(t.getStart(), aDay);

	}

	@Test
	public void testGetEnd() {
		assertEquals(t.getEnd(), aFuture);
	}

	@Test
	public void testGetEvents() {
		 t.addEvent(eName, eDescription, aDay);
		 assertEquals(t.getEvents().size(), 1);
	}

	@Test
	public void testSetName() {
		String aName = "a";
		t.setName(aName);
		assertEquals(aName, t.getName());
	}

	@Test
	public void testSetStart() {
		t.setStart(aFuture);
		assertEquals(aFuture, t.getStart());
	}

	@Test
	public void testSetEnd() {
		t.setEnd(aDay);
		assertEquals(aDay, t.getEnd());
	}

	@Test
	public void testAddEventDuration() {
		LocalDateTime help = LocalDateTime.MAX;
		LocalDateTime help2 = LocalDateTime.MIN;
		t.addEventDuration(eDescription, eDescription, help2, help);
		assertTrue(t.getEvents().size() == 1);
		
	}

	@Test
	public void testAddEvent() {
		String tName = "Sup";
		String eName = "Hup";
		String eDescription = "W";
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime aFuture = aDay.plusDays(500);
		LocalDateTime eventDay = today.plusDays(200);
		Timeline t1 =  new Timeline(tName, aDay, aFuture);
		t1.addEvent(eDescription, eName, eventDay);
		assertTrue(t1.getEvents().size() == 1);	
		 
	}
}
		