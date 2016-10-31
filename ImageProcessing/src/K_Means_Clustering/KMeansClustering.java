package K_Means_Clustering;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class KMeansClustering {
	private int imgHeight, imgWeight;
	BufferedImage mainColoredImage;
	
	public KMeansClustering(BufferedImage img, int[][] imageMat){
		this.imgHeight = img.getHeight();
		this.imgWeight = img.getWidth();
		
		this.mainColoredImage = img;
		
//		int [] K = {30, 40, 55, 75, 100, 120, 150, 180};
		int [] K = {30, 50, 65, 80, 120, 150, 180};
		
		clusterPixels(K, imageMat);
	}
	
	public void clusterPixels(int[] k, int[][] imageMat){
		
		int distance = 500;
		
		int[][] clusterGroup = new int[imgWeight][imgHeight];
		
		for(int l = 0; l<10; l++){ // loop for cluster iteration to get new center
			for(int i = 0; i < imgHeight; i++){
				for(int j = 0; j < imgWeight; j++){
					for(int a = 0; a <k.length; a++){
						int tempDis = Math.abs(k[a] - imageMat[j][i]);
						if(tempDis < distance){
							distance = tempDis;
							clusterGroup[j][i] =  a;
						}
					}
					distance = 500;
				}
			}
			
			 k = calculateNewCentroid(clusterGroup, k, imageMat);
			
			 changeClusterPixelsColor(clusterGroup, k);
		}
	}
	
	private void changeClusterPixelsColor(int[][] clusterGroup, int k[] ) {
		int numberOfCluster = k.length;
		
		Color[] c = new Color[numberOfCluster];
		
		/*for(int i = 0; i < numberOfCluster; i++){
			c[i] = new Color(k[i], k[i], k[i]);
		}*/
		
		/*c[0] = new Color(255,0,0);
		c[1] = new Color(0,255,0);
		c[2] = new Color(0,0,255);
		c[3] = new Color(255,255,255);
		*/
		if(numberOfCluster> 3){
			 Random rand = new Random();
			 Color tempColor;
			 int max = 255, min = 50, randomNum=0;
			 int[] RGB = new int[3];
			 
			 // This loop can start from i=0 or i==4. If i=4 then previous commented color code need to be uncommented
			 for(int i=0; i< numberOfCluster; i++){
				 randomNum = rand.nextInt((max - min) + 1) + min;
				 
				 for(int a=0; a< 3; a++){					 
					 RGB[a] = rand.nextInt((max - min) + 1) + min;
				 }				
				 tempColor = new Color(RGB[0], RGB[1], RGB[2]);
				 
				 c[i] = tempColor;
				 if((255 - min) > 10){					 
					 min = min + 5;
				 }
			 }
		}
		
		for(int i = 0; i < imgHeight; i++){
			for(int j = 0; j < imgWeight; j++){
				for(int a = 0; a < numberOfCluster; a++){
					if(clusterGroup[j][i] == a){
						mainColoredImage.setRGB(j, i, c[a].getRGB());
					}
				}
			}
		}
		
		new WriteImage().Write(mainColoredImage, "src/K_Means_Clustering/Images/", "Kmean.jpg");
	}

	public int[] calculateNewCentroid(int [][] clusterGroup, int[] k, int[][] imageMat){
		
		int[][] countClusterSet = new int[k.length][2];
		
		for(int a = 0; a< k.length; a++){
			countClusterSet[a][0] = 0;
			countClusterSet[a][1] = 0;
		}
		
		for(int i = 0; i < imgHeight; i++){
			for(int j = 0; j < imgWeight; j++){
				for(int a = 0; a< k.length; a++)
				if(clusterGroup[j][i] == a){
					countClusterSet[a][0]++;
					countClusterSet[a][1] += imageMat[j][i];
				}
			}
		}
		
		for(int a = 0; a< k.length; a++){
			k[a] = countClusterSet[a][1] / countClusterSet[a][0];
		}
		
		return k;
	}
}

