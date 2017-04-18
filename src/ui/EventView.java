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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventView {
	
	private EventListener eventListener;
	
	
	 public void addListener(EventListener eventList) {
			eventListener = eventList;
	 }
	 /**
	  * method to return the root to the application view
	  * @return HBox, root
	  */
	 public HBox getRoot(){
		 HBox root = new HBox();
		 root.setAlignment(Pos.BOTTOM_CENTER);					//alignment in the root
		 Button addEvent = new Button("Add Event");				//button for add event
		 root.getChildren().add(addEvent);
		 addEvent.setOnAction(new EventHandler<ActionEvent>() {
	           
	            @Override
	            public void handle(ActionEvent event) {
	               final Stage eventWindow = new Stage();
	               
	               
	               final TextField year = new TextField("YYYY");	//textField to enter year
	               final TextField month = new TextField("MM");		//textField to enter month
	               final TextField day = new TextField("DD");		//textField to enter dayOfMonth
	               final TextField hours = new TextField("HH");		//textField to enter hour of the day
	               
	               HBox textFieldsStart = new HBox();
	               //HBox containing all textFields for start time of the event
	               textFieldsStart.getChildren().addAll(year, month, day, hours);
	              
	               /*final TextField yearEnd = new TextField("YYYY");
	               final TextField monthEnd = new TextField("MM");
	               final TextField dayEnd = new TextField("DD");
	               final TextField hoursEnd = new TextField("HH");
	               HBox textFieldsEnd = new HBox();
	               textFieldsEnd.getChildren().addAll(yearEnd, monthEnd, dayEnd, hoursEnd);
	               */
	               final TextField name = new TextField("Name");				//TextField to enter name of event
	               final TextField description = new TextField("Description");	//textfield to enter description of event
	               HBox eventName = new HBox();
	               //Hbox containing name and description textfields.
	               eventName.getChildren().addAll(name, description);
	               
	               Button ok = new Button("Ok");				//button to accept event
	               Button cancel = new Button("Cancel");		//button to decline event
	               HBox buttons = new HBox();
	               //Hbox with buttons
	               buttons.getChildren().addAll(ok, cancel);
	               
	               /*
	                * When "Ok" button is clicked, textfields are fetched and converted to String and LocalDateTime
	                * then application listener is calling the onAddEvent-method
	                */
	               ok.setOnAction(new EventHandler<ActionEvent>() {
	    	           
	   	            	@Override
	   	            	public void handle(ActionEvent event) {
	   	            		//initialize the variables
	   	            		LocalDateTime startTime = null;
	   	            		String localDate;
	   	            		String eventname = "";
	   	            		String eventdescrip = "";
	   	            		try {
	   	            			//try to convert to LocalDateTime and String
	   	            			localDate = year.getText()+"-"+month.getText()+"-"+day.getText()+"T03:"+hours.getText()+":00";
	   	            			eventname = name.getText();
	   	            			eventdescrip = description.getText();
	   	            			startTime = LocalDateTime.parse(localDate);
	   	            		}
	   	            		catch (Exception e) {
	   	            			//if input is wrong, show error (popup window) message.
	   	            			Alert fieldError = new Alert(Alert.AlertType.ERROR, "Can't create Event, check input.");
	   	            			fieldError.showAndWait();
	   	            		}
	   	            		
	   	            		if (eventListener.onAddEvent(eventname, eventdescrip, startTime)){
	   	            			//if eventlistner found issues with the parameters, close window.
	   	            			eventWindow.close();
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
	   	            	
	               
	               VBox view = new VBox();
	               view.getChildren().addAll(eventName, textFieldsStart, buttons);
	               
	               //create scene and show the popup window for create Event
	               Scene eventScene = new Scene(view);
	               eventWindow.setScene(eventScene);
	               eventWindow.show();
	               
	               
	            }
	        });
		//return the root created.
		return root;
		 
		 
		 
		
		
		}

	
	 

}
