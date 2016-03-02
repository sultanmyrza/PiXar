package model;

import java.util.ArrayList;

/*
 * Class to store items
 */

public class Store {
	// list where will be stored all available items
	ArrayList<Item> store;

	public Store() {
		store = new ArrayList<Item>();
		/*
		 * add all item to store
		 */ 
		// hair for female
		store.add(new Item("hair", "0", 0, 0, 0, 0)); // to disable hair
		store.add(new Item("hair", "1", 102, 54, 133, 182));
		store.add(new Item("hair", "2", 64, 40, 193, 209));
		store.add(new Item("hair", "3", 91, 66, 155, 169));
		store.add(new Item("hair", "4", 81, 54, 167, 170));
		// hair for male
		store.add(new Item("hair", "5", 96, 28, 167, 170));
		store.add(new Item("hair", "6", 95, 28, 167, 170));
		store.add(new Item("hair", "7", 100, 28, 167, 170));
		store.add(new Item("hair", "8", 96, 25, 167, 170));

		// dress for female
		store.add(new Item("dress", "0", 0, 0, 0, 0)); // to disable dress
		store.add(new Item("dress", "1", 92, 214, 155, 82));
		store.add(new Item("dress", "2", 92, 214, 155, 82));
		store.add(new Item("dress", "3", 92, 214, 155, 82));
		store.add(new Item("dress", "4", 92, 214, 155, 82));
		// dress for male
		store.add(new Item("dress", "5", 92, 214, 155, 82));
		store.add(new Item("dress", "6", 92, 214, 155, 82));
		store.add(new Item("dress", "7", 92, 214, 155, 82));
		store.add(new Item("dress", "8", 91, 209, 155, 82));


		// face for female
		store.add(new Item("face", "0", 0, 0, 0, 0)); // to disable face
		store.add(new Item("face", "1", 120, 117, 120, 122));
		store.add(new Item("face", "2", 120, 117, 120, 122));
		store.add(new Item("face", "3", 120, 117, 120, 122));
		store.add(new Item("face", "4", 120, 117, 120, 122));
		// face for male
		store.add(new Item("face", "5", 123, 113, 102, 129)); 
		store.add(new Item("face", "6", 123, 124, 102, 106));
		store.add(new Item("face", "7", 122, 123, 102, 106));
		store.add(new Item("face", "8", 126, 127, 102, 106));
		
		// item glass
		store.add(new Item("glass", "0", 0, 0, 0, 0)); // to disable glass
		store.add(new Item("glass", "1", 124, 158, 96, 33));
		store.add(new Item("glass", "2", 124, 158, 96, 33));
		store.add(new Item("glass", "3", 117, 160, 96, 33));
		store.add(new Item("glass", "4", 120, 160, 96, 33));
		
		// item others
		store.add(new Item("item", "0", 0, 0, 0, 0)); // to disable item
		store.add(new Item("item", "1", 142, 183, 107, 88));
		store.add(new Item("item", "2", 106, 80, 107, 88));
		store.add(new Item("item", "3", 124, 156, 107, 88));

	}
	
	// return list of stores
	public ArrayList<Item> getStoreItems() {
		return store;
	}

	// return Item object from store
	public Item getStoreItem(String type, String name) {
		for (Item item : store) {
			//System.out.println(item.getType() + " " + item.getName());
			if (item.getName().equals(name) && item.getType().equals(type)) {
				return item;
			}
		}
		// if item do not exist in Store
		return null;
	}
}
