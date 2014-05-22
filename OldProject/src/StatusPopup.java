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
	
}
