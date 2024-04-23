package edlift.model.util;

import java.util.Arrays;

public class Queue {

	private int booked, maxBookings;
	private int[] bookings;

	public Queue(int maxBookings) {
		this.booked = 0;
		this.maxBookings = maxBookings;
		this.bookings = new int[maxBookings];
		for (int i = 0; i < maxBookings; i++) bookings[i] = Integer.MIN_VALUE;
	}
	
	public boolean insert(int n) {
		if (booked < maxBookings) {
			bookings[booked] = n;
			booked++;
			return true;
		}
		return false;
	}
	public int extract() {
		if (booked == 0) { return Integer.MIN_VALUE; }
		int res = bookings[0];
		booked--;
		for (int i = 0; i < booked; i++) {
			bookings[i] = bookings[i + 1];
		}
		bookings[booked] = Integer.MIN_VALUE;
		return res;
	}
	public int peek() { return bookings[0]; }
	public boolean hasItems() {return (booked != 0) ? true : false;}
	public int size() { return booked; }
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Queue [booked=");
		builder.append(booked);
		builder.append(", maxBookings=");
		builder.append(maxBookings);
		builder.append(", bookings=");
		builder.append(Arrays.toString(bookings));
		builder.append("]");
		return builder.toString();
	}
}