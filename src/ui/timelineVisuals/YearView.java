package ui.timelineVisuals;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class YearView extends HBox {

	
	private final int BOX_HEIGHT = 70;
	private final int BOX_LENGTH = 200;
	
	public YearView() {
		setMinSize(BOX_LENGTH, BOX_HEIGHT);
		setMinSize(BOX_LENGTH, BOX_HEIGHT);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, null, null)));
	}
	
	public int getLength() {
		return BOX_LENGTH;
	}
}
