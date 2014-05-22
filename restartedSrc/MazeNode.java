
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MazeNode extends Component implements Displayable {
	int x,y;
	boolean isWall, isStart, isEnd, toRender;
	public MazeNode(int d, int e, boolean _isWall, boolean _isStart, boolean _isEnd){
		x = d;
		y = e;
		isWall = _isWall;
		isStart = _isStart;
		isEnd = _isEnd;
		toRender = true;
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

	@Override
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

	@Override
	public boolean toRender() {
		return toRender;
	}
	
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
	    g2d.setColor(Color.ORANGE);
	    g2d.fillRect(x, y, 50, 50);
	    g2d.drawRect(x, y, 50, 50);
	}
}
