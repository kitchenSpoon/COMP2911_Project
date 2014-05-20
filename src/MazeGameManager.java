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
	static Renderer r;
	static Renderer2 r2;
	static StatusPopup popup;
	static String input;
	
	public static void main(String[] args){
		gameLoop();
	}
	
	public static void gameLoop(){
	
		frame = new JFrame ("Maze of Doom");
		menuPanel = new JPanel();
		menu();
		player = new Player ("Jack", 1, 1);
		maze = new Maze(11,11);
		r = new Renderer();
		popup = new StatusPopup();
		frame.add(menuPanel, BorderLayout.NORTH);	
		
		r2 = new Renderer2(maze.getTiles(), frame);
		r.renderAll();
		
		//r.generate();
		// Debugging InputReceiver
		while (true) {
			//System.out.println(r.getInput());	
			
			input = r.getInput();
			if(input.equals("UP") || 
				input.equals("DOWN") ||
				input.equals("LEFT") ||
				input.equals("RIGHT")){
				player.updatePlayer(maze,input);
					r.renderAll();
				r2.updatePlayer(player.getX(), player.getY());
			}
			//maze.printMaze(player.getX(),player.getY());
			if(checkGame(player,maze)){
				break;
			}
		}
		popup.updateStatus(WIN);
		System.out.println("You Win!!");
		
	}
	
	public static boolean checkGame(Player player, Maze maze){
		MazeNode end = maze.getEnd();
		if(player.getX() == end.getX() &&
			player.getY() == end.getY()){
			return true;
		}
		return false;
	
	}
	
	public static void menu() {
		
		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.addActionListener(new 
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
				
				}			
			}
		);
		
		JButton resetPlayerButton = new JButton("Reset Game");
		resetPlayerButton.addActionListener(new 
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					input = "RESET";
					player.updatePlayer(maze, input);
					r2.updatePlayer(player.getX(), player.getY());
				}			
			}
		);
		
		menuPanel.add(newMazeButton);
		menuPanel.add(resetPlayerButton);
		
	}
	
}
