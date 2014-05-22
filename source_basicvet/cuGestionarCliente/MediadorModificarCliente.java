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

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Cliente;

public class MediadorModificarCliente implements ActionListener {
	private GUICliente guiCliente;
	private Persistencia persistencia;
	private DefaultTableModel tablaCliente;
	private int fila;
	private GUIGestionarCliente guiGestionarCliente;
	private String idCliente;
	private Object source;
	private ControlCliente controlCliente;
	private int filaReal;
	
	public MediadorModificarCliente(DefaultTableModel tabla,GUIGestionarCliente guiGestionarCliente){
		super();
		this.guiGestionarCliente = guiGestionarCliente;
		this.tablaCliente = tabla;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializar() {
		this.persistencia = new Persistencia();
		fila = this.guiGestionarCliente.getTablaClientes().getSelectedRow();
		try{
			if(fila>=0){
				this.controlCliente = new ControlCliente();
				this.guiCliente = new GUICliente();
				this.guiCliente.setListenerButtons(this);
				this.guiCliente.getBotonAgregar().setVisible(false);
				persistencia.iniciarTransaccion();
				filaReal = this.guiGestionarCliente.getTableRowSorter().convertRowIndexToModel(fila);
				idCliente = (String) this.tablaCliente.getValueAt(filaReal, 0);
				Cliente cliente = controlCliente.obtenerCliente(idCliente.toLowerCase());
				this.guiCliente.getCampoDni().setText(cliente.getDni().toUpperCase());
				this.guiCliente.getCampoDni().setEditable(true);
				this.guiCliente.getCampoApellido().setText(cliente.getApellido().toUpperCase());
				this.guiCliente.getCampoTelefono().setText(cliente.getTelefono()+"");
				this.guiCliente.getCampoNombre().setText(cliente.getNombre().toUpperCase());
				this.guiCliente.getCampoCuil().setText(cliente.getCuil().toUpperCase());
				persistencia.concretarTransaccion();
				this.guiCliente.show();
			}else{JOptionPane.showMessageDialog(this.guiCliente, "SELECCIONE ALGUN CLIENTE");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	private void opcionModificar(){
		try{
			persistencia.iniciarTransaccion();
			String dni = this.guiCliente.getCampoDni().getText().toLowerCase();
			String apellido = this.guiCliente.getCampoApellido().getText();
			String tel = this.guiCliente.getCampoTelefono().getText();
			if(!(dni.isEmpty() || apellido.isEmpty() || tel.isEmpty())){
				int existeCliente = controlCliente.existeCliente(dni.toLowerCase());
				if(dni.compareTo(idCliente.toLowerCase())!=0){
					if(existeCliente!=1){
						String cuil ="0";
						if(!this.guiCliente.getCampoCuil().getText().isEmpty()){
							cuil=this.guiCliente.getCampoCuil().getText();
						}
						String nombre="";
						if(!this.guiCliente.getCampoNombre().getText().isEmpty()){
							nombre = this.guiCliente.getCampoNombre().getText();
						}
						Cliente cliente = controlCliente.obtenerCliente(idCliente.toLowerCase());
						cliente.setDni(dni.toLowerCase());
						cliente.setApellido(apellido.toLowerCase());
						cliente.setNombre(nombre.toLowerCase());
						cliente.setTelefono(tel);
						cliente.setCuil(cuil.toLowerCase());
						this.tablaCliente.setValueAt(dni.toUpperCase(),filaReal,0);
						this.tablaCliente.setValueAt(nombre.toUpperCase(),filaReal,1);
						this.tablaCliente.setValueAt(apellido.toUpperCase(), filaReal, 2);
						this.tablaCliente.setValueAt(tel, filaReal, 3);
						this.guiCliente.setVisible(false);
						this.guiCliente.dispose();
					
				}else{
					Cliente cli=controlCliente.obtenerCliente(dni.toLowerCase());
					if(cli.isEliminado()){
						int opcion = JOptionPane.showConfirmDialog(this.guiCliente, "¿DESEA DAR DE ALTA AL MISMO?", "CLIENTE ELIMINADO", JOptionPane.YES_NO_OPTION);
						if(opcion == JOptionPane.YES_OPTION){
							cli.setEliminado(false);
							this.tablaCliente.addRow(new String[]{cli.getDni().toUpperCase(),cli.getNombre().toUpperCase(),cli.getApellido().toUpperCase(),cli.getTelefono().toUpperCase()});
							this.guiCliente.setVisible(false);
							this.guiCliente.dispose();
						}
					}else{JOptionPane.showMessageDialog(this.guiCliente, "EL CLIENTE YA EXISTE");}
				}
				}else{
					String cuil ="0";
					if(!this.guiCliente.getCampoCuil().getText().isEmpty()){
						cuil=this.guiCliente.getCampoCuil().getText();
					}
					String nombre="";
					if(!this.guiCliente.getCampoNombre().getText().isEmpty()){
						nombre = this.guiCliente.getCampoNombre().getText();
					}
					Cliente cliente = controlCliente.obtenerCliente(idCliente.toLowerCase());
					cliente.setApellido(apellido.toLowerCase());
					cliente.setNombre(nombre.toLowerCase());
					cliente.setTelefono(tel);
					cliente.setCuil(cuil.toLowerCase());
					this.tablaCliente.setValueAt(dni.toUpperCase(),filaReal,0);
					this.tablaCliente.setValueAt(nombre.toUpperCase(),filaReal,1);
					this.tablaCliente.setValueAt(apellido.toUpperCase(), filaReal, 2);
					this.tablaCliente.setValueAt(tel, filaReal, 3);
					this.guiCliente.setVisible(false);
					this.guiCliente.dispose();
					
				}
			}else{JOptionPane.showMessageDialog(this.guiCliente, "LOS CAMPOS: D.N.I., APELLIDO, TELEFONO \n DEBEN ESTAR COMPLETOS ");}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		if(this.guiCliente.getBotonModificar()==source){
			this.opcionModificar();
		}
	}
}
