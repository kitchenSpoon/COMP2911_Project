
/**
 * Used for storing and passing game options around.
 * @author User_Lian
 *
 */
public class MazeGameOptions {

	private int difficulty;
	private boolean hasAI;
	private boolean hasMultiplayer;
	private boolean hasTreasure;
	private boolean hasSound;
	
	public MazeGameOptions(){
		difficulty = 0;
		hasTreasure = false;
		hasMultiplayer = false;
		hasSound = true;
	}
	
	/**
	 * Gets difficulty.
	 * @return Difficulty level
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * Sets difficulty.
	 * @param difficulty difficulty to be set
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Gets whether AI is enabled
	 * @return
	 */
	public boolean isHasAI() {
		return hasAI;
	}

	/**
	 * Sets AI enabled/disabled
	 * @param hasAI
	 */
	public void setHasAI(boolean hasAI) {
		this.hasAI = hasAI;
	}

	/**
	 * Gets whether multiplayer mode is enabled.
	 * @return
	 */
	public boolean isHasMultiplayer() {
		return hasMultiplayer;
	}
	
	/**
	 * Set multiplayer mode on/off
	 * @param hasMultiplayer
	 */
	public void setHasMultiplayer(boolean hasMultiplayer) {
		this.hasMultiplayer = hasMultiplayer;
	}

	/**
	 * Gets whether treasure mode is enabled.
	 * @return
	 */
	public boolean isHasTreasure() {
		return hasTreasure;
	}

	/**
	 * Sets treasure mode on/off
	 * @param hasTreasure
	 */
	public void setHasTreasure(boolean hasTreasure) {
		this.hasTreasure = hasTreasure;
	}
}
