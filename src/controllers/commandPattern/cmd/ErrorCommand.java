package controllers.commandPattern.cmd;

import controllers.commandPattern.Command;
import model.Hero;


/**
 * This command send message to Model
 * than model notify its observers
 *
 */

public class ErrorCommand implements Command {

	Hero theHero;
	String errorMsg;
	
	public ErrorCommand(String msg, Hero theHero) {
		this.theHero = theHero;
		errorMsg = msg;
	}
	@Override
	public void execute() {
		/*
		 * when errorCommand called in Hero it notify
		 */
		theHero.errorCommand(errorMsg);
	}

}
