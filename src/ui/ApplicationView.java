package ui;

import controls.ApplicationListener;
import controls.ChangeListener;
import de.jensd.fx.fontawesome.AwesomeDude;
import de.jensd.fx.fontawesome.AwesomeIcon;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton.ButtonType;

import functions.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.timelineVisuals.ShowEvents;
import ui.timelineVisuals.TimelineInformationBox;
import ui.timelineVisuals.VisualTimeline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class ApplicationView implements ChangeListener {
	private final Tooltip saveTo = new Tooltip();
	private final Tooltip loadTo = new Tooltip();
	private final Tooltip helpTo = new Tooltip();
	
	private Background background = new Background(new BackgroundFill(Color.web("rgb(235,235,235)"), null, null));
	private Background scrollBackground = new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null));
	private CornerRadii radii = new CornerRadii(5);

	private final JFXButton saveButton = new JFXButton("", new Label("",AwesomeDude.createIconLabel(AwesomeIcon.SAVE, "20")) );
	private final JFXButton helpButton = new JFXButton("", new Label("",AwesomeDude.createIconLabel(AwesomeIcon.QUESTION_SIGN, "20")) ); 
	private final JFXButton loadButton = new JFXButton("", new Label("",AwesomeDude.createIconLabel(AwesomeIcon.FOLDER_OPEN, "20")) ); 

	private EventView eventView;
	private TimelineView timelineView;
	private ApplicationListener appListener;
	// contains all parts of the window (Main view)
	private final BorderPane view = new BorderPane();
	// contains ComboBox to choose current timeline, add/delete timeline
	private final HBox timelineButtons = new HBox();
	private final HBox timelineChooserBox = new HBox();
	private final VBox timelineOptionsBox = new VBox();
	boolean filePath;

	// scroll window for timeline
	private final ScrollPane scrollTimeline = new ScrollPane();
	// comboBox to choose timeline
	private final JFXComboBox<Timeline> chooseTimeline = new JFXComboBox<Timeline>();
	// contains all events at the correct position
	private final ShowEvents eventBox = new ShowEvents();
	private final TimelineInformationBox informationBox = new TimelineInformationBox();
	private final VisualTimeline currentTimeline = new VisualTimeline(eventBox);
	private final FlowPane addEventPane = new FlowPane();
	
	private static String FILENAMEINAddEvent;
	private static String FILENAMEINDeleteEvent;
	private static String FILENAMEINEditEvent;

	private static String FILENAMEINCreateTimeline;
	private static String FILENAMEINDeleteTimeline;

	private static String FILENAMEINScrollfunction;


	/**
	 * Constructor, creates and initialize EventView and TimelineView
	 */
	public ApplicationView() {
		eventView = new EventView();
		timelineView = new TimelineView();
	}

	/**
	 * Update the ApplicationListener variable with the ApplicationListener
	 * given as input
	 *
	 * @param appList
	 *            , (ApplicationListener)
	 */
	public void addListener(ApplicationListener appList) {
		appListener = appList;
		eventBox.setListenerAndView(appList, eventView);
		eventBox.setInformationBox(informationBox);
		eventView.setRoot(view);
		timelineView.setRoot(view);
	}

	/**
	 * Returns the EventView currently used
	 *
	 * @return EventView
	 */
	public EventView getEventView() {
		return eventView;
	}

	/**
	 * Returns the TimelineView currently used
	 *
	 * @return TimelineView
	 */
	public TimelineView getTimelineView() {
		return timelineView;
	}

	/**
	 * Returns the root of the Application Window
	 *
	 * @return GridPane
	 */
	public BorderPane getRoot() {
		return root();
	}

	/**
	 * Creates the Root for the Application Window collects the Timeline Buttons
	 * and the Main Timeline Box
	 */
	private BorderPane root() {
		view.setBackground(background);
		view.getChildren().clear();
		addEventPane.getChildren().clear();
		timelineButtonsBox();
		addEventPane.setLayoutX(10);
		addEventPane.setLayoutY(145);
		addEventPane.getChildren().addAll(getAddEventButton(), informationBox);
		view.setTop(timelineOptionsBox);
		view.setCenter(timelineScrollBox());
		view.getChildren().add(addEventPane);
		
		
		return view;
	}

	/**
	 * Return "Add Timeline" Button
	 *
	 * @return Button
	 */
	private JFXButton getAddTimelineButton() {
		return timelineView.getAddTimelineButton();
	}

	/**
	 * Returns the "Delete Timeline" Button
	 *
	 * @return Button
	 */
	private JFXButton getDeleteTimelineButton() {
		return timelineView.getDeleteTimelineButton();
	}

	/**
	 * Returns the "Add Event" Button
	 *
	 * @return Button
	 */
	private JFXButton getAddEventButton() {
		return eventView.getAddEventButton();
	}

	/**
	 * Creates the Help Button
	 */
	private JFXButton createHelpButton() {
		helpTo.setText("Help");
		helpTo.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		helpButton.setTooltip(helpTo);
		helpButton.setRipplerFill(Color.web("rgb(87,56,97)"));
		helpButton.setBackground(scrollBackground);
		helpButton.setMinSize(40, 40);
		helpButton.setMaxSize(40, 40);
		helpButton.setButtonType(ButtonType.FLAT);
		
		




        helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				final File HelpButtonPic = new File("src/HelpButtonPic.png");
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

				VBox v = new VBox();
				v.getChildren().add(label1);

				popup.setResizable(false);

				popup.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
					if (!isNowFocused) {
						popup.hide();
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
						comboBox.setMaxSize(70, 70);
						comboBox.getItems().add("Zoom");
						comboBox.getItems().add("Delete Event");
						comboBox.getItems().add("Edit Event");

						comboBox.setLayoutX(0);
						comboBox.setLayoutY(0);
						Label helpText = new Label("Choose the subject you need help with from \n the "
								+ "drop down menu or press help manual ");
						helpText.setLayoutX(0);
						helpText.setLayoutY(25);
						Label helpText2 = new Label();
						helpText2.setLayoutX(0);
						helpText2.setLayoutY(25);
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
											+ "Apply changes to the information as preferred, and then click the  Finish button when done. ");

								}

							}

						});
						Button button = new Button("Help Manual");
						VBox button2 = new VBox();
						button2.getChildren().add(button);
						button2.setLayoutX(75);
						button2.setLayoutY(0);
						button.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TStage primaryStage = new Stage();
								Stage primaryStage = new Stage();
								primaryStage.setTitle("Help Manual");
								// pic add:
								final File helpAddEventPic = new File("src/helpAddEvent.png");
								FileInputStream helpAddEventPicIS = null;
								try {
									helpAddEventPicIS = new FileInputStream(helpAddEventPic);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								Image imageHelpAddEvent = new Image(helpAddEventPicIS);
								ImageView ivhelpAddEventPic = new ImageView();
								ivhelpAddEventPic.setImage(imageHelpAddEvent);
								ivhelpAddEventPic.setPreserveRatio(true);
								ivhelpAddEventPic.setFitHeight(300);

								final File helpDeleteEventPic = new File("src/helpDeleteEvent.png");
								FileInputStream helpDeleteEventPicIS = null;
								try {
									helpDeleteEventPicIS = new FileInputStream(helpDeleteEventPic);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								Image imageHelpDeleteEvent = new Image(helpDeleteEventPicIS);
								ImageView ivhelpDeleteEventPic = new ImageView();
								ivhelpDeleteEventPic.setImage(imageHelpDeleteEvent);
								ivhelpDeleteEventPic.setPreserveRatio(true);
								ivhelpDeleteEventPic.setFitHeight(400);

								final File helpEditEventPic = new File("src/helpEditEvent.png");
								FileInputStream helpEditEventPicIS = null;
								try {
									helpEditEventPicIS = new FileInputStream(helpEditEventPic);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								Image imageHelpEditEvent = new Image(helpEditEventPicIS);
								ImageView ivhelpEditEventPic = new ImageView();
								ivhelpEditEventPic.setImage(imageHelpEditEvent);
								ivhelpEditEventPic.setPreserveRatio(true);
								ivhelpEditEventPic.setFitHeight(300);

								final File helpEditEventSecPic = new File("src/helpEditEvent2.png");
								FileInputStream helpEditEventSecPicIS = null;
								try {
									helpEditEventSecPicIS = new FileInputStream(helpEditEventSecPic);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								Image imageHelpEditEventSec = new Image(helpEditEventSecPicIS);
								ImageView ivhelpEditEventSecPic = new ImageView();
								ivhelpEditEventSecPic.setImage(imageHelpEditEventSec);
								ivhelpEditEventSecPic.setPreserveRatio(true);
								ivhelpEditEventSecPic.setFitHeight(300);

								final File HelpCreateTimelinePic = new File("src/HelpCreateTimeline.png");
								FileInputStream HelpCreateTimelinePicIS = null;
								try {
									HelpCreateTimelinePicIS = new FileInputStream(HelpCreateTimelinePic);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								Image imageHelpCreateTimelinePic = new Image(HelpCreateTimelinePicIS);
								ImageView ivHelpCreateTimelinePic = new ImageView();
								ivHelpCreateTimelinePic.setImage(imageHelpCreateTimelinePic);
								ivHelpCreateTimelinePic.setPreserveRatio(true);
								ivHelpCreateTimelinePic.setFitHeight(700);
								
								final File helpDeleteTimelinePic = new File("src/helpDeleteTimeline.png");
								FileInputStream helpDeleteTimelinePicIS = null;
								try {
									helpDeleteTimelinePicIS = new FileInputStream(helpDeleteTimelinePic);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								Image imagehelpDeleteTimelinePic = new Image(helpDeleteTimelinePicIS);
								ImageView ivhelpDeleteTimelinePic = new ImageView();
								ivhelpDeleteTimelinePic.setImage(imagehelpDeleteTimelinePic);
								ivhelpDeleteTimelinePic.setPreserveRatio(true);
								ivhelpDeleteTimelinePic.setFitHeight(300);
								
								final File helpScrollfunctionPic = new File("src/HelpScrollfunction1.png");
								FileInputStream helpScrollfunctionPicIS = null;
								try {
									helpScrollfunctionPicIS = new FileInputStream(helpScrollfunctionPic);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								Image imagehelpScrollfunctionPic = new Image(helpScrollfunctionPicIS);
								ImageView ivhelpScrollfunctionPic = new ImageView();
								ivhelpScrollfunctionPic.setImage(imagehelpScrollfunctionPic);
								ivhelpScrollfunctionPic.setPreserveRatio(true);
								ivhelpScrollfunctionPic.setFitHeight(300);
								
								final File helpScrollfunction2Pic = new File("src/HelpScrollfunction2.png");
								FileInputStream helpScrollfunction2PicIS = null;
								try {
									helpScrollfunction2PicIS = new FileInputStream(helpScrollfunction2Pic);
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								Image imagehelpScrollfunction2Pic = new Image(helpScrollfunction2PicIS);
								ImageView ivhelpScrollfunction2Pic = new ImageView();
								ivhelpScrollfunction2Pic.setImage(imagehelpScrollfunction2Pic);
								ivhelpScrollfunction2Pic.setPreserveRatio(true);
								ivhelpScrollfunction2Pic.setFitHeight(300);

								// Label used to show help text:
								FILENAMEINAddEvent = "src/helpAddEvent.txt";
								String contenthelpAdd = null;
								try {
									contenthelpAdd = new String(Files.readAllBytes(Paths.get(FILENAMEINAddEvent)));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								String contenthelpAdd2 = contenthelpAdd;

								FILENAMEINDeleteEvent = "src/helpDeleteEvent.txt";
								String contenthelpDelete = null;
								try {
									contenthelpDelete = new String(Files.readAllBytes(Paths.get(FILENAMEINDeleteEvent)));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								String contenthelpDelete2 = contenthelpDelete;

								FILENAMEINEditEvent = "src/helpEditEvent.txt";
								String contenthelpEdit = null;
								try {
									contenthelpEdit = new String(Files.readAllBytes(Paths.get(FILENAMEINEditEvent)));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								String contenthelpEdit2 = contenthelpEdit;
								final Label description = new Label();
								description.setFont(Font.font("monospace", 12));

								FILENAMEINCreateTimeline = "src/HelpCreateTimeline.txt";
								String contentHelpCreateTimeline = null;
								try {
									contentHelpCreateTimeline = new String(
											Files.readAllBytes(Paths.get(FILENAMEINCreateTimeline)));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								String contentHelpCreateTimeline2 = contentHelpCreateTimeline;

								FILENAMEINDeleteTimeline = "src/HelpDeleteTimeline.txt";
								String contentHelpDeleteTimeline = null;
								try {
									contentHelpDeleteTimeline = new String(
											Files.readAllBytes(Paths.get(FILENAMEINDeleteTimeline)));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								String contentHelpDeleteTimeline2 = contentHelpDeleteTimeline;

								FILENAMEINScrollfunction = "src/HelpScrollfunction.txt";
								String contentHelpScrollfunction = null;
								try {
									contentHelpScrollfunction = new String(
											Files.readAllBytes(Paths.get(FILENAMEINScrollfunction)));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								String contentHelpScrollfunction2 = contentHelpScrollfunction;

								// Main Categories:
								Hyperlink event1 = new Hyperlink("Event"); // event1 since event is used as a variable in: ActionEvent event
								Hyperlink timeline = new Hyperlink("Timeline");
								Hyperlink other = new Hyperlink("Other");

								event1.setFont(Font.font("monospace", 20));
								timeline.setFont(Font.font("monospace", 20));
								other.setFont(Font.font("monospace", 20));

								event1.setLayoutY(0);
								timeline.setLayoutY(20);
								other.setLayoutY(40);

								// Subcategories for Event:
								Hyperlink eventHelpAdd = new Hyperlink("");
								Hyperlink eventHelpDelete = new Hyperlink("");
								Hyperlink eventHelpEdit = new Hyperlink("");

								eventHelpAdd.setFont(Font.font("monospace", 12));
								eventHelpDelete.setFont(Font.font("monospace", 12));
								eventHelpEdit.setFont(Font.font("monospace", 12));

								eventHelpAdd.setDisable(true);
								eventHelpDelete.setDisable(true);
								eventHelpEdit.setDisable(true);

								// Subcategories for Timeline:
								Hyperlink timelineHelpCreate = new Hyperlink("");
								Hyperlink timelineHelpDelete = new Hyperlink("");

								timelineHelpCreate.setFont(Font.font("monospace", 12));
								timelineHelpDelete.setFont(Font.font("monospace", 12));

								timelineHelpCreate.setDisable(true);
								timelineHelpDelete.setDisable(true);

								// Subcategories for Other:
								Hyperlink scrollfunction = new Hyperlink("");

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);
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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);
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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

										}

										else {

											timelineHelpCreate.setText("");
											timelineHelpCreate.setDisable(true);
											timelineHelpDelete.setText("");
											timelineHelpDelete.setDisable(true);

											timeline.setLayoutY(20);
											other.setLayoutY(40);
											
											//so the color of the hypertext doesn't change
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);
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
											scrollfunction.setText("How to scroll");
											scrollfunction.setLayoutY(74);
											scrollfunction.setDisable(false);
											
											//so the color of the hypertext doesn't change
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

										}

										else {

											scrollfunction.setText("");
											scrollfunction.setDisable(true);
											scrollfunction.setLayoutY(0);
											
											//so the color of the hypertext doesn't change
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);
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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);
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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);
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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
											event1.setVisited(false);
											eventHelpAdd.setVisited(false);
											eventHelpDelete.setVisited(false);
											eventHelpEdit.setVisited(false);
											timeline.setVisited(false);
											other.setVisited(false);
											timelineHelpCreate.setVisited(false);
											timelineHelpDelete.setVisited(false);
											scrollfunction.setVisited(false);

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
							}
						});

						Group root = new Group();

						root.getChildren().addAll(button2);
						root.getChildren().addAll(comboBox);
						root.getChildren().addAll(helpText);
						root.getChildren().addAll(helpText2);
						primaryStage.setScene(new Scene(root, 550, 160));
						primaryStage.show();

					}
				});

				VBox v1 = new VBox();
				v.getChildren().add(moreHelp);
				GridPane root = new GridPane();

				root.add(v, 3, 1);
				root.add(v1, 4, 1);
				Scene scene = new Scene(root);
				popup.setScene(scene);
				popup.show();

			}
		});
		return helpButton;
	}
	/* Creates a button which saves a given
	 * timeline to a file path chosen by
	   the user through the fileChooser.*/

	private JFXButton saveTimelineButton() {
		saveTo.setText("Save Timeline");
		saveTo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		saveButton.setTooltip(saveTo);
		saveButton.setRipplerFill(Color.web("rgb(87,56,97)"));
		saveButton.setMinSize(40, 40);
		saveButton.setMaxSize(40, 40);
		saveButton.setBackground(scrollBackground);
		saveButton.setButtonType(ButtonType.FLAT);

		saveButton.setOnAction(ActionEvent  -> {
			view.requestFocus();  
			appListener.onTimelineSaved();
		});
    return saveButton;
	}



	private JFXButton loadTimelineButton() {
		loadTo.setText("Load Timeline");
		loadTo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
	
		loadButton.setTooltip(loadTo);
		loadButton.setRipplerFill(Color.web("rgb(87,56,97)"));
		loadButton.setMinSize(40, 40);
		loadButton.setMaxSize(40, 40);
		loadButton.setBackground(scrollBackground);

		loadButton.setOnAction(ActionEvent -> {
			view.requestFocus();
			appListener.onTimelineLoaded();
		});
		return loadButton;
}

	/**
	 * collects and return all buttons associated with timeline
	 */
	private void timelineButtonsBox() {
		
		timelineButtons.getChildren().clear();
		timelineChooserBox.getChildren().clear();
		timelineOptionsBox.getChildren().clear();
		timelineButtons.setSpacing(18.0);
		timelineButtons.setPadding(new Insets(10, 10, 10, 10));
		timelineButtons.setMinHeight(50);
		timelineButtons.getChildren().addAll(getAddTimelineButton(), saveTimelineButton(), loadTimelineButton(), getDeleteTimelineButton(), createHelpButton());
		timelineButtons.setAlignment(Pos.CENTER);
		timelineButtons.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));
		timelineChooserBox.getChildren().addAll(chooseTimeline);
		timelineChooserBox.setPadding(new Insets(20));
		timelineChooserBox.setAlignment(Pos.CENTER);
		timelineOptionsBox.getChildren().addAll(timelineButtons, timelineChooserBox);
		
	}
	
	

	

	/**
	 * creates a combo box where loaded timelines can be chosen from also calls
	 * method to create the current timeline and add events to it
	 *
	 * @param timelines
	 *            , the timelines available
	 * @param current
	 *            , the currently open timeline
	 */
	private void chooseTimeline(ArrayList<Timeline> timelines, Timeline current) {

		chooseTimeline.getItems().clear();
		chooseTimeline.setFocusColor(Color.web("rgb(87,56,97)"));
		chooseTimeline.setUnFocusColor(Color.web("rgb(87,56,97)"));
		chooseTimeline.setStyle("-fx-font-size: 20px; -fx-font-family: monospace;");
		chooseTimeline.setBackground(background);

		for (Timeline t : timelines) {
			chooseTimeline.getItems().add(t);
		}
		chooseTimeline.setValue(current);
		chooseTimeline.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (chooseTimeline.getItems().size() > 0) {
					appListener.onTimelineSelected(chooseTimeline.getValue());
				}
			}

		});
	}

	/**
	 * Creates a ScrollPane for the timeline and adds the current timeline and
	 * the events pane to it
	 */
	private ScrollPane timelineScrollBox() {
		VBox content = new VBox();
		content.setBackground(scrollBackground);
		content.setPadding(new Insets(90, 5, 20, 5));
		content.getChildren().addAll(currentTimeline, eventBox);
		scrollTimeline.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollTimeline.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollTimeline.setBackground(scrollBackground);
		scrollTimeline.setStyle("-fx-background: rgb(223,223,223);");
		scrollTimeline.setContent(content);
		
		scrollTimeline.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, radii, BorderStroke.THIN)));
		
		return scrollTimeline;
	}

	private void clearTimelineBox() {
		currentTimeline.getChildren().clear();
		eventBox.getChildren().clear();
		informationBox.getChildren().clear();
		chooseTimeline.getItems().clear();
		Text noTimelines = new Text("There are no new timelines currently selected");
		noTimelines.setFont(new Font("Times new Roman", 20));
		noTimelines.setFill(Color.BLACK);
		//currentTimeline.add(noTimelines, 0, 1);
	}

	@Override
	public void onChangedTimeline(ArrayList<Timeline> timelines, Timeline current) {
		if (!(current == null)) {
			chooseTimeline(timelines, current);
			//showYear(current);
			currentTimeline.createVisualTimeline(current);
			eventView.setTimelineStartEnd(current.getStart().toLocalDate(), current.getEnd().toLocalDate());

			getDeleteTimelineButton().setDisable(false);
			eventView.setDisable(false);
			saveButton.setDisable(false);
			addEventPane.setVisible(true);
		}
		else {
			clearTimelineBox();
			getDeleteTimelineButton().setDisable(true);
			eventView.setDisable(true);
			saveButton.setDisable(true);
			addEventPane.setVisible(false);
		}

	}

	@Override
	public void onNewTimelineSelected(Timeline current) {
		currentTimeline.createVisualTimeline(current);
		eventView.setTimelineStartEnd(current.getStart().toLocalDate(), current.getEnd().toLocalDate());
		onTimelineSaved(current);
	}

	@Override
	public void onEditTimeline(Timeline current) {
		currentTimeline.updateVisualTimeline();
	}

	@Override
	public void onEditEvent(Timeline current) {
		currentTimeline.updateVisualTimeline();
	}

	@Override
	public void onTimelineSaved(Timeline current) {
		if (current.getFile() != null) {
			if (current.getFile().isFile()) {
				timelineView.setTimelineSaved(true);
			}
		}
		else {
			timelineView.setTimelineSaved(false);
		}
	}
}