package SobelEdgeDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FileChooser_Saver.FileChooser;

public class MainApp {
	
	public static void main(String args[]){
		try {
			FileChooser fileChooser = FileChooser.getFileChooserInstance();
			String filePath = "src/SobelEdgeDetection/SobelImages/";
			BufferedImage img = ImageIO.read(new File(fileChooser.getFile(filePath)));
			
		    SobelEdgeDetection sobelEdgeDetection = new SobelEdgeDetection();
		    sobelEdgeDetection.EdgeDetection(img);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
