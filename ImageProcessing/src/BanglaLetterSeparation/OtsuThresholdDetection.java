package BanglaLetterSeparation;

import java.awt.image.BufferedImage;

public class OtsuThresholdDetection {
	private int imgHeight, imgWeight, threshold =0;
	
	public OtsuThresholdDetection(BufferedImage img, int[][] imageMat){
		this.imgHeight = img.getHeight();
		this.imgWeight = img.getWidth();
		
		int[] histogram = genereateImageHistogram(imageMat);
		
		thresholdCalculation(histogram);
	}
	
	public int thresholdCalculation(int[] histogram){
		
		/*for(int i=0; i<256; i++){
			System.out.println(histogram[i]);
		}*/
		
		double[] pi = new double [256];
		double mn = imgHeight * imgWeight;
		
		 for (int i = 0; i < 256; i++) {
	            pi[i] = histogram[i] / (double)mn;
	     }
		 
		 double P1=0, m1 =0, m2 =0 , newSigma=0, maxSigma = 0;
		 
         for (int k = 1; k < 255; k++) {
            for (int i = 0; i <= k; i++) {
                P1 += pi[i];
            }
            
            for (int i = 0; i <= k; i++) {
                m1 += i * pi[i];
            }
            
            if (P1 != 0)
                m1 = m1 / P1;

            for (int i = k+1; i <= 255; i++) {
                m2 += i * pi[i];
            }
            
            m2 = m2 / (1-P1);

            newSigma = P1 * (1-P1) * (m1 - m2) * (m1 - m2);

            if (newSigma > maxSigma) {
            	maxSigma = newSigma;
            	threshold = k;
            }

            P1 = 0; m1 = 0; m2 = 0;
         }
        
        // System.out.println(threshold);
         
        return threshold;
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
	
	public int getThreshold(){
		return threshold;
	}
}
