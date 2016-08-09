package GrayScaleAndBinaryImageConversion;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		String filepath = "src/GrayScaleAndBinaryImageConversion/Images/";
		try {
		    img = ImageIO.read(new File(filepath + "img_1.jpg"));
		    
		    BufferedImage grayImg = new ConvertImageToGrayScale().convetToGrayScale(img);
		    new WriteImage().Write(grayImg, filepath, "grayScaleImage_1.jpg");
		    
		    BufferedImage BinaryImg = new ConvertImageToBinary().convetToBinary(img);
		    new WriteImage().Write(BinaryImg, filepath, "BinaryImage.jpg");
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
