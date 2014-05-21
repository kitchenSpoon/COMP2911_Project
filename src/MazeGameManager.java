import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MazeGameManager extends JPanel{
	int x,y;
	public MazeGameManager(){
		x = 0;
		y = 0;
	}	
	
	public void paint(Graphics g){
		Graphics g2d =(Graphics2D) g;
		g2d.fillOval(x+0,y+0,30,30);
		g2d.fillOval(x+100,y+200,30,30);
	}
	
	public void loop(){
		while(true){
			System.out.println("repainting");
			validate();
			//x++;
			//y++;
			//repaint();
			
		}
	}
}
