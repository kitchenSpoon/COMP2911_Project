/**
 * A class to store a player's score
 *
 */
public class ScoreNode {

	private double score;
	private String player;
	
	/**
	 * Initialise the score
	 * @param _score the number of points the player received
	 * @param _player the player's name
	 */
	public ScoreNode (double _score, String _player) {
		score = _score;
		player = _player;
	}
	
	/**
	 * Get the player
	 * @return the player's name
	 */
	public String getPlayer () {
		return player;
	}
	
	/**
	 * Get the score
	 * @return the player's score
	 */
	public double getScore () {
		return score;
	}
	
	/**
	 * Convert the ScoreNode to a string 
	 */
	public String toString () {
		return score + " " + player;
	}
	
}
