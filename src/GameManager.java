import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A class that initialises all the objects necessary to run a maze game.
 * Also creates a frame to act as the main menu.
 */
public class GameManager {

	private JFrame mainFrame;
	private MazeGameManager mazeGame;
	private MazeGameOptions mazeOptions;
	private boolean newGameClicked = false;
	private boolean settingsClicked = false;
	
	static Scoreboard easyScores;
	static Scoreboard mediumScores;
	static Scoreboard hardScores;
	public final static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	public final static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private ArrayList<MazeTheme> themes;
	private ImageStore themePreviews;

	/**
	 * Sets up the Game's main menu
	 */
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
		themes = new ArrayList<MazeTheme>();
		themes.add(new MazeTheme(Arrays.asList("Default","./images/coin20.png",
				"./images/end80.png",
				"./images/path80.png",
				"./images/player20.png",
				"./images/preview110.png",
				"./images/start80.png",
				"./images/wall80.png"
				)));
		themes.add(new MazeTheme(Arrays.asList("Lava","./images/lava/coin20.png",
				"./images/lava/end20.png",
				"./images/lava/path20.png",
				"./images/lava/player20.png",
				"./images/lava/preview110.png",
				"./images/lava/start20.png",
				"./images/lava/wall20.png"
				)));
		themes.add(new MazeTheme(Arrays.asList("Lava2","./images/lava/coin20.png",
				"./images/lava/end20.png",
				"./images/lava/path20.png",
				"./images/lava/player20.png",
				"./images/lava/preview110.png",
				"./images/lava/start20.png",
				"./images/lava/wall20.png"
				)));
		themes.add(new MazeTheme(Arrays.asList("Lava3","./images/lava/coin20.png",
				"./images/lava/end20.png",
				"./images/lava/path20.png",
				"./images/lava/player20.png",
				"./images/lava/preview110.png",
				"./images/lava/start20.png",
				"./images/lava/wall20.png"
				)));
		themes.add(new MazeTheme(Arrays.asList("Default2","./images/coin20.png",
				"./images/end80.png",
				"./images/path80.png",
				"./images/player20.png",
				"./images/preview110.png",
				"./images/start80.png",
				"./images/wall80.png"
				)));
		themes.add(new MazeTheme(Arrays.asList("Default3","./images/coin20.png",
				"./images/end80.png",
				"./images/path80.png",
				"./images/player20.png",
				"./images/preview110.png",
				"./images/start80.png",
				"./images/wall80.png"
				)));
		themes.add(new MazeTheme(Arrays.asList("Default4","./images/coin20.png",
				"./images/end80.png",
				"./images/path80.png",
				"./images/player20.png",
				"./images/preview110.png",
				"./images/start80.png",
				"./images/wall80.png"
				)));
		themePreviews = new ImageStore();
		for (int i = 0; i < themes.size(); i++) {
			themePreviews.add(themes.get(i).getPathOfImage("PREVIEW"), themes.get(i).getName());
		}
		
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
		mazeOptions.setTheme(themes.get(0));

				
		while (true) {
			gameManagerLoop();
		}
			
	}
	
	/**
	 * Initialises the maze game's score boards
	 */
	private void initialiseScoreboards () {
		
		easyScores = new Scoreboard(".easy.txt");
		mediumScores = new Scoreboard(".medium.txt");
		hardScores = new Scoreboard(".hard.txt");
		
	}
	
	/**
	 * Hides the main menu when a maze is run or the settings screen is opened
	 */
	public void gameManagerLoop() {
		
		while (!newGameClicked && !settingsClicked) {	
			System.out.print("");
		}
		mainFrame.setVisible(false);
		if (settingsClicked) {
			new CustomiseMazeGame(mazeOptions, themePreviews, themes);
			settingsClicked = false;
		}
		else if (newGameClicked) {
			newGameClicked = false;
			startNewGame();
		}
		mainFrame.setVisible(true);
	
	}
	
	/**
	 * Starts a new maze game
	 */
	public void startNewGame() {
		mazeGame = new MazeGameManager(mazeOptions);
		mazeGame.startGame();
	}
	
}
