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
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.Especie;
import persistencia.dominio.Propietario;
import persistencia.dominio.Raza;
import clasesComunes.ControlEspecie;
import clasesComunes.ControlRaza;
import cuGestionarPropietario.ControlPropietario;

public class MediadorModificarAnimal implements ActionListener{
	private GUIAnimal guiAnimal;
	private int idAnimal;
	private Persistencia persistencia = new Persistencia();
	private JTable tablaAnimal;
	private Object source;
	private int fila;
	private ControlAnimal controlAnimal;  
	private ControlPropietario controlPropietario;
	private ControlEspecie controlEspecie;
	private ControlRaza controlRaza;
	
	public MediadorModificarAnimal(JTable tablaAnimal){
		super();
		this.tablaAnimal = tablaAnimal;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializar(){
		fila = this.tablaAnimal.getSelectedRow();
		try{
			if(fila>=0){
				this.controlAnimal = new ControlAnimal();
				this.controlPropietario = new ControlPropietario();
				this.controlEspecie = new ControlEspecie();
				this.controlRaza = new ControlRaza();
				this.guiAnimal = new GUIAnimal();
				this.guiAnimal.setListenerButtons(this);
				this.guiAnimal.getBotonBuscarPropietario().setVisible(false);
				this.guiAnimal.getBotonEliminar().setVisible(false);
				this.guiAnimal.getBotonAgregar().setVisible(false);
				persistencia.iniciarTransaccion();
				this.cargarEspecies();
				idAnimal = Integer.parseInt((String) this.tablaAnimal.getValueAt(fila, 0));
				Animal animal = controlAnimal.obtenerAnimal(idAnimal);
				String nombre = animal.getNombre();
				int id= animal.getId();
				this.guiAnimal.getCampoId().setText(id+"");
				this.guiAnimal.getCampoNumero().setText(animal.getNro().toUpperCase());
				this.guiAnimal.getCampoNombre().setText(nombre.toUpperCase());//nombre se puede modificar
				this.guiAnimal.getCampoEspecie().setSelectedItem(animal.getEspecie().toUpperCase());
				this.cargarRazas(animal.getEspecie());
				this.guiAnimal.getCampoRaza().setSelectedItem(animal.getRaza().toUpperCase());
				this.guiAnimal.getCampoSexo().setSelectedItem(animal.getSexo().toUpperCase());
				this.guiAnimal.getCampoEdad().setText(animal.getEdad()+"");
				this.guiAnimal.getCampoNacimiento().setDate(animal.getFechaNac());
				String dni = animal.getPropietario().getDni();
				this.guiAnimal.getCampoDniPropietario().setText(dni);
				Propietario propietario = controlPropietario.obtenerPropietario(dni);
				this.guiAnimal.getCampoDniPropietario().setText(propietario.getDni());
				this.guiAnimal.getCampoNombrePropietario().setText(propietario.getNombre());
				this.guiAnimal.getCampoApellidoPropietario().setText(propietario.getApellido());
				this.guiAnimal.getCampoDireccionPropietario().setText(propietario.getDireccion());
				this.guiAnimal.getCampoCuilPropietario().setText(propietario.getCuil());
				this.guiAnimal.getCampoTelefonoPropietario().setText(propietario.getTelefono()+"");
				persistencia.concretarTransaccion();
				this.guiAnimal.show();
			}else{JOptionPane.showMessageDialog(this.guiAnimal, "SELECCIONE ALGUN ANIMAL");	}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	

	private void cargarEspecies(){
		Iterator<Especie> iterEspecie = this.controlEspecie.obtenerEspecies();
		while(iterEspecie.hasNext()){
			Especie motivo = iterEspecie.next();
			this.guiAnimal.getCampoEspecie().addItem(motivo.getNombre().toUpperCase());
		}
	}
	
	
	public void cargarRazas(String nombreEspecie){
		// MLucero comentando la persistencia funciona para eliminar
	
			this.guiAnimal.getCampoRaza().removeAllItems();
			Raza raza;
			Especie especie = controlEspecie.obtenerEspecie(nombreEspecie);
			Collection<Raza> collRazas = especie.obtenerRazas();
			Iterator<Raza> iterRazas = collRazas.iterator();
			while(iterRazas.hasNext()){
				raza = iterRazas.next();
				this.guiAnimal.getCampoRaza().addItem(raza.getNombre().toUpperCase());
			}
	
	}
	
	private void opcionModificarAnimal(){
		try{
			persistencia.iniciarTransaccion();
			Animal animal = controlAnimal.obtenerAnimal(idAnimal);
			String nombre= this.guiAnimal.getCampoNombre().getText();
			String especie = (String)this.guiAnimal.getCampoEspecie().getSelectedItem();
			String raza = (String)this.guiAnimal.getCampoRaza().getSelectedItem();
			String sexo = (String)this.guiAnimal.getCampoSexo().getSelectedItem();
			String edad = this.guiAnimal.getCampoEdad().getText();
			String num=animal.getPropietario().getDni()+animal.getNombre();
			animal.setNro(num.toLowerCase());
			animal.setNombre(nombre.toLowerCase());
			animal.setEspecie(especie.toLowerCase());
			animal.setRaza(raza.toLowerCase());
			animal.setSexo(sexo.toLowerCase());
			animal.setEdad(Integer.parseInt(edad));
			animal.setFechaNac(this.guiAnimal.getCampoNacimiento().getDate());
			DefaultTableModel modelo = (DefaultTableModel) this.tablaAnimal.getModel();
			modelo.setValueAt(num.toUpperCase(), fila, 1);
			modelo.setValueAt(nombre.toUpperCase(), fila, 2);
			modelo.setValueAt(raza.toUpperCase(), fila, 3);
			modelo.setValueAt(especie.toUpperCase(), fila, 4);
			modelo.setValueAt(edad.toUpperCase(), fila, 5);
			modelo.fireTableDataChanged();
			persistencia.concretarTransaccion();
			this.guiAnimal.setVisible(false);
			this.guiAnimal.dispose();
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

	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		if(this.guiAnimal.getBotonModificar()==source){
			this.opcionModificarAnimal();
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
