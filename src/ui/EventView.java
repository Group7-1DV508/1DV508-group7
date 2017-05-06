package ui;

<<<<<<< HEAD
import java.time.LocalDate;
=======
>>>>>>> refs/remotes/origin/master
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
<<<<<<< HEAD
import javafx.scene.layout.ColumnConstraints;
=======
import javafx.scene.control.Alert.AlertType;
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
	private final Stage eventWindow = new Stage();
	// TextFields - Add Event Window
	private TextField name;
	private TextField description;
	private TextArea  descriptionArea;

	private TextField yearStart;
	private TextField monthStart;
	private TextField dayStart;
	

	private TextField yearEnd;
	private TextField monthEnd;
	private TextField dayEnd;
	
=======

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
>>>>>>> refs/remotes/origin/master


	// Buttons
	private Button addEvent = new Button("Add Event");
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
<<<<<<< HEAD
	private DatePicker checkInDatePickerStart;
	private DatePicker checkInDatePickerEnd;
=======

	//Labels - View Event Window
	private Label title = new Label("Title:");
	private Label eventStart = new Label("Event start:");
	private Label eventEnd = new Label("Event end:");
	private Label des = new Label("Description:");
	private DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  HH:mm");
	private DatePicker checkInDatePickerStart = new DatePicker();
	private DatePicker checkInDatePickerEnd = new DatePicker();

>>>>>>> refs/remotes/origin/master

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

