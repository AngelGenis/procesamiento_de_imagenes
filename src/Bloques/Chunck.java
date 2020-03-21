package Bloques;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Chunck {
	public static BufferedImage leerImagen(File img) throws IOException {
		 BufferedImage image = ImageIO.read(img); 
		
		return image;
	}
	
	public static void operaciones(File imgn, int col, int fil) throws IOException {
		//Standard height and size of image
		int width=300;
		int height=300;
		
	 	int tamAn = width/col;
	 	int tamAlt = height/fil;

	 	int iniAlt=0;
	 	int iniAn=0;
	 	
	 	int finAlt = tamAlt;
	 	int finAn = tamAn;
	 	
	 	int fotos = col*fil;
	 
	 	BufferedImage newImage = leerImagen(imgn);
	 	
	 	int i=0;
	 	int cont = 0;
	 	
	 	for(int j=0; j<fotos; j++) {
	 			BufferedImage output = new BufferedImage(tamAn, tamAlt, BufferedImage.TYPE_INT_ARGB);
	 			
	 			for(int x=iniAn; x<finAn; x++) {
	 				for(int y=iniAlt; y<finAlt; y++) {
	 				
	 					int rgb_imagen = newImage.getRGB(x,y);	
	 					output.setRGB(x%tamAn, y%tamAlt, rgb_imagen);
					
		 			}
	 			}		
	 			
	 			File outputfile = new File ("C:/Users/Angel/Desktop/ImagenesJava/output"+i+".png");
	 			
	 			i++;
	 			cont++;
	 			
	 			iniAn+=tamAn;
	 			finAn+=tamAn;
	 			iniAn%=width;
	 			
	 			if(iniAn==0)
	 				finAn%=height;

	 			if((j+1)%col==0) {
	 				iniAlt+=tamAlt;
		 			finAlt+=tamAlt;
	 				cont = 0;
	 			}

	 			try {
	 				ImageIO.write(output, "png", outputfile);
	 				
	 			} catch (IOException e) {
	 				// TODO Auto-generated catch block
	 				System.out.println("error: "+ e);
	 				e.printStackTrace();	
	 			}
	 			
	 			//---------------------------------------
		 	}
	 		
	}
}
