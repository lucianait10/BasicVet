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
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.Propietario;
import cuGestionarAnimal.MediadorIngresarAnimal;

public class MediadorModificarPropietario implements ActionListener {
	private GUIPropietario guiPropietario;
	private Persistencia persistencia;
	private DefaultTableModel tablaPropietario;
	private int fila;
	private DefaultTableModel modeloAnimal;
	private String idPropietario;
	private LinkedList<Animal> listaAimales = new LinkedList<Animal>();
	private Object source;
	private ControlPropietario controlPropietario;
	private GUIGestionarPropietario guiGestionarPropietario;
	private int filaReal;
	private String dni;
	
	public MediadorModificarPropietario(DefaultTableModel modelo,GUIGestionarPropietario guiGestionarPropietario){
		super();
		this.guiGestionarPropietario = guiGestionarPropietario;
		this.tablaPropietario = modelo;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializar() {
		fila = this.guiGestionarPropietario.getTablaPropietarios().getSelectedRow();
		this.persistencia = new Persistencia();
		try{
			persistencia.iniciarTransaccion();
			if(fila>=0){
				this.modeloAnimal = new DefaultTableModel(){
					private static final long serialVersionUID = 1L;
					
					public boolean isCellEditable (int row, int column){return false;}
				};
				this.controlPropietario = new ControlPropietario();
				this.modeloAnimal.setColumnIdentifiers(new String[]{"ID","NUMERO","NOMBRE","RAZA","ESPECIE","EDAD"});
				this.guiPropietario = new GUIPropietario(this.modeloAnimal);
				this.guiPropietario.setListenerButtons(this);
				this.guiPropietario.getBotonAgregar().setVisible(false);
				
				filaReal = this.guiGestionarPropietario.getTableRowSorter().convertRowIndexToModel(fila);
				idPropietario = (String) this.tablaPropietario.getValueAt(filaReal, 0);
				Propietario propietario = controlPropietario.obtenerPropietario(idPropietario.toLowerCase());
				this.guiPropietario.getCampoDni().setText(propietario.getDni().toUpperCase());
				this.guiPropietario.getCampoDni().setEditable(true);
				this.guiPropietario.getCampoApellido().setText(propietario.getApellido().toUpperCase());
				this.guiPropietario.getCampoTelefono().setText(propietario.getTelefono()+"");
				this.guiPropietario.getCampoNombre().setText(propietario.getNombre().toUpperCase());
				this.guiPropietario.getCampoDireccion().setText(propietario.getDireccion().toUpperCase());
				this.guiPropietario.getCampoCuil().setText(propietario.getCuil().toUpperCase());
				Collection<Animal> colAnimal = propietario.obtenerAnimales();
				Iterator<Animal> iterAnimales = colAnimal.iterator();
				while(iterAnimales.hasNext()){
					Animal animal = iterAnimales.next();
	
					if(!animal.isEliminado()){
						int id =animal.getId();
						String numero = animal.getNro().toUpperCase();
						String nombre = animal.getNombre().toUpperCase();
						String raza = animal.getRaza().toUpperCase();
						String especie = animal.getEspecie().toUpperCase();
						int edad = animal.getEdad();
						this.modeloAnimal.addRow(new String[]{id+"",numero,nombre,raza,especie,edad+""});
					}
				}
				persistencia.concretarTransaccion();
				
				this.guiPropietario.show();
			}else{JOptionPane.showMessageDialog(this.guiPropietario, "SELECCIONE ALGUN PROPIETARIO");}
			
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
			String dni = this.guiPropietario.getCampoDni().getText();
			String apellido = this.guiPropietario.getCampoApellido().getText();
			String tel = this.guiPropietario.getCampoTelefono().getText();
			if(!(dni.isEmpty() || apellido.isEmpty() || tel.isEmpty())){
				int existePropietario = controlPropietario.existePropietario(dni);
				if(dni.compareTo(idPropietario)!=0){
					if(existePropietario!=1 ){
							String cuil = "0";
							if(!this.guiPropietario.getCampoCuil().getText().isEmpty()){
								cuil=this.guiPropietario.getCampoCuil().getText();
							}
							String nombre = "";
							if(!this.guiPropietario.getCampoNombre().getText().isEmpty()){
								nombre = this.guiPropietario.getCampoNombre().getText();
							}
							String direccion = "";
							if(!this.guiPropietario.getCampoNombre().getText().isEmpty()){
								direccion = this.guiPropietario.getCampoNombre().getText();
							}
							Propietario propietario = controlPropietario.obtenerPropietario(idPropietario.toLowerCase());
							propietario.setDni(dni.toLowerCase());
							propietario.setApellido(apellido.toLowerCase());
							propietario.setNombre(nombre.toLowerCase());
							propietario.setTelefono(tel);
							propietario.setDireccion(direccion.toLowerCase());
							propietario.setCuil(cuil.toLowerCase());
							
							for(int i=0;i<listaAimales.size();i++){
								Animal animal = this.listaAimales.get(i);
								animal.setPropietario(propietario);
								propietario.agregarAnimal(animal);
							}
							
							Collection<Animal> colAni=propietario.obtenerAnimales();
							Iterator<Animal> iterAni=colAni.iterator();
							
							while(iterAni.hasNext()){
								Animal animal = iterAni.next();
								animal.setNro((dni.toLowerCase()+animal.getNombre()).toLowerCase());					
							}
							this.tablaPropietario.setValueAt(dni.toUpperCase(),filaReal,0);
							this.tablaPropietario.setValueAt(nombre.toUpperCase(),filaReal,1);
							this.tablaPropietario.setValueAt(apellido.toUpperCase(), filaReal, 2);
							this.tablaPropietario.setValueAt(tel, filaReal, 3);
							this.tablaPropietario.setValueAt(direccion.toUpperCase(), filaReal, 4);
							this.guiPropietario.setVisible(false);
							this.guiPropietario.dispose();
					}else{
						Propietario prop=controlPropietario.obtenerPropietario(dni.toLowerCase());
						if(prop.isEliminado()){
							int opcion = JOptionPane.showConfirmDialog(this.guiPropietario, "¿DESEA DAR DE ALTA AL MISMO?", "PROPIETARIO ELIMINADO", JOptionPane.YES_NO_OPTION);
					       	if(opcion == JOptionPane.YES_OPTION){
					       		prop.setEliminado(false);
								this.tablaPropietario.addRow(new String[]{prop.getDni().toUpperCase(),prop.getNombre().toUpperCase(),prop.getApellido().toUpperCase(),prop.getTelefono().toUpperCase(),prop.getDireccion().toUpperCase()});
								this.guiPropietario.setVisible(false);
								this.guiPropietario.dispose();
					       	}
						}else{JOptionPane.showMessageDialog(this.guiPropietario, "EL PROPIETARIO YA EXISTE");}
					}
			}else{			
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
					Propietario propietario = controlPropietario.obtenerPropietario(idPropietario.toLowerCase());
					propietario.setApellido(apellido.toLowerCase());
					propietario.setNombre(nombre.toLowerCase());
					propietario.setTelefono(tel);
					propietario.setDireccion(direccion.toLowerCase());
					propietario.setCuil(cuil.toLowerCase());
					
					for(int i=0;i<listaAimales.size();i++){
						Animal animal = this.listaAimales.get(i);
						animal.setPropietario(propietario);
						propietario.agregarAnimal(animal);
					}
					
					Collection<Animal> colAni=propietario.obtenerAnimales();
					Iterator<Animal> iterAni=colAni.iterator();
					while(iterAni.hasNext()){
						Animal animal = iterAni.next();
						
						animal.setNro((dni.toLowerCase()+animal.getNombre()).toLowerCase());					
					}
					this.tablaPropietario.setValueAt(dni.toUpperCase(),filaReal,0);
					this.tablaPropietario.setValueAt(nombre.toUpperCase(),filaReal,1);
					this.tablaPropietario.setValueAt(apellido.toUpperCase(), filaReal, 2);
					this.tablaPropietario.setValueAt(tel, filaReal, 3);
					this.tablaPropietario.setValueAt(direccion.toUpperCase(), filaReal, 4);
					persistencia.concretarTransaccion();
					this.guiPropietario.setVisible(false);
					this.guiPropietario.dispose();
				}
			}else{JOptionPane.showMessageDialog(this.guiPropietario, "LOS CAMPOS: D.N.I., APELLIDO, TELEFONO \n DEBEN ESTAR COMPLETOS ");}
			
			
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		if(this.guiPropietario.getBotonModificar()==source){
			this.opcionModificar();
		}
		if(this.guiPropietario.getBotonAgregarAnimal()==e.getSource()){
			this.opcionAgregarAnimal();
		}
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
			
			MediadorIngresarAnimal medIngresarAnimal = new MediadorIngresarAnimal(this.modeloAnimal,this.listaAimales,true);
			medIngresarAnimal.setearId(this.dni,apellido,nombre,direccion,telefono,cuil);
			medIngresarAnimal.setearVisivilidadBotonBuscar(false);
			medIngresarAnimal.mostrar();
		}else{JOptionPane.showMessageDialog(this.guiPropietario, "DNI DEBE SER COMPLETADO");}
	}
}
