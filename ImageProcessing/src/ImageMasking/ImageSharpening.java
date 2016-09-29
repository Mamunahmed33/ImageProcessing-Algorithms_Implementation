package ImageMasking;

import java.awt.Color;
import java.awt.image.BufferedImage;

import SobelEdgeDetection.WriteImage;

public class ImageSharpening {
private int imgHeight, imgWeight;
	
	public ImageSharpening(BufferedImage img, int[][] imageMat){
		this.imgHeight = img.getHeight();
		this.imgWeight = img.getWidth();
		
		laplasianFilter1(imageMat);
	}

	private void laplasianFilter1(int[][] imageMat) {
		int[][] lap1Matrix = new int[imgWeight][imgHeight];
		
		/*for(int i = 1; i < imgHeight; i++)
		{
			for(int j = 1; j < imgWeight; j++)
			{
				
			}
		}*/
		
		BufferedImage image2 = new BufferedImage(imgWeight, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
		
		int[][] lap = {{-1,-1,-1},
							{-1,9,-1},
							{-1,-1,-1}};
		
		for(int i = 1; i < imgHeight - 1; i++)
		{
			int x = 0;
			for(int j = 1; j < imgWeight - 1; j++)
			{
				x = lap[0][0] * imageMat[j-1][i-1] + lap[0][1] * imageMat[j][i-1]+ lap[0][2] * imageMat[j+1][i-1]
					+ lap[1][0] * imageMat[j-1][i]+ lap[1][1] * imageMat[j][i]+ lap[1][2] * imageMat[j+1][i]
					+ lap[2][0] * imageMat[j-1][i+1]+ lap[2][1] * imageMat[j][i+1]+ lap[2][2] * imageMat[j+1][i+1];
				
				x = Math.abs(x);
				
				if(x > 255)
				{
					x = 0;
				}
				else if(x < 0)
				{
					x = 255;
				}
				
				lap1Matrix[j][i] = x;
				Color c = new Color(x, x ,x);
				
				image2.setRGB(j, i, c.getRGB());
			}
		}
		
		lapMatrixAddWithOriginalImage(lap1Matrix, imageMat);
		
		new WriteImage().Write(image2, "src/ImageMasking/Images/", "lap1.jpg");
	}
	
public void laplasianFilter2(int[][] imageMat) {
		
		BufferedImage image3 = new BufferedImage(imgWeight, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
		
		int[][] lap = {{-1,-1,-1},
							{-1,10,-1},
							{-1,-1,-1}};
		
		for(int i = 1; i < imgHeight - 1; i++)
		{
			int x = 0;
			for(int j = 1; j < imgWeight - 1; j++)
			{
				x = lap[0][0] * imageMat[j-1][i-1] + lap[0][1] * imageMat[j][i-1]+ lap[0][2] * imageMat[j+1][i-1]
					+ lap[1][0] * imageMat[j-1][i]+ lap[1][1] * imageMat[j][i]+ lap[1][2] * imageMat[j+1][i]
					+ lap[2][0] * imageMat[j-1][i+1]+ lap[2][1] * imageMat[j][i+1]+ lap[2][2] * imageMat[j+1][i+1];
				
				x = Math.abs(x);
				
				if(x > 255)
				{
					x = 0;
				}
				else if(x < 0)
				{
					x = 255;
				}
				
				Color c = new Color(x, x ,x);
				
				image3.setRGB(j, i, c.getRGB());
			}
		}
		
		new WriteImage().Write(image3, "src/ImageMasking/Images/", "lap2.jpg");
	}

public void laplasianFilter3(int[][] imageMat) {
	
	BufferedImage image4 = new BufferedImage(imgWeight, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
	
	int[][] lap = {{0,-1,0},
						{-1,5,-1},
						{0,-1,0}};
	
	for(int i = 1; i < imgHeight - 1; i++)
	{
		int x = 0;
		for(int j = 1; j < imgWeight - 1; j++)
		{
			x = lap[0][0] * imageMat[j-1][i-1] + lap[0][1] * imageMat[j][i-1]+ lap[0][2] * imageMat[j+1][i-1]
				+ lap[1][0] * imageMat[j-1][i]+ lap[1][1] * imageMat[j][i]+ lap[1][2] * imageMat[j+1][i]
				+ lap[2][0] * imageMat[j-1][i+1]+ lap[2][1] * imageMat[j][i+1]+ lap[2][2] * imageMat[j+1][i+1];
			
			x = Math.abs(x);
			
			if(x > 255)
			{
				x = 0;
			}
			else if(x < 0)
			{
				x = 255;
			}
			
			Color c = new Color(x, x ,x);
			
			image4.setRGB(j, i, c.getRGB());
		}
	}
	
	new WriteImage().Write(image4, "src/ImageMasking/Images/", "lap2.jpg");
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
				
				if(x > 255)
				{
					x = 255;
				}
				else if(x < 0)
				{
					x = 255;
				}
				
				Color c = new Color(x, x ,x);
				
				image4.setRGB(j, i, c.getRGB());
			}
		}
		
		new WriteImage().Write(image4, "src/ImageMasking/Images/", "addlap2.jpg");
	}
}
