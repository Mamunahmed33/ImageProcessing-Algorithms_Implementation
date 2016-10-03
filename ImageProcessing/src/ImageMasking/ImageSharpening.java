package ImageMasking;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageSharpening {
private int imgHeight, imgWeight;
	
	public ImageSharpening(BufferedImage img, int[][] imageMat){
		this.imgHeight = img.getHeight();
		this.imgWeight = img.getWidth();
		
		int counter = 1;
		
		int[][] lap1 = {{-1,-1,-1},
						{-1,9,-1},
						{-1,-1,-1}};
		
		int[][] lap2 = {{-1,-1,-1},
						{-1,10,-1},
						{-1,-1,-1}};
		
		int[][] lap3 = {{0,-1,0},
						{-1,5,-1},
						{0,-1,0}};
		
		int[][] averageMean = {{1,1,1,1,1},
								{1,1,1,1,1},
								{1,1,1,1,1},
								{1,1,1,1,1},
								{1,1,1,1,1}};
		
		laplasianFilter1(imageMat, lap1, counter);
		++counter;
		laplasianFilter1(imageMat, lap2, counter);
		++counter;
		laplasianFilter1(imageMat, lap3, counter);
		++counter;
		averageMean(imageMat, averageMean, counter);
	}

	private void laplasianFilter1(int[][] imageMat, int[][] lap, int counter) {
		int[][] lap1Matrix = new int[imgWeight][imgHeight];
		
		BufferedImage image2 = new BufferedImage(imgWeight, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int i = 1; i < imgHeight - 1; i++)
		{
			int x = 0;
			for(int j = 1; j < imgWeight - 1; j++)
			{
				x = lap[0][0] * imageMat[j-1][i-1] + lap[0][1] * imageMat[j][i-1]+ lap[0][2] * imageMat[j+1][i-1]
					+ lap[1][0] * imageMat[j-1][i]+ lap[1][1] * imageMat[j][i]+ lap[1][2] * imageMat[j+1][i]
					+ lap[2][0] * imageMat[j-1][i+1]+ lap[2][1] * imageMat[j][i+1]+ lap[2][2] * imageMat[j+1][i+1];
				
				x = Math.abs(x);
				
				x = imageValueChecker(x);
				
				lap1Matrix[j][i] = x;
				Color c = new Color(x, x ,x);
				
				image2.setRGB(j, i, c.getRGB());
			}
		}
		
		lapMatrixAddWithOriginalImage(lap1Matrix, imageMat);
		
		String outputFileName = "lap"+ counter+ ".jpg"; 
		
		new WriteImage().Write(image2, "src/ImageMasking/Images/", outputFileName);
	}
	
	private void averageMean(int[][] imageMat, int[][] lap, int counter) {
		int[][] lap1Matrix = new int[imgWeight][imgHeight];
		
		int h = lap.length;
		
		System.out.println(h);
		
		BufferedImage image2 = new BufferedImage(imgWeight, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int i = 1; i < imgHeight - 1; i++)
		{
			int x = 0;
			for(int j = 1; j < imgWeight - 1; j++)
			{
				for(int k=0; k< h; k++){
					for(int l=0; l< h; l++){
						x += lap[l][k] * imageMat[j-1][i-1];
					}
				}
				
				x = lap[0][0] * imageMat[j-1][i-1] + lap[0][1] * imageMat[j][i-1]+ lap[0][2] * imageMat[j+1][i-1]
					+ lap[1][0] * imageMat[j-1][i]+ lap[1][1] * imageMat[j][i]+ lap[1][2] * imageMat[j+1][i]
					+ lap[2][0] * imageMat[j-1][i+1]+ lap[2][1] * imageMat[j][i+1]+ lap[2][2] * imageMat[j+1][i+1];
				
				x = Math.abs(x);
				
				x = imageValueChecker(x);
				
				lap1Matrix[j][i] = x;
				Color c = new Color(x, x ,x);
				
				image2.setRGB(j, i, c.getRGB());
			}
		}
		
		lapMatrixAddWithOriginalImage(lap1Matrix, imageMat);
		
		String outputFileName = "lap"+ counter+ ".jpg"; 
		
		new WriteImage().Write(image2, "src/ImageMasking/Images/", outputFileName);
	}
	
	private void lapMatrixAddWithOriginalImage(int[][] lapMat, int[][] imgMat){
		int[][] lap1Matrix = new int[imgWeight][imgHeight];
		BufferedImage image4 = new BufferedImage(imgWeight, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int i = 2; i < imgHeight - 2; i++)
		{
			int x = 0;
			for(int j = 2; j < imgWeight - 2; j++)
			{
				x = lapMat[j][i]+imgMat[j][i];
				
				x = imageValueChecker(x);
				
				Color c = new Color(x, x ,x);
				
				image4.setRGB(j, i, c.getRGB());
			}
		}
		
		new WriteImage().Write(image4, "src/ImageMasking/Images/", "addlap2.jpg");
	}
	
	private int imageValueChecker(int value){
		if(value > 255)
			value = 255;
		else if(value < 0)
			value = 0;
		
		return value;
	}
}
