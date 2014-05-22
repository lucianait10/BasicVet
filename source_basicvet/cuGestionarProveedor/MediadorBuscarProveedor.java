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

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Proveedor;

public class MediadorBuscarProveedor implements ActionListener{
	private GUIGestionarProveedor guiBuscar;
	private DefaultTableModel modelo;
	private Persistencia persistencia = new Persistencia();
	private Object source;
	private String idProveedorBuscado;
	private MediadorGeneral mediador;
	private ControlProveedor controlProveedor;
	
	public MediadorBuscarProveedor(MediadorGeneral med){
		super();
		this.mediador = med;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiBuscar.show();
	}
	
	private void inicializar(){
		this.modelo = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable (int row, int column){return false;}
		};
		this.controlProveedor = new ControlProveedor();
		this.modelo.setColumnIdentifiers(new String[]{"DNI","NOMBRE","APELLIDO","TELEFONO"});
		try{
			persistencia.iniciarTransaccion();
			Iterator<Proveedor> iterProveedores = controlProveedor.obtenerProveedores();
			Proveedor prov;
			while(iterProveedores.hasNext()){
				prov = iterProveedores.next();
				if(!prov.isEliminado())
					this.modelo.addRow(new String[]{prov.getDni().toUpperCase(),prov.getNombre().toUpperCase(),prov.getApellido().toUpperCase(),prov.getTelefono()+""});
			}
			persistencia.concretarTransaccion();
			this.guiBuscar = new GUIGestionarProveedor(this.modelo);
			this.guiBuscar.getBotonEliminarProveedor().setVisible(false);
			this.guiBuscar.getBotonModificarProveedor().setVisible(false);
			this.guiBuscar.setListenerButtons(this);
			this.guiBuscar.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionSeleccionar();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	private void opcionAgregarProveedor(){
		MediadorIngresarProveedor medIngresarPropietario = new MediadorIngresarProveedor(this.modelo);
		medIngresarPropietario.mostrar();
	}
	
	private void opcionSeleccionar(){
		int fila = this.guiBuscar.getTablaProveedores().getSelectedRow();
		
		if(fila>=0){
			int filaReal = this.guiBuscar.getTableRowSorter().convertRowIndexToModel(fila);
			idProveedorBuscado =((String)modelo.getValueAt(filaReal,0)).toLowerCase();
			mediador.setProveedorBuscado(idProveedorBuscado);
			this.guiBuscar.setVisible(false);
			this.guiBuscar.dispose();
		}else{
			JOptionPane.showMessageDialog(this.guiBuscar, "SELECCIONE ALGUN PROVEEDOR");
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		if(this.guiBuscar.getBotonAgregarProveedor()==source){
			this.opcionAgregarProveedor();
		}
		if(this.guiBuscar.getBotonSeleccionarProveedor()==source){
			this.opcionSeleccionar();
		}
	}
}
