import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
public class ImageStore {

	private HashMap<String, Image> imageRefs;
	private int scalingSize;
	public ImageStore() {
		imageRefs = new HashMap<String, Image>();
		scalingSize = 0;
	}
	
	public ImageStore(int size) {
		imageRefs = new HashMap<String, Image>();
		scalingSize = size;
	}
	
	public void add(String path, String ref) {
		BufferedImage newImage = null;
		try {
			newImage = ImageIO.read(new File(path));
			if (scalingSize != 0) {
				if (newImage.getHeight() != scalingSize) {
					Image i = newImage.getScaledInstance(scalingSize, scalingSize, Image.SCALE_DEFAULT);
					newImage = new BufferedImage(scalingSize, scalingSize, 
						      BufferedImage.TYPE_INT_ARGB);
						Graphics g = newImage.getGraphics();
						g.drawImage(i, 0, 0, null);
						imageRefs.put(ref, newImage);
						g.dispose();
					
				}
			} else {
				imageRefs.put(ref, newImage);
			}
			
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
