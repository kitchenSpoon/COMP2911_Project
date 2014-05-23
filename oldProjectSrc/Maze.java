import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Maze {
	public final static String IMAGE_PATH = "./images/";
	MazeNode[][] tiles;
	int height;
	int width;
	MazeNode start;
	MazeNode end;
	MazeNode currentPosition;
	public Maze(int _height, int _width){
		height = _height;
		width = _width;
		tiles = new MazeNode[_height][_width];
		initTiles();
		start = tiles[1][1];
		end = tiles[height-2][width-2];
		currentPosition = start;
		generateMaze();
		printMaze();
		//displayMaze();
	}
	
	public MazeNode[][] getTiles() {
		return tiles;
	}

	public MazeNode getEnd(){
		return end;
	}
	
	/**
	 * Start maze filled with walls
	 */
	public void initTiles(){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				if (i == 1 && j == 1) {
					tiles[i][j]= new MazeNode(i,j,true, true, false);
				} else if (i == height - 2 && j == width - 2) {
					tiles[i][j] = new MazeNode(i,j,true, false, true);
				} else {
					tiles[i][j] = new MazeNode(i,j,true, false, false);
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
		
		toGoBack.add(tiles[1][1]);
		parent.add(tiles[1][1]);
		
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
					tiles[(curr.getX() + prev.getX())/2][(curr.getY())].setIsWall(false);
				} else {
					tiles[curr.getX()][(curr.getY() + prev.getY())/2].setIsWall(false);
				}
			}
			
			//Neighbours
			//there are better ways to do this?
			ArrayList<MazeNode> randNeighbours = new ArrayList<MazeNode>();
			
			if(curr.getX()-2 >= 0 && !visited.contains(tiles[curr.getX()-2][curr.getY()])){
				randNeighbours.add(tiles[curr.getX()-2][curr.getY()]);
			}
			if(curr.getX()+2 < height && !visited.contains(tiles[curr.getX()+2][curr.getY()])){
				randNeighbours.add(tiles[curr.getX()+2][curr.getY()]);
			}
			if(curr.getY()-2 >= 0 && !visited.contains(tiles[curr.getX()][curr.getY()-2])){
				randNeighbours.add(tiles[curr.getX()][curr.getY()-2]);
			}
			if(curr.getY()+2 < width && !visited.contains(tiles[curr.getX()][curr.getY()+2])){
				randNeighbours.add(tiles[curr.getX()][curr.getY()+2]);
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
		return tiles[x][y].isWall();
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
				if (currentPosition.x == j && currentPosition.y == i && tiles[i][j].isWall() == false) {
					c.gridx = j;
					c.gridy = i;
					ImageIcon occupiedImage = new ImageIcon(IMAGE_PATH
							+ "occupiedSquare_10.jpg");
					JLabel occupiedLabel = new JLabel(occupiedImage);
					mazePanel.add(occupiedLabel, c);
					continue;
				}
				if(tiles[i][j] == start) {
					c.gridx = j;
					c.gridy = i;
					ImageIcon startImage = new ImageIcon(IMAGE_PATH
							+ "Start_10.jpg");
					JLabel startLabel = new JLabel(startImage);
					mazePanel.add(startLabel, c);
					
				}
				else if(tiles[i][j] == end) {
					c.gridx = j;
					c.gridy = i;
					ImageIcon endImage = new ImageIcon(IMAGE_PATH + "End_10.jpg");
					JLabel endLabel = new JLabel(endImage);
					mazePanel.add(endLabel, c);
				}
				else if(tiles[i][j].isWall() == true) {
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
	}
	
	public void generateTreasure(){
		//maze should already be generated
		int numTreasure = (height * width)/20;
		int treasureCount = 0;
		Random random = new Random();
		while(treasureCount < numTreasure){
			int x = random.nextInt(height);
			int y = random.nextInt(width);
			System.out.println(x+" "+y);
			if(!tiles[x][y].isWall() && !tiles[x][y].isTreasure()) {
				tiles[x][y].setTreasure(true);
				treasureCount++;
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
				if(tiles[i][j] == start)
					type = 'S';
				else if(tiles[i][j] == end)
					type = 'E';	
				else if(tiles[i][j].isWall() == true)
					type = '#';
				else if(tiles[i][j].isTreasure() == true)
					type = 'G';
				
				System.out.print(type);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void printMaze(int x,int y){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				char type = '.';
				if(tiles[i][j] == start)
					type = 'S';
				else if(tiles[i][j] == end)
					type = 'E';	
				else if(tiles[i][j].isWall() == true)
					type = '#';
				if(i == x && j == y)
					type = 'P';
				
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
