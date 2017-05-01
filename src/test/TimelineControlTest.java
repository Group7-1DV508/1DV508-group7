package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controls.ChangeListener;
import controls.EventControl;
import controls.TimelineControl;
import functions.App;
import functions.Timeline;

public class TimelineControlTest implements ChangeListener {
	
	private static App app;
	private static TimelineControl timelineC;
	
	//Timeline input
	private final String EXPECTED_TIMELINE_NAME = "Timeline Test 1";
	private final LocalDateTime EXPECTED_TIMELINE_START = LocalDateTime.of(2001, 01, 01, 00, 00);
	private final LocalDateTime EXPECTED_TIMELINE_END = LocalDateTime.of(2002, 01, 01, 00, 00);
	
	private final String EXPECTED_TIMELINE_NAME2 = "Timeline Test 2";
	private final LocalDateTime EXPECTED_TIMELINE_START2 = LocalDateTime.of(2000, 01, 01, 00, 00);
	private final LocalDateTime EXPECTED_TIMELINE_END2 = LocalDateTime.of(2010, 01, 01, 00, 00);
	
	private final String EXPECTED_TIMELINE_NAME3 = "";
	private final LocalDateTime EXPECTED_TIMELINE_START3 = null;
	private final LocalDateTime EXPECTED_TIMELINE_END3 = null;
	
	private final Timeline timeline_1 = new Timeline("Timeline Test 1", LocalDateTime.of(2001, 01, 01, 00, 00), LocalDateTime.of(2002, 01, 01, 00, 00));
	private final Timeline timeline_2 = new Timeline("Timeline Test 2", LocalDateTime.of(2000, 01, 01, 00, 00), LocalDateTime.of(2010, 01, 01, 00, 00));
	private final Timeline timeline_3 = new Timeline("", null, null);
	
	private boolean changeTimeline;
	private boolean newTimeline;
	private boolean editTimeline;
	private boolean editEvent;
	
	
	@BeforeClass
	public static void beforeAllTests() {
		app = new App();
		timelineC = new TimelineControl();
		timelineC.setApp(app);
		
	}
	
	@AfterClass
	public static void afterAllTests() {
		
	}
	
	@Before
	public void setUp() throws Exception {
		app.addListener(this);
		
		//initialize ChangeListener booleans (to check if correct changelistener is called)
		newTimeline = false;
		changeTimeline = false;
		editTimeline = false;
		editEvent = false;
		
		
	}
	
	@Test
	public void testOnAddTimeline() {
		assertTrue(timelineC.onAddTimeline(EXPECTED_TIMELINE_NAME, EXPECTED_TIMELINE_START, EXPECTED_TIMELINE_END));
		assertTrue(changeTimeline);
		changeTimeline = false;
		
		assertTrue(timelineC.onAddTimeline(EXPECTED_TIMELINE_NAME2, EXPECTED_TIMELINE_START2, EXPECTED_TIMELINE_END2));
		assertTrue(changeTimeline);
		changeTimeline = false;
		
		assertFalse(timelineC.onAddTimeline(EXPECTED_TIMELINE_NAME3, EXPECTED_TIMELINE_START3, EXPECTED_TIMELINE_END3));
		assertFalse(changeTimeline);
		
		assertEquals(app.getTimelines().size(), 2);
		
		assertEquals(app.getTimelines().get(0).getName(), EXPECTED_TIMELINE_NAME);
		assertEquals(app.getTimelines().get(0).getStart(), EXPECTED_TIMELINE_START);
		assertEquals(app.getTimelines().get(0).getEnd(), EXPECTED_TIMELINE_END);
		
		assertEquals(app.getTimelines().get(1).getName(), EXPECTED_TIMELINE_NAME2);
		assertEquals(app.getTimelines().get(1).getStart(), EXPECTED_TIMELINE_START2);
		assertEquals(app.getTimelines().get(1).getEnd(), EXPECTED_TIMELINE_END2);
		
	}
	
	@Test
	public void testOnDeleteTimeline() {
		assertEquals(app.getTimelines().size(), 2);
		
		assertTrue(timelineC.onDeleteTimeline());
		assertTrue(changeTimeline);
		changeTimeline = false;
		assertEquals(app.getTimelines().size(), 1);
		assertFalse(app.getCurrentTimeline() == null);
		assertTrue(timelineC.onDeleteTimeline());
		assertTrue(changeTimeline);
		changeTimeline = false;
		assertEquals(app.getTimelines().size(), 0);
		assertTrue(app.getCurrentTimeline() == null);
	}

	@Override
	public void onChangedTimeline(ArrayList<Timeline> timelines, Timeline current) {
		changeTimeline = true;
		
	}

	@Override
	public void onNewTimelineSelected(Timeline current) {
		newTimeline = true;
		
	}

	@Override
	public void onEditTimeline(Timeline current) {
		editTimeline = true;
		
	}

	@Override
	public void onEditEvent(Timeline current) {
		editEvent = true;
		
	}

	
	

}
