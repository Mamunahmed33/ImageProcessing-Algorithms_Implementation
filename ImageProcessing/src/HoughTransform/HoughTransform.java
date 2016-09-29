package HoughTransform;
import java.awt.Color;
import java.awt.image.BufferedImage;

import HarrisCornerDetection.WriteImage;
/*
	Input: Sobel Image, The original Colored image
	Output: A JPG image with detected line
	Process: 1. Takes the sovel image and the original colored image
			 2. Gets the grayscale matrix of that image by calling ConvertImageToMatrix class
			 3. Detects(red color) the maximum line from the image
*/
public class HoughTransform {
	BufferedImage coloredImage;
	private WriteImage writeImage;
	
	public HoughTransform(){
		writeImage = WriteImage.getInstance();
	}
	
	public void LineDetection(BufferedImage img){
		int height = img.getHeight();
		int width = img.getWidth();
		
		int [][] imgMat = new ConvertImageToGrayScaleMatrix().imageToMatrix(img);
		
		int r = (int) Math.sqrt(height * height + width * width);
		
		int[][] houghMat = new int [r][180];
		
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int i=0; i< r; i++){
			for(int j =0; j< 180; j++)
				houghMat[i][j] = 0;
		}
		
		int rTemp = 0;
		
		for(int i=0; i< height; i++){
			for(int j =0; j< width; j++){
				if(imgMat[j][i] > 200){
					for(int theta = 0; theta < 180 ; theta += 5){
						rTemp = (int) (i* Math.sin(Math.toRadians(theta)) + j* Math.cos(Math.toRadians(theta)));
						if(rTemp > 0 && rTemp < r){
							houghMat[rTemp][theta] +=1 ; 
						}
					}
				}
			}
		}
		
		int max =0, R =0, Theta =0;
		
		for(int i=0; i< r; i++){
			for(int j =0; j< 180; j++){
				if(houghMat[i][j] > max){
					max = houghMat[i][j];
					R = i;
					Theta = j;
				}
			}
		}
		
		for(int i=0; i< height; i++){
			for(int j =0; j< width; j++){
				if(imgMat[j][i] > 200){
					
					int tempR = (int) (j*Math.cos(Math.toRadians(Theta))+ i*Math.sin(Math.toRadians(Theta)));
					
					if(tempR == R){
						Color c = new Color(255, 0 , 0);
						coloredImage.setRGB(j, i, c.getRGB());
					}
				}
			}
		}
		
		writeImage.Write(coloredImage, "src/HoughTransform/HoughTransformImages/", "MostVottedLine.jpg");
	}
	
	public void setColoredImage(BufferedImage image){
		this.coloredImage = image;
	}
}
