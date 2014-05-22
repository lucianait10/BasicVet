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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JOptionPane;

import persistencia.Persistencia;
import persistencia.dominio.TipoEstudio;
import clasesComunes.ControlTipoEstudio;
  
public class MediadorPanelEstudio implements ActionListener{
	private PanelEstudio panelEstudio;
	private Persistencia persistencia;
	private ControlTipoEstudio controlTipoEstudio;
	
	public MediadorPanelEstudio(PanelEstudio pEstudio){
		super();
		this.panelEstudio = pEstudio;
		inicializar();
	}

	private void inicializar(){
		this.controlTipoEstudio = new ControlTipoEstudio();
		this.persistencia = new Persistencia();
		try{
			persistencia.iniciarTransaccion();
			this.panelEstudio.setListenerButtons(this);
			Iterator<TipoEstudio> iterEstudio = controlTipoEstudio.obtenerEstudiosComplementarios();
			while(iterEstudio.hasNext()){
				TipoEstudio tipoEstudio = iterEstudio.next();
				panelEstudio.agregarEstudio(tipoEstudio.getTipo().toUpperCase());
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
					persistencia.concretarTransaccion();
					panelEstudio.agregarEstudio(nuevoEstudio.toUpperCase());
				}else{
					JOptionPane.showMessageDialog(this.panelEstudio,"EL TIPO DE ESTUDIO YA EXISTE");
				}
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void quitarTipoEstudio(){
		int fila=panelEstudio.getListaTipoEstudios().getSelectedIndex();
		try{
			if(fila>=0){
				persistencia.iniciarTransaccion();
				controlTipoEstudio.eliminarTipoEstudio(((String)panelEstudio.getModeloTipoEstudio().getElementAt(fila)).toLowerCase());
				persistencia.concretarTransaccion();
				panelEstudio.quitarEstudio(fila);
			}else{
				JOptionPane.showMessageDialog(panelEstudio, "SELECCIONE ALGUN MOTIVO");
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(this.panelEstudio.getBotonAgregarTipoEstudio()==e.getSource()){
			this.agregarTipoEstudio();
		}
		if(this.panelEstudio.getBotonQuitarTipoEstudio()==e.getSource()){
			this.quitarTipoEstudio();
		}
	}
}
