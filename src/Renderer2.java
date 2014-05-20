import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Renderer2 {

	private MazePanel mazePanel;
	private JFrame frame;
	private JPanel menuPanel;
	
	public Renderer2(ArrayList<ArrayList<MazeNode>> mazeTiles) {
		mazePanel = new MazePanel(mazeTiles, 1, 1);
		menuPanel = new JPanel();
		menu();
		frame = new JFrame("Maze of Doom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mazePanel, BorderLayout.CENTER);
		frame.add(menuPanel, BorderLayout.NORTH);
		frame.setSize(250, 300);
		frame.setVisible(true);
	}

	public void updatePlayer(int x, int y) {
		mazePanel.moveSquare(x, y);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}
	
	public void menu() {
		
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
					//mazePanel.repaint();
				}			
			}
		);
		
		menuPanel.add(newMazeButton);
		menuPanel.add(resetPlayerButton);
		
	}
	
	
}
