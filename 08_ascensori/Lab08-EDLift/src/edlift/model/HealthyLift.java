package edlift.model;

public class HealthyLift extends Lift {
	public HealthyLift(int minFloor, int maxFloor, int initialFloor, double speed) {
		super(minFloor, maxFloor, initialFloor, speed);
	}
	public String getMode() { return "HEALTHY"; }
	public RequestResult goToFloor(int floor) {
		this.checkArrivalFloor(floor);
		if (this.getCurrentState() != LiftState.REST) { return RequestResult.REJECTED; }
		if (java.lang.Math.abs(this.getCurrentFloor() - floor) <= 1) { return RequestResult.REJECTED; }
		if (this.getCurrentFloor() < floor) { this.setRequestedFloor(floor - 1); RequestResult.MODIFIED.setFloor(floor - 1); }
		if (this.getCurrentFloor() > floor) { this.setRequestedFloor(floor + 1); RequestResult.MODIFIED.setFloor(floor + 1);}
		
		return RequestResult.MODIFIED;
	}
}