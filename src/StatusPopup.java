import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A popup that informs the player when they have won the game, and takes in the
 * player's name for the score board
 */
public class StatusPopup {

	private JFrame frame;
	
	/**
	 * Set up the frame
	 */
	public StatusPopup(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Displays the popup and asks for the player's name
	 * @param text a String to customise the win message displayed
	 * @return the String entered by the player when prompted for a name
	 */
	public String winPopupCustom (String text) {
		//JOptionPane.showMessageDialog(frame, text, "Congratulations", JOptionPane.PLAIN_MESSAGE);
		String name = JOptionPane.showInputDialog (frame, text + "\n\nEnter your name for the leaderboard:", "Congratulations", JOptionPane.PLAIN_MESSAGE); 
		System.out.println("Name: " + name);
		return name;
	}
	
	
}
