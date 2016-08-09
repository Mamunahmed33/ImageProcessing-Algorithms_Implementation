package LetterSeparation;
import java.awt.Color;
import java.awt.image.BufferedImage;

/*
	Input: Colored Image / Any type of image
	return: 2D grayscale Matrix 
	Process: 1. Covert's the colored(or any) image to a grayscale image
			 2. Calculates the sum of grayscale R,G,B Values 
			 3. Sets the sum value to the matrix positions (pixels)
*/

public class ConvertImageToMatrix {
	public int[][] imageToMatrix(BufferedImage img){
		
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		int[][] matrix = new int[imgWidth][imgHeight];
		
		for(int col = 0; col < imgHeight; col++ ){
			for(int row = 0; row < imgWidth ; row++){
				
				Color c = new Color(img.getRGB(row, col));
                int red = (int)(c.getRed());
                int green = (int)(c.getGreen());
                int blue = (int)(c.getBlue());
                
                if(red >200 || green > 200 || blue > 200)
                {
                    matrix[row][col] = 0;
                }
                else{
                	 matrix[row][col] = 255;
                }
			}
		}
		
		return matrix;
	}
}
