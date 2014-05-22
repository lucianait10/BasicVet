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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Propietario;


public class MediadorBuscarPropietario implements ActionListener{
	private GUIGestionarPropietario guiBuscar;
	private DefaultTableModel modelo;
	private Persistencia persistencia = new Persistencia();
	private Object source;
	private String idPropietarioBuscado;
	private MediadorGeneral mediador;
	private ControlPropietario controlPropietario;
	
	public MediadorBuscarPropietario(MediadorGeneral med){
		super();
		this.mediador = med;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiBuscar.show();
	}
	
	private void inicializar(){
		this.modelo = new DefaultTableModel(){
			private static final long serialVersionUID = 1L;
			
			public boolean isCellEditable (int row, int column){return false;}
		};
		this.controlPropietario = new ControlPropietario();
		this.modelo.setColumnIdentifiers(new String[]{"DNI","NOMBRE","APELLIDO","TELEFONO","DIRECCION"});
		try{
			persistencia.iniciarTransaccion();
			Iterator<Propietario> iterPropietarios = controlPropietario.obtenerPropietarios();
			Propietario prop;
			while(iterPropietarios.hasNext()){
				prop = iterPropietarios.next();
				if(!prop.isEliminado())
					this.modelo.addRow(new String[]{prop.getDni().toUpperCase(),prop.getNombre().toUpperCase(),prop.getApellido().toUpperCase(),prop.getTelefono()+"",prop.getDireccion().toUpperCase()});
			}
			persistencia.concretarTransaccion();
			this.guiBuscar = new GUIGestionarPropietario(this.modelo);
			this.guiBuscar.setTitle("BUSCAR PROPIETARIO");
			this.guiBuscar.getBotonEliminarPropietario().setVisible(false);
			this.guiBuscar.getBotonModificarPropietario().setVisible(false);
			this.guiBuscar.getBotonReportePropietario().setVisible(false);
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
	
	private void opcionAgregarPropietario(){
		MediadorIngresarPropietario medIngresarPropietario = new MediadorIngresarPropietario(this.modelo);
		medIngresarPropietario.mostrar();
	}
	
	private void opcionSeleccionar(){
		int fila = this.guiBuscar.getTablaPropietarios().getSelectedRow();
		
		if(fila>=0){
			int filaReal = this.guiBuscar.getTableRowSorter().convertRowIndexToModel(fila);
			idPropietarioBuscado =((String)modelo.getValueAt(filaReal,0)).toLowerCase();
			mediador.setPropietarioBuscado(idPropietarioBuscado);
			this.guiBuscar.setVisible(false);
			this.guiBuscar.dispose();
		}else{
			JOptionPane.showMessageDialog(this.guiBuscar, "SELECCIONE ALGUNA FILA");
		}
	}

	private void opcionCancelar(){
		this.guiBuscar.setVisible(false);
		this.guiBuscar.dispose();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource();
		if(this.guiBuscar.getBotonAgregarPropietario()==source){
			this.opcionAgregarPropietario();
		}
		if(this.guiBuscar.getBotonSeleccionarPropietario()==source){
			this.opcionSeleccionar();
		}
		if(this.guiBuscar.getBotonCancelar()==source){
			this.opcionCancelar();
		}
	}

}
