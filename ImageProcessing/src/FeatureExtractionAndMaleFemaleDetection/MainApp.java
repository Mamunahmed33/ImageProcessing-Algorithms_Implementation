package FeatureExtractionAndMaleFemaleDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	
	public static void main(String args[]){
		String filePath = "D:\\LFW GENDER\\fold0\\malen";
		FeatureExtractionUsingSobel FE = new FeatureExtractionUsingSobel();
		BufferedImage img;
		int i =0;
		try {
			while((img = ImageIO.read(new File("filePath" +i+".jpg"))) !=null){
			//	BufferedImage img = ImageIO.read(new File("filePath" +i+".jpg"));

				FE.EdgeDetection(img);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
