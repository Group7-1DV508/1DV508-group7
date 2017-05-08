package ui;

import java.util.ArrayList;

import functions.Event;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MonthView extends HBox {

	private ArrayList<DayView> days;
	private ArrayList<Event> events;
	private final int BOX_HEIGHT = 70;
	private final int BOX_LENGTH = 150;
	
	public MonthView() {
		setMinSize(BOX_LENGTH, BOX_HEIGHT);
		setMinSize(BOX_LENGTH, BOX_HEIGHT);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, null, null)));
	}
	public int getLength() {
		return BOX_LENGTH;
	}
	
}
