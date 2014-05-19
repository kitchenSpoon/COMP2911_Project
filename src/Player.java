
public class Player implements Displayable {

	private int x;
	private int y;
	private String name;
	boolean toRender;
	public Player(String n, int xStart, int yStart) {
		x = xStart;
		y = yStart;
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
	
	public void setY(int _x) {
		x = _x;
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
		if(input.equals("UP")){
			if(!maze.isWall(x - 1, y))
				x -= 1;
		} 
		else if (input.equals("DOWN")){
			if(!maze.isWall(x + 1, y))
				x += 1;
		}
		else if (input.equals("LEFT")){
			if(!maze.isWall(x, y - 1))
				y -= 1;
		}
		else if (input.equals("RIGHT")){
			if(!maze.isWall(x, y + 1))
				y += 1;
		}
	}
	
}
