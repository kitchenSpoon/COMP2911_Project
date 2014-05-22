import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeGameManager {

	public final static int WIN = 1;

	static JFrame frame;
	static JPanel menuPanel;
	static Player player;
	static Maze maze;
	//static Renderer r;
	//static Renderer2 r2;
	static StatusPopup popup;
	static String input = "NO MOVE";
	private InputReceiver inputReceiver;
	private MazePanel mazePanel;
	static boolean resetGame = false;
	public void startGame() {
		gameLoop();
	}

	private void gameLoop() {

		inputReceiver = new InputReceiver();
		frame = new JFrame("Maze of Doom");
		menuPanel = new JPanel();
		menu();
		player = new Player("Jack", 1, 1);
		maze = new Maze(11, 11);
		popup = new StatusPopup(frame);
		frame.add(menuPanel, BorderLayout.NORTH);
		frame.addKeyListener(inputReceiver);
		menuPanel.addKeyListener(inputReceiver);
		mazePanel = new MazePanel(maze.getTiles(), 1, 1, player);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mazePanel, BorderLayout.CENTER);
		frame.setSize(250, 300);
		frame.setVisible(true);
		frame.requestFocus();
		//r2 = new Renderer2(maze.getTiles(), frame);
	
		while (true) {
			// System.out.println(r.getInput());

			// input = r.getInput();
			//input = inputReceiver.input();
			
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
					updatePlayer(oldX, oldY);
					input = "NO_MOVE";
				}
			}
			// maze.printMaze(player.getX(),player.getY());
			if (checkGame(player, maze)) {
				break;
			}
		}
		popup.updateStatus(WIN);
		System.out.println("You Win!!");

	}

	public static boolean checkGame(Player player, Maze maze) {
		MazeNode end = maze.getEnd();
		if (player.getX() == end.getX() && player.getY() == end.getY()) {
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
				maze = new Maze(11, 11);
				frame = new JFrame("Maze of Doom");
				frame.add(menuPanel, BorderLayout.NORTH);
				player = new Player("Jack", 1, 1);
				mazePanel = new MazePanel(maze.getTiles(), 1, 1, player);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(mazePanel, BorderLayout.CENTER);
				frame.addKeyListener(inputReceiver);
				menuPanel.addKeyListener(inputReceiver);
				frame.setSize(250, 300);
				frame.setVisible(true);
				frame.requestFocus();
				//r2 = new Renderer2(maze.getTiles(), frame);
			//	r = new Renderer();
				// r.renderAll();
			}
		});

		JButton resetPlayerButton = new JButton("Reset Game");
		resetPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				player.updatePlayer(maze, "RESET");
				updatePlayer(player.getX(), player.getY());
				frame.requestFocus();
			}
		});

		menuPanel.add(newMazeButton);
		menuPanel.add(resetPlayerButton);

	}
	
	public void updatePlayer(int oldX, int oldY) {
		
		mazePanel.moveSquare(oldX, oldY);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		
	}

}
