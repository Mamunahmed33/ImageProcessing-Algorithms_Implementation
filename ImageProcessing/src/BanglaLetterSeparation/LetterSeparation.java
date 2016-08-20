package BanglaLetterSeparation;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LetterSeparation {
	int imgHeight, imgWidth;
	int [][] imgMat;
	int x1=300, y1=300, x2=0, y2=0, count=1;
	String fileSource = "src/BanglaLetterSeparation/Images/";
	
	BufferedImage img, mainImg, imgM;
	WriteImage writeImage = new WriteImage();
	
	public void count(BufferedImage img, int[][] imgMat) throws IOException{
		imgM = ImageIO.read(new File(fileSource + "i.jpg"));

		imgHeight = img.getHeight();
		imgWidth = img.getWidth();
		this.imgMat = imgMat;
		this.img = img;
		
		int [][] countBlackWhite= countBlackAndWhiteInALine();
		
		vanishMatra(countBlackWhite);
		
		for(int i = 0; i < imgHeight; i++ ){
			for(int j = 0; j < imgWidth ; j++){
				if(imgMat[j][i] == 1){
					
					if(imgMat[j][i] == 1){
						x1 = j; y1=i;
						findRegion(j, i);
						cropImage(x1, y1, x2-x1, y2-y1);
						x1=0; x2=0; y2=0; y1=0;
					}
				}
			}
		}
		 
		 writeImage.Write(img, fileSource, "Region.jpg");
	}
	
	public int[][] countBlackAndWhiteInALine(){
		int [][] countBlackWhite= new int[imgHeight][2];
		
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
			countBlackWhite[i][0] = white;
			countBlackWhite[i][1] = black;
			white= 0; black=0;
		}
		
		return countBlackWhite;
	}
	
	public void vanishMatra(int[][] countBlackWhite){
		Color c = new Color(0, 0, 0);
		for(int i = 0; i < imgHeight; i++ ){
			//	System.out.println(countBalckWhite[i][0] +"  "+ countBalckWhite[i][1]);
			if(countBlackWhite[i][0]> 80){
				for(int j = 0; j < imgWidth ; j++){
					img.setRGB(j, i, c.getRGB());
					imgMat[j][i] = 0;
				}
			}
		}
		
		writeImage.Write(img, fileSource, "WithoutMatra.jpg");
	}
	
	public void findRegion(int j, int i){
		Color c = new Color(255, 0, 0);
	
		if(i < imgHeight-1 && j <imgWidth-1){
		//	System.out.println(i+"  "+ j);
		
			if(imgMat[j-1][i-1] == 1){
				img.setRGB(j-1, i-1, c.getRGB());
				imgMat[j-1][i-1]++;
				findRegion(j-1, i-1);
				setMaxMinXY(j-1, i-1);
			}
			if(imgMat[j][i-1] == 1){
				img.setRGB(j, i-1, c.getRGB());
				imgMat[j][i-1]++;
				findRegion(j, i-1);
				setMaxMinXY(j, i-1);
			}
			if(imgMat[j+1][i-1] == 1){
				img.setRGB(j+1, i-1, c.getRGB());
				imgMat[j+1][i-1]++;
				findRegion(j+1, i-1);
				setMaxMinXY(j+1, i-1);
			}
			if(imgMat[j-1][i] == 1){
				img.setRGB(j-1, i, c.getRGB());
				imgMat[j-1][i]++;
				findRegion(j-1, i);
				setMaxMinXY(j-1, i);
			}
			if(imgMat[j][i] == 1){
				img.setRGB(j, i, c.getRGB());
				imgMat[j][i]++;
				findRegion(j, i);
				setMaxMinXY(j, i);
			}
			if(imgMat[j+1][i] == 1){
				img.setRGB(j+1, i, c.getRGB());
				imgMat[j+1][i]++;
				findRegion(j+1, i);
				setMaxMinXY(j+1, i);
			}
			if(imgMat[j-1][i+1] == 1){
				img.setRGB(j-1, i+1, c.getRGB());
				imgMat[j-1][i+1]++;
				findRegion(j-1, i+1);
				setMaxMinXY(j-1, i+1);
			}
			if(imgMat[j][i+1] == 1){
				img.setRGB(j, i+1, c.getRGB());
				imgMat[j][i+1]++;
				findRegion(j, i+1);
				setMaxMinXY(j, i+1);
			}
			if(imgMat[j+1][i+1] == 1){
				img.setRGB(j+1, i+1, c.getRGB());
				imgMat[j+1][i+1]++;
				findRegion(j+1, i+1);
				setMaxMinXY(j+1, i+1);
			}
		}
	}
	
	private void cropImage(int x, int y, int w, int h) {
		//System.out.println(x+ "  "+y+"  "+w+"  "+h+""+mainImg);
		 
		w = Math.abs(w);
		h = Math.abs(h);
		
		if(w >0 && h >0){
			BufferedImage dest = imgM.getSubimage(x-2, y-2, w+4, h+4);
			String s = "" + count + ".jpg";
			new WriteImage().Write(dest, fileSource, s);
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

