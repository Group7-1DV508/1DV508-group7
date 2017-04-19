package ui;


import controls.ApplicationListener;
import controls.ChangeListener;

import java.awt.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import functions.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ApplicationView implements ChangeListener {
	
	private EventView eventView;
	private TimelineView timelineView;
	private ApplicationListener appListener;
	
	
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


	public VBox getRoot()  {
		VBox view = new VBox();
		view.getChildren().addAll(timelineView.getRoot(eventView.getRoot()));
		return view;
	}

	@Override
	public void onChangedTimeline(ArrayList<Timeline> timelines, Timeline current) {
		
		
	}

	@Override
	public void onNewTimelineSelected(Timeline current) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEditTimeline(Timeline current) {
		// TODO Auto-generated method stub
		
	}
}
