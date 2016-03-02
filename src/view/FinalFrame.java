package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextArea;

/*
 * This class's purpose is just or show Congrant message
 * at the end with animation
 */

public class FinalFrame extends JDialog {
	
	// do not know what it is but it suggested by Eclipse
	private static final long serialVersionUID = 1L;
	
	
	JLabel hand1, hand2, hand3, hand4;
	JTextArea textArea;


	/**
	 * Create the dialog.
	 */
	public FinalFrame() {
		
		setBounds(100, 100, 522, 375);
		getContentPane().setLayout(null);

		hand1 = new JLabel("");
		hand1.setIcon(new ImageIcon("./images/hand/1.png"));
		hand1.setBounds(-74, 40, 121, 95);
		getContentPane().add(hand1);

		hand2 = new JLabel("");
		hand2.setIcon(new ImageIcon("./images/hand/2.png"));
		hand2.setBounds(480, 61, 128, 95);
		getContentPane().add(hand2);

		hand3 = new JLabel("");
		hand3.setIcon(new ImageIcon("./images/hand/3.png"));
		hand3.setBounds(-74, 254, 132, 142);
		getContentPane().add(hand3);

		hand4 = new JLabel("");
		hand4.setIcon(new ImageIcon("./images/hand/4.png"));
		hand4.setBounds(448, 272, 132, 162);
		getContentPane().add(hand4);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Courier 10 Pitch", Font.BOLD, 20));
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBounds(93, 100, 327, 172);
		getContentPane().add(textArea);
		
		// to show frame at center of screen
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/*
	 * animation method with thread
	 */
	void animateFinal(String text) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				// for positions
				int x, y, a, b;
				x = -74;
				y = 40;
				a = 448;
				b = 272;
				for (int i = 0; i < 12; i++) {
					// Thread.sleep
					delay(10);
					// for ourselves
					System.out.println("Final frame");
					// move labels 12 times
					hand1.setBounds(x += 5, y, 121, 95);
					hand4.setBounds(a -= 4, b -= 2, 132, 162);
				}

				x = 480;
				y = 61;
				a = -74;
				b = 254;

				for (int i = 0; i < 12; i++) {
					delay(20);
					// move label 12 times
					hand2.setBounds(x -= 5, 61, 128, 95);
				}

				for (int i = 0; i < 12; i++) {
					delay(12);
					// move label 12 times
					hand3.setBounds(a += 3, b -= 2, 132, 142);
				}
				
				// delay for text
				delay(500);
				
				// print text like letter by letter
				for (int i = 0; i < text.length(); i++) {
					textArea.setText(text.substring(0, i));
					delay(50);
				}
			}
		};
		thread.start();
	}
	
	/*
	 * in This class we use too much Thread sleep
	 * thats why we decided to put it to method delay
	 */
	void delay(int milisec){
		try {
			Thread.sleep(milisec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
