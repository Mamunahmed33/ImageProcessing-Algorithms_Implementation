import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageReading {
	static BufferedImage image = null;
	public static void main(String args[]){
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/Images/img_6.png"));
		    
		/*    image = new BufferedImage(img.getWidth(), img.getWidth(),
		    	    BufferedImage.TYPE_BYTE_GRAY);*/
		    
		    //img = new ConvertImageToGrayScale().convetToGrayScale(img);
		    img = new ConvertImageToBinary().convetToBinary(img);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File f = null;
		f = new File("src/Images/Output.jpg");
		
		try {
			ImageIO.write(img, "jpg", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//new ConvertImageToMatrix().imageToMatrix(img);
	}
}
