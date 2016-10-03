package RedEyeDetectionAndRemoving;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		String fileSource = "src/RedEyeDetectionAndRemoving/Images/";
		
		try {
		    img = ImageIO.read(new File(fileSource+"redeye1.jpg"));
		    
		   RedEye redEye = new RedEye();
		   redEye.corrigirRedEye(0, img.getWidth(), 0, img.getHeight(), img);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
