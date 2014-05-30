import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/** Class to display the top scores of the maze game in a new frame.
 */
public class ScoreboardPrinter {

	private JFrame frame;
	private JPanel panel;
	private GridBagConstraints c;
	private int numOfScores = 3; // The number of scores to print
	private Scoreboard scores;
	private Font titleFont;
	private Font textFont;
	
	/** 
	 * Initialises the High Scores frame
	 */
	public ScoreboardPrinter () {
		
		titleFont = new Font("Title", Font.BOLD, 20);
		textFont = new Font("Text", Font.PLAIN, 14);
		
		frame = new JFrame("High Scores");
		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		
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
	
	/**
	 * Displays the top scores on easy level
	 */
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
		
        for (int i = 0; i < numOfScores && i < scores.size(); i++) {
        	
        	String s = scores.getScore(i);
        	JLabel scoreLine = new JLabel(s);
        	scoreLine.setFont(textFont);
        	c.gridx = 0;
        	c.gridy = 2 + i;
        	panel.add(scoreLine, c);
        	
        }
        
	}
	
	/**
	 * Displays the top scores on the medium level
	 */
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
		
        for (int i = 0; i < numOfScores && i < scores.size(); i++) {
        	
        	String s = scores.getScore(i);
        	JLabel scoreLine = new JLabel(s);
        	scoreLine.setFont(textFont);
        	c.gridx = 0;
        	c.gridy = 6 + i;
        	panel.add(scoreLine, c);
        	
        }
		
	}

	/**
	 * Displays the top scores on hard level
	 */
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
		
        for (int i = 0; i < numOfScores && i < scores.size(); i++) {
        	
        	String s = scores.getScore(i);
        	JLabel scoreLine = new JLabel(s);
        	scoreLine.setFont(textFont);
        	c.gridx = 0;
        	c.gridy = 10 + i;
        	panel.add(scoreLine, c);
        	
        }
		
	}
		
}
