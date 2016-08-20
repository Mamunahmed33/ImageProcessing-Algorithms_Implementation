package BanglaLetterSeparation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		String fileSource = "src/BanglaLetterSeparation/Images/";
		
		try {
		    img = ImageIO.read(new File(fileSource+"i.jpg"));
		    
		    ConvertImageToBinary bin = new ConvertImageToBinary();
		    
		    BufferedImage BinaryImg = bin.convetToBinary(img);
		    new WriteImage().Write(BinaryImg, fileSource, "BinaryImage.jpg");
		    
		    int[][] imgMat = bin.getBinaryImageMatrix();
		    
		    LetterSeparation cp =  new LetterSeparation();
		//    cp.setMainImage(img);
		    
		    cp.count(BinaryImg, imgMat);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
