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
import persistencia.dominio.Cartilla;
import persistencia.dominio.Dosis;
import persistencia.dominio.Medicamento;
import persistencia.dominio.Producto;
import persistencia.dominio.Vacuna;
import cuGestionarProducto.ControlProducto;
import cuGestionarProducto.MediadorBuscarProducto;

public class MediadorAltaDosisVacuna extends MediadorGeneral implements ActionListener{
	private GUIDosisVacuna guiDosisVacuna;
	private Medicamento vacuna;
	private Dosis dosisVac;
	private Persistencia persistencia = new Persistencia();
	private ControlCartilla controlCartilla;
	private ControlProducto controlProducto;
	private int idCartilla;
	private int codProducto;
	private DefaultTableModel modeloVac;
	private Date hoy= new Date();

	public MediadorAltaDosisVacuna(int idCartilla,DefaultTableModel modelo){
		super();
		this.idCartilla=idCartilla;
		this.modeloVac=modelo;
		this.inicializar();	
	}

	public void inicializar(){
		try{
			this.guiDosisVacuna = new GUIDosisVacuna(modeloVac);
			controlCartilla= new ControlCartilla();
			controlProducto=new ControlProducto();
			this.guiDosisVacuna.setFecha(hoy);
			this.guiDosisVacuna.getBotonAceptar().setEnabled(true);
			this.guiDosisVacuna.getBotonCancelar().setEnabled(true);
			this.guiDosisVacuna.setListenerButtons(this);
		}catch(Exception e){;
		this.guiDosisVacuna.setVisible(false);
		this.guiDosisVacuna.dispose();
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
			if(p.getTipo().toLowerCase().compareTo("vacuna")==0){
				vacuna = controlProducto.obtenerVacuna(codProducto);
				this.guiDosisVacuna.getTextoNombre().setText(vacuna.getNombre().toUpperCase());
				this.guiDosisVacuna.getTextoLab().setText(vacuna.getLaboratorio().toUpperCase());
				this.guiDosisVacuna.getTextoTipo().setText(vacuna.getTipo().toUpperCase());
				this.guiDosisVacuna.getTextoSerie().setText(((Vacuna)vacuna).getSerie().toUpperCase());
			}else{JOptionPane.showMessageDialog(this.guiDosisVacuna,"NO ES VACUNA");}
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
			Cartilla c =controlCartilla.obtenerCartilla(idCartilla);
			this.vacuna= new Vacuna();
			this.vacuna = controlProducto.obtenerVacuna(codProducto);
			dosisVac=new Dosis();
			if(!(this.guiDosisVacuna.getFechaProx().getDate()==null)&&!(this.guiDosisVacuna.getFecha().getDate()==null)){
				if(this.fechaValida(this.guiDosisVacuna.getFechaProx().getDate())){
					if(this.guiDosisVacuna.getFecha().getDate().equals(hoy)){
						dosisVac.setFecha(this.guiDosisVacuna.getFecha().getDate());
						dosisVac.setFechaProxDosis(this.guiDosisVacuna.getFechaProx().getDate());
						dosisVac.setVia(this.guiDosisVacuna.getTextoVia().getText().toLowerCase());
						if(!(this.guiDosisVacuna.getTextoCant().getText().isEmpty())){
							dosisVac.setCant(Float.parseFloat(this.guiDosisVacuna.getTextoCant().getText()));
						}
						dosisVac.setMedicamento(this.vacuna);
						dosisVac=controlCartilla.guardarDosis(dosisVac);
						/**Agrega la dosis a la tabla*/
						String id = (Integer.toString(dosisVac.getId()));
						String fecha = dosisVac.getFecha().getDate()+"/"+(dosisVac.getFecha().getMonth()+1)+"/"+(dosisVac.getFecha().getYear()+1900);
						String fechaProxDosis = dosisVac.getFechaProxDosis().getDate()+"/"+(dosisVac.getFechaProxDosis().getMonth()+1)+"/"+(dosisVac.getFechaProxDosis().getYear()+1900);
						String via = dosisVac.getVia().toUpperCase();
						String cant = Float.toString(dosisVac.getCant());
						
						Vacuna v=(Vacuna)dosisVac.getMedicamento();
						String nombre = v.getNombre().toUpperCase();
						String tipo = v.getTipo().toUpperCase();
						String lab=v.getLaboratorio().toUpperCase();
						String serie= v.getSerie().toUpperCase();
						String[] s = {id,fecha,fechaProxDosis,nombre,via,tipo,lab,serie,cant};
						modeloVac.addRow(s);
						/***************************************/
						c.agregarDosis(dosisVac);						
						this.guiDosisVacuna.setVisible(false);
						this.guiDosisVacuna.dispose();
						
					}else{JOptionPane.showMessageDialog(this.guiDosisVacuna,"NO ES FECHA ACTUAL");}
				}else{JOptionPane.showMessageDialog(this.guiDosisVacuna,"FECHA INVALIDA");}
			}else{JOptionPane.showMessageDialog(this.guiDosisVacuna,"LOS CAMPOS FECHA NO PUEDEN SER NULOS");}
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
		this.guiDosisVacuna.setVisible(false);
		this.guiDosisVacuna.dispose();
	}


	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiDosisVacuna.show();
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiDosisVacuna.getBotonAceptar() == source){
			this.aceptarInsercion();
		}
		if(this.guiDosisVacuna.getBotonCancelar() == source){
			this.cancelarInsercion();
		}
		if(this.guiDosisVacuna.getBotonBuscar() == source){
			this.buscarProducto();
		}

	}

}
