package controllers.commandPattern.cmd;

import controllers.commandPattern.Command;
import model.Hero;
import model.Item;

public class UseSelectedItem implements Command {

	Hero theHero;
	Item theItem;
	public UseSelectedItem(Item theItem, Hero theHero) {
		this.theHero = theHero;
		this.theItem = theItem;
	}
	
	@Override
	public void execute() {
		theHero.useSelectedItemCommand(this.theItem);
	}

}
