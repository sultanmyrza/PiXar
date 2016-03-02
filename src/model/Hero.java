package model;

import java.util.ArrayList;
import java.util.HashMap;

import controllers.Observer;
import controllers.Subject;

public class Hero implements Subject {
	// hero items 
	private HashMap<String, Item> items;
	private HashMap<String, Item> emptyItems;
	// used in case if command is correct but not valid
	private String error = null;

	// list of observer to notify
	ArrayList<Observer> observers;

	public Hero() {
		observers = new ArrayList<Observer>();
		items = new HashMap<String, Item>();
		emptyItems = new HashMap<String, Item>();
		/*
		 * creating empty Items to make reference to them later on
		 * when we will need to delete item
		 * if we set to null it causes null pointer exception error
		 * so have to trick by creating items that reference to non-existing icon 
		 */
		emptyItems.put("hair", new Item("hair", "0", 0, 0, 0, 0));
		emptyItems.put("face", new Item("face", "0", 0, 0, 0, 0));
		emptyItems.put("glass", new Item("glass", "0", 0, 0, 0, 0));
		emptyItems.put("item", new Item("item", "0", 0, 0, 0, 0));
		emptyItems.put("dress", new Item("dress", "0", 0, 0, 0, 0));
		
		/*
		 * Hero have no hair, face, glass | item and dress
		 * thats why we should set all Hero items to non-existing items
		 */
		items.put("hair", emptyItems.get("hair") );
		items.put("face", emptyItems.get("face") );
		items.put("glass", emptyItems.get("glass") );
		items.put("item", emptyItems.get("item")  );
		items.put("dress", emptyItems.get("dress") );
	}

	// return hero items
	public HashMap<String, Item> getItems() {
		return items;
	}

	// set Hero's item to selected
	public void useSelectedItemCommand(Item newItem) {
		// rewrite item
		items.put(newItem.getType(), newItem);
		System.err.println("Notify observers..");
		notifyObservers();
	}

	// remove Hero's item
	public void removeItemCommand(String type) {
		// set to empty item 
		items.put(type, emptyItems.get(type) );
		notifyObservers();
	}
	// kind reset Hero's item (in fact we just set[refer] to empty Items)
	public void resetCommand() {
		for (String key : items.keySet()) {
			// again set all items to non-existing item (not to null)
			// for ourselves
			System.out.println("Hero Items are: " + key);
			items.put(key, emptyItems.get(key));
		}
		notifyObservers();
	}

	public void errorCommand(String msg) {
		this.error = msg;
		// notify View
		notifyObservers();
		this.error = null;
	}

	public String getError() {
		return this.error;
	}

	@Override
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	@Override
	public void unregister(Observer delObserve) {
		int delIndex = observers.indexOf(delObserve);
		observers.remove(delIndex);
	}

	@Override
	public void notifyObservers() {
		System.err.println("Notify sended Hero(Subject) -> View(Object)");
		for (Observer observer : observers) {
			observer.update(this);
		}
	}
	
}