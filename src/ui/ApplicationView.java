package ui;


import controls.ApplicationListener;

import java.awt.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import functions.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ApplicationView {
	
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

	
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		root.getChildren().addAll(eventView.getRoot(), timelineView.getRoot());
		
		primaryStage.setTitle("Timelines Panel");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
