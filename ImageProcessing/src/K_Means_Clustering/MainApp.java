package K_Means_Clustering;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		String fileSource = "src/K_Means_Clustering/Images/";
		
		try {
		    img = ImageIO.read(new File(fileSource+"img13.jpg"));
		    
		    ConvertImageToGrayScale grayScale = new ConvertImageToGrayScale();
		    grayScale.convetToGrayScale(img);
		    
		    int[][] imgGrayMat = grayScale.getImageGrayScaleMatrix();
		    
		    KMeansClustering kMeansClustering = new KMeansClustering(img, imgGrayMat);
		   // kMeansClustering.KMeansClustering(img, imgGrayMat);
		   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
