package NegativeImage;

import java.awt.Color;
import java.awt.image.BufferedImage;

import K_Means_Clustering.ConvertImageToGrayScale;

public class NegativeImage {
	public BufferedImage convertImageToNegative(BufferedImage img){
		
		ConvertImageToGrayScale convertImageToGrayScale = new ConvertImageToGrayScale();
		BufferedImage grayImage = convertImageToGrayScale.convetToGrayScale(img);
		int[][] imgMat = convertImageToGrayScale.getImageGrayScaleMatrix();
		
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		Color c = new Color(0, 0, 0); 
		int newRGBValue = 0;
		
		for(int col = 0; col < imgHeight; col++ ){
			for(int row = 0; row < imgWidth ; row++){
				newRGBValue = 255- imgMat[row][col];
				
				c = new Color(newRGBValue, newRGBValue, newRGBValue);
				
				img.setRGB(row, col, c.getRGB());
			}
		}
		
		return img;
	}
}
