package LetterSeparation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.javafx.geom.Rectangle;

public class CountPixelColor {
	int imgHeight, imgWidth;
	int [][] imgMat;
	BufferedImage img, mainImg,imgM;
	int x1=300, y1=300, x2=0, y2=0, count=1;
	
	public void count(BufferedImage img, int[][] imgMat) throws IOException{
		imgM = ImageIO.read(new File("src/Images/i.jpg"));

		imgHeight = img.getHeight();
		imgWidth = img.getWidth();
		this.imgMat = imgMat;
		this.img = img;
		
		int [][] countBalckWhite= new int[imgHeight][2];
		
		int black =0, white =0;
		for(int i = 0; i < imgHeight; i++ ){
			for(int j = 0; j < imgWidth ; j++){
			
				 if(imgMat[j][i] == 1){
					 white++;
				 }
				 else{
					 black++;
				 }
			}
			countBalckWhite[i][0] = white;
			countBalckWhite[i][1] = black;
			white= 0; black=0;
		}
		
		for(int i = 0; i < imgHeight; i++ ){
		//	System.out.println(countBalckWhite[i][0] +"  "+ countBalckWhite[i][1]);
			
			if(countBalckWhite[i][0]> 80){
					for(int j = 0; j < imgWidth ; j++){
						Color c = new Color(0, 0, 0);
						img.setRGB(j, i, c.getRGB());
						imgMat[j][i] = 0;
					}
			}
		}
		
			new WriteImage().Write(img, "src/Images/", "WithoutMatra.jpg");
		 
		 
		 for(int i = 0; i < imgHeight; i++ ){
			for(int j = 0; j < imgWidth ; j++){
				if(imgMat[j][i] == 1){
					
					if(imgMat[j][i] == 1){
						x1 = j; y1=i;
						checkConnected(j, i);
						cropImage(x1, y1, x2-x1, y2-y1);
						x1=0; x2=0; y2=0; y1=0;
					}
				}
			}
		}
		 new WriteImage().Write(img, "src/Images/", "Region.jpg");
	}
	
	public void checkConnected(int j, int i){
		Color c = new Color(255, 0, 0);
	
		if(i < imgHeight-1 && j <imgWidth-1){
		//	System.out.println(i+"  "+ j);
		
			if(imgMat[j-1][i-1] == 1){
				img.setRGB(j-1, i-1, c.getRGB());
				imgMat[j-1][i-1]++;
				checkConnected(j-1, i-1);
				setMaxMinXY(j-1, i-1);
			}
			if(imgMat[j][i-1] == 1){
				img.setRGB(j, i-1, c.getRGB());
				imgMat[j][i-1]++;
				checkConnected(j, i-1);
				setMaxMinXY(j, i-1);
			}
			if(imgMat[j+1][i-1] == 1){
				img.setRGB(j+1, i-1, c.getRGB());
				imgMat[j+1][i-1]++;
				checkConnected(j+1, i-1);
				setMaxMinXY(j+1, i-1);
			}
			if(imgMat[j-1][i] == 1){
				img.setRGB(j-1, i, c.getRGB());
				imgMat[j-1][i]++;
				checkConnected(j-1, i);
				setMaxMinXY(j-1, i);
			}
			if(imgMat[j][i] == 1){
				img.setRGB(j, i, c.getRGB());
				imgMat[j][i]++;
				checkConnected(j, i);
				setMaxMinXY(j, i);
			}
			if(imgMat[j+1][i] == 1){
				img.setRGB(j+1, i, c.getRGB());
				imgMat[j+1][i]++;
				checkConnected(j+1, i);
				setMaxMinXY(j+1, i);
			}
			if(imgMat[j-1][i+1] == 1){
				img.setRGB(j-1, i+1, c.getRGB());
				imgMat[j-1][i+1]++;
				checkConnected(j-1, i+1);
				setMaxMinXY(j-1, i+1);
			}
			if(imgMat[j][i+1] == 1){
				img.setRGB(j, i+1, c.getRGB());
				imgMat[j][i+1]++;
				checkConnected(j, i+1);
				setMaxMinXY(j, i+1);
			}
			if(imgMat[j+1][i+1] == 1){
				img.setRGB(j+1, i+1, c.getRGB());
				imgMat[j+1][i+1]++;
				checkConnected(j+1, i+1);
				setMaxMinXY(j+1, i+1);
			}
		}
	}
	
	private void cropImage(int x, int y,int w,int h) {
		//System.out.println(x+ "  "+y+"  "+w+"  "+h+""+mainImg);
		 
		if(w >0 && h >0){
	      BufferedImage dest = imgM.getSubimage(x-2, y-2, w+4, h+4);
	      String s = "a"+count;
	      s = s+".jpg";
	      new WriteImage().Write(dest, "src/Images/", s);
		}
		
		count++;
	}
	
	public void setMaxMinXY(int a, int b){
	//	System.out.println(a+"  "+b);
		if(x1 > a){
			x1 = a;
		}
		if(a > x2){
			x2 = a;
		}
		if(b < y1){
			y1 = b;
		}
		 if(b > y2){
			y2 = b;
		}
		//System.out.println(x1+ "  "+x2+"  "+y1+"  "+y2);
	}
	
	public void setMainImage(BufferedImage src){
		mainImg = src;
	}
}

