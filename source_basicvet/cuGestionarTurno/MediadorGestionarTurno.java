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

package cuGestionarTurno;

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
import persistencia.dominio.Turno;
import cuGestionarAnimal.ControlAnimal;
import cuGestionarAnimal.MediadorBuscarAnimal;

@SuppressWarnings("unused")
public class MediadorGestionarTurno extends MediadorGeneral implements ActionListener {
	private GUIGestionarTurno gesTurno;
	private DefaultTableModel modelo = new DefaultTableModel(){
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable (int row, int column){return false;}
	};
	private Persistencia persistencia;
	private ControlAnimal controlAnimal;
	private ControlTurno controlTurno;
	private int numero;
	
//----------------------------------------------------------------------------------------------		
	public MediadorGestionarTurno(){
		super();
		this.inicializar();
	}
//----------------------------------------------------------------------------------------------	
	@SuppressWarnings("deprecation")
	public void mostrar() {
		this.gesTurno.show();
	}
//----------------------------------------------------------------------------------------------
	public void inicializar(){
		modelo.setColumnIdentifiers(new String[] { "NUMERO", "NOMBRE", "FECHA", "HORA", "MOTIVO" });             
		this.gesTurno = new GUIGestionarTurno(modelo);
		this.persistencia = new Persistencia();
		this.controlAnimal = new ControlAnimal();
		this.controlTurno = new ControlTurno();
		this.gesTurno.getEliminar().setEnabled(false);
		this.gesTurno.getModificar().setEnabled(false);
		this.gesTurno.getIngresar().setEnabled(false);
		this.gesTurno.setListenerButtons(this);
		this.gesTurno.setListenerMouse(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {if(e.getClickCount()==2)modificarTurno();}
		});
	}
//----------------------------------------------------------------------------------------------	
	public void ingresarTurno(){
		numero = Integer.parseInt(this.gesTurno.getCampoId().getText());
		this.gesTurno.setVisible(false);
		MediadorIngresarTurno medIngresar = new MediadorIngresarTurno(modelo,this,numero,this.gesTurno);
		medIngresar.Mostrar();
	}
//----------------------------------------------------------------------------------------------	
	public void eliminarTurno(){
		//numero = Integer.parseInt(this.gesTurno.getCampoId().getText());
		//this.gesTurno.setVisible(false);
		//MediadorEliminarTurno medEliminar = new MediadorEliminarTurno(numero,modelo,this.gesTurno.getJTable(),gesTurno);
		
		//MLucero
		
		if(gesTurno.listaTurnos.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(this.gesTurno,"SELECCION DE TURNO INVALIDOoo");
		}else{
			try{
			String idTurno=(String)gesTurno.listaTurnos.getValueAt(gesTurno.listaTurnos.getSelectedRow(),0);
			persistencia.iniciarTransaccion();
			int idTurnoElim = Integer.parseInt(idTurno);
			Turno turnoEliminar = controlTurno.obtenerTurno(idTurnoElim);
			int prueba = JOptionPane.showConfirmDialog(this.gesTurno,"Confirma eliminar el turno numero  \n"+ turnoEliminar.getId());
			if (prueba == 0)
				controlTurno.eliminarTurno(turnoEliminar);
    		persistencia.concretarTransaccion();
    		buscarTurnos();
			}catch(Exception e){
    			System.out.println("Error "+this.getClass().toString());
    			e.printStackTrace();
    			persistencia.deshacerTransaccion();
    		}
		}
	}
//----------------------------------------------------------------------------------------------	
	public void modificarTurno(){
		numero = Integer.parseInt(this.gesTurno.getCampoId().getText());
		String idTurno=(String)gesTurno.listaTurnos.getValueAt(gesTurno.listaTurnos.getSelectedRow(),0);
		int numeroTurno=Integer.parseInt(idTurno);
		this.gesTurno.setVisible(false);
		MediadorModificarTurno medModificar = new MediadorModificarTurno(numero,numeroTurno,this.gesTurno.getJTable(),gesTurno);
	}
//----------------------------------------------------------------------------------------------
	public void buscarAnimal(){
		MediadorBuscarAnimal medBuscarAnimal= new MediadorBuscarAnimal(this);
		medBuscarAnimal.mostrar();
	}
//----------------------------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	public void buscarTurnos(){
		try{
			persistencia.iniciarTransaccion();
			modelo.setRowCount(0);
			this.gesTurno.getModificar().setEnabled(false);
			this.gesTurno.getEliminar().setEnabled(false);
			numero = Integer.parseInt(this.gesTurno.getCampoId().getText());
	        Animal animal = controlAnimal.obtenerAnimal(numero);
	        //Mlucero 
	        Iterator<Turno> iterTurnos = controlAnimal.obtenerTurnosDeAnimal(numero);
	        //Iterator<Turno> iterTurnos = animal.obtenerTurnos().iterator();
	        while (iterTurnos.hasNext()){
	        	this.gesTurno.getModificar().setEnabled(true);
	        	this.gesTurno.getEliminar().setEnabled(true);
	            Turno turno = (Turno)iterTurnos.next();
	            String id = turno.getId()+"";
	            String nombre = animal.getNombre().toUpperCase();
	            String fecha = turno.getFecha().getDate()+"/"+(((Turno) turno).getFecha().getMonth()+1)+"/"+(((Turno) turno).getFecha().getYear()+1900);
	            String hora =  turno.getHora();
	            String motivo = turno.getMotivo();
	            String[] s = {id,nombre,fecha,hora,motivo};
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
			Animal a =controlAnimal.obtenerAnimal(id);
			this.gesTurno.getCampoId().setText(a.getId()+"");
			this.gesTurno.getCampoNombre().setText(a.getNombre().toUpperCase());
			this.gesTurno.getIngresar().setEnabled(true);
			persistencia.concretarTransaccion();
			this.buscarTurnos();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	public void visibilidadBotones(){
		this.gesTurno.getModificar().setEnabled(true);
		this.gesTurno.getEliminar().setEnabled(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (this.gesTurno.getIngresar()==e.getSource()) {
			this.ingresarTurno();
		}
		if (this.gesTurno.getEliminar()==e.getSource()) {
			this.eliminarTurno();
	    }
		if(this.gesTurno.getModificar()== e.getSource()){
			this.modificarTurno();
		}
		if(this.gesTurno.getBotonBuscarAnimal()==e.getSource()){
			this.buscarAnimal();
		}
	}
}
