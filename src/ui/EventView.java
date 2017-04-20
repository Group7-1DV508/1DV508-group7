package ui;

import java.time.LocalDateTime;

import controls.EventControl;
import controls.EventListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventView {
	
	private EventListener eventListener;
	
	//TextFields - Add Event Window
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
    
    //Buttons - Add Event Window
    private Button ok;
    private Button cancel;
	
	 public void addListener(EventListener eventList) {
			eventListener = eventList;
	 }
	 /**
	  * method to return the root to the application view
	  * @return HBox, root
	  */
	 public GridPane getRoot(){
		 GridPane root = new GridPane();
		 root.setAlignment(Pos.BOTTOM_CENTER);					//alignment in the root
		 Button addEvent = new Button("Add Event");				//button for add event
		 root.add(addEvent, 0, 1);
		 addEvent.setOnAction(new EventHandler<ActionEvent>() {
	           
	            @Override
	            public void handle(ActionEvent event) {
	               final Stage eventWindow = new Stage();
	           
	               GridPane textFieldsStart = createAddEventWindow();
	               
	    
	               /*
	                * When "Ok" button is clicked, textfields are fetched and converted to String and LocalDateTime
	                * then application listener is calling the onAddEvent-method
	                */
	               ok.setOnAction(new EventHandler<ActionEvent>() {
	    	           
	   	            	@Override
	   	            	public void handle(ActionEvent event) {
	   	            		if (isNeededFieldEmpty()) {
	   	            			Alert emptyFieldError = new Alert(Alert.AlertType.ERROR, "Name, Description and Start Date can't be empty.");
	   	            			emptyFieldError.showAndWait();
	   	            		}
	   	            		else if (isNotDurationEvent()) {
	   	            			LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(), dayStart.getText(), hoursStart.getText());
		   	            		String eventname = name.getText();
		   	            		String eventdescrip = description.getText();
		   	            		
		   	            		if (eventListener.onAddEvent(eventname, eventdescrip, startTime)){
		   	            			//if eventlistner found issues with the parameters, close window.
		   	            			eventWindow.close();
		   	            		}
	   	            		}
	   	            		else {
	   	            			LocalDateTime startTime = createLocalDateTime(yearStart.getText(), monthStart.getText(), dayStart.getText(), hoursStart.getText());
		   	            		LocalDateTime endTime = createLocalDateTime(yearEnd.getText(), monthEnd.getText(), dayEnd.getText(), hoursEnd.getText());
	   	            			String eventname = name.getText();
		   	            		String eventdescrip = description.getText();
		   	            		
		   	            		
		   	            		if (eventListener.onAddEventDuration(eventname, eventdescrip, startTime, endTime)){
		   	            			//if eventlistner found issues with the parameters, close window.
		   	            			eventWindow.close();
		   	            		}
	   	            			
	   	            		}
	   		
	   	            	}
	               });
	               
	               cancel.setOnAction(new EventHandler<ActionEvent>() {
	    	           
	   	            	@Override
	   	            	public void handle(ActionEvent event) {
	   	            		//when cancel button is clicked, close the window
	   	            		eventWindow.close();
	   	            	}
	               });
	   	          
	               //create scene and show the popup window for create Event
	               Scene eventScene = new Scene(textFieldsStart);
	               eventWindow.setScene(eventScene);
	               eventWindow.show();
	               
	               
	            }
	        });
		//return the root created.
		return root;
		 
		
		}
	 private GridPane createAddEventWindow() {
		 GridPane pane = new GridPane();
		 
         name = new TextField();				//TextField to enter name of event
         description = new TextField();	//textfield to enter description of event
		 
		 yearStart = new TextField();	//textField to enter year
         monthStart = new TextField();		//textField to enter month
         dayStart = new TextField();		//textField to enter dayOfMonth
         hoursStart = new TextField();		//textField to enter hour of the day
		
         yearEnd = new TextField();
         monthEnd = new TextField();
         dayEnd = new TextField();
         hoursEnd = new TextField();
         
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
         
         ok = new Button();
         cancel = new Button();
         
         
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
	 
	 private LocalDateTime createLocalDateTime(String year, String month, String day, String hour) {
		 String localDate;
		 LocalDateTime time = null;
		 
		 for (int i = 0; i < 4-year.length() ; i++ ) {
			 year = "0"+year;
		 }
		 localDate = year+"-"+month+"-"+day+"T"+hour+":00:00";
		 
		 try {
    			time = LocalDateTime.parse(localDate);
    		}
    		catch (Exception e) {
    			//if input is wrong, show error (popup window) message.
    			Alert fieldError = new Alert(Alert.AlertType.ERROR, "Date input is not correct.");
    			fieldError.showAndWait();
    		}
		 return time;
		 
	 }
	 
	 private boolean isNeededFieldEmpty() {
		 if (name.getText().isEmpty() || description.getText().isEmpty()) {
			 return true;
		 }
		 else if (yearStart.getText().isEmpty() || monthStart.getText().isEmpty() || dayStart.getText().isEmpty() || hoursStart.getText().isEmpty()) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 
	 private boolean isNotDurationEvent() {
		 if (yearEnd.getText().isEmpty() || monthEnd.getText().isEmpty() || dayEnd.getText().isEmpty() || hoursEnd.getText().isEmpty()) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }

	
	 

}
