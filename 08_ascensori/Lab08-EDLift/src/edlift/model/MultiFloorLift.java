package edlift.model;

import edlift.model.util.Queue;

public class MultiFloorLift extends Lift {
	
	private Queue queue;
		
	public MultiFloorLift(int minFloor, int maxFloor, int initialFloor, double speed) {
		super(minFloor, maxFloor, initialFloor, speed);
		queue = new Queue(4);
	}

	public String getMode() { return "MULTI"; }
	public RequestResult goToFloor(int floor) {
		this.checkArrivalFloor(floor);
		if (this.getCurrentState() != LiftState.REST) {
			if (queue.insert(floor) == false) { return RequestResult.REJECTED; }
			return RequestResult.ACCEPTED;
		} else {
			this.setRequestedFloor(floor);
			RequestResult.ACCEPTED.setFloor(floor);
			return RequestResult.ACCEPTED;
		}
	}
	public boolean hasPendingFloors() { return queue.hasItems(); }
	public int nextPendingFloor(LiftState state) { return queue.extract(); }
}