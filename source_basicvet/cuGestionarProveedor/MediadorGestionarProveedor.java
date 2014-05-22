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

package cuGestionarProveedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.BasicVet;
import persistencia.dominio.Proveedor;
import clasesComunes.ControlBasicVet;
import cuGestionarProveedor.report.ReporteProveedor;

public class MediadorGestionarProveedor implements ActionListener {
	private GUIGestionarProveedor guiGestionarProveedor;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Object source;
	private Persistencia persistencia = new Persistencia();
	private ControlProveedor controlProveedor;
	private ControlBasicVet controlBasicVet;
	
	public MediadorGestionarProveedor(){
		super();
		inicializar();
	}
	
	@SuppressWarnings({ "deprecation" })
	public void mostrar(){
		this.guiGestionarProveedor.show();
	}

	private void inicializar() {
		this.controlProveedor = new ControlProveedor();
		this.controlBasicVet = new ControlBasicVet();
		this.modelo.setColumnIdentifiers(new String[]{"DNI","NOMBRE","APELLIDO","TELEFONO"});
		this.guiGestionarProveedor = new GUIGestionarProveedor(this.modelo);
		this.guiGestionarProveedor.getBotonEliminarProveedor().setEnabled(false);
		this.guiGestionarProveedor.getBotonModificarProveedor().setEnabled(false);
		this.guiGestionarProveedor.getBotonReporteProveedor().setEnabled(false);
		try{
			persistencia.iniciarTransaccion();
			Iterator<Proveedor> iterProveedores = controlProveedor.obtenerProveedores();
			Proveedor pro;
			while(iterProveedores.hasNext()){
				pro = iterProveedores.next();
				if(!pro.isEliminado()){
					this.modelo.addRow(new String[]{pro.getDni().toUpperCase(),pro.getNombre().toUpperCase(),pro.getApellido().toUpperCase(),pro.getTelefono()+""});
				}
			}
			if(this.modelo.getRowCount()>0){
				this.guiGestionarProveedor.getBotonModificarProveedor().setEnabled(true);
				this.guiGestionarProveedor.getBotonEliminarProveedor().setEnabled(true);
				this.guiGestionarProveedor.getBotonReporteProveedor().setEnabled(true);
			}
			persistencia.concretarTransaccion();
			this.guiGestionarProveedor.getBotonCancelar().setVisible(false);
			this.guiGestionarProveedor.getBotonSeleccionarProveedor().setVisible(false);
			this.guiGestionarProveedor.setListenerButtons(this);
			this.guiGestionarProveedor.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionModificarVeterinario();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	private void opcionAgregarVeterinario() {
		MediadorIngresarProveedor medIngresarVeterinario = new MediadorIngresarProveedor(this.modelo);
		medIngresarVeterinario.mostrar();
		if(this.modelo.getRowCount()>0){
			this.guiGestionarProveedor.getBotonModificarProveedor().setEnabled(true);
			this.guiGestionarProveedor.getBotonEliminarProveedor().setEnabled(true);
			this.guiGestionarProveedor.getBotonReporteProveedor().setEnabled(true);
		}
		this.modelo.fireTableDataChanged();
	}
	
	private void opcionEliminarVeterinario(){
		int fila = this.guiGestionarProveedor.getTablaProveedores().getSelectedRow();
		int aux = this.guiGestionarProveedor.getTableRowSorter().convertRowIndexToModel(fila);
		try{
			if(aux>=0){
	        	int opcion = JOptionPane.showConfirmDialog(this.guiGestionarProveedor, "DESEA ELIMINAR EL PROVEEDOR", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
	        	if(opcion == JOptionPane.YES_OPTION){
	        		persistencia.iniciarTransaccion();
	        		String idVeterinario = (String) this.modelo.getValueAt(aux, 0);
	        		Proveedor proveedor = controlProveedor.obtenerProveedor(idVeterinario.toLowerCase());
	        		proveedor.setEliminado(true);
	        		persistencia.concretarTransaccion();
	        		this.modelo.removeRow(aux);
	        		this.modelo.fireTableDataChanged();
	        		if(this.modelo.getRowCount()==0){
	        			this.guiGestionarProveedor.getBotonModificarProveedor().setEnabled(false);
	        			this.guiGestionarProveedor.getBotonEliminarProveedor().setEnabled(false);
	        			this.guiGestionarProveedor.getBotonReporteProveedor().setEnabled(false);
	        		}
	        	}
			}else{JOptionPane.showMessageDialog(this.guiGestionarProveedor, "SELECCION UNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
		
	
	private void opcionModificarVeterinario(){
		@SuppressWarnings("unused")
		MediadorModificarProveedor medModificarVeterinario = new MediadorModificarProveedor(this.modelo, this.guiGestionarProveedor);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource(); 
		if(this.guiGestionarProveedor.getBotonAgregarProveedor()== source){
			this.opcionAgregarVeterinario();
		}
		if(this.guiGestionarProveedor.getBotonEliminarProveedor()==source){
			this.opcionEliminarVeterinario();
		}
		if(this.guiGestionarProveedor.getBotonModificarProveedor()==source){
			this.opcionModificarVeterinario();
		}
		if(this.guiGestionarProveedor.getBotonReporteProveedor()==e.getSource()){
			this.opcionReporte();
		}
	}

	private void opcionReporte(){
		if(this.guiGestionarProveedor.getTableRowSorter().getViewRowCount()>0){	
			int numColumnas = this.modelo.getColumnCount();
			int numFilas = this.guiGestionarProveedor.getTableRowSorter().getViewRowCount();
			Object[][] datos = new Object[numFilas][numColumnas];
			for(int i=0;i<numFilas;i++){
				for(int j=0;j<numColumnas;j++){
					int filaVista = this.guiGestionarProveedor.getTableRowSorter().convertRowIndexToModel(i);
					datos[i][j] = this.modelo.getValueAt(filaVista, j);
				}
			}
			guiGestionarProveedor.setVisible(false);
			ReporteProveedor viewReport = new ReporteProveedor(guiGestionarProveedor);
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
		}else{JOptionPane.showMessageDialog(guiGestionarProveedor, "LA LISTA ESTA VACIA");}
	}
}
