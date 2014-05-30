import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class MazeTheme {
	private HashMap<String, String> imagePaths;
	private String name;
	public MazeTheme(List<String> list) {
		imagePaths = new HashMap<String, String>();
		storePaths(list);
	}
	
	public MazeTheme(String...paths) {
		storePaths(Arrays.asList(paths));
	}

	private void storePaths(List<String> paths) {
		int i = 0;
		name = paths.get(0);
		i++;
		imagePaths.put("COIN", paths.get(i));
		i++;
		imagePaths.put("END", paths.get(i));
		i++;
		imagePaths.put("PATH", paths.get(i));
		i++;
		imagePaths.put("PLAYER", paths.get(i));
		i++;
		imagePaths.put("PREVIEW", paths.get(i));
		i++;
		imagePaths.put("START", paths.get(i));
		i++;
		imagePaths.put("WALL", paths.get(i));
		
	}
	
	public Set<String> getImageIds() {
		return imagePaths.keySet();
	}
	public String getPathOfImage(String type) {
		if (!imagePaths.containsKey(type)) {
			return null;
		}
		return imagePaths.get(type);
	}

	public String getName() {
		return name;
	}
	
}