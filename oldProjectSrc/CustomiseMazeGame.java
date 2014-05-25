import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CustomiseMazeGame {

	public final static int EASY = 0;
	public final static int MEDIUM = 1;
	public final static int HARD = 2;
	
	private MazeGameOptions mazeOptions;
	
	private JFrame frame;
	private JPanel container ;
	
	
	//private int difficulty = 0; // default easy difficulty
	
	public CustomiseMazeGame (MazeGameOptions _mazeOptions) {
		if(mazeOptions == null) {
			System.out.println("MazeOptions is null");
		}
		mazeOptions = _mazeOptions;
		
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		frame = new JFrame ("Settings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 200);
		
		initialiseDifficulty();
		initialiseTreasure();
		initialiseMultiplayer();
		initialiseDone();
		
		frame.add(container);
		frame.setVisible(true);
		
	}

	private void initialiseDifficulty () {
		
		JPanel difficultyPanel = new JPanel ();
		
		JLabel difficultyLabel = new JLabel ("Choose difficulty level");
		difficultyPanel.add(difficultyLabel);
		
		JButton easyDifficulty = new JButton("Easy");
		easyDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setDifficulty(EASY);
				System.out.println("Easy Game");
			}
		});
		difficultyPanel.add(easyDifficulty);
		
		JButton mediumDifficulty = new JButton("Medium");
		mediumDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setDifficulty(MEDIUM);
				System.out.println("Intermediate Game");
			}
		});
		difficultyPanel.add(mediumDifficulty);
		
		JButton hardDifficulty = new JButton("Hard");
		hardDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setDifficulty(HARD);
				System.out.println("Difficult Game");
			}
		});
		difficultyPanel.add(hardDifficulty);
	
		container.add(difficultyPanel, BorderLayout.NORTH);
		
	}
	
	private void initialiseTreasure() {
		
		JPanel treasurePanel = new JPanel ();
		
		JLabel treasureLabel = new JLabel ("Choose Treasure availabilty");
		treasurePanel.add(treasureLabel);
		
		JButton noTreasure = new JButton("No Treasure");
		noTreasure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setHasTreasure(false);
				System.out.println("No Treasure");
			}
		});
		treasurePanel.add(noTreasure);
		
		JButton hasTreasure = new JButton("Has Treasure");
		hasTreasure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setHasTreasure(true);
				System.out.println("has Treasure");
			}
		});
		treasurePanel.add(hasTreasure);
	
		container.add(treasurePanel, BorderLayout.SOUTH);
		
	}

	private void initialiseMultiplayer() {
		
		JPanel multiplayerPanel = new JPanel ();
		
		JLabel multiplayerLabel = new JLabel ("Choose Multiplayer mode");
		multiplayerPanel.add(multiplayerLabel);
		
		JButton singleplayer = new JButton("Single Player");
		singleplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setHasMultiplayer(false);
				System.out.println("Single Player");
			}
		});
		multiplayerPanel.add(singleplayer);
		
		JButton multiplayer = new JButton("Multiplayer");
		multiplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setHasMultiplayer(true);
				System.out.println("Multiplayer");
			}
		});
		multiplayerPanel.add(multiplayer);
	
		container.add(multiplayerPanel, BorderLayout.SOUTH);
		
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
		container.add(donePanel, BorderLayout.SOUTH);
		
	}
	
}
