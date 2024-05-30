package oroscopo.ui;

import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import oroscopo.controller.AbstractController;
import oroscopo.model.SegnoZodiacale;

public class MainPane extends BorderPane {

	private AbstractController controller = null;
	private int fortunaMin;
	private DateTimeFormatter monthFormatter;
	private TextArea output;
	private ComboBox<SegnoZodiacale> segniZodiacali;
	
	private void initPane() {
		
	}
	
	private void myHandle(ActionEvent e) {
		
	}
	
	public MainPane(AbstractController controller, int fortunaMin) {
		this.fortunaMin = fortunaMin;
		this.controller = controller;
	}
}
