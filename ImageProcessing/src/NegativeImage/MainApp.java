package NegativeImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import K_Means_Clustering.WriteImage;

public class MainApp {
	
	public static void main(String args[]){
		try {
			BufferedImage img = ImageIO.read(new File("src/NegativeImage/Images/img21.jpg"));
		    
			/*ConvertImageToGrayScale convertImageToGrayScale = new ConvertImageToGrayScale();
			BufferedImage grayImage = convertImageToGrayScale.convetToGrayScale(img);*/
			
			NegativeImage negativeImage = new NegativeImage();
			BufferedImage negativeImg = negativeImage.convertImageToNegative(img);
			
			new WriteImage().Write(negativeImg, "src/NegativeImage/Images/", "NegativeImage.jpg");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
