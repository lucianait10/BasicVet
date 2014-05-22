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

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Proveedor;


public class MediadorModificarProveedor implements ActionListener {
	private GUIProveedor guiProveedor;
	private GUIGestionarProveedor guiGestionarProveedor;
	private Persistencia persistencia;
	private DefaultTableModel tablaProveedor;
	private int fila;
	private String idProveedor;
	private Object source;
	private ControlProveedor controlProveedor;
	private int filaReal;
	
	public MediadorModificarProveedor(DefaultTableModel tabla,GUIGestionarProveedor guiGestionarProveedor){
		super();
		this.guiGestionarProveedor = guiGestionarProveedor;
		this.tablaProveedor = tabla;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializar() {
		fila = this.guiGestionarProveedor.getTablaProveedores().getSelectedRow();
		this.persistencia = new Persistencia();
		try{
			if(fila>=0){
				this.controlProveedor = new ControlProveedor();
				this.guiProveedor = new GUIProveedor();
				this.guiProveedor.setListenerButtons(this);
				this.guiProveedor.getBotonAgregar().setVisible(false);
				persistencia.iniciarTransaccion();
				filaReal = this.guiGestionarProveedor.getTableRowSorter().convertRowIndexToModel(fila);
				idProveedor = (String) this.tablaProveedor.getValueAt(filaReal, 0);
				Proveedor proveedor = controlProveedor.obtenerProveedor(idProveedor.toLowerCase());
				this.guiProveedor.getCampoDni().setText(proveedor.getDni().toUpperCase());
				this.guiProveedor.getCampoDni().setEditable(true);
				this.guiProveedor.getCampoApellido().setText(proveedor.getApellido().toUpperCase());
				this.guiProveedor.getCampoTelefono().setText(proveedor.getTelefono()+"");
				this.guiProveedor.getCampoNombre().setText(proveedor.getNombre().toUpperCase());
				this.guiProveedor.getCampoCuil().setText(proveedor.getCuil().toUpperCase());
				persistencia.concretarTransaccion();
				this.guiProveedor.show();
			}else{JOptionPane.showMessageDialog(this.guiProveedor, "SELECCIONE ALGUN PROVEEDOR");}
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
			String dni = this.guiProveedor.getCampoDni().getText().toLowerCase();
			String apellido = this.guiProveedor.getCampoApellido().getText();
			String tel = this.guiProveedor.getCampoTelefono().getText();
			if(!(dni.isEmpty() || apellido.isEmpty() || tel.isEmpty())){
				int existeVeterinario = controlProveedor.existeProveedor(dni.toLowerCase());
				if(dni.compareTo(idProveedor.toLowerCase())!=0){
					if(existeVeterinario!=1){
						String cuil ="0";
						if(!this.guiProveedor.getCampoCuil().getText().isEmpty()){
							cuil=this.guiProveedor.getCampoCuil().getText();
						}
						String nombre="";
						if(!this.guiProveedor.getCampoNombre().getText().isEmpty()){
							nombre = this.guiProveedor.getCampoNombre().getText();
						}
						Proveedor proveedor = controlProveedor.obtenerProveedor(idProveedor.toLowerCase());
						proveedor.setDni(dni.toLowerCase());
						proveedor.setApellido(apellido.toLowerCase());
						proveedor.setNombre(nombre.toLowerCase());
						proveedor.setTelefono(tel);
						proveedor.setCuil(cuil.toLowerCase());
						this.tablaProveedor.setValueAt(dni.toUpperCase(),filaReal,0);
						this.tablaProveedor.setValueAt(nombre.toUpperCase(),filaReal,1);
						this.tablaProveedor.setValueAt(apellido.toUpperCase(), filaReal, 2);
						this.tablaProveedor.setValueAt(tel, filaReal, 3);
						this.guiProveedor.setVisible(false);
						this.guiProveedor.dispose();
					}else{
						Proveedor pro=controlProveedor.obtenerProveedor(dni.toLowerCase());
						if(pro.isEliminado()){
							int opcion = JOptionPane.showConfirmDialog(this.guiProveedor, "¿DESEA DAR DE ALTA AL MISMO?", "PROVEEDOR ELIMINADO", JOptionPane.YES_NO_OPTION);
				        	if(opcion == JOptionPane.YES_OPTION){
				        		pro.setEliminado(false);
								this.tablaProveedor.addRow(new String[]{pro.getDni().toUpperCase(),pro.getNombre().toUpperCase(),pro.getApellido().toUpperCase(),pro.getTelefono().toUpperCase()});
								this.guiProveedor.setVisible(false);
								this.guiProveedor.dispose();
				        	}
						}else{JOptionPane.showMessageDialog(this.guiProveedor, "EL PROVEEDOR YA EXISTE");}
					}
				}else{
					String cuil ="0";
					if(!this.guiProveedor.getCampoCuil().getText().isEmpty()){
						cuil=this.guiProveedor.getCampoCuil().getText();
					}
					String nombre="";
					if(!this.guiProveedor.getCampoNombre().getText().isEmpty()){
						nombre = this.guiProveedor.getCampoNombre().getText();
					}
					Proveedor proveedor = controlProveedor.obtenerProveedor(idProveedor.toLowerCase());
					proveedor.setDni(dni.toLowerCase());
					proveedor.setApellido(apellido.toLowerCase());
					proveedor.setNombre(nombre.toLowerCase());
					proveedor.setTelefono(tel);
					proveedor.setCuil(cuil.toLowerCase());
					this.tablaProveedor.setValueAt(dni.toUpperCase(),filaReal,0);
					this.tablaProveedor.setValueAt(nombre.toUpperCase(),filaReal,1);
					this.tablaProveedor.setValueAt(apellido.toUpperCase(), filaReal, 2);
					this.tablaProveedor.setValueAt(tel, filaReal, 3);
					this.guiProveedor.setVisible(false);
					this.guiProveedor.dispose();
				}
			}else{JOptionPane.showMessageDialog(this.guiProveedor, "LOS CAMPOS: D.N.I., APELLIDO, TELEFONO \n DEBEN ESTAR COMPLETOS ");}
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
		if(this.guiProveedor.getBotonModificar()==source){
			this.opcionModificar();
		}
	}
}
