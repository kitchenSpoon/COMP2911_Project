import java.awt.Image;
import java.util.HashMap;
public class ImageStore {

	private HashMap<String, Image> imageRefs;
	
	public ImageStore() {
		imageRefs = new HashMap<String, Image>();
	}
	
	public void add(String ref) {
		if (imageRefs.containsKey(ref)) {
			
		}
	}
	
	public Image getImage(String ref) {
		if (imageRefs.containsKey(ref)) {
			return imageRefs.get(ref);
		} else {
			return null;
		}
	}
}
