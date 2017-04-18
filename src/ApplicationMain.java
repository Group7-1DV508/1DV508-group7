
import controls.ApplicationControl;
import functions.Application;
import io.FileHandler;
import ui.ApplicationView;

public class ApplicationMain {

	public static void main(String[] args) {
		
		ApplicationView appView = new ApplicationView();
		Application app = new Application();
		FileHandler fileHandler = new FileHandler();
		ApplicationControl appControl = new ApplicationControl(appView, app, fileHandler);

	}

}
