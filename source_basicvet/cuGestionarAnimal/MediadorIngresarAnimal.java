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

package cuGestionarAnimal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.Especie;
import persistencia.dominio.Propietario;
import persistencia.dominio.Raza;
import clasesComunes.ControlEspecie;
import clasesComunes.ControlRaza;
import cuGestionarPropietario.ControlPropietario;
import cuGestionarPropietario.MediadorBuscarPropietario;

public class MediadorIngresarAnimal extends MediadorGeneral implements ActionListener {
	private Persistencia persistencia;
	private GUIAnimal guiAnimal;
	private Object source;
	private DefaultTableModel modelo;
	private String dni;
	private boolean esPropietario;
	private LinkedList<Animal> listaAnimales;
	private ControlEspecie controlEspecie;  
	private ControlAnimal controlAnimal;
	private ControlPropietario controlPropietario;
	private ControlRaza controlRaza;

	
	
	public MediadorIngresarAnimal(DefaultTableModel m, LinkedList<Animal> l,boolean esPropietario){
		super();
		this.listaAnimales = l;
		this.modelo = m;
		this.esPropietario = esPropietario;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiAnimal.show();
	}
	
	private void inicializar(){
		this.persistencia = new Persistencia();
		this.controlRaza = new ControlRaza();
		this.controlEspecie = new ControlEspecie();
		this.controlAnimal = new ControlAnimal();
		this.controlPropietario = new ControlPropietario();
		this.guiAnimal = new GUIAnimal();
		this.guiAnimal.getBotonModificar().setVisible(false);
		this.guiAnimal.getBotonEliminar().setVisible(false);
		this.cargarEspecies();
		this.cargarRazas(this.guiAnimal.getCampoEspecie().getSelectedItem().toString().toLowerCase());
		if(!this.esPropietario)this.guiAnimal.getBotonAgregar().setEnabled(false);
		this.guiAnimal.setListenerButtons(this);
	}
	
	
	public void cargarEspecies(){
		try{
			persistencia.iniciarTransaccion();
			Iterator<Especie> iterEspecie = controlEspecie.obtenerEspecies();
			while(iterEspecie.hasNext()){
				Especie motivo = iterEspecie.next();
				this.guiAnimal.getCampoEspecie().addItem(motivo.getNombre().toUpperCase());
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	
	public void cargarRazas(String nombreEspecie){
		try{
			persistencia.iniciarTransaccion();
			this.guiAnimal.getCampoRaza().removeAllItems();
			Raza raza;
			Especie especie = controlEspecie.obtenerEspecie(nombreEspecie);
			Iterator<Raza> iterRazas = especie.obtenerRazas().iterator();
			while(iterRazas.hasNext()){
				raza = iterRazas.next();
				this.guiAnimal.getCampoRaza().addItem(raza.getNombre().toUpperCase());
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	
	private void opcionAgregarEspecie(){
		String nuevoEspecie = JOptionPane.showInputDialog("INGRESE UNA NUEVA ESPECIE");
		try{
			if(nuevoEspecie.length()>0){
				persistencia.iniciarTransaccion();
				int existeEspecie = controlEspecie.existeEspecie(nuevoEspecie.toLowerCase());
				if(existeEspecie!=1){
					Especie especie = new Especie(nuevoEspecie.toLowerCase());
					controlEspecie.guardarEspecie(especie);
					
					this.guiAnimal.getCampoEspecie().addItem(nuevoEspecie.toUpperCase());
				}else{JOptionPane.showMessageDialog(this.guiAnimal,"LA ESPECIE YA EXISTE");}
				persistencia.concretarTransaccion();
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	
	private void opcionAgregarRaza() {
		String especieSeleccionada = guiAnimal.getCampoEspecie().getSelectedItem().toString();
		String nuevoRaza = JOptionPane.showInputDialog("INGRESE UNA NUEVA RAZA");
		try{
			if(nuevoRaza.length()>0 && especieSeleccionada.length()>0){
				persistencia.iniciarTransaccion();
				int existeRaza = controlRaza.existeRaza(nuevoRaza.toLowerCase());
				if(existeRaza!=1){
					Raza raza = new Raza(nuevoRaza);
					Especie especie = controlEspecie.obtenerEspecie(especieSeleccionada.toLowerCase());
					especie.agregarRazas(raza);
					
					this.guiAnimal.getCampoRaza().addItem(nuevoRaza.toUpperCase());
				}else{JOptionPane.showMessageDialog(this.guiAnimal,"LA RAZA YA EXISTE");}
				persistencia.concretarTransaccion();
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	
	public void setPropietarioBuscado(String idPropietarioBuscado){
		try{
			persistencia.iniciarTransaccion();
			Propietario propietario  = controlPropietario.obtenerPropietario(idPropietarioBuscado+"");
			this.guiAnimal.getCampoDniPropietario().setText(propietario.getDni());
			this.guiAnimal.getCampoNombrePropietario().setText(propietario.getNombre());
			this.guiAnimal.getCampoApellidoPropietario().setText(propietario.getApellido());
			this.guiAnimal.getCampoCuilPropietario().setText(propietario.getCuil());
			this.guiAnimal.getCampoDireccionPropietario().setText(propietario.getDireccion());
			this.guiAnimal.getCampoTelefonoPropietario().setText(propietario.getTelefono());
			this.guiAnimal.getBotonAgregar().setEnabled(true);
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	

	private void opcionAgregar() {
		try{
			persistencia.iniciarTransaccion();
			String nombre = this.guiAnimal.getCampoNombre().getText();
			String raza = (String)this.guiAnimal.getCampoRaza().getSelectedItem();
			String especie = this.guiAnimal.getCampoEspecie().getSelectedItem().toString();
			String edadStr = this.guiAnimal.getCampoEdad().getText();
			String sexo = (String)this.guiAnimal.getCampoSexo().getSelectedItem();
			if(!(nombre.isEmpty() || raza.isEmpty() || especie.isEmpty() || edadStr.isEmpty() || sexo.isEmpty())){
				this.dni = this.guiAnimal.getCampoDniPropietario().getText().toLowerCase();
				String numero = dni.toLowerCase()+this.guiAnimal.getCampoNombre().getText().toLowerCase();
				if(controlAnimal.existeAnimal(numero)!=1){
					int edad = Integer.parseInt(edadStr);
					Date nacimiento = this.guiAnimal.getCampoNacimiento().getDate();
					Animal animal = new Animal(numero.toLowerCase(),nombre.toLowerCase(),edad,nacimiento,raza.toLowerCase(),especie.toLowerCase(),sexo.toLowerCase());
					if(!esPropietario){
						animal=controlAnimal.guardarAnimal(animal);
						Propietario prop = controlPropietario.obtenerPropietario(this.dni);
						animal.setPropietario(prop);
						prop.agregarAnimal(animal);
					}else this.listaAnimales.addFirst(animal);
					this.modelo.addRow(new String[]{animal.getId()+"",numero.toUpperCase(),nombre.toUpperCase(),raza.toUpperCase(),especie.toUpperCase(),edad+""});
					this.modelo.fireTableDataChanged();
					this.guiAnimal.setVisible(false);
					this.guiAnimal.dispose();
					
				}else{
					int opcion = JOptionPane.showConfirmDialog(this.guiAnimal, "¿DESEA REVISAR LA LISTA DE ANIMALES?", "NUMERO DE ANIMAL EXISTENTE", JOptionPane.YES_NO_OPTION);
		        	if(opcion == JOptionPane.YES_OPTION){
		        		MediadorRecuperar medRecup=new MediadorRecuperar(numero,this);
		        		medRecup.mostrar();
		        		this.guiAnimal.setVisible(false);
						this.guiAnimal.dispose();
		        	}
		        	else{
		        		JOptionPane.showMessageDialog(this.guiAnimal, "SE AGREGA UN NUEVO ANIMAL CON NUMERO: "+numero.toUpperCase());
		        		int edad = Integer.parseInt(edadStr);
						Date nacimiento = this.guiAnimal.getCampoNacimiento().getDate();
						Animal animal = new Animal(numero.toLowerCase(),nombre.toLowerCase(),edad,nacimiento,raza.toLowerCase(),especie.toLowerCase(),sexo.toLowerCase());
						if(!esPropietario){
							animal=controlAnimal.guardarAnimal(animal);
							
							Propietario prop = controlPropietario.obtenerPropietario(this.dni);
							animal.setPropietario(prop);
							prop.agregarAnimal(animal);
						}else this.listaAnimales.addFirst(animal);
						this.modelo.addRow(new String[]{animal.getId()+"",numero.toUpperCase(),nombre.toUpperCase(),raza.toUpperCase(),especie.toUpperCase(),edad+""});
						this.modelo.fireTableDataChanged();
						this.guiAnimal.setVisible(false);
						this.guiAnimal.dispose();
		        		
		        	}
		        	
				}
			}else{JOptionPane.showMessageDialog(this.guiAnimal, "LOS CAMPOS: NOMBRE, RAZA, ESPECIE, EDAD, SEXO \n DEBEN ESTAR COMPLETOS ");}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	
	
	public void setAnimalBuscado(int id){
		try{
			persistencia.iniciarTransaccion();
			Animal a=controlAnimal.obtenerAnimal(id);
			this.modelo.addRow(new String[]{a.getId()+"",a.getNro().toUpperCase(),a.getNombre().toUpperCase(),a.getRaza().toUpperCase(),a.getEspecie().toUpperCase(),a.getEdad()+""});
			this.modelo.fireTableDataChanged();
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
		
	}
	
	private void opcionBuscarPropietario(){
		MediadorBuscarPropietario medBuscarPropietario = new MediadorBuscarPropietario(this);
		medBuscarPropietario.mostrar();
	}
	
	public void setearId(String dni,String apellido,String nombre,String direccion,String telefono,String cuil){
		this.guiAnimal.getCampoDniPropietario().setText(dni.toUpperCase());
		this.guiAnimal.getCampoApellidoPropietario().setText(apellido.toUpperCase());
		this.guiAnimal.getCampoCuilPropietario().setText(cuil.toUpperCase());
		this.guiAnimal.getCampoNombrePropietario().setText(nombre.toUpperCase());
		this.guiAnimal.getCampoTelefonoPropietario().setText(telefono.toUpperCase());
		this.guiAnimal.getCampoDireccionPropietario().setText(direccion.toUpperCase());
	}
	
	public void setearVisivilidadBotonBuscar(boolean valor){
		this.guiAnimal.getBotonBuscarPropietario().setVisible(valor);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		if(this.guiAnimal.getBotonBuscarPropietario()==source){
			this.opcionBuscarPropietario();
		}
		if(this.guiAnimal.getBotonAgregar()==source){
			this.opcionAgregar();
		}
		if(this.guiAnimal.getBotonAgregarEspecie()==source){
			this.opcionAgregarEspecie();
		}
		if(this.guiAnimal.getBotonAgregarRaza()==source){
			this.opcionAgregarRaza();
		}
		if(this.guiAnimal.getCampoEspecie().getSelectedIndex()>0){
			int fila = this.guiAnimal.getCampoEspecie().getSelectedIndex();
			String nombreEspecie = (String) this.guiAnimal.getCampoEspecie().getItemAt(fila);
			this.cargarRazas(nombreEspecie.toLowerCase());
		}
	}
}
