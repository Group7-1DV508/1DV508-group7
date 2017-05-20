package ui;

import controls.ApplicationListener;
import controls.ChangeListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import functions.App;
import functions.Event;
import functions.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.timelineVisuals.EventShape;
import ui.timelineVisuals.ShowEvents;
import ui.timelineVisuals.TimelineInformationBox;
import ui.timelineVisuals.VisualTimeline;

public class ApplicationView implements ChangeListener {

	private Button savey;
	private EventView eventView;
	private TimelineView timelineView;
	private ApplicationListener appListener;
	// contains all parts of the window (Main view)
	private final VBox view = new VBox();
	// contains ComboBox to choose current timeline, add/delete timeline and
	// help button
	private final HBox timelineButtons = new HBox();

	/*
	 * contains all parts within the Current Timeline View Add/Edit/Delete event
	 * buttons, scroll window, current timeline and events visuals
	 */
	private final VBox timelineMainBox = new VBox();
	// scroll window for timeline
	private final ScrollPane scrollTimeline = new ScrollPane();
	//add timeline and addEvent to the same grid
	private final GridPane timelineGrid = new GridPane();
	// buttons for add/edit/delete event
	private final HBox eventButtons = new HBox();
	// comboBox to choose timeline
	private final ComboBox<Timeline> chooseTimeline = new ComboBox<Timeline>();
	// contains all created events for the current timeline
	private ArrayList<EventShape> eventShapes = new ArrayList<EventShape>();
	// contains all months/years for the current timeline
	//private final GridPane currentTimeline = new GridPane();
	// list of months, used to divide the month names to the month boxes
	private final ArrayList<Text> monthTexts = new ArrayList<Text>();
	// contains one month
	private HBox timelineMonth;
	// contains all events at the correct position
	private final ShowEvents eventBox = new ShowEvents();
	// shape that represents an event
	private EventShape eventShape;
	private final TimelineInformationBox informationBox = new TimelineInformationBox();
	private final VisualTimeline currentTimeline = new VisualTimeline(eventBox);

	private static String FILENAMEIN;
	private static String FILENAMEINAddEvent;
	private static String FILENAMEINDeleteEvent;



