package view;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import model.Item;

import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.util.HashMap;
import javax.swing.JSeparator;
import java.awt.Color;

public class ScoreFrame extends JDialog {
	// suggested by Eclipse
	private static final long serialVersionUID = 2L;

	JProgressBar progressBar;
	JLabel progressLabel;
	private JPanel avatarPanel;
	private JLabel itemLabel;
	private JLabel hairLabel;
	private JLabel glassLabel;
	private JLabel faceLabel;
	private JLabel dressLabel;
	private JLabel bodyLabel;
	private JPanel targetPanel;
	private JLabel labelTarget;
	private JButton levelButton;
	private JLabel autoCloseButton;


	public void animateScore(int similarity, HashMap<String, Item> heroItems, int level) {

		for (Item item : heroItems.values()) {
			this.setItem(item);
		}
		setLevel(level);

		Thread thread = new Thread() {
			public void run() {

				for (int i = 0; i <= similarity; i++) {
					progressLabel.setText(i + " %.");
					progressBar.setValue(i);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				// for automatically close pop up window after 2 seconds
				for (int i = 2; i >= 1; i--) {
					autoCloseButton.setText("Will automaticaly close after: " + i);
					try {
						Thread.sleep(1_000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				dispose();
			}
		};
		thread.start();
	}
	
	/**
	 * Create the dialog.
	 */
	public ScoreFrame() {
		setBounds(100, 100, 682, 553);
		getContentPane().setLayout(null);

		JPanel progressPanel = new JPanel();
		progressPanel.setBounds(246, 372, 216, 89);
		getContentPane().add(progressPanel);
		progressPanel.setLayout(null);

		progressLabel = new JLabel("New label");
		progressLabel.setBounds(0, 0, 205, 37);
		progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		progressPanel.add(progressLabel);

		progressBar = new JProgressBar();
		progressBar.setBounds(0, 37, 205, 37);

		progressBar.setMinimum(0);
		progressBar.setMaximum(100);

		progressPanel.add(progressBar);

		avatarPanel = new JPanel();
		avatarPanel.setLayout(null);
		avatarPanel.setBounds(0, 0, 295, 346);
		getContentPane().add(avatarPanel);

		itemLabel = new JLabel("");
		itemLabel.setBounds(106, 82, 107, 88);
		avatarPanel.add(itemLabel);

		hairLabel = new JLabel("");
		hairLabel.setBounds(96, 25, 167, 170);
		avatarPanel.add(hairLabel);

		glassLabel = new JLabel("");
		glassLabel.setBounds(120, 160, 96, 33);
		avatarPanel.add(glassLabel);

		faceLabel = new JLabel("");
		faceLabel.setBounds(120, 117, 120, 122);
		avatarPanel.add(faceLabel);

		dressLabel = new JLabel("");
		dressLabel.setBounds(91, 209, 155, 82);
		avatarPanel.add(dressLabel);

		bodyLabel = new JLabel("");
		bodyLabel.setIcon(new ImageIcon("./images/avatar/body/bodyFemaleWhite.png"));
		bodyLabel.setBounds(94, 77, 159, 209);
		avatarPanel.add(bodyLabel);
		
		JButton btnYourWork = new JButton("your work!");
		btnYourWork.setBounds(81, 290, 135, 25);
		avatarPanel.add(btnYourWork);

		targetPanel = new JPanel();
		targetPanel.setLayout(null);
		targetPanel.setBounds(391, 57, 254, 289);
		getContentPane().add(targetPanel);

		labelTarget = new JLabel("");
		labelTarget.setBounds(83, 0, 163, 229);
		targetPanel.add(labelTarget);

		levelButton = new JButton("LeVeL");
		levelButton.setBounds(94, 230, 135, 25);
		targetPanel.add(levelButton);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(342, 22, 16, 324);
		getContentPane().add(separator);
		
		autoCloseButton = new JLabel("Will automaticaly close after ");
		autoCloseButton.setBounds(222, 484, 240, 15);
		getContentPane().add(autoCloseButton);


		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		// to pop up at center of screen
		setLocationRelativeTo(null);
	}

	/*
	 * Setter Getter for pop up frame
	 */
	public void setItem(Item item) {
		/*
		 * if item null do not set it
		 */
		if (item == null) {
			return;
		}
		/*
		 * else set it to its label
		 */
		int x = item.getX();
		int y = item.getY();
		int width = item.getWidth();
		int height = item.getHeight();

		if (item.getType().equals("hair")) {

			hairLabel.setBounds(x, y, width, height);
			hairLabel.setIcon(new ImageIcon(item.getSrc()));

		} else if (item.getType().equals("dress")) {

			dressLabel.setBounds(x, y, width, height);
			dressLabel.setIcon(new ImageIcon(item.getSrc()));

		} else if (item.getType().equals("face")) {

			faceLabel.setBounds(x, y, width, height);
			faceLabel.setIcon(new ImageIcon(item.getSrc()));

		} else if (item.getType().equals("glass")) {

			glassLabel.setBounds(x, y, width, height);
			glassLabel.setIcon(new ImageIcon(item.getSrc()));

		} else if (item.getType().equals("item")) {

			itemLabel.setBounds(x, y, width, height);
			itemLabel.setIcon(new ImageIcon(item.getSrc()));
		}
	}

	/*
	 * Set Level
	 */
	public void setLevel(int level) {

		// labelTarget.setBounds(84, 12, 153, 217);

		labelTarget.setIcon(new ImageIcon("./images/avatar/target/level" + (level + 1) + ".png"));

		levelButton.setText("LeVeL " + (level + 1));
		if (level == 0) {
			levelButton.setBounds(94, 241, 117, 25);
		} else if (level == 1) {
			levelButton.setBounds(94, 241, 117, 25);
		} else if (level == 2) {
			levelButton.setBounds(94, 241, 117, 25);
		}

	}
}
