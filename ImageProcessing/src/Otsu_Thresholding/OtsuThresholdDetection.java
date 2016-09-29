package Otsu_Thresholding;

import java.awt.image.BufferedImage;

public class OtsuThresholdDetection {
	private int imgHeight, imgWeight;
	
	public OtsuThresholdDetection(BufferedImage img, int[][] imageMat){
		this.imgHeight = img.getHeight();
		this.imgWeight = img.getWidth();
		
		int[] histogram = genereateImageHistogram(imageMat);
		
		for(int i=0; i<256; i++){
			System.out.println(histogram[i]);
		}
	}

	private int[] genereateImageHistogram(int[][] imageMatrix) {
		int[] histogram = new int[256];
		
		for(int i=0; i<256; i++){
			histogram[i] = 0;
		}	
		
		for(int i = 0; i < imgHeight; i++){
			for(int j = 0; j < imgWeight; j++){
				int colorIntesity = imageMatrix[j][i];
				histogram[colorIntesity]++;
			}
		}
		
		return histogram;
	}
}
