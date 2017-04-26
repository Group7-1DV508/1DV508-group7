package ui;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controls.EventControl;
import controls.EventListener;
import controls.TimelineListener;
import functions.Event;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
	private Button editEvent;
	private Button delete;
	private Text title;
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
public Button getAddEventButton() {
		
		Button addEvent = new Button("Add Event"); 
		
		/*
		 * when Add Event button is clicked a popup window is created where
		 * the user can provide information about the Event,
		 * User has to provide Name, Description and Start Date, End Date
		 * however is optional.
		 */
		addEvent.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				final Stage eventWindow = new Stage();

				GridPane textFieldsStart = createAddEventWindow();

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
							} 
						/*
						 * If the event doesn't have End Date an non duration Event 
						 * is created
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
						 * If the Event has End Time an Event with duration is created
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
				 * when cancel button is clicked the popup window
				 * is closed
				 */
				cancel.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						eventWindow.close();
					}
				});

				/*
				 * Creates a Scene and builds the Add Event popup
				 * window
				 */
				Scene eventScene = new Scene(textFieldsStart);
				eventWindow.setScene(eventScene);
				eventWindow.show();

			}
		});
		// return the root created.
		return addEvent;

	}

	public Button EditButton() {

		
		editEvent =new Button("Edit");
		editEvent.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				name.setDisable(false);
				description.setDisable(false);
				yearStart.setDisable(false);
				monthStart.setDisable(false);
				dayStart.setDisable(false);
				hoursStart.setDisable(false);
				yearEnd.setDisable(false);
				monthEnd.setDisable(false);
				dayEnd.setDisable(false);
				hoursEnd.setDisable(false);
				ok.setDisable(false);
				cancel.setDisable(false);

				// GridPane textFieldsEdit = createEditEventWindow();

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
						 if (yearEnd == null || monthEnd == null || dayEnd == null ||hoursEnd==null ){
							
							 LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(),
								      			  dayStart.getText(), hoursStart.getText());
							 String eventname = name.getText();
							 String eventdescrip = description.getText();
							 title = new Text("Title: "+ description.getText());
						if (eventListener.onAddEvent(eventname, eventdescrip, startTime) ) {
							
							
							name.setDisable(true);
							description.setDisable(true);

							yearStart.setDisable(true);
							monthStart.setDisable(true);
							dayStart.setDisable(true);
							hoursStart.setDisable(true);

							yearEnd.setDisable(true);
							monthEnd.setDisable(true);
							dayEnd.setDisable(true);
							hoursEnd.setDisable(true);
						
							
								
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
								// it should dispaly an alart if the input is not correct
								//title = new Text("Title: "+ description.getText());
								name.setDisable(true);
								description.setDisable(true);

								yearStart.setDisable(true);
								monthStart.setDisable(true);
								dayStart.setDisable(true);
								hoursStart.setDisable(true);

								yearEnd.setDisable(true);
								monthEnd.setDisable(true);
								dayEnd.setDisable(true);
								hoursEnd.setDisable(true);
								ok.setDisable(false);
								cancel.setDisable(false);
							
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
						name.setDisable(true);
						description.setDisable(true);

						yearStart.setDisable(true);
						monthStart.setDisable(true);
						dayStart.setDisable(true);
						hoursStart.setDisable(true);

						yearEnd.setDisable(true);
						monthEnd.setDisable(true);
						dayEnd.setDisable(true);
						hoursEnd.setDisable(true);
						ok.setDisable(true);
						cancel.setDisable(true);
					}
				});

			}
		});
		return editEvent;

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
		ok = new Button("OK");
		cancel = new Button("Cancel");
		delete = new Button("Delete");

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
		pane.add(delete, 3, 9);

		return pane;
	}

	
	public void ViewEventInfo(Event e) {
		final Stage eventWindow = new Stage();

		VBox window = new VBox();
		window.setSpacing(10);
		window.setPrefSize(200, 200);
		
		Label info = new Label("Information");
		info.setFont(Font.font("Verdana",20));
		info.setTextFill(Color.CORNFLOWERBLUE);
		title = new Text("Title: "+ e.getEventName());
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm");
		String formattedStringS = e.getEventStart().format(formatter);

		Text dateStart = new Text("Event starts at: " + formattedStringS);
		Text dec = new Text("Description: "+ e.getEventDescription());
		dec.setWrappingWidth(250);
	
			
	if (e.getEventEnd() == null) {
				//editEventWindow.close();
				window.getChildren().addAll(info, title, dateStart, dec, EditButton());
			
		
	}else   {
			createEditEventWindow(e);
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm");
			String formattedStringE = e.getEventEnd().format(formatter2);
			Text end = new Text("Event Ends at: " + formattedStringE);
			window.getChildren().addAll(info, title, dateStart, dec, end, EditButton());
			
		}

	
		
		// get delete Button
		HBox all = new HBox();
		all.setPrefSize(550, 190);
		all.setPadding(new Insets(10,10,10,10));

		all.getChildren().addAll(window,createEditEventWindow(e) );
		Scene eventScene = new Scene(all);
		eventWindow.setScene(eventScene);
		eventWindow.show();

	}

	public VBox createEditEventWindow(Event e) {
		VBox editeHolder = new VBox();
		editeHolder.setPrefSize(300, 300);
		name = new TextField(e.getEventName());
		description = new TextField(e.getEventDescription());
		Label nameL = new Label("Name");
		Label descriptionL = new Label("Description");

		int strYear = e.getEventStart().getYear();
		String year1 = Integer.toString(strYear);
		yearStart = new TextField(year1);
		Label yearL = new Label("Year");

		

		int strMonth = e.getEventStart().getMonthValue();
		String month1 = Integer.toString(strMonth);
		monthStart = new TextField(month1);
		Label monthL = new Label("Month");


		int strDay = e.getEventStart().getDayOfMonth();
		String days1 = Integer.toString(strDay);
		dayStart = new TextField(days1);
		Label dayL = new Label("Day");

		int strHour = e.getEventStart().getHour();
		String hour1 = Integer.toString(strHour);
		hoursStart = new TextField(hour1);
		Label hourL = new Label("Hour");
		
		HBox h2 = new HBox();
		name.setDisable(true);
		description.setDisable(true);
		yearStart.setDisable(true);
		monthStart.setDisable(true);
		dayStart.setDisable(true);
		hoursStart.setDisable(true);

	
		
		
		if (e.getEventEnd() != null){
		int strYearEnd = e.getEventEnd().getYear();
		String year2 = Integer.toString(strYearEnd);
		yearEnd = new TextField(year2);
		
		int strMonthEnd = e.getEventEnd().getMonthValue();
		String month2 = Integer.toString(strMonthEnd);
		monthEnd = new TextField(month2);

		int strDayEnd = e.getEventEnd().getDayOfMonth();
		String days2 = Integer.toString(strDayEnd);
		dayEnd = new TextField(days2);

		int strHourEnd = e.getEventEnd().getHour();
		String hour2 = Integer.toString(strHourEnd);
		hoursEnd = new TextField(hour2);
		// Change the so event can be edited:
		
		h2.getChildren().addAll(yearEnd, monthEnd, dayEnd, hoursEnd);
		
		}else if (e.getEventEnd() == null){
			yearEnd = new TextField("");
			monthEnd = new TextField("");
			dayEnd = new TextField("");
			hoursEnd = new TextField("");
			h2.getChildren().addAll(yearEnd, monthEnd, dayEnd, hoursEnd);
			
		}
	
			yearEnd.setDisable(true);
			monthEnd.setDisable(true);
			dayEnd.setDisable(true);
			hoursEnd.setDisable(true);
		
		
		GridPane h1 = new GridPane();
		Label yearLE = new Label("Year");
		Label monthLE = new Label("Month");
		Label dayLE = new Label("Day");
		Label hourLE = new Label("Hour");
		
		h1.add(nameL, 0, 1);
		h1.add(name ,0, 2);
		h1.add(descriptionL,0, 3);
		h1.add(description,0, 4);
		h1.add(yearL,0, 5);
		h1.add(yearStart,0, 6);
		h1.add(monthL,1, 5);
		h1.add(monthStart,1, 6);
		
		h1.add(dayL,2, 5);
		h1.add(dayStart,2, 6);
		h1.add(hourL,3, 5);
		h1.add(hoursStart,3, 6);
		
		
		
		h1.add(yearLE,0,7);
		h1.add(monthLE,1, 7);
		h1.add(dayLE,2, 7);
		h1.add(hourLE,3, 7);

		h1.add(yearEnd,0,8);
		h1.add(monthEnd,1, 8);
		h1.add(dayEnd,2, 8);
		h1.add(hoursEnd,3, 8);
		
		
		
		HBox h3 = new HBox();
		



		// Buttons initialized
		ok = new Button("Ok");
		ok.setDisable(true);
		cancel = new Button("Cancel");
		cancel.setDisable(true);
		h3.setAlignment(Pos.BASELINE_RIGHT);
		h3.setPadding(new Insets(10,0,10,10));
		h3.getChildren().addAll(cancel, ok);
		h3.setSpacing(10);
		editeHolder.getChildren().addAll( h1, h3);
		return editeHolder;

	}

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