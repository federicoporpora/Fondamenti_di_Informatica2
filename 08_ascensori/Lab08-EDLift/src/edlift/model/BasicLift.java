package edlift.model;

public class BasicLift extends Lift {
	public BasicLift(int minFloor, int maxFloor, int initialFloor, double speed) {
		super(minFloor, maxFloor, initialFloor, speed);
	}
	public String getMode() { return "BASIC"; }
	public RequestResult goToFloor(int floor) {
		this.checkArrivalFloor(floor);
		if (this.getCurrentState() != LiftState.REST) { return RequestResult.REJECTED; }
		this.setRequestedFloor(floor);
		RequestResult.ACCEPTED.setFloor(floor);
		return RequestResult.ACCEPTED;
	}
}