import java.util.Comparator;

public class ScoreNodeComparator implements Comparator<ScoreNode> {

	public int compare (ScoreNode s1, ScoreNode s2) {
		
		return (int) -(s1.getScore() - s2.getScore());
		
	}
		
}

