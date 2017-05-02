package ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class Alerts {
	
	Alert alert = new Alert(AlertType.ERROR);
	
public boolean Checkname ( String name, String description , String year, String month){
	
	 
	
	if (name.length() > 30){
		
		alert.setTitle("Erro");
		alert.setContentText("Plase, ");
		alert.showAndWait();
		return false;}	
	else if ( description.length() > 500){
		alert.setTitle("Error");
		alert.setContentText("Plase, dec");
		alert.showAndWait();
		return false;
	}else if (year.length() != 4 ){
		alert.setTitle("Error");
		alert.setContentText("Plase, dec");
		alert.showAndWait();
		return false;
	}
	
	
	
	else{
		return true;
	}
	
	//}else if (year.length() > 4){
		
		//return false ;
	//}
	

}
	
	

}
