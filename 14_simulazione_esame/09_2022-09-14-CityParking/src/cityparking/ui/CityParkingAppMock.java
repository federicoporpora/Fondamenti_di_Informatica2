package cityparking.ui;

import java.time.Duration;

import cityparking.controller.Controller;
import cityparking.controller.MyController;
import cityparking.model.Tariffa;

public class CityParkingAppMock extends CityParkingApp {

	@Override
	protected Controller createController() {
		var t = new Tariffa(Duration.ofMinutes(15), 2.00, 0.80, 35.00, 16.00);
		return new MyController(t);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
