
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
	
	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public boolean isHasAI() {
		return hasAI;
	}

	public void setHasAI(boolean hasAI) {
		this.hasAI = hasAI;
	}

	public boolean isHasMultiplayer() {
		return hasMultiplayer;
	}

	public void setHasMultiplayer(boolean hasMultiplayer) {
		this.hasMultiplayer = hasMultiplayer;
	}

	public boolean isHasTreasure() {
		return hasTreasure;
	}

	public void setHasTreasure(boolean hasTreasure) {
		this.hasTreasure = hasTreasure;
	}
	
	
}
