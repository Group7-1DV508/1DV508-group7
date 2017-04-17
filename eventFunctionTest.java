import static org.junit.Assert.*;

import org.exparity.hamcrest.date.Months;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Date;


public class eventFunctionTest {
	
	 
	LocalDate start = LocalDate.of(2014, 3, 30);
	LocalDate end = LocalDate.of(2015, 3, 30);
	String name = "nameOfEvent";
	String des = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	int maxName = 15;
	int maxDes = 300; 
	
	@Test
	//public void testAddEventDuration() {
	//need to come up with something to compare start and end dates.
//	}
	
	public void testName() {
		assertTrue(name, name.length() > 0); // Event name should at least one char
		assertTrue(name, name.length() <= maxName); 
		}
	@Test
	public void testDes() {
		assertTrue(des, des.length() >= 0);
		assertTrue(des, des.length() <= maxDes);
		}
	
	@Test
	 public void testDate() {
		 MatcherAssert.assertThat(end, LocalDateMatchers.after(start));
		 

}
}



