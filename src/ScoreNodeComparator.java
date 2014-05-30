import java.util.Comparator;

/**
 * A Comparator for sorting the Scoreboard
 */
public class ScoreNodeComparator implements Comparator<ScoreNode> {

	/**
	 * Compare the two ScoreNodes. A larger score ranks better. 
	 */
	public int compare (ScoreNode s1, ScoreNode s2) {
		
		return (int) -(s1.getScore() - s2.getScore());
		
	}
		
}