	/**
	 * Constructor, creates and initialize EventView and TimelineView
	 */
	public ApplicationView() {
		eventView = new EventView();
		timelineView = new TimelineView();
		savey = new Button();
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
	private Button createHelpButton()  {
		Button helpButton = new Button("?");
		helpButton.setStyle("-fx-background-radius: 5em; " + "-fx-min-width: 30px; " + "-fx-min-height: 30px; " + "-fx-max-width: 30px; " + 
		"-fx-max-height: 30px;");
		
		helpButton.setOnAction(new EventHandler<ActionEvent>(){
			  
			@Override public void handle(ActionEvent e) {
		        Stage primaryStage = new Stage();
		        
		        primaryStage.setTitle("Help Window");
		        VBox v1 = new VBox();
		        VBox v2 = new VBox();
		        final  ComboBox<String> comboBox = new ComboBox<>();
		        comboBox.setMaxSize(70, 70);
		        comboBox.getItems().add("quick help");
		        comboBox.getItems().add("Zoom");
		        comboBox.getItems().add("Delete Event");
		        
		        comboBox.setLayoutX(0);
		        comboBox.setLayoutY(0);
		        Label helpText = new Label();
		        helpText.setLayoutX(0);
		        helpText.setLayoutY(25);
		        Label helpText2 = new Label();
		        helpText2.setLayoutX(0);
		        helpText2.setLayoutY(25);
		        comboBox.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						String hej = comboBox.getSelectionModel().getSelectedItem();
						if(hej == "quick help"){
							helpText.setText("nothin here yet");
						}
						String hej1 = comboBox.getSelectionModel().getSelectedItem();
						if(hej1 == "Zoom"){
							helpText.setText("To zoom in right-click with the computer mouse.\n"
									+ "When zoomed in, the month in that year will appear.\n"
									+ " Zooming in one more time will give the user a day by day view with the respective event circles.\n"
									+ "To zoom out left-click with the computer mouse.");
					
					}
						String hej2 = comboBox.getSelectionModel().getSelectedItem();
							if(hej2 == "Delete Event"){
								helpText.setText("Click on the Event circle, which should be deleted.\n"
										+ "The event information box will appear.\n"
										+ "Click on the “ Delete” button in the bottom right corner, next to the “Edit information” button.");
						
						}
						
					}
		        
		        });
		        Button button = new Button("For Dummies");
		       VBox button2 = new VBox();
		       button2.getChildren().add(button);
		       button2.setLayoutX(75);
		       button2.setLayoutY(0);
		       button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TStage primaryStage = new Stage();
					Stage primaryStage = new Stage();
					primaryStage.setTitle("Help for Dummies");
					 //pic add:
				      final File helpAddEventPic = new File("src/helpAddEvent.png");
				      FileInputStream helpAddEventPicIS = null;
					try {
						helpAddEventPicIS = new FileInputStream(helpAddEventPic);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				      Image imageHelpAddEvent = new Image(helpAddEventPicIS);
				      ImageView ivhelpAddEventPic = new ImageView();
				      ivhelpAddEventPic.setImage(imageHelpAddEvent);
				      ivhelpAddEventPic.setPreserveRatio(true);
				      ivhelpAddEventPic.setFitHeight(300);
				      
				       
				       final File helpDeleteEventPic = new File("src/helpDeleteEvent.png");
					      FileInputStream helpDeleteEventPicIS = null;
						try {
							helpDeleteEventPicIS = new FileInputStream(helpDeleteEventPic);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					      Image imageHelpDeleteEvent = new Image(helpDeleteEventPicIS);
					      ImageView ivhelpDeleteEventPic = new ImageView();
					      ivhelpDeleteEventPic.setImage(imageHelpDeleteEvent);
					      ivhelpDeleteEventPic.setPreserveRatio(true);
					      ivhelpDeleteEventPic.setFitHeight(300);

				      //Label used to show help text:
					      FILENAMEINAddEvent ="src/helpAddEvent.txt"; 
				  String contenthelpAdd = null;
					try {
						contenthelpAdd = new String(Files.readAllBytes(Paths.get(FILENAMEINAddEvent)));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String contenthelpAdd2 = contenthelpAdd;
					
					 FILENAMEINAddEvent ="src/helpDeleteEvent.txt"; 
					  String contenthelpDelete = null;
						try {
							contenthelpDelete = new String(Files.readAllBytes(Paths.get(FILENAMEINAddEvent)));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String contenthelpDelete2 = contenthelpDelete;
				      final Label description = new Label();
				      description.setFont(Font.font("Verdana", 12));

				      //Main Categories:  
				      Hyperlink event1 = new Hyperlink("Event"); //event1 since event is used as a variable in: ActionEvent event
				       Hyperlink timeline = new Hyperlink("Timeline");
				       Hyperlink other = new Hyperlink("Other");
				       
				       event1.setFont(Font.font("Verdana", 20));
				       timeline.setFont(Font.font("Verdana", 20));
				       other.setFont(Font.font("Verdana", 20));
				       
				       event1.setLayoutY(0); 
				       timeline.setLayoutY(20);
				       other.setLayoutY(40);
				       
				       //Subcategories for Event:
				       Hyperlink eventHelpAdd = new Hyperlink("");
				       Hyperlink eventHelpDelete = new Hyperlink("");
				       Hyperlink eventHelpEdit = new Hyperlink("");
				       
				       eventHelpAdd.setFont(Font.font("Verdana", 12));
				       eventHelpDelete.setFont(Font.font("Verdana", 12));
				       eventHelpEdit.setFont(Font.font("Verdana", 12));
				       
				       eventHelpAdd.setDisable(true);
				       eventHelpDelete.setDisable(true);
				       eventHelpEdit.setDisable(true);
				          
				       //Subcategories for Timeline:
				       Hyperlink timelineHelpCreate = new Hyperlink("");
				       Hyperlink timelineHelpDelete = new Hyperlink("");
				       
				       timelineHelpCreate.setFont(Font.font("Verdana", 12));
				       timelineHelpDelete.setFont(Font.font("Verdana", 12));
				       
				       timelineHelpCreate.setDisable(true);
				       timelineHelpDelete.setDisable(true);
				         
				      event1.setOnAction(new EventHandler<ActionEvent>() {

				            @Override
				            public void handle(ActionEvent event) {
				                if(eventHelpAdd.getText() == ""){
				              	 eventHelpAdd.setLayoutY(31);
				               	 eventHelpAdd.setText("How to add event");
				               	 eventHelpAdd.setDisable(false);
				               	 eventHelpDelete.setLayoutY(43);
				               	 eventHelpDelete.setText("How to delete event");
				               	 eventHelpDelete.setDisable(false);
				               	 eventHelpEdit.setLayoutY(55);
				               	 eventHelpEdit.setText("How to edit an event");
				               	 eventHelpEdit.setDisable(false);
				               	 
				               	 timeline.setLayoutY(70);
				               	 other.setLayoutY(90);
				               	 
				               	 timelineHelpCreate.setText("");
				            	     timelineHelpCreate.setDisable(true);
				                   timelineHelpDelete.setText("");
				              	 timelineHelpDelete.setDisable(true);
				              	  
				              	  }
				               
				               else {
				                eventHelpAdd.setText("");
				             	  eventHelpAdd.setDisable(true);
				             	  eventHelpDelete.setText("");
				             	  eventHelpDelete.setDisable(true);
				              	 eventHelpEdit.setText("");
		                   	    eventHelpEdit.setDisable(true);
				             	 
				             	  timeline.setLayoutY(20);
				             	  other.setLayoutY(40);
				               }
				               }  
				             });
				           
				          timeline.setOnAction(new EventHandler<ActionEvent>() {

				              @Override
				              public void handle(ActionEvent event) {
				              	 if(timelineHelpCreate.getText() == ""){
				              		  //"closing" the event help if "open"
				              		  eventHelpAdd.setText("");
				                    	  eventHelpAdd.setDisable(true);
				                    	  eventHelpDelete.setText("");
				                    	  eventHelpDelete.setDisable(true);
				                    	  eventHelpEdit.setText("");
				                    	  eventHelpEdit.setDisable(true);
				                    	  timeline.setLayoutY(20);
				                    	  
				                    	 timelineHelpCreate.setLayoutY(55);
				                    	 timelineHelpCreate.setText("How to create a timeline"); 
				                    	 timelineHelpCreate.setDisable(false);
				                  	 
				                    	 timelineHelpDelete.setLayoutY(67);
				                  	 timelineHelpDelete.setText("How to delete timeline");
				                  	 timelineHelpDelete.setDisable(false);
				                  	 
				                  	other.setLayoutY(84);
				                    	  
				                    	  
				                    	  
				              		 
				                  	  
				                  	  }
				                   
				                   else {
				               
				                  	  timelineHelpCreate.setText("");
				                  	  timelineHelpCreate.setDisable(true);
				                    	  timelineHelpDelete.setText("");
				                    	  timelineHelpDelete.setDisable(true);
				                    	 
				                    	  timeline.setLayoutY(20);
				                    	  other.setLayoutY(40);
				                   }
				              }  
				            });
				          
				          //events for subcategories
				          eventHelpAdd.setOnAction(new EventHandler<ActionEvent>() {

				              @Override
				              public void handle(ActionEvent event)  {
				              	String evenHelpAddString = contenthelpAdd2;
				              	if(description.getText() == evenHelpAddString){
				              		description.setText("");;
				              		v1.getChildren().remove(ivhelpAddEventPic);
				              		v2.getChildren().remove(ivhelpDeleteEventPic);
				                  	  }
				              	else{
				              		description.setLayoutX(140);
				              		description.setLayoutY(10);
				              		description.setText(evenHelpAddString);
				              		v1.getChildren().add(ivhelpAddEventPic);
				              		v2.getChildren().remove(ivhelpDeleteEventPic);
				              		v1.setLayoutX(200);
				              		v1.setLayoutY(370);
				              		
				              		
				              	}
				              }});
				        eventHelpDelete.setOnAction(new EventHandler<ActionEvent>() {

				              @Override
				              public void handle(ActionEvent event)  {
				              	String evenHelpDeleteString = contenthelpDelete2;
				              	if(description.getText() == evenHelpDeleteString){
				              		description.setText("");;
				              		v1.getChildren().remove(ivhelpAddEventPic);
				              		v2.getChildren().remove(ivhelpDeleteEventPic);
				              		description.setText("");;
				              	
				                  	  }
				              	else{
				              		description.setLayoutX(140);
				              		description.setLayoutY(10);
				              		description.setText(evenHelpDeleteString);
				              		v1.getChildren().remove(ivhelpAddEventPic);
				              		v2.getChildren().add(ivhelpDeleteEventPic);
				              	    v2.setLayoutX(200);
				              	    v2.setLayoutY(370);
				              		
				              		
				              	}
				              }}); 
					    Group root = new Group();
					    root.getChildren().addAll(v1, v2);
					    root.getChildren().addAll(event1, timeline, other);
					    root.getChildren().addAll(eventHelpAdd, eventHelpDelete);
					    root.getChildren().addAll(timelineHelpCreate, timelineHelpDelete);
					    root.getChildren().addAll(description);
					    primaryStage.setScene(new Scene(root, 1267, 700));
					primaryStage.show();
				}
		       });
		     
				 Group root = new Group();
				 
		          root.getChildren().addAll(button2);
		          root.getChildren().addAll(comboBox);
		          root.getChildren().addAll(helpText);
		          root.getChildren().addAll(helpText2);
		          primaryStage.setScene(new Scene(root, 200, 200));
		          primaryStage.show();
			}
		});
		return helpButton;
	}
	/* Creates a button which saves a given
	 * timeline to a file path chosen by
	   the user through the fileChooser.*/
	
	private Button saveTimelineButton() {
		 savey = new Button("Save Timeline");
		savey.setPrefSize(120, 30);
	

		savey.setOnAction(ActionEvent  -> {
	
			appListener.onTimelineSaved();
		});
		return savey;
	}
	
	
	
	private Button loadTimelineButton() {
		Button loaded = new Button("Load Timeline");
		loaded.setPrefSize(120, 30);
		
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
		timelineButtons.getChildren().clear();
		timelineButtons.setAlignment(Pos.CENTER);
		timelineButtons.setSpacing(20.0);
		timelineButtons.getChildren().addAll(chooseTimeline, getAddTimelineButton(), getDeleteTimelineButton(),
				createHelpButton());
		return timelineButtons;
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
		eventButtons.getChildren().addAll(saveTimelineButton(), loadTimelineButton());
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
		timelineMainBox.getChildren().addAll(timelineBox(), eventButtonsBox());
		return timelineMainBox;
	}

	

	
	
	/**
	 * Creates a ScrollPane for the timeline and adds the current timeline and
	 * the events pane to it
	 */
	
	private GridPane timelineBox() {  
		VBox content = new VBox();
		content.setPadding(new Insets(0, 3, 0, 3));
		timelineGrid.setPrefSize(800, 400);
		timelineGrid.setGridLinesVisible(true);
		content.setAlignment(Pos.TOP_CENTER);
		content.getChildren().addAll(informationBox,getAddEventButton(),timelineScrollBox());
		timelineGrid.add(content, 0, 1);

		return timelineGrid;
	}
	
	private ScrollPane timelineScrollBox() {  
		VBox content = new VBox();
		content.setPadding(new Insets(0, 4, 0, 4));
		scrollTimeline.setPrefSize(5000, 400);
		scrollTimeline.setStyle("-fx-background-color:transparent;");
		content.getChildren().addAll(currentTimeline, eventBox);
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
			getDeleteTimelineButton().setDisable(false);
			eventView.setDisable(false);
			savey.setDisable(false);
		}
		else {
			clearTimelineBox();
			getDeleteTimelineButton().setDisable(true);
			eventView.setDisable(true);
			savey.setDisable(true);
			
		}
		
	} 

	@Override
	public void onNewTimelineSelected(Timeline current) {
		currentTimeline.createVisualTimeline(current);
		//showYear(current);
		
	}

	@Override
	public void onEditTimeline(Timeline current) {
		currentTimeline.updateVisualTimeline();

	}

	@Override
	public void onEditEvent(Timeline current) {
		currentTimeline.updateVisualTimeline();

	}

	

}