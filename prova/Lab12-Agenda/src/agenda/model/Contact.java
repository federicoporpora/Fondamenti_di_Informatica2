package agenda.model;

import java.util.ArrayList;
import java.util.List;


public class Contact implements Comparable<Contact> {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((detailList == null) ? 0 : detailList.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (detailList == null) {
			if (other.detailList != null)
				return false;
		} else if (!detailList.equals(other.detailList))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	private String name;
	private String surname;

	private List<Detail> detailList;
	
	public Contact(String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.detailList = new ArrayList<Detail>();
	}
	
	public Contact(String name, String surname, List<Detail> details) {
		this.name = name;
		this.surname = surname;
		this.detailList = details;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}

	public String getName() {
		return name;
	}

	public void setSurname(String secondName) {
		this.surname = secondName;
	}

	public String getSurname() {
		return surname;
	}

	public List<Detail> getDetailList() {
		return detailList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(" ");
		sb.append(getSurname());
		sb.append("\n");

		for (Detail d : getDetailList()) {
			sb.append(d.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	@Override
	public int compareTo(Contact o) {
		int result = getSurname()
				.trim()
				.compareToIgnoreCase(o.getSurname().trim());
		return result != 0 
				? result 
				: getName()
					.trim()
					.compareToIgnoreCase(o.getName().trim());
	}
}
