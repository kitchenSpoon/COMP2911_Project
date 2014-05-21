import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameManager {
	
	//static MazeGameManager mazeGame;
	static JFrame frame = new JFrame ("Maze of Doom");
	static JFrame gframe = new JFrame ("Maze of Doom");
	static JPanel menuPanel = new JPanel();
	static MazeGameManager mgm1;
	
	public static void main(String[] args){
		

		frame = new JFrame("Maze of Doom");
		frame.setSize(250, 250);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//gframe = new JFrame("Game");
		
		
		menu();
		run();
	}
public static void menu() {
		
		JButton newMazeButton = new JButton("New Maze");
		newMazeButton.addActionListener(new 
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					mgm1 = new MazeGameManager();
					gframe = new JFrame("Game");
					gframe.add(mgm1);	
					//gframe.validate();
					//gframe.pack();
					gframe.setSize(350, 350);
					//gframe.setSize(300, 300);
					gframe.setVisible(true);
					gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					System.out.println("yay");
					//gframe.getContentPane().validate();
					//gframe.getContentPane().repaint();
					//frame.dispose();
				}			
			}
		);
		
		menuPanel.add(newMazeButton);
		frame.add(menuPanel, BorderLayout.NORTH);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
}


	public static void run(){
		while(true){
			System.out.println("running");
			if(mgm1 != null){
				System.out.println("mgm1");
				mgm1.loop();
			}
		}
	}
}
