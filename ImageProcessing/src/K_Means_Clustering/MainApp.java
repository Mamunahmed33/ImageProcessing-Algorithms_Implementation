package K_Means_Clustering;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FileChooser_Saver.FileChooser;

public class MainApp {
	public static void main(String args[]){
		BufferedImage img = null;
		String fileSource = "src/K_Means_Clustering/Images/";
		
		FileChooser fileChooser = FileChooser.getFileChooserInstance();
		
		try {
		    img = ImageIO.read(new File(fileChooser.getFile(fileSource)));
		    
		    ConvertImageToGrayScale grayScale = new ConvertImageToGrayScale();
		    BufferedImage a =   grayScale.convetToGrayScale(img);
		    new WriteImage().Write(a, "src/K_Means_Clustering/Images/", "Gray.jpg");
		    
		    int[][] imgGrayMat = grayScale.getImageGrayScaleMatrix();
		    
		    KMeansClustering kMeansClustering = new KMeansClustering(img, imgGrayMat);
		   // kMeansClustering.KMeansClustering(img, imgGrayMat);
		   
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
