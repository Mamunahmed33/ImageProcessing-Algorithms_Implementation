import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SobelEdge {
	public static void main(String[] args) {
		BufferedImage  image;
	   int width;
	   int height;
	   
		File input = new File("src/Images/img_7.jpg");
        try {
			image = ImageIO.read(input);
			width = image.getWidth();
	        height = image.getHeight();
	        int mat[][] = new int[height][width];
	        int edgeMat[][] = new int[height][width];
	        
	        for(int i=0; i<height; i++){
	           for(int j=0; j<width; j++){
	              Color c = new Color(image.getRGB(j, i));
	              int red = (int)(c.getRed());
	              int green = (int)(c.getGreen());
	              int blue = (int)(c.getBlue());
	              
	              mat[i][j] = (red+green+blue)/3;
	              
	              edgeMat[i][j]=0;
	           }
	        }
	        
	        int[][] maskx = new int[][]{
	        		{-1,-2,-1},
	        		{0,0,0},
	        		{1,2,1}
	        };
	        
	        edgeMat = edgeDetection(mat,maskx, height, width);
            
            for(int i=0; i<height; i++){
    	        
 	           for(int j=0; j<width; j++){
 	           
 	        	  Color newColor = new Color(edgeMat[i][j],edgeMat[i][j],edgeMat[i][j]);
 	             
 	             image.setRGB(j,i,newColor.getRGB());
 	              
 	           }
 	        }
	        
            outputImage("src/Images/edgeX", image);
	        
	        int[][] masky = new int[][]{
	        		{-1,0,1},
	        		{-2,0,2},
	        		{-1,0,1}
	        };
	        
	        edgeMat = edgeDetection(mat,masky, height, width);
	            
            for(int i=0; i<height; i++){
    	        
 	           for(int j=0; j<width; j++){
 	           
 	        	 Color newColor = new Color(edgeMat[i][j],edgeMat[i][j],edgeMat[i][j]);
 	             
 	             image.setRGB(j,i,newColor.getRGB());
 	              
 	           }
 	        }
		        
            outputImage("edgeY", image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	private static int[][] edgeDetection(int[][] mat, int[][] mask, int height, int width) {
		int [][] edgeMat = new int[height][width];
		for(int i=1; i<height-1; i++){
	        
	           for(int j=1; j<width-1; j++){
	        	   
	        	 edgeMat[i][j] += mat[i-1][j-1]*mask[0][0];
	        	 edgeMat[i][j] += mat[i][j-1]*mask[1][0];
	        	 edgeMat[i][j] += mat[i+1][j-1]*mask[2][0];
	        	 edgeMat[i][j] += mat[i-1][j]*mask[0][1];
	        	 edgeMat[i][j] += mat[i][j]*mask[1][1];
	        	 edgeMat[i][j] += mat[i+1][j]*mask[2][1];
	        	 edgeMat[i][j] += mat[i-1][j+1]*mask[0][2];
	        	 edgeMat[i][j] += mat[i][j+1]*mask[1][2];
	        	 edgeMat[i][j] += mat[i+1][j+1]*mask[2][2];
	        	 
	        	 if(edgeMat[i][j]>255){
	        		 edgeMat[i][j] = 255;
	        	 }
	        	 
	        	 if(edgeMat[i][j]<0){
	        		edgeMat[i][j] = 0;
	        	 }
	           }
	        }
		return edgeMat;
	}
	
	private static void outputImage(String name, BufferedImage image) throws IOException {
		File ouptut = new File(name+".jpg");
        ImageIO.write(image, "jpg", ouptut);
	}
}
