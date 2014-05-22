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
import persistencia.dominio.TipoEstudio;
import clasesComunes.ControlEstudioComplementario;
import clasesComunes.ControlTipoEstudio;

public class MediadorModificarEstudioComplementario implements ActionListener {
	private GUIEstudioComplementario guiEstudioComplementario;
	private Persistencia persistencia;
	private ControlEstudioComplementario controlEstudioComplementario;
	private long idFicha;
	private DefaultTableModel tablaModelo;
	private long idEstudio;
	private JFileChooser fileChooserOpen;
	private String fileImagePath;
	private int fila;
	private ControlTipoEstudio controlTipoEstudio;
	private GUIGestionarEstudioComplementario gesEst;
	
	public MediadorModificarEstudioComplementario(long idFicha,long idEstudio, int filaVista, DefaultTableModel tablaModelo, GUIGestionarEstudioComplementario guiGesEstComp) {
		this.gesEst = guiGesEstComp;
		this.idFicha = idFicha;
		this.idEstudio = idEstudio;
		this.fila = filaVista;
		this.tablaModelo = tablaModelo;
		inicializar();
	}	

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiEstudioComplementario.show();
	}

	@SuppressWarnings("deprecation")
	private void inicializar(){
		guiEstudioComplementario = new GUIEstudioComplementario(idFicha+"",gesEst);
		guiEstudioComplementario.setListenerButtons(this);
		guiEstudioComplementario.getBotonAgregar().setVisible(false);
		persistencia = new Persistencia();
		try{
			persistencia.iniciarTransaccion();
			controlTipoEstudio = new ControlTipoEstudio();
			controlEstudioComplementario = new ControlEstudioComplementario();
			EstudioComplementario estComp = controlEstudioComplementario.obtenerEstudioComplementario(idEstudio);
			Date fecha = estComp.getFecha();
			String fechaStr = fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+(fecha.getYear()+1900);
			this.guiEstudioComplementario.getCampoFecha().setText(fechaStr);
			this.guiEstudioComplementario.getCampoArea().setText(estComp.getInforme().firstElement());
			this.guiEstudioComplementario.getCampoTipo().setSelectedItem(estComp.getTipo().toUpperCase());
			this.guiEstudioComplementario.setImagenPanel(estComp.getPath());
			fileImagePath = estComp.getPath();
			persistencia.concretarTransaccion();
			this.cargarEstudios();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void actionPerformed(ActionEvent e){
		if(this.guiEstudioComplementario.getBotonModificar()==e.getSource()){
			opcionModificar();
		}
		if(this.guiEstudioComplementario.getBotonBusqueda()==e.getSource()){
			opcionBusquedaImagen();
		}
		if(this.guiEstudioComplementario.getBotonAgregarTipo()==e.getSource()){
			this.agregarTipoEstudio();
		}
	}

	private void opcionBusquedaImagen(){
		if (fileChooserOpen == null){
			fileChooserOpen = new JFileChooser();
		}
		File pad = new File("");
	    fileChooserOpen.setCurrentDirectory(pad);
	    fileChooserOpen.setDialogTitle("Seleccionar Imagen Estudio Complementario");
		int retVal = fileChooserOpen.showOpenDialog(fileChooserOpen);
		if (retVal == JFileChooser.APPROVE_OPTION)
			fileImagePath = fileChooserOpen.getSelectedFile().getAbsolutePath();
		this.guiEstudioComplementario.setImagenPanel(fileImagePath);	
	}

	@SuppressWarnings("deprecation")
	private void opcionModificar(){
		try{
			String separador = System.getProperty("file.separator");
			FileInputStream fis = new FileInputStream(fileImagePath); //inFile -> Archivo a copiar
			String nomStr = fileImagePath.substring(fileImagePath.lastIndexOf(separador) + 1, fileImagePath.length());
			fileImagePath =(new File("")).getAbsolutePath()+separador+"src"+separador+"imagenes"+separador+"imgEstudios"+separador+idFicha+"-"+nomStr;
			
			FileOutputStream fos = new FileOutputStream(fileImagePath); //outFile -> Copia del archivo
			FileChannel inChannel = fis.getChannel();
			FileChannel outChannel = fos.getChannel();
			inChannel.transferTo(0, inChannel.size(), outChannel);
			fis.close();
			fos.close();
			try{
				persistencia.iniciarTransaccion();
				EstudioComplementario estComp = controlEstudioComplementario.obtenerEstudioComplementario(idEstudio);
				Date fecha = new Date();
				estComp.setFecha(fecha);
				estComp.setPath(fileImagePath);
				String fechaStr = fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+(fecha.getYear()+1900);
				Vector<String> vecComentario = new Vector<String>();
				vecComentario.add(this.guiEstudioComplementario.getCampoArea().getText());
				estComp.setInforme(vecComentario);
				String tipoEstudioStr = this.guiEstudioComplementario.getCampoTipo().getSelectedItem().toString().toLowerCase();
				estComp.setTipo(tipoEstudioStr);
				persistencia.concretarTransaccion();
				tablaModelo.setValueAt(tipoEstudioStr.toUpperCase(), fila, 1);
				tablaModelo.setValueAt(fechaStr, fila, 2);
				tablaModelo.fireTableDataChanged();
				this.guiEstudioComplementario.setVisible(false);
				this.guiEstudioComplementario.dispose();
			}catch(Exception e){
				System.out.println("Error "+this.getClass().toString());
				e.printStackTrace();
				persistencia.deshacerTransaccion();
				JOptionPane.showMessageDialog(this.guiEstudioComplementario, "ERROR AL COPIAR LOS DATOS");
			}
		}catch(IOException ioe){
			JOptionPane.showMessageDialog(this.guiEstudioComplementario, "ERROR AL COPIAR LOS DATOS");
		}
	}
	
	public void cargarEstudios(){
		try{
			persistencia.iniciarTransaccion();
			Iterator<TipoEstudio> iterEstudios = controlTipoEstudio.obtenerTipoEstudios();
			while(iterEstudios.hasNext()){
				TipoEstudio tipoEstudio = iterEstudios.next();
				this.guiEstudioComplementario.getCampoTipo().addItem(tipoEstudio.getTipo().toUpperCase());
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
					this.guiEstudioComplementario.getCampoTipo().addItem(nuevoEstudio.toUpperCase());
					persistencia.concretarTransaccion();
				}else{
					JOptionPane.showMessageDialog(this.guiEstudioComplementario,"EL TIPO DE ESTUDIO YA EXISTE");
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
