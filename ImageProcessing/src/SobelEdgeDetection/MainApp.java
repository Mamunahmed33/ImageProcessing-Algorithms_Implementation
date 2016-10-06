package SobelEdgeDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	
	public static void main(String args[]){
		try {
			BufferedImage img = ImageIO.read(new File("src/FeatureExtractionUsingSobel/SobelImages/img_3.jpg"));
		    
		    SobelEdgeDetection sobelEdgeDetection = new SobelEdgeDetection();
		    sobelEdgeDetection.EdgeDetection(img);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
