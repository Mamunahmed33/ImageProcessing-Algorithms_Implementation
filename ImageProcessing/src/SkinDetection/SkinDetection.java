package SkinDetection;

import java.awt.Color;
import java.awt.image.BufferedImage;

import K_Means_Clustering.WriteImage;

public class SkinDetection {
	BufferedImage img;
	private int imgHeight, imgWeight;
	
	public SkinDetection(BufferedImage img){
		this.img = img;
		this.imgHeight = img.getHeight();
		this.imgWeight = img.getWidth();
		skin();
	}
	
	public void skin(){
		
		int minRGB=500, maxRGB=0;
		int [] rgb = new int[3];
		Color c;
		Color newColor = new Color(255,0,0);
		Color bColor = new Color(0,0,0);
		
		for(int i = 0; i < imgHeight; i++){
			for(int j = 0; j < imgWeight; j++){
				c = new Color(img.getRGB(j, i));
				rgb[0] = c.getRed();
				rgb[1] = c.getGreen();
				rgb[2] = c.getBlue();
				
				if(rgb[0]> 95 && rgb[1]>40 && rgb[2] > 20){
					
					for(int k=0; k<3 ; k++){
						if(rgb[k] < minRGB){
							minRGB = rgb[k];
						}
						if(rgb[k] > maxRGB){
							maxRGB = rgb[k];
						}
					}
					
					int RminusG = Math.abs(rgb[0] - rgb[1]);
					if(maxRGB - minRGB > 15){
						if(RminusG > 15 && rgb[0] > rgb[1] && rgb[0]> rgb[2])
						img.setRGB(j, i, newColor.getRGB());
					}
					
					minRGB =0; 
					maxRGB = 0;
				}
				else{
					img.setRGB(j, i, bColor.getRGB());
				}
			}
		}
		new WriteImage().Write(img, "src/SkinDetection/Images/", "skin.jpg");
	}
}
