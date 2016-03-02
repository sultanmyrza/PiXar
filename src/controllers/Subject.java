package controllers;

/**
 * It make more sense if we call Subject (as Publisher)
 * and Observer ( as Subscriber )
 * 
 *  in our game our Subject is Hero and Observer is View
 */

public interface Subject {
	public void register(Observer newObserver);
	public void unregister(Observer delObserve);
	public void notifyObservers();
}
