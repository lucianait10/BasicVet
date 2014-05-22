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

package cuGestionarCliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.BasicVet;
import persistencia.dominio.Cliente;
import clasesComunes.ControlBasicVet;
import cuGestionarCliente.report.ReporteCliente;

public class MediadorGestionarCliente implements ActionListener {
	private GUIGestionarCliente guiGestionarCliente;
	private DefaultTableModel modelo;
	private Object source;
	private Persistencia persistencia = new Persistencia();
	private ControlCliente controlProveedor;
	private ControlBasicVet controlBasicVet;
	
	public MediadorGestionarCliente(){
		super();
		inicializar();
	}
	
	@SuppressWarnings({ "deprecation" })
	public void mostrar(){
		this.guiGestionarCliente.show();
	}

	private void inicializar() {
		this.modelo = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable (int row, int column){return false;}
		};
		this.controlProveedor = new ControlCliente();
		this.controlBasicVet = new ControlBasicVet();
		this.modelo.setColumnIdentifiers(new String[]{"DNI","NOMBRE","APELLIDO","TELEFONO"});
		this.guiGestionarCliente = new GUIGestionarCliente(this.modelo);
		this.guiGestionarCliente.getBotonEliminarCliente().setEnabled(false);
		this.guiGestionarCliente.getBotonModificarCliente().setEnabled(false);
		this.guiGestionarCliente.getBotonReporteCliente().setEnabled(false);
		try{
			persistencia.iniciarTransaccion();
			Iterator<Cliente> iterClientes = controlProveedor.obtenerClientes();
			Cliente cli;
			while(iterClientes.hasNext()){
				cli = iterClientes.next();
				if(!cli.isEliminado()){
					this.modelo.addRow(new String[]{cli.getDni().toUpperCase(),cli.getNombre().toUpperCase(),cli.getApellido().toUpperCase(),cli.getTelefono()+""});
				}
			}
			if(this.modelo.getRowCount()>0){
				this.guiGestionarCliente.getBotonModificarCliente().setEnabled(true);
				this.guiGestionarCliente.getBotonEliminarCliente().setEnabled(true);
				this.guiGestionarCliente.getBotonReporteCliente().setEnabled(true);
			}
			persistencia.concretarTransaccion();
			this.guiGestionarCliente.getBotonCancelar().setVisible(false);
			this.guiGestionarCliente.getBotonSeleccionarCliente().setVisible(false);
			this.guiGestionarCliente.setListenerButtons(this);
			this.guiGestionarCliente.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionModificarCliente();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	private void opcionAgregarCliente() {
		MediadorIngresarCliente medIngresarCliente = new MediadorIngresarCliente(this.modelo);
		medIngresarCliente.mostrar();
		if(this.modelo.getRowCount()>0){
			this.guiGestionarCliente.getBotonModificarCliente().setEnabled(true);
			this.guiGestionarCliente.getBotonEliminarCliente().setEnabled(true);
			this.guiGestionarCliente.getBotonReporteCliente().setEnabled(true);
		}
		this.modelo.fireTableDataChanged();
	}
	
	private void opcionEliminarCliente(){
		int fila = this.guiGestionarCliente.getTablaClientes().getSelectedRow();
		if(fila>=0){
        	int opcion = JOptionPane.showConfirmDialog(this.guiGestionarCliente, "DESEA ELIMINAR EL CLIENTE", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
        	try{
	        	if(opcion == JOptionPane.YES_OPTION){
	        		persistencia.iniciarTransaccion();
	        		String idCliente = (String) this.modelo.getValueAt(fila, 0);
	        		Cliente cliente = controlProveedor.obtenerCliente(idCliente.toLowerCase());
	        		cliente.setEliminado(true);
	        		persistencia.concretarTransaccion();
	        		this.modelo.removeRow(fila);
	        		this.modelo.fireTableDataChanged();
	        		if(this.modelo.getRowCount()==0){
	        			this.guiGestionarCliente.getBotonModificarCliente().setEnabled(false);
	        			this.guiGestionarCliente.getBotonEliminarCliente().setEnabled(false);
	        			this.guiGestionarCliente.getBotonReporteCliente().setEnabled(false);
	        		}
	        	}
        	}
        	catch(Exception e){
				System.out.println("Error "+this.getClass().toString());
				e.printStackTrace();
				persistencia.deshacerTransaccion();
			}
		}else{JOptionPane.showMessageDialog(this.guiGestionarCliente, "SELECCION UNA FILA");}
	}
	
	private void opcionModificarCliente(){
		@SuppressWarnings("unused")
		MediadorModificarCliente medModificarCliente = new MediadorModificarCliente(this.modelo,this.guiGestionarCliente);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource(); 
		if(this.guiGestionarCliente.getBotonAgregarCliente()== source){
			this.opcionAgregarCliente();
		}
		if(this.guiGestionarCliente.getBotonEliminarCliente()==source){
			this.opcionEliminarCliente();
		}
		if(this.guiGestionarCliente.getBotonModificarCliente()==source){
			this.opcionModificarCliente();
		}
		if(this.guiGestionarCliente.getBotonReporteCliente()==e.getSource()){
			this.opcionReporte();
		}
	}

	private void opcionReporte(){
		if(this.guiGestionarCliente.getTableRowSorter().getViewRowCount()>0){	
			int numColumnas = this.modelo.getColumnCount();
			int numFilas = this.guiGestionarCliente.getTableRowSorter().getViewRowCount();
			Object[][] datos = new Object[numFilas][numColumnas];
			for(int i=0;i<numFilas;i++){
				for(int j=0;j<numColumnas;j++){
					int filaVista = this.guiGestionarCliente.getTableRowSorter().convertRowIndexToModel(i);
					datos[i][j] = this.modelo.getValueAt(filaVista, j);
				}
			}
			guiGestionarCliente.setVisible(false);
			ReporteCliente viewReport = new ReporteCliente(guiGestionarCliente);
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
		}else{JOptionPane.showMessageDialog(guiGestionarCliente, "LA LISTA ESTA VACIA");}
	}
}
