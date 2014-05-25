import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


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
		frame.requestFocus();
		
	}

	private void initialiseDifficulty () {
		
		JPanel difficultyPanel = new JPanel ();
		
		JLabel difficultyLabel = new JLabel ("Choose difficulty level");
		difficultyPanel.add(difficultyLabel);
		ButtonGroup diffButtonGroup = new ButtonGroup();
		
		JToggleButton easyDifficulty = new JToggleButton("Easy");
		easyDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setDifficulty(EASY);
				System.out.println("Easy Game");
			}
		});
		diffButtonGroup.add(easyDifficulty);
		difficultyPanel.add(easyDifficulty);
		
		JToggleButton mediumDifficulty = new JToggleButton("Medium");
		mediumDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setDifficulty(MEDIUM);
				System.out.println("Intermediate Game");
			}
		});
		diffButtonGroup.add(mediumDifficulty);

		difficultyPanel.add(mediumDifficulty);
		
		JToggleButton hardDifficulty = new JToggleButton("Hard");
		hardDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setDifficulty(HARD);
				System.out.println("Difficult Game");
			}
		});
		diffButtonGroup.add(hardDifficulty);

		difficultyPanel.add(hardDifficulty);
		if (mazeOptions != null) {
			switch(mazeOptions.getDifficulty()) {
			case EASY:
				easyDifficulty.setSelected(true);
				break;
			case MEDIUM:
				mediumDifficulty.setSelected(true);
				break;
			case HARD:
				hardDifficulty.setSelected(true);
				break;
			default:
				break;
			}
		}
		
		container.add(difficultyPanel, BorderLayout.NORTH);
		
	}
	
	private void initialiseTreasure() {
		
		JPanel treasurePanel = new JPanel ();
		
		JLabel treasureLabel = new JLabel ("Choose Treasure availabilty");
		treasurePanel.add(treasureLabel);
		ButtonGroup treasureButtonGroup = new ButtonGroup();
		JToggleButton noTreasure = new JToggleButton("No Treasure");
		noTreasure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setHasTreasure(false);
				System.out.println("No Treasure");
			}
		});
		treasurePanel.add(noTreasure);
		treasureButtonGroup.add(noTreasure);
		
		JToggleButton hasTreasure = new JToggleButton("Has Treasure");
		hasTreasure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setHasTreasure(true);
				System.out.println("has Treasure");
			}
		});
		treasureButtonGroup.add(hasTreasure);
		treasurePanel.add(hasTreasure);
		if (mazeOptions != null && mazeOptions.isHasTreasure()) {
			hasTreasure.setSelected(true);
		} else {
			noTreasure.setSelected(true);
		}
		container.add(treasurePanel, BorderLayout.SOUTH);
		
	}

	private void initialiseMultiplayer() {
		
		JPanel multiplayerPanel = new JPanel ();
		
		JLabel multiplayerLabel = new JLabel ("Choose Multiplayer mode");
		multiplayerPanel.add(multiplayerLabel);
		ButtonGroup playerButtonGroup = new ButtonGroup();
		JToggleButton singlePlayer = new JToggleButton("Single Player");
		singlePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setHasMultiplayer(false);
				System.out.println("Single Player");
			}
		});
		playerButtonGroup.add(singlePlayer);
		multiplayerPanel.add(singlePlayer);
		
		JToggleButton multiplayer = new JToggleButton("Multiplayer");
		multiplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeOptions.setHasMultiplayer(true);
				System.out.println("Multiplayer");
			}
		});
		playerButtonGroup.add(multiplayer);
		multiplayerPanel.add(multiplayer);

		if (mazeOptions != null && mazeOptions.isHasMultiplayer()) {
			multiplayer.setSelected(true);
		} else {
			singlePlayer.setSelected(true);
		}
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
