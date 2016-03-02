package controllers.commandPattern.cmd;

import controllers.commandPattern.Command;
import model.Hero;

/*
 * don wanna write comments here 
 * Because most of them the will be the same as in ErrorCommand
 */

public class RemoveItem implements Command {

	Hero theHero;
	String theItem;
	
	public RemoveItem(String theItem, Hero theHero) {
		this.theItem = theItem;
		this.theHero = theHero; 	
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		theHero.removeItemCommand(theItem);

	}

}
