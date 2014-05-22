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
import persistencia.dominio.Antiparasitario;
import persistencia.dominio.Dosis;


public class MediadorModificarDosisAntiparasitario implements ActionListener {
	private int idDosisAntip;
	private GUIDosisAntiparasitario guiDosisAntip;
	private GUIGestionarCartilla guiGestionarCartilla;
	private Dosis antiparasitario;
	private Persistencia persistencia = new Persistencia();
	private ControlCartilla controlCartilla;
	private DefaultTableModel modeloAntip=new DefaultTableModel();
	private int fila;
	private int filaReal;
	private Date hoy= new Date();

	public MediadorModificarDosisAntiparasitario(DefaultTableModel modelo,GUIGestionarCartilla guiGestionarCart){
		super();
		this.modeloAntip=modelo;
		this.guiGestionarCartilla=guiGestionarCart;
		this.inicializar();
	}

	public void inicializar(){
		controlCartilla= new ControlCartilla();
		fila = this.guiGestionarCartilla.getAntiparasitarios().getTablaAntiparas().getSelectedRow();
		try{
			if(fila>=0){
				persistencia.iniciarTransaccion();
				this.guiDosisAntip = new GUIDosisAntiparasitario(modeloAntip);
				filaReal = this.guiGestionarCartilla.getAntiparasitarios().getTablaAntiparas().convertRowIndexToModel(fila);
				idDosisAntip = Integer.parseInt((String)this.modeloAntip.getValueAt(filaReal, 0));
				antiparasitario = controlCartilla.obtenerDosis(idDosisAntip);
				this.guiDosisAntip.getFecha().setDate(antiparasitario.getFecha());
				this.guiDosisAntip.getFechaProx().setDate(antiparasitario.getFechaProxDosis());
				this.guiDosisAntip.getTextoNombre().setText(((Antiparasitario)antiparasitario.getMedicamento()).getNombre().toUpperCase());
				this.guiDosisAntip.getTextoCantidad().setText(Float.toString(antiparasitario.getCant()));		
				this.guiDosisAntip.getTextoVia().setText(antiparasitario.getVia().toUpperCase());
				this.guiDosisAntip.getTextoTipo().setText(antiparasitario.getMedicamento().getTipo().toUpperCase());
				this.guiDosisAntip.getBotonAceptar().setEnabled(true);
				this.guiDosisAntip.getBotonCancelar().setEnabled(true);
				this.guiDosisAntip.setListenerButtons(this);
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
	public void mostrar(){
		this.guiDosisAntip.show();
	}

	@SuppressWarnings("deprecation")
	private void aceptarModificacion(){
		try{
			persistencia.iniciarTransaccion();
			antiparasitario = controlCartilla.obtenerDosis(idDosisAntip);
			if(!(this.guiDosisAntip.getFechaProx().getDate()==null)&&!(this.guiDosisAntip.getFecha().getDate()==null)){
				if(this.fechaValida(this.guiDosisAntip.getFechaProx().getDate())){
					if(this.guiDosisAntip.getFecha().getDate().equals(hoy)){
						antiparasitario.setFecha(this.guiDosisAntip.getFecha().getDate());
						antiparasitario.setFechaProxDosis(this.guiDosisAntip.getFechaProx().getDate());
						((Antiparasitario)antiparasitario.getMedicamento()).setNombre(this.guiDosisAntip.getTextoNombre().getText().toLowerCase());
						if(!(this.guiDosisAntip.getTextoCantidad().getText().isEmpty())){
							antiparasitario.setCant(Float.parseFloat(this.guiDosisAntip.getTextoCantidad().getText()));
						}
						antiparasitario.setVia(this.guiDosisAntip.getTextoVia().getText().toLowerCase());
						((Antiparasitario)antiparasitario.getMedicamento()).setTipo(this.guiDosisAntip.getTextoTipo().getText().toLowerCase());
						/**Modifica el antiparasitario en la tabla de la cartilla*/
						this.modeloAntip.setValueAt(antiparasitario.getId()+"", filaReal, 0);
						String fecha = antiparasitario.getFecha().getDate()+"/"+(antiparasitario.getFecha().getMonth()+1)+"/"+(antiparasitario.getFecha().getYear()+1900);
						this.modeloAntip.setValueAt(fecha, filaReal, 1);
						String fechaProxDosis = antiparasitario.getFechaProxDosis().getDate()+"/"+(antiparasitario.getFechaProxDosis().getMonth()+1)+"/"+(antiparasitario.getFechaProxDosis().getYear()+1900);
						this.modeloAntip.setValueAt(fechaProxDosis, filaReal, 2);
						this.modeloAntip.setValueAt(antiparasitario.getMedicamento().getNombre().toUpperCase(), filaReal, 3);
						this.modeloAntip.setValueAt(antiparasitario.getVia().toUpperCase(), filaReal, 4);
						this.modeloAntip.setValueAt(antiparasitario.getMedicamento().getTipo().toUpperCase(), filaReal, 5);
						this.modeloAntip.setValueAt(antiparasitario.getMedicamento().getCantidad(), filaReal, 6);
						this.modeloAntip.fireTableDataChanged();
						this.guiDosisAntip.setVisible(false);
						this.guiDosisAntip.dispose();
						
					}else{JOptionPane.showMessageDialog(this.guiDosisAntip,"NO ES FECHA ACTUAL");}
				}else{JOptionPane.showMessageDialog(this.guiDosisAntip,"FECHA INVALIDA");}
			}else{JOptionPane.showMessageDialog(this.guiDosisAntip,"LOS CAMPOS FECHA NO PUEDEN SER NULOS");}
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
		this.guiDosisAntip.setVisible(false);
		this.guiDosisAntip.dispose();
	}


	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiDosisAntip.getBotonAceptar() == source){
			this.aceptarModificacion();
		}
		if(this.guiDosisAntip.getBotonCancelar() == source){
			this.cancelarModificacion();
		}

	}

}


