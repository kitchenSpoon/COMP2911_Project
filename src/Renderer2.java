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
	
	public Renderer2(ArrayList<ArrayList<MazeNode>> mazeTiles, JFrame _frame) {
		frame = _frame;
		mazePanel = new MazePanel(mazeTiles, 1, 1);
		//frame = new JFrame("Maze of Doom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mazePanel, BorderLayout.CENTER);
		
		frame.setSize(250, 300);
		frame.setVisible(true);
	}

	public void updatePlayer(int x, int y) {
		mazePanel.moveSquare(x, y);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}
	
}
