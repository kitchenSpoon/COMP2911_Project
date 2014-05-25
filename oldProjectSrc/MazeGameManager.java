import java.awt.BorderLayout;
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
	boolean resetGame = false;
	boolean playing = true; // set to false when main menu button is pressed
	MazeGameOptions mazeOptions;
	PriorityQueue<String> scores;
	public static ImageStore mazeImages = new ImageStore();
	int themeReference;
	public MazeGameManager(MazeGameOptions _mazeOptions){
		mazeOptions = _mazeOptions;
		mazeImages.add("./images/end80.tif", "END");
		mazeImages.add("./images/path80.tif", "PATH");
		mazeImages.add("./images/wall80.tif", "WALL");
		mazeImages.add("./images/player80.tif", "PLAYER");
		mazeImages.add("./images/start80.tif", "START");
		mazeImages.add("./images/coin20.png", "COIN");
		
	}
	
	public void startGame() {
		gameLoop();
	}
	public void checkDifficulty(JFrame frame){
		if (mazeOptions.getDifficulty() == 0) {
			maze = new Maze(11, 11);
			frame.setSize(220, 345);
			scores = GameManager.easyScores;
		}
		else if (mazeOptions.getDifficulty() == 1) {
			//maze = new Maze(21, 21);
			maze = new Maze(19, 19);
			frame.setSize(420, 545);
			scores = GameManager.mediumScores;
		}
		else {
			maze = new Maze(31, 31);
			frame.setSize(620, 745);
			scores = GameManager.hardScores;
		}
	}
	public void checkTreasure(JFrame frame){
		if (mazeOptions.isHasTreasure() == true) {
			maze.generateTreasure();
		}
	}
	public void checkGameOptions(JFrame frame){
		checkDifficulty(frame);
		checkTreasure(frame);
	}
	private void gameLoop() {

		inputReceiver = new InputReceiver();
		frame = new JFrame("Maze of Doom");
		menuPanel = new JPanel();
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
		
		bottomBar();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mazePanel, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.requestFocus();
		
		//timer for points
		long start = System.currentTimeMillis();
		while (playing) {
			
			if (!input.isEmpty()) {
				
				long end = System.currentTimeMillis();
				double duration = (end - start)/1000.0;
				DecimalFormat df = new DecimalFormat("#.00");
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
					// r.renderAll();
					updatePlayer(player,oldX, oldY);
					input = "NO_MOVE";
				}
				
				//multiplayer options
				if(mazeOptions.isHasMultiplayer()){
					if (input.equals("UP2") || input.equals("DOWN2")
							|| input.equals("LEFT2") || input.equals("RIGHT2")
							|| input.equals("RESET")) {
						if (resetGame) {
							input = "RESET";
							resetGame = false;
						}
						int oldX = player2.getX();
						int oldY = player2.getY();
						player2.updatePlayer(maze, input);
						// r.renderAll();
						updatePlayer(player2,oldX, oldY);
						input = "NO_MOVE";
					}
				}
			}
			// maze.printMaze(player.getX(),player.getY());
			updateTreasure(player, player2, maze);
			if (checkGame(player, player2, maze)) {
				break;
			}
		}
		
		if (playing) {
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
			
			
			scores.add(winnerScore + " " + playerName);
			
			System.out.println("You Win!! You took " + duration + " seconds\n"
								+ "You score " + ((player.getScore() * 10) - duration) + 
								" points. (10 points for every treasure minus the time taken)");
		}
		frame.dispose();

	}

	public void updateTreasure(Player player,Player player2, Maze maze) {
		System.out.println(player.getX() + " " + player.getY());
		if(maze.isTreasure(player.getX(),player.getY())){
			System.out.println("Treasure");
			maze.setTreasure(player.getX(),player.getY(),false);
			player.setScore(player.getScore() + 1);
		}
		
		//multiplayer options
		if(mazeOptions.isHasMultiplayer()){
			if(maze.isTreasure(player2.getX(),player2.getY())){
				System.out.println("Treasure");
				maze.setTreasure(player2.getX(),player2.getY(),false);
				player2.setScore(player2.getScore() + 1);
			}
		}
	}
	
	public boolean checkGame(Player player,Player player2, Maze maze) {
		MazeNode start = maze.getStart();
		MazeNode end = maze.getEnd();
		if (player.getX() == end.getX() && player.getY() == end.getY()) {
			winner = 1;
			return true;
		}
		
		//multiplayer options
		if(mazeOptions.isHasMultiplayer()){
			if (player2.getX() == start.getX() && player2.getY() == start.getY()) {
				winner = 2;
				return true;
			}
		}
		return false;
	}

	public void topMenu() {

		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
				frame = new JFrame("Maze of Doom");
				
				checkGameOptions(frame);
				
				if(maze == null) System.out.println("Maze must be initialized before player");
				player = new Player("Jack", 1, 1,0);
				
				//multiplayer options
				if(mazeOptions.isHasMultiplayer())
					player2 = new Player("Jack2", maze.getHeight()-2, maze.getWidth()-2,1);
				
				frame.add(menuPanel, BorderLayout.NORTH);
				mazePanel = new MazePanel(maze.getTiles(), 1, 1, player,player2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(mazePanel, BorderLayout.CENTER);
				frame.addKeyListener(inputReceiver);
				menuPanel.addKeyListener(inputReceiver);
				frame.setVisible(true);
				frame.requestFocus();
			}
		});

		JButton resetPlayerButton = new JButton("Restart");
		resetPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
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

		menuPanel.add(newMazeButton);
		menuPanel.add(resetPlayerButton);

	}
	
	public void bottomBar () {
		
		bottomPanel = new JPanel (new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		scoreText = new JTextArea();
		c.gridx = 0;
		c.gridy = 0;
		bottomPanel.add(scoreText, c);
		
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
	
	public void updatePlayer(Player p, int oldX, int oldY) {
		
		mazePanel.moveSquare(p,oldX, oldY);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		
	}

}
