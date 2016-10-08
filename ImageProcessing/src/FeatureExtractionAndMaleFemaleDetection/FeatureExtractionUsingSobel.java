package FeatureExtractionAndMaleFemaleDetection;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import HarrisCornerDetection.WriteImage;

/*
Input: Colored Image
Output: XConvolition Image, YConvolution Image, Final Image
Process: 1. Takes a colored image 
		 2. Gets the grayscale matrix of that image by calling ConvertImageGrayScaleToMatrix class
		 3. Calculates xConvolution and yConvolution
		 4. Calculates magnitude from xConvolution and yConvolution
		 5. Calculates Directions from the x and y convolution
		 5. Generates Histogram
*/

public class FeatureExtractionUsingSobel {
	BufferedImage img, finalImg;
	int[][] imageMatrix;
	int height, width;
	private WriteImage writeImage;
	String imgFeatures;
	
	public FeatureExtractionUsingSobel(){
		writeImage = WriteImage.getInstance();
	} 
	
	public void EdgeDetection(BufferedImage img){

		BufferedImage[] smallImg= cropImage(img);
		
		imgFeatures = "1 "; //Set 1 for Male and 2 for Female
		imageMatrix = new ConvertImageToGrayScaleMatrix().imageToMatrix(img);

		for(int i=0; i< 8; i++){
			this.img = smallImg[i];
			this.height = img.getHeight();
			this.width = img.getWidth();

			int[][] xMatrix = xConvolution();
			int[][] yMatrix = yConvolution();
			
			int[][] magnitude = calculateMagnitude(xMatrix, yMatrix);

			int[][] directions = calculateDirection(xMatrix, yMatrix);
			generateHistogram(directions, magnitude);
		}
		
		writeInFile(imgFeatures);
		imgFeatures = "";
	}
	
	public int[][] xConvolution(){
		int[][] xMatrix = new int[width][height]; 
		
		BufferedImage image1 = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		int x = 0;
		
		int [][] xMask = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}}; 
		
		for(int i = 1; i < height - 1; i++)
		{
			for(int j = 1; j < width - 1; j++)
			{
				x = xMask[0][0] * imageMatrix[j-1][i-1] + xMask[0][1] * imageMatrix[j][i-1]+ xMask[0][2] * imageMatrix[j+1][i-1]
					+ xMask[1][0] * imageMatrix[j-1][i]+ xMask[1][1] * imageMatrix[j][i]+ xMask[1][2] * imageMatrix[j+1][i]
					+ xMask[2][0] * imageMatrix[j-1][i+1]+ xMask[2][1] * imageMatrix[j][i+1]+ xMask[2][2] * imageMatrix[j+1][i+1];
			
				x = Math.abs(x);
				
				x = pixelValueChecker(x);
				
				xMatrix[j][i] = x;
				Color c = new Color(x, x ,x);
				
				image1.setRGB(j, i, c.getRGB());
			}
		}
		
		//writeImage.Write(image1, "src/FeatureExtractionAndMaleFemaleDetection/Images/", "xConvolution.jpg");
		
		return xMatrix;
	}
	
	public int[][] yConvolution(){
		
		int[][] yMatrix = new int[width][height]; 
		
		BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		int y = 0;
		
		int [][] yMask = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}}; 		
		
		for(int i = 1; i < height - 1; i++)
		{
			for(int j = 1; j < width - 1; j++)
			{
				y = yMask[0][0] * imageMatrix[j-1][i-1] + yMask[0][1] * imageMatrix[j][i-1]+ yMask[0][2] * imageMatrix[j+1][i-1]
					+ yMask[1][0] * imageMatrix[j-1][i] + yMask[1][1] * imageMatrix[j][i]+ yMask[1][2] * imageMatrix[j+1][i]
					+ yMask[2][0] * imageMatrix[j-1][i+1] + yMask[2][1] * imageMatrix[j][i+1]+ yMask[2][2] * imageMatrix[j+1][i+1];
				
				y = Math.abs(y);
				
				y = pixelValueChecker(y);
				
				yMatrix[j][i] = y;
				
				Color c = new Color(y, y ,y);
				image2.setRGB(j, i, c.getRGB());
			}
		}
		
		//writeImage.Write(image2, "src/FeatureExtractionAndMaleFemaleDetection/Images/", "yConvolution.jpg");
		
		return yMatrix;
	}
	
	private int pixelValueChecker(int value){
		if(value > 255)
			value = 255;
		else if(value < 0)
			value = 0;
		
		return value;
	}
	
	public int[][] calculateMagnitude(int[][] xMatrix, int[][] yMatrix){
		int M, Mx, My;
		int[][] magnitude = new int[width][height];
		finalImg = new BufferedImage( width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int i = 1; i < height-1; i++)
		{
			for(int j = 1; j < width-1; j++)
			{
				Mx = xMatrix[j][i];
				My = yMatrix[j][i];
				M = (int) Math.sqrt(Mx*Mx + My*My);
				
				M = pixelValueChecker(M);
				magnitude[j][i] = M;
				
				Color c = new Color(M, M, M);
				
				finalImg.setRGB(j, i, c.getRGB());
			}
		}
		
		//writeImage.Write(finalImg, "D:\\LFW GENDER\\SobelImage\\", "FinalImg.jpg");
		
		return magnitude;
	}
	
	public int[][] calculateDirection(int[][] x, int[][] y){
		
		int direction[][] = new int[width][height];
		for(int i = 1; i < height-1; i++)
		{
			for(int j = 1; j < width-1; j++)
			{
				int yTemp = y[j][i], xTemp = x[j][i];
				
				if(xTemp !=0){					
					direction[j][i] = (int) Math.toDegrees(Math.atan(yTemp/xTemp)) + 90;
				}
				else{
					direction[j][i] = 0;
				}
			}
		}
		
		return direction;
	}

	public void generateHistogram(int[][] directions, int[][] magnitude){
		int[] histogram = new int[18];
		
		for(int i = 1; i < height - 1; i++)
		{
			for(int j = 1; j < width - 1; j++)
			{
				int segment = directions[j][i] / 10;
				histogram[segment] += magnitude[j][i];
			}
		}
		
		for(int i=0; i< 18 ; i++){
			imgFeatures += ""+ histogram[i]+" ";
		}
	}

	public BufferedImage[] cropImage(BufferedImage img){
		BufferedImage[] smallImages = new BufferedImage[8];

		int x = 0;
		int y = 0;
		int w = img.getWidth()/8;
		int h = img.getHeight()/8;

		for(int i=0; i<8 ; i++){
			smallImages[i] = img.getSubimage(x, y, w, h);
			x+=w;
			y+=y;
		}

		return smallImages;
	}
	
	public void writeInFile(String features){
		try(FileWriter fw = new FileWriter("D:\\LFW GENDER\\FeatureFile.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(imgFeatures);
			} catch (IOException e) {
				System.err.println("IOException: " + e.getMessage());
			}
	}
}
