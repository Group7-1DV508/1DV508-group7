package ui;

import java.time.LocalDateTime;

import controls.EventListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
	 
	 public HBox getRoot(){
		 HBox root = new HBox();
		 Button addEvent = new Button("Add Event");
		 root.getChildren().add(addEvent);
		 addEvent.setOnAction(new EventHandler<ActionEvent>() {
	           
	            @Override
	            public void handle(ActionEvent event) {
	               final Stage eventWindow = new Stage();
	               
	               
	               final TextField year = new TextField("YYYY");
	               final TextField month = new TextField("MM");
	               final TextField day = new TextField("DD");
	               final TextField hours = new TextField("HH");
	               
	               HBox textFieldsStart = new HBox();
	               textFieldsStart.getChildren().addAll(year, month, day, hours);
	              
	               /*final TextField yearEnd = new TextField("YYYY");
	               final TextField monthEnd = new TextField("MM");
	               final TextField dayEnd = new TextField("DD");
	               final TextField hoursEnd = new TextField("HH");
	               HBox textFieldsEnd = new HBox();
	               textFieldsEnd.getChildren().addAll(yearEnd, monthEnd, dayEnd, hoursEnd);
	               */
	               final TextField name = new TextField("Name");
	               final TextField description = new TextField("Description");
	               HBox eventName = new HBox();
	               eventName.getChildren().addAll(name, description);
	               
	               Button ok = new Button("Ok");
	               Button cancle = new Button("Cancle");
	               HBox buttons = new HBox();
	               buttons.getChildren().addAll(ok, cancle);
	               
	               ok.setOnAction(new EventHandler<ActionEvent>() {
	    	           
	   	            	@Override
	   	            	public void handle(ActionEvent event) {
	   	            		LocalDateTime startTime = null;
	   	            		String localDate;
	   	            		String eventname = "";
	   	            		String eventdescrip = "";
	   	            		try {
	   	            			localDate = year.getText()+"-"+month.getText()+"-"+day.getText()+"-"+hours.getText()+"-00";
	   	            			eventname = name.getText();
	   	            			eventdescrip = description.getText();
	   	            			startTime = LocalDateTime.parse(localDate);
	   	            		}
	   	            		catch (Exception e) {
	   	            			
	   	            		}
	   	            		
	   	            		if (eventListener.onAddEvent(eventname, eventdescrip, startTime)){
	   	            			eventWindow.close();
	   	            		}
	   	            	}
	               });
	               
	               VBox view = new VBox();
	               view.getChildren().addAll(eventName, textFieldsStart, buttons);
	               
	               Scene eventScene = new Scene(view);
	               eventWindow.setScene(eventScene);
	               eventWindow.show();
	               
	               
	            }
	        });
		 
		return root;
		 
		 
		 
		
		
		}

	
	 

}