<<<<<<< HEAD
	
	
	
	public VBox createAddEventWindow(){
		
		VBox vbox = new VBox();
			 vbox.setSpacing(10);
			 vbox.setPrefSize(400, 400);
			 vbox.setStyle("-fx-padding: 10;");
			 
		GridPane pane = new GridPane ();
	
	    
	     	name = new TextField();
	     	
	     	descriptionArea = new TextArea();
	     	descriptionArea.setPrefSize(200, 200);
	     	descriptionArea.setWrapText(true);
	     	
	     	/* Limit the number of characters*/
	     	final int nameMAX_CHARS = 40 ;
	     	name.setTextFormatter(new TextFormatter<String>(change -> 
            change.getControlNewText().length() <= nameMAX_CHARS ? change : null));
	     	final int desMAX_CHARS = 300 ;
	     	descriptionArea.setTextFormatter(new TextFormatter<String>(change -> 
	            change.getControlNewText().length() <= desMAX_CHARS ? change : null));
	     
	     Label nameL = new Label("Name");
	     Label descriptionL = new Label("Description");
	     Label EventStart = new Label("Event Start");
	     Label EventEnd = new Label("Event End");
	     
	     
	     timeStart = new ComboBox<String>();
		 timeStart.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		
		 timeEnd = new ComboBox<String>();
		 timeEnd.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		 
		 
		 pane.add(nameL, 0, 1);
		 pane.add(name , 0, 2);
		 
		 
		 pane.add(EventStart, 0, 3);
		 pane.add(checkInDatePickerStart, 0, 4);
		 pane.add(timeStart, 1, 4);
		 
		 pane.add(EventEnd, 0, 5);
		 pane.add(checkInDatePickerEnd, 0, 6);
		 pane.add(timeEnd, 1, 6);
		 
		
		 

		 HBox buttonHolder = new HBox();
		 	  buttonHolder.getChildren().addAll(ok ,cancel);
		 	  buttonHolder.setSpacing(20);
		 	  buttonHolder.setPadding(new Insets(10,10,10,10));
		 	 
		 vbox.getChildren( ).addAll(pane, descriptionL,descriptionArea,buttonHolder);
		 
		 
		return vbox;
	
		
	}
	
	
	
	
	
	public Button getAddEventButton(){
        
        
        
	      Button addEvent = new Button("Add Event");
	 		     addEvent.setMinSize(75, 30);
	 		     checkInDatePickerStart = new DatePicker();
	 		     checkInDatePickerEnd = new DatePicker();
	 		     ok = new Button("Ok");
	 		   	 cancel = new Button("Cancel"); 
	 		     

	 		     
	 	  addEvent.setOnAction(new EventHandler<ActionEvent>() {
	 		       
	 		    		  
	       			@Override
	 			  public void handle(ActionEvent event) {
	       				final Stage eventWindow = new Stage();
	 				
	       				VBox textFieldsStart = createAddEventWindow();
	       				checkInDatePickerStart.getEditor().clear();
	       				checkInDatePickerEnd.getEditor().clear();
	 						
						
			 ok.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								if (isNeededFieldEmpty()){
									
								
								if (checkInDatePickerEnd.getValue() == null){
								String eventname = name.getText();
								String eventdescrip = descriptionArea.getText();

								 LocalDate date = checkInDatePickerStart.getValue()  ;
						         
								
								
						         int  year =  date.getYear() ;
						         String year1 = Integer.toString(year);
						         int  month =  date.getMonthValue();
						         String month1 = Integer.toString(month);
						         int  day =  date.getDayOfMonth() ;
						         String day1 = Integer.toString(day);
						         
						         LocalDateTime startTime = createLocalDateTime( year1 ,month1 ,day1, timeStart.getValue() );
							     	
						         if (eventListener.onAddEvent(eventname, eventdescrip, startTime)) {
										eventWindow.close();
									}
						         
								}else{
									String eventname = name.getText();
									String eventdescrip = descriptionArea.getText();
									
									LocalDate date = checkInDatePickerStart.getValue();
							        
							         int  year =  date.getYear() ;
							         String year1 = Integer.toString(year);
							         int  month =  date.getMonthValue();
							         String month1 = Integer.toString(month);
							         int  day =  date.getDayOfMonth() ;
							         String day1 = Integer.toString(day);
							         
							         
							         LocalDateTime startTime = createLocalDateTime( year1 ,month1 ,day1, timeStart.getValue() );
									
									
									
							         LocalDate date2= checkInDatePickerEnd.getValue();
							         int  yearEnd =  date2.getYear() ;
							         String yearEnd1 = Integer.toString(yearEnd);
							         int  monthEnd =  date2.getMonthValue();
							         String monthEnd1 = Integer.toString(monthEnd);
							         int  dayEnd =   date2.getDayOfMonth() ;
							         String dayEnd1 = Integer.toString(dayEnd);
							       
							         LocalDateTime endTime = createLocalDateTime(yearEnd1, monthEnd1,dayEnd1, timeEnd.getValue());
										

										if (eventListener.onAddEventDuration(eventname, eventdescrip, startTime, endTime)) {
											eventWindow.close();
									}

									
									
								}
								}
							}
			 			});
							
							
			 				cancel.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									eventWindow.close();
								}
							});
							
						
	 				
	 				
	 				 
	 				
	 				Scene eventScene = new Scene(textFieldsStart);
					eventWindow.setScene(eventScene);
					eventWindow.setTitle("Add Event");
					eventWindow.show();

	 		} 
	 	});
	         
	    
	        
		
	 	return addEvent;
	}
	
	
	
	
	
	
	public Button EditButton(Event e) {

		editEvent = new Button("Edit");
=======
	public Button getAddEventButton() {
		addEvent.setPadding(new Insets(5));

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
						}
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
								if (eventListener.onAddEvent(eventname, eventdescrip, startTime)) {
									eventWindow.close();
								}
								else {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Error in chosing time");
									alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
									alert.show();
								}
							}
							/*
							 * If the Event has End Time an Event with duration is
							 * created
							 */
							else {
								LocalDateTime endTime = createLocalDateTime(checkInDatePickerEnd.getValue().getYear()+"", 
										checkInDatePickerEnd.getValue().getMonthValue()+"",
										checkInDatePickerEnd.getValue().getDayOfMonth()+"",
										timeStart.getValue());
								if (startTime.compareTo(endTime) > 0) {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Error in event dates");
									alert.setHeaderText("Start date has to be earlier than end date!");
									alert.show();
								}
								
								else  {
									if (eventListener.onAddEventDuration(eventname, eventdescrip, startTime, endTime)) {
									eventWindow.close();
									}
									else {
										Alert alert = new Alert(AlertType.ERROR);
										alert.setTitle("Error in chosing time");
										alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
										alert.show();
									}
								}
	
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
	
	public void setDisable (boolean notShown) {
		addEvent.setDisable(notShown);
	}

	public Button EditButton(Event e) {
		editEvent = new Button("Edit Info");
		editEvent.setMinSize(80, 30);
		editEvent.setFont(Font.font("Verdana", 15));
		editEvent.setTranslateX(10);
		editEvent.setTranslateY(30);
>>>>>>> refs/remotes/origin/master
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
									"Name, Description and date fields can't be empty.");
							emptyFieldError.showAndWait();
						}
			
						/*
						 * If the event doesn't have End Date an non duration
						 * Event is created
						 */
