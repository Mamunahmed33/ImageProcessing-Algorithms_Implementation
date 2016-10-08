package FeatureExtractionAndMaleFemaleDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	
	public static void main(String args[]){
		String filePath = "D:\\LFW GENDER\\fold4\\malen\\";
		FeatureExtractionUsingSobel FE = new FeatureExtractionUsingSobel();
		BufferedImage img;
		int i =1;
		
		try {
			while((img = ImageIO.read(new File(filePath +i+ ".png")))!=null){
			//	BufferedImage img = ImageIO.read(new File("filePath" +i+".jpg"));

				FE.EdgeDetection(img);
				i++;
				System.out.println(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
