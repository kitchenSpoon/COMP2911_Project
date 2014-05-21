
public class MazeNode implements Displayable {
	int x,y;
	boolean isWall, isStart, isEnd, toRender;
	public MazeNode(int _x, int _y, boolean _isWall, boolean _isStart, boolean _isEnd){
		x = _x;
		y = _y;
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
}