<<<<<<< HEAD
						if (!e.isDuration()) {

							LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(),
									dayStart.getText(), timeStart.getValue());
							String eventname = name.getText();
							String eventdescrip = description.getText();
							
						 //  if( alert.Checkname(eventname, eventdescrip)){
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
=======
						else {
							LocalDateTime startTime = createLocalDateTime(checkInDatePickerStart.getValue().getYear()+"", 
									checkInDatePickerStart.getValue().getMonthValue()+"",
									checkInDatePickerStart.getValue().getDayOfMonth()+"",
									timeStart.getValue());
				                String eventname = name.getText();
				                String eventdescrip = description.getText();
							
							if (!e.isDuration()) {
			
			                titleText.setText("  " + name.getText());
			                decText.setText(description.getText());
			                dateStartText.setText(startTime.format(format));
			
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
				                else {
				                  Alert alert = new Alert(AlertType.ERROR);
				                  alert.setTitle("Error in chosing time");
				                  alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
				                  alert.show();
								}
				            }
			              /*
			               * If the Event has End Time an Event with duration is
			               * created
			               */
			              else {
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
			                }
			
			                else {
			                   if(eventListener.onEditEventDuration(eventname, eventdescrip, startTime, endTime)) {
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
			                  else {
			                      Alert alert = new Alert(AlertType.ERROR);
			                      alert.setTitle("Error in chosing time");
			                      alert.setHeaderText("It appears your are trying to create an event outside of timeline!");
			                      alert.show();
			                  }
			                }
			              }
			            }
			          }
					});
