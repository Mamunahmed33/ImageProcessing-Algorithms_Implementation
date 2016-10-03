package RedEyeDetectionAndRemoving;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ImageMasking.WriteImage;

public class RedEye {
	
	public void corrigirRedEye(int posStartX, int maxX, int posStartY, int maxY, BufferedImage image) {
	    for(int x = posStartX; x < maxX; x++) {
	        for(int y = posStartY; y < maxY; y++) {

	            int c = image.getRGB(x,y);
	            Color co = new Color(image.getRGB(x,y));
	            
	            int  red = co.getRed();
	            int  green = co.getGreen();
	            int  blue = co.getBlue();

	            float redIntensity = ((float)red / ((green + blue) / 2));
	            if (redIntensity > 2.2) {
	                Color newColor = new Color(0, 0, 0);
	                image.setRGB(x, y, newColor.getRGB());
	            }

	        }
	    }
	    
	    new WriteImage().Write(image, "src/RedEyeDetectionAndRemoving/Images/", "removedRedEye.jpg");
	}
}
