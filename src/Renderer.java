import java.awt.Graphics;
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

	public final static String IMAGE_PATH = "./images/";
	static JPanel mazePanel;
	static GridBagConstraints c;
	public static ArrayList<Displayable> toBeRendered = new ArrayList<Displayable>();
	static JFrame mazeFrame;
	private static InputReceiver InputReceiver = new InputReceiver();
	private static String input;
	
	public Renderer() {
		mazePanel = new JPanel();
		mazePanel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		mazePanel.setVisible(true);
		c.fill = GridBagConstraints.BOTH;	
		mazeFrame = new JFrame();
		generate();
	}
	
	public static void addToRenderer(Displayable object){
		toBeRendered.add(object);
	}
	
	public static void removeFromRenderer(Displayable object){
		toBeRendered.remove(object);
	}
	
	public void renderAll(){
		mazePanel.removeAll();
		for(Displayable d : toBeRendered){
			if(d.toRender())
				render(d);
		}
		//generate();
	}
	
	public static void render(Displayable o) {
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = o.getX();
		c.gridy = o.getY();
		ImageIcon image = new ImageIcon(IMAGE_PATH + o.getImagePath());
		JLabel label = new JLabel(image);
		mazePanel.add(label, c);
		mazePanel.repaint();
		
	}
	
	public static void paintComponent(Graphics g) {
		
	}
	
	public static void generate() {
		
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
