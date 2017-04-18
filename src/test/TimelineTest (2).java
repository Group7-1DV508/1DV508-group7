import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;

import org.junit.Test;

public class TimelineTest {
	
	String tName = "Sup";
	String eName = "Hup";
	String eDescription = "W";
	LocalDate aDay = LocalDate.now();
	LocalDate aFuture = aDay.plusDays(500);
	LocalDate eDay = aDay.plusDays(200);
	Timeline t =  new Timeline(tName, aDay, aFuture);
	
	
	
	/* @Test
	public void testTimeline() {
		 fail("Not yet implemented");
	} */

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

	/* @Test
	public void testGetEvents() {
		 fail("Not yet implemented");
	} */

	@Test
	public void testSetName() {
	String aName = "a";
		t.setName(aName);
		assertEquals(aName, t.getName());
	}

	@Test
	public void testSetStart() {
		t.setStart(aDay);
		assertEquals(aDay, t.getStart());
	}

	@Test
	public void testSetEnd() {
		t.setEnd(aDay);
		assertEquals(aDay, t.getEnd());
	}

	@Test
	public void testAddEventDuration() {
	
		t.addEventDuration(eDescription, eName, eDay, eDay);
		 assertTrue(t.getEvents().size() == 1);
		
	}
	




	@Test
	public void testAddEvent() {
		String tName = "Sup";
		String eName = "Hup";
		String eDescription = "W";
		LocalDate aDay = LocalDate.now();
		LocalDate aFuture = aDay.plusDays(500);
		LocalDate eDay = aDay.plusDays(200);
		Timeline t1 =  new Timeline(tName, aDay, aFuture);
		t1.addEvent(eDescription, eName, eDay);
		 assertTrue(t1.getEvents().size() == 1);	
		 
	}
	
		





}
