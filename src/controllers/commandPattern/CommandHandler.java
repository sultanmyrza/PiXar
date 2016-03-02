package controllers.commandPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import controllers.commandPattern.cmd.ErrorCommand;
import controllers.commandPattern.cmd.RemoveItem;
import controllers.commandPattern.cmd.ResetCommand;
import controllers.commandPattern.cmd.UseSelectedItem;
import model.Hero;
import model.Item;
import model.Store;

/**
 * Receive current command, references to Hero and store objects  
 * handle command and return Command class that match with if statements
 */

public class CommandHandler {
	
	public Command getCommand(String cmd, Hero theHero, Store store) {
		
		/*
		 * If entered command return Error command
		 * no need to work with empty
		 */
		if (cmd.length() == 0) {
			return new ErrorCommand("Do not enter empty line!", theHero);
		}
		
		HashMap<String, Item> heroItems = theHero.getItems();
		
		// list of words
		ArrayList<String> words = new ArrayList<String>();
		
		/*
		 * parsing cmd [entered line from view] 
		 * and storing it to Arraylis words
		 */
		StringTokenizer tokenizer = new StringTokenizer(cmd);
		while (tokenizer.hasMoreElements()) {
			String word = (String) tokenizer.nextElement();
			words.add(word);
		}
		
		/*
		 * Checking for reset command
		 * because it is easiest
		 */
		if (words.size() == 1 && words.get(0).equals("reset")) {
			return new ResetCommand(theHero);
		}
		
		/*
		 * Checking for remove "item" command
		 */
		if (words.size() == 2 && words.get(0).equals("remove")) {
			/*
			 * if user try to delete non - existing item
			 */
			String item = words.get(1);
			if(heroItems.containsKey(item) == false){
				return new ErrorCommand("Can not delete non existing item \""+item+"\"", 
										theHero);
			}else{
				/*
				 * here means that deleting item exist
				 * now we just have to check if it is not already deleted
				 */
				Item deletingItem = heroItems.get(item);
				if (deletingItem.getName().equals("0")) {
					/*
					 * if this works 
					 * that mean that item already deleted
					 * have to send error message like(its already delete)
					 */
					return new ErrorCommand(item + " is already deleted!", theHero);
				}else{
					/*
					 * if this works
					 * that means that we have this item and its not deleted yet
					 */
					System.out.println(cmd);
					return new RemoveItem(item, theHero);
				}
			}
		}
		
		/*
		 * use select item Command
		 */
		
		if (words.size() == 3 && words.get(0).equals("use")) {
			String type = words.get(1);
			String name = words.get(2);
			
			/*
			 * if type exist
			 */
			if (heroItems.containsKey(type)) {
				if (store.getStoreItem(type, name) != null) {
					// means that item exist in store
					return new UseSelectedItem(store.getStoreItem(type, name), theHero);
				}else{
					// item does not exits
					return new ErrorCommand(type + " \"" + name + "\" does not exist!", theHero);
				}
			}else{
				return new ErrorCommand("type: \"" + type + "\" does not exist!", theHero);
			}
			
		}
		
		/*
		 * Check for other commands
		 */
		
		return new ErrorCommand(cmd +" is invalid command", theHero);
	}
	
}
