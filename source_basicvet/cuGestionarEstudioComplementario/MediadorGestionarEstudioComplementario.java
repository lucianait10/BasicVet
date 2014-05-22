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

package cuGestionarEstudioComplementario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.EstudioComplementario;
import persistencia.dominio.FichaClinica;
import clasesComunes.ControlEstudioComplementario;
import cuGestionarFichaClinica.ControlFichaClinica;
import cuGestionarFichaClinica.GUIInterfazFichaClinica;

public class MediadorGestionarEstudioComplementario implements ActionListener {
	private Persistencia persistencia;
	private GUIGestionarEstudioComplementario guiGestionarEstudio;
	private long idFicha; 
	private ControlFichaClinica controlFichaClinica;
	private ControlEstudioComplementario controlEstudioComplementario;
	private DefaultTableModel tablaModelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private GUIInterfazFichaClinica ingFichaClinica;
	
	public MediadorGestionarEstudioComplementario(long id, GUIInterfazFichaClinica ingFichaClinica){
		this.idFicha = id;
		this.ingFichaClinica = ingFichaClinica;
		inicializar();
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiGestionarEstudio.show();
	}
	
	@SuppressWarnings("deprecation")
	private void inicializar() {
		this.tablaModelo.setColumnIdentifiers(new String[] { "ID", "TIPO", "FECHA"});
		this.guiGestionarEstudio = new GUIGestionarEstudioComplementario(tablaModelo,ingFichaClinica);
		this.guiGestionarEstudio.setListenerButtons(this);
		this.guiGestionarEstudio.getBotonEliminarEstComp().setEnabled(false);
		this.guiGestionarEstudio.getBotonModificarEstComp().setEnabled(false);
		persistencia = new Persistencia();
		controlFichaClinica = new ControlFichaClinica();
		controlEstudioComplementario = new ControlEstudioComplementario(); 
		try{
			persistencia.iniciarTransaccion();
			FichaClinica ficha  = controlFichaClinica.obtenerFichaClinica(idFicha+"");
			Collection<EstudioComplementario> c = ficha.obtenerEstudiosComplementarios();
			Iterator<EstudioComplementario> iterEstudios = c.iterator();
			EstudioComplementario estComp;
			while(iterEstudios.hasNext()){
				estComp = iterEstudios.next();
				long id = estComp.getId();
				Date fecha = estComp.getFecha();
				String tipoEstudio = estComp.getTipo();
				String fechaStr = fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+(fecha.getYear()+1900);
				this.tablaModelo.addRow(new String[]{id+"",tipoEstudio.toUpperCase(),fechaStr});
			}
			if(this.tablaModelo.getRowCount()>0){
				this.guiGestionarEstudio.getBotonEliminarEstComp().setEnabled(true);
				this.guiGestionarEstudio.getBotonModificarEstComp().setEnabled(true);
			}
			this.guiGestionarEstudio.setListenerMouse(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)opcionModificar();}
			});
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		if(this.guiGestionarEstudio.getBotonAgregarEstComp()==e.getSource()){
			MediadorIngresarEstudioComplementario medIngEstComp = new MediadorIngresarEstudioComplementario(this.guiGestionarEstudio,this.idFicha,this.tablaModelo);
			medIngEstComp.mostrar();
		}
		if(this.guiGestionarEstudio.getBotonModificarEstComp()==e.getSource()){
			this.opcionModificar();
		}
		if(this.guiGestionarEstudio.getBotonEliminarEstComp()==e.getSource()){
			this.opcionEliminar();
		}
	}
	
	private void opcionModificar(){
		int fila = this.guiGestionarEstudio.getTablaEstudios().getSelectedRow();
		if(fila>=0){
			int filaVista = this.guiGestionarEstudio.getTableRowSorter().convertRowIndexToModel(fila);
			long idEstudio = Integer.parseInt((String)this.tablaModelo.getValueAt(filaVista, 0));
			MediadorModificarEstudioComplementario medModEstComp = new MediadorModificarEstudioComplementario(this.idFicha,idEstudio,filaVista,this.tablaModelo,guiGestionarEstudio);
			medModEstComp.mostrar();
		}else{
			JOptionPane.showMessageDialog(guiGestionarEstudio, "SELECCIONE ALGUN ESTUDIO COMPLEMENTARIO");
		}
	}

	private void opcionEliminar() {
		int fila = this.guiGestionarEstudio.getTablaEstudios().getSelectedRow();
		int filaVista = this.guiGestionarEstudio.getTableRowSorter().convertRowIndexToModel(fila);
		if(filaVista>=0){
        	int opcion = JOptionPane.showConfirmDialog(this.guiGestionarEstudio, "DESEA ELIMINAR EL ESTUDIO COMPLEMENTARIO", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
        	if(opcion == JOptionPane.YES_OPTION){
        		long idEstudio = Integer.parseInt((String) this.tablaModelo.getValueAt(filaVista, 0));
        		try{
	        		persistencia.iniciarTransaccion();
	        		controlEstudioComplementario.eliminarEstudioComplementario(idEstudio);
	        		this.tablaModelo.removeRow(filaVista);
	        		persistencia.concretarTransaccion();
        		}
        		catch(Exception e){
					System.out.println("Error "+this.getClass().toString());
					e.printStackTrace();
					persistencia.deshacerTransaccion();
				}
        		if(this.tablaModelo.getRowCount()==0){
        			this.guiGestionarEstudio.getBotonEliminarEstComp().setEnabled(false);
        			this.guiGestionarEstudio.getBotonModificarEstComp().setEnabled(false);
        		}
        	}
		}else{JOptionPane.showMessageDialog(this.guiGestionarEstudio, "SELECCIONE UNA FILA");}
	}
}
