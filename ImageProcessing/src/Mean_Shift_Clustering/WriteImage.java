package Mean_Shift_Clustering;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
Input: Any Image, pathname and name of the output image
Output: A JPG image
Process: 1. Takes a image and writes the image to that path folder 
*/
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
