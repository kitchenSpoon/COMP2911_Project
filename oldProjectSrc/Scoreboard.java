import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Scoreboard {

	private JFrame frame;
	private JPanel panel;
	private GridBagConstraints c;
	private int numOfScores = 3;
	private PriorityQueue<String> queue;
	private ArrayList<String> tempScores;
	
	public Scoreboard () {
		
		frame = new JFrame("High Scores");
		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		
		easyScores();
		mediumScores();
		hardScores();
		
		frame.add(panel);
		frame.setSize(500, 400);
		frame.setVisible(true);
		
	}
	
	private void easyScores() {
		
		String scores = "";
		String temp;
		tempScores = new ArrayList<String>();
		
		queue = GameManager.easyScores;

		for (int i = 0; i < numOfScores && i < queue.size(); i++) {
			temp = queue.poll();
			tempScores.add(temp);
			scores = scores + "\n" + temp;
		}
		
		for (String s: tempScores) {
			queue.add(s);
		}
		
		JTextArea easy = new JTextArea ("EASY LEVEL\n" + scores);

		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = 350;
		panel.add(easy, c);
		
	}
	
	private void mediumScores() {
		
		String scores = "";
		String temp;
		tempScores = new ArrayList<String>();
		
		queue = GameManager.mediumScores;

		for (int i = 0; i < numOfScores && i < queue.size(); i++) {
			temp = queue.poll();
			tempScores.add(temp);
			scores = scores + "\n" + temp;
		}
		
		for (String s: tempScores) {
			queue.add(s);
		}
		
		JTextArea medium = new JTextArea ("MEDIUM LEVEL\n" + scores);
		
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = 350;
		panel.add(medium, c);
		
	}

	private void hardScores() {
	
		String scores = "";
		String temp;
		tempScores = new ArrayList<String>();
		
		queue = GameManager.hardScores;
	
		for (int i = 0; i < numOfScores && i < queue.size(); i++) {
			temp = queue.poll();
			tempScores.add(temp);
			scores = scores + "\n" + temp;
		}
		
		for (String s: tempScores) {
			queue.add(s);
		}
		
		JTextArea hard = new JTextArea ("HARD LEVEL\n" + scores);
	
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.fill = 350;
		panel.add(hard, c);
		
	}
		
}
