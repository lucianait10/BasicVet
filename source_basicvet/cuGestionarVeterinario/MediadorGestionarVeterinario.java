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

import persistencia.Persistencia;
import persistencia.dominio.BasicVet;
import persistencia.dominio.Veterinario;
import clasesComunes.ControlBasicVet;
import cuGestionarVeterinario.report.ReporteVeterinario;

public class MediadorGestionarVeterinario implements ActionListener {
	private GUIGestionarVeterinario guiGestionarVeterinario;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Object source;
	private Persistencia persistencia = new Persistencia();
	private ControlVeterinario controlVeterinario;
	private ControlBasicVet controlBasicVet;
	
	public MediadorGestionarVeterinario(){
		super();  
		inicializar();
	}
	
	@SuppressWarnings({ "deprecation" })
	public void mostrar(){
		this.guiGestionarVeterinario.show();
	}

	private void inicializar() {
		this.controlVeterinario = new ControlVeterinario();
		this.controlBasicVet = new ControlBasicVet();
		this.modelo.setColumnIdentifiers(new String[]{"DNI","NOMBRE","APELLIDO","TELEFONO","MATRICULA"});
		this.guiGestionarVeterinario = new GUIGestionarVeterinario(this.modelo);
		this.guiGestionarVeterinario.getBotonEliminarVeterinario().setEnabled(false);
		this.guiGestionarVeterinario.getBotonModificarVeterinario().setEnabled(false);
		this.guiGestionarVeterinario.getBotonReporteVeterinario().setEnabled(false);
		try{
			persistencia.iniciarTransaccion();
			Iterator<Veterinario> iterVeterinarios = controlVeterinario.obtenerVeterinarios();
			Veterinario vet;
			while(iterVeterinarios.hasNext()){
				vet = iterVeterinarios.next();
				if(!vet.isEliminado()){
					this.modelo.addRow(new String[]{vet.getDni().toUpperCase(),vet.getNombre().toUpperCase(),vet.getApellido().toUpperCase(),vet.getTelefono()+"",vet.getNumMatricula()+""});
				}
			}
			if(this.modelo.getRowCount()>0){
				this.guiGestionarVeterinario.getBotonModificarVeterinario().setEnabled(true);
				this.guiGestionarVeterinario.getBotonEliminarVeterinario().setEnabled(true);
				this.guiGestionarVeterinario.getBotonReporteVeterinario().setEnabled(true);
			}
			persistencia.concretarTransaccion();
			this.guiGestionarVeterinario.getBotonCancelar().setVisible(false);
			this.guiGestionarVeterinario.getBotonSeleccionarVeterinario().setVisible(false);
			this.guiGestionarVeterinario.setListenerButtons(this);
			this.guiGestionarVeterinario.setListenerMouse(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionModificarVeterinario();}
			});
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	private void opcionAgregarVeterinario() {
		MediadorIngresarVeterinario medIngresarVeterinario = new MediadorIngresarVeterinario(this.modelo);
		medIngresarVeterinario.mostrar();
		if(this.modelo.getRowCount()>0){
			this.guiGestionarVeterinario.getBotonModificarVeterinario().setEnabled(true);
			this.guiGestionarVeterinario.getBotonEliminarVeterinario().setEnabled(true);
			this.guiGestionarVeterinario.getBotonReporteVeterinario().setEnabled(true);
		}
		this.modelo.fireTableDataChanged();
	}
	
	private void opcionEliminarVeterinario(){
		int fila = this.guiGestionarVeterinario.getTablaVeterinarios().getSelectedRow();
		try{
		if(fila>=0){
        	int opcion = JOptionPane.showConfirmDialog(this.guiGestionarVeterinario, "DESEA ELIMINAR EL VETERINARIO", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
        	if(opcion == JOptionPane.YES_OPTION){
        		persistencia.iniciarTransaccion();
        		int filaReal = this.guiGestionarVeterinario.getTableRowSorter().convertRowIndexToModel(fila);
        		String idVeterinario = (String) this.modelo.getValueAt(filaReal, 0);
        		Veterinario veterinario = controlVeterinario.obtenerVeterinario(idVeterinario.toLowerCase());
        		veterinario.setEliminado(true);	
        		persistencia.concretarTransaccion();
        		this.modelo.removeRow(filaReal);
        		this.modelo.fireTableDataChanged();
        		if(this.modelo.getRowCount()==0){
        			this.guiGestionarVeterinario.getBotonModificarVeterinario().setEnabled(false);
        			this.guiGestionarVeterinario.getBotonEliminarVeterinario().setEnabled(false);
        			this.guiGestionarVeterinario.getBotonReporteVeterinario().setEnabled(false);
        		}
        	}
		}else{JOptionPane.showMessageDialog(this.guiGestionarVeterinario, "SELECCIONE ALGUN VETERINARIO");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	private void opcionModificarVeterinario(){
		@SuppressWarnings("unused")
		MediadorModificarVeterinario medModificarVeterinario = new MediadorModificarVeterinario(this.modelo,this.guiGestionarVeterinario);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		source = e.getSource(); 
		if(this.guiGestionarVeterinario.getBotonAgregarVeterinario()== source){
			this.opcionAgregarVeterinario();
		}
		if(this.guiGestionarVeterinario.getBotonEliminarVeterinario()==source){
			this.opcionEliminarVeterinario();
		}
		if(this.guiGestionarVeterinario.getBotonModificarVeterinario()==source){
			this.opcionModificarVeterinario();
		}
		if(this.guiGestionarVeterinario.getBotonReporteVeterinario()==e.getSource()){
			this.opcionReporte();
		}
	}

	private void opcionReporte(){
		if(this.guiGestionarVeterinario.getTableRowSorter().getViewRowCount()>0){	
			int numColumnas = this.modelo.getColumnCount();
			int numFilas = this.guiGestionarVeterinario.getTableRowSorter().getViewRowCount();
			Object[][] datos = new Object[numFilas][numColumnas];
			for(int i=0;i<numFilas;i++){
				for(int j=0;j<numColumnas;j++){
					int filaVista = this.guiGestionarVeterinario.getTableRowSorter().convertRowIndexToModel(i);
					datos[i][j] = this.modelo.getValueAt(filaVista, j);
				}
			}
			guiGestionarVeterinario.setVisible(false);
			ReporteVeterinario viewReport = new ReporteVeterinario(guiGestionarVeterinario);
			try{
				persistencia.iniciarTransaccion();
				BasicVet bv = controlBasicVet.obtenerBasicVet("1");
				viewReport.viewFicha(datos,bv.getNombre(),bv.getDireccion(),bv.getTelefono()+"",bv.getCuil());
				persistencia.concretarTransaccion();
			}
			catch(Exception e){
				System.out.println("Error "+this.getClass().toString());
				e.printStackTrace();
				persistencia.deshacerTransaccion();
			}
		}else{JOptionPane.showMessageDialog(guiGestionarVeterinario, "LA LISTA ESTA VACIA");}
	}
}
