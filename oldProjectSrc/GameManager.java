import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameManager {

	private JFrame mainFrame;
	private MazeGameManager mazeGame;
	private MazeGameOptions mazeOptions;
	private boolean newGameClicked = false;
	
	static ArrayList<ScoreNode> easyScores;
	static ArrayList<ScoreNode> mediumScores;
	static ArrayList<ScoreNode> hardScores;
	
	public GameManager() {
		mainFrame = new JFrame("Maze of Doom: Start Screen");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(200, 250);

		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				newGameClicked = true;
			}
		});
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(newMazeButton, c);
		
		JButton instructionsButton = new JButton("Instructions");
		instructionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				MazeGameInstructions instructions = new MazeGameInstructions();
			}
		});
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(instructionsButton, c);
		
		JButton settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CustomiseMazeGame customise = new CustomiseMazeGame(mazeOptions);
			}
		});
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(settingsButton, c);
		
		initialiseScoreboards();
		
		JButton highScoresButton = new JButton("High Scores");
		highScoresButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Scoreboard scores = new Scoreboard();
			}
		});
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(highScoresButton, c);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(exitButton, c);
		
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		
		//default maze settings
		mazeOptions = new MazeGameOptions();
				
		while (true) {
			gameManagerLoop();
		}
		
		
	}
	
	private void initialiseScoreboards () {
		
		easyScores = new ArrayList<ScoreNode>();
		mediumScores = new ArrayList<ScoreNode>();
		hardScores = new ArrayList<ScoreNode>();
		
	}
	
	public void gameManagerLoop() {
		while (!newGameClicked) {
			System.out.print("");
		}
		mainFrame.setVisible(false);
		newGameClicked = false;
		startNewGame();
		mainFrame.setVisible(true);
	}
	
	public void startNewGame() {
		mazeGame = new MazeGameManager(mazeOptions);
		mazeGame.startGame();
	}
}
