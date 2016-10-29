package FeatureExtractionAndMaleFemaleDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	
	public static void main(String args[]){
		//String filePath = "D:\\image-gender-classify\\data\\female\\";
		String filePath = "D:\\LFW GENDER\\fold0\\femalen\\";
		FeatureExtractionUsingSobel FE = new FeatureExtractionUsingSobel();
		BufferedImage img;
		int i =1;
		//String s = "1Snipped0"; 
		try {
			//while((img = ImageIO.read(new File(filePath +s+ ".jpg")))!=null){
			while((img = ImageIO.read(new File(filePath +i+ ".png")))!=null){
			//	BufferedImage img = ImageIO.read(new File("filePath" +i+".jpg"));

				FE.EdgeDetection(img);
				i++;
				//s= i+ "Snipped0";
				System.out.println(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
