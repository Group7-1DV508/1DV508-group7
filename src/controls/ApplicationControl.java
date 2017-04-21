package controls;

import java.util.ArrayList;

import functions.App;
import functions.Timeline;
import io.FileHandler;
import javafx.scene.text.Text;
import ui.ApplicationView;

public class ApplicationControl implements ApplicationListener {
	
	private ApplicationView appView;
	private App app;
	private FileHandler fileHandler;
	private TimelineControl timelineControl;
	private EventControl eventControl;
	
	/**
	 * Constructor, Creates an ApplicationControl and sets variables for 
	 * ApplicationView, App, FileHandler. Also creates a new TimelineControl and 
	 * an EventControl. 
	 * @param av , ApplicationView
	 * @param app , App
	 * @param fh , FileHandler
	 */
	public ApplicationControl(ApplicationView av, App app, FileHandler fh) {
		appView = av;
		this.app = app;
		fileHandler = fh;
		timelineControl = new TimelineControl();
		eventControl = new EventControl();
		eventControl.setApp(app);
		timelineControl.setApp(app);
	}
	
	/**
	 * Connects the View and Control through the Listener
	 * Also connects ApplicationView to App through the Listener 
	 */
	public void setUpListeners() {
		appView.getTimelineView().addListener(timelineControl);
		appView.getEventView().addListener(eventControl);
		appView.addListener(this);
		app.addListener(appView);
	}
	

	@Override
	public void onTimelineSelected(Timeline t) {
		app.setCurrentTimeline(t);
		
	}
	

}
