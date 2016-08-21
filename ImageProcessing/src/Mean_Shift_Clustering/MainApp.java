package Mean_Shift_Clustering;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		String fileSource = "src/Mean_Shift_Clustering/Images/";
		
		try {
		    img = ImageIO.read(new File(fileSource+"img_4.jpg"));
		    
		    ConvertImageToGrayScale grayScale = new ConvertImageToGrayScale();
		    grayScale.convetToGrayScale(img);
		    
		    int[][] imgGrayMat = grayScale.getImageGrayScaleMatrix();
		    
		    MeanShiftClustering meanShiftClustering = new MeanShiftClustering(img, imgGrayMat);
		    //kMeansClustering.Mean_Shift_Clustering(img, imgGrayMat);
		   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
