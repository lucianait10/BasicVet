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

package cuGestionarCartilla;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Dosis;
import persistencia.dominio.Vacuna;


public class MediadorModificarDosisVacuna implements ActionListener {
	private int idDosisVac;
	private Dosis vacuna;
	private GUIDosisVacuna guiDosisVac;
	private GUIGestionarCartilla guiGestionarCartilla;
	private Persistencia persistencia = new Persistencia();
	private ControlCartilla controlCartilla;
	private DefaultTableModel modeloVac=new DefaultTableModel();
	private int fila;
	private int filaReal;


	public MediadorModificarDosisVacuna(DefaultTableModel modelo,GUIGestionarCartilla guiGestionarCart){
		super();
		this.modeloVac=modelo;
		this.guiGestionarCartilla=guiGestionarCart;
		this.inicializar();
	}

	public void inicializar(){
		controlCartilla= new ControlCartilla();
		fila = this.guiGestionarCartilla.getVacunas().getTablaVacuna().getSelectedRow();
		try{
			if(fila>=0){
				this.guiDosisVac = new GUIDosisVacuna(modeloVac);
				filaReal = this.guiGestionarCartilla.getVacunas().getTablaVacuna().convertRowIndexToModel(fila);
				idDosisVac = Integer.parseInt((String)this.modeloVac.getValueAt(filaReal, 0));
				persistencia.iniciarTransaccion();
				vacuna = controlCartilla.obtenerDosis(idDosisVac);
				this.guiDosisVac.getFecha().setDate(vacuna.getFecha());
				this.guiDosisVac.getFechaProx().setDate(vacuna.getFechaProxDosis());
				this.guiDosisVac.getTextoNombre().setText(((Vacuna)vacuna.getMedicamento()).getNombre().toUpperCase());
				this.guiDosisVac.getTextoVia().setText(vacuna.getVia().toUpperCase());
				this.guiDosisVac.getTextoCant().setText(Float.toString(vacuna.getCant()));
				this.guiDosisVac.getTextoLab().setText(((Vacuna)vacuna.getMedicamento()).getLaboratorio().toUpperCase());
				this.guiDosisVac.getTextoTipo().setText(((Vacuna)vacuna.getMedicamento()).getTipo().toUpperCase());
				this.guiDosisVac.getTextoSerie().setText(((Vacuna)(vacuna.getMedicamento())).getSerie().toUpperCase());
				
				this.guiDosisVac.getBotonAceptar().setEnabled(true);
				this.guiDosisVac.getBotonCancelar().setEnabled(true);
				this.guiDosisVac.setListenerButtons(this);
				
				persistencia.concretarTransaccion();
			}else{JOptionPane.showMessageDialog(this.guiGestionarCartilla, "SELECCIONE ALGUNA FILA");}
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}



	}


	@SuppressWarnings("deprecation")
	private void aceptarModificacion(){
		try{
			persistencia.iniciarTransaccion();
			vacuna = controlCartilla.obtenerDosis(idDosisVac);
			if(!(this.guiDosisVac.getFechaProx().getDate()==null)&&!(this.guiDosisVac.getFecha().getDate()==null)){
				if(this.fechaValida(this.guiDosisVac.getFechaProx().getDate())){
					
					//if(this.guiDosisVac.getFecha().getDate().equals(hoy)){
						
						vacuna.setFecha(this.guiDosisVac.getFecha().getDate());
						vacuna.setFechaProxDosis(this.guiDosisVac.getFechaProx().getDate());
						((Vacuna)vacuna.getMedicamento()).setNombre(this.guiDosisVac.getTextoNombre().getText().toLowerCase());
						vacuna.setVia(this.guiDosisVac.getTextoVia().getText().toLowerCase());
						if(!(this.guiDosisVac.getTextoCant().getText().isEmpty())){
							vacuna.setCant(Float.parseFloat(this.guiDosisVac.getTextoCant().getText()));
						}
						((Vacuna)vacuna.getMedicamento()).setLaboratorio(this.guiDosisVac.getTextoLab().getText().toLowerCase());
						((Vacuna)vacuna.getMedicamento()).setTipo(this.guiDosisVac.getTextoTipo().getText().toLowerCase());
						((Vacuna)vacuna.getMedicamento()).setSerie(this.guiDosisVac.getTextoSerie().getText().toLowerCase());
						/**Modifica la vacuna en la tabla de la cartilla*/
						this.modeloVac.setValueAt(vacuna.getId()+"", filaReal, 0);
						String fecha = vacuna.getFecha().getDate()+"/"+(vacuna.getFecha().getMonth()+1)+"/"+(vacuna.getFecha().getYear()+1900);
						this.modeloVac.setValueAt(fecha, filaReal, 1);
						String fechaProxDosis = vacuna.getFechaProxDosis().getDate()+"/"+(vacuna.getFechaProxDosis().getMonth()+1)+"/"+(vacuna.getFechaProxDosis().getYear()+1900);
						this.modeloVac.setValueAt(fechaProxDosis, filaReal, 2);
						this.modeloVac.setValueAt(vacuna.getMedicamento().getNombre().toUpperCase(), filaReal, 3);
						this.modeloVac.setValueAt(vacuna.getVia().toUpperCase(), filaReal, 4);
						this.modeloVac.setValueAt(vacuna.getMedicamento().getTipo().toUpperCase(), filaReal, 5);
						this.modeloVac.setValueAt(vacuna.getMedicamento().getLaboratorio().toUpperCase(), filaReal, 6);
						this.modeloVac.setValueAt(((Vacuna)vacuna.getMedicamento()).getSerie().toUpperCase(), filaReal, 7);
						this.modeloVac.setValueAt(vacuna.getMedicamento().getCantidad(), filaReal, 8);
						this.modeloVac.fireTableDataChanged();
						
						
			
						this.guiDosisVac.setVisible(false);
						this.guiDosisVac.dispose();
						
					//}else{JOptionPane.showMessageDialog(this.guiDosisVac,"NO ES FECHA ACTUAL");}
				}else{JOptionPane.showMessageDialog(this.guiDosisVac,"FECHA INVALIDA");}
			}else{JOptionPane.showMessageDialog(this.guiDosisVac,"LOS CAMPOS FECHA NO PUEDEN SER NULOS");}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
		
		

	}

	@SuppressWarnings("deprecation")
	private boolean fechaValida(Date fecha){
		Date fechaActual = new Date();
		fechaActual.setDate(fechaActual.getDate()-1);
		return fecha.after(fechaActual);
	}
	
	public void cancelarModificacion(){
		this.guiDosisVac.setVisible(false);
		this.guiDosisVac.dispose();
	}


	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiDosisVac.show();
	}

	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiDosisVac.getBotonAceptar() == source){
			this.aceptarModificacion();
		}
		if(this.guiDosisVac.getBotonCancelar() == source){
			this.cancelarModificacion();
		}

	}

}


