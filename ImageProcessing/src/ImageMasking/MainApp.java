package ImageMasking;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		String fileSource = "src/ImageMasking/Images/";
		
		try {
		    img = ImageIO.read(new File(fileSource+"input.png"));
		    
		    ConvertImageToGrayScale grayScale = new ConvertImageToGrayScale();
		    BufferedImage a =   grayScale.convetToGrayScale(img);
		    
		    int[][] imgGrayMat = grayScale.getImageGrayScaleMatrix();
		    ImageSharpening is = new ImageSharpening(img, imgGrayMat);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
