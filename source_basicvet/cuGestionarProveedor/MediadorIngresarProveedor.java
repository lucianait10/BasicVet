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

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Proveedor;

public class MediadorIngresarProveedor extends MediadorGeneral implements ActionListener{
	private Persistencia persistencia;
	private GUIProveedor guiProveedor;
	private DefaultTableModel modeloProveedor;
	private Object source;
	private ControlProveedor controlProveedor;
	
	public MediadorIngresarProveedor(DefaultTableModel modeloP) {
		super();
		this.modeloProveedor = modeloP;
		inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiProveedor.show();
	}

	private void inicializar(){
		this.persistencia = new Persistencia();
		this.controlProveedor = new ControlProveedor();		
		this.guiProveedor = new GUIProveedor();
		this.guiProveedor.getBotonModificar().setVisible(false);
		this.guiProveedor.setListenerButtons(this);
	}
	
	private void opcionAgregarProveedor(){
		try{
			persistencia.iniciarTransaccion();
			String dni = this.guiProveedor.getCampoDni().getText();
			String apellido = this.guiProveedor.getCampoApellido().getText();
			String tel = this.guiProveedor.getCampoTelefono().getText();
			if(!(dni.isEmpty() ||  apellido.isEmpty() || tel.isEmpty())){
				int existeProveedor = controlProveedor.existeProveedor(dni.toLowerCase());
				if(existeProveedor!=1){
					String cuil = "0";
					if(!this.guiProveedor.getCampoCuil().getText().isEmpty()){
						cuil=this.guiProveedor.getCampoCuil().getText();
					}
					String nombre="";
					if(!this.guiProveedor.getCampoNombre().getText().isEmpty()){
						nombre = this.guiProveedor.getCampoNombre().getText();
					}
					Proveedor proveedor = new Proveedor(dni.toLowerCase(),nombre.toLowerCase(),apellido.toLowerCase(),tel,cuil.toLowerCase(),false);
					controlProveedor.guardarProveedor(proveedor);
					this.modeloProveedor.addRow(new String[]{dni.toUpperCase(),nombre.toUpperCase(),apellido.toUpperCase(),tel});
					this.guiProveedor.setVisible(false);
					this.guiProveedor.dispose();
				}
				else{
					Proveedor prov=controlProveedor.obtenerProveedor(dni.toLowerCase());
					if(prov.isEliminado()){
						int opcion = JOptionPane.showConfirmDialog(this.guiProveedor, "¿DESEA DAR DE ALTA AL MISMO?", "PROVEEDOR ELIMINADO", JOptionPane.YES_NO_OPTION);
				       	if(opcion == JOptionPane.YES_OPTION){
				       		prov.setEliminado(false);
							this.modeloProveedor.addRow(new String[]{prov.getDni().toUpperCase(),prov.getNombre().toUpperCase(),prov.getApellido().toUpperCase(),prov.getTelefono().toUpperCase()});
							this.guiProveedor.setVisible(false);
							this.guiProveedor.dispose();
				       	}
					}
					else{
						JOptionPane.showMessageDialog(this.guiProveedor, "EL PROVEEDOR YA EXISTE");}
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
	

	
	public void actionPerformed(ActionEvent e){
		source = e.getSource();
		if(this.guiProveedor.getBotonAgregar()==source){
			this.opcionAgregarProveedor();
		}
	}

}
