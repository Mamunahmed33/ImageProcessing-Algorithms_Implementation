package SkinDetection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import K_Means_Clustering.ConvertImageToGrayScale;
import K_Means_Clustering.KMeansClustering;

public class MainApp {
	
	public static void main(String args[]){
		try {
			BufferedImage img = ImageIO.read(new File("src/SkinDetection/Images/skin2.jpg"));
		    
			SkinDetection redEye = new SkinDetection(img);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
