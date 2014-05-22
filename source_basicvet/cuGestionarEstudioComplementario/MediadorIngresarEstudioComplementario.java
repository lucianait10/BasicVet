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

package cuGestionarEstudioComplementario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.EstudioComplementario;
import persistencia.dominio.FichaClinica;
import persistencia.dominio.TipoEstudio;
import clasesComunes.ControlEstudioComplementario;
import clasesComunes.ControlTipoEstudio;
import cuGestionarFichaClinica.ControlFichaClinica;

public class MediadorIngresarEstudioComplementario implements ActionListener{
	private GUIEstudioComplementario guiEstComp;
	private Persistencia persistencia;
	private ControlEstudioComplementario controlEstComp;
	private ControlFichaClinica controlFichaClinica;
	private JFileChooser fileChooserOpen;
	private String fileImagePath;
	private Date fecha = new Date();
	private long idFichaClinica;
	private DefaultTableModel tablaModelo;
	private ControlTipoEstudio controlTipoEstudio;
	private GUIGestionarEstudioComplementario guiGes;
	
	public MediadorIngresarEstudioComplementario(GUIGestionarEstudioComplementario guiGestionarEstudio, long id, DefaultTableModel tablaModelo){
		this.guiGes = guiGestionarEstudio;
		this.tablaModelo = tablaModelo;
		this.idFichaClinica = id;
		inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiEstComp.show();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializar(){
		String separador = System.getProperty("file.separator");
		//String so = System.getProperty("os.name");
		this.guiEstComp = new GUIEstudioComplementario("C:"+separador+"logo.jpg",guiGes);
		persistencia = new Persistencia();
		controlEstComp = new ControlEstudioComplementario();
		controlTipoEstudio = new ControlTipoEstudio();
		controlFichaClinica = new ControlFichaClinica();
		fileImagePath = (new File("")).getAbsolutePath()+separador+"src"+separador+"imagenes"+separador+"fondo.png";
		this.guiEstComp.setListenerButtons(this);
		this.guiEstComp.getBotonModificar().setVisible(false);
		this.guiEstComp.getCampoFecha().setText(fecha .getDate()+"/"+(fecha.getMonth()+1)+"/"+(fecha.getYear()+1900));
		this.cargarEstudios();
	}

	
	public void actionPerformed(ActionEvent e){
		if(this.guiEstComp.getBotonAgregar()==e.getSource()){
			opcionAgregarEstudio();
		}
		if(this.guiEstComp.getBotonBusqueda()==e.getSource()){
			opcionBuscarImagen();
		}		
		if(this.guiEstComp.getBotonAgregarTipo()==e.getSource()){
			this.agregarTipoEstudio();
		}
	}

	private void opcionBuscarImagen() {
		if (fileChooserOpen == null){
			fileChooserOpen = new JFileChooser();
		}
		File pad = new File("");
	    fileChooserOpen.setCurrentDirectory(pad);
	    fileChooserOpen.setDialogTitle("Seleccionar Imagen Estudio Complementario");
		int retVal = fileChooserOpen.showOpenDialog(fileChooserOpen);
		if (retVal == JFileChooser.APPROVE_OPTION)
			fileImagePath = fileChooserOpen.getSelectedFile().getAbsolutePath();
		this.guiEstComp.setImagenPanel(fileImagePath);
	}

	@SuppressWarnings("deprecation")
	private void opcionAgregarEstudio() {
		try{
			String separador = System.getProperty("file.separator");
			FileInputStream fis = new FileInputStream(fileImagePath); //inFile -> Archivo a copiar
			
			String nomStr = fileImagePath.substring(fileImagePath.lastIndexOf(separador) + 1, fileImagePath.length());
			fileImagePath =(new File("")).getAbsolutePath()+separador+"src"+separador+"imagenes"+separador+"imgEstudios"+separador+idFichaClinica+"-"+nomStr;
			//System.out.println(fileImagePath+" img path");
			FileOutputStream fos = new FileOutputStream(fileImagePath); //outFile -> Copia del archivo
			FileChannel inChannel = fis.getChannel();
			FileChannel outChannel = fos.getChannel();
			inChannel.transferTo(0, inChannel.size(), outChannel);
			fis.close();
			fos.close();
			try{
				persistencia.iniciarTransaccion();
				String tipoEstudio = this.guiEstComp.getCampoTipo().getSelectedItem().toString().toLowerCase();
				if(tipoEstudio.length()>0){
					Vector<String> vecComentario = new Vector<String>();
					vecComentario.add(this.guiEstComp.getCampoArea().getText());
					EstudioComplementario estComp = new EstudioComplementario(fecha,vecComentario,fileImagePath,tipoEstudio);
					controlEstComp.guardarEstudioComplementario(estComp);
					FichaClinica fichaClinica = controlFichaClinica.obtenerFichaClinica(idFichaClinica+"");
					fichaClinica.agregarEstudioComplementario(estComp);
					String fechaStr = fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+(fecha.getYear()+1900);
					this.tablaModelo.addRow(new String[]{estComp.getId()+"",tipoEstudio.toUpperCase(),fechaStr});
					persistencia.concretarTransaccion();
					this.guiEstComp.setVisible(false);
					this.guiEstComp.dispose();
				}else{
					JOptionPane.showMessageDialog(this.guiEstComp,	"SELECCIONE EL TIPO DEL ESTUDIO COMPLEMENTARIO");
				}
				
			}
			catch(Exception e){
				System.out.println("Error "+this.getClass().toString());
				e.printStackTrace();
				persistencia.deshacerTransaccion();
			}
		}catch (IOException ioe){
			ioe.printStackTrace();
			JOptionPane.showMessageDialog(this.guiEstComp,	"ERROR AL COPIAR LOS DATOS");
		}
	}
	
	public void cargarEstudios(){
		try{
			persistencia.iniciarTransaccion();
			Iterator<TipoEstudio> iterEstudios = controlTipoEstudio.obtenerTipoEstudios();
			while(iterEstudios.hasNext()){
				TipoEstudio tipoEstudio = iterEstudios.next();
				this.guiEstComp.getCampoTipo().addItem(tipoEstudio.getTipo().toUpperCase());
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void agregarTipoEstudio(){
		String nuevoEstudio = JOptionPane.showInputDialog("INGRESE EL NUEVO TIPO DE ESTUDIO");
		try{
			if(nuevoEstudio.length()>0){
				persistencia.iniciarTransaccion();
				int existeTipo = controlTipoEstudio.existeTipoEstudio(nuevoEstudio.toLowerCase());
				if(existeTipo!=1){
					TipoEstudio tipoEstudio = new TipoEstudio(nuevoEstudio.toLowerCase());
					controlTipoEstudio.guardarTipoEstudio(tipoEstudio);
					this.guiEstComp.getCampoTipo().addItem(nuevoEstudio.toUpperCase());
					persistencia.concretarTransaccion();
				}else{
					JOptionPane.showMessageDialog(this.guiEstComp,"EL TIPO DE ESTUDIO YA EXISTE");
				}
				
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
}
