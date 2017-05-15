package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import controls.EventListener;
import functions.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventView {

	private EventListener eventListener;

	// TextArea - Add Event Window
	private TextArea description;

	// TextFields - Add Event Window
	private TextField name;


	// Buttons
	private Button addEvent = new Button("Add Event");
	private Button ok = new Button("Finish");
	private Button cancel = new Button("Cancel");
	private Button editEvent = new Button("Edit Info");
	private Button delete = new Button("Delete event");
	private Text titleText;
	private Text decText;
	private Text dateStartText;
	private Text dateEndText;
	private ComboBox<String> timeStart = new ComboBox<String>();
	private ComboBox<String> timeEnd = new ComboBox<String>();

	//Labels - View Event Window
	private Label title = new Label("Title:");
	private Label eventStart = new Label("Event start:");
	private Label eventEnd = new Label("Event end:");
	private Label des = new Label("Description:");
	
	// Other
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  HH:mm");
	private DatePicker checkInDatePickerStart = new DatePicker();
	private DatePicker checkInDatePickerEnd = new DatePicker();
	


	/**
	 * Update the EventListener variable with the EventListener given as input
	 *
	 * @param eventList, (EventListener)
	 */
	public void addListener(EventListener eventList) {
		eventListener = eventList;
	}

	/**
	 * method to create and return the add Event button,
	 *
	 * @return addEvent button
	 */

	public Button getAddEventButton() {
		addEvent.setMinSize(120, 30);

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
							Alert emptyFieldError = new Alert(Alert.AlertType.ERROR,
									"Name, Description and Start Date can't be empty.");
							emptyFieldError.showAndWait();
						} // End of alert for empty fields
						else {
							LocalDateTime startTime = createLocalDateTime(checkInDatePickerStart.getValue().getYear()+"", 
									checkInDatePickerStart.getValue().getMonthValue()+"",
									checkInDatePickerStart.getValue().getDayOfMonth()+"",
									timeStart.getValue());
							String eventname = name.getText();
							String eventdescrip = description.getText();
							/*
							 * If the event doesn't have End Date an non duration
							 * Event is created
							 */
							if (isNotDurationEvent()) {
								// Create event
								if (eventListener.onAddEvent(eventname, eventdescrip, startTime)) {
									eventWindow.close();
								} // End for successfully creating non duration event
								
								// Check if event is out of timeline
								else {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Error in chosing time");
									alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
									alert.show();
								} // End of alert for out of timeline event
							} // End of creating non duration event
							
							/*
							 * If the Event has End Time an Event with duration is
							 * created
							 */
							else {
								// Event is with duration, end time is created
								LocalDateTime endTime = createLocalDateTime(checkInDatePickerEnd.getValue().getYear()+"", 
										checkInDatePickerEnd.getValue().getMonthValue()+"",
										checkInDatePickerEnd.getValue().getDayOfMonth()+"",
										timeEnd.getValue());
								// Check if start time is later than end time
								if (startTime.compareTo(endTime) > 0) {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Error in event dates");
									alert.setHeaderText("Start date has to be earlier than end date!");
									alert.show();
								} // End of checking if start date is earlier than end date
								
								// Event has correct start and end date, create event with duration 
								else  {
									if (eventListener.onAddEventDuration(eventname, eventdescrip, startTime, endTime)) {
									eventWindow.close();
									} // End of successfully creating event with duration
									else {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Error in chosing time");
										alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
										alert.show();
									} // End of alert for event out of timeline
								} // End of successfully creating event with duration
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
				eventWindow.show();
				textFieldsStart.requestFocus();

			}
		});
		return addEvent;

	}
	
	/**
	 * Method to disable addEvent when no timelines are loaded
	 * @param notShown true if button should be disabled
	 */
	public void setDisable (boolean notShown) {
		addEvent.setDisable(notShown);
	}

	/**
	 * Creates and returns Edit Event button and it's functionalities
	 * @param e event to be edited
	 * @return editEvent button
	 */
	public Button EditButton(Event e) {
		// Button parameters
		editEvent.setMinSize(80, 30);
		editEvent.setFont(Font.font("Verdana", 15));
		editEvent.setTranslateX(10);
		editEvent.setTranslateY(30);
		editEvent.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				name.setDisable(false);
				description.setDisable(false);
				checkInDatePickerStart.setDisable(false);
				checkInDatePickerEnd.setDisable(false);
				timeStart.setDisable(false);
				timeEnd.setDisable(false);
				ok.setDisable(false);
				cancel.setDisable(false);


				ok.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						/*
						 * If Name, Description or Start Date fields are empty
						 * an Error alert shows to the user
						 */
						
						if (checkInDatePickerEnd.getEditor().getText().length() == 0) {
							checkInDatePickerEnd.setValue(null);
							
						}
						
						if (timeEnd.getValue() == null || timeEnd.getValue().length() == 0) {
							timeEnd.setValue(null);
						}
						
						if (isNeededFieldEmpty()) {
							Alert emptyFieldError = new Alert(Alert.AlertType.ERROR,
									"Name, Description and date fields can't be empty.");
							emptyFieldError.showAndWait();
						} // End of checking if fields are empty
			
						/*
						 * If all fields were not empty,
						 * event editing is possible
						 */
						else {
							LocalDateTime startTime = createLocalDateTime(checkInDatePickerStart.getValue().getYear()+"", 
									checkInDatePickerStart.getValue().getMonthValue()+"",
									checkInDatePickerStart.getValue().getDayOfMonth()+"",
									timeStart.getValue());
				                String eventname = name.getText();
				                String eventdescrip = description.getText();
							
				            // Event is not a duration event, it's not attemted to be converted, then
				            // edit old event
							if (!e.isDuration() && checkInDatePickerEnd.getValue() == null) {
			
			                titleText.setText("  " + name.getText());
			                decText.setText(description.getText());
			                dateStartText.setText(startTime.format(format));
			
				                if (eventListener.onEditEvent(eventname, eventdescrip, startTime)) {
				
				    				name.setDisable(true);
				    				description.setDisable(true);
				    				
				    				checkInDatePickerStart.setDisable(true);
				    				checkInDatePickerEnd.setDisable(true);
				    				
				    				timeStart.setDisable(true);
				    				timeEnd.setDisable(true);
				    				
				    				ok.setDisable(true);
				    				cancel.setDisable(true);
				                }
				                else {
				                  Alert alert = new Alert(AlertType.ERROR);
				                  alert.setTitle("Error in chosing time");
				                  alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
				                  alert.show();
								}
				            } // End of editing of event from non duration to non duration
							else if (e.isDuration() && checkInDatePickerEnd.getValue() == null) {
				                titleText.setText("  " + name.getText());
				                decText.setText(description.getText());
				                dateStartText.setText(startTime.format(format));
								
								eventListener.onDeleteEvent();
								if(eventListener.onAddEvent(eventname, eventdescrip, startTime)) {
				    				name.setDisable(true);
				    				description.setDisable(true);
				    				
				    				checkInDatePickerStart.setDisable(true);
				    				checkInDatePickerEnd.setDisable(true);
				    				
				    				timeStart.setDisable(true);
				    				timeEnd.setDisable(true);
				    				
				    				ok.setDisable(true);
				    				cancel.setDisable(true);
								} // End of add of new event
				                else {
					                  Alert alert = new Alert(AlertType.ERROR);
					                  alert.setTitle("Error in chosing time");
					                  alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
					                  alert.show();
								} // End of alert for out of timeline event
								
							}// End of editing event from duration to non duration
							
							else if (!e.isDuration() && checkInDatePickerEnd.getValue() != null) {
								LocalDateTime endTime = createLocalDateTime(checkInDatePickerEnd.getValue().getYear()+"", 
										checkInDatePickerEnd.getValue().getMonthValue()+"",
										checkInDatePickerEnd.getValue().getDayOfMonth()+"",
										timeStart.getValue());
								
				                //Update in EventInfoView
				                titleText.setText("  "+name.getText());
				                decText.setText(description.getText());
				                dateStartText.setText(startTime.format(format));
				                dateEndText.setText(endTime.format(format));
								
				                if (startTime.compareTo(endTime) > 0) {
					                  Alert alert = new Alert(AlertType.ERROR);
					                  alert.setTitle("Error in event dates");
					                  alert.setHeaderText("Start date has to be earlier than end date!");
					                  alert.show();
					                } // End of alert for start date later than end date for event
				                
				                
								eventListener.onDeleteEvent();
								if (eventListener.onAddEventDuration(eventname, eventdescrip, startTime, endTime)) {
				    				name.setDisable(true);
				    				description.setDisable(true);
				    				
				    				checkInDatePickerStart.setDisable(true);
				    				checkInDatePickerEnd.setDisable(true);
				    				
				    				timeStart.setDisable(true);
				    				timeEnd.setDisable(true);
				    				
				    				ok.setDisable(true);
				    				cancel.setDisable(true);
								} // End of add of new event
				                else {
					                  Alert alert = new Alert(AlertType.ERROR);
					                  alert.setTitle("Error in chosing time");
					                  alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
					                  alert.show();
								}// End of event out of timeline alert
							}// End of edit event from non duration to duration
				            /*
				            * If the Event has End Time an Event with duration is
				            * created
				            */
							else {
			            	// Get end date, since it's event with duration  
							LocalDateTime endTime = createLocalDateTime(checkInDatePickerEnd.getValue().getYear()+"", 
									checkInDatePickerEnd.getValue().getMonthValue()+"",
									checkInDatePickerEnd.getValue().getDayOfMonth()+"",
									timeStart.getValue());
							
							
			                //Update in EventInfoView
			                titleText.setText("  "+name.getText());
			                decText.setText(description.getText());
			                dateStartText.setText(startTime.format(format));
			                dateEndText.setText(endTime.format(format));
			
			                
			                if (startTime.compareTo(endTime) > 0) {
			                  Alert alert = new Alert(AlertType.ERROR);
			                  alert.setTitle("Error in event dates");
			                  alert.setHeaderText("Start date has to be earlier than end date!");
			                  alert.show();
			                } // End of alert for start date later than end date for event
			
			                else {
			                   if(eventListener.onEditEventDuration(eventname, eventdescrip, startTime, endTime)) {
			                      // it should dispaly an alert if the input is
			                      // not correct
			                      // title = new Text("Title: "+
			                      // description.getText());
				    				name.setDisable(true);
				    				description.setDisable(true);
				    				
				    				checkInDatePickerStart.setDisable(true);
				    				checkInDatePickerEnd.setDisable(true);
				    				
				    				timeStart.setDisable(true);
				    				timeEnd.setDisable(true);
				    				
				    				ok.setDisable(true);
				    				cancel.setDisable(true);
			                    } // End of editing event without duration
			                  else {
			                      Alert alert = new Alert(AlertType.ERROR);
			                      alert.setTitle("Error in chosing time");
			                      alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
			                      alert.show();
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
						name.setDisable(true);
						description.setDisable(true);

						checkInDatePickerStart.setDisable(true);
						timeStart.setDisable(true);

						checkInDatePickerEnd.setDisable(true);
						timeEnd.setDisable(true);
						ok.setDisable(true);
						cancel.setDisable(true);
					} // End of handle() method for cancel
				}); // End of setOnAction for cancel button
			} // End of handle() for editEvent button
		}); // End of setOnAction for editEvent button
		
		return editEvent;
	}

	/**
	 * Delete button responsible for delete selected event
	 * @param e event to be deleted
	 * @param s closes event information window of delete event
	 * @return button with set action on it.
	 */
	public Button getDeleteButton(Event e, Stage s) {
		delete.setMinSize(80, 30);
		delete.setFont(Font.font("Verdana", 15));
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
				if (result.get() == ButtonType.OK){
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

		GridPane pane = new GridPane();
		
		// TextFields,TextAreas initialized
		name = new TextField();
		name.setPromptText("Event name");
		name.setFont(new Font("Times new Roman", 20));
		name.setPrefWidth(446);

		description = new TextArea();
		description.setPromptText("Event information");
		description.setFont(Font.font("Times new Roman", 20));
		description.setPrefSize(446, 200);
		description.setWrapText(true);
		
		// In case fields are disabled
		checkInDatePickerStart.setDisable(false);
		checkInDatePickerEnd.setDisable(false);
		ok.setDisable(false);
		cancel.setDisable(false);
		timeStart.setDisable(false);
		timeEnd.setDisable(false);
		checkInDatePickerStart.setValue(null);
		checkInDatePickerEnd.setValue(null);
		
     	/* Limit the number of characters*/
     	final int nameMAX_CHARS = 40;
     	name.setTextFormatter(new TextFormatter<String>(change -> 
        change.getControlNewText().length() <= nameMAX_CHARS ? change : null));
     	final int desMAX_CHARS = 300;
     	description.setTextFormatter(new TextFormatter<String>(change -> 
            change.getControlNewText().length() <= desMAX_CHARS ? change : null));
     	
     	
     	// Combo box for start event times
     	timeStart.setPromptText("Start time");
     	timeStart.setValue(null);
		timeStart.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeStart.setStyle("-fx-font: 16 timesnewroman;");
		timeStart.setMinWidth(130);

		// Combo box for end event times
		timeEnd.setPromptText("End time");
		timeEnd.setValue(null);
		timeEnd.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeEnd.setStyle("-fx-font: 16 timesnewroman;");
		timeEnd.setMinWidth(130);
	    
		// Date pickers for start event
		checkInDatePickerStart.setStyle("-fx-font: 16 timesnewroman;");
		checkInDatePickerStart.setPromptText("Event start");
		
		// Date pickers for end event
		checkInDatePickerEnd.setStyle("-fx-font: 16 timesnewroman;");
		checkInDatePickerEnd.setPromptText("Event end");

		// Buttons initialized
		ok.setPrefSize(135, 35);
		ok.setFont(Font.font("Times new Roman",20));

		cancel.setPrefSize(135, 35);
		cancel.setTranslateX(175);
		cancel.setFont(Font.font("Times new Roman",20));


		// HBox initialized

		HBox hb = new HBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		HBox hb4 = new HBox();

		hb.getChildren().addAll(checkInDatePickerStart, timeStart);
		hb.setAlignment(Pos.CENTER);
		HBox.setMargin(checkInDatePickerStart, new Insets(5));
		HBox.setMargin(checkInDatePickerEnd, new Insets(5));
		HBox.setMargin(timeStart, new Insets(5));
		HBox.setMargin(timeEnd, new Insets(5));
		hb1.getChildren().addAll(checkInDatePickerEnd, timeEnd);
		hb1.setAlignment(Pos.CENTER);
		hb2.getChildren().addAll(ok,cancel);
		hb3.getChildren().add(name);
		hb4.getChildren().add(description);

		hb.setPadding(new Insets(0,0,10,0));
		hb1.setPadding(new Insets(0,0,30,0));
		hb3.setPadding(new Insets(0,0,10,0));
		hb4.setPadding(new Insets(0,0,10,0));

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
	 * Creates a window that displays information about 
	 * certain event
	 * @param e event that information is displayed about
	 */
	public void ViewEventInfo(Event e) {
		final Stage eventWindow = new Stage();

		VBox window = new VBox();
		window.setSpacing(20);
		window.setPrefSize(200, 200);

		Label info = new Label("Information");
		info.setFont(Font.font("Verdana", 25));
		info.setUnderline(true);
		info.setTextFill(Color.BLACK);
		info.setAlignment(Pos.CENTER);

		title.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
		title.setTranslateX(8);
		titleText = new Text("  " + e.getEventName());
		titleText.setFont(Font.font ("Verdana", 15));
		titleText.setWrappingWidth(250);
		titleText.setTranslateY(-13);

		String formattedStringS = e.getEventStart().format(format);

		eventStart.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
		eventStart.setTranslateX(8);
		eventStart.setTranslateY(-20);

		dateStartText = new Text(formattedStringS);
		dateStartText.setFont(Font.font("Verdana", 15));;
		dateStartText.setWrappingWidth(250);
		dateStartText.setTranslateY(-33);
		dateStartText.setTranslateX(8);

		des.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
		des.setTranslateX(8);
		des.setTranslateY(-40);

		decText = new Text(e.getEventDescription());
		decText.setWrappingWidth(250);
		decText.setFont(Font.font("Verdana", 15));;
		decText.setTranslateX(8);
		decText.setTranslateY(-53);


		if (e.getEventEnd() == null) {
			dateEndText = new Text(" ");
			window.getChildren().addAll(info, title, titleText, eventStart, dateStartText, des, decText, EditButton(e),getDeleteButton(e, eventWindow));

		} else {
			createEditEventWindow(e);
			String formattedStringE = e.getEventEnd().format(format);


			eventEnd.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
			eventEnd.setTranslateX(8);
			eventEnd.setTranslateY(-35);

			dateEndText = new Text(formattedStringE);
			dateEndText.setFont(Font.font("Verdana", 15));;
			dateEndText.setTranslateY(-48);
			dateEndText.setTranslateX(8);
			window.getChildren().addAll(info, title, titleText, eventStart, dateStartText, eventEnd, dateEndText, des, decText, EditButton(e),getDeleteButton(e, eventWindow));

		}

		// get delete Button
		HBox all = new HBox();
		all.setPrefSize(550, 190);
		all.setPadding(new Insets(10, 10, 10, 10));
		all.getChildren().addAll(window, createEditEventWindow(e));
		Scene eventScene = new Scene(all);
		eventWindow.setTitle("Event");
		eventWindow.setScene(eventScene);
		eventWindow.show();

	}

	/**
	 * Responsible for getting information about event
	 * in edit event window
	 * @param e event to be edited
	 * @return a window 
	 */
	public VBox createEditEventWindow(Event e) {
		
		VBox editeHolder = new VBox();
		editeHolder.setPrefSize(400, 400);
		editeHolder.setTranslateX(50);
		name = new TextField(e.getEventName());
		description = new TextArea(e.getEventDescription());
		Label nameL = new Label("Name");
		Label descriptionL = new Label("Description");
		
		LocalDateTime startDate = e.getEventStart();
		String startLocalDate = localDateTimeToString(startDate);
		LocalDate localDate = LocalDate.parse(startLocalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		checkInDatePickerStart.setValue(localDate);
		checkInDatePickerStart.setMinWidth(150);
		
		Label yearL = new Label("Start Date");
		int strHour = e.getEventStart().getHour();
		timeStart = new ComboBox<String>();
		timeStart.setMinSize(130, 25);
		timeStart.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeStart.getSelectionModel().clearAndSelect(strHour);
		timeEnd = new ComboBox<String>();
		timeEnd.setMinSize(130, 25);
		timeEnd.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00", "");
		Label hourL = new Label("Start Time");

		HBox h2 = new HBox();
		name.setDisable(true);
		description.setDisable(true);
		checkInDatePickerStart.setDisable(true);
		checkInDatePickerEnd.setDisable(true);
		timeStart.setDisable(true);

		if (e.getEventEnd() != null) {
			LocalDateTime endDate = e.getEventEnd();
			String endLocalDate = localDateTimeToString(endDate);
			LocalDate localDate2 = LocalDate.parse(endLocalDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			checkInDatePickerEnd.setValue(localDate2);
			checkInDatePickerEnd.setMinWidth(150);

			int strHourEnd = e.getEventEnd().getHour();

			timeEnd.getSelectionModel().clearAndSelect(strHourEnd);
			timeEnd.setMinSize(100, 25);
			// Change the so event can be edited:
			h2.getChildren().addAll(checkInDatePickerEnd, timeEnd/*hoursEnd*/);

		} else if (e.getEventEnd() == null) {
			checkInDatePickerEnd.setValue(null);
			h2.getChildren().addAll(checkInDatePickerEnd);

		}

		checkInDatePickerEnd.setDisable(true);
		timeEnd.setDisable(true);

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

		vb1.getChildren().addAll(nameL,name);
		vb1.setPadding(new Insets(10,10,10,10));

		vb2.getChildren().addAll(descriptionL,description);
		vb2.setPadding(new Insets(10,10,10,10));

		vb3.getChildren().addAll(yearL,checkInDatePickerStart);
		vb3.setPadding(new Insets(0,5,0,0));

		vb6.getChildren().addAll(hourL,timeStart);
		vb6.setPadding(new Insets(0,5,0,0));

		vb7.getChildren().addAll(yearLE,checkInDatePickerEnd);
		vb7.setPadding(new Insets(0,5,0,0));

		vb10.getChildren().addAll(hourLE,timeEnd);
		vb10.setPadding(new Insets(0,5,0,0));

		hb1.getChildren().addAll(vb3,vb4,vb5,vb6);
		hb1.setPadding(new Insets(5,5,5,10));

		hb2.getChildren().addAll(vb7,vb8,vb9,vb10);
		hb2.setPadding(new Insets(5,5,5,10));

		h1.add(vb1, 0, 1);
		h1.add(vb2, 0, 2);
		h1.add(hb1, 0, 3);
		h1.add(hb2, 0, 4);


		// Buttons initialized
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
	 * help method to create a LocalDateTime from user input
	 *
	 * @param year - String
	 * @param month - String
	 * @param day - String
	 * @param hour - String
	 * @return LocalDateTime created from user input
	 */
	private LocalDateTime createLocalDateTime(String year, String month, String day, String hour) {
		String localDate;
		LocalDateTime time = null;

		// if user input year is less than 4 digits, zeroes will be added in
		// front.
		for (int i = 0; i < 4 - year.length(); i++) {
			year = "0" + year;
		}
		// if month is less than 2 digits, zeroes will be added in front
		for (int i = 0; i < 2 - month.length(); i++) {
			month = "0" + month;
		}
		// if day is less than 2 digits, zeroes will be added in front
		for (int i = 0; i < 2 - day.length(); i++) {
			day = "0" + day;
		}
		// creates the String format that is needed to create LocalDateTime
		localDate = year + "-" + month + "-" + day + "T" + hour + ":00";

		try {
			time = LocalDateTime.parse(localDate);
		} catch (Exception e) {
			// if input is wrong, show error (popup window) message.
			Alert fieldError = new Alert(Alert.AlertType.ERROR, "Date input is not correct.");
			fieldError.showAndWait();
			System.out.println(e);
		}
		return time;

	}

	/**
	 * help method to check if any fields that are needed to create an Event is
	 * empty (Name, Description, Year, Month, Day and Hour)
	 *
	 * @return boolean, true if needed fields are empty otherwise false
	 */
	private boolean isNeededFieldEmpty() {
		//Check if name or description is empty
		if (name.getText().isEmpty() || description.getText().isEmpty()) {
			return true;
		// check if date picker for start is not selected or if time for start 
		// is not selected
		} else if (checkInDatePickerStart.getValue() == null
				|| timeStart.getValue() == null) {
			return true;
		}
		// check if date picker for end value is selected, but time is not selected
		// or if date picker for end is not selected, but time is selected
		else if ((checkInDatePickerEnd.getValue() != null && timeEnd.getValue() == null)
				|| (checkInDatePickerEnd.getValue() == null && timeEnd.getValue() != null)) {
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
		if (checkInDatePickerEnd.getValue() == null || timeEnd.getValue() == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Help method to turn LocalDateTime to string with 0
	 * where month or day is less than 10
	 * @param l LocalDateTime to be converted
	 * @return LocalDateTime turned into string
	 */
	private String localDateTimeToString(LocalDateTime l) {
		String date = "";
		for (int i = 0; i < 4 - (l.getYear()+"").length(); i++) {
			date = date + "0";
		}
		date = date + l.getYear() + "-";
		for (int i = 0; i < 2 - (l.getMonthValue()+"").length(); i++) {
			date = date + "0";
		}
		date = date + l.getMonthValue() + "-";
		for (int i = 0; i < 2 - (l.getDayOfMonth()+"").length(); i++) {
			date = date + "0";
		}
		date = date + l.getDayOfMonth();
		
		return date;
	}

}