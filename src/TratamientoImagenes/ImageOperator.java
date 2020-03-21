package TratamientoImagenes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

public class ImageOperator {
	
	
	public static BufferedImage leerImagen(File img) throws IOException {
		 BufferedImage image = ImageIO.read(img); 
		
		return image;
	}
	
	public static File operaciones(String operacion, File imgn, File imgn2) throws IOException {
		BufferedImage newImage = leerImagen(imgn);
	 	BufferedImage newImage2 = leerImagen(imgn2);
	 	BufferedImage output = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
	 	
		for(int x=0; x<300; x++) {
			for(int y=0; y<300; y++){			
				int rgb_imagen1 = newImage.getRGB(x, y);
				int rgb_imagen2 = newImage2.getRGB(x, y);
		
				Color color_imagen1 = new Color(rgb_imagen1);
				Color color_imagen2 = new Color(rgb_imagen2);
		
				int rojo_img1 = color_imagen1.getRed();
				int verde_img1 = color_imagen1.getGreen();
				int azul_img1  = color_imagen1.getBlue();
				
				int rojo_img2 = color_imagen2.getRed();
				int verde_img2 = color_imagen2.getGreen();
				int azul_img2  = color_imagen2.getBlue();
				
				int opR = 0, opV = 0, opA = 0;
				
				if(operacion == "suma") {
					opR = rojo_img1 + rojo_img2;
					opV = verde_img1 + verde_img2;
					opA = azul_img1 + azul_img2;
				
					if(opR>254) {
						opR=255;
					}
					if(opV>254) {
						opV = 254;
					}
					if(opA>254) {
						opA = 254;
					}
				}
				
				if(operacion == "resta") {
					opR = rojo_img1 - rojo_img2;
					opV = verde_img1 - verde_img2;
					opA = azul_img1 - azul_img2;
				
					if(opR<0) {
						opR=0;
					}
					if(opV<0) {
						opV = 0;
					}
					if(opA<0) {
						opA = 0;
					}	
				}
				if(operacion == "multiplicacion") {	
					double div = 0.00392156863;
					
					opR = (int) (div * rojo_img1 * rojo_img2);
					opV = (int) (div * verde_img1 * verde_img2);
					opA = (int) (div * azul_img1 * azul_img2);
				}
				
				if(operacion == "lineal") {
					opR = (int) Math.round((0.6 * rojo_img1) + (0.4 * rojo_img2));
					opV = (int) Math.round((0.6 * verde_img1) + (0.4 * verde_img2));
					opA = (int) Math.round((0.6 * azul_img1) + (0.4 * azul_img2));
				}
					
				Color newPixelColor = new Color(opR, opV, opA);

				int nuevoRGB = newPixelColor.getRGB();
				
				output.setRGB(x, y, nuevoRGB);		
			}	
		}
		
		File outputfile = new File ("C:/Users/Angel/Desktop/ImagenesJava/output.png");
		
		try {
			ImageIO.write(output, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error: "+ e);
			e.printStackTrace();	
		}
		
		return outputfile;
	}
	
	
 }
