import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/Images/img_3.jpg"));
		    
		 //   BufferedImage grayImg = new ConvertImageToGrayScale().convetToGrayScale(img);
		    //new ConvertImageToMatrix().imageToMatrix(img);
		  //  new WriteImage().Write(img, "src/Images/", "grayScaleImage_1.jpg");
		    
		    /*BufferedImage BinaryImg = new ConvertImageToBinary().convetToBinary(img);
		    new WriteImage().Write(BinaryImg, "src/Images/", "BinaryImage.jpg");
		    */
		    SobelEdgeDetection sobelEdgeDetection = new SobelEdgeDetection();
		    sobelEdgeDetection.EdgeDetection(img);
		    BufferedImage sobelImg = sobelEdgeDetection.getSobelImage();
		    
		    HoughTransform ht = new HoughTransform();
		    ht.setColoredImage(img);
		    ht.LineDetection(sobelImg);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
