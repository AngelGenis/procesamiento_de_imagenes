package Interfaz;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import Bloques.Chunck;
import TratamientoImagenes.ImageOperator;
import TratamientoImagenes.imagenes;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;

public class MainWindow extends JFrame {

	private static imagenes nuevaimg = new imagenes();
	protected static String foto2;
	protected static String foto1;
	protected static File archivoelegido;
	protected static File archivoelegido2;
	protected static File out;
	protected static Image imaa;
	
	private static  Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0,
		image.getBounds().width, image.getBounds().height,
		0, 0, width, height);
		gc.dispose();
		image.dispose(); 
		return scaled;
	}
	
	private static void colocarLabels(int col, int fil, Shell shell, Display display) {
		int labels = col*fil;
		int cordx=0;
		int cordy=0;
		
		int divisionx = 300/col;
		int divisiony = 300/fil;
		
		int iteracion = 0;
		
    	for(int i=0; i<labels; i++) {
    		System.out.println("Cordenada x: "+cordx+" cordenada y: "+cordy);
			Label lbl_img = new Label(shell, SWT.NONE);
			lbl_img.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
			lbl_img.setBounds(cordx+280, cordy+50, 30, 30);

			//cargar imagenes
			File imgcargada = new File("Fotos/Chuncks/output"+i+".png");

			Image imaa = new Image(display, imgcargada.getPath());
			lbl_img.setImage(imaa);
			
	    	iteracion ++;
			
	    	cordx+= (300/col)+3;
	  
	    	if(iteracion == col) {
	    		cordx = 0;
	    		cordy+=(300/col)+3;
	    		iteracion = 0;
	    	}
    	}

    }
		

	

	public static void main(String[] args) {
		
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(947, 479);
		shell.setText("SWT Application");
		Label lbl_output = new Label(shell, SWT.NONE);
		
		
		
		
		final Slider slider = new Slider(shell, SWT.HORIZONTAL);
      
        slider.setMinimum(0);
        slider.setMaximum(60);
        slider.setIncrement(6);
        slider.setSelection(6);
     
        slider.setBounds(330, 370, 200, 15);
        
        
       
       
        final Label text = new Label(shell, SWT.NONE);
        text.setBounds(415, 385, 286, 15);
        text.setText("Alpha: 6");
        
        slider.setVisible(false);
        text.setVisible(false);
      
       

	
		Label lbl_img1 = new Label(shell, SWT.NONE);
		lbl_img1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbl_img1.setBounds(27, 52, 300, 300);
		
		Button btn_chunck = new Button(shell, SWT.NONE);
		
		Label lbl_img2 = new Label(shell, SWT.NONE);
		lbl_img2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbl_img2.setBounds(546, 52, 300, 300);
		
		archivoelegido = new File("Fotos/mujerpintada.jpg");
		foto1 = archivoelegido.getPath();
		
		Image imagen1 = new Image(display, foto1);
        
	   	imagen1 = resize(imagen1, lbl_img1.getBounds().width, lbl_img1.getBounds().height);
	   	lbl_img1.setImage(imagen1);   
		
		
	    archivoelegido2 = new File("Fotos/piramide.jpg");
        foto2 = archivoelegido2.getPath();
		Image imagen2 = new Image(display, foto2);
        
	   	imagen2 = resize(imagen2, lbl_img2.getBounds().width, lbl_img2.getBounds().height);
	   	lbl_img2.setImage(imagen2);   
		
		
		lbl_output.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		lbl_output.setBounds(280, 50, 300, 300);
		lbl_output.setVisible(false);
		//Image imagen = new Image(display, "C:/Users/Angel/Documents/Fondos/imagen.jpg");
		//imagen = resize(imagen, lbl_output.getBounds().width, lbl_output.getBounds().height);
		//lbl_output.setImage(imagen);
		
		Combo combo_operaciones = new Combo(shell, SWT.NONE);
		combo_operaciones.setBounds(414, 195, 91, 23);
		
		combo_operaciones.add("+");
		combo_operaciones.add("-");
		combo_operaciones.add("*");
		combo_operaciones.add("#");
		
		
		
		Button btn_cargar_img2 = new Button(shell, SWT.NONE);
		btn_cargar_img2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 JFileChooser selector=new JFileChooser();
                 
                 int estado = selector.showOpenDialog(null);
                 archivoelegido2 = selector.getSelectedFile();
                 foto2 = archivoelegido2.getPath();
                 
               
                 
                 if(estado == JFileChooser.APPROVE_OPTION);
                 {
                	 Image imagen = new Image(display, foto2);
         
                	 imagen = resize(imagen, lbl_img2.getBounds().width, lbl_img2.getBounds().height);
                	 lbl_img2.setImage(imagen);   
                	 
                	 
                 }
			}
             
		});
		
		btn_cargar_img2.setBounds(654, 367, 133, 25);
		btn_cargar_img2.setText("Cargar imagen");
	
		Button btn_cargar_img1 = new Button(shell, SWT.NONE);
		btn_cargar_img1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 JFileChooser selector=new JFileChooser();
                 
                 int estado = selector.showOpenDialog(null);
                 archivoelegido = selector.getSelectedFile();
                 
                 System.out.println("archivo   : "+archivoelegido);
                 
                 foto1 = archivoelegido.getPath();
                 System.out.println("foto   : "+foto1);
             
                 if(estado == JFileChooser.APPROVE_OPTION){
                	 Image imagen = new Image(display, foto1);
                	 imagen = resize(imagen, lbl_img1.getBounds().width, lbl_img1.getBounds().height);
                	 lbl_img1.setImage(imagen);    				
                 }
			}
                              
		});
	
		btn_cargar_img1.setText("Cargar imagen");
		btn_cargar_img1.setBounds(135, 367, 133, 25);
		
		Button btn_operar = new Button(shell, SWT.NONE);
		
		Button btn_refresh = new Button(shell, SWT.NONE);
		btn_refresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lbl_img1.setVisible(true);
				lbl_img2.setVisible(true);
				btn_cargar_img1.setVisible(true);
				btn_cargar_img2.setVisible(true);
				btn_operar.setVisible(true);
				combo_operaciones.setVisible(true);
				btn_chunck.setVisible(false);
				
				
				lbl_output.setVisible(false);
				btn_refresh.setVisible(false);
				slider.setVisible(false);
		        text.setVisible(false);
		        
				
			}
   
		});
		
		btn_refresh.setBounds(10, 10, 100, 25);
		btn_refresh.setText("Atras");
		btn_refresh.setVisible(false);
		btn_chunck.setVisible(false);
	
		btn_operar.addSelectionListener(new SelectionAdapter() {
			private BufferedImage nuevaFoto;

			@Override
			public void widgetSelected(SelectionEvent e) {		
				
				String operador = combo_operaciones.getText();
		
				try {
					if(foto1.isEmpty() && foto2.isEmpty()) {
						System.out.println("error, Inserte una foto");
					}else {	
						Image imaa;
						if(operador.equals("+")) {
							System.out.print("Ejecutanto +");
							out = ImageOperator.operaciones("suma",archivoelegido, archivoelegido2,0);						
						}
						else if(operador.equals("-")) {
							System.out.print("Ejecutanto -");
							out = ImageOperator.operaciones("resta",archivoelegido, archivoelegido2,0);		
						}
						else if(operador.equals("*")){
							System.out.print("Ejecutanto *");
							out = ImageOperator.operaciones("multiplicacion",archivoelegido, archivoelegido2,0);							
						}
						else if(operador.equals("#")){
							System.out.print("Ejecutanto #");
							slider.setVisible(true);
					        text.setVisible(true);
							out = ImageOperator.operaciones("lineal", archivoelegido, archivoelegido2,6);	
							
						}
						
						lbl_img1.setVisible(false);
						lbl_img2.setVisible(false);
						btn_cargar_img1.setVisible(false);
						btn_cargar_img2.setVisible(false);
						btn_operar.setVisible(false);
						combo_operaciones.setVisible(false);
						
						
						lbl_output.setVisible(true);
						btn_refresh.setVisible(true);
						btn_chunck.setVisible(true);
								
						imaa = new Image(display, out.getPath());
						lbl_output.setImage(imaa);
					}
				}catch(Exception e1) {
					System.out.println("Something went wrong: "+ e1);
				};				
			}
		});
		
		btn_operar.setText("Operar");
		btn_operar.setBounds(393, 405, 133, 25);
		
		
		btn_chunck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					lbl_output.setVisible(false);
					slider.setVisible(false);
			        text.setVisible(false);
					Chunck.operaciones(out, 10, 10);
					MainWindow.colocarLabels(10, 10, shell, display);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
             
		});
		
		btn_chunck.setBounds(200, 10, 133, 25);
	    btn_chunck.setText("Chunck");
	    
	    slider.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
	            int outString = slider.getSelection();
	        
	            if(outString >=0 && outString<=10) {
	            	outString = 1;
	            }
	            if(outString >10 && outString<=15) {
	            	outString = 2;
	            }
	            if(outString >15 && outString<=20) {
	            	outString = 3;
	            }
	            if(outString >20 && outString<=25) {
	            	outString = 4;
	            }
	            if(outString >25 && outString<=30) {
	            	outString = 5;
	            }
	            if(outString >30 && outString<=35) {
	            	outString = 6;
	            }
	            if(outString >35 && outString<=40) {
	            	outString = 7;
	            }
	            if(outString >40 && outString<=45) {
	            	outString = 8;
	            }
	            if(outString >45 && outString<=50) {
	            	outString = 9;
	            }
  
	            try {
					out = ImageOperator.operaciones("lineal", archivoelegido, archivoelegido2, outString);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
	            
	            imaa = new Image(display, out.getPath());
				lbl_output.setImage(imaa);
				
				
	            text.setText("Alpha: " + Integer.toString(outString));        
			}
        });
	    
	 

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}	
	}
}
