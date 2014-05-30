import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class MazeThemeImporter {

	private String path;
	private ArrayList<MazeTheme> themes;
	public MazeThemeImporter(String p) {
		path = p;
		ArrayList<MazeTheme> themes = new ArrayList<MazeTheme>();
		importThemes();
	}
	
	public MazeThemeImporter() {
		path = "./Themes/";
		ArrayList<MazeTheme> themes = new ArrayList<MazeTheme>();
		importThemes();

	}
	
	private void importThemes() {
		ArrayList<MazeTheme> importedThemes = new ArrayList<MazeTheme>();
		try {
			File folder = new File(path);
			ArrayList<String> themeData = new ArrayList<String>();
			for (File f : folder.listFiles()) {
				if (f.isDirectory()) {
					themeData.add(f.getName());
					File[] sortedFilesList = f.listFiles();
					Arrays.sort(sortedFilesList);
					for (int i = 0; i < sortedFilesList.length; i++) {
						if (sortedFilesList[i].isFile() && !sortedFilesList[i].isHidden()) {
						    themeData.add(sortedFilesList[i].getPath());
						}
					}
					MazeTheme m = new MazeTheme(themeData);
					importedThemes.add(m);
					themeData.clear();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		themes = importedThemes;
	}
	
	public ArrayList<MazeTheme> getThemes() {
		return themes;
	}
	
}
