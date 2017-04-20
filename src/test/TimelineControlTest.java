package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import functions.Timeline;

public class TimelineControlTest {
	String tIsCorrect;
	LocalDateTime start;
	LocalDateTime end;
	Timeline t;
	@Before
	public void setUp() throws Exception {
		tIsCorrect = "A";
		start = LocalDateTime.now();
		end = start.plusDays(90);
		t = new Timeline(tIsCorrect, start, end);
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
