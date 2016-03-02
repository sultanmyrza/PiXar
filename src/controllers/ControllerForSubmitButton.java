package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import controllers.commandPattern.Command;
import controllers.commandPattern.CommandHandler;
import model.Hero;
import model.Store;
import view.View;

/*
 * This controller connect View and Model
 * Observer patter used
 * This class register theView(subscriber|observer) to theBoy(publisher|subject)
 */

public class ControllerForSubmitButton {

	private Hero theModel;
	private View theView;
	private Store store;
	/*
	 * Create command classes and send them theBoy object to execute on it
	 */

	public ControllerForSubmitButton(Hero newModel, View newView) {
		theModel = newModel;
		theView = newView;
		store = new Store();
		// subscribe theView(view) to theHero(model) to receive updates
		theModel.register(theView);
		// add action listener to View for this Controller
		theView.addEnterSubmitButtonListener(new SubmitButtonListener());
	}

	// subclass listener for View
	public class SubmitButtonListener implements ActionListener, KeyListener {

		/*
		 * Action listener and key listeners for view
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			workWithEnteredCommand();
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// just check if pressed key is Enter
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				workWithEnteredCommand();
			}

		}

		/*
		 * kind a rubbish :)
		 */
		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		/*
		 * workWithEnteredCommand handle entered text in text field since we add
		 * Action and Key listener for the same text field it will be convenient
		 * to make it a method to reuse in implemented methods We love DRY (DONT
		 * REPEAT YOURSELF) principle :)
		 */
		void workWithEnteredCommand() {
			/*
			 * since we have only one submit Button I guess there is no need to
			 * check Event source and if event source instance of JButton or not
			 */

			// get entered command from View and convert it to string
			String currentCmd = theView.getCurrentCmd();
			// to erase entered command from GUI :)
			theView.setCurrentCmd("");
			// append command to cmdLog if it was not empty
			if (currentCmd.length() > 0) {
				theView.setCmdLog(theView.getCmdLog() + "\n" + currentCmd);
			}
			System.out.println(currentCmd);

			// converting entered line to Command
			Command command = new CommandHandler().getCommand(currentCmd, theModel, store);
			// Execute command
			command.execute();
		}

	}

}
