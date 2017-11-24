package GrayScaleAndBinaryImageConversion;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FileChooser_Saver.FileChooser;


public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		String filePath = "src/GrayScaleAndBinaryImageConversion/Images/";
		
		FileChooser fileChooser = FileChooser.getFileChooserInstance();
		
		try {
		    img = ImageIO.read(new File(fileChooser.getFile(filePath)));
		    WriteImage writeImage = new WriteImage();
		    
		    BufferedImage grayImg = new ConvertImageToGrayScale().convetToGrayScale(img);
		    writeImage.Write(grayImg, filePath, "grayScaleImage_1.jpg");
		    
		    BufferedImage BinaryImg = new ConvertImageToBinary().convetToBinary(img);
		    writeImage.Write(BinaryImg, filePath, "BinaryImage.jpg");
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
