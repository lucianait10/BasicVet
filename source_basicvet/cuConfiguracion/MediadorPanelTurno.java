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
import persistencia.dominio.Motivo;
import clasesComunes.ControlMotivo;

public class MediadorPanelTurno implements ActionListener{
	private PanelTurno panelTurno;
	private Persistencia persistencia;
	private ControlMotivo controlMotivo;
	
	public MediadorPanelTurno(PanelTurno pTurno){
		super();
		this.panelTurno =pTurno;
		inicializar();
	}

	private void inicializar(){
		this.controlMotivo = new ControlMotivo();
		this.persistencia = new Persistencia();
		try{
			persistencia.iniciarTransaccion();
			this.panelTurno.setListenerButtons(this);
			Iterator<Motivo> iterMotivos = controlMotivo.obtenerMotivos();
			while(iterMotivos.hasNext()){
				Motivo motivo = iterMotivos.next();
				panelTurno.agregarMotivo(motivo.getNombre().toUpperCase());
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void agregarMotivo(){
		String nuevoMotivo = JOptionPane.showInputDialog("INGRESE EL NUEVO MOTIVO");
		try{
			if(nuevoMotivo.length()>0){
				persistencia.iniciarTransaccion();
				int existeMotivo = controlMotivo.existeMotivo(nuevoMotivo.toLowerCase());
				if(existeMotivo!=1){
					Motivo motivo = new Motivo(nuevoMotivo.toLowerCase());
					controlMotivo.guardarMotivo(motivo);
					persistencia.concretarTransaccion();
					panelTurno.agregarMotivo(nuevoMotivo.toUpperCase());
				}else{JOptionPane.showMessageDialog(this.panelTurno,"EL MOTIVO YA EXISTE");}
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void quitarMotivo(){
		int fila=panelTurno.getListaMotivo().getSelectedIndex();
		try{
			if(fila>=0){
				persistencia.iniciarTransaccion();
				controlMotivo.eliminarMotivo(((String)panelTurno.getModeloLista().getElementAt(fila)).toLowerCase());
				persistencia.concretarTransaccion();
				panelTurno.quitarMotivo(fila);
			}else{
				JOptionPane.showMessageDialog(panelTurno, "SELECCIONE ALGUN MOTIVO");
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	

	
	public void actionPerformed(ActionEvent e) {
		if(this.panelTurno.getBotonAgregarMotivo()==e.getSource()){
			this.agregarMotivo();
		}
		if(this.panelTurno.getBotonQuitarMotivo()==e.getSource()){
			this.quitarMotivo();
		}
	}
}
