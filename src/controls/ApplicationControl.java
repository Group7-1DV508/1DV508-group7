package controls;

import functions.Application;
import io.FileHandler;
import ui.ApplicationView;

public class ApplicationControl implements ApplicationListener {
	
	private ApplicationView appView;
	private Application app;
	private FileHandler fileHandler;
	private TimelineControl timelineControl;
	private EventControl eventControl;
	
	public ApplicationControl(ApplicationView av, Application app, FileHandler fh) {
		appView = av;
		this.app = app;
		fileHandler = fh;
		timelineControl = new TimelineControl();
		eventControl = new EventControl();
	}
	
	public void setUpListeners() {
		appView.getTimelineView().addListener(timelineControl);
		appView.getEventView().addListener(eventControl);
		appView.addListener(this);
	}

}
