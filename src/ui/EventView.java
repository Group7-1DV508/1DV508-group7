package ui;

import java.time.LocalDateTime;

import controls.EventListener;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EventView {
	
	private EventListener eventListener;
	
	public TextField tF1 = new TextField ();
	public TextField tF2 = new TextField ();
	public TextField tF3 = new TextField ();
	public TextField tF4 = new TextField ();
	public TextField tF5 = new TextField ();
	public TextField tF6 = new TextField ();
	public TextField tF7 = new TextField ();
	public   VBox root = new VBox();
	public   HBox b5 = new HBox();
	public 	 VBox b3 = new VBox();
	LocalDateTime a;
	LocalDateTime a2 ;
	 public  VBox  create() {
		 
		 VBox holder = new VBox();
		 
	}
	 
	 public void addListener(EventListener eventList) {
			eventListener = eventList;
	 }
		 
		 HBox b1 = new HBox();//hold year month day 
			  b1.setSpacing(20);
			  b1.setPrefSize(300,50 );
			  b1.setPadding(new Insets(30,30,30,10));
			  
		 HBox b2 = new HBox();
		      b2.setPrefSize(300,200 );
		
			  b3.setSpacing(20); //hold buttons 
			  b3.setPrefSize(300,100 );
			  b3.setPadding(new Insets(10,17,10,10));
			  
		 HBox b4 = new HBox(); //holld time
		 	  b4.setPrefSize(200,50 );
		 	  

			  b5.setStyle("-fx-background-color: rgb(224, 152, 249);");
			  b5.setPadding(new Insets (10,5,10,5));
			  b5.setAlignment(Pos.CENTER);
			  b5.setPrefSize(300,200 );
		 
		
		 
		
		 Button btn =new Button("Save");
		 		btn.setStyle("-fx-font: 16 arial; -fx-base: #dd85fc;");
		 
		 
		 
		 
		 
		 VBox v1 =new VBox();	
		 Label L1 = new Label ("Year");
		 	  tF1.setPrefSize(80, 10);
		 	  v1.getChildren().addAll(L1,tF1);

		
		 
		 VBox v2 =new VBox();
		 Label L2 = new Label ("Month");
		       tF2.setPrefSize(40, 10);
	           v2.getChildren().addAll(L2,tF2);
		 

		 VBox   v3 =new VBox();
		 Label L3 = new Label ("Day");
		   	   tF3.setPrefSize(40, 10);
			   v3.getChildren().addAll(L3,tF3);
		 
		 
		 VBox v4 =new VBox();
		 Label tL = new Label("Describe");
		 TextField t1 = new TextField ();
			 t1.setPrefSize(300, 150);
		String describe = t1.getText();
			 b2.setPadding(new Insets(10,10,10,10));
			 v4.getChildren().addAll(tL,t1);
		 
		 VBox v5 =new VBox();
		 Label L4 = new Label("Time");
		 
		 Text timeT = new Text(":");
		 timeT.setFont(new Font(30));
		 TextField tF5 = new TextField ();
		 tF4.setPrefSize(40, 10);
		 tF5.setPrefSize(40, 10);
		 b4.setPadding(new Insets(10,10,10,10));
		 v5.getChildren().addAll(L4,tF4);
		
		 
		 Text timeT2 = new Text("To");
		 Text timeT3 = new Text(":");
		
		 timeT2.setFont(new Font(25));
		 timeT3.setFont(new Font(30));
		 tF6.setPrefSize(40, 10);
		 tF7.setPrefSize(40, 10);
		 b4.setAlignment(Pos.BOTTOM_LEFT);
		 
		 
		 TextField title= new TextField("add title");
		 title.setPrefSize(30, 10);
		 String titleS = title.getText();
		 //*****************//
		 btn.setOnAction((ActionEvent e) -> {	
			 Alert alert = new Alert(AlertType.ERROR); 
					
			 
				 String year = tF1.getText();
				 int year1 =Integer.parseInt(year);
				 String mon = tF2.getText();
				 int mon1 =Integer.parseInt(mon);
				 String day = tF3.getText();
				 int day1 =Integer.parseInt(day);
				 String hour = tF4.getText();
				 int hour1 =Integer.parseInt(hour);
				 String min = tF5.getText();
				 int min1 =Integer.parseInt(min);
				 
				 String hour2 = tF6.getText();
				 int hour12 =Integer.parseInt(hour2);
				 String min2 = tF7.getText();
				 int min12 =Integer.parseInt(min2);
				 
				 
				if (year1 <1000 ||year1 >10000){
					alert.setTitle("ERROR");
					alert.setHeaderText("Year is not correct" );
					alert.showAndWait();
				}
				
				else if (mon1 <1 ||mon1 > 12){
					alert.setTitle("ERROR");
					alert.setHeaderText("month is not correct" );
					alert.showAndWait();
					
				} else if (day1 <1 ||day1 > 31){
					alert.setTitle("ERROR");
					alert.setHeaderText("day is not correct" );
					alert.showAndWait();
					
					
				}else if (hour1 < 0  || hour1 >24){
					alert.setTitle("ERROR");
					alert.setHeaderText("hour is not correct" );
					alert.showAndWait();
					
				}else if (min1 < 0  || min1 >24){
					alert.setTitle("ERROR");
					alert.setHeaderText("minte is not correct" );
					alert.showAndWait();
				
				}else if  (hour12 < hour1){
							alert.setTitle("ERROR");
							alert.setHeaderText("the duration time is not correct(hour)" );
							alert.showAndWait();
				
				}else if (hour12 == hour1 && min12 <min1){
					alert.setTitle("ERROR");
					alert.setHeaderText("the duration time is not correct (min)" );
					alert.showAndWait();
		
				} else{
				
				a = LocalDateTime.of(year1,mon1,day1,hour1,min1);
			    a2 = LocalDateTime.of(year1,mon1,day1,hour12,min12);
				System.out.println(a +"\t"+a2);}
				
				EventControl getDuration = new  EventControl();
				getDuration.OnAddEvantDuration(titleS,describe, a,a2);
				getDuration.OnAddEvent(titleS,describe, a);
		 });
		 
		
		 HBox Hholder = new HBox();
		 Hholder.setPrefSize(600, 250);
		 Hholder.setPadding(new Insets(10,0,10,10));
		 Hholder.setSpacing(20);
		 
		 
		 HBox Hholder2 =new HBox();
		 
		 
		 VBox vholder = new VBox();		  
			  vholder.setPrefSize(300, 300);
			  vholder.getChildren().addAll(b1,b4);
			  Hholder.getChildren().addAll(this.b5,vholder);
			  
			  
		  Hholder2.getChildren().addAll(b3,b2);
		 //PCholder.getChildren().addAll( btn2);
		 b1.getChildren().addAll( v1,v2, v3);
		 b4.getChildren().addAll(v5,timeT, tF5, timeT2,tF6, timeT3, tF7);
		 b2.getChildren().addAll(v4);
		 b3.getChildren().addAll( title ,btn);
		 b3.setAlignment(Pos.CENTER);
		 holder.getChildren().addAll(Hholder, Hholder2);
		
		 
		 return holder; 
		   
	        	 
		 

	 
	}
	 

	 

	 
