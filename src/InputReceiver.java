import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listens to the keyboard for player's input
 */
public class InputReceiver implements KeyListener {

	//private int counter = 0;

	/**
	 * Identify when a user has pressed a key for movement
	 */
	public void keyPressed(KeyEvent e) {
	
		//System.out.println("press " + counter);
		//counter++;
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_KP_DOWN || key == KeyEvent.VK_DOWN) {
			MazeGameManager.input = "DOWN";
		}
		else if (key == KeyEvent.VK_KP_UP || key == KeyEvent.VK_UP) {
			MazeGameManager.input = "UP";
		}
		else if (key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_LEFT) {
			MazeGameManager.input = "LEFT";
		}
		else if (key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_RIGHT) {
			MazeGameManager.input = "RIGHT";
		}
		else if (key == KeyEvent.VK_S) {
			MazeGameManager.input = "DOWN2";
		}
		else if (key == KeyEvent.VK_W) {
			MazeGameManager.input = "UP2";
		}
		else if (key == KeyEvent.VK_A) {
			MazeGameManager.input = "LEFT2";
		}
		else if (key == KeyEvent.VK_D) {
			MazeGameManager.input = "RIGHT2";
		}
		else if (key == KeyEvent.VK_ESCAPE) {
			MazeGameManager.input = "ESCAPE";
		}		
	}

	/**
	 * When a key has been released, set the input to nothing
	 */
	public void keyReleased(KeyEvent e) {
		MazeGameManager.input = "NO_MOVE";
	}

	/**
	 * No action for typed keys
	 */
	public void keyTyped(KeyEvent e) {
		
	}

	/**
	 * Get the uer's input
	 * @return the key that the user has pressed
	 */
	public String input () {
		return MazeGameManager.input;	
	}
	
}
