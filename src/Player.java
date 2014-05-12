
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
	

	
}
