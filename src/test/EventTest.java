import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import functions.Event;

public class EventTest {
	
	String name = "Smoke";
	String description = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	LocalDate start = LocalDate.now();
	LocalDate end = start.plusDays(2);
	Event event = new Event(name, description, start, end);

	/* @Test
	public void testEventStringStringLocalDateLocalDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testEventStringStringLocalDate() {
		fail("Not yet implemented");
	} */

	@Test
	public void testGetEventName() {
		assertTrue(event.getEventName() == name);
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
		assertTrue(event.getEventDescription() == description);
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
		assertTrue(event.getEventStart() == start);
		 assertEquals(event.getEventStart(), start);
	}

	@Test
	public void testSetEventStart() {
		LocalDate yesterday = start.minusDays(-1);
		event.setEventStart(yesterday);
		assertEquals(yesterday, event.getEventStart());
	}

	@Test
	public void testGetEventEnd() {
		assertTrue(event.getEventEnd() == end);
		assertEquals(event.getEventEnd(), end);
	}

	@Test
	public void testSetEventEnd() {
		LocalDate aYearfromNow = start.plusDays(365);
		event.setEventEnd(aYearfromNow);
		assertEquals(aYearfromNow, event.getEventEnd());
	}

}
