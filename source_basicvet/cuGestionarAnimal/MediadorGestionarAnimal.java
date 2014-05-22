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
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.BasicVet;
import clasesComunes.ControlBasicVet;
import cuGestionarAnimal.report.ReporteAnimal;

public class MediadorGestionarAnimal extends MediadorGeneral implements ActionListener{
	private GUIGestionarAnimal guiGestionarAnimal;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Object source;
	private Persistencia persistencia = new Persistencia();
	private ControlAnimal controlAnimal;
	private ControlBasicVet controlBasicVet;
	  
	public MediadorGestionarAnimal(){
		super();
		inicializar();
	}
	
	@SuppressWarnings({ "deprecation" })
	public void mostrar(){
		this.guiGestionarAnimal.show();
	}
	
	private void inicializar() {
		this.controlAnimal = new ControlAnimal();
		this.controlBasicVet = new ControlBasicVet();
		this.modelo.setColumnIdentifiers(new String[]{"ID","NUMERO","NOMBRE","RAZA","ESPECIE","EDAD"});
		this.guiGestionarAnimal = new GUIGestionarAnimal(this.modelo);
		this.guiGestionarAnimal.getBotonEliminarAnimal().setEnabled(false);
		this.guiGestionarAnimal.getBotonModificarAnimal().setEnabled(false);
		this.guiGestionarAnimal.getBotonReporte().setEnabled(false);
		try{
			persistencia.iniciarTransaccion();
			Iterator<Animal> iterAnimales = controlAnimal.obtenerAnimales();
			Animal animal;
			while(iterAnimales.hasNext()){
				animal = iterAnimales.next();
				if(!animal.isEliminado())
				this.modelo.addRow(new String[]{animal.getId()+"",animal.getNro().toUpperCase(),animal.getNombre().toUpperCase(),animal.getRaza().toUpperCase(),animal.getEspecie().toUpperCase(),animal.getEdad()+""});
			}
			if(this.modelo.getRowCount()>0){
				this.guiGestionarAnimal.getBotonModificarAnimal().setEnabled(true);
				this.guiGestionarAnimal.getBotonEliminarAnimal().setEnabled(true);
				this.guiGestionarAnimal.getBotonReporte().setEnabled(true);
			}
			this.guiGestionarAnimal.setListenerButtons(this);
			this.guiGestionarAnimal.setListenerMouse(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionModificarAnimal();}
			});
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	private void opcionAgregarAnimal() {
		MediadorIngresarAnimal medIngresarAnimal = new MediadorIngresarAnimal(this.modelo,new LinkedList<Animal>(),false);
		medIngresarAnimal.mostrar();
		if(this.modelo.getRowCount()>0){
			this.guiGestionarAnimal.getBotonModificarAnimal().setEnabled(true);
			this.guiGestionarAnimal.getBotonEliminarAnimal().setEnabled(true);
			this.guiGestionarAnimal.getBotonReporte().setEnabled(true);
		}
		this.modelo.fireTableDataChanged();
	}
	
	private void opcionModificarAnimal(){
		JTable tablaAnimal = this.guiGestionarAnimal.getTablaAnimales();
		@SuppressWarnings("unused")
		MediadorModificarAnimal medModificarAnimal = new MediadorModificarAnimal(tablaAnimal);
	}
	private void opcionEliminarAnimal(){
		int fila = this.guiGestionarAnimal.getTablaAnimales().getSelectedRow();
		if(fila>=0){
        	int opcion = JOptionPane.showConfirmDialog(this.guiGestionarAnimal, "DESEA ELIMINAR EL ANIMAL", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
        	try{
        		persistencia.iniciarTransaccion();
	        	if(opcion == JOptionPane.YES_OPTION){
	        		int idAnimal = Integer.parseInt((String) this.modelo.getValueAt(fila, 0));
	        		Animal animal = controlAnimal.obtenerAnimal(idAnimal);
	        		animal.setEliminado(true);	
	        		this.modelo.removeRow(fila);
	        		this.modelo.fireTableDataChanged();
	        		if(this.modelo.getRowCount()==0){
	        			this.guiGestionarAnimal.getBotonModificarAnimal().setEnabled(false);
	        			this.guiGestionarAnimal.getBotonEliminarAnimal().setEnabled(false);
	        			this.guiGestionarAnimal.getBotonReporte().setEnabled(false);
	        		}
	        	}
	        	persistencia.concretarTransaccion();
        	}
        	catch(Exception e){
				System.out.println("Error "+this.getClass().toString());
				e.printStackTrace();
				persistencia.deshacerTransaccion();
			}
		}else{JOptionPane.showMessageDialog(this.guiGestionarAnimal, "SELECCION UNA FILA");}
	}
	
	public DefaultTableModel getTable(){
		return this.modelo;
	}

	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		if(this.guiGestionarAnimal.getBotonAgregarAnimal()==source){
			this.opcionAgregarAnimal();
		}
		if(this.guiGestionarAnimal.getBotonModificarAnimal()==source){
			this.opcionModificarAnimal();
		}
		if(this.guiGestionarAnimal.getBotonEliminarAnimal()==source){
			this.opcionEliminarAnimal();
		}
		if(this.guiGestionarAnimal.getBotonReporte()==source){
			this.opcionReporte();
		}
	}

	private void opcionReporte() {
		if(this.guiGestionarAnimal.getTableRowSorter().getViewRowCount()>0){	
			int numColumnas = this.modelo.getColumnCount();
			int numFilas = this.guiGestionarAnimal.getTableRowSorter().getViewRowCount();
			Object[][] datos = new Object[numFilas][numColumnas];
			for(int i=0;i<numFilas;i++){
				for(int j=0;j<numColumnas;j++){
					int filaVista = this.guiGestionarAnimal.getTableRowSorter().convertRowIndexToModel(i);
					datos[i][j] = this.modelo.getValueAt(filaVista, j);
				}
			}
			guiGestionarAnimal.setVisible(false);
			ReporteAnimal viewReport = new ReporteAnimal(guiGestionarAnimal);
			try{
				persistencia.iniciarTransaccion();
				BasicVet bv = controlBasicVet.obtenerBasicVet("1");
				viewReport.viewFicha(datos,bv.getNombre(),bv.getDireccion(),bv.getTelefono()+"",bv.getCuil());
				persistencia.concretarTransaccion();
			}
			catch(Exception e){
				System.out.println("Error "+this.getClass().toString());
				e.printStackTrace();
				persistencia.deshacerTransaccion();
			}
		}else{JOptionPane.showMessageDialog(this.guiGestionarAnimal, "SELECCION UNA FILA");}
	}
}
