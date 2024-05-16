package agenda.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Agenda {
	private SortedSet<Contact> contactSet;

	public Agenda() {
		contactSet = new TreeSet<Contact>();
	}
	
	public Agenda(Collection<Contact> contacts) {
		contactSet = new TreeSet<Contact>(contacts);
		
	}

	public void addContact(Contact c) {
		contactSet.add(c);
		
	}
	
	public void removeContact(Contact c)
	{
		contactSet.remove(c);
		
	}
	
	
	public Set<Contact> getContacts() {
		Set<Contact> result = new TreeSet<Contact>();
		result= Set.copyOf (contactSet);
		return result;
	}

	public Optional<Contact> getContact(String firstName, String secondName) {
		for (Contact contact : contactSet) {
			if (contact.getName().equals(firstName)
					&& contact.getSurname().equals(secondName))
				return Optional.of(contact);
		}
		return Optional.empty();
	}

	public Optional<Contact> getContact(int index) {
		List<Contact> list = new ArrayList<Contact> (contactSet);
		if (index < list.size()&& index>=0)
			return Optional.of(list.get(index));
		/* SOLUZIONE ALTERNATIVA
		int = 0;
		for (Contact contact : contactSet) {
			if (i == index)
				return contact;
			i++;
		}*/
		return Optional.empty();
	}

	public SortedSet<Contact> searchContacts(String secondName) {
		TreeSet<Contact> contacts = new TreeSet<Contact>();
		for (Contact c : contactSet) {
			if (c.getSurname()
					.toLowerCase()
					.contains(secondName.toLowerCase()))
				contacts.add(c);
		}
		return contacts;
	}
}
