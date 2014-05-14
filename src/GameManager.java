import java.util.ArrayList;


public class GameManager {
	public static void main(String[] args){
		gameLoop();
	}
	
	public static void gameLoop(){
		Player player = new Player("Jack",1,1);
		Maze maze = new Maze(37,37);
		Renderer r = new Renderer();
		
		r.renderAll();
		//r.generate();
		// Debugging InputReceiver
		while (true) {
			System.out.println(r.getInput());	
			String input = r.getInput();
			if(input.equals("UP") || 
				input.equals("DOWN") ||
				input.equals("LEFT") ||
				input.equals("RIGHT")){
					updatePlayer(player,maze,input);
					r.renderAll();
			}
		}
		
	}
	
	public static void updatePlayer(Player player,Maze maze,String input){
		if(input.equals("UP")){
			player.setX(player.getX() - 1);
			System.out.println(player.getX());	
		} 
		else if (input.equals("DOWN")){
			player.setX(player.getX() + 1);
		}
		else if (input.equals("LEFT")){
			player.setY(player.getY() - 1);
		}
		else if (input.equals("RIGHT")){
			player.setY(player.getY() + 1);
		}
	}
}
