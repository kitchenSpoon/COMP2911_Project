import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;


public class MazeNode extends Component {
	int x,y,height,width;
	boolean isWall, isTreasure, isStart, isEnd, toRender;
	private Sprite sprite;
	public MazeNode(int _x, int _y, boolean _isWall, boolean _isStart, boolean _isEnd){
		x = _x;
		y = _y;
		isWall = _isWall;
		isStart = _isStart;
		isEnd = _isEnd;
		toRender = true;
		height = 10;
		width = 10;
		//sprite = new Sprite(spriteImage);
		
	}
	public void paint(Graphics g) {
		//sprite.draw(g, x, y);
		if (isWall)
			g.setColor(Color.BLACK);
		else if (isStart)
			g.setColor(Color.GREEN);
		else if (isEnd)
			g.setColor(Color.RED);
		else if (isTreasure)
			g.setColor(Color.YELLOW);
		else 
			g.setColor(Color.WHITE);
		
		g.fillRect(x*height, y*width, height, width);
		
		g.drawRect(x*height, y*width, height, width);
	}
	public int getX(){
		return x;
	}
	
	public void setX(int _x){
		x = _x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setY(int _y){
		y = _y;
	}
	
	public boolean isWall(){
		return isWall;
	}
	
	public void setIsWall(boolean _isWall){
		isWall = _isWall;
	}
	public boolean isStart() {
		return isStart;
	}
	public boolean isEnd() {
		return isEnd;
	}
	public boolean isTreasure(){
		return isTreasure;
	}
	public void setTreasure(boolean _isTreasure){
		isTreasure = _isTreasure;
	}

	public String getImagePath() {
		if (isWall()) {
			return "Wall_10.jpg";
		} else if (isStart()) {
			return "Start_10.jpg";
		} else if (isEnd()) {
			return "End_10.jpg";
		} else {
			return "Path_10.jpg";
		}
	}

	public boolean toRender() {
		return toRender;
	}
}
