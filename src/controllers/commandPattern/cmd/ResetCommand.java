package controllers.commandPattern.cmd;

import controllers.commandPattern.Command;
import model.Hero;

public class ResetCommand implements Command {
	
	Hero theHero;
	
	public ResetCommand(Hero theHero) {
		// TODO Auto-generated constructor stub
		this.theHero = theHero;
	}
	
	@Override
	public void execute() {
		theHero.resetCommand();
	}

}
