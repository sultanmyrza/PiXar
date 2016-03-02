package view;

import controllers.ControllerForSubmitButton;
import controllers.Observer;
import controllers.ControllerForGrading.GradeButtonListener;
import model.Hero;
import model.Item;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.util.HashMap;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class View implements Observer {

	private JFrame frame;
	private JTextField currentCmd;
	private JButton submitButton;
	private JTextArea cmdLog;

	private JLabel clockLabel;
	private JLabel envelopeLabel;
	/*
	 * Item for Hero model
	 */
	private JLabel hairLabel;
	private JLabel glassLabel;
	private JLabel faceLabel;
	private JLabel dressLabel;
	private JLabel bodyLabel;
	JLabel itemLabel;
	/*
	 * For Target
	 */
	JLabel labelTarget;
	JButton levelButton;
	
	
	private String welcomeText = "Hello! Welcome to our new KGFI brand new Game \"TeenAger\" \n"
			+ "================================\n" 
			+ "available commands are:\n"
			+ "use \"type\" \"name\" ex: use hair 1 \n"
			+ "remove \"type\" ex: remove hair \n"
			+ "reset (will reset hero items";
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;

	// to submit hero
	JButton doneButton;

	public View() {
		initialize();

		/*
		 * I know Hard Code but we have only 3 days to finish game no time for
		 * perfect coding :)
		 */
		cmdLog.setText(welcomeText);

	}

	/*
	 * View methods
	 */

	public String getCurrentCmd() {
		return this.currentCmd.getText().toString();
	}

	public void setCurrentCmd(String cmd) {
		this.currentCmd.setText(cmd);
	}

	public String getCmdLog() {
		return this.cmdLog.getText().toString();
	}

	public void setCmdLog(String cmdLog) {
		this.cmdLog.setText(cmdLog);
	}

	public void showMessage(String msg) {
		JOptionPane.showConfirmDialog(frame, msg, "Message", JOptionPane.PLAIN_MESSAGE);
	}

	/*
	 * methods for setting up Hero
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
	 * Set current level's target
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

	/*
	 * For Timer class
	 */
	public void updateTime(int time) {
		clockLabel.setIcon(new ImageIcon("./images/timer/" + time + ".png"));
	}

	/*
	 * Connect Controller and View with action listener this case action
	 * listener is invoker for Controller this method should be public since it
	 * is in other package than controller
	 * 
	 * this methods add Enter key listener and submitButton JButton action
	 * Listener thats why it is called add Enter SubmitButton listener Long but
	 * descriptive name I guess :)
	 */

	public void addEnterSubmitButtonListener(ControllerForSubmitButton.SubmitButtonListener listener) {
		// action listener for submit button
		this.submitButton.addActionListener(listener);
		// key listener for Text field (currentCmd)
		this.currentCmd.addActionListener(listener);
	}

	public void addGradeHeroButton(GradeButtonListener listener) {
		this.doneButton.addActionListener(listener);
		this.levelButton.addActionListener(listener);
	}

	/*
	 * Observer implemented method 
	 * @see controllers.Observer#update(model.Hero)
	 */
	@Override
	public void update(Hero theHero) {
		// loop through HashMap <type,item>
		System.err.println("View (Observer) notified!");
		for (Item item : theHero.getItems().values()) {
			this.setItem(item);
		}

		if (theHero.getError() != null) {
			JOptionPane.showConfirmDialog(frame, theHero.getError(), "Error!", JOptionPane.PLAIN_MESSAGE);
		}

	}

	/**
	 * =========================================================================
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		// frame.setBounds(100, 100, 1286, 687);
		frame.setBounds(100, 100, 1282, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/*
		 * ====================================================================
		 * avatar panel
		 */
		JPanel avatarPanel = new JPanel();
		avatarPanel.setBounds(12, 12, 307, 364);
		frame.getContentPane().add(avatarPanel);
		avatarPanel.setLayout(null);

		doneButton = new JButton("START!");
		doneButton.setFont(new Font("Courier New", Font.BOLD, 20));
		doneButton.setBounds(53, 303, 210, 30);
		avatarPanel.add(doneButton);

		itemLabel = new JLabel("");
		// item icon must be empty in start
		// itemLabel.setIcon(new ImageIcon("./images/avatar/item/6.png"));
		itemLabel.setBounds(106, 82, 107, 88);
		avatarPanel.add(itemLabel);

		hairLabel = new JLabel("");
		// this icon also should be empty
		// hairLabel.setIcon(new ImageIcon("./images/avatar/hair/8.png"));
		hairLabel.setBounds(96, 25, 167, 170);
		avatarPanel.add(hairLabel);

		glassLabel = new JLabel("");
		glassLabel.setBounds(120, 160, 96, 33);
		avatarPanel.add(glassLabel);
		// glass icon also empty
		// glassLabel.setIcon(new ImageIcon("./images/avatar/item/2.png"));

		faceLabel = new JLabel("");
		faceLabel.setBounds(120, 117, 120, 122);
		avatarPanel.add(faceLabel);
		// face icon must be empty in start
		// faceLabel.setIcon(new ImageIcon("./images/avatar/face/1.png"));

		dressLabel = new JLabel("");
		dressLabel.setBounds(91, 209, 155, 82);
		avatarPanel.add(dressLabel);
		// dress label shoul be empty too
		// dressLabel.setIcon(new ImageIcon("./images/avatar/dress/2.png"));

		bodyLabel = new JLabel("");
		bodyLabel.setIcon(new ImageIcon("./images/avatar/body/bodyFemaleWhite.png"));
		bodyLabel.setBounds(94, 77, 159, 209);
		avatarPanel.add(bodyLabel);

		JLabel frameLabel = new JLabel("");
		frameLabel.setIcon(new ImageIcon("./images/avatar/heroFrame5.png"));
		frameLabel.setBounds(27, 4, 268, 352);
		avatarPanel.add(frameLabel);

		/*
		 * Store Panel part
		 * =====================================================================
		 * ===============================
		 */
		JPanel storePanel = new JPanel();
		storePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		storePanel.setBounds(343, 12, 853, 364);
		frame.getContentPane().add(storePanel);
		storePanel.setLayout(null);

		JPanel hairPanel = new JPanel();
		hairPanel.setBounds(22, 12, 821, 101);
		storePanel.add(hairPanel);
		GridBagLayout gbl_hairPanel = new GridBagLayout();
		gbl_hairPanel.columnWidths = new int[] { 91, 91, 91, 91, 91, 91, 91, 91, 91, 0 };
		gbl_hairPanel.rowHeights = new int[] { 117, 0 };
		gbl_hairPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_hairPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		hairPanel.setLayout(gbl_hairPanel);

		JLabel lblHair = new JLabel("Hair:");
		GridBagConstraints gbc_lblHair = new GridBagConstraints();
		gbc_lblHair.fill = GridBagConstraints.BOTH;
		gbc_lblHair.insets = new Insets(0, 0, 0, 5);
		gbc_lblHair.gridx = 0;
		gbc_lblHair.gridy = 0;
		hairPanel.add(lblHair, gbc_lblHair);

		JLabel label_8 = new JLabel("1");
		label_8.setIcon(new ImageIcon("./images/avatar/hair/hairMini/1.png"));
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.fill = GridBagConstraints.BOTH;
		gbc_label_8.insets = new Insets(0, 0, 0, 5);
		gbc_label_8.gridx = 1;
		gbc_label_8.gridy = 0;
		hairPanel.add(label_8, gbc_label_8);

		JLabel label_9 = new JLabel("2");
		label_9.setIcon(new ImageIcon("./images/avatar/hair/hairMini/2.png"));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_9.insets = new Insets(0, 0, 0, 5);
		gbc_label_9.gridx = 2;
		gbc_label_9.gridy = 0;
		hairPanel.add(label_9, gbc_label_9);

		JLabel label_10 = new JLabel("3");
		label_10.setIcon(new ImageIcon("./images/avatar/hair/hairMini/3.png"));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.fill = GridBagConstraints.BOTH;
		gbc_label_10.insets = new Insets(0, 0, 0, 5);
		gbc_label_10.gridx = 3;
		gbc_label_10.gridy = 0;
		hairPanel.add(label_10, gbc_label_10);

		JLabel label_11 = new JLabel("4");
		label_11.setIcon(new ImageIcon("./images/avatar/hair/hairMini/4.png"));
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.fill = GridBagConstraints.BOTH;
		gbc_label_11.insets = new Insets(0, 0, 0, 5);
		gbc_label_11.gridx = 4;
		gbc_label_11.gridy = 0;
		hairPanel.add(label_11, gbc_label_11);

		JLabel label_12 = new JLabel("5");
		label_12.setIcon(new ImageIcon("./images/avatar/hair/hairMini/5.png"));
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.fill = GridBagConstraints.BOTH;
		gbc_label_12.insets = new Insets(0, 0, 0, 5);
		gbc_label_12.gridx = 5;
		gbc_label_12.gridy = 0;
		hairPanel.add(label_12, gbc_label_12);

		JLabel label_13 = new JLabel("6");
		label_13.setIcon(new ImageIcon("./images/avatar/hair/hairMini/6.png"));
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.fill = GridBagConstraints.BOTH;
		gbc_label_13.insets = new Insets(0, 0, 0, 5);
		gbc_label_13.gridx = 6;
		gbc_label_13.gridy = 0;
		hairPanel.add(label_13, gbc_label_13);

		JLabel label_14 = new JLabel(" 7");
		label_14.setIcon(new ImageIcon("./images/avatar/hair/hairMini/7.png"));
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.fill = GridBagConstraints.BOTH;
		gbc_label_14.insets = new Insets(0, 0, 0, 5);
		gbc_label_14.gridx = 7;
		gbc_label_14.gridy = 0;
		hairPanel.add(label_14, gbc_label_14);

		JLabel label_15 = new JLabel("8");
		label_15.setIcon(new ImageIcon("./images/avatar/hair/hairMini/8.png"));
		GridBagConstraints gbc_label_15 = new GridBagConstraints();
		gbc_label_15.fill = GridBagConstraints.BOTH;
		gbc_label_15.gridx = 8;
		gbc_label_15.gridy = 0;
		hairPanel.add(label_15, gbc_label_15);

		JPanel facePanel = new JPanel();
		facePanel.setBounds(22, 125, 821, 84);
		storePanel.add(facePanel);
		GridBagLayout gbl_facePanel = new GridBagLayout();
		gbl_facePanel.columnWidths = new int[] { 91, 91, 91, 91, 91, 91, 91, 91, 91, 0 };
		gbl_facePanel.rowHeights = new int[] { 117, 0 };
		gbl_facePanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_facePanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		facePanel.setLayout(gbl_facePanel);

		JLabel lblFace = new JLabel("Face:");
		GridBagConstraints gbc_lblFace = new GridBagConstraints();
		gbc_lblFace.fill = GridBagConstraints.BOTH;
		gbc_lblFace.insets = new Insets(0, 0, 0, 5);
		gbc_lblFace.gridx = 0;
		gbc_lblFace.gridy = 0;
		facePanel.add(lblFace, gbc_lblFace);

		JLabel label_16 = new JLabel("1");
		label_16.setIcon(new ImageIcon("./images/avatar/face/faceMini/1.png"));
		label_16.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label_16 = new GridBagConstraints();
		gbc_label_16.fill = GridBagConstraints.BOTH;
		gbc_label_16.insets = new Insets(0, 0, 0, 5);
		gbc_label_16.gridx = 1;
		gbc_label_16.gridy = 0;
		facePanel.add(label_16, gbc_label_16);

		JLabel label_17 = new JLabel("2");
		label_17.setIcon(new ImageIcon("./images/avatar/face/faceMini/2.png"));
		GridBagConstraints gbc_label_17 = new GridBagConstraints();
		gbc_label_17.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_17.insets = new Insets(0, 0, 0, 5);
		gbc_label_17.gridx = 2;
		gbc_label_17.gridy = 0;
		facePanel.add(label_17, gbc_label_17);

		JLabel label_18 = new JLabel("3");
		label_18.setIcon(new ImageIcon("./images/avatar/face/faceMini/3.png"));
		GridBagConstraints gbc_label_18 = new GridBagConstraints();
		gbc_label_18.fill = GridBagConstraints.BOTH;
		gbc_label_18.insets = new Insets(0, 0, 0, 5);
		gbc_label_18.gridx = 3;
		gbc_label_18.gridy = 0;
		facePanel.add(label_18, gbc_label_18);

		JLabel label_19 = new JLabel("4");
		label_19.setIcon(new ImageIcon("./images/avatar/face/faceMini/4.png"));
		GridBagConstraints gbc_label_19 = new GridBagConstraints();
		gbc_label_19.fill = GridBagConstraints.BOTH;
		gbc_label_19.insets = new Insets(0, 0, 0, 5);
		gbc_label_19.gridx = 4;
		gbc_label_19.gridy = 0;
		facePanel.add(label_19, gbc_label_19);

		JLabel label_20 = new JLabel("5");
		label_20.setIcon(new ImageIcon("./images/avatar/face/faceMini/5.png"));
		GridBagConstraints gbc_label_20 = new GridBagConstraints();
		gbc_label_20.fill = GridBagConstraints.BOTH;
		gbc_label_20.insets = new Insets(0, 0, 0, 5);
		gbc_label_20.gridx = 5;
		gbc_label_20.gridy = 0;
		facePanel.add(label_20, gbc_label_20);

		JLabel label_21 = new JLabel("6");
		label_21.setIcon(new ImageIcon("./images/avatar/face/faceMini/6.png"));
		GridBagConstraints gbc_label_21 = new GridBagConstraints();
		gbc_label_21.fill = GridBagConstraints.BOTH;
		gbc_label_21.insets = new Insets(0, 0, 0, 5);
		gbc_label_21.gridx = 6;
		gbc_label_21.gridy = 0;
		facePanel.add(label_21, gbc_label_21);

		JLabel label_22 = new JLabel(" 7");
		label_22.setIcon(new ImageIcon("./images/avatar/face/faceMini/7.png"));
		GridBagConstraints gbc_label_22 = new GridBagConstraints();
		gbc_label_22.fill = GridBagConstraints.BOTH;
		gbc_label_22.insets = new Insets(0, 0, 0, 5);
		gbc_label_22.gridx = 7;
		gbc_label_22.gridy = 0;
		facePanel.add(label_22, gbc_label_22);

		JLabel label_23 = new JLabel("8");
		label_23.setIcon(new ImageIcon("./images/avatar/face/faceMini/8.png"));
		GridBagConstraints gbc_label_23 = new GridBagConstraints();
		gbc_label_23.fill = GridBagConstraints.BOTH;
		gbc_label_23.gridx = 8;
		gbc_label_23.gridy = 0;
		facePanel.add(label_23, gbc_label_23);

		/*
		 * item Panel
		 */
		JPanel itemPanel = new JPanel();
		itemPanel.setBounds(22, 213, 821, 84);
		storePanel.add(itemPanel);
		GridBagLayout gbl_itemPanel = new GridBagLayout();
		gbl_itemPanel.columnWidths = new int[] { 91, 91, 91, 91, 91, 91, 91, 91, 91, 0 };
		gbl_itemPanel.rowHeights = new int[] { 117, 0 };
		gbl_itemPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_itemPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		itemPanel.setLayout(gbl_itemPanel);

		JLabel lblItem = new JLabel("Glass:");
		GridBagConstraints gbc_lblItem = new GridBagConstraints();
		gbc_lblItem.fill = GridBagConstraints.BOTH;
		gbc_lblItem.insets = new Insets(0, 0, 0, 5);
		gbc_lblItem.gridx = 0;
		gbc_lblItem.gridy = 0;
		itemPanel.add(lblItem, gbc_lblItem);

		JLabel label_24 = new JLabel("1");
		label_24.setIcon(new ImageIcon("./images/avatar/item/itemsMini/1.png"));
		label_24.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label_24 = new GridBagConstraints();
		gbc_label_24.fill = GridBagConstraints.BOTH;
		gbc_label_24.insets = new Insets(0, 0, 0, 5);
		gbc_label_24.gridx = 1;
		gbc_label_24.gridy = 0;
		itemPanel.add(label_24, gbc_label_24);

		JLabel label_25 = new JLabel("2");
		label_25.setIcon(new ImageIcon("./images/avatar/item/itemsMini/2.png"));
		GridBagConstraints gbc_label_25 = new GridBagConstraints();
		gbc_label_25.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_25.insets = new Insets(0, 0, 0, 5);
		gbc_label_25.gridx = 2;
		gbc_label_25.gridy = 0;
		itemPanel.add(label_25, gbc_label_25);

		JLabel label_26 = new JLabel("3");
		label_26.setIcon(new ImageIcon("./images/avatar/item/itemsMini/3.png"));
		GridBagConstraints gbc_label_26 = new GridBagConstraints();
		gbc_label_26.fill = GridBagConstraints.BOTH;
		gbc_label_26.insets = new Insets(0, 0, 0, 5);
		gbc_label_26.gridx = 3;
		gbc_label_26.gridy = 0;
		itemPanel.add(label_26, gbc_label_26);

		JLabel label_27 = new JLabel("4");
		label_27.setIcon(new ImageIcon("./images/avatar/item/itemsMini/4.png"));
		GridBagConstraints gbc_label_27 = new GridBagConstraints();
		gbc_label_27.fill = GridBagConstraints.BOTH;
		gbc_label_27.insets = new Insets(0, 0, 0, 5);
		gbc_label_27.gridx = 4;
		gbc_label_27.gridy = 0;
		itemPanel.add(label_27, gbc_label_27);

		JLabel lblItem_1 = new JLabel("Item:");
		GridBagConstraints gbc_lblItem_1 = new GridBagConstraints();
		gbc_lblItem_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblItem_1.gridx = 5;
		gbc_lblItem_1.gridy = 0;
		itemPanel.add(lblItem_1, gbc_lblItem_1);

		JLabel label_29 = new JLabel("1");
		label_29.setIcon(new ImageIcon("./images/avatar/item/itemsMini/5.png"));
		GridBagConstraints gbc_label_29 = new GridBagConstraints();
		gbc_label_29.fill = GridBagConstraints.BOTH;
		gbc_label_29.insets = new Insets(0, 0, 0, 5);
		gbc_label_29.gridx = 6;
		gbc_label_29.gridy = 0;
		itemPanel.add(label_29, gbc_label_29);

		JLabel label_30 = new JLabel("2");
		label_30.setIcon(new ImageIcon("./images/avatar/item/itemsMini/6.png"));
		GridBagConstraints gbc_label_30 = new GridBagConstraints();
		gbc_label_30.fill = GridBagConstraints.BOTH;
		gbc_label_30.insets = new Insets(0, 0, 0, 5);
		gbc_label_30.gridx = 7;
		gbc_label_30.gridy = 0;
		itemPanel.add(label_30, gbc_label_30);

		JLabel label_31 = new JLabel("3");
		label_31.setIcon(new ImageIcon("./images/avatar/item/itemsMini/7.png"));
		GridBagConstraints gbc_label_31 = new GridBagConstraints();
		gbc_label_31.fill = GridBagConstraints.BOTH;
		gbc_label_31.gridx = 8;
		gbc_label_31.gridy = 0;
		itemPanel.add(label_31, gbc_label_31);

		/*
		 * Dress Panel
		 * =====================================================================
		 * ==============================================
		 */
		JPanel dressPanel = new JPanel();
		dressPanel.setBounds(22, 293, 821, 59);
		storePanel.add(dressPanel);

		label = new JLabel("3");
		label.setIcon(new ImageIcon("./images/avatar/dress/mini/3.png"));

		label_2 = new JLabel("4");
		label_2.setIcon(new ImageIcon("./images/avatar/dress/mini/4.png"));

		label_3 = new JLabel("5");
		label_3.setIcon(new ImageIcon("./images/avatar/dress/mini/5.png"));

		label_4 = new JLabel("6");
		label_4.setIcon(new ImageIcon("./images/avatar/dress/mini/6.png"));

		label_5 = new JLabel(" 7");
		label_5.setIcon(new ImageIcon("./images/avatar/dress/mini/7.png"));

		label_6 = new JLabel("8");
		label_6.setIcon(new ImageIcon("./images/avatar/dress/mini/8.png"));
		dressPanel.setLayout(new GridLayout(0, 9, 0, 0));

		JLabel lblDress = new JLabel("Dress:");
		dressPanel.add(lblDress);

		JLabel lblNewLabel = new JLabel("1");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setIcon(new ImageIcon("./images/avatar/dress/mini/1.png"));
		dressPanel.add(lblNewLabel);

		JLabel label_1 = new JLabel("2");
		label_1.setIcon(new ImageIcon("./images/avatar/dress/mini/2.png"));
		dressPanel.add(label_1);
		dressPanel.add(label);
		dressPanel.add(label_2);
		dressPanel.add(label_3);
		dressPanel.add(label_4);
		dressPanel.add(label_5);
		dressPanel.add(label_6);

		/*
		 * Command Panel
		 * =====================================================================
		 * ===================================
		 */

		JPanel commandPanel = new JPanel();
		commandPanel.setBorder(null);
		commandPanel.setBounds(12, 388, 595, 267);
		frame.getContentPane().add(commandPanel);
		commandPanel.setLayout(null);

		cmdLog = new JTextArea();
		cmdLog.setBounds(12, 12, 360, 213);
		cmdLog.setEditable(false);
		// commandPanel.add(cmdLog);

		// for scrolling cmd
		JScrollPane scrollPane = new JScrollPane(cmdLog);
		scrollPane.setBounds(new Rectangle(12, 12, 571, 206));
		commandPanel.add(scrollPane);

		currentCmd = new JTextField();
		currentCmd.setBounds(12, 230, 476, 25);
		commandPanel.add(currentCmd);
		currentCmd.setColumns(10);

		submitButton = new JButton("Enter");
		submitButton.setBounds(500, 230, 83, 25);
		commandPanel.add(submitButton);

		/*
		 * ====================================================================
		 * Clock Panel
		 */

		JPanel clockPanel = new JPanel();
		clockPanel.setBorder(null);
		clockPanel.setBounds(698, 388, 209, 265);
		frame.getContentPane().add(clockPanel);
		clockPanel.setLayout(null);

		clockLabel = new JLabel("");
		clockLabel.setIcon(new ImageIcon("./images/timer/0.png"));
		clockLabel.setBounds(12, 0, 195, 266);
		clockPanel.add(clockLabel);

		/*
		 * Target part
		 * =====================================================================
		 * =========================
		 */
		JPanel targetPanel = new JPanel();
		targetPanel.setBounds(936, 376, 317, 279);
		frame.getContentPane().add(targetPanel);
		targetPanel.setLayout(null);

		envelopeLabel = new JLabel("");
		// envelopeLabel.setIcon(new
		// ImageIcon("/home/clay/Desktop/close2.png"));
		envelopeLabel.setBounds(54, 22, 205, 207);
		targetPanel.add(envelopeLabel);

		labelTarget = new JLabel("");
		labelTarget.setBounds(81, 0, 163, 229);
		targetPanel.add(labelTarget);
		labelTarget.setIcon(new ImageIcon(""));

		levelButton = new JButton("LeVeL");
		levelButton.setBounds(91, 242, 135, 25);
		targetPanel.add(levelButton);

		frame.setLocationRelativeTo(null);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

	}

	public void setButtonName(String name) {
		this.doneButton.setText(name);
	}

	public void showScoreFrame(int similarity, HashMap<String, Item> heroItems, int level) {
		ScoreFrame scoreFrame = new ScoreFrame();
		scoreFrame.animateScore(similarity, heroItems, level);

	}

	public void showFinalFrame(String text) {
		FinalFrame finalFrame = new FinalFrame();
		finalFrame.animateFinal(text);
		levelButton.setText("QUIT");
		labelTarget.setIcon(new ImageIcon(""));

	}

	public void openEnvelope(boolean state) {
		if (state == true) {
			envelopeLabel.setIcon(new ImageIcon("./images/avatar/envelope/open3.png"));
		} else {
			envelopeLabel.setIcon(new ImageIcon("./images/avatar/envelope/close3.png"));
		}
		labelTarget.setIcon(new ImageIcon(""));
		levelButton.setText("OPEN!");
	}
}
