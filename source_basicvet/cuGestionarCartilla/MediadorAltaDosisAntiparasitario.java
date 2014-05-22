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
import persistencia.dominio.Antiparasitario;
import persistencia.dominio.Cartilla;
import persistencia.dominio.Dosis;
import persistencia.dominio.Medicamento;
import persistencia.dominio.Producto;
import cuGestionarProducto.ControlProducto;
import cuGestionarProducto.MediadorBuscarProducto;

public class MediadorAltaDosisAntiparasitario extends MediadorGeneral implements ActionListener {
	private GUIDosisAntiparasitario guiDosisAntiparasitario;
	private Medicamento antip;
	private Dosis dosisAntip;
	private ControlCartilla controlCartilla; 
	private ControlProducto controlProducto;
	private Persistencia persistencia = new Persistencia();
	private int idCartilla;
	private int codProducto;
	private DefaultTableModel modeloAntic;
	private Date hoy=new Date();  

	public MediadorAltaDosisAntiparasitario(int idCartilla,DefaultTableModel modelo){
		super();
		this.idCartilla=idCartilla;
		this.modeloAntic=modelo;
		this.inicializar();
	}

	public void inicializar(){
		try{
			controlCartilla= new ControlCartilla();
			controlProducto=new ControlProducto();
			this.guiDosisAntiparasitario = new GUIDosisAntiparasitario(modeloAntic);
			this.guiDosisAntiparasitario.setFecha(hoy);
			
			this.guiDosisAntiparasitario.getBotonAceptar().setEnabled(true);
			this.guiDosisAntiparasitario.getBotonCancelar().setEnabled(true);
			this.guiDosisAntiparasitario.setListenerButtons(this);

		}catch(Exception e){;
		this.guiDosisAntiparasitario.setVisible(false);  
		this.guiDosisAntiparasitario.dispose();
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
			if(p.getTipo().toLowerCase().compareTo("antiparasitario")==0){
				antip=controlProducto.obtenerAntiparasitario(codProducto);
				this.guiDosisAntiparasitario.getTextoNombre().setText(antip.getNombre().toUpperCase());
				this.guiDosisAntiparasitario.getTextoTipo().setText(antip.getTipo().toUpperCase());
			}else{JOptionPane.showMessageDialog(this.guiDosisAntiparasitario,"NO ES ANTIPARASITARIO");}
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
			this.antip= new Antiparasitario();
			this.antip = controlProducto.obtenerAntiparasitario(codProducto);
			dosisAntip=new Dosis();
			if(!(this.guiDosisAntiparasitario.getFechaProx().getDate()==null)&&!(this.guiDosisAntiparasitario.getFecha().getDate()==null)){
				if(this.fechaValida(this.guiDosisAntiparasitario.getFechaProx().getDate())){
					if(this.guiDosisAntiparasitario.getFecha().getDate().equals(hoy)){
						dosisAntip.setFecha(this.guiDosisAntiparasitario.getFecha().getDate());
						dosisAntip.setFechaProxDosis(this.guiDosisAntiparasitario.getFechaProx().getDate());
						dosisAntip.setVia(this.guiDosisAntiparasitario.getTextoVia().getText().toLowerCase());
						if(!(this.guiDosisAntiparasitario.getTextoCantidad().getText().isEmpty())){
							dosisAntip.setCant(Float.parseFloat(this.guiDosisAntiparasitario.getTextoCantidad().getText()));
						}
						dosisAntip.setMedicamento(this.antip);	
						dosisAntip=controlCartilla.guardarDosis(dosisAntip);
						/**Agrega la dosis a la tabla*/
						String id = (Integer.toString(dosisAntip.getId()));
						String fecha = dosisAntip.getFecha().getDate()+"/"+(dosisAntip.getFecha().getMonth()+1)+"/"+(dosisAntip.getFecha().getYear()+1900);
						String fechaProxDosis = dosisAntip.getFechaProxDosis().getDate()+"/"+(dosisAntip.getFechaProxDosis().getMonth()+1)+"/"+(dosisAntip.getFechaProxDosis().getYear()+1900);
						String via = dosisAntip.getVia().toUpperCase();
						String cant = Float.toString(dosisAntip.getCant());
						
						Antiparasitario a=(Antiparasitario)dosisAntip.getMedicamento();
						String nombre = a.getNombre().toUpperCase();
						String tipo = a.getTipo().toUpperCase();
						String[] s = {id,fecha,fechaProxDosis,nombre,via,tipo,cant};
						modeloAntic.addRow(s);
						/***************************************/
						c.agregarDosis(dosisAntip);
						this.guiDosisAntiparasitario.setVisible(false);
						this.guiDosisAntiparasitario.dispose();
					}else{JOptionPane.showMessageDialog(this.guiDosisAntiparasitario,"NO ES FECHA ACTUAL");}
				}else{JOptionPane.showMessageDialog(this.guiDosisAntiparasitario,"FECHA INVALIDA");}
			}else{JOptionPane.showMessageDialog(this.guiDosisAntiparasitario,"LOS CAMPOS FECHA NO PUEDEN SER NULOS");}
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
		this.guiDosisAntiparasitario.setVisible(false);
		this.guiDosisAntiparasitario.dispose();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiDosisAntiparasitario.show();
	}

	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiDosisAntiparasitario.getBotonAceptar() == source){
			this.aceptarInsercion();
		}
		if(this.guiDosisAntiparasitario.getBotonCancelar() == source){
			this.cancelarInsercion();
		}
		if(this.guiDosisAntiparasitario.getBotonBuscar() == source){
			this.buscarProducto();
		}

	}

}

