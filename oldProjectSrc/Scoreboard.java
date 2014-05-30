import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Stores all the scores from gameplay.
 *
 */
public class Scoreboard {

	private ArrayList<ScoreNode> scores;
	private File file;
	
	/**
	 * Initialise the scoreboard. Create a file for storing scores if one has not 
	 * already been created. If one has, then load all previous scores into an
	 * ArrayList.
	 * @param fileName the file where the scores will be stored so that they can be
	 * loaded the next time the game is played
	 */
	public Scoreboard (String fileName) {
		
		scores = new ArrayList<ScoreNode>();
		
		file = new File(fileName);
		
		if (file.exists()) {
			
			 try {
				 
				Scanner s = new Scanner(new FileReader(fileName));
				
				while (s.hasNextLine()) {
					
					String str = s.nextLine();
					System.out.println(str);
					int space = str.indexOf(" ");
					double score = Double.parseDouble(str.substring(0, space - 1));
					String player = str.substring(space + 1);
					ScoreNode newNode = new ScoreNode (score, player);
					
					scores.add(newNode);
					
				}
				
				s.close();
				
			 } catch (FileNotFoundException e) {} 
		}
		else {
			try {
				file.createNewFile();
			} catch (IOException e) {}
		}
		
		
	}
	
	/**
	 * Add a new score to the Scoreboard
	 * @param n a score to be added
	 */
	public void addScore (ScoreNode n) {
		
		scores.add(n);
		
		try {
			FileWriter f = new FileWriter(file, true);
			f.write(n.toString()+"\n");
			f.close();
		} catch (IOException e) {}
		
	}
	
	/**
	 * Get a score from the Scoreboard
	 * @param scoreNumber the ranking of the score being requested
	 * @return a string containing the score and the player who achieved that score
	 */
	public String getScore (int scoreNumber) {
		
		Comparator<ScoreNode> comparator = new ScoreNodeComparator();
		Collections.sort(scores, comparator);
		
		DecimalFormat df = new DecimalFormat("#.00");
		
		ScoreNode n = scores.get(scoreNumber);
		
		return df.format(n.getScore()) + ".........." + n.getPlayer();
		
	}
	
	/**
	 * Get the size of the Scoreboard
	 * @return the number of scores in the Scoreboard
	 */
	public int size () {
		return scores.size();
	}
	
}
