import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CustomiseMazeGame {

	public final static int EASY = 0;
	public final static int MEDIUM = 1;
	public final static int HARD = 2;
	
	private JFrame frame;
	//private int difficulty = 0; // default easy difficulty
	
	public CustomiseMazeGame () {
		
		frame = new JFrame ("Settings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 200);
		
		initialiseDifficulty();
		initialiseDone();
		
		frame.setVisible(true);
		
	}

	private void initialiseDifficulty () {
		
		JPanel difficultyPanel = new JPanel ();
		
		JLabel difficultyLabel = new JLabel ("Choose difficulty level");
		difficultyPanel.add(difficultyLabel);
		
		JButton easyDifficulty = new JButton("Easy");
		easyDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				GameManager.difficulty = EASY;
				System.out.println("Easy Game");
			}
		});
		difficultyPanel.add(easyDifficulty);
		
		JButton mediumDifficulty = new JButton("Medium");
		mediumDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				GameManager.difficulty = MEDIUM;
				System.out.println("Intermediate Game");
			}
		});
		difficultyPanel.add(mediumDifficulty);
		
		JButton hardDifficulty = new JButton("Hard");
		hardDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				GameManager.difficulty = HARD;
				System.out.println("Difficult Game");
			}
		});
		difficultyPanel.add(hardDifficulty);
	
		frame.add(difficultyPanel, BorderLayout.NORTH);
		
	}
	
	private void initialiseDone () {
		
		JPanel donePanel = new JPanel();
		JButton done = new JButton("Submit");
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				frame.dispose();
			}
		});
		donePanel.add(done);
		frame.add(donePanel, BorderLayout.SOUTH);
		
	}
	
}