public void brows(Stage st, Scene sc){ // not finished 

	Button btn2 = new Button ("Browse");
	btn2.setStyle("-fx-font: 16 arial; -fx-base: #dd85fc;");
	 btn2.setOnAction((ActionEvent e) -> {	 
		 VBox holder2 = new VBox ();
		 holder2.setPrefSize(400, 200);
		 
		 
		 Label path = new Label("Insert pectur Path");
		 TextField wPath = new TextField(" ");
		 wPath.setPrefSize(100, 20);
		 holder2.getChildren().addAll(path,wPath);
		 
		 
		 VBox root = new VBox(); 
		 root.setPrefSize(100, 200);
		 root.setPadding(new Insets(10,10,10,10));
	
		 
		 
		 VBox root2 = new VBox();  //have the ok button 
		 root2.setPrefSize(100, 200);
		 root2.setPadding(new Insets(10,10,10,20));
		 root2.setAlignment(Pos.BOTTOM_RIGHT);
		
		 Button pcB = new Button("ok");
		 pcB.setStyle("-fx-font: 16 arial; -fx-base: #dd85fc;");
		 
		 root2.getChildren().add(pcB);
		 
		 pcB.setOnAction((ActionEvent e1) -> {
			 
			 
			 if (wPath.getText().trim().length() > 0 ){
				 String s  = wPath.getText();
				 File file = new File(s);
				 Image img = new Image(file.toURI().toString(), 250,200,true,true);
				 ImageView iv1 = new ImageView();
				 iv1.setImage(img); 
				 
				 this.b5.getChildren().add(iv1); //get the image
			 
			 
				 st.close();
				 st.setScene(sc); 
				 st.show();
			 }
			 else if (wPath.getText().trim().length() <=0) {
				 st.close();
				 st.setScene(sc); 
			     st.show();
			 
			 }
			
		 
		 
		 
		 
		 
		 });
		 
		 root.getChildren().addAll(holder2 , root2);
		
		 
		    st.setTitle("Add event");
		    Scene scene1= new Scene(root, 400, 200);
		    st.setScene(scene1); 
	        st.show(); 
		 
	
	
	 });
	
	 
	 b3.getChildren().add(btn2);
	
	
}


public void start(Stage primaryStage){
	VBox v= new VBox();
	addEvent add= new addEvent();
	
	primaryStage.setTitle("Random Panel");
	Scene se = new Scene(v,550,450);
	v.getChildren().addAll(add.create());
	add.brows(primaryStage, se);
	primaryStage.setScene(se);
	primaryStage.show();
}

	  // launch application
	 public static void main(String[] args) {
		 
		 launch(args);

		}
}
