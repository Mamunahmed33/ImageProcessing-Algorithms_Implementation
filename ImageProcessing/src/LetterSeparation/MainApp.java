package LetterSeparation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/Images/i.jpg"));
		    
		    ConvertImageToBinary bin = new ConvertImageToBinary();
		    
		    BufferedImage BinaryImg = bin.convetToBinary(img);
		    new WriteImage().Write(BinaryImg, "src/Images/", "BinaryImage.jpg");
		    
		    int[][] imgMat = bin.getImageMat();
		    
		    CountPixelColor cp =  new CountPixelColor();
		    cp.setMainImage(img);
		    
		    cp.count(BinaryImg, imgMat);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
