package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import functions.Timeline;

public class TimelineControlTest {
	String tIsCorrect = "A";
	LocalDateTime start = LocalDateTime.now();
	LocalDateTime end = start.plusDays(90);
	Timeline t = new Timeline(tIsCorrect, start, end);
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testisNameCorrect() {
		
		assertEquals(t.getName(), tIsCorrect);
		
	}
	
	@Test
	public void testisStartCorrect() {
		assertEquals(t.getStart(), start);
	}
	
	@Test
	public void testisEndCorrect() {
		assertEquals(t.getEnd(), end);
	}
	
	@Test
	public void testisCorrectInput() {
		String wrong ="";
		Timeline z = new Timeline(wrong, start, end);
		assertEquals(z.getName(), wrong);
		
	}
	
	

}
