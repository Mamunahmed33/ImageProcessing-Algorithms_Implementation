import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class WriteImage {
	public void Write(BufferedImage img, String path, String imageName){
	
		File f = null;
		f = new File(path + imageName);
		
		try {
			ImageIO.write(img, "jpg", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
