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

package cuConfiguracion;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import persistencia.Persistencia;
import persistencia.dominio.BasicVet;
import clasesComunes.ControlBasicVet;



public class MediadorPanelBasicVet implements ActionListener{
	private Persistencia persistencia;
	private PanelBasicVet panelBasicVet;
	public static String BASE = (new File("")).getAbsolutePath();
	private String PATH_IMAGE = BASE + "/src/imagenes/";
	public static final String SUN_JAVA_COMMAND = "sun.java.command";
	private ControlBasicVet controlBasicVet;
	private String pathImagen; 

	public MediadorPanelBasicVet(PanelBasicVet panelBV){
		super();
		this.panelBasicVet = panelBV;
		inicializar();
	}

	private void inicializar(){
		this.persistencia = new Persistencia();
		this.controlBasicVet = new ControlBasicVet();
		try{
			persistencia.iniciarTransaccion();
			int existe = controlBasicVet.existeBasicVet("1");
			if(existe==1){
				BasicVet basicVet = controlBasicVet.obtenerBasicVet("1");
				this.panelBasicVet.getCampoNombreVeterinaria().setText(basicVet.getNombre());
				this.panelBasicVet.getCampoDireccion().setText(basicVet.getDireccion());
				this.panelBasicVet.getCampoTelefono().setText(basicVet.getTelefono()+"");
				this.panelBasicVet.getCampoCuil().setText(basicVet.getCuil());
				this.panelBasicVet.getCampoTelEmer().setText(basicVet.getTelefonoEmergencia());
				this.panelBasicVet.getCampoEmail().setText(basicVet.getEmail());
				this.panelBasicVet.getCampoWeb().setText(basicVet.getPageWeb());
				this.pathImagen = basicVet.getPath();
				this.panelBasicVet.getLabelImagen().setIcon(new ImageIcon(PATH_IMAGE+"imagenVeterinaria.jpg"));
			}
			persistencia.concretarTransaccion();
			this.panelBasicVet.setListenerButtons(this);
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void actionPerformed(ActionEvent e){
		if(this.panelBasicVet.getBotonBuscarArchivo()==e.getSource()){
			this.opcionBuscarArchivo();
		}
		if(this.panelBasicVet.getBotonAplicar()==e.getSource()){
			this.opcionAplicar();
		}
	}

	private void opcionBuscarArchivo(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File((new File("")).getAbsolutePath()+"/src/imagenes/imgVet"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG/GIF/PNG", "jpg", "gif","png");
		fileChooser.setFileFilter(filter);
		int seleccion = fileChooser.showOpenDialog(this.panelBasicVet);
		if(seleccion == JFileChooser.APPROVE_OPTION){
		   File fichero = fileChooser.getSelectedFile();
		   this.pathImagen = fichero.getPath();
		   ImageIcon imagenIcon = new ImageIcon(fichero.getPath());
		   this.panelBasicVet.getLabelImagen().setIcon(new ImageIcon((imagenIcon.getImage()).getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
		}
	}
	
	private void opcionAplicar(){
		File fichero = new File(pathImagen);
		ImageIcon imagenIcon = new ImageIcon(fichero.getPath());
		String extension = (String)pathImagen.subSequence(pathImagen.length()-3, pathImagen.length());
		BufferedImage bufferedImage = new BufferedImage(150,150,Transparency.OPAQUE);
		Graphics g = bufferedImage.getGraphics();
		g.drawImage(new ImageIcon((imagenIcon.getImage()).getScaledInstance(150, 150, Image.SCALE_DEFAULT)).getImage(), 0, 0, null);
		try{
			String nuevoPath = PATH_IMAGE+"imagenVeterinaria"+".jpg";
			ImageIO.write(bufferedImage,extension,new File(nuevoPath));
			persistencia.iniciarTransaccion();
			BasicVet basicVet = controlBasicVet.obtenerBasicVet("1");
			basicVet.setPath(nuevoPath);;
			basicVet.setNombre(this.panelBasicVet.getCampoNombreVeterinaria().getText());
			basicVet.setDireccion(this.panelBasicVet.getCampoDireccion().getText());
			basicVet.setTelefono(this.panelBasicVet.getCampoTelefono().getText());
			basicVet.setCuil(this.panelBasicVet.getCampoCuil().getText());
			basicVet.setTelefonoEmergencia(this.panelBasicVet.getCampoTelEmer().getText());
			basicVet.setEmail(this.panelBasicVet.getCampoEmail().getText());
			basicVet.setPageWeb(this.panelBasicVet.getCampoWeb().getText());
			persistencia.concretarTransaccion();
			JOptionPane.showMessageDialog(null, "DEBE REINICIAR EL PROGRAMA PARA QUE LOS CAMBIOS TENGAN EFECTOS");
		}catch(Exception e1){
			System.out.println("Error "+this.getClass().toString());
			e1.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
}
