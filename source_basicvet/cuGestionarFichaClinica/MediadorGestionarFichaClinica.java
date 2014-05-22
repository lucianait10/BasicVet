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

package cuGestionarFichaClinica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.FichaClinica;
import cuGestionarAnimal.ControlAnimal;
import cuGestionarAnimal.MediadorBuscarAnimal;
import cuGestionarFichaClinica.report.ReporteFichaClinica;

@SuppressWarnings("unused")
public class MediadorGestionarFichaClinica extends MediadorGeneral implements ActionListener{
	private GUIGestionarFichaClinica gesFichaClinica;
	private int numero;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Persistencia persistencia = new Persistencia();
	private int fila;
	private ControlFichaClinica controlFichaClinica;
	private ControlAnimal controlAnimal;

	public MediadorGestionarFichaClinica(){
		super();
		this.inicializar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.gesFichaClinica.show();			
	}

	private void inicializar() {
		modelo.addColumn("NUMERO");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("FECHA");
		this.gesFichaClinica = new GUIGestionarFichaClinica(modelo);
		this.controlFichaClinica = new ControlFichaClinica();
		this.controlAnimal = new ControlAnimal();
		this.gesFichaClinica.getBotonIngresarFichaClinica().setEnabled(false);
		this.gesFichaClinica.getBotonEliminarFichaClinica().setEnabled(false);
		this.gesFichaClinica.getBotonModificarFichaClinica().setEnabled(false);
		this.gesFichaClinica.getReporteFichaClinica().setEnabled(false);
		this.gesFichaClinica.getBotonBuscarAnimal().setEnabled(true);
		this.gesFichaClinica.setListenerButtons(this);
		this.gesFichaClinica.setListenerMouse(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)modificarOpcion();}
		});
	}
	
	public void ingresarOpcion(){
		numero = Integer.parseInt(this.gesFichaClinica.getCampoIdAnimal().getText());
		this.gesFichaClinica.setVisible(false);
		MediadorIngresarFichaClinica medIngresar = new MediadorIngresarFichaClinica(numero,this.modelo,this,gesFichaClinica);
		if(this.modelo.getRowCount()>0){this.gesFichaClinica.getBotonModificarFichaClinica().setEnabled(true);}
		this.modelo.fireTableDataChanged();
	}
	public void modificarOpcion(){
		numero = Integer.parseInt(this.gesFichaClinica.getCampoIdAnimal().getText());
		int fila = this.gesFichaClinica.getJTable().getSelectedRow();
		int idFicha = -1;
		if(fila>=0){
			idFicha = Integer.parseInt((String) this.modelo.getValueAt(fila,0));
			this.gesFichaClinica.setVisible(false);
			MediadorModificarFichaClinica medModificar = new MediadorModificarFichaClinica(numero,fila,idFicha,this.gesFichaClinica);
		}
		
	}
	
	public void buscarAnimalOpcion(){
		MediadorBuscarAnimal medBuscarAnimal= new MediadorBuscarAnimal(this);
		medBuscarAnimal.mostrar();
	}
	
	@SuppressWarnings("deprecation")
	public void buscarFichasClinicas(){
		try{
			persistencia.iniciarTransaccion();
			modelo.setRowCount(0);
			this.gesFichaClinica.getBotonModificarFichaClinica().setEnabled(false);
			this.gesFichaClinica.getBotonEliminarFichaClinica().setEnabled(false);
			this.gesFichaClinica.getReporteFichaClinica().setEnabled(false);
			int idAnimal = Integer.parseInt(this.gesFichaClinica.getCampoIdAnimal().getText().toLowerCase());
	        Animal animal = controlAnimal.obtenerAnimal(idAnimal);
	        Iterator<FichaClinica> iterFichas = animal.obtenerFichas().iterator();
	        while (iterFichas.hasNext()){
	        	this.gesFichaClinica.getBotonModificarFichaClinica().setEnabled(true);
	        	this.gesFichaClinica.getBotonEliminarFichaClinica().setEnabled(true);
	        	this.gesFichaClinica.getReporteFichaClinica().setEnabled(true);
	        	FichaClinica ficha = iterFichas.next();
	            String id = ficha.getId()+"";
	            String nombre = animal.getNombre().toUpperCase();
	            String fecha = ficha.getFecha().getDate()+"/"+(ficha.getFecha().getMonth()+1)+"/"+(ficha.getFecha().getYear()+1900);
	            String[] s = {id,nombre,fecha};
	            modelo.addRow(s);
	            modelo.fireTableDataChanged();
	        }
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
			Animal a = controlAnimal.obtenerAnimal(id);
			this.gesFichaClinica.getCampoIdAnimal().setText(a.getId()+"");
			this.gesFichaClinica.getCampoNombre().setText(a.getNombre().toUpperCase());
			this.gesFichaClinica.getBotonIngresarFichaClinica().setEnabled(true);
			persistencia.concretarTransaccion();
			this.buscarFichasClinicas();
			
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void setearBotonReporte(boolean valor){
		this.gesFichaClinica.getReporteFichaClinica().setEnabled(valor);
	}
	
	public void setearBotonEliminar(boolean valor){
		this.gesFichaClinica.getBotonEliminarFichaClinica().setEnabled(valor);
	}
	
	public void setearBotonModificar(boolean valor){
		this.gesFichaClinica.getBotonModificarFichaClinica().setEnabled(valor);
	}
	
	
	public void actionPerformed(ActionEvent e){
		if(this.gesFichaClinica.getBotonBuscarAnimal()==e.getSource()){
			this.buscarAnimalOpcion();
		}
		if(this.gesFichaClinica.getBotonIngresarFichaClinica()==e.getSource()){
			this.ingresarOpcion();
		}
		if(this.gesFichaClinica.getBotonModificarFichaClinica()== e.getSource()){
			this.modificarOpcion();
		}
		if(this.gesFichaClinica.getBotonEliminarFichaClinica()==e.getSource()){
			this.eliminarOpcion();
		}
		if(this.gesFichaClinica.getReporteFichaClinica()==e.getSource()){
			int fila = this.gesFichaClinica.getJTable().getSelectedRow();
			int idFicha = -1;
			if(fila>=0){
				idFicha = Integer.parseInt((String) this.modelo.getValueAt(fila,0));
				gesFichaClinica.setVisible(false);
				ReporteFichaClinica viewReport = new ReporteFichaClinica(gesFichaClinica);
				viewReport.viewFicha(idFicha,Integer.parseInt(this.gesFichaClinica.getCampoIdAnimal().getText()));
			}else{JOptionPane.showMessageDialog(gesFichaClinica, "SELECCIONE ALGUNA FILA");}
		}
	}

	private void eliminarOpcion() {
		numero = Integer.parseInt(this.gesFichaClinica.getCampoIdAnimal().getText());
		fila = this.gesFichaClinica.getJTable().getSelectedRow();
		try{
			persistencia.iniciarTransaccion();
			if(fila>=0){
	        	int opcion = JOptionPane.showConfirmDialog(this.gesFichaClinica, "DESEA ELIMINAR LA FICHA CLINICA", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
	        	if(opcion == JOptionPane.YES_OPTION){
	        		String idFicha = (String)this.modelo.getValueAt(fila, 0);
	        		FichaClinica ficha = controlFichaClinica.obtenerFichaClinica(idFicha);
	        		controlFichaClinica.eliminarFichaClinica(ficha);
	        		modelo.removeRow(fila);
	        		if(this.gesFichaClinica.getTablaModel().getRowCount()==0){
	        			this.gesFichaClinica.getBotonEliminarFichaClinica().setEnabled(false);
	        			this.gesFichaClinica.getBotonModificarFichaClinica().setEnabled(false);
	        			this.gesFichaClinica.getReporteFichaClinica().setEnabled(false);
	        		}
	        	}
			}else{JOptionPane.showMessageDialog(this.gesFichaClinica, "SELECCIONE ALGUNA FILA");}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
}
