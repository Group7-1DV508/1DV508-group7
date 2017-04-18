package ui;


import controls.ApplicationListener;

import java.awt.TextField;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


//import functions.ApplicationFunction;

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
	
	//private static ApplicationFunction af = null;
	


	public TextField tF1 = new TextField();
	public TextField tF2 = new TextField();
	public TextField tF3 = new TextField();
	public TextField tF4 = new TextField();
	public TextField tF5 = new TextField();
	public TextField tF6 = new TextField();
	public TextField tF7 = new TextField();
	public VBox root = new VBox();
	public VBox holder2 = new VBox();
	public VBox root2 = new VBox();
	public Scene scene;
	public Scene scene1;
	public Stage stage;

	
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


	//public static void setApplicationFunction(ApplicationFunction af) {
		//ApplicationView.af = af;
	//}


	public VBox create() {
		VBox holder = new VBox();
		List<Timeline> timeLines = af.getTimelines();
		for (Timeline tl : timeLines) {
			Text name = new Text(20, 50, tl.getName() + ": ");
			Text start = new Text(20, 50, "" + tl.getStart() + "--");
			Text end = new Text(20, 50, "" + tl.getEnd());

			HBox root = new HBox();
			root.getChildren().add(name);
			root.getChildren().add(start);
			root.getChildren().add(end);
			holder.getChildren().add(root);
		}
		return holder;
	}


	
	public void start(Stage primaryStage) throws Exception {
		VBox v = create();
		primaryStage.setTitle("Timelines Panel");
		Scene se = new Scene(v, 550, 550);
		primaryStage.setScene(se);
		primaryStage.show();
	}
}
