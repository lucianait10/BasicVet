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

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Veterinario;

public class MediadorIngresarVeterinario extends MediadorGeneral implements ActionListener{
	private Persistencia persistencia;
	private GUIVeterinario guiVeterinario;
	private DefaultTableModel modeloVeterinario;
	private Object source;
	private ControlVeterinario controlVeterinario;
	
	
	public MediadorIngresarVeterinario(DefaultTableModel modeloP) {
		super();
		this.modeloVeterinario=modeloP;
		inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiVeterinario.show();
	}

	private void inicializar(){
		this.persistencia = new Persistencia();
		this.controlVeterinario = new ControlVeterinario();
		this.guiVeterinario = new GUIVeterinario();
		this.guiVeterinario.getBotonModificar().setVisible(false);
		this.guiVeterinario.setListenerButtons(this);
	}
	
	private void opcionAgregarVeterinario(){
		try{
			persistencia.iniciarTransaccion();
			String dni = this.guiVeterinario.getCampoDni().getText();
			String apellido = this.guiVeterinario.getCampoApellido().getText();
			String tel = this.guiVeterinario.getCampoTelefono().getText();
			if(!(dni.isEmpty() ||  apellido.isEmpty() || tel.isEmpty())){
				int existeVeterinario = controlVeterinario.existeVeterinario(dni.toLowerCase());
				if(existeVeterinario!=1){
					String cuil = "0";
					if(!this.guiVeterinario.getCampoCuil().getText().isEmpty()){
						cuil=this.guiVeterinario.getCampoCuil().getText();
					}
					String matriculaStr ="0";
					if(!this.guiVeterinario.getCampoMatricula().getText().isEmpty()){
						matriculaStr=this.guiVeterinario.getCampoMatricula().getText();
					}
					long matricula = Integer.parseInt(matriculaStr);
					String nombre = "";
					if(!this.guiVeterinario.getCampoNombre().getText().isEmpty()){
						nombre = this.guiVeterinario.getCampoNombre().getText();
					}
					Veterinario veterinario = new Veterinario(dni.toLowerCase(),nombre.toLowerCase(),apellido.toLowerCase(),tel,cuil.toLowerCase(),false,matricula);
					controlVeterinario.guardarVeterinario(veterinario);
					this.modeloVeterinario.addRow(new String[]{dni.toUpperCase(),nombre.toUpperCase(),apellido.toUpperCase(),tel,matricula+""});
					this.guiVeterinario.setVisible(false);
					this.guiVeterinario.dispose();
				}else{
					Veterinario vet=controlVeterinario.obtenerVeterinario(dni.toLowerCase());
					if(vet.isEliminado()){
						int opcion = JOptionPane.showConfirmDialog(this.guiVeterinario, "¿DESEA DAR DE ALTA AL MISMO?", "VETERINARIO ELIMINADO", JOptionPane.YES_NO_OPTION);
			        	if(opcion == JOptionPane.YES_OPTION){
			        		vet.setEliminado(false);
							this.modeloVeterinario.addRow(new String[]{vet.getDni().toUpperCase(),vet.getNombre().toUpperCase(),vet.getApellido().toUpperCase(),vet.getTelefono().toUpperCase(),vet.getNumMatricula()+""});
							this.guiVeterinario.setVisible(false);
							this.guiVeterinario.dispose();
			        	}
					}
					else{
						JOptionPane.showMessageDialog(this.guiVeterinario, "EL VETERINARIO YA EXISTE");}
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
	
	

	
	public void actionPerformed(ActionEvent e){
		source = e.getSource();
		if(this.guiVeterinario.getBotonAgregar()==source){
			this.opcionAgregarVeterinario();
		}
	}
}
