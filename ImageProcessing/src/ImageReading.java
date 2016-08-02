import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageReading {
		BufferedImage img = null;
		
		public BufferedImage readImage(String filePathWithFileName){
			try {
			    img = ImageIO.read(new File(filePathWithFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return img;
	}
}
