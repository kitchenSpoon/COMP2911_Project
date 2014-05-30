import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
public class ImageStore {

	private HashMap<String, Image> imageRefs;
	
	public ImageStore() {
		imageRefs = new HashMap<String, Image>();
	}
	
	public void add(String path, String ref) {
		BufferedImage newImage = null;
		try {
			newImage = ImageIO.read(new File(path));
			
			imageRefs.put(ref, newImage);
		} catch (IOException e) {
			return;
		}
				
	}
	
	public Image getImage(String ref) {
		if (imageRefs.containsKey(ref)) {
			return imageRefs.get(ref);
		} else {
			return null;
		}
	}
	public void addTheme(MazeTheme theme) {
		for (String s : theme.getImageIds()) {
			add(theme.getPathOfImage(s), s);
		}
	}

	public int size() {
		return imageRefs.size();
	}
}
