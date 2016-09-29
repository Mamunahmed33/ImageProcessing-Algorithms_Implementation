package HoughTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/HoughTransform/HoughTransformImages/images.jpg"));
		    
		    SobelEdgeDetection sobelEdgeDetection = new SobelEdgeDetection();
		    sobelEdgeDetection.EdgeDetection(img);
		    BufferedImage sobelImg = sobelEdgeDetection.getSobelImage();
		    
		    HoughTransform ht = new HoughTransform();
		    ht.setColoredImage(img);
		    ht.LineDetection(sobelImg);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
