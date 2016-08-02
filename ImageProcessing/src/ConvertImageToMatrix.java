import java.awt.Color;
import java.awt.image.BufferedImage;

public class ConvertImageToMatrix {
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
