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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;

import persistencia.Persistencia;
import persistencia.dominio.Animal;
import persistencia.dominio.BasicVet;
import persistencia.dominio.Horario;
import persistencia.dominio.Motivo;
import persistencia.dominio.Turno;
import clasesComunes.ControlBasicVet;
import clasesComunes.ControlMotivo;
import cuGestionarAnimal.ControlAnimal;
import cuGestionarTurno.report.ReporteTurnoSemana;

public class MediadorIngresarTurno implements DateListener, ActionListener  {
	private DefaultTableModel modelo;
	private Persistencia persistencia = new Persistencia();
	private GUISeleccionTurno seleccionTurno;
	private Date fecha;
	private int[] filas;
	private LinkedList<Integer> lista1=new LinkedList<Integer>();
	private LinkedList<Integer> lista2=new LinkedList<Integer>();
	private MediadorGestionarTurno medGes;
	private int id;
	private ControlTurno controlTurno;
	private ControlAnimal controlAnimal;
	private ControlHorario controlHorario;
	private ControlMotivo controlMotivo;
	private ControlBasicVet controlBasicVet;
	private GUIGestionarTurno gesTurno;

	public MediadorIngresarTurno(DefaultTableModel m, MediadorGestionarTurno medGes,int numero, GUIGestionarTurno gesTurno){
		super();
		this.gesTurno = gesTurno; 
		this.medGes = medGes;
		this.id = numero;
		this.modelo = m;
		this.inicializar();
	}
//-----------------------------------------------------------------------------------------------
	private void inicializar(){
        try{
        	this.seleccionTurno = new GUISeleccionTurno(gesTurno);
        	this.seleccionTurno.setListenerButtons(this);
        	this.seleccionTurno.getBotonModificar().setVisible(false);
        	this.seleccionTurno.getBotonEliminar().setVisible(false);
    		this.seleccionTurno.getCalendario().addDateListener(this);
    		this.seleccionTurno.getPanelAzul().setVisible(false);
    		fecha = new Date();
    		this.listarTurnosFecha(fecha);
    		this.controlMotivo = new ControlMotivo();
    		this.controlTurno = new ControlTurno();
    		this.controlAnimal = new ControlAnimal();
    		this.controlHorario = new ControlHorario();
    		this.controlBasicVet = new ControlBasicVet();
    		this.cargarMotivos();
        }catch(Exception e){;
        	this.seleccionTurno.setVisible(false);
        	this.seleccionTurno.dispose();
        }
	}
//-----------------------------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	public void aceptarInsercion(){
		filas = this.seleccionTurno.obtenerTabla().getSelectedRows();
		
		if(filas.length>0){
			persistencia.iniciarTransaccion();
			boolean esTurno = false;
			for(int i=0;i<filas.length && !esTurno;i++){
				for(int j = 0;j<lista1.size() && !esTurno;j++){
					esTurno = (filas[i]==lista1.get(j)) || false;
				}
			}
			for(int i=0;i<filas.length && !esTurno;i++){
				for(int j = 0;j<lista2.size() && !esTurno;j++){
					esTurno = (filas[i]==lista2.get(j)) || false;
				}
			}
			try{
				if(!esTurno){
					
					if(this.fechaValida(fecha)){
						String motivo = this.seleccionTurno.getCampoMotivo().getSelectedItem().toString();
						if(motivo.length()>0){
							Animal animal = controlAnimal.obtenerAnimal(this.id);
							String hora = this.seleccionTurno.getTableModel().getValueAt(filas[0], 0)+"";
							Turno turno = new Turno(fecha,hora,motivo,animal.getId());
							turno.setAnimal(animal);
							animal.agregarTurno(turno);
							String fechaStr = fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+(fecha.getYear()+1900);
							int x = controlTurno.guardarTurno(turno).getId();
							Horario h;
							for(int i=0;i<filas.length;i++){
								h = new Horario(filas[i],1);
								controlHorario.guardarHorario(h);
								turno.agregarHorario(h);
								
								this.seleccionTurno.getTableModel().setValueAt(this.id, i+1, 1);
							}
							persistencia.concretarTransaccion();
							String[] s = {x+"",animal.getNombre().toUpperCase(),fechaStr,hora,motivo.toUpperCase()};
							modelo.addRow(s);
							modelo.fireTableDataChanged();
							//this.modeloAlarma.addRow(new String[]{turno.getId()+"","TURNO",turno.toString(),animal.getPropietario().getTelefono()+""});
							//modeloAlarma.fireTableDataChanged();
							
							this.medGes.visibilidadBotones();
							this.seleccionTurno.setVisible(false);
							this.seleccionTurno.dispose();
							this.gesTurno.setVisible(true);
							
						}else{JOptionPane.showMessageDialog(this.seleccionTurno,"SELECCIONE EL MOTIVO DEL TURNO");}
					}else{JOptionPane.showMessageDialog(this.seleccionTurno,"FECHA INVALIDA");}
				}else{JOptionPane.showMessageDialog(this.seleccionTurno,"HORARIO YA RESERVADO");}

			}
			catch(Exception e){
				System.out.println("Error "+this.getClass().toString());
				e.printStackTrace();
				persistencia.deshacerTransaccion();
			}
		}else{JOptionPane.showMessageDialog(this.seleccionTurno,"SELECCIONE ALGUN TURNO");}
		
	}
