
public class MazeNode {
	int x,y;
	boolean isWall;
	public MazeNode(int _x, int _y, boolean _isWall){
		x = _x;
		y = _y;
		isWall = _isWall;
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
}
