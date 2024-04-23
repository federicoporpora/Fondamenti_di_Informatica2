package edlift.model;

public enum RequestResult {
	ACCEPTED("accepted"),
	REJECTED("rejected"),
	MODIFIED("modified");
	
	private RequestResult(String msg) {
		this.msg=msg;
		this.floor = Integer.MIN_VALUE;
	}
	private String msg;
	private int floor;
	
	public String getMsg() { return msg; }
	public void setMsg(String msg) { this.msg = msg; }
	
	public int getFloor() { return floor; }
	public void setFloor(int f) { floor = f; }
	
}
