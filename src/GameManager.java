import java.util.ArrayList;


public class GameManager {
	public static void main(String[] args){
		gameLoop();
	}
	
	public static void gameLoop(){
		Player player = new Player("Jack",1,1);
		Maze maze = new Maze(11,11);
		Renderer r = new Renderer();
		Renderer2 r2 = new Renderer2(maze.getTiles());
		
		r.renderAll();
		
		//r.generate();
		// Debugging InputReceiver
		while (true) {
			//System.out.println(r.getInput());	
			
			String input = r.getInput();
			if(input.equals("UP") || 
				input.equals("DOWN") ||
				input.equals("LEFT") ||
				input.equals("RIGHT")){
				player.updatePlayer(maze,input);
					r.renderAll();
				r2.updatePlayer(player.getX(), player.getY());
			}
			//maze.printMaze(player.getX(),player.getY());
			if(checkGame(player,maze)){
				break;
			}
		}
		System.out.println("You Win!!");
		
	}
	
	public static boolean checkGame(Player player, Maze maze){
		MazeNode end = maze.getEnd();
		if(player.getX() == end.getX() &&
			player.getY() == end.getY()){
			return true;
		}
		return false;
	
	}
	
	
}
