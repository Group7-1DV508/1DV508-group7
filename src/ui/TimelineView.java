package ui;

	import controls.TimelineControl;
    import javafx.application.Application;
	import javafx.stage.Stage;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.ComboBox;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.VBox;
	import javafx.scene.text.Font;

	public class TimelineView extends Application {

		public int startYear = 0;
		public int endYear = 0;
		public boolean eStartYear = true;
		public boolean eEndYear = true;
		public String sYear;
		public String eYear;
		public TextField tf;
		Button addT;
		Button finish;
		public ComboBox<Integer> yearcmb = new ComboBox<Integer>();
		public ComboBox<Integer> yearcmb2 = new ComboBox<Integer>();
		Label startT;
		Label endT;
		Label name;
		Stage st ;
		Stage ntStage ;
		TimelineControl tc = new TimelineControl();

		@Override
		public void start(Stage primaryStage) {
			try {
				st = new Stage();
				st.setTitle("Timeline Manager");
				addT = new Button("Add Timeline");

				VBox vb = new VBox(15);
				vb.getChildren().add(addT);
				st.setScene(new Scene(vb, 200, 300));
				st.setResizable(false);
				st.show();
				addT.setOnAction(e -> TimelineControl.addTimeline());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void secStart(){
			try {
			ntStage = new Stage();

			ntStage.setTitle("Add Timeline Window");
			finish = new Button("Finish");

			startT = new Label("Start Year");
			endT = new Label("End Year");
			name = new Label("Name:");
			tf = new TextField();

			startT.setTranslateX(147);
			startT.setTranslateY(40);
			startT.setFont(new Font("Times new Roman", 20));

			endT.setTranslateX(175);
			endT.setTranslateY(40);
			endT.setFont(new Font("Times new Roman", 20));

			name.setTranslateY(2);
			finish.setTranslateX(190);
			finish.setTranslateY(120);
			finish.setMinHeight(30);
			finish.setMinWidth(100);

			yearcmb.setEditable(false);
			yearcmb.setMinSize(50, 30);

			yearcmb2.setEditable(false);
			yearcmb2.setMinSize(50, 30);

			HBox hb = new HBox();
			HBox hb1 = new HBox();
			HBox hb2 = new HBox();
			VBox vb2 = new VBox();

			yearcmb.setTranslateX(150);
			yearcmb.setTranslateY(50);
			yearcmb2.setTranslateX(180);
			yearcmb2.setTranslateY(50);

			hb2.getChildren().addAll(name, tf);
			hb2.setTranslateY(-45);
			hb2.setTranslateX(10);
			hb1.getChildren().addAll(yearcmb, yearcmb2);
			hb1.setTranslateY(20);
			hb.getChildren().addAll(startT, endT);
			hb.setTranslateY(20);
			vb2.getChildren().addAll(hb, hb1, hb2, finish);
			ntStage.setScene(new Scene(vb2, 500, 300));
			ntStage.setResizable(false);
			ntStage.show();

			finish.setOnAction(e -> TimelineControl.finish());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
