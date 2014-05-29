import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MazeGameManager {
	
	public final static int WIN = 1;
	private int winner = 0;

	JFrame frame;
	JPanel menuPanel;
	Player player;
	Player player2;
	Maze maze;
	StatusPopup popup;
	static String input = "NO MOVE";
	InputReceiver inputReceiver;
	MazePanel mazePanel;
	JTextArea scoreText;
	JPanel bottomPanel;
	long start;
	boolean resetGame = false;
	boolean playing = true; // set to false when main menu button is pressed
	MazeGameOptions mazeOptions;
	Scoreboard scores;
	DecimalFormat df = new DecimalFormat("#.00");
	public static ImageStore mazeImages = new ImageStore();
	int themeReference;
	private boolean newGameClicked = false;
	public MazeGameManager(MazeGameOptions _mazeOptions){
		mazeOptions = _mazeOptions;
		mazeImages.add("./images/end80.png", "END");
		mazeImages.add("./images/path80.png", "PATH");
		mazeImages.add("./images/wall80.png", "WALL");
		mazeImages.add("./images/player20.png", "PLAYER");
		mazeImages.add("./images/start80.png", "START");
		mazeImages.add("./images/coin20.png", "COIN");
		
	}
	
	/**
	 * Start game
	 */
	public void startGame() {
		gameLoop();
	}
	
	/**
	 * Set game attributes associated with different game difficulty level.
	 * @param frame
	 */
	public void checkDifficulty(JFrame frame){
		if (mazeOptions.getDifficulty() == 0) {
			int height = 11, width = 11;
			maze = new Maze(width, height);
			frame.setSize(Math.max((width+1)*20,350), (height-1) * 20 + (2 * 80));
			scores = GameManager.easyScores;
		}
		else if (mazeOptions.getDifficulty() == 1) {
			int height = 23, width = 31;
			maze = new Maze(width, height);
			frame.setSize(Math.max((width+1)*20,350), (height-1) * 20 + (2 * 80));
			scores = GameManager.mediumScores;
		}
		else {
			int height = 27,width = 43;
			maze = new Maze(width, height);
			frame.setSize(Math.max((width+1)*20,350), (height-1) * 20 + (2 * 80));
			scores = GameManager.hardScores;
		}
	}
	
	/**
	 * Check if the option to generate treasure is set and generate treasure if it is.
	 * @param frame 
	 */
	public void checkTreasure(JFrame frame){
		if (mazeOptions.isHasTreasure() == true) {
			maze.generateTreasure();
		}
	}
	
	/**
	 * Check Different game options and modify the game accordingly
	 * @param frame 
	 */
	public void checkGameOptions(JFrame frame){
		checkDifficulty(frame);
		checkTreasure(frame);
	}
	
	/**
	 * Construct maze game.
	 */
	private void constructMazeGame() {
		inputReceiver = new InputReceiver();
		if (newGameClicked) {
			frame.dispose();
			newGameClicked = false;
		}
		frame = new JFrame("Maze of Doom");
		frame.setBackground(Color.DARK_GRAY);
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.DARK_GRAY);
		topMenu();
		
		checkGameOptions(frame);
		
		if(maze == null) System.out.println("Maze must be initialized before player");
		player = new Player("Jack", 1, 1,0);
		
		//multiplayer options
		if(mazeOptions.isHasMultiplayer())
			player2 = new Player("Jack2", maze.getHeight()-2, maze.getWidth()-2,1);
		
		popup = new StatusPopup(frame);
		frame.add(menuPanel, BorderLayout.NORTH);
		frame.addKeyListener(inputReceiver);
		
		menuPanel.addKeyListener(inputReceiver);
		mazePanel = new MazePanel(maze.getTiles(), 1, 1, player, player2);
		mazePanel.setBackground(Color.DARK_GRAY);
		
		bottomBar();
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mazePanel, BorderLayout.CENTER);
		frame.setLocation(GameManager.screenWidth/2-frame.getSize().width/2, GameManager.screenHeight/2-frame.getSize().height/2);
		frame.setVisible(true);
		frame.requestFocus();
		
		//remove
		//SoundPlayer.playBGSound();
		
		start = System.currentTimeMillis();
		
	}
	
	/**
	 * Game loop, construct maze, handles inputs and game logic
	 */
	private void gameLoop() {

		constructMazeGame();
		
		while (playing && frame.isShowing() && !newGameClicked) {
			if (!input.isEmpty()) {
				//timer for points
				long end = System.currentTimeMillis();
				double duration = (end - start)/1000.0;
				
				if (!mazeOptions.isHasMultiplayer()) {
					scoreText.setText("Time elapsed: " + df.format(duration) + " seconds\n" + 
			                  "Treasure collected: " + (int) player.getScore());
					
					if (input.equals("UP") || input.equals("DOWN")
							|| input.equals("LEFT") || input.equals("RIGHT")
							|| input.equals("RESET")) {
						if (resetGame) {
							input = "RESET";
							resetGame = false;
						}
						
						int oldX = player.getX();
						int oldY = player.getY();
						player.updatePlayer(maze, input);
						updatePlayer(player, oldX, oldY);
						input = "NO_MOVE";
					}
				} else {
					scoreText.setText("Time elapsed: " + df.format(duration) + " seconds\n" + 
			                  "P1 Treasure collected: " + (int) player.getScore() + "\n" +
			                  "P2 Treasure collected: " + (int) player2.getScore());
					
					if (input.equals("UP") || input.equals("DOWN")
							|| input.equals("LEFT") || input.equals("RIGHT")
							|| input.equals("RESET")) {
						if (resetGame) {
							input = "RESET";
							resetGame = false;
						}
						
						int oldX = player.getX();
						int oldY = player.getY();
						player.updatePlayer(maze, input);
						updatePlayer(player, oldX, oldY);
						input = "NO_MOVE";
					} else if (input.equals("UP2") || input.equals("DOWN2")
							|| input.equals("LEFT2") || input.equals("RIGHT2")
							|| input.equals("RESET")) {
						if (resetGame) {
							input = "RESET";
							resetGame = false;
						}
						
						int oldX = player2.getX();
						int oldY = player2.getY();
						player2.updatePlayer(maze, input);
						updatePlayer(player2, oldX, oldY);
						input = "NO_MOVE";
					}
				}
			}
			// maze.printMaze(player.getX(),player.getY()); /*debuggin*/
			
			//checks if players have walked over any treasure
			updateTreasure(player, player2, maze);
			
			//checks if anyone won
			if (checkGame(player, player2, maze)) {
				break;
			}
		}
		
		if (playing && frame.isShowing() && !newGameClicked) {
			long end = System.currentTimeMillis();
			double duration = (end - start)/1000.0;
			
			String winnerName;
			double winnerScore;
			
			//multiplayer options
			if(mazeOptions.isHasMultiplayer()){
				if(winner == 1){
					winnerName = "Player 1";
					winnerScore = ((player.getScore() * 10) - duration);
				} else {
					winnerName = "Player 2";
					winnerScore = ((player2.getScore() * 10) - duration);
				}
			} else {
				winnerName = "You";
				winnerScore = ((player.getScore() * 10) - duration);
			}
			
			String playerName = popup.winPopupCustom(winnerName + " Win!! " + winnerName + " took " + duration + " seconds\n"
								 + winnerName + " score " + winnerScore + 
								 " points. (10 points for every treasure minus the time taken)");
			
			ScoreNode newScore = new ScoreNode(winnerScore, playerName);
			scores.addScore(newScore);
			
			System.out.println("You Win!! You took " + duration + " seconds\n"
								+ "You score " + ((player.getScore() * 10) - duration) + 
								" points. (10 points for every treasure minus the time taken)");
		} 
		
		frame.dispose();
		if(newGameClicked) {
			startGame();
		}
	}

	/**
	 * Check collision with treasure and picks it up.
	 * @param player
	 * @param player2
	 * @param maze
	 */
	public void updateTreasure(Player player,Player player2, Maze maze) {
		System.out.println(player.getX() + " " + player.getY());
		if(maze.isTreasure(player.getX(),player.getY())){
			System.out.println("Treasure");
			maze.setTreasure(player.getX(),player.getY(),false);
			player.setScore(player.getScore() + 1);
			SoundPlayer.playCoinSound();
		}
		
		//multiplayer options
		if(mazeOptions.isHasMultiplayer()){
			if(maze.isTreasure(player2.getX(),player2.getY())){
				System.out.println("Treasure");
				maze.setTreasure(player2.getX(),player2.getY(),false);
				player2.setScore(player2.getScore() + 1);
				SoundPlayer.playCoinSound();
			}
		}
	}
	
	/**
	 * Checks if the game has ended.
	 * @param player
	 * @param player2
	 * @param maze
	 * @return
	 */
	public boolean checkGame(Player player,Player player2, Maze maze) {
		MazeNode start = maze.getStart();
		MazeNode end = maze.getEnd();
		if (player.getX() == end.getX() && player.getY() == end.getY()) {
			winner = 1;
			SoundPlayer.playWinSound();
			return true;
		}
		
		//multiplayer options
		if(mazeOptions.isHasMultiplayer()){
			if (player2.getX() == start.getX() && player2.getY() == start.getY()) {
				winner = 2;
				SoundPlayer.playWinSound();
				return true;
			}
		}
		return false;
	}

	/**
	 * In game options
	 */
	public void topMenu() {

		//generate new maze button
		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				newGameClicked = true;
			}
		});

		// reset player position button
		JButton resetPlayerButton = new JButton("Restart");
		resetPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				start = System.currentTimeMillis();
				
				maze.regenerateTreasure();
				
				player.updatePlayer(maze, "RESET");
				player.setScore(0);
				updatePlayer(player,player.getX(), player.getY());
				
				//multiplayer options
				if(mazeOptions.isHasMultiplayer()){
					player2.updatePlayer(maze, "RESET");
					player2.setScore(0);
					updatePlayer(player2,player2.getX(), player2.getY());
				}
				
				frame.requestFocus();
			}
		});
		
		// mute/unmute button
		final JButton muteToggleButton = new JButton("Mute/Unmute");
		muteToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				SoundPlayer.toggleSound();
				if(SoundPlayer.getHasSound()){
					muteToggleButton.setSelected(true);
				} else {
					muteToggleButton.setSelected(false);
				}
				frame.requestFocus();
			}
		});

		menuPanel.add(newMazeButton);
		menuPanel.add(resetPlayerButton);
		menuPanel.add(muteToggleButton);
		
	}
	
	/**
	 * In game options
	 */
	public void bottomBar () {
		
		bottomPanel = new JPanel (new GridBagLayout());
		bottomPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints c = new GridBagConstraints();
		
		scoreText = new JTextArea();
		scoreText.setBackground(Color.DARK_GRAY);
		scoreText.setForeground(Color.WHITE);
		c.gridx = 0;
		c.gridy = 0;
		bottomPanel.add(scoreText, c);
		
		//go back to main menu
		JButton mainMenuButton = new JButton("Main Menu");
		mainMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				playing = false;
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		bottomPanel.add(mainMenuButton, c);
		
		frame.add(bottomPanel, BorderLayout.SOUTH);

	}
	
	/**
	 * Update player's graphic
	 * Should this be placed here? 
	 * @param p Player whose graphic is to be moved.
	 * @param oldX Old position
	 * @param oldY Old position
	 */
	public void updatePlayer(Player p, int oldX, int oldY) {
		mazePanel.moveSquare(p,oldX, oldY);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();	
	}
}
