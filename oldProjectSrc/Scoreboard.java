import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Scoreboard {

	private JFrame frame;
	private JPanel panel;
	private GridBagConstraints c;
	private int numOfScores = 3;
	private ArrayList<ScoreNode> scores;
	private Comparator<ScoreNode> comparator;
	private DecimalFormat df = new DecimalFormat("#.00");
	
	public Scoreboard () {
		
		frame = new JFrame("High Scores");
		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		comparator = new ScoreNodeComparator();
		
		easyScores();
		mediumScores();
		hardScores();
		
		frame.add(panel);
		frame.setSize(500, 400);
		frame.setVisible(true);
		
	}
	
	private void easyScores() {
		
		scores = GameManager.easyScores;
        Collections.sort(scores, comparator);
		
        String scoreString = "";
        for (int i = 0; i < numOfScores && i < scores.size(); i++) {
        	ScoreNode score = scores.get(i);
        	scoreString = scoreString + "\n" + df.format(score.getScore()) + " " + score.getPlayer();
        }
        
		JTextArea easy = new JTextArea ("EASY LEVEL\n" + scoreString);

		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = 350;
		panel.add(easy, c);
		
	}
	
	private void mediumScores() {
		
		scores = GameManager.mediumScores;
        Collections.sort(scores, comparator);
		
        String scoreString = "";
        for (int i = 0; i < numOfScores && i < scores.size(); i++) {
        	ScoreNode score = scores.get(i);
        	scoreString = scoreString + "\n" + df.format(score.getScore()) + " " + score.getPlayer();
        }
        
		JTextArea medium = new JTextArea ("MEDIUM LEVEL\n" + scoreString);
		
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = 350;
		panel.add(medium, c);
		
	}

	private void hardScores() {
	
		scores = GameManager.hardScores;
        Collections.sort(scores, comparator);
		
        String scoreString = "";
        for (int i = 0; i < numOfScores && i < scores.size(); i++) {
        	ScoreNode score = scores.get(i);
        	scoreString = scoreString + "\n" + df.format(score.getScore()) + " " + score.getPlayer();
        }
        
		JTextArea hard = new JTextArea ("HARD LEVEL\n" + scoreString);
	
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.fill = 350;
		panel.add(hard, c);
		
	}
		
}
