package controls;

import functions.App;
import io.FileHandler;
import ui.ApplicationView;

public class ApplicationControl implements ApplicationListener {
	
	private ApplicationView appView;
	private App app;
	private FileHandler fileHandler;
	private TimelineControl timelineControl;
	private EventControl eventControl;
	
	public ApplicationControl(ApplicationView av, App app, FileHandler fh) {
		appView = av;
		this.app = app;
		fileHandler = fh;
		timelineControl = new TimelineControl();
		eventControl = new EventControl();
		eventControl.setApp(app);
		timelineControl.setApp(app);
	}
	
	public void setUpListeners() {
		appView.getTimelineView().addListener(timelineControl);
		appView.getEventView().addListener(eventControl);
		appView.addListener(this);
	}

}
