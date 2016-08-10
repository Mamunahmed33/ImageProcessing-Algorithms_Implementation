package GrayScaleAndBinaryImageConversion;
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
    Color whiteColor = new Color(255, 255, 255);
    Color blackColor = new Color(0, 0, 0);
	public BufferedImage convetToBinary(BufferedImage img){
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		for(int col = 0; col < imgHeight; col++ ){
			for(int row = 0; row < imgWidth ; row++){
				Color c = new Color(img.getRGB(row, col));

                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                
                if(red >150 || green > 150 || blue > 150)
                {
                    img.setRGB(row, col, whiteColor.getRGB());
                }
                else{
                    img.setRGB(row, col, blackColor.getRGB());
                }
			}
		}
		
		return img;
	}
}