>>>>>>> refs/remotes/origin/master

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
	 * Delete button responsible for delete selected event
	 * @param e event to be deleted
	 * @param s closes event information window of delete event
	 * @return button with set action on it.
	 */
	public Button getDeleteButton(Event e, Stage s) {
		delete = new Button("Delete event");
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

<<<<<<< HEAD
	
=======
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
		
     	/* Limit the number of characters*/
     	final int nameMAX_CHARS = 40;
     	name.setTextFormatter(new TextFormatter<String>(change -> 
        change.getControlNewText().length() <= nameMAX_CHARS ? change : null));
     	final int desMAX_CHARS = 300;
     	description.setTextFormatter(new TextFormatter<String>(change -> 
            change.getControlNewText().length() <= desMAX_CHARS ? change : null));

//		yearStart = new TextField();
//		yearStart.setPromptText("Start year");
//		yearStart.setFont(Font.font("Times new Roman", 20));
//		yearStart.setMaxWidth(102);
//
//		monthStart = new TextField();
//		monthStart.setPromptText("Start month");
//		monthStart.setFont(Font.font("Times new Roman", 20));
//		monthStart.setMaxWidth(120);
//
//		dayStart = new TextField();
//		dayStart.setPromptText("Start day");
//		dayStart.setFont(Font.font("Times new Roman", 20));
//		dayStart.setMaxWidth(97);
     	
     	

		timeStart = new ComboBox<String>();
		timeStart.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeStart.setPromptText("Start time");
		timeStart.setStyle("-fx-font: 16 timesnewroman;");
		timeStart.setPrefWidth(127);

//		yearEnd = new TextField();
//		yearEnd.setPromptText("End year");
//		yearEnd.setFont(Font.font("Times new Roman", 20));
//		yearEnd.setMaxWidth(102);
//
//		monthEnd = new TextField();
//		monthEnd.setPromptText("End month");
//		monthEnd.setFont(Font.font("Times new Roman", 20));
//		monthEnd.setMaxWidth(120);
//
//		dayEnd = new TextField();
//		dayEnd.setPromptText("End day");
//		dayEnd.setFont(Font.font("Times new Roman", 20));
//		dayEnd.setMaxWidth(97);

		timeEnd = new ComboBox<String>();
		timeEnd.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeEnd.setPromptText("End time");
		timeEnd.setStyle("-fx-font: 16 timesnewroman;");
		timeEnd.setPrefWidth(127);
	    
		checkInDatePickerStart.setStyle("-fx-font: 16 timesnewroman;");
		checkInDatePickerStart.setPromptText("Event start");
		checkInDatePickerEnd.setStyle("-fx-font: 16 timesnewroman;");
		checkInDatePickerEnd.setPromptText("Event end");

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

>>>>>>> refs/remotes/origin/master
	public void ViewEventInfo(Event e) {
		//final Stage eventWindow = new Stage();

		VBox window = new VBox();
<<<<<<< HEAD
			 window.setSpacing(10);
			 window.setPrefSize(300, 300);
		HBox buttonHolder = new HBox ();
		buttonHolder.setSpacing(10);

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
			buttonHolder.getChildren().addAll(	EditButton(e),  DeletEvent( e));
			window.getChildren().addAll(info,Title, titleText,Description, decText,start,dateStartText,end,dateEndText,buttonHolder);
					
		
		
		} else {
			createEditEventWindow(e);
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm");
			String formattedStringE = e.getEventEnd().format(formatter2);
			dateEndText = new Text(  formattedStringE);
			buttonHolder.getChildren().addAll(	EditButton(e),  DeletEvent( e));
			window.getChildren().addAll(info,Title, titleText,Description, decText,start,dateStartText,end,dateEndText, buttonHolder);
			
=======
		HBox hb = new HBox();
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
>>>>>>> refs/remotes/origin/master

		}

		// get delete Button
		
		HBox all = new HBox();
<<<<<<< HEAD
		all.setPrefSize(700, 200);
=======
		all.setPrefSize(550, 190);
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
=======
		editeHolder.setTranslateX(50);
>>>>>>> refs/remotes/origin/master
		name = new TextField(e.getEventName());
		description = new TextArea(e.getEventDescription());
		Label nameL = new Label("Name");
		Label descriptionL = new Label("Description");

		int strYear = e.getEventStart().getYear();
		String year1 = Integer.toString(strYear);
		yearStart = new TextField(year1);
<<<<<<< HEAD
		Label yearL = new Label("Year");
=======
		yearStart.setMaxWidth(100);
		Label yearL = new Label("Start Year");
>>>>>>> refs/remotes/origin/master

		int strMonth = e.getEventStart().getMonthValue();
		String month1 = Integer.toString(strMonth);
		monthStart = new TextField(month1);
<<<<<<< HEAD
		Label monthL = new Label("Month");
=======
		monthStart.setMaxWidth(100);
		Label monthL = new Label("Start Month");
>>>>>>> refs/remotes/origin/master

		int strDay = e.getEventStart().getDayOfMonth();
		String days1 = Integer.toString(strDay);
		dayStart = new TextField(days1);
		dayStart.setMaxWidth(100);
		Label dayL = new Label("Start Day");

		int strHour = e.getEventStart().getHour();
		timeStart = new ComboBox<String>();
<<<<<<< HEAD
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
=======
		timeStart.setMaxSize(100, 25);
		timeStart.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		timeStart.getSelectionModel().clearAndSelect(strHour);
		timeEnd = new ComboBox<String>();
		timeEnd.setMaxSize(100, 25);
		timeEnd.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00",
				"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00",
				"20:00", "21:00", "22:00", "23:00");
		Label hourL = new Label("Start Time");
>>>>>>> refs/remotes/origin/master

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
<<<<<<< HEAD
=======
			yearEnd.setMaxWidth(100);
>>>>>>> refs/remotes/origin/master

			int strMonthEnd = e.getEventEnd().getMonthValue();
			String month2 = Integer.toString(strMonthEnd);
			monthEnd = new TextField(month2);
