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

package cuGestionarUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Usuario;


public class MediadorGestionarUsuario implements ActionListener{
	private GUIGestionarUsuario guiGestionarUsuario;
	private ControlUsuario controlUsuario;
	private Persistencia persistencia;
	private DefaultTableModel modeloUsuario = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};

	public MediadorGestionarUsuario() {
		super();
		inicializar();
	}

	public void mostrar() {
		this.guiGestionarUsuario.setVisible(true);
	}

	private void inicializar(){
		this.modeloUsuario.addColumn("NOMBRE");
		this.controlUsuario = new ControlUsuario();
		this.persistencia = new Persistencia();
		this.guiGestionarUsuario = new GUIGestionarUsuario(modeloUsuario);
		this.guiGestionarUsuario.getBotonEliminarUsuario().setEnabled(false);
		this.guiGestionarUsuario.getBotonModificarUsuario().setEnabled(false);
		try{
			persistencia.iniciarTransaccion();
			Iterator<Usuario> iterUsuario = controlUsuario.obtenerUsuarios();
			Usuario user;
			while(iterUsuario.hasNext()){
				user = iterUsuario.next();
				this.modeloUsuario.addRow(new String[]{user.getNomUsuario().toUpperCase()});
			}
			if(this.modeloUsuario.getRowCount()>0){
				this.guiGestionarUsuario.getBotonEliminarUsuario().setEnabled(true);
				this.guiGestionarUsuario.getBotonModificarUsuario().setEnabled(true);
			}
			persistencia.concretarTransaccion();
			this.guiGestionarUsuario.getBotonCancelar().setVisible(false);
			this.guiGestionarUsuario.getBotonAceptarUsuario().setVisible(false);
			this.guiGestionarUsuario.setListenerButtons(this);
			this.guiGestionarUsuario.setListenerMouse(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionModificarUsuario();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	private void opcionIngresarUsuario(){
		MediadorIngresarUsuario medIngresarUsuario = new MediadorIngresarUsuario(this.modeloUsuario,this.guiGestionarUsuario);
		medIngresarUsuario.mostrar();
	}
	
	private void opcionEliminarUsuario(){
		int fila = this.guiGestionarUsuario.getTabla().getSelectedRow();
		int filaReal = this.guiGestionarUsuario.getTableRowSorter().convertRowIndexToModel(fila);
		try{
			if(fila>=0){
				int opcion = JOptionPane.showConfirmDialog(this.guiGestionarUsuario, "DESEA ELIMINAR EL USUARIO", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
	        	if(opcion == JOptionPane.YES_OPTION){
	        		persistencia.iniciarTransaccion();
	        		String nomUsuario = (String) this.modeloUsuario.getValueAt(filaReal, 0);
	        		Usuario user = controlUsuario.obtenerUsuario(nomUsuario.toLowerCase());
	        		controlUsuario.eliminarUsuario(user);
	        		this.modeloUsuario.removeRow(filaReal);
	        		if(modeloUsuario.getRowCount()==0){
						this.guiGestionarUsuario.getBotonModificarUsuario().setEnabled(false);
						this.guiGestionarUsuario.getBotonEliminarUsuario().setEnabled(false);
					}
	        		persistencia.concretarTransaccion();
	        	}
			}else{JOptionPane.showMessageDialog(guiGestionarUsuario, "SELECCIONE ALGUN USUARIO");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	private void opcionModificarUsuario(){
		int fila = this.guiGestionarUsuario.getTabla().getSelectedRow();
		int filaReal = this.guiGestionarUsuario.getTableRowSorter().convertRowIndexToModel(fila);
		if(fila>=0){
			String nomUsuario = (String) this.modeloUsuario.getValueAt(filaReal, 0);
			MediadorModificarUsuario medModificarUsuario = new MediadorModificarUsuario(nomUsuario);
			medModificarUsuario.mostrar();	
		}else{JOptionPane.showMessageDialog(guiGestionarUsuario, "SELECCIONE ALGUN USUARIO");}
	}
	
	private void opcionAceptarUsuario(){}
	
	private void opcionCancelar(){
		this.guiGestionarUsuario.setVisible(false);
		this.guiGestionarUsuario.dispose();
	}	

	
	public void actionPerformed(ActionEvent e) {
		if(this.guiGestionarUsuario.getBotonIngresarUsuario()==e.getSource()){
			this.opcionIngresarUsuario();
		}
		if(this.guiGestionarUsuario.getBotonModificarUsuario()==e.getSource()){
			this.opcionModificarUsuario();
		}
		if(this.guiGestionarUsuario.getBotonEliminarUsuario()==e.getSource()){
			this.opcionEliminarUsuario();
		}
		if(this.guiGestionarUsuario.getBotonAceptarUsuario()==e.getSource()){
			this.opcionAceptarUsuario();
		}
		if(this.guiGestionarUsuario.getBotonCancelar()==e.getSource()){
			this.opcionCancelar();
		}
	}
}
