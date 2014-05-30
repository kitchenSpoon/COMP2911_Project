import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MazePanel extends JPanel {

	final int WALL = 0;
	final int PATH = 1;
	final int START = 2;
	final int END = 3;
	final int PLAYER = 4;
	// private MazeSquare player;
	private Player player;
	private Player player2;
	// private ArrayList<MazeSquare> mazeSquares;
	MazeNode[][] mazeNodes;

	public MazePanel(MazeNode[][] mazeNodesIn, int xStart, int yStart, Player p, Player p2) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		player = p;
		player2 = p2;
		// mazeSquares = new ArrayList<MazeSquare>();
		// player = new MazeSquare(xStart*20, yStart*20, 20, 20, PLAYER);
		mazeNodes = mazeNodesIn;
		// for (int i = 0; i < mazeNodes.length; i++) {
		// for (MazeNode mn : mazeNodes[i]) {
		// if (mn.isEnd) {
		// mazeSquares.add(new MazeSquare(mn.x*20, mn.y*20, 20, 20, END));
		// //mazeSquares.add(new MazeSquare())
		// } else if (mn.isWall) {
		// mazeSquares.add(new MazeSquare(mn.x*20, mn.y*20, 20, 20, WALL));
		// } else if (mn.isStart) {
		// mazeSquares.add(new MazeSquare(mn.x*20, mn.y*20, 20, 20, START));
		// } else {
		// mazeSquares.add(new MazeSquare(mn.x*20, mn.y*20, 20, 20, PATH));
		// }
		// }

		// }

		// MazeSquare mSquare = new MazeSquare(0, 0, 20, 20);
		// mazeSquares.add(mSquare);
		// mazeSquares.add(new MazeSquare(20, 0, 20, 20));

	}

	public void moveSquare(Player p, double oldX, double oldY) {

		// Current square state, stored as final variables
		// to avoid repeat invocations of the same methods.
		int currentX = p.getX();
		int currentY = p.getY();

		int offset = 1;
		if ((currentX != oldX) || (currentY != oldY)) {
			// The square is moving, repaint background
			// over the old square location.

				repaint((int) oldX * 20, (int) oldY * 20, 20 + offset, 20 + offset);

				// Repaint the square at the new location.
				repaint((int)currentX * 20, (int)currentY * 20, 20 + offset, 20 + offset);
				
			
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (MazeNode[] row : mazeNodes) {
			for (MazeNode mn : row) {
				mn.paint(g);
			}
		}
		if(player != null)
			player.paint(g);
		
		if(player2 != null)
			player2.paint(g);
	}
}
