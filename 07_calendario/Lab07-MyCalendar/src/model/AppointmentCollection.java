package model;

public class AppointmentCollection {
	private final int DEFAULT_GROWTH_FACTOR = 2, DEFAULT_PHYSICAL_SIZE = 10;
	private Appointment[] innerContainer;
	private int size;
		
		public AppointmentCollection() {
			innerContainer = new Appointment[DEFAULT_PHYSICAL_SIZE];
			size = 0;
		}
		public AppointmentCollection(int physicalSize) {
			innerContainer = new Appointment[physicalSize];
			size = 0;
		}
		public Appointment get(int index) { return (innerContainer[index]); }
		public int size() { return size; }
		public void add(Appointment appointment) {
			if (size < innerContainer.length) {
				innerContainer[size] = appointment;
				size++;
			}
			else {
				AppointmentCollection res = new AppointmentCollection(this.innerContainer.length * DEFAULT_GROWTH_FACTOR);
				for (int i = 0; i < size; i++) {
					if (this.innerContainer[i] != null) {
						res.innerContainer[i] = this.innerContainer[i];
					}
				}
				res.innerContainer[size] = appointment;
				size++;
				innerContainer = res.innerContainer;
			}
		}
		public void remove(int index) {
			if (index == size - 1) { innerContainer[index] = null; size--; }
			else {
				for (int i = size - 1; i > index; i--) {
					innerContainer[i - 1] = innerContainer[i];
				}
				innerContainer[size - 1] = null;
				size--;
			}
		}
		public int indexOf(Appointment app) {
			for (int i = 0; i < this.size(); i++) {
				if (this.get(i).equals(app)) return i;
			}
			return -1;
		}
}
