import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class StatusPopup {

	private JFrame frame;
	public final static int WIN = 1;
	
	public StatusPopup(JFrame frame) {
		this.frame = frame;
	}
	public void updateStatus (int status) {
		if (status == WIN) {
			winPopup();
		}
	}
	
	private void winPopup () {
		JOptionPane.showMessageDialog(frame, "You Win!", "Congratulations", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void winPopupScore (double duration) {
		JOptionPane.showMessageDialog(frame, "You Win!, you took " + duration + " seconds", "Congratulations", JOptionPane.PLAIN_MESSAGE);
	}
	
	public String winPopupCustom (String text) {
		//JOptionPane.showMessageDialog(frame, text, "Congratulations", JOptionPane.PLAIN_MESSAGE);
		String name = JOptionPane.showInputDialog (frame, text + "\n\nEnter your name for the leaderboard:", "Congratulations", JOptionPane.PLAIN_MESSAGE); 
		System.out.println("Name: " + name);
		return name;
	}
	
	
}
