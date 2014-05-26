
public class ScoreNode {

	private double score;
	private String player;
	
	public ScoreNode (double _score, String _player) {
		score = _score;
		player = _player;
	}
	
	public String getPlayer () {
		return player;
	}
	
	public double getScore () {
		return score;
	}
	
}
