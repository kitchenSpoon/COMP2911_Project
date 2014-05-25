import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class MazeGameInstructions {

	private JFrame frame;
	private JPanel panel;
	private GridBagConstraints c;
	
	public MazeGameInstructions () {
		
		frame = new JFrame("How to Play");
		panel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		
		nav();
		scoring();
		multiplayer();
		
		frame.add(panel);
		frame.setSize(500, 400);
		frame.setVisible(true);
		
	}
	
	public void nav () {
		
		JTextArea navInstructions = new JTextArea ("NAVIGATION\n\n"
				                   + "Play as the blue box\n"
				                   + "Use the arrow keys to move\n"
				                   + "The goal is to move from the green box to the red"
				                   );
		
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(navInstructions, c);
		
	}

	
	public void scoring () {
	
		JTextArea navInstructions = new JTextArea ("SCORING\n\n"
                + "Each Treasure is worth 10 points\n"
				+ "Your score is determined by (treasure points) - (time taken)\n"
                + "If the Treasure setting is off, your score will be negative"
				);

		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(navInstructions, c);
		
	}
	
	public void multiplayer () {
		
		JTextArea navInstructions = new JTextArea ("MULTIPLAYER\n\n"
                + "Player 1 uses the arrow keys to move\n" 
                + "Player 2 uses the WASD keys to move - W (up), S (down), A (left) D (right)\n"
                + "Players will start at opposite corners of the board\n"
                + "The goal is to move to the other player's starting position\n"
                + "The first player to do so will win\n"
                + "\n If the Treasure setting is on, the winner will be determined by\n"
                + "the player with the most amount of points, as determined by standard scoring"
                );

		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(navInstructions, c);
		
	}
	
	
}
