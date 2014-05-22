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
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.Propietario;
import cuGestionarAnimal.MediadorIngresarAnimal;

public class MediadorIngresarPropietario extends MediadorGeneral implements ActionListener{
	private Persistencia persistencia;
	private GUIPropietario guiPropietario;
	private DefaultTableModel modeloAnimal;
	private DefaultTableModel modeloPropietario;
	private Object source;
	private String dni;
	private LinkedList<Animal> listaAnimales = new LinkedList<Animal>();
	private ControlPropietario controlPropietario;
	
	
	public MediadorIngresarPropietario(DefaultTableModel modeloP) {
		super();
		this.modeloPropietario=modeloP;
		inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiPropietario.show();
	}

	private void inicializar(){
		this.persistencia = new Persistencia();
		this.controlPropietario = new ControlPropietario();		
		this.modeloAnimal = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable (int row, int column){return false;}
		};
		this.modeloAnimal.setColumnIdentifiers(new String[]{"ID","NUMERO","NOMBRE","RAZA","ESPECIE","EDAD"});
		this.guiPropietario = new GUIPropietario(this.modeloAnimal);
		this.guiPropietario.getBotonModificar().setVisible(false);
		this.guiPropietario.setListenerButtons(this);
	}
	
	private void opcionAgregarAnimal() {
		if(!this.guiPropietario.getCampoDni().getText().isEmpty()){
			this.dni = this.guiPropietario.getCampoDni().getText().toLowerCase();
			String apellido ="";
			if (!this.guiPropietario.getCampoApellido().getText().isEmpty()){
				apellido=this.guiPropietario.getCampoApellido().getText();
			}
			
			String nombre = "";
			if (!this.guiPropietario.getCampoNombre().getText().isEmpty()){
				nombre=this.guiPropietario.getCampoNombre().getText();
			}
			String direccion = "";
			if (!this.guiPropietario.getCampoDireccion().getText().isEmpty()){
				direccion=this.guiPropietario.getCampoDireccion().getText();
			}
			String telefono  = "";
			if (!this.guiPropietario.getCampoTelefono().getText().isEmpty()){
				telefono=this.guiPropietario.getCampoTelefono().getText();
			}
			String cuil = "0";
			if (!this.guiPropietario.getCampoCuil().getText().isEmpty()){
				cuil=this.guiPropietario.getCampoCuil().getText();
			}
			
			MediadorIngresarAnimal medIngresarAnimal = new MediadorIngresarAnimal(this.modeloAnimal,this.listaAnimales,true);
			medIngresarAnimal.setearId(this.dni,apellido,nombre,direccion,telefono,cuil);
			medIngresarAnimal.setearVisivilidadBotonBuscar(false);
			medIngresarAnimal.mostrar();
		}else{JOptionPane.showMessageDialog(this.guiPropietario, "DNI DEBE SER COMPLETADO");}
	}
	
	private void opcionAgregarPropietario(){
		try{
			persistencia.iniciarTransaccion();
			String dni = this.guiPropietario.getCampoDni().getText();
			String apellido = this.guiPropietario.getCampoApellido().getText();
			String tel = this.guiPropietario.getCampoTelefono().getText();
			if(!(dni.isEmpty() ||  apellido.isEmpty() || tel.isEmpty())){
				int existePropietario = controlPropietario.existePropietario(dni);
				if(existePropietario!=1){
						String cuil = "0";
						if(!this.guiPropietario.getCampoCuil().getText().isEmpty()){
							cuil=this.guiPropietario.getCampoCuil().getText();
						}
						String nombre = "";
						if(!this.guiPropietario.getCampoNombre().getText().isEmpty()){
							nombre = this.guiPropietario.getCampoNombre().getText();
						}
						String direccion = "";
						if(!this.guiPropietario.getCampoDireccion().getText().isEmpty()){
							direccion = this.guiPropietario.getCampoDireccion().getText();
						}
						Propietario propietario = new Propietario(dni.toLowerCase(),nombre.toLowerCase(),apellido.toLowerCase(),tel,cuil.toLowerCase(),direccion.toLowerCase(),false);
						controlPropietario.guardarPropietario(propietario);
						for(int i=0;i<listaAnimales.size();i++){
							Animal animal = this.listaAnimales.get(i);
							animal.setPropietario(propietario);
							propietario.agregarAnimal(animal);
						}
						this.modeloPropietario.addRow(new String[]{dni.toUpperCase(),nombre.toUpperCase(),apellido.toUpperCase(),tel,direccion.toUpperCase()});
						this.guiPropietario.setVisible(false);
						this.guiPropietario.dispose();
				}else{
					Propietario prop=controlPropietario.obtenerPropietario(dni.toLowerCase());
					if(prop.isEliminado()){
						int opcion = JOptionPane.showConfirmDialog(this.guiPropietario, "¿DESEA DAR DE ALTA AL MISMO?", "PROPIETARIO ELIMINADO", JOptionPane.YES_NO_OPTION);
				       	if(opcion == JOptionPane.YES_OPTION){
				       		prop.setEliminado(false);
							this.modeloPropietario.addRow(new String[]{prop.getDni().toUpperCase(),prop.getNombre().toUpperCase(),prop.getApellido().toUpperCase(),prop.getTelefono().toUpperCase(),prop.getDireccion().toUpperCase()});
							this.guiPropietario.setVisible(false);
							this.guiPropietario.dispose();
				       	}
					}else{JOptionPane.showMessageDialog(this.guiPropietario, "EL PROPIETARIO YA EXISTE");}
				}
			}else{JOptionPane.showMessageDialog(this.guiPropietario, "LOS CAMPOS: D.N.I., APELLIDO, TELEFONO \n DEBEN ESTAR COMPLETOS ");}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public LinkedList<Animal> getListaAnimales(){
		return this.listaAnimales;
	}
	
	public DefaultTableModel getTabla(){
		return this.modeloAnimal;
	}

	
	public void actionPerformed(ActionEvent e){
		source = e.getSource(); 
		if(this.guiPropietario.getBotonAgregarAnimal()== source){
			this.opcionAgregarAnimal();
		}
		if(this.guiPropietario.getBotonAgregar()==source){
			this.opcionAgregarPropietario();
		}
	}
}
