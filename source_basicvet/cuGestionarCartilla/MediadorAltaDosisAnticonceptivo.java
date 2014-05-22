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

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Anticonceptivo;
import persistencia.dominio.Cartilla;
import persistencia.dominio.Dosis;
import persistencia.dominio.Medicamento;
import persistencia.dominio.Producto;
import cuGestionarProducto.ControlProducto;
import cuGestionarProducto.MediadorBuscarProducto;

public class MediadorAltaDosisAnticonceptivo extends MediadorGeneral implements ActionListener {
	private GUIDosisAnticonceptivo guiDosisAnticonceptivo;
	private Medicamento antic;
	private Dosis dosisAntic;
	private Persistencia persistencia = new Persistencia();
	private ControlCartilla controlCartilla;
	private ControlProducto controlProducto;
	private int idCartilla;
	private int codProducto;
	private Date hoy= new Date();
	private DefaultTableModel tablaAntic=new DefaultTableModel();

	public MediadorAltaDosisAnticonceptivo(int idCartilla,DefaultTableModel modelo){
		super();
		this.inicializar();
		this.idCartilla=idCartilla;
		this.tablaAntic=modelo;
	}

	public void inicializar(){
		try{
			controlCartilla=new ControlCartilla();
			controlProducto= new ControlProducto();
			this.guiDosisAnticonceptivo = new GUIDosisAnticonceptivo(tablaAntic);
			this.guiDosisAnticonceptivo.setFecha(hoy);
			this.guiDosisAnticonceptivo.getBotonAceptar().setEnabled(true);
			this.guiDosisAnticonceptivo.getBotonCancelar().setEnabled(true);
			this.guiDosisAnticonceptivo.setListenerButtons(this);

		}catch(Exception e){;
		this.guiDosisAnticonceptivo.setVisible(false);
		this.guiDosisAnticonceptivo.dispose();
		}
	}



	public void buscarProducto(){
		MediadorBuscarProducto medBuscarProd= new MediadorBuscarProducto(this);
		medBuscarProd.mostrar();
	}

	
	public void setProductoBuscado(int codProducto){
		this.codProducto = codProducto;
		try{
			persistencia.iniciarTransaccion();
			Producto p=controlProducto.obtenerProducto(codProducto);
			if(p.getTipo().toLowerCase().compareTo("anticonceptivo")==0){
				Anticonceptivo a=controlProducto.obtenerAnticonceptivo(codProducto);
				this.guiDosisAnticonceptivo.getTextoNombre().setText(a.getNombre().toUpperCase());
				this.guiDosisAnticonceptivo.getTextoLab().setText(a.getLaboratorio().toUpperCase());
				this.guiDosisAnticonceptivo.getTextoTipo().setText(a.getTipo().toUpperCase());		
			}else{JOptionPane.showMessageDialog(this.guiDosisAnticonceptivo,"NO ES ANTICONCEPTIVO");}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}

	@SuppressWarnings("deprecation")
	public void aceptarInsercion(){
		try{
			persistencia.iniciarTransaccion();
			Cartilla c=controlCartilla.obtenerCartilla(idCartilla);
			this.antic= new Anticonceptivo();
			this.antic= controlProducto.obtenerAnticonceptivo(codProducto);
			dosisAntic= new Dosis();
			if(!(this.guiDosisAnticonceptivo.getFechaProx().getDate()==null)&&!(this.guiDosisAnticonceptivo.getFecha().getDate()==null)){
				if(this.fechaValida(this.guiDosisAnticonceptivo.getFechaProx().getDate())){
					if(this.guiDosisAnticonceptivo.getFecha().getDate().equals(hoy)){
						dosisAntic.setFecha(this.guiDosisAnticonceptivo.getFecha().getDate());
						dosisAntic.setFechaProxDosis(this.guiDosisAnticonceptivo.getFechaProx().getDate());
						dosisAntic.setVia(this.guiDosisAnticonceptivo.getTextoVia().getText().toLowerCase());
						if(!(this.guiDosisAnticonceptivo.getTextoCant().getText().isEmpty())){
							dosisAntic.setCant(Float.parseFloat(this.guiDosisAnticonceptivo.getTextoCant().getText()));
						}
						dosisAntic.setMedicamento(this.antic);
						dosisAntic=controlCartilla.guardarDosis(dosisAntic);
						/**Agrega la dosis a la tabla*/
						String id = (Integer.toString(dosisAntic.getId()));
						String fecha = dosisAntic.getFecha().getDate()+"/"+(dosisAntic.getFecha().getMonth()+1)+"/"+(dosisAntic.getFecha().getYear()+1900);
						String fechaProxDosis = dosisAntic.getFechaProxDosis().getDate()+"/"+(dosisAntic.getFechaProxDosis().getMonth()+1)+"/"+(dosisAntic.getFechaProxDosis().getYear()+1900);
						String via = dosisAntic.getVia().toUpperCase();
						String cant = Float.toString(dosisAntic.getCant());
						
						Anticonceptivo a=(Anticonceptivo)dosisAntic.getMedicamento();
						String nombre = a.getNombre().toUpperCase();
						String tipo = a.getTipo().toUpperCase();
						String lab = a.getLaboratorio().toUpperCase();
						String[] s = {id,fecha,fechaProxDosis,nombre,via,tipo,lab,cant};
						tablaAntic.addRow(s);
						/***************************************/
						c.agregarDosis(dosisAntic);
						this.guiDosisAnticonceptivo.setVisible(false);
						this.guiDosisAnticonceptivo.dispose();
						
					}else{JOptionPane.showMessageDialog(this.guiDosisAnticonceptivo,"NO ES FECHA ACTUAL");}
				}else{JOptionPane.showMessageDialog(this.guiDosisAnticonceptivo,"FECHA INVALIDA");}
			}else{JOptionPane.showMessageDialog(this.guiDosisAnticonceptivo,"LOS CAMPOS FECHA NO PUEDEN SER NULOS");}
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


	public void cancelarInsercion(){
		this.guiDosisAnticonceptivo.setVisible(false);
		this.guiDosisAnticonceptivo.dispose();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiDosisAnticonceptivo.show();
	}


	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiDosisAnticonceptivo.getBotonAceptar() == source){
			this.aceptarInsercion();
		}
		if(this.guiDosisAnticonceptivo.getBotonCancelar() == source){
			this.cancelarInsercion();
		}
		if(this.guiDosisAnticonceptivo.getBotonBuscar() == source){
			this.buscarProducto();
		}

	}

}


