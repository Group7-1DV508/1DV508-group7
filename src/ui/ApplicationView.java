package ui;

import controls.ApplicationListener;
import controls.ChangeListener;
import de.jensd.fx.fontawesome.AwesomeDude;
import de.jensd.fx.fontawesome.AwesomeIcon;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton.ButtonType;

import functions.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
	
	private Background background = new Background(new BackgroundFill(Color.web("rgb(235,235,235)"), null, null));
	private Background scrollBackground = new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null));
	private CornerRadii radii = new CornerRadii(5);
	private Light.Distant light = new Light.Distant();
	
	private Lighting lighting = new Lighting();
	

	private final JFXButton saveButton = new JFXButton("", new Label("",AwesomeDude.createIconLabel(AwesomeIcon.SAVE, "30")) );
	private final JFXButton helpButton = new JFXButton("", new Label("",AwesomeDude.createIconLabel(AwesomeIcon.QUESTION_SIGN, "30")) ); 
	private final JFXButton loadButton = new JFXButton("", new Label("",AwesomeDude.createIconLabel(AwesomeIcon.FILE_ALT, "30")) ); 

	private EventView eventView;
	private TimelineView timelineView;
	private ApplicationListener appListener;
	// contains all parts of the window (Main view)
	private final VBox view = new VBox();
	// contains ComboBox to choose current timeline, add/delete timeline
	private final HBox timelineButtons = new HBox();
	private final HBox helpBox = new HBox();
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
	private final JFXComboBox<Timeline> chooseTimeline = new JFXComboBox<Timeline>();
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
		view.setBackground(background);
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

		helpButton.setTooltip(helpTo);
		helpButton.setRipplerFill(Color.web("rgb(87,56,97)"));
		helpButton.setBackground(background);
		light.setAzimuth(-20.0);
		lighting.setDiffuseConstant(1.2);
		lighting.setLight(light);
		lighting.setSurfaceScale(3.0);
		helpButton.setEffect(lighting);
		helpButton.setButtonType(ButtonType.RAISED);
		

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
		saveTo.setText("Save Timeline");
		saveTo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		saveButton.setTooltip(saveTo);
		saveButton.setRipplerFill(Color.web("rgb(87,56,97)"));
		saveButton.setBackground(background);
		light.setAzimuth(-20.0);
		lighting.setDiffuseConstant(1.2);
		lighting.setLight(light);
		lighting.setSurfaceScale(3.0);
		saveButton.setEffect(lighting);
		saveButton.setButtonType(ButtonType.RAISED);

		saveButton.setOnAction(ActionEvent  -> {
			  appListener.onTimelineSaved();
		});
    return saveButton;
	}



	private Button loadTimelineButton() {
		loadTo.setText("Load Timeline");
		loadTo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
	
		loadButton.setTooltip(loadTo);
		loadButton.setRipplerFill(Color.web("rgb(87,56,97)"));
		loadButton.setBackground(background);
		light.setAzimuth(-20.0);
		lighting.setDiffuseConstant(1.2);
		lighting.setLight(light);
		lighting.setSurfaceScale(3.0);
		loadButton.setEffect(lighting);
		loadButton.setButtonType(ButtonType.RAISED);

		loadButton.setOnAction(ActionEvent -> {
			appListener.onTimelineLoaded();
		});
		return loadButton;
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
		helpBox.getChildren().clear();
		helpBox.getChildren().add(createHelpButton());
		helpBox.setAlignment(Pos.BOTTOM_LEFT);

		timelineButtons.getChildren().clear();
		timelineButtons.setSpacing(18.0);
		timelineButtons.getChildren().addAll(chooseTimeline,getAddTimelineButton(), saveTimelineButton(), loadTimelineButton(), getDeleteTimelineButton(), getAddEventButton());
		timelineButtons.setAlignment(Pos.CENTER_LEFT);
		timelineButtons.setPadding(new Insets(0,500,0,10));

		temp.getChildren().addAll(timelineButtons,helpBox);

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
		chooseTimeline.setFocusColor(Color.web("rgb(87,56,97)"));
		chooseTimeline.setUnFocusColor(Color.web("rgb(87,56,97)"));
		chooseTimeline.setBackground(background);

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
		content.setBackground(scrollBackground);
		content.setPadding(new Insets(0, 3, 0, 3));
		scrollTimeline.setPrefSize(400, 300);
		scrollTimeline.setBackground(scrollBackground);
		scrollTimeline.setStyle("-fx-background: rgb(223,223,223);");
		scrollTimeline.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, radii, BorderStroke.THIN)));
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