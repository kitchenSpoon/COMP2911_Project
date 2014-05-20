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
	private MazeSquare player;
	private ArrayList<MazeSquare> mazeSquares;	
	public MazePanel(ArrayList<ArrayList<MazeNode>> mazeNodes, int xStart, int yStart) {
		setBorder(BorderFactory.createLineBorder(Color.black));

		mazeSquares = new ArrayList<MazeSquare>();
		player = new MazeSquare(xStart*20, yStart*20, 20, 20, PLAYER);
		for (ArrayList<MazeNode> list : mazeNodes) {
			for (MazeNode mn : list) {
				if (mn.isEnd) {
					mazeSquares.add(new MazeSquare(mn.x*20, mn.y*20, 20, 20, END));
					//mazeSquares.add(new MazeSquare())
				} else if (mn.isWall) {
					mazeSquares.add(new MazeSquare(mn.x*20, mn.y*20, 20, 20, WALL));
				} else if (mn.isStart) {
					mazeSquares.add(new MazeSquare(mn.x*20, mn.y*20, 20, 20, START));
				} else {
					mazeSquares.add(new MazeSquare(mn.x*20, mn.y*20, 20, 20, PATH));
				}
			}
			
		}
		mazeSquares.add(player);

//		MazeSquare mSquare = new MazeSquare(0, 0, 20, 20);
//		mazeSquares.add(mSquare);
//		mazeSquares.add(new MazeSquare(20, 0, 20, 20));
		
		
		
	}
	
	 public void moveSquare(int x, int y){

	        // Current square state, stored as final variables 
	        // to avoid repeat invocations of the same methods.
	        int currentX = player.x;
	        int currentY = player.y;
	        
	        int offset = 1;

	        if ((currentX!=x) || (currentY!=y)) {

	            // The square is moving, repaint background 
	            // over the old square location. 
	            repaint(currentX*20,currentY*20,20+ offset,20+offset);

	            // Update coordinates.
	            player.x = x*20;
	            player.y = y*20;

	            // Repaint the square at the new location.
	            repaint(player.x, player.y, 20+offset, 20+offset);
	            
	        }
	    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawString("This is my custom Panel!",50,20);
		for (MazeSquare ms : mazeSquares) {
			ms.paintSquare(g);
		}
	}
	
}
