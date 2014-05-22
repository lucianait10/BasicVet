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

package cuGestionarAnimal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.Propietario;
import cuGestionarPropietario.ControlPropietario;


public class MediadorBuscarAnimal extends MediadorGeneral implements ActionListener{

	private GUIBuscarAnimal guiBuscarAnimal;
	private DefaultTableModel tabla = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Persistencia persistencia= new Persistencia(); 
	private static int idAnimalBuscado;  
	MediadorGeneral mediador;
	private ControlPropietario controlPropietario;	
	
	public MediadorBuscarAnimal(MediadorGeneral m){
		super();
		this.mediador=m;
		this.inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar() {
		this.guiBuscarAnimal.show();
	}
	
	@SuppressWarnings("unchecked")
	private void inicializar() {
		this.controlPropietario = new ControlPropietario();
		try{
			tabla.setColumnIdentifiers(new String[]{"ID","NUMERO","NOMBRE","RAZA","ESPECIE","EDAD"});
			persistencia.iniciarTransaccion();
			Iterator iter=controlPropietario.obtenerPropietarios();
			while(iter.hasNext()){
				Propietario p=(Propietario)iter.next();
				Collection<Animal> animales = p.obtenerAnimales();
				Iterator iterAnimales=animales.iterator();
				while(iterAnimales.hasNext()){
					Animal a =(Animal)iterAnimales.next();
					if(!a.isEliminado()){
						String numero = a.getNro().toUpperCase();
						String nombre = a.getNombre().toUpperCase();
						String raza = a.getRaza().toUpperCase();
						String especie = a.getEspecie().toUpperCase();
						String edad = a.getEdad()+"";
						String[] s = {a.getId()+"",numero,nombre,raza,especie,edad};
						tabla.addRow(s);
					}
				}
			}
			persistencia.concretarTransaccion();
			this.guiBuscarAnimal = new GUIBuscarAnimal(tabla);
			this.guiBuscarAnimal.getBotonAceptar().setEnabled(true);
			this.guiBuscarAnimal.getBotonCancelar().setEnabled(true);
			this.guiBuscarAnimal.getBotonAgregar().setEnabled(true);
			this.guiBuscarAnimal.setListenerButtons(this);
			this.guiBuscarAnimal.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)aceptarBusqueda();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}

	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiBuscarAnimal.getBotonAceptar() == source){
			this.aceptarBusqueda();
		}
		if(this.guiBuscarAnimal.getBotonCancelar() == source){
			this.cancelarBusqueda();
		}
		if(this.guiBuscarAnimal.getBotonAgregar() == source){
			this.agregarAnimal();
		}
	}

	private void agregarAnimal() {
		MediadorIngresarAnimal medAltaAnimal= new MediadorIngresarAnimal(this.tabla,new LinkedList<Animal>(),false);
		medAltaAnimal.mostrar();
	}

	private void cancelarBusqueda() {
		this.guiBuscarAnimal.setVisible(false);
		this.guiBuscarAnimal.dispose();
	}

	private void aceptarBusqueda() {
		int fila = this.guiBuscarAnimal.getTablaBusqueda().getSelectedRow();		
		if(fila >= 0){
			int filaReal = this.guiBuscarAnimal.getTableRowSorter().convertRowIndexToModel(fila);
			idAnimalBuscado =Integer.parseInt((String)tabla.getValueAt(filaReal,0));
			mediador.setAnimalBuscado(idAnimalBuscado);
			this.guiBuscarAnimal.setVisible(false);
			this.guiBuscarAnimal.dispose();
		}
		else{JOptionPane.showMessageDialog(guiBuscarAnimal,"SELECCIONE ALGUNA FILA");}
	}
}
