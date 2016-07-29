import java.awt.Color;
import java.awt.image.BufferedImage;


public class ConvertImageToBinary {
	public BufferedImage convetToBinary(BufferedImage img){
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		for(int col = 0; col < imgHeight; col++ ){
			for(int row = 0; row < imgWidth ; row++){
				Color c = new Color(img.getRGB(col, row));
                int red = (int)(c.getRed());
                int green = (int)(c.getGreen());
                int blue = (int)(c.getBlue());
                
                if(red >150 || green > 150 || blue > 150)
                {
                    Color newColor = new Color(255, 255, 255);
                    img.setRGB(col, row, newColor.getRGB());
                }
                else{
                	Color newColor = new Color(0, 0, 0);
                    img.setRGB(col, row, newColor.getRGB());
                }
			}
		}
		
		return img;
	}
}
