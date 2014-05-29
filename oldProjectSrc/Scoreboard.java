import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.JLabel;

public class Scoreboard {

	private ArrayList<ScoreNode> scores;
	private File file;
	
	public Scoreboard (String fileName) {
		
		scores = new ArrayList<ScoreNode>();
		
		file = new File(fileName);
		
		if (file.exists()) {
			
			 try {
				 
				Scanner s = new Scanner(new FileReader(fileName));
				
				while (s.hasNextLine()) {
					
					String str = s.nextLine();
					System.out.println(str);
					int space = str.indexOf(" ");
					double score = Double.parseDouble(str.substring(0, space - 1));
					String player = str.substring(space + 1);
					ScoreNode newNode = new ScoreNode (score, player);
					
					scores.add(newNode);
					
				}
				
				s.close();
				
			 } catch (FileNotFoundException e) {} 
		}
		else {
			try {
				file.createNewFile();
			} catch (IOException e) {}
		}
		
		
	}
	
	public void addScore (ScoreNode n) {
		
		scores.add(n);
		
		try {
			FileWriter f = new FileWriter(file, true);
			f.write(n.toString()+"\n");
			f.close();
		} catch (IOException e) {}
		
	}
	
	public String getScore (int scoreNumber) {
		
		Comparator<ScoreNode> comparator = new ScoreNodeComparator();
		Collections.sort(scores, comparator);
		
		DecimalFormat df = new DecimalFormat("#.00");
		
		ScoreNode n = scores.get(scoreNumber);
		
		return df.format(n.getScore()) + ".........." + n.getPlayer();
		
	}
	
	public int size () {
		return scores.size();
	}
	
}
