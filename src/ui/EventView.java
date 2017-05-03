package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controls.EventListener;
import functions.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

	//Labels - View Event Window
	private Label title = new Label("Title:");
	private Label eventStart = new Label("Event start:");
	private Label eventEnd = new Label("Event end:");
	private Label des = new Label("Description:");


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
				eventWindow.setTitle("ADD EVENT WINDOW");
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
						 * created
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
				textFieldsStart.requestFocus();

			}
		});
		return addEvent;

	}

	public Button EditButton(Event e) {

		editEvent = new Button("Edit Info");
		editEvent.setMinSize(80, 30);
		editEvent.setFont(Font.font("Verdana", 15));
		editEvent.setTranslateX(60);
		editEvent.setTranslateY(-30);
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
						 * Setting id's to Months, to get them as text
						 */
						ids();

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

							titleText.setText("  " + name.getText());
							decText.setText(description.getText());
							dateStartText.setText(monthStart.getId()+" " + dayStart.getText()+ " " + yearStart.getText()+
							" " + timeStart.getValue());


							if(yearEnd.getText().length()!= 0){

						    dateEndText.setText(monthEnd.getId()+" " + dayEnd.getText()+ " " + yearEnd.getText()+
									" " + timeEnd.getValue());

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
							titleText.setText("  "+name.getText());
							decText.setText(description.getText());
							dateStartText.setText(monthStart.getId()+" " + dayStart.getText()+ " " + yearStart.getText()+
									" " + timeStart.getValue());

							dateEndText.setText(monthEnd.getId()+" " + dayEnd.getText()+ " " + yearEnd.getText()+
									" " + timeEnd.getValue());

							if (eventListener.onEditEventDuration(eventname, eventdescrip, startTime, endTime)) {
								// it should dispaly an alert if the input is
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
	 * Setting Id's to months to get the in text form in Event View Window
	 */

	private void ids (){
		if(monthStart.getText().equals("1")){
			monthStart.setId("Jan");
		}else if(monthStart.getText().equals("2")){
			monthStart.setId("Feb");
		}else if(monthStart.getText().equals("3")){
			monthStart.setId("Mar");
		}else if(monthStart.getText().equals("4")){
			monthStart.setId("April");
		}else if(monthStart.getText().equals("5")){
			monthStart.setId("May");
		}else if(monthStart.getText().equals("6")){
			monthStart.setId("Jun");
		}else if(monthStart.getText().equals("7")){
			monthStart.setId("Jul");
		}else if(monthStart.getText().equals("8")){
			monthStart.setId("Aug");
		}else if(monthStart.getText().equals("9")){
			monthStart.setId("Sep");
		}else if(monthStart.getText().equals("10")){
			monthStart.setId("Oct");
		}else if(monthStart.getText().equals("11")){
			monthStart.setId("Nov");
		}else if(monthStart.getText().equals("12")){
			monthStart.setId("Dec");
		}

		if(monthEnd.getText().equals("1")){
			monthEnd.setId("Jan");
		}else if(monthEnd.getText().equals("2")){
			monthEnd.setId("Feb");
		}else if(monthEnd.getText().equals("3")){
			monthEnd.setId("Mar");
		}else if(monthEnd.getText().equals("4")){
			monthEnd.setId("April");
		}else if(monthEnd.getText().equals("5")){
			monthEnd.setId("May");
		}else if(monthEnd.getText().equals("6")){
			monthEnd.setId("Jun");
		}else if(monthEnd.getText().equals("7")){
			monthEnd.setId("Jul");
		}else if(monthEnd.getText().equals("8")){
			monthEnd.setId("Aug");
		}else if(monthEnd.getText().equals("9")){
			monthEnd.setId("Sep");
		}else if(monthEnd.getText().equals("10")){
			monthEnd.setId("Oct");
		}else if(monthEnd.getText().equals("11")){
			monthEnd.setId("Nov");
		}else if(monthEnd.getText().equals("12")){
			monthEnd.setId("Dec");
		}
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

		yearStart = new TextField();
		yearStart.setPromptText("Start year");
		yearStart.setFont(Font.font("Times new Roman", 20));
		yearStart.setMaxWidth(102);

		monthStart = new TextField();
		monthStart.setPromptText("Start month");
		monthStart.setFont(Font.font("Times new Roman", 20));
		monthStart.setMaxWidth(120);

		dayStart = new TextField();
		dayStart.setPromptText("Start day");
		dayStart.setFont(Font.font("Times new Roman", 20));
		dayStart.setMaxWidth(97);

		timeStart = new ComboBox<String>();
		timeStart.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeStart.setPromptText("Start time");
		timeStart.setStyle("-fx-font: 16 timesnewroman;");
		timeStart.setPrefWidth(127);

		yearEnd = new TextField();
		yearEnd.setPromptText("End year");
		yearEnd.setFont(Font.font("Times new Roman", 20));
		yearEnd.setMaxWidth(102);

		monthEnd = new TextField();
		monthEnd.setPromptText("End month");
		monthEnd.setFont(Font.font("Times new Roman", 20));
		monthEnd.setMaxWidth(120);

		dayEnd = new TextField();
		dayEnd.setPromptText("End day");
		dayEnd.setFont(Font.font("Times new Roman", 20));
		dayEnd.setMaxWidth(97);

		timeEnd = new ComboBox<String>();
		timeEnd.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeEnd.setPromptText("End time");
		timeEnd.setStyle("-fx-font: 16 timesnewroman;");
		timeEnd.setPrefWidth(127);

		// Buttons initialized
		ok = new Button("Finish");
		ok.setPrefSize(135, 35);
		ok.setFont(Font.font("Times new Roman",20));

		cancel = new Button("Cancel");
		cancel.setPrefSize(135, 35);
		cancel.setTranslateX(175);
		cancel.setFont(Font.font("Times new Roman",20));

		delete = new Button("Delete");

		// HBox initialized

		HBox hb = new HBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		HBox hb3 = new HBox();
		HBox hb4 = new HBox();

		hb.getChildren().addAll(yearStart,monthStart,dayStart,timeStart);
		hb1.getChildren().addAll(yearEnd,monthEnd,dayEnd,timeEnd);
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

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm");
		String formattedStringS = e.getEventStart().format(formatter);

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
			window.getChildren().addAll(info, title, titleText, eventStart, dateStartText, des, decText, EditButton(e));
		} else {
			createEditEventWindow(e);
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm");
			String formattedStringE = e.getEventEnd().format(formatter2);

			eventEnd.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
			eventEnd.setTranslateX(8);
			eventEnd.setTranslateY(-35);

			dateEndText = new Text(formattedStringE);
			dateEndText.setFont(Font.font("Verdana", 15));;
			dateEndText.setTranslateY(-48);
			dateEndText.setTranslateX(8);
			window.getChildren().addAll(info, title, titleText, eventStart, dateStartText, eventEnd, dateEndText, des, decText, EditButton(e));

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

	public VBox createEditEventWindow(Event e) {
		VBox editeHolder = new VBox();
		editeHolder.setPrefSize(400, 400);
		editeHolder.setTranslateX(50);
		name = new TextField(e.getEventName());
		description = new TextArea(e.getEventDescription());
		Label nameL = new Label("Name");
		Label descriptionL = new Label("Description");

		int strYear = e.getEventStart().getYear();
		String year1 = Integer.toString(strYear);
		yearStart = new TextField(year1);
		yearStart.setMaxWidth(100);
		Label yearL = new Label("Start Year");

		int strMonth = e.getEventStart().getMonthValue();
		String month1 = Integer.toString(strMonth);
		monthStart = new TextField(month1);
		monthStart.setMaxWidth(100);
		Label monthL = new Label("Start Month");

		int strDay = e.getEventStart().getDayOfMonth();
		String days1 = Integer.toString(strDay);
		dayStart = new TextField(days1);
		dayStart.setMaxWidth(100);
		Label dayL = new Label("Start Day");

		int strHour = e.getEventStart().getHour();
		timeStart = new ComboBox<String>();
		timeStart.setMaxSize(100, 25);
		timeStart.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeStart.getSelectionModel().clearAndSelect(strHour+1);
		timeEnd = new ComboBox<String>();
		timeEnd.setMaxSize(100, 25);
		timeEnd.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		Label hourL = new Label("Start Time");

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
			yearEnd.setMaxWidth(100);

			int strMonthEnd = e.getEventEnd().getMonthValue();
			String month2 = Integer.toString(strMonthEnd);
			monthEnd = new TextField(month2);
			monthEnd.setMaxWidth(100);

			int strDayEnd = e.getEventEnd().getDayOfMonth();
			String days2 = Integer.toString(strDayEnd);
			dayEnd = new TextField(days2);
			dayEnd.setMaxWidth(100);


			timeEnd.getSelectionModel().clearAndSelect(strHour+1);
			timeEnd.setMaxSize(100, 25);
			// Change the so event can be edited:

			h2.getChildren().addAll(yearEnd, monthEnd, dayEnd, timeEnd/*hoursEnd*/);

		} else if (e.getEventEnd() == null) {
			yearEnd = new TextField("");
			yearEnd.setMaxWidth(100);
			monthEnd = new TextField("");
			monthEnd.setMaxWidth(100);
			dayEnd = new TextField("");
			dayEnd.setMaxWidth(100);
			h2.getChildren().addAll(yearEnd, monthEnd, dayEnd);

		}

		yearEnd.setDisable(true);
		monthEnd.setDisable(true);
		dayEnd.setDisable(true);
		timeEnd.setDisable(true);

		GridPane h1 = new GridPane();
		Label yearLE = new Label("End Year");
		yearLE.setFont(Font.font("Verdana", 13));
		Label monthLE = new Label("End Month");
		monthLE.setFont(Font.font("Verdana", 13));
		Label dayLE = new Label("End Day");
		dayLE.setFont(Font.font("Verdana", 13));
		Label hourLE = new Label("End Time");
		hourLE.setFont(Font.font("Verdana", 13));

		nameL.setFont(Font.font("Verdana", 17));
		descriptionL.setFont(Font.font("Verdana", 17));
		yearL.setFont(Font.font("Verdana", 13));
		monthL.setFont(Font.font("Verdana", 13));
		dayL.setFont(Font.font("Verdana", 13));
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

		vb3.getChildren().addAll(yearL,yearStart);
		vb3.setPadding(new Insets(0,5,0,0));

		vb4.getChildren().addAll(monthL,monthStart);
		vb4.setPadding(new Insets(0,5,0,0));

		vb5.getChildren().addAll(dayL,dayStart);
		vb5.setPadding(new Insets(0,5,0,0));

		vb6.getChildren().addAll(hourL,timeStart);
		vb6.setPadding(new Insets(0,5,0,0));

		vb7.getChildren().addAll(yearLE,yearEnd);
		vb7.setPadding(new Insets(0,5,0,0));

		vb8.getChildren().addAll(monthLE,monthEnd);
		vb8.setPadding(new Insets(0,5,0,0));

		vb9.getChildren().addAll(dayLE,dayEnd);
		vb9.setPadding(new Insets(0,5,0,0));

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
		ok = new Button("Finish");
		ok.setDisable(true);
		ok.setMinSize(80, 30);
		ok.setFont(Font.font("Verdana", 15));
		cancel = new Button("Cancel");
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