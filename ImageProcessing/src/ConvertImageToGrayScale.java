import java.awt.Color;
import java.awt.image.BufferedImage;

public class ConvertImageToGrayScale {
	
	public BufferedImage convetToGrayScale(BufferedImage img){
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		for(int col = 0; col < imgHeight; col++ ){
			for(int row = 0; row < imgWidth ; row++){
				Color c = new Color(img.getRGB(col, row));
                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() *0.114);
                
                Color newColor = new Color(red+green+blue,
                		red+green+blue, red+green+blue);
               
                img.setRGB(col, row, newColor.getRGB());
			}
		}
		
		return img;
	}
			
}
