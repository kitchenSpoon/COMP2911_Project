
public class Player implements Displayable {

	private int x;
	private int y;
	private int xStart;
	private int yStart;
	private String name;
	boolean toRender;
	public Player(String n, int _xStart, int _yStart) {
		x = _xStart;
		y = _yStart;
		xStart = _xStart;
		yStart = _yStart;
		name = n;
		toRender = true;
		Renderer.addToRenderer(this);
	}
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}
	public void setX(int _x) {
		x = _x;
	}
	
	public void setY(int _y) {
		x = _y;
	}
	@Override
	public String getImagePath() {
		return "occupiedSquare_10.jpg";
	}
	@Override
	public boolean toRender() {
		return toRender;
	}
	
	
	public void updatePlayer(Maze maze,String input){
		if(input.equals("LEFT")){
			if(!maze.isWall(x - 1, y))
				x -= 1;
		} 
		else if (input.equals("RIGHT")){
			if(!maze.isWall(x + 1, y))
				x += 1;
		}
		else if (input.equals("UP")){
			if(!maze.isWall(x, y - 1))
				y -= 1;
		}
		else if (input.equals("DOWN")){
			if(!maze.isWall(x, y + 1))
				y += 1;
		}
		else if (input.equals("RESET")) {
			x = xStart;
			y = yStart;
		}
	}
	
	
//	
//	public void updatePlayer(Maze maze,String input){
//		if(input.equals("UP")){
//			if(!maze.isWall(x - 1, y))
//				x -= 1;
//		} 
//		else if (input.equals("DOWN")){
//			if(!maze.isWall(x + 1, y))
//				x += 1;
//		}
//		else if (input.equals("LEFT")){
//			if(!maze.isWall(x, y - 1))
//				y -= 1;
//		}
//		else if (input.equals("RIGHT")){
//			if(!maze.isWall(x, y + 1))
//				y += 1;
//		}
//	}
//	
}
