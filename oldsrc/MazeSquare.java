import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MazeSquare extends Rectangle {

	final int WALL = 0;
	final int PATH = 1;
	final int START = 2;
	final int END = 3;
	final int PLAYER = 4;
	private int type;

	public MazeSquare(int xIn, int yIn, int widthIn, int heightIn, int typeIn) {
		super(xIn, yIn, widthIn, heightIn);
		type = typeIn;
	}

	public void paintSquare(Graphics g) {

		switch(type) {
		case WALL:
			g.setColor(Color.BLACK);
			break;
		case PATH:
			g.setColor(Color.WHITE);
			break;
		case START:
			g.setColor(Color.GREEN);
			break;
		case END:
			g.setColor(Color.RED);
			break;
		case PLAYER:
			g.setColor(Color.BLUE);
		default:
			break;
		}
		g.fillRect(x, y, width, height);
		
		g.drawRect(x, y, width, height);
	}

}
