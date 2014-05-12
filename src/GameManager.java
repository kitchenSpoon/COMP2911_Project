import java.util.ArrayList;


public class GameManager {
	public static void main(String[] args){
		gameLoop();
	}
	
	public static void gameLoop(){
		Maze maze = new Maze(17,17);
		Player player = new Player("Jack",1,1);
		Renderer r = new Renderer();
		
		r.renderAll();
	}
}