<<<<<<< HEAD
=======
			monthEnd.setMaxWidth(100);
>>>>>>> refs/remotes/origin/master

			int strDayEnd = e.getEventEnd().getDayOfMonth();
			String days2 = Integer.toString(strDayEnd);
			dayEnd = new TextField(days2);
<<<<<<< HEAD

			
			timeEnd.getSelectionModel().clearAndSelect(strHour+1);
=======
			dayEnd.setMaxWidth(100);

			int strHourEnd = e.getEventEnd().getHour();

			timeEnd.getSelectionModel().clearAndSelect(strHourEnd);
			timeEnd.setMaxSize(100, 25);
>>>>>>> refs/remotes/origin/master
			// Change the so event can be edited:

			h2.getChildren().addAll(yearEnd, monthEnd, dayEnd, timeEnd/*hoursEnd*/);

		} else if (e.getEventEnd() == null) {
			yearEnd = new TextField("");
			yearEnd.setMaxWidth(100);
			monthEnd = new TextField("");
			monthEnd.setMaxWidth(100);
			dayEnd = new TextField("");
<<<<<<< HEAD
=======
			dayEnd.setMaxWidth(100);
>>>>>>> refs/remotes/origin/master
			h2.getChildren().addAll(yearEnd, monthEnd, dayEnd);

		}

		yearEnd.setDisable(true);
		monthEnd.setDisable(true);
		dayEnd.setDisable(true);
		timeEnd.setDisable(true);

		GridPane h1 = new GridPane();
<<<<<<< HEAD
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
=======
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
>>>>>>> refs/remotes/origin/master

		HBox h3 = new HBox();

		// Buttons initialized
		ok = new Button("Finish");
		ok.setDisable(true);
		ok.setMinSize(80, 30);
		ok.setFont(Font.font("Verdana", 15));
		cancel = new Button("Cancel");
		cancel.setDisable(true);
<<<<<<< HEAD
		h3.setAlignment(Pos.BASELINE_RIGHT);
		h3.setPadding(new Insets(10, 0, 10, 10));
		h3.getChildren().addAll(cancel, ok);
		h3.setSpacing(10);
		editeHolder.getChildren().addAll(pane2,h1, h3);
=======
		cancel.setMinSize(80, 30);
		cancel.setFont(Font.font("Verdana", 15));

		hb3.setPadding(new Insets(10, 0, 10, 10));
		hb3.getChildren().addAll(ok, cancel);
		hb3.setSpacing(10);
		hb3.setTranslateY(20);
		editeHolder.getChildren().addAll(h1, hb3);
		editeHolder.setMinSize(700, 500);
>>>>>>> refs/remotes/origin/master
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
							
							eventWindow.close();
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
<<<<<<< HEAD
	 * 
=======
	 *
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
		
		Alert alert = new Alert(AlertType.ERROR);
		if (name.getText().isEmpty() || descriptionArea.getText().isEmpty()) {
			
			
			alert.setTitle("Error");
			alert.setContentText("Please, fill in Title and description.");
			alert.showAndWait();
			
=======
		if (name.getText().isEmpty() || description.getText().isEmpty()) {
			return true;
		} else if (checkInDatePickerStart == null
				|| timeStart.getValue() == null) {
			return true;
		} else {
>>>>>>> refs/remotes/origin/master
			return false;
			
			
		} else if (checkInDatePickerStart.getValue()== null  || timeStart.getValue()== null) {

			alert.setTitle("Error");
			alert.setContentText("Please, fill in date and time.");
			alert.showAndWait();
			return false;
		} else  {
			return true;
		}
	}
		

	/**
	 * help method to check if the Event to be created is a duration event or
	 * not
	 *
	 * @return boolean, true if it is not a duration event otherwise false
	 */

<<<<<<< HEAD
	
=======
	private boolean isNotDurationEvent() {
		if (checkInDatePickerStart == null|| timeEnd.getValue() == null) {
			return true;
		} else {
			return false;
		}
	}
>>>>>>> refs/remotes/origin/master

} 