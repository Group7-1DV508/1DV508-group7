package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import functions.ApplicationFunction;
import javafx.application.Application;
import ui.ApplicationView;

public class TestApplicationView {

	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static String[] args;

	@Test
	public void testApplicationView() {
		ApplicationFunction af = new ApplicationFunction();
		LocalDate start = LocalDate.parse("2005-11-12", dtf);
		LocalDate end = LocalDate.parse("2015-11-12", dtf);
		af.addTimeline("Timeline 1", start, end);
		start = LocalDate.parse("2016-11-12", dtf);
		end = LocalDate.parse("2017-11-12", dtf);
		af.addTimeline("Timeline 2", start, end);

		ApplicationView.setApplicationFunction(af);
		Application.launch(ApplicationView.class, args);
	}

}
