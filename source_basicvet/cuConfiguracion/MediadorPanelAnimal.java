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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import persistencia.Persistencia;
import persistencia.dominio.Especie;
import persistencia.dominio.Raza;
import clasesComunes.ControlEspecie;
import clasesComunes.ControlRaza;

public class MediadorPanelAnimal implements ActionListener, ListSelectionListener{
	private PanelAnimal panelAnimal;
	private Persistencia persistencia;
	private ControlEspecie controlEspecie;  
	private ControlRaza controlRaza;
	
	public MediadorPanelAnimal(PanelAnimal pAnimal){
		super();
		this.panelAnimal = pAnimal;
		inicializar();		
	}

	private void inicializar() {
		this.controlRaza = new ControlRaza();
		this.controlEspecie = new ControlEspecie();
		this.persistencia = new Persistencia();
		try{
			persistencia.iniciarTransaccion();
			this.panelAnimal.setListenerButtons(this);
			this.panelAnimal.setListSelectionListener(this);
			Iterator<Especie> iterEspecie = controlEspecie.obtenerEspecies();
			while(iterEspecie.hasNext()){
				Especie especie = iterEspecie.next();
				panelAnimal.agregarEspecie(especie.getNombre().toUpperCase());
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		if(this.panelAnimal.getBotonAgregarEspecie() == e.getSource()){
			this.opcionAgregarEspecie();
		}
		if(this.panelAnimal.getBotonQuitarEspecie() == e.getSource()){
			this.opcionQuitarEspecie();
		}
		if(this.panelAnimal.getBotonAgregarRaza()== e.getSource()){
			this.opcionAgregarRaza();
		}
		if(this.panelAnimal.getBotonQuitarRaza()==e.getSource()){
			this.opcionQuitarRaza();
		}
	}
	
	private void opcionQuitarRaza() {
			int fila=panelAnimal.getListaRazas().getSelectedIndex();
			if(fila>=0){
				try{
					persistencia.iniciarTransaccion();
					controlRaza.eliminarRaza(((String)panelAnimal.getModeloRaza().getElementAt(fila)).toLowerCase());
					persistencia.concretarTransaccion();
					panelAnimal.quitarRaza(fila);
				}
				catch(Exception e){
					System.out.println("Error "+this.getClass().toString());
					e.printStackTrace();
					persistencia.deshacerTransaccion();
				}
			}else{
				JOptionPane.showMessageDialog(panelAnimal, "SELECCIONE ALGUNA RAZA");
			}	
		
		
	}

	private void opcionAgregarRaza() {
		int filaEspecie=panelAnimal.getListaEspecies().getSelectedIndex();
		if(filaEspecie>=0){
			String nuevoRaza = JOptionPane.showInputDialog("INGRESE UNA NUEVA RAZA");
			if(nuevoRaza.length()>0){
				try{
					persistencia.iniciarTransaccion();
					int existeRaza = controlRaza.existeRaza(nuevoRaza.toLowerCase());
					if(existeRaza!=1){
						Raza raza = new Raza(nuevoRaza);
						Especie especie = controlEspecie.obtenerEspecie(((String)panelAnimal.getModeloEspecie().getElementAt(filaEspecie)).toLowerCase());
						especie.agregarRazas(raza);
						persistencia.concretarTransaccion();
						panelAnimal.agregarRaza(nuevoRaza.toUpperCase());
					}else{JOptionPane.showMessageDialog(this.panelAnimal,"LA RAZA YA EXISTE");}
				}
				catch(Exception e){
					System.out.println("Error "+this.getClass().toString());
					e.printStackTrace();
					persistencia.deshacerTransaccion();
				}	
			}
		}else{
			JOptionPane.showMessageDialog(panelAnimal, "SELECCIONE ALGUNA ESPECIE");
		}
	}

	private void opcionAgregarEspecie() {
		String nuevoEspecie = JOptionPane.showInputDialog("INGRESE UNA NUEVA ESPECIE");
		try{
			if(nuevoEspecie.length()>0){
				persistencia.iniciarTransaccion();
				int existeEspecie = controlEspecie.existeEspecie(nuevoEspecie.toLowerCase());
				if(existeEspecie!=1){
					Especie especie = new Especie(nuevoEspecie.toLowerCase());
					controlEspecie.guardarEspecie(especie);
					persistencia.concretarTransaccion();
					panelAnimal.agregarEspecie(nuevoEspecie.toUpperCase());
				}else{JOptionPane.showMessageDialog(this.panelAnimal,"LA ESPECIE YA EXISTE");}
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
		
	}
	
	public void opcionQuitarEspecie(){
		int fila=panelAnimal.getListaEspecies().getSelectedIndex();
		try{
			if(fila>=0){
				persistencia.iniciarTransaccion();
				controlEspecie.eliminarEspecie(((String)panelAnimal.getModeloEspecie().getElementAt(fila)).toLowerCase());
				persistencia.concretarTransaccion();
				panelAnimal.quitarEspecie(fila);
			}else{
				JOptionPane.showMessageDialog(panelAnimal, "SELECCIONE ALGUNA ESPECIE");
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	private void opcionListarRazas(String mombreEspecie){
		try{
			persistencia.iniciarTransaccion();
			Especie especie = controlEspecie.obtenerEspecie(mombreEspecie);
			Raza raza;
			Iterator<Raza> iterRazas = especie.obtenerRazas().iterator();
			panelAnimal.getModeloRaza().removeAllElements();
			while(iterRazas.hasNext()){
				raza = iterRazas.next();
				panelAnimal.agregarRaza(raza.getNombre().toUpperCase());
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            //if(e.getValueIsAdjusting())
            if (!lsm.isSelectionEmpty()){
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                    	String nombreEspecie = ((String)panelAnimal.getListaEspecies().getModel().getElementAt(i)).toLowerCase();
                    	this.opcionListarRazas(nombreEspecie);
                    }
                }
            }
	}

}
