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
import persistencia.dominio.Anticonceptivo;
import persistencia.dominio.Dosis;

public class MediadorModificarDosisAnticonceptivo implements ActionListener {
	private int idDosisAntic;
	private Dosis anticonceptivo;
	private GUIDosisAnticonceptivo guiDosisAntic;
	private GUIGestionarCartilla guiGestionarCartilla;
	private Persistencia persistencia = new Persistencia();
	private ControlCartilla controlCartilla;
	private DefaultTableModel modeloAntic=new DefaultTableModel();
	private int fila;
	private int filaReal;
	private Date hoy= new Date();
	

	public MediadorModificarDosisAnticonceptivo(DefaultTableModel modelo,GUIGestionarCartilla guiGestionarCart){
		super();
		this.modeloAntic=modelo;
		this.guiGestionarCartilla=guiGestionarCart;
		this.inicializar();
	}

	public void inicializar(){
		controlCartilla= new ControlCartilla();
		fila = this.guiGestionarCartilla.getAnticonceptivos().getTablaAnticonc().getSelectedRow();
		try{
			if(fila>=0){
				this.guiDosisAntic = new GUIDosisAnticonceptivo(modeloAntic);
				filaReal = this.guiGestionarCartilla.getAnticonceptivos().getTablaAnticonc().convertRowIndexToModel(fila);
				idDosisAntic = Integer.parseInt((String)this.modeloAntic.getValueAt(filaReal, 0));
				persistencia.iniciarTransaccion();
				anticonceptivo =controlCartilla.obtenerDosis(idDosisAntic);
				this.guiDosisAntic.getFecha().setDate(anticonceptivo.getFecha());
				this.guiDosisAntic.getFechaProx().setDate(anticonceptivo.getFechaProxDosis());
				this.guiDosisAntic.getTextoNombre().setText(((Anticonceptivo)anticonceptivo.getMedicamento()).getNombre().toUpperCase());
				this.guiDosisAntic.getTextoVia().setText(anticonceptivo.getVia().toUpperCase());
				this.guiDosisAntic.getTextoLab().setText(((Anticonceptivo)anticonceptivo.getMedicamento()).getLaboratorio().toUpperCase());
				this.guiDosisAntic.getTextoTipo().setText(((Anticonceptivo)anticonceptivo.getMedicamento()).getTipo().toUpperCase());
				this.guiDosisAntic.getTextoCant().setText(Float.toString(anticonceptivo.getCant()));
				
				this.guiDosisAntic.getBotonAceptar().setEnabled(true);
				this.guiDosisAntic.getBotonCancelar().setEnabled(true);
				this.guiDosisAntic.setListenerButtons(this);
				
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
			anticonceptivo = controlCartilla.obtenerDosis(idDosisAntic);
			if(!(this.guiDosisAntic.getFechaProx().getDate()==null)&&!(this.guiDosisAntic.getFecha().getDate()==null)){
				if(this.fechaValida(this.guiDosisAntic.getFechaProx().getDate())){
					if(this.guiDosisAntic.getFecha().getDate().equals(hoy)){
						anticonceptivo.setFecha(this.guiDosisAntic.getFecha().getDate());
						anticonceptivo.setFechaProxDosis(this.guiDosisAntic.getFechaProx().getDate());
						((Anticonceptivo)anticonceptivo.getMedicamento()).setNombre(this.guiDosisAntic.getTextoNombre().getText().toLowerCase());
						anticonceptivo.setVia(this.guiDosisAntic.getTextoVia().getText().toLowerCase());
						((Anticonceptivo)anticonceptivo.getMedicamento()).setLaboratorio(this.guiDosisAntic.getTextoLab().getText().toLowerCase());
						((Anticonceptivo)anticonceptivo.getMedicamento()).setTipo(this.guiDosisAntic.getTextoTipo().getText().toLowerCase());
						if(!(this.guiDosisAntic.getTextoCant().getText().isEmpty())){
							anticonceptivo.setCant(Float.parseFloat(this.guiDosisAntic.getTextoCant().getText().toLowerCase()));
						}
						/**Modifica el anticonceptivo en la tabla de la cartilla*/
						this.modeloAntic.setValueAt(anticonceptivo.getId()+"", filaReal, 0);
						String fecha = anticonceptivo.getFecha().getDate()+"/"+(anticonceptivo.getFecha().getMonth()+1)+"/"+(anticonceptivo.getFecha().getYear()+1900);
						this.modeloAntic.setValueAt(fecha, filaReal, 1);
						String fechaProxDosis = anticonceptivo.getFechaProxDosis().getDate()+"/"+(anticonceptivo.getFechaProxDosis().getMonth()+1)+"/"+(anticonceptivo.getFechaProxDosis().getYear()+1900);
						this.modeloAntic.setValueAt(fechaProxDosis, filaReal, 2);
						this.modeloAntic.setValueAt(anticonceptivo.getMedicamento().getNombre().toUpperCase(), filaReal, 3);
						this.modeloAntic.setValueAt(anticonceptivo.getVia().toUpperCase(), filaReal, 4);
						this.modeloAntic.setValueAt(anticonceptivo.getMedicamento().getTipo().toUpperCase(), filaReal, 5);
						this.modeloAntic.setValueAt(anticonceptivo.getMedicamento().getLaboratorio().toUpperCase(), filaReal, 6);
						this.modeloAntic.setValueAt(anticonceptivo.getMedicamento().getCantidad(), filaReal, 7);
						this.modeloAntic.fireTableDataChanged();

						this.guiDosisAntic.setVisible(false);
						this.guiDosisAntic.dispose();
					}else{JOptionPane.showMessageDialog(this.guiDosisAntic,"NO ES FECHA ACTUAL");}
				}else{JOptionPane.showMessageDialog(this.guiDosisAntic,"FECHA INVALIDA");}
			}else{JOptionPane.showMessageDialog(this.guiDosisAntic,"LOS CAMPOS FECHA NO PUEDEN SER NULOS");}
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
		this.guiDosisAntic.setVisible(false);
		this.guiDosisAntic.dispose();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiDosisAntic.show();
	}

	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiDosisAntic.getBotonAceptar() == source){
			this.aceptarModificacion();
		}
		if(this.guiDosisAntic.getBotonCancelar() == source){
			this.cancelarModificacion();
		}

	}

}


