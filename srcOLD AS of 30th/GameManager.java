import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameManager {

	private JFrame mainFrame;
	private MazeGameManager mazeGame;
	private MazeGameOptions mazeOptions;
	private boolean newGameClicked = false;
	private boolean settingsClicked = false;
	
	//static ArrayList<ScoreNode> easyScores;
	//static ArrayList<ScoreNode> mediumScores;
	//static ArrayList<ScoreNode> hardScores;
	
	static Scoreboard easyScores;
	static Scoreboard mediumScores;
	static Scoreboard hardScores;
	public final static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	public final static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	public GameManager() {
	
		mainFrame = new JFrame("Maze of Doom: Start Screen");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 400);
		mainFrame.setBackground(Color.DARK_GRAY);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		mainPanel.setBackground(Color.DARK_GRAY);
		
		ImageIcon titleImage = new ImageIcon("./images/title.jpg");
		JLabel titleLabel = new JLabel(titleImage);
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(titleLabel, c);
		
		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("NEW MAZE");
				newGameClicked = true;
			}
		});
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
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
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(instructionsButton, c);
		
		JButton settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				settingsClicked = true;
			}
		});
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(settingsButton, c);
		
		initialiseScoreboards();
		
		JButton highScoresButton = new JButton("High Scores");
		highScoresButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ScoreboardPrinter scores = new ScoreboardPrinter();
			}
		});
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(highScoresButton, c);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.add(exitButton, c);
		
		mainFrame.setLocation(screenWidth/2-mainFrame.getSize().width/2, screenHeight/2-mainFrame.getSize().height/2);

		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		
		//default maze settings
		mazeOptions = new MazeGameOptions();
				
		while (true) {
			gameManagerLoop();
		}
			
	}
	
	private void initialiseScoreboards () {
		
		easyScores = new Scoreboard(".easy.txt");
		mediumScores = new Scoreboard(".medium.txt");
		hardScores = new Scoreboard(".hard.txt");
		
	}
	
	public void gameManagerLoop() {
		while (!newGameClicked && !settingsClicked) {	
			System.out.print("");
		}
		mainFrame.setVisible(false);
		if (settingsClicked) {
			new CustomiseMazeGame(mazeOptions);
			settingsClicked = false;
		}
		else if (newGameClicked) {
			newGameClicked = false;
			startNewGame();
		}
		mainFrame.setVisible(true);
	}
	
	public void startNewGame() {
		mazeGame = new MazeGameManager(mazeOptions);
		mazeGame.startGame();
	}
}
