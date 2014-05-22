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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Cliente;

@SuppressWarnings({ "unchecked"})
public class MediadorBuscarCliente implements ActionListener{

	private GUIBuscarCliente guiBuscarCliente;
	private DefaultTableModel tabla = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Persistencia persistencia= new Persistencia(); 
	private static String dniClienteBuscado;
	MediadorGeneral mediador;

	
	
	public MediadorBuscarCliente(MediadorGeneral m){
		super();
		this.mediador=m;
		this.inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar() {
		this.guiBuscarCliente.show();
	}
	
	private void inicializar() {
		this.tabla.setColumnIdentifiers(new String[]{"DNI","NOMBRE","APELLIDO","TELEFONO","CUIL"});
		try{
			persistencia.iniciarTransaccion();
			Iterator iter=persistencia.obtenerColeccion(Cliente.class);
			while(iter.hasNext()){
				Cliente c=(Cliente)iter.next();
				this.tabla.addRow(new String[]{c.getDni()+"",c.getNombre().toUpperCase(),c.getApellido().toUpperCase(),c.getTelefono()+"",c.getCuil()});
			}
			this.guiBuscarCliente = new GUIBuscarCliente(tabla);
			this.guiBuscarCliente.getBotonAceptar().setEnabled(true);
			this.guiBuscarCliente.getBotonCancelar().setEnabled(true);
			this.guiBuscarCliente.getBotonAgregar().setEnabled(true);
			this.guiBuscarCliente.setListenerButtons(this);
			this.guiBuscarCliente.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)aceptarBusqueda();}
			});
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}

	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiBuscarCliente.getBotonAceptar() == source){
			this.aceptarBusqueda();
		}
		if(this.guiBuscarCliente.getBotonCancelar() == source){
			this.cancelarBusqueda();
		}
		if(this.guiBuscarCliente.getBotonAgregar() == source){
			this.agregarCliente();
		}
		
	}

	private void agregarCliente() {
		MediadorIngresarCliente medIngresarCliente= new MediadorIngresarCliente(this.tabla);
		medIngresarCliente.mostrar();
		
	}

	private void cancelarBusqueda() {
		this.guiBuscarCliente.setVisible(false);
		this.guiBuscarCliente.dispose();
		
	}

	private void aceptarBusqueda() {
		JTable jt = this.guiBuscarCliente.getTablaBusqueda();
		int fila = jt.getSelectedRow();
		
		if(fila >= 0){
			int filaReal = this.guiBuscarCliente.getTableRowSorter().convertRowIndexToModel(fila);
			dniClienteBuscado =((String)tabla.getValueAt(filaReal,0)).toLowerCase();
			mediador.setClienteBuscado(dniClienteBuscado);
			this.guiBuscarCliente.setVisible(false);
			this.guiBuscarCliente.dispose();
		}
		else{
			JOptionPane.showMessageDialog(guiBuscarCliente,"SELECCIONE ALGUNA FILA");
			}
			
		}
		
}
