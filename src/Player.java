
public class Player implements Displayable {

	private int x;
	private int y;
	private String name;
	public Player(String n, int xStart, int yStart) {
		x = xStart;
		y = yStart;
		name = n;
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
		// TODO Auto-generated method stub
		return "occupiedSquare_10.jpg";
	}
	

	
}
