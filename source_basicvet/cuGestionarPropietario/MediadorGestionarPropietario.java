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

package cuGestionarPropietario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.BasicVet;
import persistencia.dominio.Propietario;
import clasesComunes.ControlBasicVet;
import cuGestionarPropietario.report.ReportePropietario;

public class MediadorGestionarPropietario implements ActionListener {
	private GUIGestionarPropietario guiGestionarPropietario;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Object source;
	private Persistencia persistencia = new Persistencia();
	private ControlPropietario controlPropietario;
	private ControlBasicVet controlBasicVet;

	public MediadorGestionarPropietario(){
		super();
		inicializar();
	}

	@SuppressWarnings({ "deprecation" })
	public void mostrar(){
		this.guiGestionarPropietario.show();
	}

	private void inicializar() {
		this.controlPropietario = new ControlPropietario();
		this.controlBasicVet = new ControlBasicVet();
		try{
			persistencia.iniciarTransaccion();
			this.modelo.setColumnIdentifiers(new String[]{"DNI","NOMBRE","APELLIDO","TELEFONO","DIRECCION"});
			this.guiGestionarPropietario = new GUIGestionarPropietario(this.modelo);
			this.guiGestionarPropietario.getBotonModificarPropietario().setEnabled(false);
			this.guiGestionarPropietario.getBotonEliminarPropietario().setEnabled(false);
			this.guiGestionarPropietario.getBotonReportePropietario().setEnabled(false);
			Iterator<Propietario> iterPropietarios = controlPropietario.obtenerPropietarios();
			Propietario prop;
			while(iterPropietarios.hasNext()){
				prop = iterPropietarios.next();
				if(!prop.isEliminado()){
					this.modelo.addRow(new String[]{prop.getDni().toUpperCase(),prop.getNombre().toUpperCase(),prop.getApellido().toUpperCase(),prop.getTelefono()+"",prop.getDireccion().toUpperCase()});
				}
			}
			if(this.modelo.getRowCount()>0){
				this.guiGestionarPropietario.getBotonModificarPropietario().setEnabled(true);
				this.guiGestionarPropietario.getBotonEliminarPropietario().setEnabled(true);
				this.guiGestionarPropietario.getBotonReportePropietario().setEnabled(true);
			}
			persistencia.concretarTransaccion();
			this.guiGestionarPropietario.getBotonSeleccionarPropietario().setVisible(false);
			this.guiGestionarPropietario.getBotonCancelar().setVisible(false);
			this.guiGestionarPropietario.setListenerButtons(this);
			this.guiGestionarPropietario.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionModificarPropietario();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	private void opcionAgregarPropietario() {
		MediadorIngresarPropietario medIngresarPropietario = new MediadorIngresarPropietario(this.modelo);
		medIngresarPropietario.mostrar();
		if(this.modelo.getRowCount()>0){
			this.guiGestionarPropietario.getBotonModificarPropietario().setEnabled(true);
			this.guiGestionarPropietario.getBotonEliminarPropietario().setEnabled(true);
			this.guiGestionarPropietario.getBotonReportePropietario().setEnabled(true);
		}
		this.modelo.fireTableDataChanged();
	}

	private void opcionEliminarPropietario(){
		int fila = this.guiGestionarPropietario.getTablaPropietarios().getSelectedRow();
		try{
			if(fila>=0){
				int opcion = JOptionPane.showConfirmDialog(this.guiGestionarPropietario, "DESEA ELIMINAR EL PROPIETARIO", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
				if(opcion == JOptionPane.YES_OPTION){
					persistencia.iniciarTransaccion();
					int filaReal = this.guiGestionarPropietario.getTableRowSorter().convertRowIndexToModel(fila);
					String idPropietario = (String) this.modelo.getValueAt(filaReal, 0);
					Propietario propietario = controlPropietario.obtenerPropietario(idPropietario.toLowerCase());
					propietario.setEliminado(true);
					persistencia.concretarTransaccion();
					this.modelo.removeRow(filaReal);
					this.modelo.fireTableDataChanged();
					if(this.modelo.getRowCount()==0){
						this.guiGestionarPropietario.getBotonModificarPropietario().setEnabled(false);
						this.guiGestionarPropietario.getBotonEliminarPropietario().setEnabled(false);
						this.guiGestionarPropietario.getBotonReportePropietario().setEnabled(false);
					}
				}
			}else{JOptionPane.showMessageDialog(this.guiGestionarPropietario, "SELECCION UNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	private void opcionModificarPropietario(){
		@SuppressWarnings("unused")
		MediadorModificarPropietario medModificarPropietario = new MediadorModificarPropietario(this.modelo,this.guiGestionarPropietario);
	}

	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource(); 
		if(this.guiGestionarPropietario.getBotonAgregarPropietario()== source){
			this.opcionAgregarPropietario();
		}
		if(this.guiGestionarPropietario.getBotonEliminarPropietario()==source){
			this.opcionEliminarPropietario();
		}
		if(this.guiGestionarPropietario.getBotonModificarPropietario()==source){
			this.opcionModificarPropietario();
		}
		if(this.guiGestionarPropietario.getBotonReportePropietario()==e.getSource()){
			this.opcionReporte();
		}
	}

	private void opcionReporte(){
		if(this.guiGestionarPropietario.getTableRowSorter().getViewRowCount()>0){
			int numColumnas = this.modelo.getColumnCount();
			int numFilas = this.guiGestionarPropietario.getTableRowSorter().getViewRowCount();
			Object[][] datos = new Object[numFilas][numColumnas];
			for(int i=0;i<numFilas;i++){
				for(int j=0;j<numColumnas;j++){
					int filaVista = this.guiGestionarPropietario.getTableRowSorter().convertRowIndexToModel(i);
					datos[i][j] = this.modelo.getValueAt(filaVista, j);
				}
			}
			guiGestionarPropietario.setVisible(false);
			ReportePropietario viewReport = new ReportePropietario(guiGestionarPropietario);
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
		}else{JOptionPane.showMessageDialog(guiGestionarPropietario, "LA LISTA ESTA VACIA");}
	}
}
