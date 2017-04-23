package ui;

import java.time.LocalDateTime;

import controls.EventControl;
import controls.EventListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EventView {

	private EventListener eventListener;

	// TextFields - Add Event Window
	private TextField name;
	private TextField description;

	private TextField yearStart;
	private TextField monthStart;
	private TextField dayStart;
	private TextField hoursStart;

	private TextField yearEnd;
	private TextField monthEnd;
	private TextField dayEnd;
	private TextField hoursEnd;

	// Buttons - Add Event Window
	private Button ok;
	private Button cancel;

	/**
	 * Update the EventListener variable with the EventListener given as input
	 * 
	 * @param eventList
	 *            , (EventListener)
	 */
	public void addListener(EventListener eventList) {
		eventListener = eventList;
	}

	/**
	 * method to create and return the add Event button,
	 * 
	 * @return GridPane root
	 */
	public GridPane getRoot() {
		GridPane root = new GridPane();
		// alignment in the root
		root.setAlignment(Pos.BOTTOM_CENTER);
		// button for add event
		Button addEvent = new Button("Add Event");
		root.add(addEvent, 0, 1);

		// New edit Event button is created
				Button editEvent = new Button("Edit Event");
				root.add(editEvent, 1, 1);
				
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
						}
						/*
						 * If the event doesn't have End Date an non duration
						 * Event is created
						 */
						else if (isNotDurationEvent()) {
							LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(),
									dayStart.getText(), hoursStart.getText());
							String eventname = name.getText();
							String eventdescrip = description.getText();

							if (eventListener.onAddEvent(eventname, eventdescrip, startTime)) {
								eventWindow.close();
							}
						}
						/*
						 * If the Event has End Time an Event with duration is
						 * created
						 */
						else {
							LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(),
									dayStart.getText(), hoursStart.getText());
							LocalDateTime endTime = createLocalDateTime(yearEnd.getText(), monthEnd.getText(),
									dayEnd.getText(), hoursEnd.getText());
							String eventname = name.getText();
							String eventdescrip = description.getText();

							if (eventListener.onAddEventDuration(eventname, eventdescrip, startTime, endTime)) {
								eventWindow.close();
							}
						 
						}

					}
				});

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

				/*
				 * When "Ok" button is clicked, textfields are fetched and
				 * converted to String and LocalDateTime then application
				 * listener is calling the onAddEvent-method
				 */
				
				Scene eventScene = new Scene(textFieldsStart);
				eventWindow.setScene(eventScene);
				eventWindow.show();

			}
		});
		editEvent.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				final Stage editEventWindow = new Stage();
				 BorderPane root = new BorderPane();
				    Scene scene = new Scene(root, 280, 100, Color.BLACK);

				    GridPane gridpane = new GridPane();
				    gridpane.setPadding(new Insets(5));
				    gridpane.setHgap(5);
				    gridpane.setVgap(5);
				    ColumnConstraints column1 = new ColumnConstraints(50);
				    ColumnConstraints column2 = new ColumnConstraints(100, 10, 300);
				    column2.setHgrow(Priority.ALWAYS);
				    gridpane.getColumnConstraints().addAll(column1, column2);


				    Button okEdit = new Button("OK");
				    okEdit.setOnAction(new EventHandler<ActionEvent>() {

				        @Override
				        public void handle(ActionEvent event) {
				        	final Stage eventWindow = new Stage();

							GridPane textFieldsStart = createAddEventWindow();
							Scene eventScene = new Scene(textFieldsStart);
							eventWindow.setScene(eventScene);
							eventWindow.show();
							editEventWindow.close();
				            
				        }
				    });
				    
				    String[] editEventBox = new String[] { };;
				   ChoiceBox<String> cb = new ChoiceBox<String>(
				        FXCollections.observableArrayList());
				    cb.getItems().add(""); //add the created events

				    

				    // ok-button
				    GridPane.setHalignment(okEdit, HPos.RIGHT);
				    gridpane.add(okEdit, 1, 0);
				    
				    
				    //cb
				    GridPane.setHalignment(cb, HPos.LEFT);
				    gridpane.add(cb, 0, 0);
				    
				    root.setCenter(gridpane);
				   editEventWindow.setScene(scene);
				    editEventWindow.show();
				  
	
				//YOUR CODE GOES HERE 
				// New window opens with text fields filled with current event paraments
				// such as name, dates, description.
				// eventListener.onEditEvent should be ran and eventListener.onEditEventDuration
				// depending what kind of event was opened. Read code for
				// adding new event, because your code should be very similar to it (it 
				// also opens new window, also checks what kind of event, also user has to 
				// make some sort of input).
			}
});


		
		  
		  // return the root created.
		return root;

	}

	/**
	 * Help method to create popup window, initializes all TextFields, Labels
	 * and Buttons for the window, and then add it all to the GridPane
	 * 
	 * @return GridPane
	 */
	private GridPane createAddEventWindow() {
		GridPane pane = new GridPane();

		// TextFields initialized
		name = new TextField();
		description = new TextField();

		yearStart = new TextField();
		monthStart = new TextField();
		dayStart = new TextField();
		hoursStart = new TextField();

		yearEnd = new TextField();
		monthEnd = new TextField();
		dayEnd = new TextField();
		hoursEnd = new TextField();

		// Labels initialized
		Label nameLabel = new Label("Name: ");
		Label descriptionLabel = new Label("Description: ");

		Label start = new Label("Start Date:");
		Label end = new Label("End Date: ");
		Label yearLabel = new Label("Year: ");
		Label monthLabel = new Label("Month: ");
		Label dayLabel = new Label("Day: ");
		Label hourLabel = new Label("Time of day (hour)");

		Label yearLabel2 = new Label("Year: ");
		Label monthLabel2 = new Label("Month: ");
		Label dayLabel2 = new Label("Day: ");
		Label hourLabel2 = new Label("Time of day (hour)");

		// Buttons initialized
		ok = new Button();
		cancel = new Button();

		// Add initialized Nodes to the GridPane
		pane.add(nameLabel, 0, 1);
		pane.add(name, 1, 1);
		pane.add(descriptionLabel, 0, 2);
		pane.add(description, 1, 2);
		pane.add(start, 0, 3);
		pane.add(yearLabel, 0, 4);
		pane.add(yearStart, 0, 5);
		pane.add(monthLabel, 1, 4);
		pane.add(monthStart, 1, 5);
		pane.add(dayLabel, 2, 4);
		pane.add(dayStart, 2, 5);
		pane.add(hourLabel, 3, 4);
		pane.add(hoursStart, 3, 5);
		pane.add(end, 0, 6);
		pane.add(yearLabel2, 0, 7);
		pane.add(yearEnd, 0, 8);
		pane.add(monthLabel2, 1, 7);
		pane.add(monthEnd, 1, 8);
		pane.add(dayLabel2, 2, 7);
		pane.add(dayEnd, 2, 8);
		pane.add(hourLabel2, 3, 7);
		pane.add(hoursEnd, 3, 8);
		pane.add(ok, 0, 9);
		pane.add(cancel, 1, 9);

		return pane;
	}

	/**
	 * Help method to create a LocalDateTime from the User input if invalid
	 * input an error alert window will show to the user
	 * 
	 * @param year,
	 *            user input
	 * @param month,
	 *            user input
	 * @param day,
	 *            user input
	 * @param hour,
	 *            user input
	 * @return LocalDateTime
	 */
	private LocalDateTime createLocalDateTime(String year, String month, String day, String hour) {
		String localDate;
		LocalDateTime time = null;

		// if user input year is less than 4 digits, zeroes will be added in
		// front.
		for (int i = 0; i < 4 - year.length(); i++) {
			year = "0" + year;
		}
		// creates the String format that is needed to create LocalDateTime
		localDate = year + "-" + month + "-" + day + "T" + hour + ":00:00";

		try {
			time = LocalDateTime.parse(localDate);
		} catch (Exception e) {
			// if input is wrong, show error (popup window) message.
			Alert fieldError = new Alert(Alert.AlertType.ERROR, "Date input is not correct.");
			fieldError.showAndWait();
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
		if (name.getText().isEmpty() || description.getText().isEmpty()) {
			return true;
		} else if (yearStart.getText().isEmpty() || monthStart.getText().isEmpty() || dayStart.getText().isEmpty()
				|| hoursStart.getText().isEmpty()) {
			return true;
		} else {
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
		if (yearEnd.getText().isEmpty() || monthEnd.getText().isEmpty() || dayEnd.getText().isEmpty()
				|| hoursEnd.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}


}