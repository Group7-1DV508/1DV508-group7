package ui;


import controls.ApplicationListener;
import controls.ChangeListener;

import java.awt.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import functions.Event;
import functions.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ApplicationView implements ChangeListener {
	
	private EventView eventView;
	private TimelineView timelineView;
	private ApplicationListener appListener;
	private GridPane view;
	private GridPane timelineBox;
	
	
	public ApplicationView() {
		eventView = new EventView();
		timelineView = new TimelineView();
	}
	
	public void addListener(ApplicationListener appList) {
		appListener = appList;
	}
	
	public EventView getEventView() {
		return eventView;
	}
	
	public TimelineView getTimelineView() {
		return timelineView;
	}


	public GridPane getRoot()  {
		
		view = new GridPane();
		timelineBox = new GridPane();
		
		view.add(timelineView.getRoot(), 0, 1);
		
		return view;
	}

	@Override
	public void onChangedTimeline(ArrayList<Timeline> timelines, Timeline current) {
		Text info = new Text("You have created a Timeline:");
		Text name = new Text("Name: "+current.getName());
		Text start = new Text("Start Year: "+getYear(current.getStart().toString()));
		Text end = new Text("End Year: "+getYear(current.getEnd().toString()));
		
		timelineBox.add(info, 0, 1);
		timelineBox.add(name, 0, 2);
		timelineBox.add(start, 0, 3);
		timelineBox.add(end, 0, 4);
		timelineBox.add(eventView.getRoot(), 0, 8);
		view.add(timelineBox, 0, 3);
		
		
	}

	@Override
	public void onNewTimelineSelected(Timeline current) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEditTimeline(Timeline current) {
		ArrayList<Event> events = current.getEvents();
		VBox eventBox = new VBox();
		
		for (Event e : events) {
			Text name = new Text(e.getEventName());
			Text description = new Text(e.getEventDescription());
			Text start = new Text(getDateTime(e.getEventStart().toString()));
			eventBox.getChildren().addAll(name, description, start);
		}
		timelineBox.add(eventBox, 5, 1);
		
	}
	
	public String getYear(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append(str.charAt(0));
		sb.append(str.charAt(1));
		sb.append(str.charAt(2));
		sb.append(str.charAt(3));
		return sb.toString();
	}
	
	public String getDateTime(String str) {
		String temp = "Date: "+str.replace("T", " Time: ");
		return temp;
	}
	
}
