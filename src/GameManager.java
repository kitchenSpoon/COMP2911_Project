import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameManager {
	
	static MazeGameManager mazeGame;
	static JFrame frame = new JFrame ("Maze of Doom");
	static JPanel menuPanel = new JPanel();
	public static void main(String[] args){
		
		
		menu(menuPanel);
		mazeGame = new MazeGameManager(frame);
		mazeGame.startGame();
	}
public static void menu(JPanel menuPanel) {
		
		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.addActionListener(new 
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					mazeGame = new MazeGameManager(frame);
					mazeGame.startGame();
				}			
			}
		);
		
		menuPanel.add(newMazeButton);
		
	}
}
