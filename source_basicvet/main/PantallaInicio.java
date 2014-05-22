/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/

package main;
  
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

import cuGestionarLogin.MediadorLogin;

@SuppressWarnings({"serial","unused"})  
public class PantallaInicio extends JWindow {
	
	private BorderLayout borderLayout1 = new BorderLayout();
	private JLabel imageLabel = new JLabel();
	private JLabel mensaje = new JLabel("Cargando");
	private JPanel southPanel = new JPanel();
	private FlowLayout southPanelFlowLayout = new FlowLayout();
	private JProgressBar progressBar = new JProgressBar();
	private ImageIcon imageCarga;
   
  
  public PantallaInicio() {
	this.imageCarga =  new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png"));
    ventanaCargando();
  }

  public void ventanaCargando() {
    this.imageLabel.setIcon(imageCarga);
    this.getContentPane().setLayout(borderLayout1);
    this.setSize(400, 200);
    this.southPanel.setBackground(Color.BLACK);
    this.mensaje.setForeground(Color.white);
    this.getContentPane().add(imageLabel, BorderLayout.CENTER);
    this.getContentPane().add(southPanel, BorderLayout.SOUTH);
    this.southPanel.add(progressBar, null);
    this.southPanel.add(mensaje,null);
    this.pack();
  }

  public void setProgresoMax(int maxProgress){
	  progressBar.setMaximum(maxProgress);
  }

  public void setProgreso(int progress){
	  final int progreso = progress;
	  progressBar.setValue(progreso);
  }

  public void setProgreso(String message, int progress){
	  final int progreso = progress;
	  final String theMessage = message;
	  setProgreso(progress);
	  progressBar.setValue(progreso);
	  setMessage(theMessage);
  }

  private void setMessage(String message){
    if (message==null){
    	message = "";
    	progressBar.setStringPainted(false);
    }else progressBar.setStringPainted(true);
    progressBar.setString(message); 
  }


  	public void velocidadDeCarga(){
  		for (int i = 0; i <= 100; i++){
  			if (i == 1){
  				this.imageLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png")));
  				mensaje.setText("Cargando...                		(Creando Reportes)");
  				try{Thread.sleep(200);}catch (InterruptedException ie){System.out.println(ie.getMessage());}
  			}
  			if (i == 11) {
  				this.imageLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png")));
  				mensaje.setText("Cargando...                		(Creando Reportes)");
  				try{Thread.sleep(200);}catch (InterruptedException ie){System.out.println(ie.getMessage());}
  			}
  			if (i == 21) {
  				this.imageLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png")));
  				mensaje.setText("Cargando...                		(Creando Reportes)");
  				try{Thread.sleep(200);}catch (InterruptedException ie){System.out.println(ie.getMessage());}
  			}
  			if (i == 31) {
  				this.imageLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png")));
  				mensaje.setText("Cargando...                		(Creando Reportes)");
  				try{Thread.sleep(200);}catch (InterruptedException ie){System.out.println(ie.getMessage());}
  			}
  			if (i == 51) {
  				this.imageLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png")));
  				mensaje.setText("Cargando...                		(Comprobando base de datos)");
  				try{Thread.sleep(200);}catch (InterruptedException ie){System.out.println(ie.getMessage());}
  			}      
  			if (i == 61) {	
  				this.imageLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png")));
  				mensaje.setText("Cargando...                		(Comprobando base de datos)");
  				try{Thread.sleep(200);}catch (InterruptedException ie){System.out.println(ie.getMessage());}
  			}
  			if (i == 81) {
  				this.imageLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png")));
  				mensaje.setText("Cargando...                		(Inicializando interfaz grafica)");
  			}
  			if (i == 85) {
  				this.imageLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png")));
  				mensaje.setText("Cargando...                		(Inicializando interfaz grafica)");
  				try{Thread.sleep(200);}catch (InterruptedException ie){System.out.println(ie.getMessage());}
  			}
  			if (i == 100) {
  				this.imageLabel.setIcon(new ImageIcon(getClass().getResource("/imagenes/fondoPanel.png")));
  				mensaje.setText("Completado...");
  				MediadorLogin medLogin = new MediadorLogin();
  			}
  			setProgreso("Cargando BasicVet " + i+"%", i);
  		}
  		dispose();
   }

}