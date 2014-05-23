import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeGameManager {

	public final static int WIN = 1;

	JFrame frame;
	JPanel menuPanel;
	Player player;
	Player player2;
	Maze maze;
	StatusPopup popup;
	static String input = "NO MOVE";
	InputReceiver inputReceiver;
	MazePanel mazePanel;
	boolean resetGame = false;
	MazeGameOptions mazeOptions;
	
	public MazeGameManager(MazeGameOptions _mazeOptions){
		mazeOptions = _mazeOptions;
	}
	
	public void startGame() {
		gameLoop();
	}
	public void checkDifficulty(JFrame frame){
		if (mazeOptions.getDifficulty() == 0) {
			maze = new Maze(11, 11);
			frame.setSize(220, 280);
		}
		else if (mazeOptions.getDifficulty() == 1) {
			maze = new Maze(21, 21);
			frame.setSize(420, 480);
		}
		else {
			maze = new Maze(31, 31);
			frame.setSize(620, 680);
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
		menu();
		
		checkGameOptions(frame);
		
		if(maze == null) System.out.println("Maze must be initialized before player");
		player = new Player("Jack", 1, 1,0);
		player2 = new Player("Jack2", maze.getHeight()-2, maze.getWidth()-2,1);
		
		popup = new StatusPopup(frame);
		frame.add(menuPanel, BorderLayout.NORTH);
		frame.addKeyListener(inputReceiver);
		menuPanel.addKeyListener(inputReceiver);
		mazePanel = new MazePanel(maze.getTiles(), 1, 1, player, player2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mazePanel, BorderLayout.CENTER);
		//frame.setSize(250, 300);
		//frame.pack();
		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.requestFocus();
		
		//timer for points
		long start = System.currentTimeMillis();
	
		while (true) {
			
			if (!input.isEmpty()) {
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
			// maze.printMaze(player.getX(),player.getY());
			updateTreasure(player, player2, maze);
			if (checkGame(player, player2, maze)) {
				break;
			}
		}
		long end = System.currentTimeMillis();
		double duration = (end - start)/1000.0;
		popup.winPopupCustom("You Win!! You took " + duration + "\n"
				+ "You score " + ((player.getScore() * 10) - duration) + " points. (10 points for every treasure minus the time taken)");
		System.out.println("You Win!! You took " + duration + "\n"
							+ "You score " + ((player.getScore() * 10) - duration) + " points. (10 points for every treasure minus the time taken)");
		frame.dispose();

	}

	public void updateTreasure(Player player,Player player2, Maze maze) {
		System.out.println(player.getX() + " " + player.getY());
		if(maze.isTreasure(player.getX(),player.getY())){
			System.out.println("Treasure");
			maze.setTreasure(player.getX(),player.getY(),false);
			player.setScore(player.getScore() + 1);
		}
		if(maze.isTreasure(player2.getX(),player2.getY())){
			System.out.println("Treasure");
			maze.setTreasure(player2.getX(),player2.getY(),false);
			player2.setScore(player2.getScore() + 1);
		}
	}
	
	public boolean checkGame(Player player,Player player2, Maze maze) {
		MazeNode start = maze.getStart();
		MazeNode end = maze.getEnd();
		if (player.getX() == end.getX() && player.getY() == end.getY()) {
			return true;
		}
		if (player2.getX() == start.getX() && player2.getY() == start.getY()) {
			return true;
		}
		return false;
	}

	public void menu() {

		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//r.disposeRenderer();
				frame.dispose();
				frame = new JFrame("Maze of Doom");
				if (GameManager.difficulty == 0) {
					maze = new Maze(11, 11);
					frame.setSize(220, 280);
				}
				else if (GameManager.difficulty == 1) {
					maze = new Maze(21, 21);
					frame.setSize(420, 480);
				}
				else {
					maze = new Maze(31, 31);
					frame.setSize(620, 680);
				}
				frame.add(menuPanel, BorderLayout.NORTH);
				player = new Player("Jack", 1, 1,0);
				player2 = new Player("Jack2", maze.getHeight()-2, maze.getWidth()-2,1);
				mazePanel = new MazePanel(maze.getTiles(), 1, 1, player,player2);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(mazePanel, BorderLayout.CENTER);
				frame.addKeyListener(inputReceiver);
				menuPanel.addKeyListener(inputReceiver);
				//frame.setSize(250, 300);
				frame.setVisible(true);
				frame.requestFocus();
				//r2 = new Renderer2(maze.getTiles(), frame);
			//	r = new Renderer();
				// r.renderAll();
			}
		});

		JButton resetPlayerButton = new JButton("Restart");
		resetPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				player.updatePlayer(maze, "RESET");
				player2.updatePlayer(maze, "RESET");
				updatePlayer(player,player.getX(), player.getY());
				updatePlayer(player2,player2.getX(), player2.getY());
				frame.requestFocus();
			}
		});

		menuPanel.add(newMazeButton);
		menuPanel.add(resetPlayerButton);

	}
	
	public void updatePlayer(Player p, int oldX, int oldY) {
		
		mazePanel.moveSquare(p,oldX, oldY);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		
	}

}
