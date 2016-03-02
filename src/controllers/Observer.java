package controllers;

import model.Hero;

/**
 * Update observer state
 */

public interface Observer {
	/**
	 * send Object theHero 
	 * GUI will update theHero and rebuild GUI depending on hero's params
	 * @param theHero 
	 */
	public void update(Hero theHero);
}
