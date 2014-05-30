import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;


public class CustomiseMazeGame {

	public final static int EASY = 0;
	public final static int MEDIUM = 1;
	public final static int HARD = 2;
	boolean submitted = false;
	private MazeGameOptions mazeOptions;
	
	private JFrame frame;
	private JPanel container;
	private ImageStore previews;
	private ArrayList<MazeTheme> themes;
	
	//private int difficulty = 0; // default easy difficulty
	
	public CustomiseMazeGame (MazeGameOptions _mazeOptions, ImageStore themePreviews, ArrayList<MazeTheme> themes) {
		if(mazeOptions == null) {
			System.out.println("MazeOptions is null");
		}
		mazeOptions = _mazeOptions;
		previews = themePreviews;
		this.themes = themes;
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		
		frame = new JFrame ("Settings");
		frame.setSize(600, 500);
		
		initialiseDifficulty();
		initialiseTreasure();
		initialiseMultiplayer();
		initialiseTheme();
		initialiseDone();
		
		frame.add(container);
		frame.setLocation(GameManager.screenWidth/2-frame.getSize().width/2, GameManager.screenHeight/2-frame.getSize().height/2);
		frame.setVisible(true);
		frame.requestFocus();
		
		while(!submitted && frame.isShowing()) {
			System.out.print("");
		}
		
	}

	public void initialiseTheme() {
		JPanel themePanel = new JPanel();
		JLabel themeLabel = new JLabel("Choose a theme");
		themePanel.add(themeLabel);
		final JScrollPane scrollPane = new JScrollPane();
		boolean scrollPaneMode = false;
		JPanel themePreviewsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = c.CENTER;
		if (previews.size() > 2) {
			scrollPaneMode = true;
		}
		ButtonGroup themeButtonGroup = new ButtonGroup();
		JRadioButtonMenuItem[] themeButtons = new JRadioButtonMenuItem[previews.size()];
		for (int i = 0; i < themeButtons.length; i++) {
			themeButtons[i] = new JRadioButtonMenuItem(themes.get(i).getName());
			themeButtons[i].setName(themes.get(i).getName());
			themeButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					Object source = event.getSource();
					if (source instanceof JRadioButtonMenuItem) {
						for (MazeTheme mt : themes) {
							if (mt.getName().equals(((JRadioButtonMenuItem) source).getName())) {
								mazeOptions.setTheme(mt);
								break;
							}
						}
//						System.out.println(((JRadioButtonMenuItem) source).getBounds());
//						Point p = ((JRadioButtonMenuItem) source).getLocation();
//						System.out.println(p);
//						Rectangle r = new Rectangle(new Point((int) p.getX(), (int)p.getY() - 68));
//						((JRadioButtonMenuItem) source).scrollRectToVisible(new Rectangle(p));
//						System.out.println(scrollPane.getViewport().getViewPosition());
					}
				}
			});
		}
		for (JRadioButtonMenuItem r : themeButtons) {
			themeButtonGroup.add(r);
			ImageIcon icon = new ImageIcon(previews.getImage(r.getName()));
			r.setIcon(icon);
			if (mazeOptions.getTheme().getName().equals(r.getName())) {
				r.setSelected(true);
			}
			
			if (scrollPaneMode) {

				themePreviewsPanel.add(r, c);
				if (c.gridx + 1 > 0) {
					c.gridy++;
					c.gridx=0;
				} else {
					c.gridx++;
				}
			} else {
				themePanel.add(r);

			}
			
		}
		
		if (scrollPaneMode) {
			scrollPane.setWheelScrollingEnabled(true);
			scrollPane.getVerticalScrollBar().setUnitIncrement(10);
			scrollPane.setViewportView(themePreviewsPanel);
			scrollPane.setPreferredSize(new Dimension(250, 160));
			themePanel.add(scrollPane);

		}
		container.add(themePanel, BorderLayout.SOUTH);
		
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
				submitted = true;
				frame.dispose();
			}
		});
		donePanel.add(done);
		container.add(donePanel, BorderLayout.SOUTH);
		
	}
	
	public boolean settingsOpen () {
		return frame.isVisible();
	}
}
