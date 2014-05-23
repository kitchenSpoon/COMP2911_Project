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
	
	public void winPopupCustom (String text) {
		JOptionPane.showMessageDialog(frame, text, "Congratulations", JOptionPane.PLAIN_MESSAGE);
	}
	
	
}
