package ui;

import controls.ApplicationListener;
import controls.ChangeListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import functions.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.timelineVisuals.EventShape;
import ui.timelineVisuals.ShowEvents;
import ui.timelineVisuals.TimelineInformationBox;
import ui.timelineVisuals.VisualTimeline;

public class ApplicationView implements ChangeListener {
	private final Image saveT = new Image(getClass().getResource("/saveT.png").toExternalForm(), 30, 30, true, true);
	private final Image loadT = new Image(getClass().getResource("/loadT.png").toExternalForm(), 35, 35, true, true);
	private final Image help = new Image(getClass().getResource("/help.png").toExternalForm(), 25, 25, true, true);
	private String css = this.getClass().getResource("/ui/application.css").toExternalForm();
	private final Tooltip saveTo = new Tooltip();
	private final Tooltip loadTo = new Tooltip();
	private final Tooltip helpTo = new Tooltip();

	private Button saveButton = new Button();
	private EventView eventView;
	private TimelineView timelineView;
	private ApplicationListener appListener;
	// contains all parts of the window (Main view)
	private final VBox view = new VBox();
	// contains ComboBox to choose current timeline, add/delete timeline
	private final HBox timelineButtons = new HBox();
	private final HBox helpButton = new HBox();
	boolean filePath;

	/*
	 * contains all parts within the Current Timeline View Add/Edit/Delete event
	 * buttons, scroll window, current timeline and events visuals
	 */
	private final VBox timelineMainBox = new VBox();
	// scroll window for timeline
	private final ScrollPane scrollTimeline = new ScrollPane();
	// buttons for add/edit/delete event
	private final HBox eventButtons = new HBox();
	// comboBox to choose timeline
	private final ComboBox<Timeline> chooseTimeline = new ComboBox<Timeline>();
	// contains all events at the correct position
	private final ShowEvents eventBox = new ShowEvents();
	private final TimelineInformationBox informationBox = new TimelineInformationBox();
	private final VisualTimeline currentTimeline = new VisualTimeline(eventBox);


	/**
	 * Constructor, creates and initialize EventView and TimelineView
	 */
	public ApplicationView() {
		eventView = new EventView();
		timelineView = new TimelineView();
		saveButton = new Button();
	}

	/**
	 * Update the ApplicationListener variable with the ApplicationListener
	 * given as input
	 *
	 * @param appList
	 *            , (ApplicationListener)
	 */
	public void addListener(ApplicationListener appList) {
		appListener = appList;
		eventBox.setListenerAndView(appList, eventView);
		eventBox.setInformationBox(informationBox);
	}

	/**
	 * Returns the EventView currently used
	 *
	 * @return EventView
	 */
	public EventView getEventView() {
		return eventView;
	}

	/**
	 * Returns the TimelineView currently used
	 *
	 * @return TimelineView
	 */
	public TimelineView getTimelineView() {
		return timelineView;
	}

	/**
	 * Returns the root of the Application Window
	 *
	 * @return GridPane
	 */
	public VBox getRoot() {

		return root();
	}

	/**
	 * Creates the Root for the Application Window collects the Timeline Buttons
	 * and the Main Timeline Box
	 */
	private VBox root() {
		view.setStyle("-fx-base: #e6e6fa");
		view.getChildren().clear();
		view.setSpacing(10);
		view.setAlignment(Pos.CENTER);
		view.getChildren().addAll(timelineButtonsBox(), timelineMainBox());
		return view;
	}

	/**
	 * Return "Add Timeline" Button
	 *
	 * @return Button
	 */
	private Button getAddTimelineButton() {
		return timelineView.getAddTimelineButton();
	}

	/**
	 * Returns the "Delete Timeline" Button
	 *
	 * @return Button
	 */
	private Button getDeleteTimelineButton() {
		return timelineView.getDeleteTimelineButton();
	}

	/**
	 * Returns the "Add Event" Button
	 *
	 * @return Button
	 */
	private Button getAddEventButton() {
		return eventView.getAddEventButton();
	}

