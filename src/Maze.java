import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {
	ArrayList<ArrayList<MazeNode>> tiles;
	int height;
	int width;
	public Maze(int _height, int _width){
		height = _height;
		width = _width;
		tiles = new ArrayList<ArrayList<MazeNode>>();
		initTiles();
		generateMaze();
		printMaze();
	}
	
	/**
	 * Start maze filled with walls
	 */
	public void initTiles(){
		for(int i = 0; i < height; i++){
			tiles.add(new ArrayList<MazeNode>());
			for(int j = 0; j < width; j++){
				tiles.get(i).add(new MazeNode(i,j,true));
			}
		}
	}
	
	/**
	 * Carve walkable passage in the maze
	 * Maybe we could use a maze generator class
	 */
	public void generateMaze(){
		ArrayList<MazeNode> visited = new ArrayList<MazeNode>();
		Stack<MazeNode> toGoBack = new Stack<MazeNode>();
		Stack<MazeNode> parent = new Stack<MazeNode>();
		
		MazeNode curr = null;
		MazeNode prev = null;
		
		toGoBack.add(tiles.get(0).get(0));
		parent.add(tiles.get(0).get(0));
		
		while(!toGoBack.isEmpty()){
			curr = toGoBack.pop();
			prev = parent.pop();
			
			if(visited.contains(curr)) continue;
			visited.add(curr);
			
			//Clear the wall at curr node as well as
			// the wall between the prev node and curr node
			curr.setIsWall(false);
			if(prev != null){
				if(curr.getX() != prev.getX()){
					tiles.get((curr.getX() + prev.getX())/2).get(curr.getY()).setIsWall(false);
				} else {
					tiles.get(curr.getX()).get((curr.getY() + prev.getY())/2).setIsWall(false);
				}
			}
			
			//Neighbours
			//there are better ways to do this?
			ArrayList<MazeNode> randNeighbours = new ArrayList<MazeNode>();
			
			if(curr.getX()-2 >= 0 && !visited.contains(tiles.get(curr.getX()-2).get(curr.getY()))){
				randNeighbours.add(tiles.get(curr.getX()-2).get(curr.getY()));
			}
			if(curr.getX()+2 < height && !visited.contains(tiles.get(curr.getX()+2).get(curr.getY()))){
				randNeighbours.add(tiles.get(curr.getX()+2).get(curr.getY()));
			}
			if(curr.getY()-2 >= 0 && !visited.contains(tiles.get(curr.getX()).get(curr.getY()-2))){
				randNeighbours.add(tiles.get(curr.getX()).get(curr.getY()-2));
			}
			if(curr.getY()+2 < width && !visited.contains(tiles.get(curr.getX()).get(curr.getY()+2))){
				randNeighbours.add(tiles.get(curr.getX()).get(curr.getY()+2));
			}
			
			//permute
			//please put me into a function
			for(int i = 0; i < randNeighbours.size(); i++){
				Random random = new Random();
				int p = random.nextInt((randNeighbours.size()-1) - i + 1) + i;
				
				MazeNode temp = randNeighbours.get(i);
				randNeighbours.set(i,randNeighbours.get(p));
				randNeighbours.set(p,temp);
			}
			
			for(int i = 0; i < randNeighbours.size(); i++){
				toGoBack.add(randNeighbours.get(i));
				parent.add(curr);
			}
			
			/* Debuggin
			System.out.println(curr.getX() + ", " + curr.getY());
			printStack(toGoBack);
			printMaze();
			*/
		}
	}
	
	/**
	 * For debugging
	 */
	public void printMaze(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				int type = 0;
				if(tiles.get(i).get(j).isWall() == true)
					type = 1;
				System.out.print(type);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * For debugging
	 * @param s Stack to be printed
	 */
	public void printStack(Stack<MazeNode> s){
		for(int i = 0; i < s.size(); i++){
			System.out.print("stack " + s.get(i).getX() + " " + s.get(i).getY() + ", ");
		}
		System.out.println();
	}
}
