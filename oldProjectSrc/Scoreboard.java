import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.PriorityQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Scoreboard {

	private JFrame frame;
	private JPanel panel;
	private GridBagConstraints c;
	private int numOfScores = 2;
	
	public Scoreboard () {
		
		frame = new JFrame("High Scores");
		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		
		easyScores();
		//mediumScores();
		//hardScores();
		
		frame.add(panel);
		frame.setSize(500, 400);
		frame.setVisible(true);
		
	}
	
	private void easyScores() {
		
		String scores = "";
		
		PriorityQueue<String> queue = GameManager.easyScores;

		int count = 0;
		for (String s : queue) {
			if (count > numOfScores) {
				break;
			}
			scores = scores + "\n" + s;
		}
		
		JTextArea navInstructions = new JTextArea ("EASY LEVEL\n\n" + scores);

		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = 350;
		panel.add(navInstructions, c);
		
	}
	
}
