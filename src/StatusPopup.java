import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class StatusPopup {

	private static JFrame frame = Renderer.mazeFrame;
	public final static int WIN = 1;
	
	public void updateStatus (int status) {
		if (status == WIN) {
			winPopup();
		}
	}
	
	private static void winPopup () {
		
		JOptionPane.showMessageDialog(frame, "You Win!", "Congratulations", JOptionPane.PLAIN_MESSAGE);
		
	}
	
}
