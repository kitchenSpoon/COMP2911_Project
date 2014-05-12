import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Renderer {

	public final static String IMAGE_PATH = "./images/";
	JPanel mazePanel;
	GridBagConstraints c;
	public Renderer() {
		mazePanel = new JPanel();
		mazePanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		mazePanel.setVisible(true);
		c.fill = GridBagConstraints.BOTH;
	}
	
	public void render(Displayable o) {
		c.fill = GridBagConstraints.BOTH;
		c.gridx = o.getX();
		c.gridy = o.getY();
		ImageIcon image = new ImageIcon(IMAGE_PATH + o.getImagePath());
		JLabel label = new JLabel(image);
		mazePanel.add(label, c);
	}
	public void generate() {
		JFrame mazeFrame = new JFrame();
		mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mazeFrame.add(mazePanel);
		mazeFrame.pack();
		mazeFrame.setVisible(true);
	}
}
