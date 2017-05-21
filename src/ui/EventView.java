package ui;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import controls.EventListener;
import de.jensd.fx.fontawesome.AwesomeDude;
import de.jensd.fx.fontawesome.AwesomeIcon;
import functions.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class EventView {

	private EventListener eventListener;

	// TextArea - Add Event Window
	private JFXTextArea description;

	// TextFields - Add Event Window
	private JFXTextField name;

	// Buttons
	private JFXButton addEvent  = new JFXButton ();
	private JFXButton ok = new JFXButton("Finish");
	private JFXButton cancel = new JFXButton("Cancel");
	private JFXButton editEvent = new JFXButton("Edit Info");
	private JFXButton delete = new JFXButton("Delete event");
	
	// Texts
	private Text titleText;
	private Text decText;
	private Text dateStartText;
	private Text dateEndText;
	
	// Combo boxes
	private JFXTimePicker JtimeStart = new JFXTimePicker();
	//private ComboBox<String> timeStart = new ComboBox<String>();
	private JFXTimePicker JtimeEnd = new JFXTimePicker();
	//private ComboBox<String> timeEnd = new ComboBox<String>();

	// Labels - View Event Window
	private Label title = new Label("Title:");
	private Label eventStart = new Label("Event start:");
	private Label eventEnd = new Label("Event end:");
	private Label des = new Label("Description:");

	// Other
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy (GG)  HH:mm");
	private JFXDatePicker checkInDatePickerStart;
	private JFXDatePicker checkInDatePickerEnd;
	private final Converter converter = new Converter();
	
	private LocalDate timelineStart;
	private LocalDate timelineEnd;

	/**
	 * Update the EventListener variable with the EventListener given as input
	 *
	 * @param eventList,
	 *            (EventListener)
	 */
	public void addListener(EventListener eventList) {
		eventListener = eventList;
	}
	
	public void setTimelineStartEnd(LocalDate start, LocalDate end) {
		timelineStart = start;
		timelineEnd = end;
	}

	/**
	 * method to create and return the add Event button,
	 *
	 * @return addEvent button
	 */

	public JFXButton getAddEventButton() {
		addEvent = new JFXButton("", new Label("",AwesomeDude.createIconLabel(AwesomeIcon.CALENDAR, "20")) ); 
		addEvent.setMaxSize(40, 40);
		addEvent.setMinSize(40, 40);
		addEvent.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.FLAT);
		addEvent.setRipplerFill(Color.web("rgb(87,56,97)"));
		addEvent.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));
		

		/*
		 * when Add Event button is clicked a popup window is created where the
		 * user can provide information about the Event, User has to provide
		 * Name, Description and Start Date, End Date however is optional.
		 */
		addEvent.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				final Stage eventWindow = new Stage();
				GridPane textFieldsStart = createAddEventWindow();
				eventWindow.setTitle("Add event window");
				eventWindow.setResizable(false);

				/*
				 * When "Ok" button is clicked, textfields are fetched and
				 * converted to String and LocalDateTime then application
				 * listener is calling the onAddEvent-method
				 */
				ok.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						/*
						 * If Name, Description or Start Date fields are empty
						 * an Error alert shows to the user
						 */
						if (isNeededFieldEmpty()) {
							createAlertError("Empty fields", "Name, description and start Date can't be empty!");
						} // End of alert for empty fields
						else {
							LocalDateTime startTime = LocalDateTime.of(checkInDatePickerStart.getValue(),
									LocalTime.parse(JtimeStart.getValue() + ":00"));
							String eventname = name.getText();
							String eventdescrip = description.getText();
							/*
							 * If the event doesn't have End Date an non
							 * duration Event is created
							 */
							if (isNotDurationEvent()) {
								// Create event
								if (eventListener.onAddEvent(eventname, eventdescrip, startTime)) {
									eventWindow.close();
								} // End for successfully creating non duration
									// event

								// Check if event is out of timeline
								else {
									createAlertError("Error in chosing time", "It appears your are trying to create an event outside of timeline!");
								} // End of alert for out of timeline event
							} // End of creating non duration event

							/*
							 * If the Event has End Time an Event with duration
							 * is created
							 */
							else {
								// Event is with duration, end time is created
								LocalDateTime endTime = LocalDateTime.of(checkInDatePickerEnd.getValue(),
										LocalTime.parse(JtimeEnd.getValue() + ":00"));
								// Check if start time is later than end time
								if (startTime.compareTo(endTime) > 0) {
									createAlertError("Error in event dates", "Start date has to be earlier than end date!");
								} // End of checking if start date is earlier than end date
								
								// Event has correct start and end date, create event with duration 
								else  {
									if (eventListener.onAddEventDuration(eventname, eventdescrip, startTime, endTime)) {
										eventWindow.close();
									} // End of successfully creating event with
										// duration
									else {
										createAlertError("Error in chosing time", "It appears your are trying to create an event outside of timeline!");
									} // End of alert for event out of timeline
								} // End of successfully creating event with
									// duration
							} // End of creating event with duration
						} // End of creating event
					} // End of handle() method
				}); // End of setOnAction for addEvent button

				/*
				 * when cancel button is clicked the popup window is closed
				 */
				cancel.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						eventWindow.close();
					}
				});

				/*
				 * Creates a Scene and builds the Add Event popup window
				 */
				Scene eventScene = new Scene(textFieldsStart);
				eventWindow.setScene(eventScene);
				eventWindow.initModality(Modality.APPLICATION_MODAL);
				eventWindow.showAndWait();
				eventWindow.setAlwaysOnTop(true);
				textFieldsStart.requestFocus();

			}
		});
		return addEvent;

	}

	/**
	 * Method to disable addEvent when no timelines are loaded
	 * 
	 * @param notShown
	 *            true if button should be disabled
	 */
	public void setDisable(boolean notShown) {
		addEvent.setDisable(notShown);
	}

	/**
	 * Creates and returns Edit Event button and it's functionalities
	 * 
	 * @param e
	 *            event to be edited
	 * @return editEvent button
	 */
	public JFXButton EditButton(Event e) {
		// Button parameters
		editEvent.setMinSize(80, 30);
		editEvent.setFont(Font.font("Verdana", 15));
		editEvent.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));
		editEvent.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.FLAT);
		editEvent.setRipplerFill(Color.web("rgb(87,56,97)"));
		editEvent.setTranslateX(10);
		editEvent.setTranslateY(30);
		editEvent.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				setDisableFields(false);

				ok.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						/*
						 * Checking for empty fields or unfinished date choosing
						 * example, user picked end time, but not end date.
						 */
						if (isNeededFieldEmpty()) {
							createAlertError("Empty fields", "Name, description and start Date can't be empty!");
						} // End of checking if fields are empty

						/*
						 * If all fields were not empty, event editing is
						 * possible
						 */
						else {
							LocalDateTime startTime = LocalDateTime.of(checkInDatePickerStart.getValue(),
									LocalTime.parse(JtimeStart.getValue() + ":00"));
				                String eventname = name.getText();
				                String eventdescrip = description.getText();
							
				            // Event is not a duration event, it's not attempted to be converted, then
				            // edit old event
							if (!e.isDuration() && checkInDatePickerEnd.getValue() == null) {
			
				                if (eventListener.onEditEvent(eventname, eventdescrip, startTime)) {
				
				                	setNewTextsDuration(startTime, null);
					                setDisableFields(true);
				                }
				                else {
				                	createAlertError("Error in chosing time", "It appears your are trying to create an event outside of timeline!");
								}
							} // End of editing of event from non duration to
								// non duration
							else if (e.isDuration() && checkInDatePickerEnd.getValue() == null) {
								if(eventListener.onEditEvent(eventname, eventdescrip, startTime)) {
									setNewTextsDuration(startTime, null);
									setDisableFields(true);
								} // End of add of new event
				                else {
				                	createAlertError("Error in chosing time", "It appears your are trying to create an event outside of timeline!");
								} // End of alert for out of timeline event

							} // End of editing event from duration to non
								// duration

							else if (!e.isDuration() && checkInDatePickerEnd.getValue() != null) {
								LocalDateTime endTime = LocalDateTime.of(checkInDatePickerEnd.getValue(),
										LocalTime.parse(JtimeEnd.getValue() + ":00"));							
				           
								
				                if (startTime.compareTo(endTime) > 0) {
				                	 createAlertError("Error in event dates", "Start date has to be earlier than end date!");
					                } // End of alert for start date later than end date for event
				                
				                else {
									if (eventListener.onEditEventDuration(eventname, eventdescrip, startTime, endTime)) {
										setNewTextsDuration(startTime, endTime);
							            setDisableFields(true);
									} // End of add of new event
					                else {
					                	createAlertError("Error in chosing time", "It appears your are trying to create an event outside of timeline!");
									}// End of event out of timeline alert
				                } // End of checking end time
							}// End of edit event from non duration to duration
							else {
			            	// Get end date, since it's event with duration  
							LocalDateTime endTime = LocalDateTime.of(checkInDatePickerEnd.getValue(),
									LocalTime.parse(JtimeEnd.getValue() + ":00"));
			
			                
			                if (startTime.compareTo(endTime) > 0) {
			                	createAlertError("Error in event dates", "Start date has to be earlier than end date!");
			                } // End of alert for start date later than end date for event
			
			                else {
			                   if(eventListener.onEditEventDuration(eventname, eventdescrip, startTime, endTime)) {
			                	   
			                	   	setNewTextsDuration(startTime, endTime);
					                setDisableFields(true);
			                    } // End of editing event without duration
			                  else {
			                	  createAlertError("Error in chosing time", "It appears your are trying to create an event outside of timeline!");
			                  } // End of alert for event outside of timeline
			                } // End of editing event from duration to duration
			              } // End of editing events
			            } // End of event editing
			          } // End of handle() method
					}); // End of setOnAction method
				/*
				 * when cancel button is clicked the popup window is closed
				 */
				cancel.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						setDisableFields(true);
					} // End of handle() method for cancel
				}); // End of setOnAction for cancel button
			} // End of handle() for editEvent button
		}); // End of setOnAction for editEvent button

		return editEvent;
	}

	/**
	 * Delete button responsible for delete selected event
	 * 
	 * @param e
	 *            event to be deleted
	 * @param s
	 *            closes event information window of delete event
	 * @return button with set action on it.
	 */
	public JFXButton getDeleteButton(Event e, Stage s) {
		delete.setMinSize(80, 30);
		delete.setFont(Font.font("Verdana", 15));
		delete.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.FLAT);
		delete.setRipplerFill(Color.web("rgb(87,56,97)"));
		delete.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));

		delete.setTranslateX(110);
		delete.setTranslateY(-20);
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// Opens alert to ask if user really wants to delete
				// the event
				Alert confirmation = new Alert(AlertType.CONFIRMATION);
				confirmation.setTitle("Deleting event");
				confirmation.setContentText("Are you sure you want to delete this event?");
				Optional<ButtonType> result = confirmation.showAndWait();
				// if user chose ok, event is deleted, information
				// window is closed as it is not needed
				if (result.get() == ButtonType.OK) {
					if (eventListener.onDeleteEvent()) {
						confirmation.close();
						s.close();
					}
				}
				// user chose cancel, so alert window is closed
				else {
					confirmation.close();
				}

			}

		});
		return delete;
	}

	/**
	 * Help method to create popup window, initializes all TextFields, Labels
	 * and Buttons for the window, and then add it all to the GridPane
	 *
	 * @return GridPane
	 */

	private GridPane createAddEventWindow() {
		
		addEvent = new JFXButton("Event", new Label("",AwesomeDude.createIconLabel(AwesomeIcon.CALENDAR, "30")) );
		
		addEvent.setRipplerFill(Color.web("rgb(87,56,97)"));
		
		 
		checkInDatePickerStart = new JFXDatePicker();
		checkInDatePickerEnd = new JFXDatePicker();
		checkInDatePickerStart.setDefaultColor(Color.web("rgb(87,56,97)"));
		checkInDatePickerEnd.setDefaultColor(Color.web("rgb(87,56,97)"));
		GridPane pane = new GridPane();

		// TextFields,TextAreas initialized
		name = new JFXTextField();

		name.setUnFocusColor(Color.web("rgb(87,56,97)"));
		name.setFocusColor(Color.web("rgb(87,56,97)"));
		name.setPromptText("Event name");
		name.setFont(new Font("Times new Roman", 20));
		name.setPrefWidth(446);

		description = new JFXTextArea();

		description.setUnFocusColor(Color.web("rgb(87,56,97)"));
		description.setFocusColor(Color.web("rgb(87,56,97)"));
		description.setPromptText("Event information");
		description.setFont(Font.font("Times new Roman", 20));
		description.setPrefSize(446, 200);
		description.setWrapText(true);

		// In case fields are disabled
		setDisableFields(false);
		datePickerSettings(checkInDatePickerStart);
		checkInDatePickerStart.setValue(timelineStart.minusDays(1));
		datePickerSettings(checkInDatePickerEnd);
		checkInDatePickerEnd.setValue(timelineStart.minusDays(1));
		
		
		
     	/* Limit the number of characters*/
     	final int nameMAX_CHARS = 40;
     	name.setTextFormatter(new TextFormatter<String>(change -> 
        change.getControlNewText().length() <= nameMAX_CHARS ? change : null));
     	final int desMAX_CHARS = 300;
     	description.setTextFormatter(new TextFormatter<String>(change -> 
            change.getControlNewText().length() <= desMAX_CHARS ? change : null));
     	
     	
     	// Combo box for start event times
     	JtimeStart.setIs24HourView(true);
		JtimeStart.setDefaultColor(Color.web("rgb(87,56,97)"));
		// Combo box for end event times
		JtimeEnd.setIs24HourView(true);
		JtimeEnd.setDefaultColor(Color.web("rgb(87,56,97)"));
		// Date pickers for start event
		checkInDatePickerStart.setStyle("-fx-font: 16 timesnewroman;");
		checkInDatePickerStart.setPromptText("Event start");
		

		// Date pickers for end event
		checkInDatePickerEnd.setStyle("-fx-font: 16 timesnewroman;");
		checkInDatePickerEnd.setPromptText("Event end");
		

		// Buttons initialized
		ok.setPrefSize(135, 35);
		ok.setFont(Font.font("Times new Roman", 20));
		ok.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.FLAT);
		ok.setRipplerFill(Color.web("rgb(87,56,97)"));
		ok.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));


		cancel.setPrefSize(135, 35);
		cancel.setTranslateX(175);
		cancel.setFont(Font.font("Times new Roman", 20));
		cancel.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.FLAT);
		cancel.setRipplerFill(Color.web("rgb(87,56,97)"));
		cancel.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));


		// HBox initialized

		HBox hb = new HBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		HBox hb4 = new HBox();

		hb.getChildren().addAll(checkInDatePickerStart, JtimeStart);
		hb.setAlignment(Pos.CENTER);
		HBox.setMargin(checkInDatePickerStart, new Insets(5));
		HBox.setMargin(checkInDatePickerEnd, new Insets(5));
		HBox.setMargin(JtimeStart, new Insets(5));
		HBox.setMargin(JtimeEnd, new Insets(5));
		hb1.getChildren().addAll(checkInDatePickerEnd, JtimeEnd);
		hb1.setAlignment(Pos.CENTER);
		hb2.getChildren().addAll(ok, cancel);
		hb3.getChildren().add(name);
		hb4.getChildren().add(description);

		hb.setPadding(new Insets(0, 0, 10, 0));
		hb1.setPadding(new Insets(0, 0, 30, 0));
		hb3.setPadding(new Insets(0, 0, 10, 0));
		hb4.setPadding(new Insets(0, 0, 10, 0));

		// Add initialized Nodes to the GridPane
		pane.setPadding(new Insets(20));
		pane.add(hb3, 0, 0);
		pane.add(hb4, 0, 1);
		pane.add(hb, 0, 2);
		pane.add(hb1, 0, 3);
		pane.add(hb2, 0, 4);

		return pane;
	}

	/**
	 * Creates a window that displays information about certain event
	 * 
	 * @param e
	 *            event that information is displayed about
	 */
	public void ViewEventInfo(Event e) {
		final Stage eventWindow = new Stage();

		checkInDatePickerStart = new JFXDatePicker();
		checkInDatePickerEnd = new JFXDatePicker();
		VBox window = new VBox();
		window.setSpacing(20);
		window.setPrefSize(200, 200);

		Label info = new Label("Information");
		info.setFont(Font.font("Verdana", 25));
		info.setUnderline(true);
		info.setTextFill(Color.BLACK);
		info.setAlignment(Pos.CENTER);

		title.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		title.setTranslateX(8);
		titleText = new Text("  " + e.getEventName());
		titleText.setFont(Font.font("Verdana", 15));
		titleText.setWrappingWidth(250);
		titleText.setTranslateY(-13);

		String formattedStringS = e.getEventStart().format(format);

		eventStart.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		eventStart.setTranslateX(8);
		eventStart.setTranslateY(-20);

		dateStartText = new Text(formattedStringS);
		dateStartText.setFont(Font.font("Verdana", 15));
		;
		dateStartText.setWrappingWidth(250);
		dateStartText.setTranslateY(-33);
		dateStartText.setTranslateX(8);

		des.setFont(Font.font("Verdana", FontWeight.BOLD, 17));
		des.setTranslateX(8);
		des.setTranslateY(-40);

		decText = new Text(e.getEventDescription());
		decText.setWrappingWidth(250);
		decText.setFont(Font.font("Verdana", 15));
		;
		decText.setTranslateX(8);
		decText.setTranslateY(-53);
		
		dateEndText = new Text(" ");
		dateEndText.setFont(Font.font("Verdana", 15));
		dateEndText.setTranslateY(-48);
		dateEndText.setTranslateX(8);
		
		eventEnd.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
		eventEnd.setTranslateX(8);
		eventEnd.setTranslateY(-35);

		if (e.getEventEnd() != null) {
			String formattedStringE = e.getEventEnd().format(format);
			dateEndText.setText(formattedStringE);
		}
		createEditEventWindow(e);
		window.getChildren().addAll(info, title, titleText, eventStart, dateStartText, eventEnd, dateEndText, des, decText, EditButton(e),getDeleteButton(e, eventWindow));

		HBox all = new HBox();
		all.setPrefSize(550, 190);
		all.setPadding(new Insets(10, 10, 10, 10));
		
		all.getChildren().addAll(window ,createEditEventWindow(e));
		Scene eventScene = new Scene(all);
		eventWindow.setTitle("Event");
		eventWindow.setScene(eventScene);
		eventWindow.initModality(Modality.APPLICATION_MODAL);
		eventWindow.showAndWait();

	}

	/**
	 * Responsible for getting information about event in edit event window
	 * 
	 * @param e
	 *            event to be edited
	 * @return a window
	 */
	public VBox createEditEventWindow(Event e) {
		
		
		
		checkInDatePickerStart.setDefaultColor(Color.web("rgb(87,56,97)"));
		checkInDatePickerEnd.setDefaultColor(Color.web("rgb(87,56,97)"));
		VBox editeHolder = new VBox();
		editeHolder.setPrefSize(400, 400);
		editeHolder.setTranslateX(50);
		name = new JFXTextField(e.getEventName());

		name.setUnFocusColor(Color.web("rgb(87,56,97)"));
		name.setFocusColor(Color.web("rgb(87,56,97)"));
		description = new JFXTextArea(e.getEventDescription());
		description.setUnFocusColor(Color.web("rgb(87,56,97)"));
		description.setFocusColor(Color.web("rgb(87,56,97)"));
		
		Label nameL = new Label("Name");
		Label descriptionL = new Label("Description");

		LocalDateTime startDate = e.getEventStart();
		datePickerSettings(checkInDatePickerStart);
		checkInDatePickerStart.setValue(startDate.toLocalDate());
    
		checkInDatePickerStart.setMinSize(150, 30);
		checkInDatePickerEnd.setMinSize(150, 30);

		Label yearL = new Label("Start Date");
		JtimeStart.setIs24HourView(true);
		JtimeStart.setDefaultColor(Color.web("rgb(87,56,97)"));
		JtimeStart.setValue(startDate.toLocalTime());
		
		JtimeEnd.setIs24HourView(true);
		JtimeEnd.setDefaultColor(Color.web("rgb(87,56,97)"));
		
		Label hourL = new Label("Start Time");

		HBox h2 = new HBox();
		name.setDisable(true);
		description.setDisable(true);
		checkInDatePickerStart.setDisable(true);
		checkInDatePickerEnd.setDisable(true);
		JtimeStart.setDisable(true);

		if (e.getEventEnd() != null) {
			LocalDateTime endDate = e.getEventEnd();
			datePickerSettings(checkInDatePickerEnd);
			checkInDatePickerEnd.setValue(endDate.toLocalDate());
			JtimeEnd.setValue(endDate.toLocalTime());
		

		} 

		h2.getChildren().addAll(checkInDatePickerEnd, JtimeEnd);
		checkInDatePickerEnd.setDisable(true);
		JtimeEnd.setDisable(true);

		GridPane h1 = new GridPane();
		Label yearLE = new Label("End Date");
		yearLE.setFont(Font.font("Verdana", 13));
		Label hourLE = new Label("End Time");
		hourLE.setFont(Font.font("Verdana", 13));

		nameL.setFont(Font.font("Verdana", 17));
		descriptionL.setFont(Font.font("Verdana", 17));
		yearL.setFont(Font.font("Verdana", 13));
		hourL.setFont(Font.font("Verdana", 13));

		VBox vb1 = new VBox();
		VBox vb2 = new VBox();
		VBox vb3 = new VBox();
		VBox vb4 = new VBox();
		VBox vb5 = new VBox();
		VBox vb6 = new VBox();
		VBox vb7 = new VBox();
		VBox vb8 = new VBox();
		VBox vb9 = new VBox();
		VBox vb10 = new VBox();

		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();

		vb1.getChildren().addAll(nameL, name);
		vb1.setPadding(new Insets(10, 10, 10, 10));

		vb2.getChildren().addAll(descriptionL, description);
		vb2.setPadding(new Insets(10, 10, 10, 10));

		vb3.getChildren().addAll(yearL, checkInDatePickerStart);
		vb3.setPadding(new Insets(0, 5, 0, 0));

		vb6.getChildren().addAll(hourL, JtimeStart);
		vb6.setPadding(new Insets(0, 5, 0, 0));

		vb7.getChildren().addAll(yearLE, checkInDatePickerEnd);
		vb7.setPadding(new Insets(0, 5, 0, 0));

		vb10.getChildren().addAll(hourLE, JtimeEnd);
		vb10.setPadding(new Insets(0, 5, 0, 0));

		hb1.getChildren().addAll(vb3, vb4, vb5, vb6);
		hb1.setPadding(new Insets(5, 5, 5, 10));

		hb2.getChildren().addAll(vb7, vb8, vb9, vb10);
		hb2.setPadding(new Insets(5, 5, 5, 10));

		h1.add(vb1, 0, 1);
		h1.add(vb2, 0, 2);
		h1.add(hb1, 0, 3);
		h1.add(hb2, 0, 4);


		// Button settings
		ok.setDisable(true);
		ok.setMinSize(80, 30);
		ok.setFont(Font.font("Verdana", 15));
		cancel.setDisable(true);
		cancel.setMinSize(80, 30);
		cancel.setFont(Font.font("Verdana", 15));

		hb3.setPadding(new Insets(10, 0, 10, 10));
		hb3.getChildren().addAll(ok, cancel);
		hb3.setSpacing(10);
		hb3.setTranslateY(20);
		editeHolder.getChildren().addAll(h1, hb3);
		editeHolder.setMinSize(700, 500);
		return editeHolder;

	}

	/**
	 * help method to check if any fields that are needed to create an Event is
	 * empty (Name, Description, Year, Month, Day and Hour)
	 *
	 * @return boolean, true if needed fields are empty otherwise false
	 */
	private boolean isNeededFieldEmpty() {
		// Check if name or description is empty
		if (name.getText().isEmpty() || description.getText().isEmpty()) {
			System.out.println("name and desc");
			return true;
			// check if date picker for start is not selected or if time for
			// start
			// is not selected
		} else if (checkInDatePickerStart.getValue() == null || JtimeStart.getValue() == null) {
			System.out.println("start date / time");
			return true;
		}
		// check if date picker for end value is selected, but time is not
		// selected
		// or if date picker for end is not selected, but time is selected
		else if ((checkInDatePickerEnd.getValue() != null && checkInDatePickerEnd.getValue().compareTo(timelineStart)>=0 && JtimeEnd.getValue() == null)
				|| (checkInDatePickerEnd.getValue() == null && JtimeEnd.getValue() != null)) {
			return true;
		}
		 else {
			return false;
		}
	}

	/**
	 * help method to check if the Event to be created is a duration event or
	 * not
	 *
	 * @return boolean, true if it is not a duration event otherwise false
	 */

	private boolean isNotDurationEvent() {
		if (checkInDatePickerEnd.getValue() == null || JtimeEnd.getValue() == null) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * Help method to create an alert of type error
	 * @param name name of the error
	 * @param message messaged displayed, describing the error
	 */
	private void createAlertError(String name, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(name);
        alert.setHeaderText(message);
        alert.show();
	}

	
	private void setNewTextsDuration(LocalDateTime startTime, LocalDateTime endTime) {
        titleText.setText("  " + name.getText());
        decText.setText(description.getText());
        dateStartText.setText(startTime.format(format));
        if (endTime == null) {
        	dateEndText.setText("");
        }
        else {
        	dateEndText.setText(endTime.format(format));
        }
	}
	
	/**
	 * Help method to disable fields when editing event
	 * @param b false if fields are active
	 */
	private void setDisableFields (boolean b) {
		name.setDisable(b);
		description.setDisable(b);
		
		checkInDatePickerStart.setDisable(b);
		checkInDatePickerEnd.setDisable(b);
		
		JtimeStart.setDisable(b);
		JtimeEnd.setDisable(b);
		
		ok.setDisable(b);
		cancel.setDisable(b);
	}
	private void datePickerSettings(DatePicker dp) {
		
		final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
		     public DateCell call(final DatePicker datePicker) {
		         return new DateCell() {
		             @Override public void updateItem(LocalDate item, boolean empty) {
		                 super.updateItem(item, empty);

		                 if (item.compareTo(timelineStart)<0 || item.compareTo(timelineEnd)>0) {
		                     setDisable(true);
		                 }
		                
		             }
		         };
		     }
		 };
		dp.setDayCellFactory(dayCellFactory);
		dp.setConverter(converter);
		dp.setShowWeekNumbers(true);
	}	
	 

	private class Converter extends StringConverter<LocalDate> {

		String pattern = "GGyyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		@Override
		public String toString(LocalDate date) {
			//System.out.println(date.toString());
			//System.out.println(timelineStart.toString());
			if (date!= null && date.compareTo(timelineStart) < 0) {
				return "";
			} 
			else if (date != null) {
				return formatter.format(date);
			}
			else {
				return "";
			}
				
			
		}

		@Override
		public LocalDate fromString(String string) {
			if (string != null && !string.isEmpty()) {
				return LocalDate.parse(string, formatter);
			} else {
				return null;
			}
		}

	}

}