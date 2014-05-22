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

package cuGestionarVeterinario;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Veterinario;

public class MediadorModificarVeterinario implements ActionListener {
	private GUIVeterinario guiVeterinario;
	private Persistencia persistencia;
	private DefaultTableModel tablaVeterinario;
	private int fila;
	private String idVeterinario;
	private ControlVeterinario controlVeterinario;
	private GUIGestionarVeterinario guiGestionarVeterinario;
	private int filaReal;
	
	public MediadorModificarVeterinario(DefaultTableModel modelo,GUIGestionarVeterinario guiGestionarVeterinario){
		super();
		this.guiGestionarVeterinario = guiGestionarVeterinario;
		this.tablaVeterinario = modelo;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializar() {
		fila = this.guiGestionarVeterinario.getTablaVeterinarios().getSelectedRow();
		this.persistencia = new Persistencia();
		try{
			if(fila>=0){
				this.controlVeterinario = new ControlVeterinario();
				this.guiVeterinario = new GUIVeterinario();
				this.guiVeterinario.setListenerButtons(this);
				this.guiVeterinario.getBotonAgregar().setVisible(false);
				persistencia.iniciarTransaccion();
				filaReal = this.guiGestionarVeterinario.getTableRowSorter().convertRowIndexToModel(fila);
				idVeterinario = (String) this.tablaVeterinario.getValueAt(filaReal, 0);
				Veterinario veterinario = controlVeterinario.obtenerVeterinario(idVeterinario.toLowerCase());
				this.guiVeterinario.getCampoDni().setText(veterinario.getDni().toUpperCase());
				this.guiVeterinario.getCampoDni().setEditable(true);
				this.guiVeterinario.getCampoApellido().setText(veterinario.getApellido().toUpperCase());
				this.guiVeterinario.getCampoTelefono().setText(veterinario.getTelefono()+"");
				this.guiVeterinario.getCampoNombre().setText(veterinario.getNombre().toUpperCase());
				this.guiVeterinario.getCampoMatricula().setText(veterinario.getNumMatricula()+"".toUpperCase());
				this.guiVeterinario.getCampoCuil().setText(veterinario.getCuil().toUpperCase());
				persistencia.concretarTransaccion();
				this.guiVeterinario.show();
			}else{JOptionPane.showMessageDialog(this.guiVeterinario, "SELECCIONE ALGUN VETERINARIO");}
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
			String dni = this.guiVeterinario.getCampoDni().getText().toLowerCase();
			String apellido = this.guiVeterinario.getCampoApellido().getText();
			String tel = this.guiVeterinario.getCampoTelefono().getText();
			if(!(dni.isEmpty() || apellido.isEmpty() || tel.isEmpty())){
				int existeVeterinario = controlVeterinario.existeVeterinario(dni.toLowerCase());
				if(dni.compareTo(idVeterinario.toLowerCase())!=0){
					if(existeVeterinario!=1){
						String cuil ="0";
						if(!this.guiVeterinario.getCampoCuil().getText().isEmpty()){
							cuil=this.guiVeterinario.getCampoCuil().getText();
						}
						String matriculaStr ="0";
						if(!this.guiVeterinario.getCampoMatricula().getText().isEmpty()){
							matriculaStr=this.guiVeterinario.getCampoMatricula().getText();
						}
						long matricula = Integer.parseInt(matriculaStr);
						String nombre="";
						if(!this.guiVeterinario.getCampoNombre().getText().isEmpty()){
							nombre = this.guiVeterinario.getCampoNombre().getText();
						}
						
						Veterinario veterinario = controlVeterinario.obtenerVeterinario(idVeterinario.toLowerCase());
						veterinario.setDni(dni.toLowerCase());
						veterinario.setApellido(apellido.toLowerCase());
						veterinario.setNombre(nombre.toLowerCase());
						veterinario.setTelefono(tel);
						veterinario.setNumMatricula(matricula);
						veterinario.setCuil(cuil.toLowerCase());
						this.tablaVeterinario.setValueAt(dni.toUpperCase(),filaReal,0);
						this.tablaVeterinario.setValueAt(nombre.toUpperCase(),filaReal,1);
						this.tablaVeterinario.setValueAt(apellido.toUpperCase(), filaReal, 2);
						this.tablaVeterinario.setValueAt(tel, filaReal, 3);
						this.tablaVeterinario.setValueAt(matricula+"".toUpperCase(), filaReal, 4);
						this.guiVeterinario.setVisible(false);
						this.guiVeterinario.dispose();
					}else{
						Veterinario vet=controlVeterinario.obtenerVeterinario(dni.toLowerCase());
						if(vet.isEliminado()){
							int opcion = JOptionPane.showConfirmDialog(this.guiVeterinario, "¿DESEA DAR DE ALTA AL MISMO?", "VETERINARIO ELIMINADO", JOptionPane.YES_NO_OPTION);
				        	if(opcion == JOptionPane.YES_OPTION){
				        		vet.setEliminado(false);
								this.tablaVeterinario.addRow(new String[]{vet.getDni().toUpperCase(),vet.getNombre().toUpperCase(),vet.getApellido().toUpperCase(),vet.getTelefono().toUpperCase(),vet.getNumMatricula()+""});
								this.guiVeterinario.setVisible(false);
								this.guiVeterinario.dispose();
				        	}
						}
						else{
							JOptionPane.showMessageDialog(this.guiVeterinario, "EL VETERINARIO YA EXISTE");}
					}
				}else{
					String cuil ="0";
					if(!this.guiVeterinario.getCampoCuil().getText().isEmpty()){
						cuil=this.guiVeterinario.getCampoCuil().getText();
					}
					String matriculaStr ="0";
					if(!this.guiVeterinario.getCampoMatricula().getText().isEmpty()){
						matriculaStr=this.guiVeterinario.getCampoMatricula().getText();
					}
					long matricula = Integer.parseInt(matriculaStr);
					String nombre="";
					if(!this.guiVeterinario.getCampoNombre().getText().isEmpty()){
						nombre = this.guiVeterinario.getCampoNombre().getText();
					}
					
					Veterinario veterinario = controlVeterinario.obtenerVeterinario(idVeterinario.toLowerCase());
					veterinario.setApellido(apellido.toLowerCase());
					veterinario.setNombre(nombre.toLowerCase());
					veterinario.setTelefono(tel);
					veterinario.setNumMatricula(matricula);
					veterinario.setCuil(cuil.toLowerCase());
					this.tablaVeterinario.setValueAt(dni.toUpperCase(),filaReal,0);
					this.tablaVeterinario.setValueAt(nombre.toUpperCase(),filaReal,1);
					this.tablaVeterinario.setValueAt(apellido.toUpperCase(), filaReal, 2);
					this.tablaVeterinario.setValueAt(tel, filaReal, 3);
					this.tablaVeterinario.setValueAt(matricula+"".toUpperCase(), filaReal, 4);
					this.guiVeterinario.setVisible(false);
					this.guiVeterinario.dispose();
				}
			}else{JOptionPane.showMessageDialog(this.guiVeterinario, "LOS CAMPOS: D.N.I., APELLIDO, TELEFONO \n DEBEN ESTAR COMPLETOS ");}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
		
	}

	
	public void actionPerformed(ActionEvent e) {
		if(this.guiVeterinario.getBotonModificar()==e.getSource()){
			this.opcionModificar();
		}	
	}
}
