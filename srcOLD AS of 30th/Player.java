import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/**
 * Player
 * @author User_Lian
 *
 */
public class Player extends Component {

	private int x;
	private int y;
	private int height;
	private int width;
	private int xStart;
	private int yStart;
	private String name;
	private double score;
	boolean toRender;
	private int colour;
	
	public Player(String n, int _xStart, int _yStart,int _colour) {
		x = _xStart;
		y = _yStart;
		xStart = _xStart;
		yStart = _yStart;
		name = n;
		toRender = true;
		score = 0;
		colour = _colour;
		height = 20;
		width = 20;
	}
	
	/**
	 * Get x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get y position
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set x position
	 * @param _x
	 */
	public void setX(int _x) {
		x = _x;
	}
	
	/**
	 * Set y position
	 * @param _y
	 */
	public void setY(int _y) {
		x = _y;
	}
	
	/**
	 * Get player's score
	 * @return
	 */
	public double getScore(){
		return score;
	}
	
	/**
	 * Set player's score
	 * @param _score
	 */
	public void setScore(double _score){
		score = _score;
	}
	
	/**
	 * Update player's position
	 * @param maze
	 * @param input
	 */
	public void updatePlayer(Maze maze,String input){
		if(input.equals("LEFT") || input.equals("LEFT2")){
			if(!maze.isWall(x - 1, y))
				x -= 1;
		} 
		else if (input.equals("RIGHT") || input.equals("RIGHT2")){
			if(!maze.isWall(x + 1, y))
				x += 1;
		}
		else if (input.equals("UP") || input.equals("UP2")){
			if(!maze.isWall(x, y - 1))
				y -= 1;
		}
		else if (input.equals("DOWN") || input.equals("DOWN2")){
			if(!maze.isWall(x, y + 1))
				y += 1;
		}
		else if (input.equals("RESET")) {
			x = xStart;
			y = yStart;
		}
	}

	/**
	 * Paint the image
	 */
	public void paint(Graphics g) {
		g.drawImage(MazeGameManager.mazeImages.getImage(name),x*20, y*20, null);
	}
}