	/**
	 * Creates the Help Button
	 */
	private Button createHelpButton() {
		helpTo.setText("Help");
		helpTo.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		Button helpButton = new Button("",new ImageView(help));
		helpButton.setStyle("-fx-background-radius: 5em; ");
		helpButton.setTooltip(helpTo);
		helpButton.setMinSize(35, 35);
		helpButton.setMaxSize(35, 35);

		helpButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override public void handle(ActionEvent e) {
		        Stage stage = new Stage();
		        //Fill stage with content
		        stage.show();
			}
		});
		return helpButton;
	}
	/* Creates a button which saves a given
	 * timeline to a file path chosen by
	   the user through the fileChooser.*/

	private Button saveTimelineButton() {
		saveButton = new Button("",new ImageView(saveT));
		saveTo.setText("Save Timeline");
		saveTo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		saveButton.setTooltip(saveTo);
		saveButton.setMinSize(70, 35);
		saveButton.setMaxSize(70, 35);
		saveButton.getStylesheets().add(css);

		saveButton.setOnAction(ActionEvent  -> {
			  appListener.onTimelineSaved();
		});
    return saveButton;
	}



	private Button loadTimelineButton() {
		loadTo.setText("Load Timeline");
		loadTo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		Button loaded = new Button("",new ImageView(loadT));

		loaded.setTooltip(loadTo);
		loaded.setMinSize(70, 35);
		loaded.setMaxSize(70, 35);
		loaded.getStylesheets().add(css);

		loaded.setOnAction(ActionEvent -> {
			appListener.onTimelineLoaded();
		});
		return loaded;
}

	/**
	 * collects and return all buttons associated with timeline
	 *
	 * @return HBox
	 */
	private HBox timelineButtonsBox() {
		chooseTimeline.setMinSize(30, 30);

		HBox temp = new HBox();
		temp.getChildren().clear();
		helpButton.getChildren().clear();
		helpButton.getChildren().add(createHelpButton());
		helpButton.setAlignment(Pos.BOTTOM_LEFT);

		timelineButtons.getChildren().clear();
		timelineButtons.setSpacing(18.0);
		timelineButtons.getChildren().addAll(chooseTimeline,getAddTimelineButton(), saveTimelineButton(), loadTimelineButton(), getDeleteTimelineButton(), getAddEventButton());
		timelineButtons.setAlignment(Pos.CENTER_LEFT);
		timelineButtons.setPadding(new Insets(0,500,0,10));

		temp.getChildren().addAll(timelineButtons,helpButton);

		return temp;
	}

	/**
	 * collects and return all buttons associated with event
	 *
	 * @return HBox
	 */
	private HBox eventButtonsBox() {
		eventButtons.getChildren().clear();
		eventButtons.setAlignment(Pos.CENTER);
		eventButtons.setSpacing(20.0);
		eventButtons.setAlignment(Pos.CENTER_LEFT);
		eventButtons.setPadding(new Insets(0,0,0,5));
		chooseTimeline.setMinSize(150,35);
		chooseTimeline.setStyle("-fx-base: #e6e6fa;-fx-font: 15 arial;-fx-text-alignment: center;");
		chooseTimeline.getStylesheets().add(css);
		return eventButtons;
	}

	/**
	 * creates a combo box where loaded timelines can be chosen from also calls
	 * method to create the current timeline and add events to it
	 *
	 * @param timelines
	 *            , the timelines available
	 * @param current
	 *            , the currently open timeline
	 */
	private void chooseTimeline(ArrayList<Timeline> timelines, Timeline current) {

		chooseTimeline.getItems().clear();

		for (Timeline t : timelines) {
			chooseTimeline.getItems().add(t);
		}
		chooseTimeline.setValue(current);
		chooseTimeline.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (chooseTimeline.getItems().size() > 0) {
					appListener.onTimelineSelected(chooseTimeline.getValue());
				}
			}

		});
	}

	/**
	 * The main timeline box, contains scroll box, and event buttons box
	 */
	private VBox timelineMainBox() {
		timelineMainBox.getChildren().clear();
		timelineMainBox.setSpacing(10.0);
		timelineMainBox.setAlignment(Pos.CENTER);
		timelineMainBox.getChildren().addAll(timelineScrollBox(), eventButtonsBox());
		return timelineMainBox;
	}

	/**
	 * Creates a ScrollPane for the timeline and adds the current timeline and
	 * the events pane to it
	 */
	private ScrollPane timelineScrollBox() {
		VBox content = new VBox();
		content.setPadding(new Insets(0, 3, 0, 3));
		scrollTimeline.setPrefSize(400, 300);
		content.getChildren().addAll(informationBox, currentTimeline, eventBox);
		scrollTimeline.setContent(content);

		return scrollTimeline;
	}

	private void clearTimelineBox() {
		currentTimeline.getChildren().clear();
		eventBox.getChildren().clear();
		informationBox.getChildren().clear();
		chooseTimeline.getItems().clear();
		Text noTimelines = new Text("There are no new timelines currently selected");
		noTimelines.setFont(new Font("Times new Roman", 20));
		noTimelines.setFill(Color.BLACK);
		//currentTimeline.add(noTimelines, 0, 1);
	}

	@Override
	public void onChangedTimeline(ArrayList<Timeline> timelines, Timeline current) {
		if (!(current == null)) {
			chooseTimeline(timelines, current);
			//showYear(current);
			currentTimeline.createVisualTimeline(current);
			eventView.setTimelineStartEnd(current.getStart().toLocalDate(), current.getEnd().toLocalDate());

			getDeleteTimelineButton().setDisable(false);
			eventView.setDisable(false);
			saveButton.setDisable(false);
		}
		else {
			clearTimelineBox();
			getDeleteTimelineButton().setDisable(true);
			eventView.setDisable(true);
			saveButton.setDisable(true);
		}

	}

	@Override
	public void onNewTimelineSelected(Timeline current) {
		currentTimeline.createVisualTimeline(current);
		eventView.setTimelineStartEnd(current.getStart().toLocalDate(), current.getEnd().toLocalDate());
		onTimelineSaved(current);
	}

	@Override
	public void onEditTimeline(Timeline current) {
		currentTimeline.updateVisualTimeline();
	}

	@Override
	public void onEditEvent(Timeline current) {
		currentTimeline.updateVisualTimeline();
	}

	@Override
	public void onTimelineSaved(Timeline current) {
		if (current.getFile().isFile()) {
			timelineView.setTimelineSaved(true);
		}
		else {
			timelineView.setTimelineSaved(false);
		}
	}
}