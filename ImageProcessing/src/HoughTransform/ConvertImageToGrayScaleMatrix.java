package HoughTransform;
import java.awt.Color;
import java.awt.image.BufferedImage;

/*
	Input: Colored Image / Any type of image
	return: 2D grayscale Matrix 
	Process: 1. Covert's the colored(or any) image to a grayscale image
			 2. Calculates the sum of grayscale R,G,B Values 
			 3. Sets the sum value to the matrix positions (pixels)
*/

public class ConvertImageToGrayScaleMatrix {
	public int[][] imageToMatrix(BufferedImage img){
		
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		int[][] matrix = new int[imgWidth][imgHeight];
		
		for(int i = 0; i < imgHeight; i++ ){
			for(int j = 0; j < imgWidth ; j++){
				
				Color c = new Color(img.getRGB(j, i));
                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() *0.114);
                
                int sum = red+green+blue;
				matrix[j][i] = sum;
			}
		}
		
		return matrix;
	}
}
