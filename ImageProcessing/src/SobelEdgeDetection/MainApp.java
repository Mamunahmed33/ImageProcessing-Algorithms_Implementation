package SobelEdgeDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	
	public static void main(String args[]){
		try {
			BufferedImage img = ImageIO.read(new File("src/SobelEdgeDetection/SobelImages/img_3.jpg"));
		    
		    SobelEdgeDetection sobelEdgeDetection = new SobelEdgeDetection();
		    sobelEdgeDetection.EdgeDetection(img);
		 //   BufferedImage sobelImg = sobelEdgeDetection.getSobelImage();
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
