import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Renderer {

	public final static String IMAGE_PATH = "../images/";
	static JPanel mazePanel;
	static GridBagConstraints c;
	public static ArrayList<Displayable> toBeRendered = new ArrayList<Displayable>();
	
	private static InputReceiver InputReceiver = new InputReceiver();
	private static String input;
	
	public Renderer() {
		mazePanel = new JPanel();
		mazePanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		mazePanel.setVisible(true);
		c.fill = GridBagConstraints.BOTH;		
	}
	
	public static void addToRenderer(Displayable object){
		toBeRendered.add(object);
	}
	
	public static void removeFromRenderer(Displayable object){
		toBeRendered.remove(object);
	}
	
	public void renderAll(){
		for(Displayable d : toBeRendered){
			if(d.toRender())
				render(d);
		}
		generate();
	}
	
	public static void render(Displayable o) {
		c.fill = GridBagConstraints.BOTH;
		c.gridx = o.getX();
		c.gridy = o.getY();
		ImageIcon image = new ImageIcon(IMAGE_PATH + o.getImagePath());
		JLabel label = new JLabel(image);
		mazePanel.add(label, c);
	}
	
	public static void generate() {
		JFrame mazeFrame = new JFrame();
		mazeFrame.addKeyListener(InputReceiver);
		mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mazeFrame.add(mazePanel);
		mazeFrame.pack();
		mazeFrame.setVisible(true);
	}
	
	public String getInput() {
		return InputReceiver.input();
	}
	
}
