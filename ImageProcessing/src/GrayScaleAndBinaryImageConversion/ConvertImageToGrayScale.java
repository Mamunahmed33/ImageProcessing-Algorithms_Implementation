package GrayScaleAndBinaryImageConversion;
import java.awt.Color;
import java.awt.image.BufferedImage;

/*
Input: Image
Returns: A Grayscale Image
Process: 1. Gets the colored image's R,G,B values and converts it to grayscale color
		 2. Calculates the sum of grayscale R,G,B Values 
		 3. Sets the sum value to the image pixels
*/

public class ConvertImageToGrayScale {
	
	public BufferedImage convetToGrayScale(BufferedImage img){
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();

		for(int col = 0; col < imgHeight; col++ ){
			for(int row = 0; row < imgWidth ; row++){
				Color c = new Color(img.getRGB(row, col));

                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() *0.114);
                
                int sum = red+green+blue;
                		
                Color newColor = new Color(sum, sum, sum);
               
                img.setRGB(row, col, newColor.getRGB());
			}
		}
		
		return img;
	}
			
}
