package model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Appointment {
	private String description;
	private LocalDateTime from, to;

	public Appointment(String description, LocalDateTime from, LocalDateTime to) {
		this.description = description;
		this.from = from;
		this.to = to;
	}
	public Appointment(String description, LocalDateTime from, Duration duration) {
		this.description = description;
		this.from = from;
		this.to = this.from.plus(duration);
	}
	public boolean equals(Appointment app) {
		if (this.description.equals(app.description) && this.from.isEqual(app.from) &&
				this.to.isEqual(app.to)) return true;
		return false;
	}
	public String getDescription() {
		return description;
	}
	public LocalDateTime getFrom() {
		return from;
	}
	public LocalDateTime getTo() {
		return to;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	public Duration getDuration() {
		return Duration.between(from, to);
	}
	public void setDuration(Duration duration) {
		this.to = this.from.plus(duration);
	}
	@Override
	public String toString() {
		DateTimeFormatter formatterShort = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		return "Appuntamento: " + this.description + "\tInizio: " + this.from.format(formatterShort) + "\tFine: " + this.to.format(formatterShort);
	}
}