import java.util.ArrayList;

import javax.swing.JFrame;


public class Renderer2 {

	private MazePanel mazePanel;
	private JFrame frame;
	public Renderer2(ArrayList<ArrayList<MazeNode>> mazeTiles) {
		mazePanel = new MazePanel(mazeTiles, 1, 1);
		frame = new JFrame("Maze of Doom");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mazePanel);
		frame.setSize(250, 250);
		frame.setVisible(true);
	}

	public void updatePlayer(int x, int y) {
		mazePanel.moveSquare(x, y);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}
}
