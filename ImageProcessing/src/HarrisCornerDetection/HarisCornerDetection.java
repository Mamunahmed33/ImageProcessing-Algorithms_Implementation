package HarrisCornerDetection;

import java.awt.Color;
import java.awt.image.BufferedImage;

import HoughTransform.WriteImage;

/*
Input: Colored Image
Output: XConvolition Image, YConvolution Image, Final Image
Process: 1. Takes a colored image 
		 2. Gets the grayscale matrix of that image by calling ConvertImageToMatrix class
		 3. Calculates xConvolution and yConvolution
		 4. Calculates magnitude from xConvolution and yConvolution and output final image
*/

public class HarisCornerDetection {
	BufferedImage img, finalImg;
	int[][] imageMatrix;
	int height =0, width =0;
	
	public HarisCornerDetection(){
		
	} 
	
	public void EdgeDetection(BufferedImage img){
		this.height = img.getHeight();
		this.width = img.getWidth();
		this.img = img;
		imageMatrix = new ConvertImageToGrayScaleMatrix().imageToMatrix(img);
		int[][] xMatrix = xConvolution();
		int[][] yMatrix = yConvolution();
		
		finalImage(xMatrix, yMatrix);
		
		computeDerivative(xMatrix, yMatrix);
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
				if(x > 255)
				{
					x = 255;
				}
				else if(x < 0)
				{
					x = 0;
				}
				xMatrix[j][i] = x;
				
				//System.out.print(xMatrix[j][i]+" ");
				
				Color c = new Color(x, x ,x);
				
				image1.setRGB(j, i, c.getRGB());
			}
		}
		
		new WriteImage().Write(image1, "src/HarrisCornerDetection/HarrisImages/", "xConvolution.jpg");
		
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
				
				if(y > 255)
				{
					y = 255;
				}
				else if(y < 0)
				{
					y = 0;
				}
				
				yMatrix[j][i] = y;
				
				Color c = new Color(y, y ,y);
				image2.setRGB(j, i, c.getRGB());
			}
		}
		
		new WriteImage().Write(image2, "src/HarrisCornerDetection/HarrisImages/", "yConvolution.jpg");
		
		return yMatrix;
	}
	
	public void computeDerivative(int[][] xMatrix, int[][] yMatrix){
		int [][] xyMatrix = new int[width][height];
		int [][] xxMatrix = new int[width][height];
		int [][] yyMatrix = new int[width][height];
		
		for(int i = 1; i < height-2; i++)
		{
			for(int j = 1; j < width-2; j++)
			{
				//System.out.print(yMatrix[j][i]+"  ");
				xxMatrix[j][i] = xMatrix[j][i] * xMatrix[j][i];
				yyMatrix[j][i] = yMatrix[j][i] * yMatrix[j][i];
				xyMatrix[j][i] = xMatrix[j][i] * yMatrix[j][i];
			}
			//System.out.println();
		}
		
		int[][] gMat = {{1,4,7,4,1},
					   {4,16,26,16,4},
					   {7,26,41,26,7},
					   {4,16,26,16,4},
					   {1,4,7,4,1}};
		
		for(int i = 2; i < height-4; i++)
		{
			for(int j = 2; j < width-4; j++)
			{
				int m = i-2, temp1=0, temp2=0, temp3=0;
				for(int k=0; k < 5; k++){
					int n = j-2;
					for(int l=0; l < 5; l++){
						//System.out.print(gMat[k][l] +" "+ xxMatrix[m][n]+" ");
						temp1 += (gMat[k][l]* xxMatrix[m][n]);
						temp2 += gMat[k][l]* yyMatrix[m][n];
						temp3 += gMat[k][l]* xyMatrix[m][n];
					}


					//System.out.println();
				}
				
				//System.out.println();
				temp1 = temp1 / 273;
				temp2 = temp2 / 273;
				temp3 = temp3 / 273;
				
				if (temp1 > 255)
					temp1 = 255;
				else if (temp1 < 0)
					temp1 = 0;
				
				xxMatrix[j][i] = temp1;
				if (temp2 > 255)
					temp2 = 255;
				else if (temp2 < 0)
					temp2 = 0;
				
				yyMatrix[j][i] = temp2;
				
				if (temp3 > 255)
					temp3 = 255;
				else if (temp3 < 0)
					temp3 = 0;
				
				xyMatrix[j][i] = temp3;
			}
		}
		
		int H, trM, R;
		
		BufferedImage HarrisImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		Color w =new Color(255,255,255);
		Color b =new Color(0,0,0);
		
		for(int i = 2; i < height-2; i++)
		{
			for(int j = 2; j < width-2; j++)
			{
				H = (xxMatrix[j][i] * yyMatrix[j][i]) - (xyMatrix[j][i] * xyMatrix[j][i]);
				trM = xxMatrix[j][i] + yyMatrix[j][i];
				R = (int) (H - (.4 * (trM * trM)));
				
				//System.out.print(R+" ");
				if (R < -100000)
				{
					//System.out.print(j +" "+ i+" ");
					HarrisImage.setRGB(j, i, w.getRGB());
				}
				else{
					HarrisImage.setRGB(j, i, b.getRGB());
				}
			}
			//System.out.println();
		}
		//System.out.print(xxMatrix[5][7] +" "+" ");
		
		new WriteImage().Write(HarrisImage, "src/HarrisCornerDetection/HarrisImages/", "HarrisImage.jpg");
	}
	
	public void finalImage(int[][] xMatrix, int[][] yMatrix){
		int M, Mx, My;
		finalImg = new BufferedImage( width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int i = 1; i < height-1; i++)
		{
			for(int j = 1; j < width-1; j++)
			{
				Mx = xMatrix[j][i];
				My = yMatrix[j][i];
				M = (int) Math.sqrt(Mx*Mx + My*My);
				
				if(M > 255){
					M = 255;
				}
				else if(M < 0){
					M = 0;
				}
				
				Color c = new Color(M, M, M);
				
				finalImg.setRGB(j, i, c.getRGB());
			}
		}
		
		new WriteImage().Write(finalImg, "src/HarrisCornerDetection/HarrisImages/", "FinalImage.jpg");
	}
}
