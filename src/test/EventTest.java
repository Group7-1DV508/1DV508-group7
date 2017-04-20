package test;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import functions.Event;

public class EventTest {
	
	String name;
	String description;
	LocalDateTime start;
	LocalDateTime end;
	Event event;
	
	@Before
	public void setUp() {
		name = "Smoke";
		description = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
		start = LocalDateTime.now();
		end = start.plusDays(2);
		event = new Event(name, description, start, end);
	}
	
	@Test
	public void testEventConstructor() {
		assertEquals(event.getEventName(), name);
		assertEquals(event.getEventDescription(), description);
		assertEquals(event.getEventStart(), start);
		assertEquals(event.getEventEnd(), end);

	}

	@Test
	public void testGetEventName() {
		assertEquals(event.getEventName(), name);
	}

	@Test
	public void testSetEventName() {
		String newName = "Anything";
		event.setEventName(newName);
	    assertEquals(newName, event.getEventName());
	}

	@Test
	public void testGetEventDescription() {
		assertEquals(event.getEventDescription(), description);
	}

	@Test
	public void testSetEventDescription() {
		String newDescription = "";
		event.setEventDescription(newDescription);
		assertEquals(newDescription, event.getEventDescription());
	}

	@Test
	public void testGetEventStart() {
		 assertEquals(event.getEventStart(), start);
	}

	@Test
	public void testSetEventStart() {
		LocalDateTime yesterday = start.minusDays(-1);
		event.setEventStart(yesterday);
		assertEquals(yesterday, event.getEventStart());
	}

	@Test
	public void testGetEventEnd() {
		assertEquals(event.getEventEnd(), end);
	}

	@Test
	public void testSetEventEnd() {
		LocalDateTime aYearfromNow = start.plusDays(365);
		event.setEventEnd(aYearfromNow);
		assertEquals(aYearfromNow, event.getEventEnd());
	}

}
