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

package cuGestionarProducto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import persistencia.Persistencia;
import persistencia.dominio.Anticonceptivo;
import persistencia.dominio.Antiparasitario;
import persistencia.dominio.Producto;
import persistencia.dominio.Vacuna;


public class MediadorAltaProducto implements ActionListener {

	private GUIProducto guiProducto;
	private Persistencia persistencia= new Persistencia() ;
	private ControlProducto controlProducto;
	private DefaultTableModel modelo= new DefaultTableModel();

	public MediadorAltaProducto(DefaultTableModel m){
		super();
		this.inicializar();	
		this.modelo=m;
	}

	public void inicializar(){
		controlProducto= new ControlProducto();
		this.guiProducto = new GUIProducto(modelo);
		this.guiProducto.getBotonAceptar().setEnabled(true);
		this.guiProducto.getBotonCancelar().setEnabled(true);
		this.guiProducto.setListenerButtons(this);

	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		guiProducto.show();
	}



	private void cancelarInsercion() {
		this.guiProducto.setVisible(false);
		this.guiProducto.dispose();

	}


	private void aceptarInsercion(){
		try{
			persistencia.iniciarTransaccion();
			String nombre=this.guiProducto.getTextoNombre().getText().toLowerCase();
			String descripcion=this.guiProducto.getTextoDescripcion().getText().toLowerCase();
			float precio=0;
			int cant=0;
			if(!this.guiProducto.getTextoPrecio().getText().isEmpty()){
				precio=Float.parseFloat(this.guiProducto.getTextoPrecio().getText());
			}
			if(!this.guiProducto.getTextoCantidad().getText().isEmpty()){
				cant=Integer.parseInt(this.guiProducto.getTextoCantidad().getText());
			}
			if(this.guiProducto.getOpcionVacuna().isSelected()){
				String lab=this.guiProducto.getTextoLab().getText().toLowerCase();
				String serie= this.guiProducto.getTextoSerie().getText().toLowerCase();
				Vacuna v=new Vacuna(precio, nombre, descripcion, "vacuna", lab, serie,cant);
				v=controlProducto.guardarVacuna(v);
				this.modelo.addRow(new String[]{v.getId()+"",v.getNombre().toUpperCase(),v.getTipo().toUpperCase(),v.getDescripcion().toUpperCase(),Float.toString(v.getPrecio()),Integer.toString(v.getCantidad())});
	
			}else{if(this.guiProducto.getOpcionAntip().isSelected()){
				String lab=this.guiProducto.getTextoLab().getText().toLowerCase();
				Antiparasitario a=new Antiparasitario(precio, nombre, descripcion, "antiparasitario", lab,cant);
				a=controlProducto.guardarAntiparasitario(a);
				this.modelo.addRow(new String[]{a.getId()+"",a.getNombre().toUpperCase(),a.getTipo().toUpperCase(),a.getDescripcion().toUpperCase(),Float.toString(a.getPrecio()),Integer.toString(a.getCantidad())});
	
			}else{if(this.guiProducto.getOpcionAntic().isSelected()){
				String lab=this.guiProducto.getTextoLab().getText().toLowerCase();
				Anticonceptivo a=new Anticonceptivo(precio, nombre, descripcion, "anticonceptivo", lab,cant);
				a=controlProducto.guardarAnticonceptivo(a);
				this.modelo.addRow(new String[]{a.getId()+"",a.getNombre().toUpperCase(),a.getTipo().toUpperCase(),a.getDescripcion().toUpperCase(),Float.toString(a.getPrecio()),Integer.toString(a.getCantidad())});
			}else{
				Producto p=new Producto(precio, nombre, descripcion, this.guiProducto.getTextoOtro().getText().toLowerCase(),cant);
				p=controlProducto.guardarProducto(p);
				this.modelo.addRow(new String[]{p.getId()+"",p.getNombre().toUpperCase(),p.getTipo().toUpperCase(),p.getDescripcion().toUpperCase(),Float.toString(p.getPrecio()),Integer.toString(p.getCantidad())});
	
			}
			}
			}
	
			persistencia.concretarTransaccion();
			this.guiProducto.setVisible(false);
			this.guiProducto.dispose();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}

	}


	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiProducto.getOpcionVacuna() == source){
			this.guiProducto.getTextoLab().setEditable(true);
			this.guiProducto.getTextoLab().setBackground(Color.WHITE);
			this.guiProducto.getTextoSerie().setEditable(true);
			this.guiProducto.getTextoSerie().setBackground(Color.WHITE);
			this.guiProducto.getTextoOtro().setEditable(false);
			this.guiProducto.getTextoOtro().setBackground(new Color(238, 238, 238));
		}
		if(this.guiProducto.getOpcionAntic() == source){
			this.guiProducto.getTextoLab().setEditable(true);
			this.guiProducto.getTextoLab().setBackground(Color.WHITE);
			this.guiProducto.getTextoSerie().setEditable(false);
			this.guiProducto.getTextoSerie().setBackground(new Color(238, 238, 238));
			this.guiProducto.getTextoOtro().setEditable(false);
			this.guiProducto.getTextoOtro().setBackground(new Color(238, 238, 238));
		}
		if(this.guiProducto.getOpcionAntip() == source){
			this.guiProducto.getTextoLab().setEditable(true);
			this.guiProducto.getTextoLab().setBackground(Color.WHITE);
			this.guiProducto.getTextoSerie().setEditable(false);
			this.guiProducto.getTextoSerie().setBackground(new Color(238, 238, 238));
			this.guiProducto.getTextoOtro().setEditable(false);
			this.guiProducto.getTextoOtro().setBackground(new Color(238, 238, 238));
		}
		if(this.guiProducto.getOpcionOtro() == source){
			this.guiProducto.getTextoLab().setEditable(false);
			this.guiProducto.getTextoLab().setBackground(new Color(238, 238, 238));
			this.guiProducto.getTextoSerie().setEditable(false);
			this.guiProducto.getTextoSerie().setBackground(new Color(238, 238, 238));
			this.guiProducto.getTextoOtro().setEditable(true);
			this.guiProducto.getTextoOtro().setBackground(Color.WHITE);
		}
		if(this.guiProducto.getBotonAceptar() == source){
			this.aceptarInsercion();
		}
		if(this.guiProducto.getBotonCancelar() == source){
			this.cancelarInsercion();
		}

	}


}
