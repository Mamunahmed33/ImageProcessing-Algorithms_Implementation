package Otsu_Thresholding;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import K_Means_Clustering.ConvertImageToGrayScale;
import K_Means_Clustering.KMeansClustering;

public class MainApp {
	
	public static void main(String args[]){
		try {
			BufferedImage img = ImageIO.read(new File("src/SobelEdgeDetection/SobelImages/img_3.jpg"));
		    
			ConvertImageToGrayScale grayScale = new ConvertImageToGrayScale();
		    grayScale.convetToGrayScale(img);
		    
		    int[][] imgGrayMat = grayScale.getImageGrayScaleMatrix();
		    
		    OtsuThresholdDetection otsuThresholdDetection = new OtsuThresholdDetection(img, imgGrayMat);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
