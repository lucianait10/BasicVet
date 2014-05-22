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

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Cliente;

public class MediadorIngresarCliente extends MediadorGeneral implements ActionListener{
	private Persistencia persistencia;
	private GUICliente guiCliente;
	private DefaultTableModel modeloAnimal;
	private DefaultTableModel modeloCliente;
	private Object source;
	private ControlCliente controlCliente;
	
	public MediadorIngresarCliente(DefaultTableModel modeloC) {
		super();
		this.modeloCliente = modeloC;
		inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiCliente.show();
	}

	private void inicializar(){
		this.persistencia = new Persistencia();
		this.controlCliente = new ControlCliente();		
		this.guiCliente = new GUICliente();
		this.guiCliente.getBotonModificar().setVisible(false);
		this.guiCliente.setListenerButtons(this);
	}
	
	private void opcionAgregarCliente(){
		try{
			persistencia.iniciarTransaccion();
			String dni = this.guiCliente.getCampoDni().getText();
			String apellido = this.guiCliente.getCampoApellido().getText();
			String tel = this.guiCliente.getCampoTelefono().getText();
			if(!(dni.isEmpty() ||  apellido.isEmpty() || tel.isEmpty())){
				int existeCliente = controlCliente.existeCliente(dni.toLowerCase());
				if(existeCliente!=1){
					String cuil ="0";
					if(!this.guiCliente.getCampoCuil().getText().isEmpty()){
						cuil=this.guiCliente.getCampoCuil().getText();
					}
					String nombre = "";
					if(!this.guiCliente.getCampoNombre().getText().isEmpty()){
						nombre = this.guiCliente.getCampoNombre().getText();
					}
					
					Cliente cliente = new Cliente(dni.toLowerCase(),nombre.toLowerCase(),apellido.toLowerCase(),tel,cuil.toLowerCase(),false);
					controlCliente.guardarCliente(cliente);
					this.modeloCliente.addRow(new String[]{dni.toUpperCase(),nombre.toUpperCase(),apellido.toUpperCase(),tel});
					this.guiCliente.setVisible(false);
					this.guiCliente.dispose();
				}
				else{
					Cliente cli=controlCliente.obtenerCliente(dni.toLowerCase());
					if(cli.isEliminado()){
						int opcion = JOptionPane.showConfirmDialog(this.guiCliente, "¿DESEA DAR DE ALTA AL MISMO?", "CLIENTE ELIMINADO", JOptionPane.YES_NO_OPTION);
			        	if(opcion == JOptionPane.YES_OPTION){
			        		cli.setEliminado(false);
							this.modeloCliente.addRow(new String[]{cli.getDni().toUpperCase(),cli.getNombre().toUpperCase(),cli.getApellido().toUpperCase(),cli.getTelefono().toUpperCase()});
							this.guiCliente.setVisible(false);
							this.guiCliente.dispose();
			        	}
					}
					else{
						JOptionPane.showMessageDialog(this.guiCliente, "EL CLIENTE YA EXISTE");}
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
	
	public DefaultTableModel getTabla(){
		return this.modeloAnimal;
	}

	
	public void actionPerformed(ActionEvent e){
		source = e.getSource();
		if(this.guiCliente.getBotonAgregar()==source){
			this.opcionAgregarCliente();
		}
	}

}
