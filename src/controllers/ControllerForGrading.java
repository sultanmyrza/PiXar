package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;

import model.Hero;
import model.Item;
import model.Store;
import view.View;

/*
 * This class class 
 * receive actions from GUI theView 
 * get data from model theHero
 * 
 */

public class ControllerForGrading implements Runnable {
	private int totalScore = 0;
	private int round = 0;
	private int currentTime = 0;
	private boolean gameOver = false;

	ArrayList<HashMap<String, Item>> tasks = new ArrayList<HashMap<String, Item>>();
	HashMap<String, Item> taskItems;

	private Hero theHero;
	private HashMap<String, Item> heroItems;

	private View theView;

	private Store store;

	public ControllerForGrading(Hero theHero, View theView, Store theStore) {
		this.theHero = theHero;
		this.theView = theView;
		this.store = theStore;

		/*
		 * Add theHero requirements for current round
		 */
		initTask();

		// add action listener to view
		this.theView.addGradeHeroButton(new GradeButtonListener());
	}

	/*
	 * Auto Timer when time is 9 it automatically call grade() method Which will
	 * compare heroItems and current round requested items
	 */
	@Override
	public void run() {
		while (!gameOver) {
			System.err.println("Thread sleep");
			try {
				Thread.sleep(5_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Thread awake");

			theView.updateTime(currentTime++);
			if (currentTime == 9) {
				grade();
				// theView.showScoreFrame(33);
			}
		}

	}

	/*
	 * Action listener of View to grade work
	 */
	public class GradeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.err.println("Action Event from View ..." + e.getSource().getClass().getTypeName());

			Object src = e.getSource();
			JButton pressedButton = (JButton) src;
			if (pressedButton.getText().toString().equals("START!")) {
				theView.setButtonName("GRADE HERO!");
				theView.setLevel(round);
				/*
				 * Run background music :)
				 */
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File("./audio/1.wav")));
					clip.start();
					/*
					 * while (!clip.isRunning()) Thread.sleep(10); while
					 * (clip.isRunning()) Thread.sleep(10); clip.close();
					 */
				} catch (Exception exc) {
					exc.printStackTrace(System.out);
				}
			} else if (pressedButton.getText().toString().equals("OPEN!")) {
				/*
				 * this if case work when player accomplish 4 tasks
				 */
				// change icon
				theView.openEnvelope(true);
				// generate text for envelope based on player Score!
				String text = generateEnvelopeTextBasedOnScore(totalScore);
				// animation which open envelope with text in it
				theView.showFinalFrame(text);
			} else if (pressedButton.getText().toString().equals("QUIT")) {
				// finish game close JFrame
				gameOver();
			} else if (round <= 3) {
				grade();
			}
		}

		private String generateEnvelopeTextBasedOnScore(int totalScore) {
			int gpa = totalScore / round;
			if (gpa >= 80) {
				return "Great job! You are a awasome designer with amazing skill sets!"
						+ " We will be glad to hire you to our studio." 
						+ "\n\n\n\tbest wishes Pixar inc.";
			} else if (gpa >= 50) {
				return "Good work. You are good designer." 
						+ "We have intership quote for you!"
						+ "\n\n\n\t\tPixar inc.";
			}

			return "Your gpa is too low. Practice make perfect!" 
					+ "Keep training and try again! Good luck!"
					+ "\n\n\n\t\tPixar inc.";
		}

	}

	/**
	 * Method to grade work
	 */
	private void grade() {

		currentTime = 0;
		int similarity = 0;
		// take items into hash map
		heroItems = theHero.getItems();
		taskItems = tasks.get(round);
		// compare heroes
		similarity = (int) matchHeros(heroItems, taskItems);

		// just for ourselves to be sure that code works at this point
		System.err.println("Send message from Controller to View");

		// pop up frame with current round result
		theView.showScoreFrame(similarity, heroItems, round);
		// sum up scores
		totalScore += similarity;
		// next level
		round++;

		if (round <= 3) {
			theView.setLevel(round);
		} else {
			gameOver = true;
			// just change icon and its behavior
			theView.openEnvelope(false);
			// for ourselves to make sure that this part worked
			System.out.println("GAME OVER");
		}
	}

	/**
	 * 
	 * @param heroItems
	 *            current item that theHero have [ adjusted by player ]
	 * @param taskItems
	 *            current round required items
	 * @return double score
	 */
	double matchHeros(HashMap<String, Item> heroItems, HashMap<String, Item> taskItems) {
		int mattchedNumbers = 0;
		int mattchedZeros = 0;
		int totalItems = heroItems.size();

		// loop through item types ["hair", "face", "glass", "item", "dress"]
		for (String key : taskItems.keySet()) {
			// comparing
			if (heroItems.get(key).getName().equals(taskItems.get(key).getName())) {
				if (taskItems.get(key).getName() == "0") {
					mattchedZeros++;
				}
				mattchedNumbers++;
			}
		}
		// for ourselves
		System.out.println("totalItems: " + totalItems);
		System.out.println("matched numbers: " + mattchedNumbers);
		System.out.println("matched zeros: " + mattchedZeros);

		return (mattchedNumbers - mattchedZeros) * 100 / (totalItems - mattchedZeros);
	}

	// terminate game (JFrame)
	private void gameOver() {
		System.exit(0);
	}

	/*
	 * Initialize task items to compare with submitted Hero items
	 */
	void initTask() {
		HashMap<String, Item> task1 = new HashMap<String, Item>();
		HashMap<String, Item> task2 = new HashMap<String, Item>();
		HashMap<String, Item> task3 = new HashMap<String, Item>();
		HashMap<String, Item> task4 = new HashMap<String, Item>();

		/*
		 * ======================================================== initializing
		 * first task (target hero to create)
		 */
		task1.put("hair", store.getStoreItem("hair", "1"));
		task1.put("face", store.getStoreItem("face", "1"));
		// have no glass so set it to 0
		task1.put("glass", store.getStoreItem("glass", "0"));
		task1.put("item", store.getStoreItem("item", "1"));
		task1.put("dress", store.getStoreItem("dress", "1"));

		// adding to task list
		tasks.add(task1);

		/*
		 * ======================================================= initializing
		 * second task (target hero to create)
		 */
		task2.put("hair", store.getStoreItem("hair", "5"));
		task2.put("face", store.getStoreItem("face", "5"));
		// have no glass so set it to 0
		task2.put("glass", store.getStoreItem("glass", "0"));
		// have no item so set it to 0
		task2.put("item", store.getStoreItem("item", "0"));
		task2.put("dress", store.getStoreItem("dress", "5"));

		// adding task to list
		tasks.add(task2);

		/*
		 * ======================================================= initializing
		 * third task (target hero to create)
		 */
		task3.put("hair", store.getStoreItem("hair", "4"));
		task3.put("face", store.getStoreItem("face", "4"));
		// have no glass so set it to 0
		task3.put("glass", store.getStoreItem("glass", "0"));
		task3.put("item", store.getStoreItem("item", "2"));
		task3.put("dress", store.getStoreItem("dress", "4"));
		// adding task to list
		tasks.add(task3);

		/*
		 * ======================================================= initializing
		 * third task (target hero to create)
		 */
		task4.put("hair", store.getStoreItem("hair", "6"));
		task4.put("face", store.getStoreItem("face", "6"));

		task4.put("glass", store.getStoreItem("glass", "3"));
		task4.put("item", store.getStoreItem("item", "3"));
		task4.put("dress", store.getStoreItem("dress", "6"));
		tasks.add(task4);

	}
}
