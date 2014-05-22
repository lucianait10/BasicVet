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

package cuGestionarCompra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import mediador.MediadorGeneral;
import persistencia.Persistencia;
import persistencia.dominio.Compra;
import persistencia.dominio.Producto;
import persistencia.dominio.Proveedor;
import cuGestionarProducto.ControlProducto;
import cuGestionarProducto.MediadorBuscarProducto;
import cuGestionarProveedor.ControlProveedor;
import cuGestionarProveedor.MediadorIngresarProveedor;

@SuppressWarnings("unused")
public class MediadorAltaCompra extends MediadorGeneral implements ActionListener {

	private GUICompra guiAltaCompra;
	private Persistencia persistencia= new Persistencia() ;
	private ControlCompra controlCompra;
	private ControlProducto controlProducto;
	private ControlProveedor controlProveedor;
	private DefaultTableModel tablaCompras= new DefaultTableModel();
	private DefaultTableModel modeloProv= new DefaultTableModel();
	private Date hoy;
	private int codProducto;
	
	public MediadorAltaCompra(DefaultTableModel t){
		super();
		this.tablaCompras=t;
		this.inicializar();	
	}
	
	@SuppressWarnings("deprecation")
	public void mostrar(){
		guiAltaCompra.show();
	}
	
	private void cancelarInsercion() {
		this.guiAltaCompra.setVisible(false);
		this.guiAltaCompra.dispose();
		
	}
	
	@SuppressWarnings("deprecation")
	public void inicializar(){
		controlCompra=new ControlCompra();
		controlProducto= new ControlProducto();
		controlProveedor = new ControlProveedor();
		this.guiAltaCompra = new GUICompra(tablaCompras);
		this.guiAltaCompra.setTitle("ALTA COMPRA");
		hoy=new Date();
		String f="";
		f=hoy.getDate()+"/"+(hoy.getMonth()+1)+"/"+(hoy.getYear()+1900);
		this.guiAltaCompra.getTextoCantProd().setText(null);
		this.guiAltaCompra.getTextoFecha().setText(f);
		this.cargarProveedores();		
		this.guiAltaCompra.getBotonAceptar().setEnabled(true);
		this.guiAltaCompra.getBotonCancelar().setEnabled(true);
		this.guiAltaCompra.getBotonBuscar().setEnabled(true);
		this.guiAltaCompra.getBotonAgregarProv().setEnabled(true);
		this.guiAltaCompra.setListenerButtons(this);
	
	}
	
	private void cargarProveedores(){
		try{
			persistencia.iniciarTransaccion();
			Iterator<Proveedor> iterProveedor =controlProveedor.obtenerProveedores();
			while(iterProveedor.hasNext()){
				Proveedor prov = iterProveedor.next();
				this.guiAltaCompra.getComboProveedor().addItem(prov.getDni()+"-"+prov.getNombre().toUpperCase());
			}
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void aceptarCompra(){
		try{
			persistencia.iniciarTransaccion();
	
			//Obtencion de los datos ingresado para agregarlos al modelo de la tabla compras
			String id=this.guiAltaCompra.getTextoIdProd().getText();
			String f=guiAltaCompra.getTextoFecha().getText();
			float precio=0;
			if(!guiAltaCompra.getTextoPrecio().getText().isEmpty()){
				precio=Float.parseFloat(guiAltaCompra.getTextoPrecio().getText());
			}
			int cant=0;
			if(!this.guiAltaCompra.getTextoCantProd().getText().isEmpty()){
				cant=Integer.parseInt(this.guiAltaCompra.getTextoCantProd().getText());
			}
			String nombreProv=this.guiAltaCompra.getComboProveedor().getSelectedItem().toString().toUpperCase();
			String nombreProd=this.guiAltaCompra.getTextoNombreProd().getText().toUpperCase();
			Proveedor prov=controlProveedor.obtenerProveedor(nombreProv.split("-")[0]);
			Producto prod=controlProducto.obtenerProducto(Integer.parseInt(id));
			//actualiza cantidad producto
			prod.setCantidad(cant+prod.getCantidad());
			//calcula total de compra
			float total=precio*cant;
			
			Compra c= new Compra(total,precio,cant, hoy, prov, prod);
			c=controlCompra.guardarCompra(c);
			//agrega la compra al modelo
			this.tablaCompras.addRow(new String[]{c.getId()+"",f,nombreProv,nombreProd,cant+"",precio+"",total+""});
			
			persistencia.concretarTransaccion();
			
			this.guiAltaCompra.setVisible(false);
			this.guiAltaCompra.dispose();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
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
			Producto prod=controlProducto.obtenerProducto(codProducto);
			this.guiAltaCompra.getTextoNombreProd().setText(prod.getNombre().toUpperCase());
			this.guiAltaCompra.getTextoIdProd().setText(prod.getId()+"");		
			persistencia.concretarTransaccion();
		}
		catch(Exception e){
			System.out.println("Error "+this.getClass().toString());
			e.printStackTrace();
			persistencia.deshacerTransaccion();
		}
	}
	
	public void agregarProveedor(){
		MediadorIngresarProveedor medIngresarProv= new MediadorIngresarProveedor(this.modeloProv);
		medIngresarProv.mostrar();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(this.guiAltaCompra.getBotonAgregarProv()== source){
			this.agregarProveedor();
			//this.cargarProveedores();
		}
		if(this.guiAltaCompra.getBotonBuscar()== source){
			this.buscarProducto();
		}
		if(this.guiAltaCompra.getBotonAceptar()== source){
			this.aceptarCompra();
		}
		if(this.guiAltaCompra.getBotonCancelar()== source){
			this.cancelarInsercion();
		}
		
	}

	

}
