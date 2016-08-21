package Mean_Shift_Clustering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import K_Means_Clustering.WriteImage;

public class MeanShiftClustering {
	private int imgHeight, imgWeight;
	int[][] clusterGroup;
	
	public MeanShiftClustering(BufferedImage img, int[][] imageMat){
		this.imgHeight = img.getHeight();
		this.imgWeight = img.getWidth();
		
		int [] k = {32, 40, 60, 85, 150};
		
		/*int [] K = new int[20];
		for(int i=0; i< 20; i++){
			int randomNumber = Math.random();
		}*/
		for(int i=0; i< k.length; i++){
			k[i] = clusterPixels(k[i], imageMat, i);
		}
		
		changeClusterPixelsColor(clusterGroup, k);
	}
	
	public int clusterPixels(int center, int[][] imageMat, int clusterNumber){
		clusterGroup = new int[imgWeight][imgHeight];
		
		int sumOfClusterPixels = 0, countClusterMember = 0;
		for(int i = 0; i < imgHeight; i++){
			for(int j = 0; j < imgWeight; j++){
				int tempDistance = Math.abs(center - imageMat[j][i]);
				
				if(tempDistance <= 10){
					sumOfClusterPixels += imageMat[j][i];
					countClusterMember++;
					clusterGroup[j][i] = clusterNumber;
				}
			}
		}
		
		int newCenter = sumOfClusterPixels / countClusterMember;
		
		if(newCenter != center){
			clusterPixels(newCenter, imageMat, clusterNumber);
		}
		
		return center;
	}
	
	private void changeClusterPixelsColor(int[][] clusterGroup, int k[] ) {
		int numberofClusterGroup = k.length;
		BufferedImage img = null;
		
		Color[] c = new Color[numberofClusterGroup]; 
		
		for(int i = 0; i < numberofClusterGroup; i++){
			c[i] = new Color(k[i], k[i], k[i]);
		}
		
		try {
			img = ImageIO.read(new File("src/Mean_Shift_Clustering/Images/img_4.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < imgHeight; i++){
			for(int j = 0; j < imgWeight; j++){
				int groupNumber = clusterGroup[j][i];
				img.setRGB(j, i, c[groupNumber].getRGB());
			}
		}
		
		new WriteImage().Write(img, "src/Mean_Shift_Clustering/Images/", "MeanShift.jpg");
	}
}