//-----------------------------------------------------------------------------------------------
	@SuppressWarnings("deprecation")
	private boolean fechaValida(Date fecha){
		Date fechaActual = new Date();
		fechaActual.setDate(fechaActual.getDate()-1);
		return fecha.after(fechaActual);
	}
//-----------------------------------------------------------------------------------------------
	public void cargarMotivos(){
		try{
			persistencia.iniciarTransaccion();
			Iterator<Motivo> iterMotivos = controlMotivo.obtenerMotivos();
			while(iterMotivos.hasNext()){
				Motivo motivo = iterMotivos.next();
				this.seleccionTurno.getCampoMotivo().addItem(motivo.getNombre().toUpperCase());
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
//-----------------------------------------------------------------------------------------------	
	public void agregarMotivo(){
		String nuevoMotivo = JOptionPane.showInputDialog("INGRESE EL NUEVO MOTIVO");
		try{
			if(nuevoMotivo.length()>0){
				persistencia.iniciarTransaccion();
				int existeMotivo = controlMotivo.existeMotivo(nuevoMotivo.toLowerCase());
				if(existeMotivo!=1){
					Motivo motivo = new Motivo(nuevoMotivo.toLowerCase());
					controlMotivo.guardarMotivo(motivo);
					
					seleccionTurno.getCampoMotivo().addItem(nuevoMotivo.toUpperCase());
				}else{JOptionPane.showMessageDialog(this.seleccionTurno,"EL MOTIVO YA EXISTE");}
				persistencia.concretarTransaccion();
			}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void Mostrar() {
		this.seleccionTurno.show();
	} 
	
	public void actionPerformed(ActionEvent e) {
		if(this.seleccionTurno.getBotonAgregar()==e.getSource()){
			this.aceptarInsercion();
		}
		if(this.seleccionTurno.getBotonReporteSemana()==e.getSource()){
			this.reporteSemanaOpcion();
		}
		if(this.seleccionTurno.getBotonAgregarMotivo()==e.getSource()){
			this.agregarMotivo();
		}
	}

	private void reporteSemanaOpcion() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		int diaCorriente = calendar.get(Calendar.DAY_OF_WEEK);
		int diasAnteriores = 0;
		int diasPosteriores = 0;
		switch(diaCorriente){
			case 1 : diasAnteriores = 0;
					 diasPosteriores = 6;
					 break;
			case 2 : diasAnteriores = 1;
			 		 diasPosteriores = 5;
			 		 break;
			case 3 : diasAnteriores = 2;
					 diasPosteriores = 4;
					 break;
			case 4 : diasAnteriores = 3;
			 		 diasPosteriores = 3;
			 		 break;
			case 5 : diasAnteriores = 4;
					 diasPosteriores = 2;
					 break;
			case 6 : diasAnteriores = 5;
					 diasPosteriores = 1;
					 break;
			case 7 : diasAnteriores = 6;
			 		 diasPosteriores = 0;
			 		 break;
		}
		LinkedList<Date> listaFecha = new LinkedList<Date>();
		int i = 1;
		for(i=1;i<=diasAnteriores;i++){
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			listaFecha.addFirst(calendar.getTime());
		}
		calendar.add(Calendar.DAY_OF_MONTH, diasAnteriores);
		listaFecha.addLast(calendar.getTime());
		i = 1;
		for(i=1;i<=diasPosteriores;i++){
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			listaFecha.addLast(calendar.getTime());
		}
		
		Object[][] datos = new Object[48][8];
		datos[0][0]= "8:00 - 8:15";datos[1][0]= "8:15 - 8:30";datos[2][0]= "8:30 - 8:45";
		datos[3][0]= "8:45 - 9:00";datos[4][0]= "9:00 - 9:15";datos[5][0]= "9:15 - 9:30";
		datos[6][0]= "9:30 - 9:45";datos[7][0]= "9:45 - 10:00";datos[8][0]= "10:00 - 10:15";
		datos[9][0]= "10:15 - 10:30";datos[10][0]="10:30 - 10:45";datos[11][0]="10:45 - 11:00";
		datos[12][0] = "11:00 - 11:15";datos[13][0] = "11:15 - 11:30";datos[14][0] = "11:30 - 11:45";
		datos[15][0] = "11:45 - 12:00";datos[16][0] = "12:00 - 12:15";datos[17][0] = "12:15 - 12:30";
		datos[18][0] = "12:30 - 12:45";datos[19][0] = "12:45 - 13:00";datos[20][0] = "13:00 - 13:15";
		datos[21][0] = "13:15 - 13:30";datos[22][0] = "13:30 - 13:45";datos[23][0] = "13.45 - 14:00";
		datos[24][0] = "14:00 - 14:15";datos[25][0] = "14:15 - 14:30";datos[26][0] = "14:30 - 14:45";
		datos[27][0] = "14:45 - 15:00";datos[28][0] = "15:00 - 15:15";datos[29][0] = "15:15 - 15:30";
		datos[30][0] = "15:30 - 15:45";datos[31][0] = "15:45 - 16:00";datos[32][0] = "16:00 - 16:15";
		datos[33][0] = "16:15 - 16:30";datos[34][0] = "16:30 - 16:45";datos[35][0] = "16:45 - 17:00";
		datos[36][0] = "17:00 - 17:15";datos[37][0] = "17:15 - 17:30";datos[38][0] = "17:30 - 17:45";
		datos[39][0] = "17:45 - 18:00";datos[40][0] = "18:00 - 18:15";datos[41][0] = "18:15 - 18:30";
		datos[42][0] = "18:30 - 18:45";datos[43][0] = "18:45 - 19:00";datos[44][0] = "19:00 - 19:15";
		datos[45][0] = "19:15 - 19:30";datos[46][0] = "19:30 - 19:45";datos[47][0] = "19:45 - 20:00";
		for(int j=0;j<7;j++){
			this.listarTurnosFecha((Date)listaFecha.get(j));
			for(i=0;i<48;i++){
				Turno turno = (Turno)this.seleccionTurno.getTableModel().getValueAt(i,1);
				if(turno == null)
					datos[i][j+1] = "";
				else
					datos[i][j+1] = turno.getAnimal().getNombre().toUpperCase();
			}
		}
		this.seleccionTurno.setVisible(false);
		seleccionTurno.setVisible(false);
		ReporteTurnoSemana viewReport = new ReporteTurnoSemana(seleccionTurno);
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
	}
	
	@SuppressWarnings("deprecation")
	public void mostrarSeleccionTurno(){
		this.seleccionTurno.show();
	}

	public void dateChanged(DateEvent e) {
		Calendar c = e.getSelectedDate();
		if (c != null) {
			fecha = c.getTime();
			this.listarTurnosFecha(fecha);
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public void listarTurnosFecha(Date fecha){
		try{
			lista1.clear();
			lista2.clear();
			persistencia.iniciarTransaccion();
			this.seleccionTurno.getTableModel().setRowCount(0);
			this.seleccionTurno.inicializarColumnaHolas(this.seleccionTurno.getTableModel());
			Collection turnos = persistencia.obtenerObjetosFecha(Turno.class,fecha.getYear()+1900,fecha.getMonth(),fecha.getDate());
			List<Turno> listaTurnos = new ArrayList<Turno>(turnos);
			List<Horario> listaHorarios;
			Collections.sort(listaTurnos);
			boolean sig = false;
			int fila;
	   	 	for(int i=0;i<listaTurnos.size();i++){
			  Turno t = (Turno) listaTurnos.get(i);
			  listaHorarios = new ArrayList<Horario>(t.obtenerHorarios());
			  if(sig){
				  for(int j=0;j<listaHorarios.size();j++){
					  fila = ((Horario)listaHorarios.get(j)).getFila();
					  lista2.addLast(fila);
					  this.seleccionTurno.getTableModel().setValueAt(t, fila, 1);
				  }
				  sig = false;
			  }else{
				  for(int j=0;j<listaHorarios.size();j++){
					  fila = ((Horario)listaHorarios.get(j)).getFila();
					  lista1.addLast(fila);
					  this.seleccionTurno.getTableModel().setValueAt(t, fila, 1);
				  }
				  sig = true;
			  }
			}
			this.seleccionTurno.colorearTabla(fecha,lista1,lista2,new LinkedList(),new LinkedList());
			persistencia.concretarTransaccion();
	}
	catch(Exception e){
		System.out.println("Error "+this.getClass().toString());
		e.printStackTrace();
		persistencia.deshacerTransaccion();
	}
	}
}
