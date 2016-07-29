import java.awt.image.BufferedImage;

public class ConvertImageToMatrix {
	public double[][] imageToMatrix(BufferedImage img){
		
		int imgHeight = img.getHeight();
		int imgWidth = img.getWidth();
		
		//System.out.println(imgHeight + "    "+ imgWidth);
		
		double[][] matrix = new double[img.getHeight()][img.getWidth()];
		
		for(int row = 0; row < imgHeight; row++ ){
			for(int col = 0; col < imgWidth ; col++){
				matrix[row][col] = img.getRGB(row, col);
				
				/*Color c = new Color( img.getRGB(row, col));
				
				System.out.print(c.getRed() + " " + c.getGreen() + " " + c.getBlue() +"\n" );
				*/
			}
			//System.out.println("\n");
		}
		
		return matrix;
	}
}
