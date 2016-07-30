import java.awt.image.BufferedImage;

public class SobelEdgeDetection {
	
	public BufferedImage EdgeDetection(BufferedImage img){
		
		int height = img.getHeight();
		int width = img.getWidth();
		
		int x = 0, y =0, magnitude;
		
		int [][] xMask = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}}; 
		int [][] yMask = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}}; 
		
		System.out.println(height + " " + width);
		
		for(int i = 1; i < height - 2; i++)
		{
			for(int j = 1; j < width - 2; j++)
			{
			//	System.out.println(img.getRGB(j-1,i-1) & 0xFF);
				x = xMask[0][0] * (img.getRGB(j-1,i-1) & 0xFF)+ xMask[0][1] * (img.getRGB(j, i-1)& 0xFF)+ xMask[0][2] * (img.getRGB(j+1, i-1)& 0xFF)
					+ xMask[1][0] * (img.getRGB(j-1, i) & 0xFF)+ xMask[1][1] * (img.getRGB(j, i) & 0xFF)+ xMask[1][2] * (img.getRGB(j+1, i)& 0xFF)
					+ xMask[2][0] * (img.getRGB(j-1, i+1)& 0xFF) + xMask[2][1] * (img.getRGB(j, i+1)& 0xFF)+ xMask[2][2] * (img.getRGB(j+1, i+1)& 0xFF);
			
				y = yMask[0][0] * (img.getRGB(j-1, i-1)& 0xFF) + yMask[0][1] * (img.getRGB(j, i-1)  & 0xFF)+ yMask[0][2] * (img.getRGB(j+1, i-1) & 0xFF)
					+ yMask[1][0] * (img.getRGB(j-1, i) & 0xFF) + yMask[1][1] * (img.getRGB(j, i) & 0xFF)+ yMask[1][2] * (img.getRGB(j+1, i) & 0xFF)
					+ yMask[2][0] * (img.getRGB(j-1, i+1) & 0xFF) + yMask[2][1] * (img.getRGB(j, i+1) & 0xFF)+ yMask[2][2] * (img.getRGB(j+1, i+1) & 0xFF);
				
				magnitude = (int) Math.sqrt(x*x + y*y);
				
/*				if(magnitude > 150){
					magnitude = 255;
				}
				else if(magnitude < 100)
				{
					magnitude = 0;
				}
*/				
				//System.out.println(x + " " + y + " " +magnitude);
				
				img.setRGB(i, j, magnitude);
				System.out.println(i + "   " + j);
			}
		}
		
		new WriteImage().Write(img, "src/Images/", "sobel_1.jpg");
		
		return img;
	}
}
