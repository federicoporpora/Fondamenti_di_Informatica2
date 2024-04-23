package edlift.controller;

import java.io.PrintWriter;
import java.util.List;

import edlift.model.Lift;
import edlift.model.LiftState;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import edlift.model.Installation;

public class Controller {

	private TextArea view;
	private Lift lift;
	private List<Installation> installations;
	private Installation currentInstallation;
	private Timeline timer;
	private int ticks;
	private PrintWriter pw;
	
	public Controller(List<Installation> installations) {
		if (installations.isEmpty()) throw new IllegalArgumentException("installation list is empty");
		this.installations = installations;
		this.currentInstallation = installations.get(0);
		this.lift = currentInstallation.getLift();
		ticks = 0;
		//
		timer = new Timeline(
				new KeyFrame(javafx.util.Duration.seconds(1), 
				e -> tick()));
        timer.setCycleCount(Timeline.INDEFINITE);
	}

	
	public List<Installation> getInstallations() {
		return installations;
	}
		
	
	public void setLift(Lift lift) {
		this.lift = lift;
	}
	
	
	public Lift getLift(){
		return lift;
	}

	
	public void setLogger(TextArea view, PrintWriter pw) {
		this.view = view; 
		this.pw=pw;
	}
	
	public void log(String msg){
		view.appendText(msg + "\n");
		pw.println("Controller log: " + msg);
		//System.out.print("Controller log: " + msg);
	}

	public void startSimulation() {
        ticks = 0;
		timer.play();
		log("simulation started");
	}

	public void stopSimulation() {
		timer.stop();
		log("simulation stopped");
	}
	
	public void goToFloor(int piano) {
		log("Lift call from floor " + piano + " " +
				getLift().goToFloor(piano).getMsg());
	}
	

	/** Il metodo tick() incorpora la logica di avanzamento della simulazione:
	 *  � invocato automaticamente dalla GUI a intervalli regolari.
	 *  Non toccare!
	 */
	public void tick() {
		ticks++;
		//log("\tsimulation clock: " + ticks);
		log("Clock: " + ticks);
		
		switch (lift.getCurrentState()) {
			case REST:
				log("lift at rest at floor " + lift.getCurrentFloor());
				//log("calling floor " + lift.getCallingFloor() + "\n");
				if(lift.hasPendingFloors()) {
					int nextFloor = lift.nextPendingFloor(LiftState.REST);
					lift.setRequestedFloor(nextFloor);
				}
				if (lift.getCurrentFloor() < lift.getRequestedFloor()) {
					lift.setCurrentState(LiftState.UP); 
					log("lift going up towards " + lift.getRequestedFloor());
				}
				else if (lift.getCurrentFloor() > lift.getRequestedFloor()) {
					lift.setCurrentState(LiftState.DOWN); 
					log("lift going down towards " + lift.getRequestedFloor());
				}
				break;
			case UP:
				//log("lift going up\n");
				if (lift.getCurrentFloor() < lift.getRequestedFloor()) {
					lift.setCurrentFloor(lift.getCurrentFloor() + 1);
					if (lift.getCurrentFloor() < lift.getRequestedFloor())
						log("lift up passing floor " + lift.getCurrentFloor());
				}
				else {
					lift.setCurrentState(LiftState.REST);
					log("lift up arrived at floor " + lift.getCurrentFloor());
					/*
					if(lift.hasPendingFloors()) {
						int nextFloor = lift.nextPendingFloor(LiftState.UP);
						if (lift.getCurrentFloor() != nextFloor) {
							log("will proceed to floor " + nextFloor);
							lift.setRequestedFloor(nextFloor);
						}
					}*/
				}
				break;
			case DOWN: 
				//log("lift going down\n");
				if (lift.getCurrentFloor() > lift.getRequestedFloor()) {
					lift.setCurrentFloor(lift.getCurrentFloor() - 1);
					if (lift.getCurrentFloor() > lift.getRequestedFloor())
						log("lift down passing floor " + lift.getCurrentFloor());
				}
				else {
					lift.setCurrentState(LiftState.REST);
					log("lift down arrived at floor " + lift.getCurrentFloor());
					/*
					if(lift.hasPendingFloors()) {
						int nextFloor = lift.nextPendingFloor(LiftState.DOWN);
						if (lift.getCurrentFloor() != nextFloor) {
							log("will proceed to floor " + nextFloor);
							lift.setRequestedFloor(nextFloor);
						}
					}
					*/
				}
				break;
		}

	}

	//----------------------------------
	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

	public static void info(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
}
