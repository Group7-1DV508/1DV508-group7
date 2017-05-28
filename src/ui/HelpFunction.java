package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import de.jensd.fx.fontawesome.AwesomeDude;
import de.jensd.fx.fontawesome.AwesomeIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelpFunction {
	
	private final Tooltip helpTo = new Tooltip();
	private final JFXButton helpButton = new JFXButton("", new Label("",AwesomeDude.createIconLabel(AwesomeIcon.QUESTION_SIGN, "20")) ); 
	private Hyperlink event1 = new Hyperlink("Event"); 
	private Hyperlink timeline = new Hyperlink("Timeline");
	private Hyperlink other = new Hyperlink("Other");
	private Hyperlink eventHelpAdd = new Hyperlink("");
	private Hyperlink eventHelpDelete = new Hyperlink("");
	private Hyperlink eventHelpEdit = new Hyperlink("");
	private Hyperlink timelineHelpCreate = new Hyperlink("");
	private Hyperlink timelineHelpDelete = new Hyperlink("");
	private Hyperlink scrollfunction = new Hyperlink("");
	
	public JFXButton createHelpButton() {
		helpTo.setText("Help");
		helpTo.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		helpButton.setTooltip(helpTo);
		helpButton.setRipplerFill(Color.web("rgb(87,56,97)"));
		helpButton.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));
		helpButton.setMinSize(40, 40);
		helpButton.setMaxSize(40, 40);
		helpButton.setButtonType(ButtonType.FLAT);

		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				final File HelpButtonPic = new File("resources/HelpButtonPic.png");
				FileInputStream HelpButtonPicIS = null;
				try {
					HelpButtonPicIS = new FileInputStream(HelpButtonPic);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Image imageHelpHelpButtonPic = new Image(HelpButtonPicIS);
				ImageView ivHelpButtonPic = new ImageView();
				ivHelpButtonPic.setImage(imageHelpHelpButtonPic);

				Label label1 = new Label("");
				label1.setGraphic(new ImageView(imageHelpHelpButtonPic));
				Stage popup = new Stage();

				VBox firstHelpWindow = new VBox();
				firstHelpWindow.getChildren().add(label1);

				popup.setResizable(false);

				popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
					if (!isNowFocused) {
						popup.close();
					}

				});
				Button moreHelp = new Button("More Help");
				moreHelp.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Stage primaryStage = new Stage();

						primaryStage.setTitle("Help Window");
						// VBox for pics:
						VBox v1 = new VBox();
						VBox v2 = new VBox();
						VBox v3 = new VBox();
						VBox v4 = new VBox();
						VBox v5 = new VBox();
				//		VBox v6 = new VBox(); never used missed
						VBox v7 = new VBox();
						VBox v8 = new VBox();
						VBox v9 = new VBox();

						final ComboBox<String> comboBox = new ComboBox<>();
						comboBox.getItems().add("Zoom");
						comboBox.getItems().add("Delete Event");
						comboBox.getItems().add("Edit Event");

						Label helpText = new Label("Choose the subject you need help with from \n the "
								+ "drop down menu or press help manual ");
						
						Label helpText2 = new Label();
						
						comboBox.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {

								String helpZoom = comboBox.getSelectionModel().getSelectedItem();
								if (helpZoom == "Zoom") {
									helpText.setText("To zoom in right-click with the computer mouse.\n"
											+ "When zoomed in, the month in that year will appear.\n"
											+ " Zooming in one more time will give the user a day by day view with the respective event circles.\n"
											+ "To zoom out left-click with the computer mouse.");

								}
								String helpDelete = comboBox.getSelectionModel().getSelectedItem();
								if (helpDelete == "Delete Event") {
									helpText.setText("Click on the Event circle, which should be deleted.\n"
											+ "Then the event information box will appear.\n"
											+ "Click on the Delete Event button in the bottom right corner");

								}

								String helpEdit = comboBox.getSelectionModel().getSelectedItem();
								if (helpEdit == "Edit Event") {
									helpText.setText("Click the Event circle, which should be edited.\n"
											+ " The Event windows textfields are disabled so no accidental changes can be made.\n"
											+ "To start editing press the Edit information button.\n"
											+ "After clicking the edit Event information the name, description and dates will become editable.\n"
											+ "Apply changes to the information as preferred, and then click the Finish button when done. ");

								}

							}

						});
						Button button = new Button("Help Manual");
						
						button.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TStage primaryStage = new Stage();
								Stage primaryStage = new Stage();
								primaryStage.setTitle("Help Manual");
								// pic add
								ImageView ivhelpAddEventPic = createImageView("resources/helpAddEvent.png");
								
								ImageView ivhelpDeleteEventPic = createImageView("resources/helpDeleteEvent.png");
							
								ImageView ivhelpEditEventPic = createImageView("resources/helpEditEvent.png");
								
								ImageView ivhelpEditEventSecPic = createImageView("resources/helpEditEvent2.png");
								
								ImageView ivHelpCreateTimelinePic = createImageView("resources/HelpCreateTimeline.png");
								
								ImageView ivhelpDeleteTimelinePic = createImageView("resources/helpDeleteTimeline.png");
							
								ImageView ivhelpScrollfunctionPic = createImageView("resources/HelpScrollfunction1.png");
								
								ImageView ivhelpScrollfunction2Pic = createImageView("resources/HelpScrollfunction2.png");
								

								// Label used to show help text:
								
								String contenthelpAdd2 = createHelpText("resources/helpAddEvent.txt");

								String contenthelpDelete2 = createHelpText("resources/helpDeleteEvent.txt");

								String contenthelpEdit2 = createHelpText("resources/helpEditEvent.txt");
								final Label description = new Label();
								description.setFont(Font.font("monospace", 12));

								String contentHelpCreateTimeline2 = createHelpText("resources/HelpCreateTimeline.txt");

								String contentHelpDeleteTimeline2 = createHelpText("resources/HelpDeleteTimeline.txt");

								String contentHelpScrollfunction2 = createHelpText("resources/HelpScrollfunction.txt");

								// Main Categories:
								

								event1.setFont(Font.font("monospace", 20));
								timeline.setFont(Font.font("monospace", 20));
								other.setFont(Font.font("monospace", 20));

								event1.setLayoutY(0);
								timeline.setLayoutY(20);
								other.setLayoutY(40);


								eventHelpAdd.setFont(Font.font("monospace", 12));
								eventHelpDelete.setFont(Font.font("monospace", 12));
								eventHelpEdit.setFont(Font.font("monospace", 12));

								eventHelpAdd.setDisable(true);
								eventHelpDelete.setDisable(true);
								eventHelpEdit.setDisable(true);

								timelineHelpCreate.setFont(Font.font("monospace", 12));
								timelineHelpDelete.setFont(Font.font("monospace", 12));

								timelineHelpCreate.setDisable(true);
								timelineHelpDelete.setDisable(true);

								scrollfunction.setFont(Font.font("monospace", 12));

								scrollfunction.setDisable(true);

								event1.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										if (eventHelpAdd.getText() == "") {
											eventHelpAdd.setLayoutY(31);
											eventHelpAdd.setText("How to add event");
											eventHelpAdd.setDisable(false);
											eventHelpDelete.setLayoutY(43);
											eventHelpDelete.setText("How to delete event");
											eventHelpDelete.setDisable(false);
											eventHelpEdit.setLayoutY(55);
											eventHelpEdit.setText("How to edit an event");
											eventHelpEdit.setDisable(false);

											timeline.setLayoutY(70);
											other.setLayoutY(90);

											timelineHelpCreate.setText("");
											timelineHelpCreate.setDisable(true);
											timelineHelpDelete.setText("");
											timelineHelpDelete.setDisable(true);

											scrollfunction.setText("");
											scrollfunction.setDisable(true);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);
										}

										else {
											eventHelpAdd.setText("");
											eventHelpAdd.setDisable(true);
											eventHelpDelete.setText("");
											eventHelpDelete.setDisable(true);
											eventHelpEdit.setText("");
											eventHelpEdit.setDisable(true);

											timeline.setLayoutY(20);
											other.setLayoutY(40);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);
										}
									}
								});

								timeline.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										if (timelineHelpCreate.getText() == "") {
											// "closing" the event/other help if
											// "open"
											eventHelpAdd.setText("");
											eventHelpAdd.setDisable(true);
											eventHelpDelete.setText("");
											eventHelpDelete.setDisable(true);
											eventHelpEdit.setText("");
											eventHelpEdit.setDisable(true);
											scrollfunction.setText("");
											scrollfunction.setDisable(true);

											timeline.setLayoutY(20);

											timelineHelpCreate.setLayoutY(55);
											timelineHelpCreate.setText("How to create a  timeline");
											timelineHelpCreate.setDisable(false);

											timelineHelpDelete.setLayoutY(67);
											timelineHelpDelete.setText("How to delete timeline");
											timelineHelpDelete.setDisable(false);

											other.setLayoutY(84);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										}

										else {

											timelineHelpCreate.setText("");
											timelineHelpCreate.setDisable(true);
											timelineHelpDelete.setText("");
											timelineHelpDelete.setDisable(true);

											timeline.setLayoutY(20);
											other.setLayoutY(40);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);
										}
									}
								});

								other.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										if (scrollfunction.getText() == "") {
											// "closing" the event/timeline help
											// if "open"
											eventHelpAdd.setText("");
											eventHelpAdd.setDisable(true);
											eventHelpDelete.setText("");
											eventHelpDelete.setDisable(true);
											eventHelpEdit.setText("");
											eventHelpEdit.setDisable(true);
											timelineHelpCreate.setText("");
											timelineHelpCreate.setDisable(true);
											timelineHelpDelete.setText("");
											timelineHelpDelete.setDisable(true);
											timeline.setLayoutY(20);

											other.setLayoutY(40);
											scrollfunction.setText("How to zoom");
											scrollfunction.setLayoutY(74);
											scrollfunction.setDisable(false);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);
											
										}

										else {

											scrollfunction.setText("");
											scrollfunction.setDisable(true);
											scrollfunction.setLayoutY(0);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										}
									}
								});

								// events for subcategories
								eventHelpAdd.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										String evenHelpAddString = contenthelpAdd2;
										if (description.getText() == evenHelpAddString) {
											description.setText("");

											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);
										}
										else {
											description.setLayoutX(190);
											description.setLayoutY(10);
											description.setText(evenHelpAddString);
											v1.getChildren().add(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											v1.setLayoutX(300);
											v1.setLayoutY(370);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										}
									}
								});
								eventHelpDelete.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										String evenHelpDeleteString = contenthelpDelete2;
										if (description.getText() == evenHelpDeleteString) {
											description.setText("");

											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											description.setText("");
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										} 
										else {
											description.setLayoutX(190);
											description.setLayoutY(10);
											description.setText(evenHelpDeleteString);
											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().add(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											v2.setLayoutX(200);
											v2.setLayoutY(300);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										}
									}
								});

								eventHelpEdit.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										String eventHelpEditString = contenthelpEdit2;
										if (description.getText() == eventHelpEditString) {
											description.setText("");
											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);
										} 
										else {
											description.setLayoutX(190);
											description.setLayoutY(10);
											description.setText(eventHelpEditString);
											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().add(ivhelpEditEventPic);
											v3.setLayoutX(200);
											v3.setLayoutY(300);
											v4.getChildren().add(ivhelpEditEventSecPic);
											v4.setLayoutX(700);
											v4.setLayoutY(300);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
										
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);
										}
									}
								});
								// timeline subcategories:
								timelineHelpCreate.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										String contentHelpCreateTimelineString = contentHelpCreateTimeline2;
										if (description.getText() == contentHelpCreateTimelineString) {
											description.setText("");
											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);
										}
										else {
											description.setLayoutX(190);
											description.setLayoutY(10);
											description.setText(contentHelpCreateTimeline2);
											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().add(ivHelpCreateTimelinePic);
											v5.setLayoutX(200);
											v5.setLayoutY(200);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										}
									}
								});

								timelineHelpDelete.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										String contentHelpDeleteTimelineString = contentHelpDeleteTimeline2;
										if (description.getText() == contentHelpDeleteTimelineString) {
											description.setText("");
											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										}
										else {
											description.setLayoutX(190);
											description.setLayoutY(10);
											description.setText(contentHelpDeleteTimeline2);
											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().add(ivhelpDeleteTimelinePic);
											v7.setLayoutX(200);
											v7.setLayoutY(300);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										}
									}
								});
								// other subcategories:
								scrollfunction.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										String contentHelpScrollfunctionString = contentHelpScrollfunction2;
										if (description.getText() == contentHelpScrollfunctionString) {
											description.setText("");
											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().remove(ivhelpScrollfunctionPic);
											v9.getChildren().remove(ivhelpScrollfunction2Pic);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										} 
										else {
											description.setLayoutX(190);
											description.setLayoutY(10);
											description.setText(contentHelpScrollfunction2);
											v1.getChildren().remove(ivhelpAddEventPic);
											v2.getChildren().remove(ivhelpDeleteEventPic);
											v3.getChildren().remove(ivhelpEditEventPic);
											v4.getChildren().remove(ivhelpEditEventSecPic);
											v5.getChildren().remove(ivHelpCreateTimelinePic);
											v7.getChildren().remove(ivhelpDeleteTimelinePic);
											v8.getChildren().add(ivhelpScrollfunctionPic);
											v9.getChildren().add(ivhelpScrollfunction2Pic);
											v8.setLayoutX(200);
											v8.setLayoutY(300);
											v9.setLayoutX(730);
											v9.setLayoutY(300);
											
											//so the color of the hypertext doesn't change
											setHyperLinkVisited(false);

										}
									}
								});

								Group root = new Group();
								root.getChildren().addAll(v1, v2, v3, v4, v5, v7, v8, v9);
								root.getChildren().addAll(event1, timeline, other);
								root.getChildren().addAll(eventHelpAdd, eventHelpDelete, eventHelpEdit);
								root.getChildren().addAll(timelineHelpCreate, timelineHelpDelete);
								root.getChildren().addAll(scrollfunction);
								root.getChildren().addAll(description);
								primaryStage.setScene(new Scene(root, 1267, 700));
								primaryStage.show();
								
								primaryStage.setOnCloseRequest(ActionEvent -> {
									v1.getChildren().remove(ivhelpAddEventPic);
									v2.getChildren().remove(ivhelpDeleteEventPic);
									v3.getChildren().remove(ivhelpEditEventPic);
									v4.getChildren().remove(ivhelpEditEventSecPic);
									v5.getChildren().remove(ivHelpCreateTimelinePic);
									v7.getChildren().remove(ivhelpDeleteTimelinePic);
									v8.getChildren().remove(ivhelpScrollfunctionPic);
									v9.getChildren().remove(ivhelpScrollfunction2Pic);
								});
							}
						});

						VBox root = new VBox();
						HBox comboBoxButton = new HBox();
						comboBoxButton.getChildren().addAll(comboBox, button);
						VBox content = new VBox();
						content.getChildren().addAll(helpText, helpText2);
						ScrollPane scroll = new ScrollPane();
						scroll.setContent(content);
						scroll.setPrefSize(550, 200);
						root.getChildren().addAll(comboBoxButton, scroll);
						primaryStage.setScene(new Scene(root, 550, 200));
						primaryStage.show();

					}
				});

				VBox v1 = new VBox();
				firstHelpWindow.getChildren().add(moreHelp);
				GridPane root = new GridPane();

				root.add(firstHelpWindow, 3, 1);
				root.add(v1, 4, 1);
				Scene scene = new Scene(root);
				popup.setScene(scene);
				popup.show();

			}
		});
		return helpButton;
	}
	
	private ImageView createImageView(String str) {
		final File file = new File(str);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Image image = new Image(inputStream);
		ImageView imageView = new ImageView(image);
		imageView.setImage(image);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(300);
		return imageView;
	}
	
	private String createHelpText(String str) {
		String string = null;
		try {
			string = new String(Files.readAllBytes(Paths.get(str)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return string;
	}
	private void setHyperLinkVisited(boolean b) {
		event1.setVisited(b);
		eventHelpAdd.setVisited(b);
		eventHelpDelete.setVisited(b);
		eventHelpEdit.setVisited(b);
		timeline.setVisited(b);
		other.setVisited(b);
		timelineHelpCreate.setVisited(b);
		timelineHelpDelete.setVisited(b);
		scrollfunction.setVisited(b);
	}
}
