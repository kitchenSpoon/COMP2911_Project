import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;


public class Scoreboard {

	private JFrame frame;
	private JPanel panel;
	private GridBagConstraints c;
	private int numOfScores = 3;
	private ArrayList<ScoreNode> scores;
	private Comparator<ScoreNode> comparator;
	private DecimalFormat df = new DecimalFormat("#.00");
	private Font titleFont;
	private Font textFont;
	
	
	public Scoreboard () {
		
		titleFont = new Font("Title", Font.BOLD, 20);
		textFont = new Font("Text", Font.PLAIN, 14);
		
		frame = new JFrame("High Scores");
		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		comparator = new ScoreNodeComparator();
		
		JLabel title = new JLabel("TOP SCORES FOR MAZE OF DOOM", JLabel.CENTER);
		title.setFont(titleFont);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weighty = 1;
		panel.add(title, c);
		
		easyScores();
	    mediumScores();
		hardScores();
		
		frame.add(panel);
		frame.setSize(500, 400);
		frame.setVisible(true);
		
	}
	
	private void easyScores() {
		
		JLabel easyTitle = new JLabel("Easy", JLabel.CENTER);
		easyTitle.setFont(titleFont);
		easyTitle.setForeground(Color.DARK_GRAY);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weighty = 1;
		panel.add(easyTitle, c);
		
		scores = GameManager.easyScores;
        Collections.sort(scores, comparator);
		
        for (int i = 0; i < numOfScores && i < scores.size(); i++) {
        	
        	ScoreNode score = scores.get(i);
        	JLabel scoreLine = new JLabel(score.getScore() + ".........." + score.getPlayer());
        	scoreLine.setFont(textFont);
        	c.gridx = 0;
        	c.gridy = 2 + i;
        	panel.add(scoreLine, c);
        	
        }
        
	}
	
	private void mediumScores() {
		
		JLabel mediumTitle = new JLabel("Medium", JLabel.CENTER);
		mediumTitle.setFont(titleFont);
		mediumTitle.setForeground(Color.DARK_GRAY);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.weighty = 1;
		panel.add(mediumTitle, c);
		
		scores = GameManager.mediumScores;
        Collections.sort(scores, comparator);
		
        for (int i = 0; i < numOfScores && i < scores.size(); i++) {
        	
        	ScoreNode score = scores.get(i);
        	JLabel scoreLine = new JLabel(score.getScore() + ".........." + score.getPlayer());
        	scoreLine.setFont(textFont);
        	c.gridx = 0;
        	c.gridy = 6 + i;
        	panel.add(scoreLine, c);
        	
        }
		
	}

	private void hardScores() {
	
		JLabel hardTitle = new JLabel("Hard", JLabel.CENTER);
		hardTitle.setFont(titleFont);
		hardTitle.setForeground(Color.DARK_GRAY);
		
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		c.weighty = 1;
		panel.add(hardTitle, c);
		
		scores = GameManager.hardScores;
        Collections.sort(scores, comparator);
		
        for (int i = 0; i < numOfScores && i < scores.size(); i++) {
        	
        	ScoreNode score = scores.get(i);
        	JLabel scoreLine = new JLabel(score.getScore() + ".........." + score.getPlayer());
        	scoreLine.setFont(textFont);
        	c.gridx = 0;
        	c.gridy = 10 + i;
        	panel.add(scoreLine, c);
        	
        }
		
	}
		
}
