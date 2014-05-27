import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class MazeGameInstructions {
	
	private JFrame frame;
	private JSplitPane splitPane;
	private JSplitPane splitPaneSingle;
	private JSplitPane splitPaneMultiplayer;
	
	public MazeGameInstructions () {
		
		frame = new JFrame("How to Play");
		
		initialiseSingle();
		initialiseMultiplayer();

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPaneSingle, splitPaneMultiplayer);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);
        splitPane.setPreferredSize(new Dimension(400, 400));
         
        frame.add(splitPane);
        frame.setSize(650,450);
        frame.setVisible(true);
		 
	}
	
	private void initialiseSingle () {
		
		JLabel title = new JLabel("Single Player Mode", JLabel.CENTER);
		
		JTextArea singleInstructions = new JTextArea (
				
				"NAVIGATION\n\n"
				+ "Use the arrow keys to move.\n"
				+ "\n"
				+ "The 'New Maze' button will generate a new maze to play\n"
				+ "The 'Restart' button will restart the current game, resetting the character to the start "
				+ "and setting points to 0.\n"
				+ "The 'Main Menu' button will exit the current game and open the Main Menu.\n"
				
				+ "\n\n"
				
				+ "WINNING THE GAME\n\n"
				+ "The goal is to move from the starting square at the top left corner of the maze "
				+ "to the bottom right square of the maze."
				
                + "\n\n"
                
                + "SCORING\n\n"
                + "If the maze has treasure, each coin collected will add 10 points to the player's score. "
                + "After completing the maze, the score will be calculated by (treasure points) - (time taken)."
                );
		
		singleInstructions.setEditable(false);
		singleInstructions.setLineWrap(true);
		singleInstructions.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(singleInstructions);
	    scrollPane.setMinimumSize(new Dimension(100, 50));
	    
	    splitPaneSingle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, scrollPane);
	    splitPaneSingle.setDividerSize(1);
	    splitPaneSingle.setPreferredSize(new Dimension(400, 300));
	    
	}
	
	private void initialiseMultiplayer () {
		
		JLabel title = new JLabel("Multiplayer Mode", JLabel.CENTER);
		
		//JTextArea multiplayerInstructions2 = new JTextArea (10, 30);
		JTextArea multiplayerInstructions = new JTextArea(
				
				"NAVIGATION\n\n"
				+ "Player 1 starts at the top left corner of the maze and uses the arrow keys to move.\n"
				+ "Player 2 starts at the bottom right corner of the maze and uses the WASD keys to move.\n"
				+ "\n"
				+ "The 'New Maze' button will generate a new maze to play\n"
				+ "The 'Restart' button will restart the current game, resetting the character to the start "
				+ "and setting points to 0.\n"
				+ "The 'Main Menu' button will exit the current game and open the Main Menu.\n"
				
				+ "\n\n"
				
				+ "WINNING THE GAME\n\n"
				+ "The goal is to move to the other player's starting position. "
				+ "Player 1 aims to move to the bottom right corner of the maze, and "
				+ "PLayer 2 aims to move to the top left corner of the maze.\n"
				+ "The first player to do so will win the game.\n"
				
                + "\n\n"
                
                + "SCORING\n\n"
                + "<<TO BE FINALISED>>"
				
                );
		
		multiplayerInstructions.setEditable(false);
		multiplayerInstructions.setLineWrap(true);
		multiplayerInstructions.setWrapStyleWord(true);
		multiplayerInstructions.setCaretPosition(0);
		
		JScrollPane scrollPane = new JScrollPane(multiplayerInstructions);
		scrollPane.setMinimumSize(new Dimension(100, 50));
		
		splitPaneMultiplayer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, scrollPane);
        splitPaneMultiplayer.setDividerSize(1);
		splitPaneMultiplayer.setPreferredSize(new Dimension(400, 400));
	
	}
	
}