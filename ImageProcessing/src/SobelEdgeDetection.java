import java.awt.image.BufferedImage;

public class SobelEdgeDetection {
	BufferedImage img, finalImg;
	
	public SobelEdgeDetection(){
		
	} 
	
	public void EdgeDetection(BufferedImage img){
		this.img = img;
		int[][] xMatrix = xConvolution();
		int[][] yMatrix = yConvolution();
		
		finalImage(xMatrix, yMatrix);
	}
	
	public int[][] xConvolution(){
		BufferedImage image1 = null;
		int height = img.getHeight();
		int width = img.getWidth();
		
		int[][] xMatrix = new int[height-1][width-1]; 
		
		image1 = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		int x = 0;
		
		int [][] xMask = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}}; 
		
		//System.out.println(height + " " + width);
		
		for(int i = 1; i < height - 1; i++)
		{
			for(int j = 1; j < width - 1; j++)
			{
				x = xMask[0][0] * (img.getRGB(j-1,i-1) & 0xFF)+ xMask[0][1] * (img.getRGB(j, i-1)& 0xFF)+ xMask[0][2] * (img.getRGB(j+1, i-1)& 0xFF)
					+ xMask[1][0] * (img.getRGB(j-1, i) & 0xFF)+ xMask[1][1] * (img.getRGB(j, i) & 0xFF)+ xMask[1][2] * (img.getRGB(j+1, i)& 0xFF)
					+ xMask[2][0] * (img.getRGB(j-1, i+1)& 0xFF) + xMask[2][1] * (img.getRGB(j, i+1)& 0xFF)+ xMask[2][2] * (img.getRGB(j+1, i+1)& 0xFF);
			
				if(x > 255)
				{
					x = 255;
				}
				else if(x < 0)
				{
					x = 0;
				}
				
				xMatrix[i][j] = x;
				image1.setRGB(j, i, x);
			}
		}
		
		new WriteImage().Write(image1, "src/Images/", "xConvolution.jpg");
		
		return xMatrix;
	}
	
	public int[][] yConvolution(){
		BufferedImage image2 = null;
		int height = img.getHeight();
		int width = img.getWidth();
		int[][] yMatrix = new int[height-1][width-1]; 
		
		image2 = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		int y = 0;
		
		int [][] yMask = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}}; 		
		//System.out.println(height + " " + width);
		
		for(int i = 1; i < height - 1; i++)
		{
			for(int j = 1; j < width - 1; j++)
			{
				y = yMask[0][0] * (img.getRGB(j-1, i-1)& 0xFF) + yMask[0][1] * (img.getRGB(j, i-1)  & 0xFF)+ yMask[0][2] * (img.getRGB(j+1, i-1) & 0xFF)
					+ yMask[1][0] * (img.getRGB(j-1, i) & 0xFF) + yMask[1][1] * (img.getRGB(j, i) & 0xFF)+ yMask[1][2] * (img.getRGB(j+1, i) & 0xFF)
					+ yMask[2][0] * (img.getRGB(j-1, i+1) & 0xFF) + yMask[2][1] * (img.getRGB(j, i+1) & 0xFF)+ yMask[2][2] * (img.getRGB(j+1, i+1) & 0xFF);
				
				if(y > 255)
				{
					y = 255;
				}
				else if(y < 0)
				{
					y = 0;
				}
				
				yMatrix[i][j] = y;
				image2.setRGB(j, i, y);
			}
		}
		
		new WriteImage().Write(image2, "src/Images/", "yConvolution.jpg");
		
		return yMatrix;
	}
	
	public void finalImage(int[][] xMatrix, int[][] yMatrix){
		int height = img.getHeight();
		int width = img.getWidth();
		int M, Mx, My;
		BufferedImage finalImg = new BufferedImage( width-1, height-1, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int i = 1; i < height-1; i++)
		{
			for(int j = 1; j < width-1; j++)
			{
				Mx = xMatrix[i][j];
				My = yMatrix[i][j];
				M = (int) Math.sqrt(Mx*Mx + My*My);
				
				if(M > 255){
					M = 255;
				}
				else if(M < 0){
					M = 0;
				}
				
				finalImg.setRGB(j, i, M);
			}
		}
		
		new WriteImage().Write(finalImg, "src/Images/", "finalImg.jpg");
	}
	
	public BufferedImage getSobelImage(){
		return finalImg;
	}
}
