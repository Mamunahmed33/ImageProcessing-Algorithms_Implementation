package HarrisCornerDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/HarrisCornerDetection/HarrisImages/Chess_Board.png"));
		    
		    HarisCornerDetection cornerDetection = new HarisCornerDetection();
		    cornerDetection.EdgeDetection(img);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
