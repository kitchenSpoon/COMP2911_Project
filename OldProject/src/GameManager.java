import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameManager {

	private JFrame mainFrame;
	private MazeGameManager mazeGame;
	private boolean newGameClicked = false;

	public GameManager() {
		mainFrame = new JFrame("Maze of Doom: Start Screen");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(300, 400);

		JPanel mainPanel = new JPanel();
		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				newGameClicked = true;
			}
		});
		mainPanel.add(newMazeButton);
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
	
		mainPanel.add(exitButton);
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);
		while (true) {
			gameManagerLoop();
		}
		
		
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
		mazeGame = new MazeGameManager();
		mazeGame.startGame();
	}
}
