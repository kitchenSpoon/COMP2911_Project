import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Node of the maze.
 * @author User_Lian
 *
 */
public class MazeNode extends Component {
	int x,y,height,width;
	boolean isWall, isTreasure, isStart, isEnd, toRender;
	
	public MazeNode(int _x, int _y, boolean _isWall, boolean _isStart, boolean _isEnd, int _height, int _width){
		x = _x;
		y = _y;
		isWall = _isWall;
		isStart = _isStart;
		isEnd = _isEnd;
		toRender = true;
		height = _height;
		width = _width;
	}
	
	/**
	 * Paint this image on the screen
	 */
	public void paint(Graphics g) {

		if (isWall){
			g.drawImage(MazeGameManager.mazeImages.getImage("WALL"), x*width, y*height,width,height, null);
		} else if (isStart)
			g.drawImage(MazeGameManager.mazeImages.getImage("START"), x*width, y*height,width,height, null);
		else if (isEnd)
			g.drawImage(MazeGameManager.mazeImages.getImage("END"), x*width, y*height,width,height, null);
		else if (isTreasure)
			g.drawImage(MazeGameManager.mazeImages.getImage("COIN"), x*width, y*height,width,height, null);
		else 
			g.drawImage(MazeGameManager.mazeImages.getImage("PATH"), x*width, y*height,width,height, null);
	}
	
	/**
	 * Return x posititon
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Set x position
	 * @param _x
	 */
	public void setX(int _x){
		x = _x;
	}
	
	/**
	 * Get y position
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Set y position
	 * @param _y
	 */
	public void setY(int _y){
		y = _y;
	}
	
	/**
	 * Is this node a wall?
	 * @return
	 */
	public boolean isWall(){
		return isWall;
	}
	
	/**
	 * Set this node as a wall/or not
	 * @param _isWall
	 */
	public void setIsWall(boolean _isWall){
		isWall = _isWall;
	}
	
	/**
	 * Am I the starting position?
	 * @return
	 */
	public boolean isStart() {
		return isStart;
	}
	
	/**
	 * Am I the ending position?
	 * @return
	 */
	public boolean isEnd() {
		return isEnd;
	}
	
	/**
	 * Do I have a treasure?
	 * @return
	 */
	public boolean isTreasure(){
		return isTreasure;
	}
	
	/**
	 * Place a treasure on me.
	 * @param _isTreasure
	 */
	public void setTreasure(boolean _isTreasure){
		isTreasure = _isTreasure;
	}
}
