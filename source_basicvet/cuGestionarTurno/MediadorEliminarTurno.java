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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;

import persistencia.Persistencia;
import persistencia.dominio.Horario;
import persistencia.dominio.Turno;

@SuppressWarnings("deprecation")
public class MediadorEliminarTurno implements DateListener,ActionListener {
	private GUISeleccionTurno seleccionTurno;
	private DefaultTableModel modelo;
	private Persistencia persistencia = new Persistencia();
	private LinkedList<Integer> lista1=new LinkedList<Integer>();
	private LinkedList<Integer> lista2=new LinkedList<Integer>();
	private LinkedList<Integer> listaTurno1=new LinkedList<Integer>();
	private LinkedList<Integer> listaTurno2=new LinkedList<Integer>();
	private List<Turno> listaTurnoAnimal=new ArrayList<Turno>();
	private JTable tablaGestionar;
	private List<Turno> listaTurnos = new ArrayList<Turno>();
	private int fila;
	private Date fecha;
	private int[] filas;
	private Turno turnoEliminar;
	private int idTurno;
	private int idAnimal;
	private ControlTurno controlTurno;
	private ControlHorario controlHorario;
	private GUIGestionarTurno gesTurno;

//-----------------------------------------------------------------------------------------------
	public MediadorEliminarTurno(int animal,DefaultTableModel dtm, JTable tablaGestionar, GUIGestionarTurno gesTurno){
		super();
		this.gesTurno = gesTurno;
		this.idAnimal = animal;
		this.modelo = dtm;
		this.tablaGestionar = tablaGestionar;
		this.inicializar();
	}
//-----------------------------------------------------------------------------------------------
	public void Mostrar() {
		this.seleccionTurno.show();
	}
//----------------------------------------------------------------------------------------------------
	private void inicializar() {
		try{
	        fila = tablaGestionar.getSelectedRow();
	        if(fila>=0){
	        	this.seleccionTurno = new GUISeleccionTurno(gesTurno);
	        	this.seleccionTurno.setListenerButtons(this);
	        	this.seleccionTurno.getCalendario().addDateListener(this);
	        	this.seleccionTurno.getBotonAgregar().setVisible(false);
	        	this.seleccionTurno.getBotonModificar().setVisible(false);
	        	this.seleccionTurno.getLabelTurnoAzul().setText((String)tablaGestionar.getValueAt(fila,1));
	           	SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
	           	String strFecha = (String)tablaGestionar.getValueAt(fila,2);
	           	Date fechaTurno = null;
	           	try{fechaTurno = dt.parse(strFecha);}catch(Exception e){System.out.println("Fecha invalida");}
	           	this.seleccionTurno.getTableModel().setColumnIdentifiers(new String[]{"HORA",strFecha});
	           	this.seleccionTurno.modificarIdentificadoresTabla();
	           	this.seleccionTurno.getCampoMotivo().setVisible(false);
	           	this.seleccionTurno.getCalendario().setDate(fechaTurno);
	    		this.controlTurno = new ControlTurno();
	    		this.controlHorario = new ControlHorario();
	    		this.seleccionTurno.show();
	        }else{
	        	JOptionPane.showMessageDialog(this.seleccionTurno, "SELECCIONE UN TURNO");
	        	this.gesTurno.setVisible(true);
	        }
        }catch(Exception e){
        	this.seleccionTurno.setVisible(false);
        	this.seleccionTurno.dispose();
        }
	}
//--------------------------------------------------------------------------------------------------------------------	
	public void eliminarOpcion(){
		try{
			persistencia.iniciarTransaccion();
	        filas = this.seleccionTurno.getTabla().getSelectedRows();
	        if(this.seleccionFilasValidas()){
	        	int opcion = JOptionPane.showConfirmDialog(this.seleccionTurno, "DESEA ELIMINAR EL TURNO", "CONFIRMAR ELIMINACION", JOptionPane.YES_NO_OPTION);
	        	if(opcion == JOptionPane.YES_OPTION){	
	        		this.turnoEliminar = (Turno)this.seleccionTurno.getTableModel().getValueAt(filas[0], 1);
	        		this.idTurno = this.turnoEliminar.getId();
	        		this.turnoEliminar = controlTurno.obtenerTurno(idTurno);
	        		this.vaciarHorarios(this.turnoEliminar.obtenerHorarios());
	        		controlTurno.eliminarTurno(turnoEliminar);
	        		Date nuevaFecha = this.seleccionTurno.getCalendario().getDate();
	        		persistencia.concretarTransaccion();
	        		fila = this.buscarFilaBorar(this.idTurno);
	        		this.modelo.removeRow(fila);
	        		this.listarTurnosFecha(nuevaFecha);
	        	}
	        }else{
	        	JOptionPane.showMessageDialog(this.seleccionTurno,"SELECCION DE TURNO INVALIDO");
	        }
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
//----------------------------------------------------------------------------------------------------	
	public int buscarFilaBorar(int idTurno){
		int i=0;
		while(i<modelo.getRowCount()){
			if(Integer.parseInt((String) modelo.getValueAt(i, 0))==idTurno) return i;
			i++;
		}
		return -1;
	}
//----------------------------------------------------------------------------------------------------	
	public boolean seleccionFilasValidas(){
		boolean sonFilasValidas = true;
		boolean sonDeTurno = false;
		if(filas.length>0){
			for(int i=0;i<filas.length;i++){
				sonFilasValidas = (sonFilasValidas && this.listaTurno1.contains(filas[i])) || 
								  (sonFilasValidas && this.listaTurno2.contains(filas[i]));
			}
			if(sonFilasValidas){
				Turno t = (Turno)this.seleccionTurno.getTableModel().getValueAt(filas[0], 1);
				if(t.obtenerHorarios().size()==filas.length){
					sonDeTurno = true;
					List<Horario> horariosTurno = new ArrayList<Horario>(t.obtenerHorarios());
					for(int i=0;i<horariosTurno.size() && sonDeTurno;i++){
						sonDeTurno = false;
						for(int j=0;j<filas.length;j++){
							if(filas[j]==horariosTurno.get(i).getFila()){
								sonDeTurno = true;
							}
						}
					}
				}
			}
		}
		return (sonFilasValidas && sonDeTurno);
	}
//----------------------------------------------------------------------------------------------------
	public void vaciarHorarios(Collection<Horario> coleccion){
		Iterator<Horario> iter = coleccion.iterator();
		while(iter.hasNext()){
			controlHorario.eliminarHorario(iter.next());
		}
	}
//----------------------------------------------------------------------------------------------------	
	public void dateChanged(DateEvent e) {
		Calendar c = e.getSelectedDate();
		if (c != null) {
			fecha = c.getTime();
			this.listarTurnosFecha(fecha);
		}
	}
//----------------------------------------------------------------------------------------------------		
	@SuppressWarnings({ "unchecked" })
	public void listarTurnosFecha(Date fecha){
		try{
			listaTurnos.clear();
			listaTurnoAnimal.clear();
			listaTurno1.clear();
			listaTurno2.clear();
			lista1.clear();
			lista2.clear();
			persistencia.iniciarTransaccion();
			this.seleccionTurno.getTableModel().setRowCount(0);
			this.seleccionTurno.inicializarColumnaHolas(this.seleccionTurno.getTableModel());
			Collection turnos = persistencia.obtenerObjetosFecha(Turno.class,fecha.getYear()+1900,fecha.getMonth(),fecha.getDate());
			listaTurnos  = new ArrayList<Turno>(turnos);
			for(int i=0;i<listaTurnos.size();i++){
				Turno turno = listaTurnos.get(i);
				if(turno.getId()==idAnimal){
					listaTurnoAnimal.add(turno);
					listaTurnos.remove(i);
					i--;
				}
			}
			this.llenarListar(listaTurnos, lista1, lista2);
			this.llenarListar(listaTurnoAnimal, listaTurno1, listaTurno2);
			this.seleccionTurno.colorearTabla(fecha,lista1,lista2,listaTurno1,listaTurno2);
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
//----------------------------------------------------------------------------------------------------	
	public void llenarListar(List<Turno> lt,LinkedList<Integer> l1,LinkedList<Integer> l2){
		Collections.sort(lt);
		ArrayList<Horario> listaHorarios;
		boolean sig = false;
		int fila;
   	 	for(int i=0;i<lt.size();i++){
		  Turno t = lt.get(i);
		  listaHorarios = new ArrayList<Horario>(t.obtenerHorarios());
		  if(sig){
			  for(int j=0;j<listaHorarios.size();j++){
				  fila = listaHorarios.get(j).getFila();
				  l2.addLast(fila);
				  this.seleccionTurno.getTableModel().setValueAt(t, fila, 1);
			  }
			  sig = false;
		  }else{
			  for(int j=0;j<listaHorarios.size();j++){
				  fila = listaHorarios.get(j).getFila();
				  l1.addLast(fila);
				  this.seleccionTurno.getTableModel().setValueAt(t, fila, 1);
			  }
			  sig = true;
		  }
		}
	}
//----------------------------------------------------------------------------------------------------	
	public void actionPerformed(ActionEvent e) {
		if (this.seleccionTurno.getBotonEliminar()==e.getSource()) {
			this.eliminarOpcion();
		}
	}
}
