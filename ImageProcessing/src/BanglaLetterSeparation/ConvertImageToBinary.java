package BanglaLetterSeparation;
import java.awt.Color;
import java.awt.image.BufferedImage;

/*
	Input: Image
	Returns: A Binary Image
	Process: 1. Gets the colored image's R,G,B values 
			 2. if any of them is > 200 then it sets white color to that pixel
			 3. Else it sets Black color to that pixel
*/

public class ConvertImageToBinary {
	int [][] imgMat;
	public BufferedImage convetToBinary(BufferedImage img){
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		imgMat = new int[imgWidth][imgHeight];
		
		for(int col = 0; col < imgHeight; col++ ){
			for(int row = 0; row < imgWidth ; row++){
				Color c = new Color(img.getRGB(row, col));
                int red = (int)(c.getRed());
                int green = (int)(c.getGreen());
                int blue = (int)(c.getBlue());
                
                if(red >200 || green > 200 || blue > 200)
                {
                    Color newColor = new Color(0, 0, 0);
                    img.setRGB(row, col, newColor.getRGB());
                    
                    imgMat[row][col] = 0;
                }
                else{
                	Color newColor = new Color(255, 255, 255);
                    img.setRGB(row, col, newColor.getRGB());
                    imgMat[row][col] = 1;
                }
			}
		}
		
		return img;
	}
	
	public int[][] getImageMat(){
		return imgMat;
	}
	
}
