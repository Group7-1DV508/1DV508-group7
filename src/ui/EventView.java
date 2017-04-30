package ui;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	

	private TextField yearEnd;
	private TextField monthEnd;
	private TextField dayEnd;
	

	// Buttons - Add Event Window
	private Button ok;
	private Button cancel;
	private Button editEvent;
	private Button delete;
	private Text titleText;
	private Text decText;
	private Text dateStartText;
	private Text dateEndText;
	private ComboBox<String> timeStart;
	private ComboBox<String> timeEnd;

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
		addEvent.setMinSize(75, 30);

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
						 * If the event doesn't have End Date an non duration
						 * Event is created
						 */
						else if (isNotDurationEvent()) {
							LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(),
									dayStart.getText(),
									timeStart.getValue()/*
														 * hoursStart.getText()
														 */);
							String eventname = name.getText();
							String eventdescrip = description.getText();

							if (eventListener.onAddEvent(eventname, eventdescrip, startTime)) {
								eventWindow.close();
							}
						}
						/*
						 * If the Event has End Time an Event with duration is
						 * created6
						 */
						else {
							LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(),
									dayStart.getText(),
									timeStart.getValue() /*
															 * hoursStart.getText()
															 */);
							LocalDateTime endTime = createLocalDateTime(yearEnd.getText(), monthEnd.getText(),
									dayEnd.getText(),
									timeEnd.getValue() /* hoursEnd.getText() */);
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
				Scene eventScene = new Scene(textFieldsStart);
				eventWindow.setScene(eventScene);
				eventWindow.show();

			}
		});
		return addEvent;

	}

	public Button EditButton(Event e) {

		editEvent = new Button("Edit");
		editEvent.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				name.setDisable(false);
				description.setDisable(false);
				yearStart.setDisable(false);
				monthStart.setDisable(false);
				dayStart.setDisable(false);
				timeStart.setDisable(false);
				yearEnd.setDisable(false);
				monthEnd.setDisable(false);
				dayEnd.setDisable(false);
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
						if (isNeededFieldEmpty()) {
							Alert emptyFieldError = new Alert(Alert.AlertType.ERROR,
									"Name, Description and Start Date can't be empty.");
							emptyFieldError.showAndWait();
						}
						/*
						 * If the event doesn't have End Date an non duration
						 * Event is created
						 */
						if (!e.isDuration()) {

							LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(),
									dayStart.getText(), timeStart.getValue());
							String eventname = name.getText();
							String eventdescrip = description.getText();
							Alerts alert = new Alerts();
						   if( alert.Checkname(eventname, eventdescrip)){
							titleText.setText( name.getText());
							decText.setText(description.getText());
							dateStartText.setText( yearStart.getText()+"/"+ monthStart.getText()+"/"+
													dayStart.getText()+" At "+ timeStart.getValue());
								 
							if(yearEnd.getText().length()!= 0){
						
						    dateEndText.setText(yearEnd.getText()+"/"+ monthEnd.getText()+"/"+dayEnd.getText()+" At "+timeEnd.getValue());
						    
							}
							if (eventListener.onEditEvent(eventname, eventdescrip, startTime)) {

								name.setDisable(true);
								description.setDisable(true);

								yearStart.setDisable(true);
								monthStart.setDisable(true);
								dayStart.setDisable(true);
								timeStart.setDisable(true);

								yearEnd.setDisable(true);
								monthEnd.setDisable(true);
								dayEnd.setDisable(true);
								timeEnd.setDisable(true);
								ok.setDisable(true);
								cancel.setDisable(true);
							}
						}
						}
						/*
						 * If the Event has End Time an Event with duration is
						 * created
						 */
						else {
							LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(),
									dayStart.getText(), timeStart.getValue());
							LocalDateTime endTime = createLocalDateTime(yearEnd.getText(), monthEnd.getText(),
									dayEnd.getText(), timeEnd.getValue());
							String eventname = name.getText();
							String eventdescrip = description.getText();
							//Update in EventInfoView 
							titleText.setText( name.getText());
							decText.setText(description.getText());
							dateStartText.setText(yearStart.getText()+"/"+ monthStart.getText()+"/"+
													dayStart.getText()+" At "+ timeStart.getValue());
								 
							dateEndText.setText(yearEnd.getText()+"/"+ monthEnd.getText()+"/"+dayEnd.getText()+" At "+ timeEnd.getValue());

							if (eventListener.onEditEventDuration(eventname, eventdescrip, startTime, endTime)) {
								// it should dispaly an alart if the input is
								// not correct
								// title = new Text("Title: "+
								// description.getText());
								name.setDisable(true);
								description.setDisable(true);

								yearStart.setDisable(true);
								monthStart.setDisable(true);
								dayStart.setDisable(true);
								timeStart.setDisable(true);

								yearEnd.setDisable(true);
								monthEnd.setDisable(true);
								dayEnd.setDisable(true);
								timeEnd.setDisable(true);
								ok.setDisable(true);
								cancel.setDisable(true);

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
						timeStart.setDisable(true);

						yearEnd.setDisable(true);
						monthEnd.setDisable(true);
						dayEnd.setDisable(true);
						timeEnd.setDisable(true);
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
		pane.setPadding(new Insets(10,10,10,10));
		
		// TextFields initialized
		name = new TextField();
		description = new TextField();

		yearStart = new TextField();
		monthStart = new TextField();
		dayStart = new TextField();
		timeStart = new ComboBox<String>();
		timeStart.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");

		yearEnd = new TextField();
		monthEnd = new TextField();
		dayEnd = new TextField();
		timeEnd = new ComboBox<String>();
		timeEnd.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");

		// Labels initialized
		Label nameLabel = new Label("Name: ");
		Label descriptionLabel = new Label("Description: ");

		Label start = new Label("Start Date:");
		Label end = new Label("End Date: ");
		Label yearLabel = new Label("Year: ");
		Label monthLabel = new Label("Month: ");
		Label dayLabel = new Label("Day: ");
		Label hourLabel = new Label("Time");

		Label yearLabel2 = new Label("Year: ");
		Label monthLabel2 = new Label("Month: ");
		Label dayLabel2 = new Label("Day: ");
		Label hourLabel2 = new Label("Time");

		// Buttons initialized
		ok = new Button("Ok");
		ok.setMaxSize(75, 35);
		ok.setMinSize(75, 35);
		cancel = new Button("Cancel");
		cancel.setMaxSize(75, 35);
		cancel.setMinSize(75, 35);
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
		pane.add(timeStart, 3, 5);
		pane.add(end, 0, 6);
		pane.add(yearLabel2, 0, 7);
		pane.add(yearEnd, 0, 8);
		pane.add(monthLabel2, 1, 7);
		pane.add(monthEnd, 1, 8);
		pane.add(dayLabel2, 2, 7);
		pane.add(dayEnd, 2, 8);
		pane.add(hourLabel2, 3, 7);
		pane.add(timeEnd, 3, 8);
		pane.add(ok, 0, 9);
		pane.add(cancel, 1, 9);
		

		return pane;
	}

	public void ViewEventInfo(Event e) {
		final Stage eventWindow = new Stage();

		VBox window = new VBox();
			 window.setSpacing(10);
			 window.setPrefSize(300, 300);

		Label info = new Label("Information");
			  info.setFont(Font.font("Verdana", 20));
			  info.setTextFill(Color.CORNFLOWERBLUE);
		Label	Title	= new Label("Title" );
				Title.setFont(Font.font("Verdana", 15));
				Title.setTextFill(Color.CORNFLOWERBLUE);
				Title.setUnderline(true);

		Label	Description		= new Label("Description"  );
				Description.setFont(Font.font("Verdana", 15));
				Description.setTextFill(Color.CORNFLOWERBLUE);
				Description.setUnderline(true);
		Label	start		= new Label("Event starts at" );
				start.setFont(Font.font("Verdana", 15));
				start.setTextFill(Color.CORNFLOWERBLUE);
				start.setUnderline(true);
		Label	end		= new Label("Event Ends at" );
				end.setFont(Font.font("Verdana", 15));
				end.setTextFill(Color.CORNFLOWERBLUE);
				end.setUnderline(true);
				
		titleText = new Text(e.getEventName());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm");
		String formattedStringS = e.getEventStart().format(formatter);

		dateStartText = new Text( formattedStringS);
		decText = new Text( e.getEventDescription());
		decText.setWrappingWidth(250);

		if (e.getEventEnd() == null) {
			dateEndText = new Text(" ");
			window.getChildren().addAll(info,Title, titleText,Description, decText,start,dateStartText,end,dateEndText,EditButton(e),  DeletEvent( e));
		
		
		} else {
			createEditEventWindow(e);
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm");
			String formattedStringE = e.getEventEnd().format(formatter2);
			dateEndText = new Text(  formattedStringE);
			window.getChildren().addAll(info,Title, titleText,Description, decText,start,dateStartText,end,dateEndText,EditButton(e),  DeletEvent( e));
			

		}

		// get delete Button
		
		HBox all = new HBox();
		all.setPrefSize(700, 200);
		all.setPadding(new Insets(10, 10, 10, 10));
		all.getChildren().addAll(window, createEditEventWindow(e));
		Scene eventScene = new Scene(all);
		eventWindow.setTitle("Event");
		eventWindow.setScene(eventScene);
		eventWindow.show();

	}

	public VBox createEditEventWindow(Event e) {
		VBox editeHolder = new VBox();
		editeHolder.setPrefSize(400, 400);
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
		timeStart = new ComboBox<String>();
		timeStart.setMinSize(100, 25);
		timeStart.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeStart.getSelectionModel().clearAndSelect(strHour+1);
		timeEnd = new ComboBox<String>();
		timeEnd.setMinSize(100, 25);
		timeEnd.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		Label hourL = new Label("Time");

		HBox h2 = new HBox();
		name.setDisable(true);
		description.setDisable(true);
		yearStart.setDisable(true);
		monthStart.setDisable(true);
		dayStart.setDisable(true);
		timeStart.setDisable(true);

		if (e.getEventEnd() != null) {
			int strYearEnd = e.getEventEnd().getYear();
			String year2 = Integer.toString(strYearEnd);
			yearEnd = new TextField(year2);

			int strMonthEnd = e.getEventEnd().getMonthValue();
			String month2 = Integer.toString(strMonthEnd);
			monthEnd = new TextField(month2);

			int strDayEnd = e.getEventEnd().getDayOfMonth();
			String days2 = Integer.toString(strDayEnd);
			dayEnd = new TextField(days2);

			
			timeEnd.getSelectionModel().clearAndSelect(strHour+1);
			// Change the so event can be edited:

			h2.getChildren().addAll(yearEnd, monthEnd, dayEnd, timeEnd/*hoursEnd*/);

		} else if (e.getEventEnd() == null) {
			yearEnd = new TextField("");
			monthEnd = new TextField("");
			dayEnd = new TextField("");
			h2.getChildren().addAll(yearEnd, monthEnd, dayEnd);

		}

		yearEnd.setDisable(true);
		monthEnd.setDisable(true);
		dayEnd.setDisable(true);
		timeEnd.setDisable(true);

		GridPane h1 = new GridPane();
		GridPane pane2 = new GridPane();
		
		pane2.getColumnConstraints().add(new ColumnConstraints(250)); 
		Label yearLE = new Label("Year");
		Label monthLE = new Label("Month");
		Label dayLE = new Label("Day");
		Label hourLE = new Label("Time");

		pane2.add(nameL, 0, 1);
		pane2.add(name, 0, 2);
		pane2.add(descriptionL, 0, 3);
		pane2.add(description, 0, 4);
		h1.add(yearL, 0, 5);
		h1.add(yearStart, 0, 6);
		h1.add(monthL, 1, 5);
		h1.add(monthStart, 1, 6);

		h1.add(dayL, 2, 5);
		h1.add(dayStart, 2, 6);
		h1.add(hourL, 3, 5);
		h1.add(timeStart, 3, 6);

		h1.add(yearLE, 0, 7);
		h1.add(monthLE, 1, 7);
		h1.add(dayLE, 2, 7);
		h1.add(hourLE, 3, 7);

		h1.add(yearEnd, 0, 8);
		h1.add(monthEnd, 1, 8);
		h1.add(dayEnd, 2, 8);
		h1.add(timeEnd, 3, 8);

		HBox h3 = new HBox();

		// Buttons initialized
		ok = new Button("Ok");
		ok.setDisable(true);
		cancel = new Button("Cancel");
		cancel.setDisable(true);
		h3.setAlignment(Pos.BASELINE_RIGHT);
		h3.setPadding(new Insets(10, 0, 10, 10));
		h3.getChildren().addAll(cancel, ok);
		h3.setSpacing(10);
		editeHolder.getChildren().addAll(pane2,h1, h3);
		return editeHolder;

	}
	
	
	public Button DeletEvent(Event e){
		delete = new Button("Delete");
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
			
					Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Delete Event");
							alert.setHeaderText("Look, a Confirmation Dialog");
							alert.setContentText("Are you sure you want to delete the event?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){
						if(eventListener.onDeleteEvent(e)){
							
							
						}
					} else {
						alert.close();
					    // ... user chose CANCEL or closed the dialog
					
				}
			}
			
			});
		
		return  delete;
	}

	/**
	 * help method to create a LocalDateTime from user input
	 * 
	 * @param year
	 *            - String
	 * @param month
	 *            - String
	 * @param day
	 *            - String
	 * @param hour
	 *            - String
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
				|| timeStart.getValue() == null) {
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
				|| timeEnd.getValue() == null) {
			return true;
		} else {
			return false;
		}
	}

}