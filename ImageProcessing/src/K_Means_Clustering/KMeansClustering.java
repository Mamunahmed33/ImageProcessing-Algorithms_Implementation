package K_Means_Clustering;

import java.awt.image.BufferedImage;

public class KMeansClustering {
	private int imgHeight, imgWeight;
	
	public void KMeansClustering(BufferedImage img, int[][] imageMat){
		this.imgHeight = img.getHeight();
		this.imgWeight = img.getWidth();
		
	/*	for(int i=0; i< imgHeight; i++){
			for(int j=0; j< imgWeight; j++){
				System.out.print(imageMat[j][i]+" ");  
			}
			
			System.out.println();
		}*/
		
		int [] K = {40, 60, 90, 150};
		
		clusterPixels(K, imageMat);
	}
	
	public void clusterPixels(int[] k, int[][] imageMat){
		
		int distance = 500;
		
		int[][] clusterGroup = new int[imgWeight][imgHeight];
		
		for(int l = 0; l<10; l++){ // 5 loop for cluster 
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
			
		}
	}
	
	public int[] calculateNewCentroid(int [][] clusterGroup, int[] k, int[][] imageMat){
		
		int[][] countClusterSet = new int[k.length][2];
		
		for(int a = 0; a< k.length; a++){
			countClusterSet[a][0] = 0;
			countClusterSet[a][1] = 0;
			System.out.print(k[a]+" ");
		}
		System.out.println();
		
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
			
			System.out.print(k[a]+" ");
		}
		System.out.println("\n");
		
		return k;
	}
}

