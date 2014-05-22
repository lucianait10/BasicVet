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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Veterinario;


public class MediadorBuscarVeterinario implements ActionListener{
	private GUIGestionarVeterinario guiBuscar;
	private DefaultTableModel modelo;
	private Persistencia persistencia = new Persistencia();
	private Object source;
	private int idVeterinarioBuscado;
	private MediadorGeneral mediador;
	private ControlVeterinario controlVeterinario;
	
	public MediadorBuscarVeterinario(MediadorGeneral med){
		super();
		this.mediador = med;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiBuscar.show();
	}
	
	private void inicializar(){
		this.modelo = new DefaultTableModel();
		this.controlVeterinario = new ControlVeterinario();
		this.modelo.setColumnIdentifiers(new String[]{"DNI","NOMBRE","APELLIDO","TELEFONO","DIRECCION"});
		try{
			persistencia.iniciarTransaccion();
			Iterator<Veterinario> iterVeterinarios = controlVeterinario.obtenerVeterinarios();
			Veterinario vet;
			while(iterVeterinarios.hasNext()){
				vet = iterVeterinarios.next();
				if(!vet.isEliminado())
					this.modelo.addRow(new String[]{vet.getDni()+"",vet.getNombre().toUpperCase(),vet.getApellido().toUpperCase(),vet.getTelefono()+"",vet.getNumMatricula()+""});
			}
			persistencia.concretarTransaccion();
			this.guiBuscar = new GUIGestionarVeterinario(this.modelo);
			this.guiBuscar.setTitle("BUSCAR VETERINARIO");
			this.guiBuscar.getBotonEliminarVeterinario().setVisible(false);
			this.guiBuscar.getBotonModificarVeterinario().setVisible(false);
			this.guiBuscar.getBotonReporteVeterinario().setVisible(false);
			this.guiBuscar.setListenerButtons(this);
			this.guiBuscar.setListenerMouse(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionSeleccionar();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	private void opcionAgregarVeterinario(){
		MediadorIngresarVeterinario medIngresarVeterinario = new MediadorIngresarVeterinario(this.modelo);
		medIngresarVeterinario.mostrar();
	}
	
	private void opcionCancelar(){
		this.guiBuscar.setVisible(false);
		this.guiBuscar.dispose();
	}
	
	private void opcionSeleccionar(){
		int fila = this.guiBuscar.getTablaVeterinarios().getSelectedRow();
		
		if(fila>=0){
			int filaReal = this.guiBuscar.getTableRowSorter().convertRowIndexToModel(fila);
			idVeterinarioBuscado =Integer.parseInt(((String)modelo.getValueAt(filaReal,0)).toLowerCase());
			mediador.setVeterinarioBuscado(idVeterinarioBuscado);
			this.guiBuscar.setVisible(false);
			this.guiBuscar.dispose();
		}else{
			JOptionPane.showMessageDialog(this.guiBuscar, "SELECCIONE ALGUNA FILA");
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		if(this.guiBuscar.getBotonAgregarVeterinario()==source){
			this.opcionAgregarVeterinario();
		}
		if(this.guiBuscar.getBotonCancelar()==source){
			this.opcionCancelar();
		}
		if(this.guiBuscar.getBotonSeleccionarVeterinario()==source){
			this.opcionSeleccionar();
		}
	}

}
