

import controllers.ControllerForGrading;
import controllers.ControllerForSubmitButton;
import model.Hero;
import model.Store;
import view.View;

public class GameEngine {
	public static void main(String[] args) {

		// creating models and view
		Store theStore = new Store();
		Hero theHero = new Hero();
		View theView = new View();
		
		/*
		 * Creating new controller 
		 * connect model(theHero) also Subject[Publisher]
		 * to		view(theView) is Observer[Subscriber] of theHero
		 */
		new ControllerForSubmitButton(theHero, theView);
		
		/*
		 * Creating another controller 
		 * theHero - GradinController - theView
		 */
		ControllerForGrading controllerForGrading = new ControllerForGrading(theHero, theView, theStore);
		// run thread
		controllerForGrading.run(); 
	}
	
	
}
