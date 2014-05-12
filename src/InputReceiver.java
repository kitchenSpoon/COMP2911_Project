import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputReceiver implements KeyListener {

	private String input = "NO_MOVE";
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_KP_DOWN || key == KeyEvent.VK_DOWN) {
			input = "DOWN";
		}
		else if (key == KeyEvent.VK_KP_UP || key == KeyEvent.VK_UP) {
			input = "UP";
		}
		else if (key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_LEFT) {
			input = "LEFT";
		}
		else if (key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_RIGHT) {
			input = "RIGHT";
		}
		else if (key == KeyEvent.VK_ESCAPE) {
			input = "ESCAPE";
		}		
			
	}

	public void keyReleased(KeyEvent e) {
		input = "NO_MOVE";
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public String input () {
	
		return input;
		
	}
	
}
