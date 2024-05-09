package control;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import model.*;

public class MyCalendar {
	
	private AppointmentCollection allAppointments;

	public MyCalendar() {
		this.allAppointments = new AppointmentCollection();
	}
	public void add(Appointment app) {
		allAppointments.add(app);
	}
	public boolean remove(Appointment app) {
		for (int i = 0; i < allAppointments.size(); i++) {
			if (allAppointments.get(i).equals(app)) { allAppointments.remove(i); return true; }
		}
		return false;
	}
	public AppointmentCollection getAllAppointments() {
		AppointmentCollection res = new AppointmentCollection(allAppointments.size());
		for (int i = 0; i < allAppointments.size(); i++) {
			res.add(allAppointments.get(i));
		}
		return res;
	}
	public AppointmentCollection getDayAppointments(LocalDate date) {
		LocalDateTime start = LocalDateTime.of(date, LocalTime.of(0, 0, 0));
		LocalDateTime end = start.plusDays(1);
		AppointmentCollection res = new AppointmentCollection();
		for (int i = 0; i < allAppointments.size(); i++) {
			if (isOverlapped(allAppointments.get(i).getFrom(), allAppointments.get(i).getTo(), start, end)) res.add(allAppointments.get(i));
		}
		return res;
	}
	public AppointmentCollection getMonthAppointments(LocalDate date) {
		LocalDateTime start = LocalDateTime.of(LocalDate.of(date.getYear(), date.getMonthValue(), 1), LocalTime.of(0, 0, 0));
		LocalDateTime end = start.plusMonths(1);
		AppointmentCollection res = new AppointmentCollection();
		for (int i = 0; i < allAppointments.size(); i++) {
			if (isOverlapped(allAppointments.get(i).getFrom(), allAppointments.get(i).getTo(), start, end)) res.add(allAppointments.get(i));
		}
		return res;
	}
	public AppointmentCollection getWeekAppointments(LocalDate date) {
		LocalDate inizioSettimana = date.minusDays(date.getDayOfWeek().getValue() - 1);
		LocalDateTime start = LocalDateTime.of(inizioSettimana, LocalTime.of(0, 0, 0));
		LocalDateTime end = start.plusWeeks(1);
		AppointmentCollection res = new AppointmentCollection();
		for (int i = 0; i < allAppointments.size(); i++) {
			if (isOverlapped(allAppointments.get(i).getFrom(), allAppointments.get(i).getTo(), start, end)) res.add(allAppointments.get(i));
		}
		return res;
	}
	private AppointmentCollection getAppointmentsIn(LocalDate start, LocalDate end) {
		LocalDateTime startDateTime = LocalDateTime.of(start, LocalTime.of(0, 0, 0));
		LocalDateTime endDateTime = LocalDateTime.of(end, LocalTime.of(0, 0, 0));
		AppointmentCollection res = new AppointmentCollection();
		for (int i = 0; i < allAppointments.size(); i++) {
			if (isOverlapped(allAppointments.get(i).getFrom(), allAppointments.get(i).getTo(), startDateTime, endDateTime)) res.add(allAppointments.get(i));
		}
		return res;
	}
	private boolean isOverlapped(LocalDateTime start, LocalDateTime end, LocalDateTime refStart, LocalDateTime refEnd) {
		if (end.isBefore(refStart) || start.isAfter(refEnd)) return false;
		return true;
	}
}