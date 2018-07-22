package org.koushik.javabrains.messenger.service;

import java.awt.List;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessage();

	// potrebno je napraviti konstruktor koji ce postaviti neke vrednosti u
	// HashMap-u

	public MessageService() {
		messages.put(1L, new Message(1, "Hello World!", "Koushiku"));
		messages.put(2L, new Message(2, "Hello Jeresy!", "Koushiku"));
	}

	// metoda koja vraca listu objekata klase Message i u nju ubacuje vrednosti
	// koje su dodate u hashmapu gore navedenim
	public ArrayList<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public ArrayList<Message> getAllMessagesForYear(int year) {
		ArrayList<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;

	}

	public ArrayList<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if (start + size > list.size())
			return new ArrayList<Message>();
		return (ArrayList<Message>) list.subList(start, start + size);

	}

	// ovde je napravljena metoda koja iz hashmape izvlaci id a taj id je atribut
	// kalse Message
	public Message getMessage(long id) {
		return messages.get(id);
	}

	// ovde takodje pravi metodu klase Message I kao ulazne parameter postavlja
	// //objekat klase Message a to je message
	// nakon toga nad objektom message(klase Message)vrsi metodu setId odnosno
	// //trazi da se postavi odgovarajuci id, onda taj id postavlja na mesto
	// HashMap-//e +1
	// nakon toga uzima HashMap-u I kaze put I kaze uzmi ovaj id koji si gore
	// //postavio I daj mi ga odnosno postavi ga u mapu
	// I na kraju vraca objekat message

	public Message addMesage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	// ovde prvo kaze daj mi id I proveri da li je on manji ili jednak nuli ako
	// //jeste vrati mi null, a ako nije uzmi I postavi ga u hashmapu I vrati
	// objekat kalse Message

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;

	}

	public Message removeMessage(long id) {
		return messages.remove(id);

	}
}
