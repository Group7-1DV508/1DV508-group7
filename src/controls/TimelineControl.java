package controls;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ui.TimelineView;


public class TimelineControl implements TimelineListener {

	public static void addTimeline() {
		TimelineView tv = new TimelineView();

		int counter = 1;

		for (int i = 0; i < 3000; i++) {
			tv.yearcmb.getItems().addAll(counter);
			counter++;
		}
		counter = 3000;

		for (int i = 0; i < 3000; i++) {

			tv.yearcmb2.getItems().addAll(counter);
			counter--;
		}
		tv.secStart();
	}

	public static void finish() {
		TimelineView tv = new TimelineView();

		tv.eStartYear = tv.yearcmb.getSelectionModel().isEmpty();            // Right now there are 2 problems, the first one is the combobox.getSelectionModel().isEmpty();
		tv.eEndYear = tv.yearcmb2.getSelectionModel().isEmpty();			 // for some reason it never recognizes that something is selected in the combobox.

		System.out.println(tv.eStartYear);
		System.out.println(tv.eEndYear);

		if (tv.eStartYear == true) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Error message");
			alert.setContentText("Please select a start year!");
			alert.showAndWait();
		} else if (tv.eEndYear == true) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Error message");
			alert.setContentText("Please select an end year!");
			alert.showAndWait();
		} else if (tv.startYear > tv.endYear) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Error message");
			alert.setContentText("Start year can't be smaller than End year. Try again!");
			alert.showAndWait();
		} else if (tv.tf.getText().equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText("Error message");
			alert.setContentText("Please choose a timeline name!");
			alert.showAndWait();
		} else {
			tv.startYear = tv.yearcmb.getSelectionModel().getSelectedItem();
			tv.endYear = tv.yearcmb2.getSelectionModel().getSelectedItem();
			tv.sYear = Integer.toString(tv.startYear);
			tv.eYear = Integer.toString(tv.endYear);
			tv.sYear = tv.sYear + "-01-01";
			tv.eYear = tv.eYear + "-01-01";
			String text = tv.tf.getText();

			DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate startDate = LocalDate.parse(tv.sYear, f);
			LocalDate endDate = LocalDate.parse(tv.eYear, f);

			//OnAddTimeline(text, startDate, endDate);											// And the second one is cannot make a static reference to a non static method
			System.exit(0);
		}

	}


	@Override
	public void onAddTimeline(String name, LocalDate start, LocalDate end) {
		System.out.println(name + " " + start.getYear() + " " + end.getYear());

	}

}
