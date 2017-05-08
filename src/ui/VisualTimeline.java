package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import functions.Event;
import functions.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VisualTimeline extends GridPane {

	private final ArrayList<Event> events = new ArrayList<Event>();
	private final ShowEvents eventBox;
	
	private Timeline timeline;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private LocalDateTime monthStartDate;
	
	private LocalDateTime currentStartDate;
	private LocalDateTime currentEndDate;
	private Text text;
	
	
	private YearView year;
	private MonthView month;
	private DayView day;
	
	
	private final int PANE_SPACE = 2;
	
	private DateTimeFormatter formatMonth = DateTimeFormatter.ofPattern("MMM");
	
	public VisualTimeline(ShowEvents show) {
		eventBox = show;
		setHgap(PANE_SPACE);
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			
	}
	
	/**
	 * method to check if year/month/day view is to be created
	 * @param current - Timeline, current timeline
	 */
	public void createVisualTimeline(Timeline current) {
		timeline = current;
		startDate = current.getStart();
		endDate = current.getEnd();
		
		if (startDate.getYear() < endDate.getYear()) {
			
			createYear(startDate, endDate);
		}
		else if (startDate.getYear() == endDate.getYear() && startDate.getMonthValue()<endDate.getMonthValue()) {
			
			createMonthView(startDate);
		}
		else if (startDate.getMonthValue() == endDate.getMonthValue()) {
			
			createDayView(startDate);
		}
		
	}
	
	public void updateVisualTimeline() {
		System.out.println("Start: "+currentStartDate+" End: "+currentEndDate);
		if (currentStartDate.getMonthValue() == currentEndDate.getMonthValue() &&
				currentStartDate.getYear() == currentEndDate.getYear()) {
			
			createDayView(currentStartDate);
		}
		else if (currentStartDate.getMonthValue()<currentEndDate.getMonthValue() &&
				currentStartDate.getYear()==currentEndDate.getYear()) {
			
			createMonthView(currentStartDate);
		}
		else if (currentStartDate.getYear() < currentEndDate.getYear()) {
			
			createYear(currentStartDate, currentEndDate);
		}
	}
	
	
	/**
	 * creates one yearBox for each year of the Timeline
	 * @param start - timeline start
	 * @param end - timeline end
	 */
	public void createYear(LocalDateTime start, LocalDateTime end) {
		currentStartDate = start;
		currentEndDate = end;
		getChildren().clear();
		int counter = 0;
		while (start.getYear() <= end.getYear()) {
			year = new YearView();
			text = new Text(start.getYear()+"");
			text.setFill(Color.WHITE);
			text.setFont(Font.font ("Times New Roman", 22));
			year.getChildren().add(text);
			setYearOnAction(year, start);
			start = start.plusYears(1);
			this.add(year, counter, 0);
			counter++;
		}
		
		System.out.println("YearView:   Start: "+currentStartDate+" End: "+currentEndDate);
		eventBox.addYearEvents(timeline.getEvents(), year.getLength(), timeline);
		
	}
	
	/**
	 * helpmethod to set yearbox onMouseClicked
	 * @param box - yearBox
	 * @param start - start date of the yearBox
	 */
	private void setYearOnAction(HBox box, LocalDateTime start) {
		box.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				createMonthView(start);
				
			}
			
		});
	}
	
	/**
	 * creates one monthbox for each month in the specific year
	 * @param start - start date of the year
	 */
	private void createMonthView(LocalDateTime start) {
		currentStartDate = start;
		currentEndDate = start.withMonth(12).withDayOfMonth(31);
		monthStartDate = start;
		getChildren().clear();
		LocalDateTime startDate = start;
		LocalDateTime endDate = start.withMonth(12).withDayOfMonth(31);
		int counter = 0;
		
		while (startDate.getMonthValue() <= endDate.getMonthValue() &&
				startDate.getYear() == endDate.getYear()) {
			
			month = new MonthView();
			text = new Text(startDate.format(formatMonth));
			text.setFill(Color.WHITE);
			text.setFont(Font.font ("Times New Roman", 22));
			month.getChildren().add(text);
			setMonthOnAction(month, startDate);
			if (startDate.getMonthValue() == 12) {
				startDate = startDate.plusYears(1).withDayOfYear(1);
			}
			else {
				startDate = startDate.plusMonths(1).withDayOfMonth(1);
			}
			add(month, counter, 0);
			counter++;
		}
		
		System.out.println("MonthView:   Start: "+currentStartDate+" End: "+currentEndDate);
		findEvents(start, currentEndDate);
		eventBox.addMonthEvents(events, month.getLength(), start);
		
	}
	
	/**
	 * helpmethod to set monthbox onMouseClicked (both primary and secondary)
	 * @param box - monthBox
	 * @param start - startDate of the monthBox
	 */
	private void setMonthOnAction(HBox box, LocalDateTime start) {
		box.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					
					createDayView(start);
				}
				else {
					
					createYear(timeline.getStart(), timeline.getEnd());
				}
				
				
			}
			
		});
		
	}
	
	/**
	 * creates one daybox for each day in the specific month
	 * @param start - start date of the month
	 */
	private void createDayView(LocalDateTime start) {
		currentStartDate = start;
		currentEndDate = start.withDayOfMonth(start.toLocalDate().lengthOfMonth());
		getChildren().clear();
		LocalDateTime startDate = start;
		LocalDateTime endDate;
		if (startDate.getMonthValue() == 12) {
			endDate = startDate.plusYears(1).withDayOfYear(1);
		}
		else {
			endDate = startDate.plusMonths(1).withDayOfMonth(1);
		}

		int counter = 0;
		
		while (startDate.compareTo(endDate) <0) {
			day = new DayView();
			text = new Text(startDate.getDayOfMonth()+"");
			text.setFill(Color.WHITE);
			text.setFont(Font.font ("Times New Roman", 22));
			day.getChildren().add(text);
			startDate = startDate.plusDays(1);
			setDayOnAction(day);
			add(day, counter, 0);
			counter++;
		}
		
		System.out.println("DayView:   Start: "+currentStartDate+" End: "+currentEndDate);
		findEvents(start, currentEndDate );
		eventBox.addDayEvents(events, day.getLength(), start);
	}
	
	/**
	 * helpmethod to set daybox onMouseClicked (only secondary)
	 * @param box - dayBox
	 */
	private void setDayOnAction(HBox box) {
		box.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					
					createMonthView(monthStartDate);
				}
				else {
					
				}
			}
			
		});
	
	}
	
	/**
	 * help method to find events belonging to a specific timeframe
	 * (also finds events who doesnt have startdate but only end date or
	 * has startdate before and end date after the specific timeframe)
	 * @param start - start date
	 * @param end - end date
	 */
	private void findEvents(LocalDateTime start, LocalDateTime end) {
		events.clear();
		for (Event event : timeline.getEvents()) {
			LocalDateTime eventStart = event.getEventStart();
			LocalDateTime eventEnd = event.getEventEnd();
			if (eventEnd == null)  {
				if (eventStart.compareTo(start)>=0 && eventStart.compareTo(end)<0) {
					events.add(event);
				}
			}
			else if (eventEnd != null) {				
				if ((eventStart.compareTo(start)>=0 && eventStart.compareTo(end)<0) 
					|| (eventEnd.compareTo(start) >0 && eventEnd.compareTo(end)<0)
					|| (eventStart.compareTo(start) < 0 && eventEnd.compareTo(end) > 0)) {
					events.add(event);
				}
			}
		}
		
	}
	
	
	
	
	
	
}
