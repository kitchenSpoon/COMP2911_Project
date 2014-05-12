import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Maze {
	public final static String IMAGE_PATH = "./images/";
	ArrayList<ArrayList<MazeNode>> tiles;
	int height;
	int width;
	MazeNode start;
	MazeNode end;
	MazeNode currentPosition;
	public Maze(int _height, int _width){
		height = _height;
		width = _width;
		tiles = new ArrayList<ArrayList<MazeNode>>();
		initTiles();
		start = tiles.get(1).get(1);
		end = tiles.get(height-2).get(width-2);
		currentPosition = start;
		
		generateMaze();
		printMaze();
		//displayMaze();
		addMazeToRenderer();
	}
	
	public ArrayList<ArrayList<MazeNode>> getTiles() {
		return tiles;
	}

	/**
	 * Start maze filled with walls
	 */
	public void initTiles(){
		for(int i = 0; i < height; i++){
			tiles.add(new ArrayList<MazeNode>());
			for(int j = 0; j < width; j++){
				if (i == 1 && j == 1) {
					tiles.get(i).add(new MazeNode(i,j,true, true, false));
				} else if (i == height - 2 && j == width - 2) {
					tiles.get(i).add(new MazeNode(i,j,true, false, true));
				} else {
					tiles.get(i).add(new MazeNode(i,j,true, false, false));
				}
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
		
		toGoBack.add(tiles.get(1).get(1));
		parent.add(tiles.get(1).get(1));
		
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
	
	public boolean isWall(int x, int y){
		return isWall(x,y);
	}
	
	/**
	 * Display the maze
	 */
	
	public void displayMaze () {
		
		JPanel mazePanel = new JPanel();
		mazePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		mazePanel.setVisible(true);
		c.fill = GridBagConstraints.BOTH;
		
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if (currentPosition.x == j && currentPosition.y == i && tiles.get(i).get(j).isWall() == false) {
					c.gridx = j;
					c.gridy = i;
					ImageIcon occupiedImage = new ImageIcon(IMAGE_PATH
							+ "occupiedSquare_10.jpg");
					JLabel occupiedLabel = new JLabel(occupiedImage);
					mazePanel.add(occupiedLabel, c);
					continue;
				}
				if(tiles.get(i).get(j) == start) {
					c.gridx = j;
					c.gridy = i;
					ImageIcon startImage = new ImageIcon(IMAGE_PATH
							+ "Start_10.jpg");
					JLabel startLabel = new JLabel(startImage);
					mazePanel.add(startLabel, c);
					
				}
				else if(tiles.get(i).get(j) == end) {
					c.gridx = j;
					c.gridy = i;
					ImageIcon endImage = new ImageIcon(IMAGE_PATH + "End_10.jpg");
					JLabel endLabel = new JLabel(endImage);
					mazePanel.add(endLabel, c);
				}
				else if(tiles.get(i).get(j).isWall() == true) {
					c.gridx = j;
					c.gridy = i;
					ImageIcon wallImage = new ImageIcon(IMAGE_PATH + "Wall_10.jpg");
					JLabel wallLabel = new JLabel(wallImage);
					mazePanel.add(wallLabel, c);
				}
				else {
					c.gridx = j;
					c.gridy = i;
					ImageIcon pathImage = new ImageIcon(IMAGE_PATH + "Path_10.jpg");
					JLabel pathLabel = new JLabel(pathImage);
					mazePanel.add(pathLabel, c);
				}
			}
		}
//		
//		JFrame mazeFrame = new JFrame();
//		mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		mazeFrame.add(mazePanel);
//		mazeFrame.pack();
//		mazeFrame.setVisible(true);
		
	}
	
	private void addMazeToRenderer(){
		for (ArrayList<MazeNode> list : tiles) {
			for (MazeNode mn : list) {
				Renderer.addToRenderer(mn);
			}
		}
	}
	
	/**
	 * For debugging
	 */
	public void printMaze(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				char type = '.';
				if(tiles.get(i).get(j) == start)
					type = 'S';
				else if(tiles.get(i).get(j) == end)
					type = 'E';	
				else if(tiles.get(i).get(j).isWall() == true)
					type = '#';
				
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
