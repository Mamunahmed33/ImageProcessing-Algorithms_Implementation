package FeatureExtractionAndMaleFemaleDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	
	public static void main(String args[]){
		String filePath = "D:\\LFW GENDER\\fold0\\malen";
		SobelEdgeDetection sobelEdgeDetection = new SobelEdgeDetection();
		try {
			for (int i = 1; i <10000; i++){
				BufferedImage img = ImageIO.read(new File("filePath" +i+".jpg"));

				sobelEdgeDetection.EdgeDetection(img);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
