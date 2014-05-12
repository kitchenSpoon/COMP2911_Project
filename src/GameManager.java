import java.util.ArrayList;


public class GameManager {
	public static void main(String[] args){
		gameLoop();
	}
	
	public static void gameLoop(){
		Maze maze = new Maze(17,17);
		Renderer r = new Renderer();
		
		for (ArrayList<MazeNode> list : maze.getTiles()) {
			for (MazeNode mn : list) {
				r.render(mn);
			}
		}
		
		r.generate();
	}
}
