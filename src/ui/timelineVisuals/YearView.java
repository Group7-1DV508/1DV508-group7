package ui.timelineVisuals;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class YearView extends HBox {

	
	private final int BOX_HEIGHT = 70;
	private final int BOX_LENGTH = 200;
	private final Text text;
	
	public YearView() {
		setMinSize(BOX_LENGTH, BOX_HEIGHT);
		setMinSize(BOX_LENGTH, BOX_HEIGHT);
		setAlignment(Pos.CENTER);
		setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, null, null)));
		text = new Text();
		getChildren().add(text);
	}
	
	public int getLength() {
		return BOX_LENGTH;
	}
	
	public void setText(String str) {
		text.setFill(Color.WHITE);
		text.setFont(Font.font ("Times New Roman", 25));
		text.setText(str);
	}
}