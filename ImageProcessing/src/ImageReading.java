import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageReading {
	static BufferedImage image = null;
	public static void main(String args[]){
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/Images/img_3.jpg"));
		    
		    img = new ConvertImageToGrayScale().convetToGrayScale(img);
		   // img = new ConvertImageToBinary().convetToBinary(img);
		    new WriteImage().Write(img, "src/Images/", "grayScaleImage_1.jpg");
		    img = new SobelEdgeDetection().EdgeDetection(img);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//new ConvertImageToMatrix().imageToMatrix(img);
	}
}
